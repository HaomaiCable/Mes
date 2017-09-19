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
import com.hmmes.bean.XsddBean;
import com.hmmes.bean.XsddsBean;
import com.hmmes.bean.JhbhBean;
import com.hmmes.bean.XsddWghbBean;
import com.hmmes.bean.XsddRkhbBean;
import com.hmmes.bean.XsddBgBean;
import com.hmmes.bean.JtjhBean;
import com.hmmes.bean.JtjhWghbBean;
import com.hmmes.bean.TreeNode;
import com.hmmes.bean.BaseBean.DELETED;

import com.hmmes.model.XsddModel;
import com.hmmes.model.XsddBgModel;

import com.hmmes.service.JhbhService;
import com.hmmes.service.XsddService;
import com.hmmes.service.XsddsService;
import com.hmmes.service.XsddWghbService;
import com.hmmes.service.XsddRkhbService;
import com.hmmes.service.XsddBgService;
import com.hmmes.service.JtjhService;
import com.hmmes.service.JtjhWghbService;
import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.XsddTree;
import com.hmmes.utils.SessionUtils;
import com.hmmes.utils.DateUtil;
import com.hmmes.utils.StringUtil;
import com.hmmes.utils.ExportExcel;
import com.hmmes.utils.excelutils.ExcelHelper;
import com.hmmes.utils.excelutils.JxlExcelHelper;
import com.hmmes.utils.ListUtils;

import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;  
import java.util.*;
import java.io.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

 
@Controller
@RequestMapping("/xsddManage") 
public class XsddAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(XsddAction.class);
	
	// Servrice start
	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private XsddService<XsddBean> xsddService; 
	
	@Autowired
	private JhbhService<JhbhBean> jhbhService;

	@Autowired
	private  XsddsService<XsddsBean> xsddsService;

	@Autowired
	private  XsddWghbService<XsddWghbBean> xsddWghbService;	

	@Autowired
	private  XsddRkhbService<XsddRkhbBean> xsddRkhbService;	

	@Autowired
	private  XsddBgService<XsddBgBean> xsddBgService;	
	@Autowired
	private JtjhService<JtjhBean> jtjhService;	
	@Autowired
	private JtjhWghbService<JtjhWghbBean> jtjhWghbService;		
	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 */
							
	@RequestMapping("/xsdd")
	public ModelAndView  xsdd(XsddModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.DAY_OF_YEAR,-2);//����-2��
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

	
	   /**

		String strtmp="$("+'"'+"#searchForm input:input[name='jhbh']"+'"'+").val('"+"2017070154"+"');";
System.out.println("����ɽ��Xsdd--out "+strtmp);
        out.write("<script type='text/javascript'>");
        //out.write("alert('�ļ�����ɹ���');");	
	    out.write("$("+'"'+"#searchForm input:input[name='jhbh']"+'"'+").val('"+"2017"+"');");
	    //out.write("<script type='text/javascript'>"
		//					+ "parent.callback('�ļ�����ɹ���');"
		//					+ "</script>");

		//$("#searchForm input:input[name='jhbh']").val('2017');
		out.write("var param =$('#searchForm').serializeObject();");
		out.write("$('#data-list').datagrid('reload',param);");
        out.write("</script>");
		*/
      	//response.setContentType("text/html;charset=gbk");
		//// ��ȡ�����
		//PrintWriter out = response.getWriter();
		//out.write("<script type='text/javascript'>"
		//				+ "parent.initdate(fromdate,todate);"
		//				+ "</script>");


		Map<String,Object>  context = getRootMap();
	    model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);

	    return forword("business/xsddManage",context); 
	
		
	}
	//���۶������
	@RequestMapping("/xsddchange")
	public ModelAndView  xsddchange(XsddModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.DAY_OF_YEAR,-2);//����-2��
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    //model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);

	    return forword("business/xsddChange",context); 
	
		
	}
	//���۶���������
	@RequestMapping("/xsddChangeCheck")
	public ModelAndView  xsddChangeCheck(XsddBgModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.DAY_OF_YEAR,-2);//����-2��
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    //model.setDeleted(DELETED.NO.key);
		List<XsddBgBean> dataList = xsddBgService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);

	    return forword("business/xsddChangeCheck",context); 
	
	}

	//���۶������ȷ��
	@RequestMapping("/xsddChangeAccept")
	public ModelAndView  xsddChangeAccept(XsddBgModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.DAY_OF_YEAR,-2);//����-2��
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    //model.setDeleted(DELETED.NO.key);
		List<XsddBgBean> dataList = xsddBgService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);

	    return forword("business/xsddChangeAccept",context); 
	
	}
	@RequestMapping("/xsddChangeBrow")  //���۶�����������
	public ModelAndView xsddChangeBrow(XsddBgModel model,HttpServletRequest request) throws Exception{
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
		//model.setDeleted(DELETED.NO.key);
		List<XsddBgBean> dataList = xsddBgService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/xsddChangeBrow",context); 
	}	
	
	/**
	 * �����ڴ�
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/jhqdf")
	public ModelAndView  jhqdf(XsddModel model,HttpServletRequest request) throws Exception{
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.DAY_OF_YEAR,-2);//����-2��
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

 		Map<String,Object>  context = getRootMap();
		model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jhqConfirm",context); 
	}	

    /**
	 * ����ƻ��㱨
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/ecjhq")
	public ModelAndView  ecjhq(XsddModel model,HttpServletRequest request) throws Exception{

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
		List<XsddBean> dataList = xsddService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/ecjhqInput",context); 
	}	
  /**
	 * ���۶��������
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/xsddbrow")
	public ModelAndView  xsddbrow(XsddModel model,HttpServletRequest request) throws Exception{
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
		List<XsddBean> dataList = xsddService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/xsddBrow",context); 
	}	
    //��̨�ƻ�����
	@RequestMapping("/jtjhwatch")
	public ModelAndView  jtjhwatch(XsddModel model,HttpServletRequest request) throws Exception{
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
		List<XsddBean> dataList = xsddService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhWatch",context); 
	}		
		
	/**
	 * ���۶����깤�㱨
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/xsddwghb")
	public ModelAndView xsddwghb(XsddModel model,HttpServletRequest request) throws Exception{
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
		List<XsddBean> dataList = xsddService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/xsddWgReport",context); 
	}

	//���߱��������깤�㱨
	@RequestMapping("/xsddwghb_pl")
	public ModelAndView xsddwghb_pl(XsddModel model,HttpServletRequest request) throws Exception{
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
		List<XsddBean> dataList = xsddService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/xsddWgReport_pl",context); 
	}
	/**
	 * ���۶������㱨
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/xsddrkhb")
	public ModelAndView xsddrkhb(XsddModel model,HttpServletRequest request) throws Exception{

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
		List<XsddBean> dataList = xsddService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/xsddRkReport",context); 
	}		
	
     /**
	 * json �б�ҳ��
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList")   //DataGrid �������۶����´�����ڴ𸴡����ν����ڴ𸴣�����Ҫ�����깤�㱨(Wghb)�����㱨(Rkhb)
	public void  dataList(XsddModel model,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<XsddBean> dataList = xsddService.queryByList(model);
		List<XsddBean> result = new ArrayList<XsddBean>();

		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��XsddAction--dataList"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}
	
	
	/**
	 * json �б�ҳ��
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataListLinkNoPage")   //����ҳ�ģ��������۶������
	public void  dataListLinkNoPage(XsddModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<XsddBean> dataList = xsddService.queryByList(model);
		//List<XsddBean> dataList = xsddService.queryByListNoPage(model);
        //List<XsddBean> dataList = xsddService.queryByAll();
		List<XsddBean> result = new ArrayList<XsddBean>();	
		int totaljh=0;
		int noontimejh=0;
		DecimalFormat  df2   = new DecimalFormat("######0.00");
		// ��װVO����
		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;

			Double ddsl=st.getSl()==null?0:st.getSl();
			DecimalFormat  df0   = new DecimalFormat("######0");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	        int ddid=st.getId();
			List<XsddWghbBean> wghbList = xsddWghbService.queryListById(ddid);
            Integer qbrk=2;//0
		    String sdate=sdf.format(new Date());
		    Date ud= sdf.parse(sdate);
		    java.sql.Date sd = new java.sql.Date(ud.getTime());   
		    long cqts=-9999;

			if (wghbList!=null && wghbList.size()>0 )
			{

                 if ((wghbList.get(0).getWgrq())!=null)
                 {
		              st.setMaxWgrq(wghbList.get(0).getWgrq());
	     	     }
				 Double sumwgsl=0.0;
				 Double sumcmsl=0.0;
				 Double sumrksl=0.0;
				 

				 StringBuffer sumwgslds = new StringBuffer();
				 StringBuffer sumwgslss = new StringBuffer();
				 StringBuffer sumxpgg = new StringBuffer();
				 StringBuffer sumrkslds = new StringBuffer();
		      	 for(int i=0;i<wghbList.size();i++){
			         XsddWghbBean wghb = wghbList.get(i);
			         sumwgslds.append(wghb.getWgsl()!=null?df0.format(wghb.getWgsl())+"":"");
			         sumxpgg.append(wghb.getXpgg()+"");
					 sumwgslss.append(wghb.getWgslss()+"");
					 sumwgsl=sumwgsl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
                     sumcmsl=sumcmsl+(wghb.getCmsl()==null?0.0:wghb.getCmsl());
					 if (wghb.getRk()!=null && wghb.getRk()==1)
					 {
					     sumrkslds.append(wghb.getWgsl()!=null?df0.format(wghb.getWgsl())+"":"");
					     sumrksl=sumrksl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
					 }
			         if((i+1) < wghbList.size()){
				         sumwgslds.append("��");
						 sumwgslss.append("��");
						 sumxpgg.append("��");
						 if (wghb.getRk()!=null && wghb.getRk()==1 ){
						     sumrkslds.append("��");
						 }
			         }
		         }
				 if (sumrksl>=ddsl )
				 {
					 qbrk=1;
				 }
				 else{
				     if (ddsl!=0)
				     {
					     if ((ddsl-sumrksl)/ddsl<=0.1)  //С��10%��Ϊ���
					     {
						     qbrk=1;
					     }
				     }
				 }					 
		
				 if (st.getJhrq()!=null && !"".equals(st.getJhrq()))
				 {
		
				     if (qbrk==1)
				     {
                         if (st.getMaxWgrq()==null || "".equals(st.getMaxWgrq()))
                         {
                             cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
                         }
						 else{
						     cqts=(st.getMaxWgrq().getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
                         }				     }
				     else{
					     if ((sd.getTime()-st.getJhrq().getTime())<=0)
						 {
							  cqts=-9999;
						 }
						 else{
						     cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
						 }
					 }

				 }
				 else{
					 cqts=-9999;
				 }

				 st.setSumWgsl(sumwgsl);
				 st.setSumCmsl(sumcmsl);
				 st.setSumWgslds("��"+sumwgslds.toString()+"��");
				 st.setSumWgslss("��"+sumwgslss.toString()+"��");
				 st.setSumXpgg("��"+sumxpgg.toString()+"��");
				 st.setSumRksl(sumrksl);
				 st.setQbRk(qbrk);
				 st.setSumRkslds("��"+sumrkslds.toString()+"��");
				 if (st.getState()==1)
				 { 
					st.setCqts(cqts);
				 }
				 //xsddService.updateBean(st);//
			}
			else{
				 if (st.getJhrq()!=null && !"".equals(st.getJhrq())){
				     if ((sd.getTime()-st.getJhrq().getTime())<=0)
				     {
					     cqts=-9999;
				     }
				     else{
				         cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
				     }
				 }
				 else{
					 cqts=-9999;
				 }
			     if (st.getState()==1)
				 { 
				     st.setCqts(cqts);
				 }
				 //st.setQbRk(qbrk);//
				 //xsddService.updateBean(st);//
			}
			/**
			List<JtjhBean> jtjhList = jtjhService.queryListByDdId(ddid);
			if (jtjhList!=null && jtjhList.size()>0 )
			{

				 if ((rkhbList.get(0).getRkrq())!=null)
                 {
              
				     st.setMaxRkrq(rkhbList.get(0).getRkrq());
				 }
				 Double sumrksl=0.0;
				 Integer qbrk=0;
				 StringBuffer sumrkslds = new StringBuffer();
		      	 for(int i=0;i<rkhbList.size();i++){
			         XsddRkhbBean rkhb = rkhbList.get(i);
			         sumrkslds.append(df0.format(rkhb.getRksl())+"");
					 sumrksl=sumrksl+(rkhb.getRksl()==null?0.0:rkhb.getRksl());
					 if (rkhb.getRk()!=null)
					 {
					
					     if (rkhb.getRk()==1)
					     {
						     qbrk=1;
					     } 
					 }

			         if((i+1) < rkhbList.size()){
				         sumrkslds.append("��");

			         }
		         }
				 st.setSumRksl(sumrksl);
				 st.setQbRk(qbrk);
				 st.setSumRkslds("��"+sumrkslds.toString()+"��");

			}
			*/
			if (st.getState()==1)
			{
				 totaljh++;
			}
		   
			if (cqts > 0 && st.getState()==1 )
			{
  			    noontimejh++;
			}
			result.add(st);
		}
		if (totaljh!=0)
		{
		
		    XsddBean st = new XsddBean();
            st.setJhbh("��⼰ʱ��=");
		    st.setXh((totaljh-noontimejh)+"/"+totaljh+"="+df2.format(100*(totaljh-noontimejh)/totaljh)+"%");
            result.add(st);
        }
		//����ҳ������
		//JSONObject context = new JSONObject();

		//JSONObject jsonMap = new JSONObject();
		//jsonMap.put("total",model.getPager().getRowCount());
		//jsonMap.put("rows", result);
