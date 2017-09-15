package com.hmmes.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Collections; 

import net.sf.json.JSONObject;
//import net.sf.json.JSON;
import org.json.JSONArray;
import com.alibaba.fastjson.JSON;  
//import com.alibaba.fastjson.JSONObject;  
import com.alibaba.fastjson.annotation.JSONField;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.context.*;
import org.springframework.web.context.support.*;

import com.hmmes.bean.JsonBean;
import com.hmmes.bean.ZbggBean;
import com.hmmes.bean.CgflBean;
import com.hmmes.bean.ZbwzBean;
import com.hmmes.bean.TbxxBean;
import com.hmmes.bean.GysdaBean;


import com.hmmes.bean.BaseBean.DELETED;

import com.hmmes.model.ZbggModel;
import com.hmmes.model.TbxxModel;


import com.hmmes.service.ZbggService;
import com.hmmes.service.GysdaService;
import com.hmmes.service.TbxxService;



import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.SessionUtils;
import com.hmmes.utils.DateUtil;
import com.hmmes.utils.StringUtil;



 
@Controller
@RequestMapping("/zbManage") 
public class ZbqrAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(ZbggAction.class);
	
	// Servrice start
	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private ZbggService<ZbggBean> zbggService; 
	

	@Autowired
	private TbxxService<TbxxBean> tbxxService;	
	@Autowired 
	private GysdaService<GysdaBean> gysdaService; 


	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 */

	@RequestMapping("/zb")
	public ModelAndView  zbconfirm(ZbggModel model,HttpServletRequest request) throws Exception{
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         rightNow.add(Calendar.MONTH,-1);//����-1����,�����¼ƻ�
         //rightNow.add(Calendar.DAY_OF_YEAR,-2);//����-2��
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		
		Map<String,Object>  context = getRootMap();
		model.setDeleted(DELETED.NO.key);
		List<ZbggBean> dataList = zbggService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("cgpt/zbManage",context); 
	}	
     /**
	 * json �б�ҳ��
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList")   
	public void  dataList(ZbggModel model,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		model.setKb(1);
		List<ZbggBean> dataList = zbggService.queryByList(model);
		List<ZbggBean> result = new ArrayList<ZbggBean>();

		for (Object ele : dataList)
		{
			ZbggBean st = (ZbggBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��ZbggAction--dataList"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}

	@RequestMapping("/dataListDist")   
	public void  dataListDist(ZbggModel model,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		model.setKb(1);
		List<ZbggBean> dataList = zbggService.queryByListDist(model);
		List<ZbggBean> result = new ArrayList<ZbggBean>();

		for (Object ele : dataList)
		{
			ZbggBean st = (ZbggBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��ZbggAction--dataList"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}


	@RequestMapping("/dataListForGgbh")   //�����б깫���ţ���ѯ�б�������ʾ��ϸDataGrid 
	public void  dataListForGgbhAndGysId(String ggbh,HttpServletRequest request,HttpServletResponse response) throws Exception{

		JSONObject context = new JSONObject();	
		Integer kb=0; 
		List<ZbggBean> dataListzb=zbggService.queryListByGgbh(ggbh);
		if (dataListzb!=null && dataListzb.size()>0 )
		{
			kb=dataListzb.get(0).getKb();
		}
		List<TbxxBean> dataList = tbxxService.queryListByGgbh(ggbh);
		List<TbxxBean> result = new ArrayList<TbxxBean>();

		for (TbxxBean bean : dataList)
		{
			GysdaBean gysbean=gysdaService.queryById(bean.getGysId());
		    String gysStr=gysbean.getName();
	        bean.setGysStr(gysStr);
			bean.setKb(kb);
			result.add(bean);
		}
	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��tbxx"+jsonStr);
        context.put("tbxx",jsonStr);
		context.put(SUCCESS, true);	
	    HtmlUtil.writerJson(response, context);

		return ;
	}

 
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		ZbggBean bean  = zbggService.queryById(id);
		String ggbh= bean.getGgbh()	;
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
	    String username = SessionUtils.getUser(request).getNickName();
		String lrr=bean.getCreateBy();
		if (!username.equals(lrr)){
			sendFailureMessage(response, "���б깫��Ϊ("+lrr+")����������ѡ����Ӧ��!");
			return;
		}

	    if(bean.getState()!= 1 ){
			sendFailureMessage(response, "���б깫������ͣ������,����ѡ����Ӧ��!");
			return;
		}
	    if(bean.getSp()!= null && bean.getSp()== 1 ){
			sendFailureMessage(response, "���б깫��������,����ѡ����Ӧ��!");
			return;
		}
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    List<TbxxBean> dataList = tbxxService.queryListByGgbh(ggbh);

	    if (dataList!=null &&  dataList.size()>0 )
		{
			List<TbxxBean> result = new ArrayList<TbxxBean>();	
		    for (TbxxBean tbbean : dataList)
		    {
  			    result.add(tbbean);
		    }
		    JSONArray jsonArr= new JSONArray(result);
		    String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
		    context.put("json",jsonStr);
		    Map<String,Object>  data = new HashMap<String,Object> ();
		
            data.put("ggbh", ggbh);
			data.put("wlfl", bean.getWlfl());
	        data.put("fbrq", bean.getFbrq()!=null?(df.format(bean.getFbrq())):"");
		  	data.put("yxrq", bean.getYxrq()!=null?(df1.format(bean.getYxrq())):"");
		    data.put("jhrq_xq",bean.getJhrq_xq()!=null?(df.format(bean.getJhrq_xq())):"");
            context.put("data", data);
		    context.put(SUCCESS, true);	
		
//System.out.println("����ɽ-ZbggAction--dataListById-context"+context.toString());
		    HtmlUtil.writerJson(response, context);

		}
		return ;
	}

 	/**
	 * ��ӻ��޸�����
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void  save(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 String ggbh  = request.getParameter("ggbh");
		 //String yxrq  = request.getParameter("yxrq");
		 //String fbrq  = request.getParameter("fbrq");
		 //String wlfl  = request.getParameter("wlfl");
		 //String jhrq_xq  = request.getParameter("jhrq_xq");
//System.out.println("����ɽ��ggbh="+ggbh);
//System.out.println("����ɽ��ZbggAction--add:wlfl="+wlfl);
//System.out.println("����ɽ��ZbggAction--add:jhbh="+jhbh);
//System.out.println("����ɽ��ZbggAction--add:jhrq_kh="+jhrq_kh);
//System.out.println("����ɽ��ZbggAction--add:insert"+inserted);
//System.out.println("����ɽ��ZbggAction--add:delete"+deleted);
//System.out.println("����ɽ��ZbggAction--add:update"+updated);
		 String username = SessionUtils.getUser(request).getNickName();
         if(updated != null){  
             //��json�ַ���ת���ɶ���  
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	         String sdate;
		     Date ddate=null;
             sdate=sdf1.format(new Date());	
		     try {
    	         ddate = sdf1.parse(sdate);
             } catch (ParseException e) {
                 e.printStackTrace();
             }
		    List<TbxxBean> listUpdated = new ArrayList<TbxxBean>();	
            listUpdated = JSON.parseArray(updated, TbxxBean.class);  
 	        for (TbxxBean bean : listUpdated )
		    {
				 Double dj=bean.getDj()==null?0.0:bean.getDj();
				 Double tbsl=bean.getTbsl()==null?0.0:bean.getTbsl();
				 bean.setJe(dj*tbsl);
				 tbxxService.updateBean(bean);
		    }
		    List<ZbggBean> dataListzb = zbggService.queryListByGgbh(ggbh);
			for (ZbggBean zbbean : dataListzb){ 
                 zbbean.setQd(1);
				zbbean.setQdBy(username);	
				zbbean.setQdTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
				zbggService.updateBean(zbbean);
			}
			
	         sendSuccessMessage(response, "����ɹ�~");
		     return ; 
		 }
	}
 
}
