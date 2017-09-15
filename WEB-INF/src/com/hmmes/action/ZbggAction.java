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

import com.hmmes.bean.JhbhBean;

import com.hmmes.bean.BaseBean.DELETED;

import com.hmmes.model.ZbggModel;


import com.hmmes.service.JhbhService;
import com.hmmes.service.ZbggService;

import com.hmmes.utils.HtmlUtil;

import com.hmmes.utils.SessionUtils;
import com.hmmes.utils.DateUtil;
import com.hmmes.utils.StringUtil;



 
@Controller
@RequestMapping("/zbggManage") 
public class ZbggAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(ZbggAction.class);
	
	// Servrice start
	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private ZbggService<ZbggBean> zbggService; 
	
	@Autowired
	private JhbhService<JhbhBean> jhbhService;

	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 */
							
	@RequestMapping("/zbgg")
	public ModelAndView  zbgg(ZbggModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         //rightNow.add(Calendar.DAY_OF_YEAR,-2);//����-2��
		 rightNow.add(Calendar.MONTH,-1);//����-1����,�����¼ƻ�
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);
 		 Map<String,Object>  context = getRootMap();
	     model.setDeleted(DELETED.NO.key);
		 List<ZbggBean> dataList = zbggService.queryByList(model);
		 //����ҳ������
		 context.put("dataList", dataList);

	     return forword("cgpt/zbggManage",context); 
	
		
	}

	@RequestMapping("/zbggBrow")
	public ModelAndView  zbggbrow(ZbggModel model,HttpServletRequest request) throws Exception{
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
		return forword("cgpt/zbggBrow",context); 
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
	public void  dataListRkhbForGgbh(String ggbh,HttpServletResponse response) throws Exception{
//System.out.println("����ɽ�� ggbh"+ ggbh);
		JSONObject context = new JSONObject();	
		List<ZbggBean> dataList = zbggService.queryListByGgbh(ggbh);
		List<ZbggBean> result = new ArrayList<ZbggBean>();

		for (Object ele : dataList)
		{
			ZbggBean st = (ZbggBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��XsddAction--dataListRkhbForDdid"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
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
		 String yxrq  = request.getParameter("yxrq");
		 String fbrq  = request.getParameter("fbrq");
		 String wlfl  = request.getParameter("wlfl");
		 String jhrq_xq  = request.getParameter("jhrq_xq");
//System.out.println("����ɽ��ZbggAction--add:xdrq="+xdrq);
//System.out.println("����ɽ��ZbggAction--add:wlfl="+wlfl);
//System.out.println("����ɽ��ZbggAction--add:jhbh="+jhbh);
//System.out.println("����ɽ��ZbggAction--add:jhrq_kh="+jhrq_kh);
//System.out.println("����ɽ��ZbggAction--add:insert"+inserted);
//System.out.println("����ɽ��ZbggAction--add:delete"+deleted);
//System.out.println("����ɽ��ZbggAction--add:update"+updated);
		 String username = SessionUtils.getUser(request).getNickName();
		 if (ggbh==null || "".equals(ggbh))
		 {
             String syear=DateUtil.getNowYear();
			 String smonth=DateUtil.getNowMonth();
			 Integer iyear=Integer.valueOf(syear);
             Integer imonth=Integer.valueOf(smonth);
			 JhbhBean bean  = jhbhService.queryNoByYearAndMonth(iyear, imonth,5);  //5--�ɹ�����
			 //@Param("uuid")String uuid, @Param("portletid")String portletid
  		     if(bean  == null){
				  JhbhBean newbean=new JhbhBean();
                  newbean.setNian(iyear);
				  newbean.setYue(imonth);
				  newbean.setFlag(5);  //5--�ɹ�����
				  newbean.setJhno(1);
				  jhbhService.add(newbean);
				  String sjhno=StringUtil.fillZero("1", 4);
				  ggbh=syear+smonth+sjhno;
		    }
			else 
			{
				 Integer newno=bean.getJhno()+1;
				 bean.setJhno(newno);
				 jhbhService.update(bean);
                 String sjhno=StringUtil.fillZero(newno+"", 4);
				 ggbh=syear+smonth+sjhno;
			}
	     }
         Integer row=0;
		 Integer sortFlag=0;//�Ƿ������滻row
         if(deleted != null){  
             //��json�ַ���ת���ɶ���  
             sortFlag=1;
             List<ZbggBean> listDeleted = new ArrayList<ZbggBean>();	
             listDeleted = JSON.parseArray(deleted, ZbggBean.class);  
             //TODO ����Ϳ��Ը���ת����Ķ��������Ӧ�Ĳ����� 
			 Integer [] ids=new Integer[listDeleted.size()];

             row=0;
		     for (Object ele : listDeleted )
		     {
				  ZbggBean bean = (ZbggBean)ele;	  

                  Integer ggid=bean.getId();
                  ids[row]=ggid;
				  row++;
			 } 
			 zbggService.deleteBean(ids);
         }  
 
         if(inserted != null){  
			 sortFlag=1;
             //��json�ַ���ת���ɶ���  
             List<ZbggBean> listInserted = new ArrayList<ZbggBean>();	
	
			 listInserted = JSON.parseArray(inserted, ZbggBean.class);  
			 for (Object ele : listInserted )
		     {
			     ZbggBean bean = (ZbggBean)ele;	
				 //bean.setId(null);
		         bean.setGgbh(ggbh);
				 bean.setWlfl(wlfl);
				 bean.setRow(row);
				 bean.setDeleted(DELETED.NO.key);
				 bean.setState(1);
			     bean.setKb(0);
			     bean.setCreateBy(username);
				 zbggService.addBean(bean);
				 //row++;

		     }
         }  

         if(updated != null){  
            //��json�ַ���ת���ɶ���  
             SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         String sdate;
		     Date ddate=null;
             sdate=sdf.format(new Date());	
		     try {
    	         ddate = sdf.parse(sdate);
             } catch (ParseException e) {
                 e.printStackTrace();
             }
			sortFlag=1;
		    List<ZbggBean> listUpdated = new ArrayList<ZbggBean>();	
            listUpdated = JSON.parseArray(updated, ZbggBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 ZbggBean bean = (ZbggBean)ele;	
                 bean.setUpdateBy(username);	
				 bean.setUpdateTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
 				 zbggService.updateBean(bean);
		     }
         }
  		 if (sortFlag==1)
		 {
  		     row=1;
		     List<ZbggBean> dataList = new ArrayList<ZbggBean>();	

		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		     String sdate;
			 Date dfbrq=null;

		 	 if (fbrq==null || fbrq.trim().length()<1 )
			 {
		         sdate=sdf.format(new Date());	

			 }
			 else 
			 { 
			 	 sdate=fbrq;
			 }
			 try {
 			        
       		     dfbrq = sdf.parse(sdate);
       
              } catch (ParseException e) {
                 e.printStackTrace();
             }
			 Date dyxrq=null;

		 	 if (yxrq==null || yxrq.trim().length()<1 )
			 {
		         sdate=sdf1.format(new Date());	

			 }
			 else 
			 { 
			 	 sdate=yxrq;
			 }
			 try {
 			        
       		     dyxrq = sdf1.parse(sdate);
       
              } catch (ParseException e) {
                 e.printStackTrace();
              }


			 Date djhrq_xq=null;
	         int flag=0; //�滻���,��ϸ�е����󽻻�����Ϊ�գ��ñ�ͷ�����󽻻������滻
             if  ( jhrq_xq!=null && jhrq_xq.trim().length()>=1 )
             {
                 
   		         try {
     		          djhrq_xq = sdf.parse(jhrq_xq);
                 } catch (ParseException e) {
                      e.printStackTrace();
                 }
				 flag=1;
			 }

		     dataList = zbggService.queryListByGgbh(ggbh);	
	         for (Object ele : dataList )
		     {

			     ZbggBean bean = (ZbggBean)ele;	    
                 bean.setFbrq(new java.sql.Date(dfbrq.getTime()));  //java.util.Date -->java.sql.Date 
				 bean.setYxrq(new java.sql.Timestamp(dyxrq.getTime()));  //java.util.Date -->java.sql.Date
				 bean.setWlfl(wlfl);

				 if ((bean.getJhrq_xq()==null) && (flag==1) )
				 { 
					 bean.setJhrq_xq(new java.sql.Date(djhrq_xq.getTime()));   
				 }

	             bean.setRow(row);
			     //bean.setGgbh(ggbh);
				 zbggService.updateBean(bean);
				 row++;
		    } 
		}
	    sendSuccessMessage(response, "����ɹ�~");
		return ;
	}



	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		ZbggBean bean  = zbggService.queryById(id);
		String ggbh= bean.getGgbh()	;
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
	    if(bean.getKb()  == 1 ){
			sendFailureMessage(response, "���б깫���ѿ���,�����޸�!");
			return;
		}
	    if(bean.getKb()  == 2 ){
			sendFailureMessage(response, "���б깫�����й�Ӧ��Ͷ��,�����޸�!");
			return;
		}
	    String username = SessionUtils.getUser(request).getNickName();
		String lrr=bean.getCreateBy();
		if (username.equals(lrr))
		{
	
//System.out.println("����ɽ-ZbggAction--dataListById-jhbh"+jhbh);	
		    List<ZbggBean> dataList = zbggService.queryListByGgbh(ggbh);

		    List<ZbggBean> result = new ArrayList<ZbggBean>();	

		    for (Object ele : dataList)
		    {
  			    ZbggBean elebean = (ZbggBean)ele;
			    result.add(elebean);
		    }
		    JSONArray jsonArr= new JSONArray(result);
		    String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-ZbggAction--dataListById-jsonStr"+jsonArr.toString());
		//context.put("total",result.size());
		//context.put("rows", jsonArr.toString());
		    context.put("json",jsonStr);
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		    String sdate=df.format(new Date());

		    Map<String,Object>  data = new HashMap<String,Object> ();
		    if (result==null|| result.size()<1 ){	
	            data.put("ggbh", "");
		        data.put("fbrq",sdate);
			    data.put("yxrq","");
			    data.put("wlfl","");
			    data.put("jhrq_xq","");
		    }
		    else{
	            data.put("ggbh", result.get(0).getGgbh());
				data.put("wlfl", result.get(0).getWlfl());
		        data.put("fbrq", result.get(0).getFbrq()!=null?(df.format(result.get(0).getFbrq())):"");
			  	data.put("yxrq", result.get(0).getYxrq()!=null?(df1.format(result.get(0).getYxrq())):"");
			    data.put("jhrq_xq",result.get(0).getJhrq_xq()!=null?(df.format(result.get(0).getJhrq_xq())):"");
		    }
            context.put("data", data);
		    context.put(SUCCESS, true);	
		
//System.out.println("����ɽ-ZbggAction--dataListById-context"+context.toString());
		    HtmlUtil.writerJson(response, context);

		}
		else 
		{
		    sendFailureMessage(response, "���б깫��Ϊ("+bean.getCreateBy()+")�����������޸�!");
		    return;
		}
	}


	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletRequest request,HttpServletResponse response) throws Exception{

		if(id != null && id.length > 0){
			for (int i=0;i<id.length;i++ )
			{
				String message;

				  ZbggBean bean  = zbggService.queryById(id[i]);
	              String username = SessionUtils.getUser(request).getNickName();
		          String lrr=bean.getCreateBy();	
		          if (!username.equals(lrr))
		          {
		              sendFailureMessage(response, "���б깫��Ϊ("+lrr+")����������ɾ��!");
		              continue ;
				  }
				  if(bean.getKb()  == 1  ){
					  message="IDΪ("+id[i]+")���б깫���ѿ���,����ɾ����";
                      sendFailureMessage(response, message);
                      continue ;
					  
		          } 
				  else{
			           zbggService.delete(id[i]); 
					   message="IDΪ("+id[i]+")���б깫��,ɾ���ɹ���";
   		               sendSuccessMessage(response,  message);	
	
				  }

			}

		}else{
			sendFailureMessage(response, "δѡ�м�¼");
			return; 
		}
	}


}