//System.out.println("����ɽ-xsdd-Json"+jsonMap.toString());
		//HtmlUtil.writerJson(response, jsonMap);
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
        //request.setAttribute("data",jsonArr.toString());
		//context.put("data", jsonArr.toString());
//System.out.println("����ɽ��XsddAction--dataList"+jsonStr);	
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonStr); 
		//HtmlUtil.writerJson(response, context); 

	}
	
	@RequestMapping("/dataListLink")   //DataGrid url �������۶������������깤�㱨��ͳ�����㱨�������깤�㱨(Wghb)�����㱨(Rkhb)���ݿ⣬�����Ϣ
	public void  dataListLink(XsddModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<XsddBean> dataList = xsddService.queryByList(model);
		List<XsddBean> result = new ArrayList<XsddBean>();	
				// ��װVO����
		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;
			Double ddsl=st.getSl()==null?0:st.getSl();
			DecimalFormat  df0   = new DecimalFormat("######0");
			int ddid=st.getId();
			List<XsddWghbBean> wghbList = xsddWghbService.queryListById(ddid);

			if (wghbList!=null && wghbList.size()>0 )
			{

                 //if ((wghbList.get(0).getWgrq())!=null)
                 //{
		         //     st.setMaxWgrq(wghbList.get(0).getWgrq());
                 //     st.setWgzs(wghbList.get(0).getCzg());
	     	     //}
				 Double sumwgsl=0.0;
				 Double sumcmsl=0.0;
				 Double sumrksl=0.0;
				 Integer qbrk=0;

				 StringBuffer sumwgslds = new StringBuffer();
				 StringBuffer sumwgslss = new StringBuffer();
				 StringBuffer sumxpgg = new StringBuffer();
				 StringBuffer sumrkslds = new StringBuffer();
		      	 for(int i=0;i<wghbList.size();i++){
			         XsddWghbBean wghb = wghbList.get(i);
			         sumwgslds.append(wghb.getWgsl()!=null?df0.format(wghb.getWgsl())+"":"");
			         sumxpgg.append(wghb.getXpgg()+"");
					 sumwgslss.append(wghb.getWgslss()+"");
					 sumwgsl=sumwgsl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
                     sumcmsl=sumcmsl+(wghb.getCmsl()==null?0.0:wghb.getCmsl());
					 if (wghb.getRk()!=null && wghb.getRk()==1)
					 {
					     sumrkslds.append(wghb.getWgsl()!=null?df0.format(wghb.getWgsl())+"":"");
					     sumrksl=sumrksl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
					 }
			         if((i+1) < wghbList.size()){
				         sumwgslds.append("��");
						 sumwgslss.append("��");
						 sumxpgg.append("��");
						 if (wghb.getRk()!=null && wghb.getRk()==1 ){
						     sumrkslds.append("��");
						 }
			         }
		         }
				 if (sumrksl>=ddsl )
				 {
					 qbrk=1;
				 }
				 st.setSumWgsl(sumwgsl);
				 st.setSumCmsl(sumcmsl);
				 st.setSumWgslds("��"+sumwgslds.toString()+"��");
				 st.setSumWgslss("��"+sumwgslss.toString()+"��");
				 st.setSumXpgg("��"+sumxpgg.toString()+"��");
				 st.setSumRksl(sumrksl);
				 st.setQbRk(qbrk);
				 st.setSumRkslds("��"+sumrkslds.toString()+"��");
			}
			/**
			List<XsddRkhbBean> rkhbList = xsddRkhbService.queryListById(ddid);
			if (rkhbList!=null && rkhbList.size()>0 )
			{

				 if ((rkhbList.get(0).getRkrq())!=null)
                 {
              
				     st.setMaxRkrq(rkhbList.get(0).getRkrq());
				 }
				 Double sumrksl=0.0;
				 Integer qbrk=0;
				 StringBuffer sumrkslds = new StringBuffer();
		      	 for(int i=0;i<rkhbList.size();i++){
			         XsddRkhbBean rkhb = rkhbList.get(i);
			         sumrkslds.append(df0.format(rkhb.getRksl())+"");
					 sumrksl=sumrksl+(rkhb.getRksl()==null?0.0:rkhb.getRksl());
					 if (rkhb.getRk()!=null)
					 {
					
					     if (rkhb.getRk()==1)
					     {
						     qbrk=1;
					     } 
					 }

			         if((i+1) < rkhbList.size()){
				         sumrkslds.append("��");

			         }
		         }
				 st.setSumRksl(sumrksl);
				 st.setQbRk(qbrk);
				 st.setSumRkslds("��"+sumrkslds.toString()+"��");

			}
			*/
		    
			result.add(st);
		}
		//����ҳ������
		//JSONObject jsonMap = new JSONObject();
		//jsonMap.put("total",model.getPager().getRowCount());
		//jsonMap.put("rows", result);
//System.out.println("����ɽ-xsdd-Json"+jsonMap.toString());
		//HtmlUtil.writerJson(response, jsonMap);
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��XsddAction--dataList"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	}

	@RequestMapping("/dataListChange")   //���۱����DataGrid 
	public void  dataListChange(XsddBgModel model,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<XsddBgBean> dataList = xsddBgService.queryByList(model);
		List<XsddBgBean> result = new ArrayList<XsddBgBean>();

		for (Object ele : dataList)
		{
			XsddBgBean st = (XsddBgBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��XsddAction--dataListchange"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}

	@RequestMapping("/dataListChangeForDdid")   //�������۶���ID����ѯ���۱������ʾDataGrid 
	public void  dataListChangeForDdid(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<XsddBgBean> dataList = xsddBgService.queryListById(id);
		List<XsddBgBean> result = new ArrayList<XsddBgBean>();

		for (Object ele : dataList)
		{
			XsddBgBean st = (XsddBgBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��XsddAction--dataListchangeForDdid"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}
    //�������۶���ID����ѯ�깤�㱨����ʾDataGrid 
	@RequestMapping("/dataListWghbForDdid")   //�������۶���ID����ѯ�깤�㱨����ʾDataGrid 
	public void  dataListWghbForDdid(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<XsddWghbBean> dataList = xsddWghbService.queryListById(id);
		List<XsddWghbBean> result = new ArrayList<XsddWghbBean>();

		for (Object ele : dataList)
		{
			XsddWghbBean st = (XsddWghbBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��XsddAction--dataListWghbForDdid"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}

    //�������۶���ID����ѯ���㱨����ʾDataGrid 
	@RequestMapping("/dataListRkhbForDdid")   //�������۶���ID����ѯ���㱨����ʾDataGrid 
	public void  dataListRkhbForDdid(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<XsddWghbBean> dataList = xsddWghbService.queryListById(id);
		List<XsddWghbBean> result = new ArrayList<XsddWghbBean>();

		for (Object ele : dataList)
		{
			XsddWghbBean st = (XsddWghbBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��XsddAction--dataListRkhbForDdid"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}

   //�������۶���ID����ѯ��̨�ƻ���ʾDataGrid 
	@RequestMapping("/dataListJtjhForDdId")   //�������۶���ID����ѯ���㱨����ʾDataGrid 
	public void  dataListJtjhForDdId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<JtjhBean> dataList = jtjhService.queryListByDdId(id);
		List<JtjhBean> result = new ArrayList<JtjhBean>();

	    for (Object ele : dataList)
		{
	            JtjhBean st = (JtjhBean)ele;
				Double jhsl=st.getJhsl();
			    DecimalFormat  df0   = new DecimalFormat("######0");
			    Integer  jhid=st.getId();
			    List<JtjhWghbBean> wghbList = jtjhWghbService.queryListByJhId(jhid);

			    if (wghbList!=null && wghbList.size()>0 )
			    {

                     if ((wghbList.get(0).getWgrq())!=null)
                     {
		                  st.setMaxWgrq(wghbList.get(0).getWgrq());
	     	         }
				     Double sumwgsl=0.0;
				     Integer qbwg=0;

				     StringBuffer sumwgslds = new StringBuffer();

		      	     for(int i=0;i<wghbList.size();i++){
			             JtjhWghbBean wghb = wghbList.get(i);
			             sumwgslds.append(wghb.getWgsl()!=null?df0.format(wghb.getWgsl())+"":"");
					     sumwgsl=sumwgsl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
					     if (wghb.getWg()!=null)
					     {
					
					         if (wghb.getWg()==1)
					         {
						         qbwg=1;
					         } 
					     }
			
	    	            if((i+1) < wghbList.size()){
				             sumwgslds.append("��");
				        }
		            }
				    if (sumwgsl>=jhsl )
				    {
					    qbwg=1;
				    }

				    st.setSumWgsl(sumwgsl);
				    st.setSumWgslds("��"+sumwgslds.toString()+"��");
				    st.setQbWg(qbwg);

			    }
    
			    result.add(st);
		}

		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��XsddAction--dataList"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);	
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
		 String jhbh  = request.getParameter("jhbh");
		 String ywy  = request.getParameter("ywy");
		 String xdrq  = request.getParameter("xdrq");
		 String jhrq_kh  = request.getParameter("jhrq_kh");
//System.out.println("����ɽ��XsddAction--add:xdrq="+xdrq);
//System.out.println("����ɽ��XsddAction--add:ywy="+ywy);
//System.out.println("����ɽ��XsddAction--add:jhbh="+jhbh);
//System.out.println("����ɽ��XsddAction--add:jhrq_kh="+jhrq_kh);
//System.out.println("����ɽ��XsddAction--add:insert"+inserted);
//System.out.println("����ɽ��XsddAction--add:delete"+deleted);
//System.out.println("����ɽ��XsddAction--add:update"+updated);

		 String username = SessionUtils.getUser(request).getNickName();
		 if (jhbh==null || "".equals(jhbh))
		 {
             String syear=DateUtil.getNowYear();
			 String smonth=DateUtil.getNowMonth();
			 Integer iyear=Integer.valueOf(syear);
             Integer imonth=Integer.valueOf(smonth);
			 JhbhBean bean  = jhbhService.queryNoByYearAndMonth(iyear, imonth,1);  //1--���۶������
			 //@Param("uuid")String uuid, @Param("portletid")String portletid
  		     if(bean  == null){
				  JhbhBean newbean=new JhbhBean();
                  newbean.setNian(iyear);
				  newbean.setYue(imonth);
				  newbean.setFlag(1);
				  newbean.setJhno(1);
				  jhbhService.add(newbean);
				  String sjhno=StringUtil.fillZero("1", 4);
				  jhbh=syear+smonth+sjhno;
		    }
			else 
			{
				 Integer newno=bean.getJhno()+1;
				 bean.setJhno(newno);
				 jhbhService.update(bean);
                 String sjhno=StringUtil.fillZero(newno+"", 4);
				 jhbh=syear+smonth+sjhno;
			}
	     }
         Integer row=0;
		 Integer sortFlag=0;//�Ƿ������滻row
         if(deleted != null){  
             //��json�ַ���ת���ɶ���  
             sortFlag=1;
             List<XsddBean> listDeleted = new ArrayList<XsddBean>();	
             listDeleted = JSON.parseArray(deleted, XsddBean.class);  
             //TODO ����Ϳ��Ը���ת����Ķ��������Ӧ�Ĳ����� 
			 Integer [] ids=new Integer[listDeleted.size()];

             row=0;
		     for (Object ele : listDeleted )
		     {
				 XsddBean bean = (XsddBean)ele;	  

                  Integer ddid=bean.getId();
                  ids[row]=ddid;
				  row++;
			 } 
			 xsddService.deleteBean(ids);
         }  
 
         if(inserted != null){  
			 sortFlag=1;
             //��json�ַ���ת���ɶ���  
             List<XsddBean> listInserted = new ArrayList<XsddBean>();	
	
			 listInserted = JSON.parseArray(inserted, XsddBean.class);  
			 for (Object ele : listInserted )
		     {
			     XsddBean bean = (XsddBean)ele;	
				 //bean.setId(null);
		         bean.setJhbh(jhbh);
				 bean.setRow(row);
                 //bean.setYwy(ywy);
				 bean.setDeleted(DELETED.NO.key);
				 bean.setState(1);
			     bean.setXdjt(0);
			     bean.setCreateBy(username);
				 xsddService.addBean(bean);
				 //row++;

		     }
         }  

         if(updated != null){  
            //��json�ַ���ת���ɶ���  
			sortFlag=1;
		    List<XsddBean> listUpdated = new ArrayList<XsddBean>();	
            listUpdated = JSON.parseArray(updated, XsddBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddBean bean = (XsddBean)ele;	
                 bean.setUpdateBy(username);				 
 				 xsddService.updateBean(bean);
		     }
         }
  		 if (sortFlag==1)
		 {
  		     row=1;
		     List<XsddBean> dataList = new ArrayList<XsddBean>();	

		     dataList = xsddService.queryListByJhbh(jhbh);
		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		     String sdate;
			 Date dxdrq=null;

		 	 if (xdrq==null || xdrq.trim().length()<1 )
			 {
		         sdate=sdf.format(new Date());	

			 }
			 else 
			 { 
			 	 sdate=xdrq;
			 }
			 try {
 			        
       		     dxdrq = sdf.parse(sdate);
       
              } catch (ParseException e) {
                 e.printStackTrace();
              }

			 Date djhrq_kh=null;
	         int flag=0; //�滻���,��ϸ�еĿͻ�Ҫ������Ϊ�գ��ñ�ͷ�Ŀͻ�Ҫ�������滻
             if  ( jhrq_kh!=null && jhrq_kh.trim().length()>=1 )
             {
                 
   		         try {
     		          djhrq_kh = sdf.parse(jhrq_kh);
                 } catch (ParseException e) {
                      e.printStackTrace();
                 }
				 flag=1;
			 }
	
	         for (Object ele : dataList )
		     {
			     XsddBean bean = (XsddBean)ele;	    
                 bean.setXdrq(new java.sql.Date(dxdrq.getTime()));  //java.util.Date -->java.sql.Date 

		
				 if ((bean.getJhrq_kh()==null) && (flag==1) )
				 { 
					 bean.setJhrq_kh(new java.sql.Date(djhrq_kh.getTime()));   
				 }
	             bean.setRow(row);
			     bean.setJhbh(jhbh);
				 if (bean.getYwy()==null || "".equals(bean.getYwy().trim()))
				 {
					 bean.setYwy(ywy);
				 }
			     
				 if (bean.getPh()==null || "".equals(bean.getPh())){
					 String phprefix="JH";
					 if ("���".equals(bean.getYwy())) {
					
						 phprefix="KC";
					 }

					 if ("KC".equals(phprefix) && ((bean.getJsyq()).indexOf("��/��")!=-1 || 
						 (bean.getJsyq()).indexOf("M/��")!=-1 || (bean.getJsyq()).indexOf("m/��")!=-1) )
					 { 
                         bean.setPh("KC");						
					 }
					 else
					 {
                         bean.setPh(phprefix+bean.getJhbh()+("JH".equals(phprefix)?bean.getYwy():"")+"-"+StringUtil.fillZero(bean.getRow()+"", 3));
					 }
				 }

				 xsddService.updateBean(bean);
				 row++;
		    } 
		}
	    sendSuccessMessage(response, "����ɹ�~");
		return ;
	}

	@RequestMapping("/saveJhq")  //����������д�������ڱ���
	public void saveJhq(XsddBean beanback,HttpServletRequest request,HttpServletResponse response) throws Exception{
  	    request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
	
//System.out.println("����ɽ��XsddAction--add:insert"+inserted);
//System.out.println("����ɽ��XsddAction--add:delete"+deleted);
//System.out.println("����ɽ��XsddAction--add:update"+updated);


         if(updated != null){  
            //��json�ַ���ת���ɶ���  
		    List<XsddBean> listUpdated = new ArrayList<XsddBean>();	
            listUpdated = JSON.parseArray(updated, XsddBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddBean bean = (XsddBean)ele;	
 				 xsddService.updateBean(bean);
		     }    
			 sendSuccessMessage(response, "����ɹ�~");
          }
  		  return ;
	}

	@RequestMapping("/saveEcjhq")  //�����������ν����ڱ���
	public void saveEcjhq(XsddBean beanback,HttpServletRequest request,HttpServletResponse response) throws Exception{
  	    request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
	
//System.out.println("����ɽ��XsddAction--Ecjhq:insert"+inserted);
//System.out.println("����ɽ��XsddAction--Ecjhq:delete"+deleted);
//System.out.println("����ɽ��XsddAction--Ecjhq:update"+updated);


         if(updated != null){  
            //��json�ַ���ת���ɶ���  
		    List<XsddBean> listUpdated = new ArrayList<XsddBean>();	
            listUpdated = JSON.parseArray(updated, XsddBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddBean bean = (XsddBean)ele;	
 				 xsddService.updateBean(bean);
		     }
			 sendSuccessMessage(response, "����ɹ�~");
          }
    	  return ;
	}
	
    //�����������깤�㱨
	@RequestMapping("/saveWghb")
	public void  saveWghb(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 String ddid  = request.getParameter("ddid");

//System.out.println("����ɽ��XsddAction--savewghb:ddid="+ddid);
//System.out.println("����ɽ��XsddAction--savewghb:insert"+inserted);
//System.out.println("����ɽ��XsddAction--savewghb:delete"+deleted);
//System.out.println("����ɽ��XsddAction--savewghb:update"+updated);

		 String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String sdate;
		 Date ddate=null;
         sdate=sdf.format(new Date());	
		 try {
    	     ddate = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }
		 
         Integer row=0;
         if(deleted != null){  
             //��json�ַ���ת���ɶ���  

             List<XsddWghbBean> listDeleted = new ArrayList<XsddWghbBean>();	
             listDeleted = JSON.parseArray(deleted, XsddWghbBean.class);  
             //TODO ����Ϳ��Ը���ת����Ķ��������Ӧ�Ĳ����� 
			 Integer [] ids=new Integer[listDeleted.size()];

             row=0;
		     for (Object ele : listDeleted )
		     {
				 XsddWghbBean bean = (XsddWghbBean)ele;	  

                  Integer id1=bean.getId();
                  ids[row]=id1;
				  row++;
			 } 
			 xsddWghbService.delete(ids);
         }  
 
         if(inserted != null){  
             //��json�ַ���ת���ɶ���  
             List<XsddWghbBean> listInserted = new ArrayList<XsddWghbBean>();	
	
			 listInserted = JSON.parseArray(inserted, XsddWghbBean.class);  
	
			 for (Object ele : listInserted )
		     {
				 XsddWghbBean bean = (XsddWghbBean)ele;	
				 bean.setRk(0);
			     bean.setLrBy(username);
                 bean.setLrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
				 bean.setDdid(Integer.valueOf(ddid));
				 xsddWghbService.add(bean);
		     }
         }  

         if(updated != null){  
            //��json�ַ���ת���ɶ���  
		    List<XsddWghbBean> listUpdated = new ArrayList<XsddWghbBean>();	
            listUpdated = JSON.parseArray(updated, XsddWghbBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddWghbBean bean = (XsddWghbBean)ele;	
                 bean.setLrBy(username);	
                 bean.setLrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
 				 xsddWghbService.update(bean);
		     }
		 }
        java.sql.Date wgrq =null ;
		String wgzs="";
        List<XsddWghbBean> updateDataList =xsddWghbService.queryListById(Integer.valueOf(ddid));
		if (updateDataList!=null && updateDataList.size()>0 )
		{
			wgrq=updateDataList.get(0).getWgrq();
			wgzs=updateDataList.get(0).getCzg();

		}

		XsddBean xsddbean=xsddService.queryById(Integer.valueOf(ddid));

		if (xsddbean!=null )
		{
		
		    xsddbean.setMaxWgrq(wgrq);
		    xsddbean.setWgzs(wgzs);
            xsddService.updateBean(xsddbean);
		}
	    sendSuccessMessage(response, "����ɹ�~");
		return ;
	}

   //������������깤�㱨
	@RequestMapping("/saveWghbpl")
	public void  saveWghbpl(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
         //String deleted = request.getParameter("deleted");  
         //String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 //String ddid  = request.getParameter("ddid");

//System.out.println("����ɽ��XsddAction--savewghb:ddid="+ddid);
//System.out.println("����ɽ��XsddAction--savewghb:insert"+inserted);
//System.out.println("����ɽ��XsddAction--savewghb:delete"+deleted);
//System.out.println("����ɽ��XsddAction--savewghb:update"+updated);

		 String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String sdate;
		 Date ddate=null;
         sdate=sdf.format(new Date());	
		 try {
    	     ddate = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }
		 
         Integer row=0;
    

         if(updated != null){  
        //��json�ַ���ת���ɶ���  
		    List<XsddBean> listUpdated = new ArrayList<XsddBean>();	
            listUpdated = JSON.parseArray(updated, XsddBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddBean ddbean = (XsddBean)ele;	

				 Integer ddid=ddbean.getId();
                 //List<JtjhWghbBean> wghbList = jtjhWghbService.queryListByJhId(jhid);
//System.out.println("����ɽ��XsddAction--savewghb:update"+jhbean.getWgrq()+jhbean.getWgsl());
			     if (ddbean.getWgrq()!=null && ddbean.getWgsl()!=null)
			     {
					 XsddWghbBean wgbean=new XsddWghbBean();
					 wgbean.setDdid(ddid);
					 wgbean.setWgrq(ddbean.getWgrq());
					 wgbean.setWgsl(ddbean.getWgsl());
					 wgbean.setWgslss(ddbean.getWgslss());
					 wgbean.setCzg(ddbean.getCzg());
					 wgbean.setXpgg(ddbean.getXpgg());
					 wgbean.setCmsl(ddbean.getCmsl());

                     wgbean.setLrBy(username);	
                     wgbean.setLrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
 				     xsddWghbService.add(wgbean);
                     //�滻xsdd��
					 java.sql.Date wgrq =null ;
		             String wgzs="";
                     List<XsddWghbBean> updateDataList =xsddWghbService.queryListById(ddid);
		             if (updateDataList!=null && updateDataList.size()>0 )
		             {
			             wgrq=updateDataList.get(0).getWgrq();
			             wgzs=updateDataList.get(0).getCzg();

		             }

		             XsddBean xsddbean=xsddService.queryById(Integer.valueOf(ddid));

		             if (xsddbean!=null )
		             {
		
		                 xsddbean.setMaxWgrq(wgrq);
		                 xsddbean.setWgzs(wgzs);
                         xsddService.updateBean(xsddbean);
		             }
				 }
		     }
             sendSuccessMessage(response, "����ɹ�~");
		     return ;
		 }

	}

    //����ͳ�����㱨
	@RequestMapping("/saveRkhb")
	public void  saveRkhb(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 String ddid  = request.getParameter("ddid");

//System.out.println("����ɽ��XsddAction--saverkhb:ddid="+ddid);
//System.out.println("����ɽ��XsddAction--saverkhb:insert"+inserted);
//System.out.println("����ɽ��XsddAction--saverkhb:delete"+deleted);
//System.out.println("����ɽ��XsddAction--saverkhb:update"+updated);

		 String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String sdate;
		 Date ddate=null;
         sdate=sdf.format(new Date());	
		 try {
    	     ddate = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }

         if(updated != null){  
            //��json�ַ���ת���ɶ���  
		    List<XsddWghbBean> listUpdated = new ArrayList<XsddWghbBean>();	
            listUpdated = JSON.parseArray(updated, XsddWghbBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddWghbBean bean = (XsddWghbBean)ele;	
                 bean.setRklrBy(username);	
                 bean.setRklrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
 				 xsddWghbService.update(bean);
		     }
          }
		  Double ddsl=0.0;
		  XsddBean xsddbean=xsddService.queryById(Integer.valueOf(ddid));

		  if (xsddbean!=null )
		  {
		      ddsl=xsddbean.getSl()==null?0:xsddbean.getSl();
		  }

		  List<XsddWghbBean> wghbList = xsddWghbService.queryListById(Integer.valueOf(ddid));
          Integer qbrk=2;

		  if (wghbList!=null && wghbList.size()>0 )
		  {
				 Double sumrksl=0.0;
		      	 for (int i=0;i<wghbList.size();i++){
			         XsddWghbBean wghb = wghbList.get(i);
					 if (wghb.getRk()!=null && wghb.getRk()==1)
					 {
					     sumrksl=sumrksl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
					 }
		         }
				 if (sumrksl>=ddsl )
				 {
					 qbrk=1;
				 }
				 else{
				     if (ddsl!=0)
				     {
					     if ((ddsl-sumrksl)/ddsl<=0.1)  //С��10%��Ϊ���
					     {
						     qbrk=1;
					     }
				     }
				 }					 

		  }
	
		  xsddbean.setQbRk(qbrk);
		  xsddService.updateBean(xsddbean);
	
	      sendSuccessMessage(response, "����ɹ�~");
		  return ;
	}
     //����Excel,xsdd_bg
	@RequestMapping("/exportExcelBgd")
	public void  exportExcelBgd(HttpServletRequest request,HttpServletResponse response,HttpSession session)  
		throws Exception{
	    //request.setCharacterEncoding("UTF-8");  
	   	JSONObject context = new JSONObject();
        //��ȡ�༭���� �����ȡ������json�ַ���  

        String selected = request.getParameter("selected"); 
//System.out.println("����ɽ��exportExcle="+selected);
		 //String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
	     String sdate=sdf.format(new Date());	
		 if ( selected!=null )
		 {
		     List<XsddBgBean> listSelected = new ArrayList<XsddBgBean>();	
             listSelected = JSON.parseArray(selected, XsddBgBean.class);  
			 for (XsddBgBean st: listSelected  )
			 {
			
			     if ("״̬".equals(st.getField()))
			     {
                      if ("1".equals(st.getOldContent()))
                      {
					      st.setOldContent_dc("����");
                      }	
				      else if ("2".equals(st.getOldContent()))
				      {
					      st.setOldContent_dc("��ͣ");
				      }
				      else if ("3".equals(st.getOldContent()))
				      {
					      st.setOldContent_dc("����");
				      }
				      else {
				      }

                      if ("1".equals(st.getNewContent()))
                      {
					      st.setNewContent_dc("����");
                      }	
				      else if ("2".equals(st.getNewContent()))
				      {
					      st.setNewContent_dc("��ͣ");
				      }
				      else if ("3".equals(st.getNewContent()))
				      {
					      st.setNewContent_dc("����");
				      }
				      else{
				      }
			    }
			    else {
				    st.setOldContent_dc(st.getOldContent());
				    st.setNewContent_dc(st.getNewContent());
			    }
		    }
            String [] sortNameArr = {"bh","row"};
			ListUtils.sort(listSelected, true, "bh","row"); 

            String[] titles = new String[]{"�������", "��������", "�к�", "���۶������", "�к�", "�����Ŀ", "ԭ����", "���������", "�����"
			 , "�����", "ȷ����"};
            String[] fieldNames = new String[]{"createTime", "bh", "row", "jhbh", "jhbhrow", "field", "oldContent_dc", "newContent_dc", "createBy",
                "checkBy", "acceptBy"};
            try {
				String excelname="���۶������������_("+sdate+")";
				//String  serverFilePath=session.getServletContext().getRealPath("/uploadfiles/")+excelname+".xls";
				//serverFilePath=request.getRealPath("/")+ "\\uploadfiles\\" +excelname+".xls"; //getServletContext().getRealPath("/")
//System.out.println("����ɽold��"+request.getRealPath("/")+ "\\uploadfiles\\" +excelname+".xls");
				String path = session.getServletContext().getRealPath("/uploadfiles/");
//System.out.println("����ɽnew��"+path+File.separator+excelname+".xls");
                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                //eh1.writeExcel(JtjhBean.class, listSelected);
                eh1.writeExcel(XsddBgBean.class, listSelected, fieldNames, titles);
                //List<Employee> list1 = eh1.readExcel(Employee.class, fieldNames,
                //    true);
                //System.out.println("-----------------JXL2003.xls-----------------");
                //for (Employee user : list1) {
                //    System.out.println(user);
                //}
	            context.put("fileName", excelname+".xls");
		        context.put(SUCCESS, true);	
				HtmlUtil.writerJson(response, context);
  
            } catch (Exception e) {
				    sendFailureMessage(response,"����ʧ�ܣ�");
                    e.printStackTrace();

            } 
			 //ExportExcel excel=new ExportExcel();  
             //String[] Title={"ID","�´�����","�ƻ����","ҵ��Ա","�к�","����״̬","��Ʒ�ͺ�","��Ʒ���","��ѹ�ȼ�","����"
			 //,"��λ","�ƻ�����","�ͻ�Ҫ�󽻻���","�ƻ�������","����Ҫ��","����","����ԭ��","�����̨","���ν�����"
			 //,"�Ƿ��´��̨","����Ա","","","","","�깤����","�깤����","","Wg"
			 //,"Rk","Bg","Jtjh","�ϼ��깤����","�깤������ϸ","ʹ��ľ����ϸ","�ϼƳ�������","","�ϼ��������"
			 //,"��������ϸ","ȫ�����","��������(-9999Ϊδ����)"};  

		     
            // String rfp=excel.exportExcel(request,excelname+".xls",excelname,Title, result,41);   		 
	        // if (rfp.equals("ϵͳ��ʾ��Excel�ļ������ɹ���"))
	        // { 
             //   //request.setAttribute("fileName",excelname+".xls");
			//	context.put("fileName", excelname+".xls");
		     //   context.put(SUCCESS, true);	
			//	HtmlUtil.writerJson(response, context);
    			//sendSuccessMessage(response, rfp+",�ļ���Ϊ:"+excelname+".xls");
	         //}
			 //else{
			 //     sendFailureMessage(response,rfp);
			 //}
			
		    return ;
         }
		 sendFailureMessage(response, "����ʧ�ܣ������ԣ�");
	}
     //����Excel,xsdd--Ecjhq
	@RequestMapping("/exportExcelEcjhq")
	public void  exportExcelEcjhq(HttpServletRequest request,HttpServletResponse response,HttpSession session)  
		throws Exception{

	   	JSONObject context = new JSONObject();
        //��ȡ�༭���� �����ȡ������json�ַ���  

        String selected = request.getParameter("selected"); 

         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
	     String sdate=sdf.format(new Date());	
		 if ( selected!=null )
		 {
		     List<XsddBean> listSelected = new ArrayList<XsddBean>();	
             listSelected = JSON.parseArray(selected, XsddBean.class);  
			 List<XsddBean> result = new ArrayList<XsddBean>();
			 for ( XsddBean ddbean :listSelected) 
			 {
				 //����״̬����˵��
				 //XsddBean ddbean =listSelected.get(i);
				 if (ddbean.getState()!=null)
				 {
					 if (ddbean.getState()==1)
				     {
					     ddbean.setCstate("");
				     }
				     else if (ddbean.getState()==2)
				     {
					      ddbean.setCstate("��ͣ");
				     }
				     else if (ddbean.getState()==3)
				     {
					      ddbean.setCstate("����");
				     }
				     else {
					      ddbean.setCstate("");
				     }
				 }
				 else{
					 ddbean.setCstate("");
				 }
	 		    //����uodateBy ��ʾ��������
                if (ddbean.getState()!=null && ddbean.getState()==2)
			    {
				     ddbean.setCcqts("��ͣ");
			    }
			    else if (ddbean.getState()!=null && ddbean.getState()==3)
			    {
			         ddbean.setCcqts("����");
			    }
			    else{
				     if (ddbean.getCqts()==-9999)
				     {
					     ddbean.setCcqts("δ����");
				     }
				     else if (ddbean.getCqts()<0)
				     {
					      ddbean.setCcqts("��ǰ"+ddbean.getCqts()*(-1)+"��");
				     }
				     else if (ddbean.getCqts()>0)
				     {
					     ddbean.setCcqts("����"+ddbean.getCqts()+"��");
				     }
				     else if (ddbean.getCqts()==0)
				     {
					     ddbean.setCcqts("�������");
				     }
				     else{
					      ddbean.setCcqts("");
				     }
			    }
				result.add(ddbean);
			 }
             String [] sortNameArr = {"jhbh","row"};
			 ListUtils.sort(result, true, "jhbh","row"); 

             String[] titles = new String[]{"����״̬","��������", "�´�����", "�ƻ����",  "�к�", "�ͺ�", "���", "��ѹ�ȼ�", "����"
			 , "��λ", "����", "����Ҫ��", "״̬", "��������", "���ν�����", "����ԭ��", "�����̨"};
             String[] fieldNames = new String[]{"ccqts","cqts", "xdrq", "jhbh", "row", "xh", "gg", "dy", "gy", "dw",
                "sl", "jsyq", "cstate", "jhrq", "ecjhq", "ywyy", "ywjt"};
             try {
				String excelname="���ν�����ȷ�ϵ���_("+sdate+")";

				String path = session.getServletContext().getRealPath("/uploadfiles/");

                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                eh1.writeExcel(XsddBean.class, result, fieldNames, titles);

	            context.put("fileName", excelname+".xls");
		        context.put(SUCCESS, true);	
				HtmlUtil.writerJson(response, context);
  
             } catch (Exception e) {
				    sendFailureMessage(response,"����ʧ�ܣ�");
                    e.printStackTrace();

             } 
			
		     return ;
         }
		 sendFailureMessage(response, "����ʧ�ܣ������ԣ�");
	}

    //����Excel  xsdd 
	@RequestMapping("/exportExcel")
	public void  exportExcel(HttpServletRequest request,HttpServletResponse response,HttpSession session)  
		throws Exception{
	   	JSONObject context = new JSONObject();
        //��ȡ�༭���� �����ȡ������json�ַ���  

         String selected = request.getParameter("selected"); 
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
	     String sdate=sdf.format(new Date());	
		 if ( selected!=null )
		 {
		     List<XsddBean> listSelected = new ArrayList<XsddBean>();	
             listSelected = JSON.parseArray(selected, XsddBean.class);  
			 List<XsddBean> result = new ArrayList<XsddBean>();
			 for ( XsddBean ddbean :listSelected) 
			 {
				 //����״̬����˵��
				 //XsddBean ddbean =listSelected.get(i);
				 if (ddbean.getState()!=null)
				 {
					 if (ddbean.getState()==1)
				     {
					     ddbean.setCstate("");
				     }
				     else if (ddbean.getState()==2)
				     {
					      ddbean.setCstate("��ͣ");
				     }
				     else if (ddbean.getState()==3)
				     {
					      ddbean.setCstate("����");
				     }
				     else {
					      ddbean.setCstate("");
				     }
				 }
				 else{
					 ddbean.setCstate("");
				 }
				 //ȫ���깤
				 if (ddbean.getQbRk()!=null)
				 {
				
				     if (ddbean.getQbRk()==1)
				     {
					     ddbean.setCqbrk("ȫ���깤");
				     }
				     else{
					      ddbean.setCqbrk("");
				     }
				 }
                else {
					ddbean.setCqbrk("");
				}
				 //����uodateBy ��ʾ��������
                if (ddbean.getState()!=null && ddbean.getState()==2)
			    {
				     ddbean.setCcqts("��ͣ");
			    }
			    else if (ddbean.getState()!=null && ddbean.getState()==3)
			    {
			         ddbean.setCcqts("����");
			    }
			    else{
				     if (ddbean.getCqts()==-9999)
				     {
					     ddbean.setCcqts("δ����");
				     }
				     else if (ddbean.getCqts()<0)
				     {
					      ddbean.setCcqts("��ǰ"+ddbean.getCqts()*(-1)+"��");
				     }
				     else if (ddbean.getCqts()>0)
				     {
					     ddbean.setCcqts("����"+ddbean.getCqts()+"��");
				     }
				     else if (ddbean.getCqts()==0)
				     {
					     ddbean.setCcqts("�������");
				     }
				     else{
					      ddbean.setCcqts("");
				     }
			    }
				result.add(ddbean);
			 }

             String [] sortNameArr = {"jhbh","row"};
			 ListUtils.sort(result, true, "jhbh","row"); 
             /**
			 // ��userId����username����birthDate��������  
             String [] sortNameArr = {"userId","username","birthDate"};  
             boolean [] isAscArr = {true,false,true};  
             ListUtils.sort(list,sortNameArr,isAscArr);  
             System.out.println("\n--------��userId����username����birthDate�����������userId��ͬ������username����,���username��ͬ������birthDate����------------------");  
             testObj.printfUserInfoList(list);  
          
             // ��userId��username��birthDate����������  
             ListUtils.sort(list, true, "userId", "username","birthDate");  
             System.out.println("\n--------��userId��username��birthDate�������userId��ͬ������username����,���username��ͬ������birthDate����------------------");  
             testObj.printfUserInfoList(list);  
             */
             String[] titles = new String[]{"״̬","�´�����","ҵ��Ա", "�ƻ����", "�к�", "��Ʒ�ͺ�","��Ʒ���","��ѹ�ȼ�","����"
			 ,"��λ","�ƻ�����","�ͻ�Ҫ�󽻻���","�ƻ�������","����Ҫ��","����","����Ա","����ԭ��","�����̨","���ν�����","�깤����","�깤����"
			 ,"�ϼ��깤����","�깤������ϸ","ʵ��������ϸ","ʹ��ľ����ϸ","�ϼƳ�������","�ϼ��������" ,"��������ϸ", "ȫ���깤", "����״̬", "��������"};
             String[] fieldNames = new String[]{"cstate","xdrq", "ywy", "jhbh", "row", "xh", "gg", "dy", "gy", "dw",
                "sl", "jhrq_kh", "jhrq", "jsyq", "ph","createBy", "ywyy", "ywjt", "ecjhq", "maxWgrq", "wgzs"
			 ,"sumWgsl", "sumWgslds", "sumWgslss","sumXpgg","sumCmsl", "sumRksl", "sumRkslds", "cqbrk","ccqts","cqts"};

             try {
				String excelname="���۶�����ϸ��_"+sdate+")";

				String path = session.getServletContext().getRealPath("/uploadfiles/");

                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                //eh1.writeExcel(JtjhBean.class, listSelected);
                eh1.writeExcel(XsddBean.class, result, fieldNames, titles);
	            context.put("fileName", excelname+".xls");
		        context.put(SUCCESS, true);	
				HtmlUtil.writerJson(response, context);
  
             } catch (Exception e) {
				    sendFailureMessage(response,"����ʧ�ܣ�");
                    e.printStackTrace();

             } 
		     return;
         }
		 sendFailureMessage(response, "����ʧ�ܣ������ԣ�");
	}
	
/**
	    //request.setCharacterEncoding("UTF-8");  
	   	JSONObject context = new JSONObject();
        //��ȡ�༭���� �����ȡ������json�ַ���  

         String selected = request.getParameter("selected"); 
		 String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
	     String sdate=sdf.format(new Date());	
		 if ( selected!=null )
		 {
         
		     List<XsddBean> listSelected = new ArrayList<XsddBean>();	
		     List<Object> result=new ArrayList();		     
             listSelected = JSON.parseArray(selected, XsddBean.class);  
  
             String [] sortNameArr = {"jhbh","row"};
			 ListUtils.sort(listSelected, true, "jhbh","row"); 

 	         for (Object ele : listSelected )
			 //for (int i=0; i<listSelected.size() ;i++ )
			 {
	            
				 XsddBean bean  =  (XsddBean)ele;
				 result.add(bean);
            }

			 ExportExcel excel=new ExportExcel();  


		     String excelname="���۶�����ϸ��_("+sdate+")";
             String rfp=excel.exportExcel(session,excelname+".xls",excelname,Title, result,41);   		 
	         if (rfp.equals("ϵͳ��ʾ��Excel�ļ������ɹ���"))
	         { 
                //request.setAttribute("fileName",excelname+".xls");
				context.put("fileName", excelname+".xls");
		        context.put(SUCCESS, true);	
				HtmlUtil.writerJson(response, context);
    			//sendSuccessMessage(response, rfp+",�ļ���Ϊ:"+excelname+".xls");
	         }
			 else{
			      sendFailureMessage(response,rfp);
			 }
			
		     return ;
         }
		 sendFailureMessage(response, "����ʧ�ܣ������ԣ�");
	}
	*/

    @RequestMapping("/proUpload")
    public void proUpload (HttpServletRequest request ,
		HttpServletResponse response,HttpSession session) throws IOException,Exception{
 
		Iterator iter = null;
		String title = null;
		response.setContentType("text/html;charset=gbk");
		// ��ȡ�����
		PrintWriter out = response.getWriter();
		try
		{
			// ʹ��Uploader�����ϴ�
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = upload.parseRequest(request);
			iter = items.iterator();
			// ����ÿ�����ؼ���Ӧ������
			while (iter.hasNext())
			{
				FileItem item = (FileItem)iter.next();
				// �����������ͨ����
				if (item.isFormField())
				{
					String name = item.getFieldName();
					if (name.equals("title"))
					{
						title = item.getString("gbk");
					}
				}
				// �������Ҫ�ϴ����ļ�
				else
				{
					String user = (String)request.getSession()
						.getAttribute("curUser");
					String serverFileName = null;
					// �����ļ���
					String fileName = item.getName();
					if (fileName==null || "".equals(fileName))
					{
	                       out.write("<script type='text/javascript'>"
							+ "parent.callback('û��ѡ�����ļ���������ѡ��');"
							+ "</script>");
							return ;
					}
					// ȡ���ļ���׺
					String suffix = fileName.substring(
						fileName.lastIndexOf("."));
					// �����ļ�����
					String contentType = item.getContentType();
					// ֻ�����ϴ�xls(x)
					//if (contentType.equals("image/pjpeg")
					//	|| contentType.equals("image/gif")
					//	|| contentType.equals("image/jpeg")
					//	|| contentType.equals("image/png"))
					//{
					    InputStream input = item.getInputStream();
						serverFileName =  UUID.randomUUID().toString();
				        String path = session.getServletContext().getRealPath("/uploadfiles/");
  						String serverFilePath=path+File.separator+ serverFileName + suffix;
						//serverFilePath=request.getRealPath("/")+ "\\uploadfiles\\" + serverFileName + suffix; //getServletContext().getRealPath("/")
						FileOutputStream output = new FileOutputStream(serverFilePath);
						byte[] buffer = new byte[1024];
						int len = 0;
						while((len = input.read(buffer)) > 0 )
						{
							output.write(buffer , 0 , len);
						}
						input.close();
						output.close();
						String resultCheck= importExcelCheck(serverFilePath);
						if ("�����ȷ!".equals(resultCheck))
						{
						
	         			    importExcel(serverFilePath,request);

						    //as.addPhoto(user , title , serverFileName + suffix);
							out.write("<script type='text/javascript'>"
							+ "parent.callback('�ļ�����ɹ���');"
							+ "</script>");
						}
						else{
							out.write("<script type='text/javascript'>"
							+ "parent.callback('"+resultCheck+"');"
							+ "</script>");
						}
					
					//}
					//else
					//{
					//	out.write("<script type='text/javascript'>"
					//		+ "parent.callback('��ϵͳֻ�����ϴ�"
					//		+ "JPG��GIF��PNGͼƬ�ļ��������ԣ�')</script>");
					//}
				}
			}
		}
		catch (FileUploadException fue)
		{
			fue.printStackTrace();
			out.write("<script type='text/javascript'>"
				+ "parent.callback('�����ϴ��ļ����ִ��������ԣ�');"
				+ "</script>");
		 	 //sendFailureMessage(response, "�ϴ��ļ�ʧ�ܣ������ԣ�");
	         return ;

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
 }  
 
    //�����Excel�ļ����
    public String  importExcelCheck(String FileName) throws Exception{
         String result="�����ȷ!";

	     //����������  
         InputStream stream = new FileInputStream(FileName);  
         Workbook  rwb = Workbook.getWorkbook(stream);  
		 try{
             //��ȡ�ļ���ָ�������� Ĭ�ϵĵ�һ��  
             Sheet sheet = rwb.getSheet(0);    
             //����(��ͷ��Ŀ¼����Ҫ����1��ʼ)  
             for(int i=2; i<sheet.getRows(); i++){  
               
				Cell cell = null;     
				cell=sheet.getCell(0,i);
				String c1=cell.getContents();
                cell=sheet.getCell(1,i);
                String c2=cell.getContents();
                if ("".equals(c1.trim()) && "".equals(c1.trim())) //��1��2��Ϊ�գ�����
                {
					//result="�����ļ�Ϊ�գ�������ѡ���ļ�����!";
					break;

                }

				//����  
		        for(int j=0; j<sheet.getColumns(); j++){  
                    //��ȡ��i�У���j�е�ֵ  
                    cell = sheet.getCell(j,i);   
                    String str = cell.getContents();  

			        switch(j) 
                    { 
                       case 0: 
						   if (str==null || "".equals(str))
						   {
						       result="��Ʒ�ͺŲ���Ϊ�գ�������ѡ���ļ�����!";

						   }
				  	       //bean.setXh(str);
                           break; 
                       case 1: 
				  	       //bean.setGg(str);
                           break; 
                       case 2: 
				  	       //bean.setDy(str);
                           break; 
                       case 3: 
				  	       //bean.setDw(str);
                           break; 
                       case 4: 
						   if (str==null || "".equals(str) || !(NumberUtils.isNumber(str)) )
						   {
				  	           result="����Ϊ�ջ��߲�����Ч����ֵ��������ѡ���ļ�����!";

						   }
                           break; 
                       case 5: 
					       if (str==null || "".equals(str) || str.length()< 8 )
					       {
						       result="�ͻ�Ҫ�󽻻�����Ϊ�ջ��߲�����Ч�ĸ�ʽ(���ڸ�ʽ��YYYY-MM-DD)��������ѡ���ļ�����!";

					       }
                           break; 
                       case 6: 
					       break;
				       case 7: 
                           break; 
			           case 8: 
						   if (str==null || "".equals(str))
						   {
						       result="ҵ��Ա����Ϊ�գ�������ѡ���ļ�����!";

						   }
                           break; 
			           case 9: 
                           //bean.setPh(str);
                           break; 
			           case 10: 
                           //bean.setGy(str);
                           break; 
				       default: 
                           break; 
                  } 
             }  

         }  
	 }
	 finally{
		 if (rwb!=null){
			 rwb.close();
			 stream.close();
		 }
	 }

	 return result ;

 }  
    //@RequestMapping("/importExcel1")  
    //�����ϴ�Excel�ļ��������۶���
    public void  importExcel(String FileName,HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        String str = null; 
		String jhbh=null;
	    String username = SessionUtils.getUser(request).getNickName();
		 if (jhbh==null || "".equals(jhbh))
		 {
             String syear=DateUtil.getNowYear();
			 String smonth=DateUtil.getNowMonth();
			 Integer iyear=Integer.valueOf(syear);
             Integer imonth=Integer.valueOf(smonth);
			 JhbhBean jhbhbean  = jhbhService.queryNoByYearAndMonth(iyear, imonth,1);  //1--���۶������
			 //@Param("uuid")String uuid, @Param("portletid")String portletid
  		     if(jhbhbean  == null){
				  JhbhBean newbean=new JhbhBean();
                  newbean.setNian(iyear);
				  newbean.setYue(imonth);
				  newbean.setFlag(1);
				  newbean.setJhno(1);
				  jhbhService.add(newbean);
				  String sjhno=StringUtil.fillZero("1", 4);
				  jhbh=syear+smonth+sjhno;
		    }
			else 
			{
				 Integer newno=jhbhbean.getJhno()+1;
				 jhbhbean.setJhno(newno);
				 jhbhService.update(jhbhbean);
                 String sjhno=StringUtil.fillZero(newno+"", 4);
				 jhbh=syear+smonth+sjhno;
			}
	     }
		 String sdate;
		 Date dxdrq=null;
         sdate=sdf.format(new Date());	
		 try {
    	     dxdrq = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }
		 int row=1;
		 List<XsddBean> list = new ArrayList<XsddBean>();
	     //����������  
         InputStream stream = new FileInputStream(FileName);  
         Workbook  rwb = Workbook.getWorkbook(stream);  
		 try{
             //��ȡ�ļ���ָ�������� Ĭ�ϵĵ�һ��  
             Sheet sheet = rwb.getSheet(0);    
             //����(��ͷ��Ŀ¼����Ҫ����1��ʼ)  
             for(int i=2; i<sheet.getRows(); i++){  
               
				Cell cell = null;     
				cell=sheet.getCell(0,i);
				String c1=cell.getContents();
                cell=sheet.getCell(1,i);
                String c2=cell.getContents();
                if ("".equals(c1.trim()) && "".equals(c1.trim())) //��1��2��Ϊ�գ�����
                {
					break;
                }

		        XsddBean bean =new XsddBean();
			    bean.setXdrq(new java.sql.Date(dxdrq.getTime()));  //java.util.Date -->java.sql.Date 
			    bean.setJhbh(jhbh);
		        bean.setDeleted(DELETED.NO.key);
		        bean.setRow(row);
 		        bean.setState(1);
			    bean.setXdjt(0);
			    bean.setCreateBy(username);

				//����  
		        for(int j=0; j<sheet.getColumns(); j++){  
                    //��ȡ��i�У���j�е�ֵ  
                    cell = sheet.getCell(j,i);   
                    str = cell.getContents();  

			        switch(j) 
                    { 
                       case 0: 
				  	       bean.setXh(str);
                           break; 
                       case 1: 
				  	       bean.setGg(str);
                           break; 
                       case 2: 
				  	       bean.setDy(str);
                           break; 
                       case 3: 
				  	       bean.setDw(str);
                           break; 
                       case 4: 
						   if (str!=null && !("".equals(str)) && NumberUtils.isNumber(str) )
						   {
				  	           bean.setSl(Double.valueOf(str));
						    }
                           break; 
                       case 5: 
					       if (str!=null && !("".equals(str)) && str.length()>=8 )
					       {
					           //Date ddate = DateUtil.parse(str);
				  	           str=str.replace("/","-");
						       Date ddate=null;
	
	
						       try {
		                           ddate = sdf.parse(str);
                               } catch (ParseException e) {
                                   //e.printStackTrace();
                               }

		 			          bean.setJhrq_kh(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date 
					       }
                           break; 
                       case 6: 
					       break;
				       case 7: 
                           bean.setJsyq(str);
                           break; 
			           case 8: 
                           bean.setYwy(str);
                           break; 
			           case 9: 
                           bean.setPh(str);
                           break; 
			           case 10: 
                           bean.setGy(str);
                           break; 
				       default: 
                           break; 
                  } 
             }  
				 if (bean.getPh()==null || "".equals(bean.getPh())){
					 String phprefix="JH";
					 if ("���".equals(bean.getYwy())) {
					
						 phprefix="KC";
					 }

					 if ("KC".equals(phprefix) && ((bean.getJsyq()).indexOf("��/��")!=-1 || 
						 (bean.getJsyq()).indexOf("M/��")!=-1 || (bean.getJsyq()).indexOf("m/��")!=-1) )
					 { 
                         bean.setPh("KC");						
					 }
					 else
					 {
                         bean.setPh(phprefix+bean.getJhbh()+("JH".equals(phprefix)?bean.getYwy():"")+"-"+StringUtil.fillZero(bean.getRow()+"", 3));
					 }
				 }
		     xsddService.addBean(bean);
		     row++;
         }  
	 }
	 finally{
		 if (rwb!=null){
			 rwb.close();
			 stream.close();
		 }
	 }

 	 //sendSuccessMessage(response, "����ɹ�~");
	 //return ;

 }  

	
	@RequestMapping("/saveDdbg")  //�����������
	public void saveDdbg(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 request.setCharacterEncoding("UTF-8");  
         //��ȡ�༭����  
		 String id  = request.getParameter("id");
		 String jhbh  = request.getParameter("jhbh");
		 String row  = request.getParameter("row");
		 String ywy  = request.getParameter("ywy");
		 String ywynew  = request.getParameter("ywynew");
		 String ph  = request.getParameter("ph");
		 String phnew  = request.getParameter("phnew");

		 String xh  = request.getParameter("xh");
		 String xhnew  = request.getParameter("xhnew");
		 String gg  = request.getParameter("gg");
		 String ggnew  = request.getParameter("ggnew");
		 String dy  = request.getParameter("dy");
		 String dynew  = request.getParameter("dynew");
		 String gy  = request.getParameter("gy");
		 String gynew  = request.getParameter("gynew");
		 String dw  = request.getParameter("dw");
		 String dwnew  = request.getParameter("dwnew");
		 String sl  = request.getParameter("sl");
		 String slnew  = request.getParameter("slnew");
		 String jhrq  = request.getParameter("jhrq");
		 String jhrqnew  = request.getParameter("jhrqnew");
		 String jsyq  = request.getParameter("jsyq");
		 String jsyqnew  = request.getParameter("jsyqnew");
		 String state  = request.getParameter("state");
		 String statenew  = request.getParameter("statenew");

		 XsddBean ddbean  = xsddService.queryById(Integer.valueOf(id));
	     if(ddbean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		 }
		 String bh=null;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	     String username = SessionUtils.getUser(request).getNickName();
		 if (bh==null || "".equals(bh))
		 {
             String syear=DateUtil.getNowYear();
			 String smonth=DateUtil.getNowMonth();
			 Integer iyear=Integer.valueOf(syear);
             Integer imonth=Integer.valueOf(smonth);
			 JhbhBean jhbhbean  = jhbhService.queryNoByYearAndMonth(iyear, imonth,2);  //2--��������
			 //@Param("uuid")String uuid, @Param("portletid")String portletid
  		     if(jhbhbean  == null){
				  JhbhBean newbean=new JhbhBean();
                  newbean.setNian(iyear);
				  newbean.setYue(imonth);
				  newbean.setFlag(2);
				  newbean.setJhno(1);
				  jhbhService.add(newbean);
				  String sjhno=StringUtil.fillZero("1", 3);
				  bh=syear+smonth+sjhno;
		    }
			else 
			{
				 Integer newno=jhbhbean.getJhno()+1;
				 jhbhbean.setJhno(newno);
				 jhbhService.update(jhbhbean);
                 String sjhno=StringUtil.fillZero(newno+"", 3);
				 bh=syear+smonth+sjhno;
			}
	     }		 
		 int changeFlag=0;
		 int bgrow=1;
         if (!(ywy.equals(ywynew)) &&   StringUtils.isNotBlank(ywynew) )
         {
			 ddbean.setYwy(ywynew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("ҵ��Ա");
			 bgbean.setOldContent(ywy);
			 bgbean.setNewContent(ywynew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(ph.equals(phnew)) &&   StringUtils.isNotBlank(phnew) )
         {
			 ddbean.setPh(phnew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("����");
			 bgbean.setOldContent(ph);
			 bgbean.setNewContent(phnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }

         if (!(xh.equals(xhnew)) &&   StringUtils.isNotBlank(xhnew) )
         {
			 ddbean.setXh(xhnew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("��Ʒ�ͺ�");
			 bgbean.setOldContent(xh);
			 bgbean.setNewContent(xhnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gg.equals(ggnew)) && StringUtils.isNotBlank(ggnew) )
         {
			 ddbean.setGg(ggnew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("��Ʒ���");
			 bgbean.setOldContent(gg);
			 bgbean.setNewContent(ggnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(dy.equals(dynew)) &&  StringUtils.isNotBlank(dynew) )
         {
			 ddbean.setDy(dynew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("��ѹ");
			 bgbean.setOldContent(dy);
			 bgbean.setNewContent(dynew);
			 xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gy.equals(gynew)) &&  StringUtils.isNotBlank(gynew) )
         {
			 ddbean.setGy(gynew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("����");
			 bgbean.setOldContent(gy);
			 bgbean.setNewContent(gynew);
			 xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(dw.equals(dwnew)) &&  StringUtils.isNotBlank(dwnew) )
         {
			 ddbean.setDw(dwnew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("��λ");
			 bgbean.setOldContent(dw);
			 bgbean.setNewContent(dwnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(sl.equals(slnew)) &&  StringUtils.isNotBlank(slnew) )
         {
			 ddbean.setSl(Double.valueOf(slnew));
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("����");
			 bgbean.setOldContent(sl);
			 bgbean.setNewContent(slnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(jhrq.equals(jhrqnew))  &&  StringUtils.isNotBlank(jhrqnew) )
         {
             Date ddate=null;
	         //sdate=sdf.format(new Date());	
	         try {
	              ddate = sdf.parse(jhrqnew);
                 } catch (ParseException e) {
                 e.printStackTrace();
             }
			 ddbean.setJhrq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setField("��������");
			 bgbean.setRow(bgrow);
			 bgbean.setOldContent(jhrq);
			 bgbean.setNewContent(jhrqnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(jsyq.equals(jsyqnew)) &&  StringUtils.isNotBlank(jsyqnew))
         {
			 ddbean.setJsyq(jsyqnew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setField("����Ҫ��");
			 bgbean.setRow(bgrow);
			 bgbean.setOldContent(jsyq);
			 bgbean.setNewContent(jsyqnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(state.equals(statenew))  &&  StringUtils.isNotBlank(statenew) )
         {
			 ddbean.setState(Integer.valueOf(statenew));
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("״̬");
			 bgbean.setOldContent(state);
			 bgbean.setNewContent(statenew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
		 if (changeFlag==1)
		 {
			  xsddService.updateBean(ddbean);
		      sendSuccessMessage(response, "���۶�������ɹ�~");
		 }
		 else {
	   
		    sendFailureMessage(response, "δ�������۶��������");
		 }
	}	

	@RequestMapping("/saveDdbgCheck")  //���������˱���
	public void saveDdbgCheck(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 request.setCharacterEncoding("UTF-8");  
         //��ȡ�༭����  
		 String bh  = request.getParameter("bh");
	     String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String sdate;
		 Date ddate=null;
         sdate=sdf.format(new Date());	
		 try {
    	     ddate = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }
		 List<XsddBgBean> bgdataList  = xsddBgService.queryListByBh(bh);
	     if(bgdataList.size()  == 0){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		 }
		 for (Object ele:bgdataList )
		 {
             XsddBgBean bgbean = (XsddBgBean)ele;
			 bgbean.setCheckBy(username);
             bgbean.setChecked(1);
             bgbean.setCheckTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
	         xsddBgService.update(bgbean);
		 }

         sendSuccessMessage(response, "���������ˡ��ɹ�~");
	}	



	@RequestMapping("/saveDdbgAccept")  //�������ȷ�ϱ���
	public void saveDdbgAccept(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 request.setCharacterEncoding("UTF-8");  
         //��ȡ�༭����  
		 String bh  = request.getParameter("bh");
	     String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String sdate;
		 Date ddate=null;
         sdate=sdf.format(new Date());	
		 try {
    	     ddate = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }

		 List<XsddBgBean> bgdataList  = xsddBgService.queryListByBh(bh);
	     if(bgdataList.size()  == 0){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		 }
		 for (Object ele:bgdataList )
		 {
             XsddBgBean bgbean = (XsddBgBean)ele;
			 bgbean.setAcceptBy(username);
             bgbean.setAccept(1);
             bgbean.setAcceptTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
	         xsddBgService.update(bgbean);
		 }

         sendSuccessMessage(response, "�������ȷ�ϡ��ɹ�~");
	}	

	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		XsddBean bean  = xsddService.queryById(id);
		String jhbh= bean.getJhbh()	;
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
	    if(bean.getXdjt()  == 1 || bean.getJhrq()!=null){
			sendFailureMessage(response, "�ö�����ȷ�Ͻ����ڻ��߷ֽ��´��̨,�����޸�!");
			return;
		}
	    String username = SessionUtils.getUser(request).getNickName();
		String lrr=bean.getCreateBy();
		if (username.equals(lrr))
		{
	
//System.out.println("����ɽ-XsddAction--dataListById-jhbh"+jhbh);	
		    List<XsddBean> dataList = xsddService.queryListByJhbh(jhbh);

		    List<XsddBean> result = new ArrayList<XsddBean>();	

		    for (Object ele : dataList)
		    {
  			    XsddBean elebean = (XsddBean)ele;
			    result.add(elebean);
		    }
		    JSONArray jsonArr= new JSONArray(result);
		    String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-XsddAction--dataListById-jsonStr"+jsonArr.toString());
		//context.put("total",result.size());
		//context.put("rows", jsonArr.toString());
		    context.put("json",jsonStr);
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		    String sdate=df.format(new Date());

		    Map<String,Object>  data = new HashMap<String,Object> ();
		    if (result==null|| result.size()<1 ){	
	            data.put("jhbh", "");
		        data.put("xdrq", sdate);
			    data.put("ywy","");
			    data.put("jhrq_kh","");
		    }
		    else{
	            data.put("jhbh", result.get(0).getJhbh());
		        data.put("xdrq", result.get(0).getXdrq()!=null?(df.format(result.get(0).getXdrq())):"");
			    data.put("ywy",result.get(0).getYwy());	
			    data.put("jhrq_kh",result.get(0).getJhrq_kh()!=null?(df.format(result.get(0).getJhrq_kh())):"");
		    }
            context.put("data", data);
		    context.put(SUCCESS, true);	
		
//System.out.println("����ɽ-XsddAction--dataListById-context"+context.toString());
		    HtmlUtil.writerJson(response, context);
//		//List<XsddsBean> xsdds = xsddsService.queryByBtid(id);
//		//bean.setXsddmxs(xsdds);
//		context.put(SUCCESS, true);
//		context.put("data", bean);
//System.out.println("����ɽ-xsdd-GetId"+context.toString());
//		HtmlUtil.writerJson(response, context);
		}
		else 
		{
		    sendFailureMessage(response, "�ö���Ϊ("+bean.getCreateBy()+")�����������޸�!");
		    return;
		}
	}
    //�������
	@RequestMapping("/getDdbg")
	public void getDDbg(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		 XsddBean bean  = xsddService.queryById(id);
	     if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
	    //if(bean.getXdjt()  == 0 ){
		//	sendFailureMessage(response, "�ö�����δ�ֽ��´��̨,����Ҫ���!");
		//	return;
		//}	
	    if( bean.getJhrq()==null){
			sendFailureMessage(response, "�ö�����δȷ��������,����Ҫ���!");
			return;
		}		 		
		List<XsddBean> dataList = xsddService.queryListById(id);

		List<XsddBean> result = new ArrayList<XsddBean>();	

	    for (Object ele : dataList)
	    {
		    XsddBean elebean = (XsddBean)ele;
		    result.add(elebean);
	    }
		JSONArray jsonArr= new JSONArray(result);

	    String username = SessionUtils.getUser(request).getNickName();
		String lrr=bean.getCreateBy();
		if (username.equals(lrr))
		{
           	//Map<String,Object>  datanew = new HashMap<String,Object> ();
			JSONObject datanew= new JSONObject();
			datanew.put("ywynew","");
			datanew.put("phnew","");
		    datanew.put("xhnew","");
		    datanew.put("ggnew","");
		    datanew.put("dynew","");
            datanew.put("gynew","");
		    datanew.put("dwnew","");
		    datanew.put("slnew","");	
            datanew.put("jhrqnew","");
		    datanew.put("jsyqnew","");
			datanew.put("statenew","");
			context.put("data1",datanew);

            String jsonStr1=jsonArr.toString().replace("[","").replace("]","");
			jsonStr1=jsonStr1.substring(0,jsonStr1.length()-1);


            String jsonStr2=datanew.toString();
			jsonStr2=jsonStr2.substring(1,jsonStr2.length());

            context.put("data", jsonStr1+","+jsonStr2);

			context.put(SUCCESS, true);	

//System.out.println("����ɽ-xsdd-Getddbg"+context.toString());
	        HtmlUtil.writerJson(response, context);
			return ;
		}
		else 
		{
		    sendFailureMessage(response, "�ö���Ϊ("+bean.getCreateBy()+")���������ܽ��б��!");
		    return;
		}
	}

   //����������
	@RequestMapping("/getDdbgCheck")
	public void getDdbgCheck(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		JSONObject datanew= new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		 XsddBgBean bean  = xsddBgService.queryById(id);
	     if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}	
        int bgddid=bean.getDdid();	
		String bgbh=bean.getBh();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		List<XsddBean> dddataList = xsddService.queryListById(bgddid);

		List<XsddBean> result = new ArrayList<XsddBean>();	

	    for (Object ele : dddataList)
	    {
		    XsddBean elebean = (XsddBean)ele;
		    result.add(elebean);
	    }

	    //�ñ�ŵ����б������
		List<XsddBgBean> bgdataList = xsddBgService.queryListByBh(bgbh);
		    datanew.put("ywynew","");
            datanew.put("phnew","");
		  	datanew.put("xhnew","");
			datanew.put("ggnew","");
			datanew.put("dynew","");
			datanew.put("gynew","");
			datanew.put("dwnew","");
			datanew.put("slnew","");
			datanew.put("jhrqnew","");
			datanew.put("jsyqnew","");
			datanew.put("statenew","");
        for (Object ele : bgdataList)
	    {

			XsddBgBean bgbean = (XsddBgBean)ele;
			if ("ҵ��Ա".equals(bgbean.getField()))
			{
				dddataList.get(0).setYwy(bgbean.getOldContent());
				String ywynew=bgbean.getNewContent();
	            datanew.put("ywynew",ywynew);
				continue;
		    }
			if ("����".equals(bgbean.getField()))
			{
				dddataList.get(0).setPh(bgbean.getOldContent());
				String phnew=bgbean.getNewContent();
	            datanew.put("phnew",phnew);
				continue;
		    }


			if ("��Ʒ�ͺ�".equals(bgbean.getField()))
			{
				dddataList.get(0).setXh(bgbean.getOldContent());
				String xhnew=bgbean.getNewContent();
	            datanew.put("xhnew",xhnew);
				continue;
		    }

			if ("��Ʒ���".equals(bgbean.getField()))
			{
				dddataList.get(0).setGg(bgbean.getOldContent());
				String ggnew=bgbean.getNewContent();
				datanew.put("ggnew",ggnew);
				continue;
		    }	
			
			if ("��ѹ".equals(bgbean.getField()))
			{
				dddataList.get(0).setDy(bgbean.getOldContent());
				String dynew=bgbean.getNewContent();
		        datanew.put("dynew",dynew);
				continue;
		    }	

		    if ("����".equals(bgbean.getField()))
			{
				dddataList.get(0).setGy(bgbean.getOldContent());
				String gynew=bgbean.getNewContent();
				datanew.put("gynew",gynew);
				continue;
		    }	
		
		    if ("��λ".equals(bgbean.getField()))
			{
				dddataList.get(0).setDw(bgbean.getOldContent());
				String dwnew=bgbean.getNewContent();
                datanew.put("dwnew",dwnew);
				continue;
		    }	

		    if ("����".equals(bgbean.getField()))
			{
				if (bgbean.getOldContent()!=null &&  !"".equals(bgbean.getOldContent()))
				{
				    dddataList.get(0).setSl(Double.valueOf(bgbean.getOldContent()));
				}
				else{
                   dddataList.get(0).setSl(null);
				}
				String slnew=bgbean.getNewContent();
				datanew.put("slnew",slnew);	
				continue;
		    }	

		    if ("��������".equals(bgbean.getField()))
			{
			    Date ddate=null;

	            try {
					if (StringUtils.isNotBlank(bgbean.getOldContent()))
					{
	                    ddate = sdf.parse(bgbean.getOldContent());
					}
                 } catch (ParseException e) {
                    e.printStackTrace();
                }
				if (ddate!=null)
				{
	
				    dddataList.get(0).setJhrq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date
				}
				else{
					dddataList.get(0).setJhrq(null);
				}
				String jhrqnew=bgbean.getNewContent();
				datanew.put("jhrqnew",jhrqnew);
				continue;
		    }

		    if ("����Ҫ��".equals(bgbean.getField()))
			{
				dddataList.get(0).setJsyq(bgbean.getOldContent());
				String jsyqnew=bgbean.getNewContent();
				datanew.put("jsyqnew",jsyqnew);
				continue;
		    }	

		    if ("״̬".equals(bgbean.getField()))
			{
				dddataList.get(0).setState(Integer.valueOf(bgbean.getOldContent()));
				String statenew=bgbean.getNewContent();
				datanew.put("statenew",statenew);
				continue;
		    }	


	    }
		datanew.put("bh",bgbh);
		datanew.put("checkBy",bgdataList.get(0).getCheckBy());
		datanew.put("checkTime",bgdataList.get(0).getCheckTime()!=null?(sdf.format(bgdataList.get(0).getCheckTime())):"");

		context.put("data1",datanew);
		JSONArray jsonArr= new JSONArray(result);

        String jsonStr1=jsonArr.toString().replace("[","").replace("]","");
    	jsonStr1=jsonStr1.substring(0,jsonStr1.length()-1);

        String jsonStr2=datanew.toString();
		jsonStr2=jsonStr2.substring(1,jsonStr2.length());

        context.put("data", jsonStr1+","+jsonStr2);
		context.put(SUCCESS, true);	
//System.out.println("����ɽ-xsdd-GetddbgAccept"+context.toString());

        HtmlUtil.writerJson(response, context);
		return ;
	
	}

   //�������ȷ��
	@RequestMapping("/getDdbgAccept")
	public void getDdbgAccept(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		JSONObject datanew= new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		 XsddBgBean bean  = xsddBgService.queryById(id);
	     if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}	
		if (bean.getChecked()==null || bean.getChecked()!=1)
		{
			sendFailureMessage(response, "�ñ������δ������ˣ�����˺��ٽ���ȷ��!");
			return;

		}
        int bgddid=bean.getDdid();	
		String bgbh=bean.getBh();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		List<XsddBean> dddataList = xsddService.queryListById(bgddid);

		List<XsddBean> result = new ArrayList<XsddBean>();	

	    for (Object ele : dddataList)
	    {
		    XsddBean elebean = (XsddBean)ele;
		    result.add(elebean);
	    }

	    //�ñ�ŵ����б������
		List<XsddBgBean> bgdataList = xsddBgService.queryListByBh(bgbh);
		    datanew.put("ywynew","");
            datanew.put("phnew","");
		  	datanew.put("xhnew","");
			datanew.put("ggnew","");
			datanew.put("dynew","");
			datanew.put("gynew","");
			datanew.put("dwnew","");
			datanew.put("slnew","");
			datanew.put("jhrqnew","");
			datanew.put("jsyqnew","");
			datanew.put("statenew","");
        for (Object ele : bgdataList)
	    {

			XsddBgBean bgbean = (XsddBgBean)ele;
			if ("ҵ��Ա".equals(bgbean.getField()))
			{
				dddataList.get(0).setYwy(bgbean.getOldContent());
				String ywynew=bgbean.getNewContent();
	            datanew.put("ywynew",ywynew);
				continue;
		    }
			if ("����".equals(bgbean.getField()))
			{
				dddataList.get(0).setPh(bgbean.getOldContent());
				String phnew=bgbean.getNewContent();
	            datanew.put("phnew",phnew);
				continue;
		    }

			if ("��Ʒ�ͺ�".equals(bgbean.getField()))
			{
				dddataList.get(0).setXh(bgbean.getOldContent());
				String xhnew=bgbean.getNewContent();
	            datanew.put("xhnew",xhnew);
				continue;
		    }

			if ("��Ʒ���".equals(bgbean.getField()))
			{
				dddataList.get(0).setGg(bgbean.getOldContent());
				String ggnew=bgbean.getNewContent();
				datanew.put("ggnew",ggnew);
				continue;
		    }	
			
			if ("��ѹ".equals(bgbean.getField()))
			{
				dddataList.get(0).setDy(bgbean.getOldContent());
				String dynew=bgbean.getNewContent();
		        datanew.put("dynew",dynew);
				continue;
		    }	

		    if ("����".equals(bgbean.getField()))
			{
				dddataList.get(0).setGy(bgbean.getOldContent());
				String gynew=bgbean.getNewContent();
				datanew.put("gynew",gynew);
				continue;
		    }	
		
		    if ("��λ".equals(bgbean.getField()))
			{
				dddataList.get(0).setDw(bgbean.getOldContent());
				String dwnew=bgbean.getNewContent();
                datanew.put("dwnew",dwnew);
				continue;
		    }	

		    if ("����".equals(bgbean.getField()))
			{
				if (bgbean.getOldContent()!=null &&  !"".equals(bgbean.getOldContent()))
				{
				    dddataList.get(0).setSl(Double.valueOf(bgbean.getOldContent()));
				}
				else{
                   dddataList.get(0).setSl(null);
				}				String slnew=bgbean.getNewContent();
				datanew.put("slnew",slnew);	
				continue;
		    }	

		    if ("��������".equals(bgbean.getField()))
			{
			    Date ddate=null;

	            try {
					if (StringUtils.isNotBlank(bgbean.getOldContent()))
					{
	                    ddate = sdf.parse(bgbean.getOldContent());
					}
                 } catch (ParseException e) {
                    e.printStackTrace();
                }
				if (ddate!=null)
				{
	
				    dddataList.get(0).setJhrq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date
				}
				else{
					dddataList.get(0).setJhrq(null);
				}
				String jhrqnew=bgbean.getNewContent();
				datanew.put("jhrqnew",jhrqnew);
				continue;
		    }

		    if ("����Ҫ��".equals(bgbean.getField()))
			{
				dddataList.get(0).setJsyq(bgbean.getOldContent());
				String jsyqnew=bgbean.getNewContent();
				datanew.put("jsyqnew",jsyqnew);
				continue;
		    }	

		    if ("״̬".equals(bgbean.getField()))
			{
				dddataList.get(0).setState(Integer.valueOf(bgbean.getOldContent()));
				String statenew=bgbean.getNewContent();
				datanew.put("statenew",statenew);
				continue;
		    }	


	    }
		datanew.put("bh",bgbh);
		datanew.put("acceptBy",bgdataList.get(0).getAcceptBy());
		datanew.put("acceptTime",bgdataList.get(0).getAcceptTime()!=null?(sdf.format(bgdataList.get(0).getAcceptTime())):"");
		datanew.put("checkBy",bgdataList.get(0).getCheckBy());
		datanew.put("checkTime",bgdataList.get(0).getCheckTime()!=null?(sdf.format(bgdataList.get(0).getCheckTime())):"");

		context.put("data1",datanew);
		JSONArray jsonArr= new JSONArray(result);

        String jsonStr1=jsonArr.toString().replace("[","").replace("]","");
    	jsonStr1=jsonStr1.substring(0,jsonStr1.length()-1);

        String jsonStr2=datanew.toString();
		jsonStr2=jsonStr2.substring(1,jsonStr2.length());

        context.put("data", jsonStr1+","+jsonStr2);
		context.put(SUCCESS, true);	
//System.out.println("����ɽ-xsdd-GetddbgAccept"+context.toString());

        HtmlUtil.writerJson(response, context);
		return ;
	
	}
   //���������ѯ
	@RequestMapping("/getDdbgBrow")
	public void getDdbgBrow(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		JSONObject datanew= new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		 XsddBgBean bean  = xsddBgService.queryById(id);
	     if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}	
        int bgddid=bean.getDdid();	
		String bgbh=bean.getBh();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		List<XsddBean> dddataList = xsddService.queryListById(bgddid);

		List<XsddBean> result = new ArrayList<XsddBean>();	

	    for (Object ele : dddataList)
	    {
		    XsddBean elebean = (XsddBean)ele;
		    result.add(elebean);
	    }

	    //�ñ�ŵ����б������
		List<XsddBgBean> bgdataList = xsddBgService.queryListByBh(bgbh);
		    datanew.put("ywynew","");
            datanew.put("phnew","");
		  	datanew.put("xhnew","");
			datanew.put("ggnew","");
			datanew.put("dynew","");
			datanew.put("gynew","");
			datanew.put("dwnew","");
			datanew.put("slnew","");
			datanew.put("jhrqnew","");
			datanew.put("jsyqnew","");
			datanew.put("statenew","");

        for (Object ele : bgdataList)
	    {

			XsddBgBean bgbean = (XsddBgBean)ele;
			if ("ҵ��Ա".equals(bgbean.getField()))
			{
				dddataList.get(0).setYwy(bgbean.getOldContent());
				String ywynew=bgbean.getNewContent();
	            datanew.put("ywynew",ywynew);
				continue;
		    }
			if ("����".equals(bgbean.getField()))
			{
				dddataList.get(0).setPh(bgbean.getOldContent());
				String phnew=bgbean.getNewContent();
	            datanew.put("phnew",phnew);
				continue;
		    }

			if ("��Ʒ�ͺ�".equals(bgbean.getField()))
			{
				dddataList.get(0).setXh(bgbean.getOldContent());
				String xhnew=bgbean.getNewContent();
	            datanew.put("xhnew",xhnew);
				continue;
		    }

			if ("��Ʒ���".equals(bgbean.getField()))
			{
				dddataList.get(0).setGg(bgbean.getOldContent());
				String ggnew=bgbean.getNewContent();
				datanew.put("ggnew",ggnew);
				continue;
		    }	
			
			if ("��ѹ".equals(bgbean.getField()))
			{
				dddataList.get(0).setDy(bgbean.getOldContent());
				String dynew=bgbean.getNewContent();
		        datanew.put("dynew",dynew);
				continue;
		    }	

		    if ("����".equals(bgbean.getField()))
			{
				dddataList.get(0).setGy(bgbean.getOldContent());
				String gynew=bgbean.getNewContent();
				datanew.put("gynew",gynew);
				continue;
		    }	
		
		    if ("��λ".equals(bgbean.getField()))
			{
				dddataList.get(0).setDw(bgbean.getOldContent());
				String dwnew=bgbean.getNewContent();
                datanew.put("dwnew",dwnew);
				continue;
		    }	

		    if ("����".equals(bgbean.getField()))
			{
				if (bgbean.getOldContent()!=null &&  !"".equals(bgbean.getOldContent()))
				{
				    dddataList.get(0).setSl(Double.valueOf(bgbean.getOldContent()));
				}
				else{
                   dddataList.get(0).setSl(null);
				}				String slnew=bgbean.getNewContent();
				datanew.put("slnew",slnew);	
				continue;
		    }	

		    if ("��������".equals(bgbean.getField()))
			{
			    Date ddate=null;

	            try {
					if (StringUtils.isNotBlank(bgbean.getOldContent()))
					{
	                    ddate = sdf.parse(bgbean.getOldContent());
					}
                 } catch (ParseException e) {
                    e.printStackTrace();
                }
				if (ddate!=null)
				{
	
				    dddataList.get(0).setJhrq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date
				}
				else{
					dddataList.get(0).setJhrq(null);
				}
				String jhrqnew=bgbean.getNewContent();
				datanew.put("jhrqnew",jhrqnew);
				continue;
		    }

		    if ("����Ҫ��".equals(bgbean.getField()))
			{
				dddataList.get(0).setJsyq(bgbean.getOldContent());
				String jsyqnew=bgbean.getNewContent();
				datanew.put("jsyqnew",jsyqnew);
				continue;
		    }	

		    if ("״̬".equals(bgbean.getField()))
			{
				dddataList.get(0).setState(Integer.valueOf(bgbean.getOldContent()));
				String statenew=bgbean.getNewContent();
				datanew.put("statenew",statenew);
				continue;
		    }	


	    }
		datanew.put("bh",bgbh);
		datanew.put("acceptBy",bgdataList.get(0).getAcceptBy());
		datanew.put("acceptTime",bgdataList.get(0).getAcceptTime()!=null?(sdf.format(bgdataList.get(0).getAcceptTime())):"");
		datanew.put("checkBy",bgdataList.get(0).getCheckBy());
		datanew.put("checkTime",bgdataList.get(0).getCheckTime()!=null?(sdf.format(bgdataList.get(0).getCheckTime())):"");

		context.put("data1",datanew);
		JSONArray jsonArr= new JSONArray(result);

        String jsonStr1=jsonArr.toString().replace("[","").replace("]","");
    	jsonStr1=jsonStr1.substring(0,jsonStr1.length()-1);

        String jsonStr2=datanew.toString();
		jsonStr2=jsonStr2.substring(1,jsonStr2.length());

        context.put("data", jsonStr1+","+jsonStr2);
		context.put(SUCCESS, true);	
//System.out.println("����ɽ-xsdd-GetddbgAccept"+context.toString());

        HtmlUtil.writerJson(response, context);
		return ;
	
	}		
		
   //�깤�㱨
	@RequestMapping("/getWghb")
	public void getWghb(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		XsddBean bean  = xsddService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
		List<XsddWghbBean> dataList = xsddWghbService.queryListById(id);
		List<XsddWghbBean> result = new ArrayList<XsddWghbBean>();	
		for (Object ele : dataList)
		{
			XsddWghbBean elebean = (XsddWghbBean)ele;
			result.add(elebean);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-XsddAction--getWghb-jsonArr"+jsonStr);
		context.put("json",jsonStr);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ

		Map<String,Object>  data = new HashMap<String,Object> ();
	    data.put("ddid", bean.getId());
	    data.put("jhbh", bean.getJhbh());
	    data.put("row", bean.getRow());
		data.put("state",bean.getState());	
		data.put("jhrq",bean.getJhrq()!=null?(df.format(bean.getJhrq())):"");
	    data.put("xh", bean.getXh());
	    data.put("gg", bean.getGg());
		data.put("dy",bean.getDy());	
		data.put("gy",bean.getGy());	
	    data.put("sl", bean.getSl());
		data.put("jsyq",bean.getJsyq());	

        context.put("data", data);
		context.put(SUCCESS, true);	
		
//System.out.println("����ɽ-XsddAction---getWghb-context"+context.toString());
		HtmlUtil.writerJson(response, context);

	}

   //���㱨
	@RequestMapping("/getRkhb")
	public void getRkhb(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		XsddBean bean  = xsddService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
		List<XsddWghbBean> dataList = xsddWghbService.queryListById(id);
		List<XsddWghbBean> result = new ArrayList<XsddWghbBean>();	
		for (Object ele : dataList)
		{
			XsddWghbBean elebean = (XsddWghbBean)ele;
			result.add(elebean);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-XsddAction--getRkhb-jsonArr"+jsonStr);
		context.put("json",jsonStr);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ

		Map<String,Object>  data = new HashMap<String,Object> ();
	    data.put("ddid", bean.getId());
	    data.put("jhbh", bean.getJhbh());
	    data.put("row", bean.getRow());
		data.put("state",bean.getState());	
		data.put("jhrq",bean.getJhrq()!=null?(df.format(bean.getJhrq())):"");
	    data.put("xh", bean.getXh());
	    data.put("gg", bean.getGg());
		data.put("dy",bean.getDy());	
		data.put("gy",bean.getGy());	
	    data.put("sl", bean.getSl());
		data.put("jsyq",bean.getJsyq());	

        context.put("data", data);
		context.put(SUCCESS, true);	
		
//System.out.println("����ɽ-XsddAction---getRkhb-context"+context.toString());
		HtmlUtil.writerJson(response, context);
	}

  
   //���ظ��Ĳ�Ʒ�ͺ�
	@RequestMapping("/getUniXh")
	public void getUniXh(HttpServletResponse response) throws Exception{
	
		List<XsddBean> dataList = xsddService.queryByAll();
	
	    List<String> xh1 = new ArrayList();
		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;
			xh1.add(st.getXh());
		}
		

		//ɾ���ظ���
		List<String> unixh1=new ArrayList();
		unixh1=removeDuplicate(xh1);
		Collections.sort(unixh1);
		List<JsonBean> unixh = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unixh1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unixh1.get(i));
            id++;
			unixh.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unixh);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}

    //���ظ��Ĳ�Ʒ���
	@RequestMapping("/getUniGg")
	public void getUniGg(HttpServletResponse response) throws Exception{
	
		List<XsddBean> dataList = xsddService.queryByAll();

		List<String> gg1 = new ArrayList();

		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;
			gg1.add(st.getGg());
		}
		

		//ɾ���ظ���
	
		List<String> unigg1=new ArrayList();
		unigg1=removeDuplicate(gg1);
		Collections.sort(unigg1);

		List<JsonBean> unigg = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unigg1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unigg1.get(i));
            id++;
			unigg.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unigg);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}

   //���ظ��Ĳ�Ʒ���
	@RequestMapping("/getUniDdy")
	public void getUniDdy(HttpServletResponse response) throws Exception{
	
		List<XsddBean> dataList = xsddService.queryByAll();

		List<String> ddy1 = new ArrayList();

		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;
			ddy1.add(st.getCreateBy());

		}
		

		//ɾ���ظ���
	
		List<String> uniddy1=new ArrayList();
		uniddy1=removeDuplicate(ddy1);
		Collections.sort(uniddy1);

		List<JsonBean> uniddy = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<uniddy1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(uniddy1.get(i));
            id++;
			uniddy.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(uniddy);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}
/**
	 * �޸����۶������
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updateBh")
	public void updateBh(Integer id,String jhbh,String newJhbh,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
	    XsddBean bean  = xsddService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}		
		
		if(StringUtils.isBlank(newJhbh)){
			sendFailureMessage(response, "���۶������δ���룡");
			return;
		}
        List<XsddBean> dataList = new ArrayList<XsddBean>();	
		dataList = xsddService.queryListByJhbh(jhbh);
		int flagXdjt=0;
        for (Object ele : dataList )
		{
	        if(bean.getXdjt()  == 1 ){//|| bean.getJhrq()!=null
			    flagXdjt=1;
			    break;
		    }
	    } 
	
	    if(flagXdjt  == 1 ){
			sendFailureMessage(response, "�ö����ѷֽ��´��̨,�����޸Ķ������!");//ȷ�Ͻ����ڻ���
			return;
		}
	    String username = SessionUtils.getUser(request).getNickName();
		String lrr=bean.getCreateBy();
		if (username.equals(lrr))
		{
	
		    //���ǳ�������Ա��ƥ�������
		    //if(!isAdmin && !MethodUtil.ecompareMD5(oldPwd,bean.getPwd())){
		    //	sendFailureMessage(response, "Wrong old password.");
		    //	return;
		    //}
	        
		     dataList = xsddService.queryListByJhbh(jhbh);

             for (Object ele : dataList )
		     {
			     XsddBean ddbean = (XsddBean)ele;	    
                 ddbean.setJhbh(newJhbh);  

			     xsddService.updateBean(ddbean);
	        } 
	
		    sendSuccessMessage(response, "���۶�������޸ĳɹ�~");
		}
		else 
		{
		    sendFailureMessage(response, "�ö���Ϊ("+bean.getCreateBy()+")�����������޸Ķ������!");
		    return;
		}

	}

	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletRequest request,HttpServletResponse response) throws Exception{

		if(id != null && id.length > 0){
			for (int i=0;i<id.length;i++ )
			{
				  String message;

				  XsddBean bean  = xsddService.queryById(id[i]);
	              String username = SessionUtils.getUser(request).getNickName();
		          String lrr=bean.getCreateBy();	
		          if (!username.equals(lrr))
		          {
		              sendFailureMessage(response, "�����۶���Ϊ("+lrr+")����������ɾ��!");
		              continue ;
				  }
				  if(bean.getXdjt()  == 1 || bean.getJhrq()!=null ){
					  message="IDΪ("+id[i]+")�Ķ���,��ȷ�Ͻ����ڻ��߷ֽ��´��̨,����ɾ����";
                      sendFailureMessage(response, message);
					  continue ;
					  
		           } 
				
				   else{
			           xsddService.delete(id[i]); 
					   message="IDΪ("+id[i]+")�Ķ���,ɾ���ɹ���";
   		               sendSuccessMessage(response,  message);	
	
				   }
			}

		}else{
			sendFailureMessage(response, "δѡ�м�¼");
			return; 
		}
	}

	/**
	 * �������۶�����ϸ
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/xsdds") 
	public ModelAndView  userRole(HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("/business/xsddsManage", context);
	}

	/**
	 * ��ѯ���۶�����ϸ
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getXsdds") 
	public void getXsdds(Integer id,HttpServletResponse response)  throws Exception{
		//Map<String,Object>  context = getRootMap();
		//JSONObject jsonMap = new JSONObject();
		JSONObject context = new JSONObject();	
		XsddBean bean  = xsddService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
		JSONObject data =  new JSONObject();	
		data.put("id", bean.getId());
		data.put("xdrq", bean.getXdrq());
		data.put("ywy", bean.getYwy());
		data.put("jhbh", bean.getJhbh());
		data.put("jhrq_kh", bean.getJhrq_kh());
		context.put(SUCCESS, true);
		context.put("data", data);

		List<XsddsBean> dataList = xsddsService.queryByBtid(id);
		List<XsddsBean> result = new ArrayList<XsddsBean>();	
		// ��װVO����
		for (Object ele : dataList)
		{
			// ÿ������Ԫ�ض���StockBean����
			XsddsBean st = (XsddsBean)ele;
			result.add(st);
		}
		//����ҳ������
		context.put("total",result.size());
		context.put("rows", result);
//System.out.println("����ɽ��XsddAction--getXsdds"+context.toString());	
       HtmlUtil.writerJson(response, context);

	
	}
		

	@RequestMapping("/getXsddTree")
	public void getXsddTree(Integer id,HttpServletResponse response) throws Exception{
		List<TreeNode> xsddTree = treeXsdd();
		List<TreeNode> result = new ArrayList<TreeNode>();
		// ��װVO����
		for (Object ele : xsddTree)
		{
			// ÿ������Ԫ�ض���TreeNoden����
			TreeNode st = (TreeNode)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
//System.out.println("����ɽ��XsddAction--xsdd-tree"+jsonArr.toString());
		HtmlUtil.writerJson(response, jsonArr);

		//HtmlUtil.writerJson(response, menuTree);
	}
	
	/**
	 * �������β˵�
	 * @return
	 */
	public List<TreeNode> treeXsdd(){
		List<XsddBean> rootItems = xsddService.queryByAll();//���ڵ㣬���۶�����ͷ
		List<XsddsBean> childItems = xsddsService.queryByAll();//�ӽڵ㡣���۶�������
		XsddTree util = new XsddTree(rootItems,childItems);
		return util.getTreeNode();
	}
	
	/**
	 * ��ȡ����Ĳ˵���ť����
	 * @param request
	 * @return
	 */
	public List<XsddsBean> getReqxsdds(HttpServletRequest request){
		List<XsddsBean> xsddsList= new ArrayList<XsddsBean>();
		String[] xhs  = request.getParameterValues("xh");
		String[] ggs  = request.getParameterValues("gg");
		String[] dys  = request.getParameterValues("dy");
		String[] gys  = request.getParameterValues("gy");
		String[] dws  = request.getParameterValues("dw");
		String[] phs  = request.getParameterValues("ph");
		String[] sls  = request.getParameterValues("sl");
		String[] jhrq_khs  = request.getParameterValues("jhrq_kh");
		String[] jsyqs  = request.getParameterValues("jsyq");
		//String jhbh  = request.getParameterValues("xh");
        int row=1 ;
		for (int i = 0; i < xhs.length; i++) {
			if(StringUtils.isNotBlank(xhs[i]) && StringUtils.isNotBlank(ggs[i])){
				//if(StringUtils.isNotBlank(btnId[i]) && NumberUtils.isNumber(btnId[i])){
				//	XsddsBean.setId(NumberUtils.toInt(btnId[i]));
				//}

				XsddsBean xsdds = new XsddsBean();
				xsdds.setXh(xhs[i]);
				xsdds.setGg(ggs[i]);
				xsdds.setDy(dys[i]);
				xsdds.setGy(gys[i]);
				xsdds.setDw(dws[i]);
				xsdds.setPh(phs[i]);
				xsdds.setSl(Double.valueOf(sls[i]));	//�ַ���ת��Ϊdouble
				 try {
 			        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(jhrq_khs[i]);
//System.out.println("����ɽdate"+date);
  	                xsdds.setJhrq_kh(new java.sql.Date(date.getTime()));	       
                  } catch (ParseException e) {
                     e.printStackTrace();
                  }

                //System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date2));
                //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2));
				
					
				xsdds.setJsyq(jsyqs[i]);
				xsdds.setRow(row);
				row++;
				xsddsList.add(xsdds);
			}
		}
		
		return xsddsList;
     }
	 /**
	 * ���ز��ظ���ListԪ��
	 * @param List 
	 * @return ���ظ���List
	 */
	public List<String> removeDuplicate(List<String> list)
	{
       for ( int i = 0 ; i < list.size() - 1 ; i ++ ) 
		   {
               for ( int j = list.size() - 1 ; j > i; j -- )
				   {
                         if (list.get(j).equals(list.get(i))) 

						  { 
						  
						      list.remove(j);
                           }
                    }
           }

		   return list;
    }	

}
