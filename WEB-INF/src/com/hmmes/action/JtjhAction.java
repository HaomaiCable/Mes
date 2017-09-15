

package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Collections; 
import java.text.ParseException;
import java.io.File;
//import java.util.Date;

import net.sf.json.JSONObject;
import org.json.JSONArray;
//import net.sf.json.JsonConfig;

import com.alibaba.fastjson.JSON;  
//import com.alibaba.fastjson.JSONObject;  
import com.alibaba.fastjson.annotation.JSONField;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hmmes.bean.BaseBean.DELETED;

import com.hmmes.bean.ParaBean;
import com.hmmes.bean.CpBean;
import com.hmmes.bean.CpgxmxBean;
import com.hmmes.bean.JtjhBean;
import com.hmmes.bean.JtjhWghbBean;
import com.hmmes.bean.XsddBean;
import com.hmmes.bean.SbBean;
import com.hmmes.bean.JtBean;
import com.hmmes.bean.JsonBean;
import com.hmmes.bean.GsdeBean;

import com.hmmes.model.JtjhModel;


import com.hmmes.service.JtjhService;
import com.hmmes.service.JtjhWghbService;
import com.hmmes.service.ParaService;
import com.hmmes.service.XsddService;
import com.hmmes.service.CpService;
import com.hmmes.service.CpgxmxService;
import com.hmmes.service.SbService;
import com.hmmes.service.JtService;
import com.hmmes.service.GsdeService;

import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.SessionUtils;
import com.hmmes.utils.DateUtil;
import com.hmmes.utils.StringUtil;
import com.hmmes.utils.excelutils.ExcelHelper;
import com.hmmes.utils.excelutils.JxlExcelHelper;
import com.hmmes.utils.ListUtils;
 
@Controller
@RequestMapping("/jtjhManage") 
public class JtjhAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(JtjhAction.class);
	
	// Servrice start
	// Servrice start
	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private JtjhService<JtjhBean> jtjhService; 
	
	@Autowired
	private JtjhWghbService<JtjhWghbBean> jtjhWghbService;


    @Autowired	
	private ParaService<ParaBean> paraService;
    @Autowired	
	private XsddService<XsddBean> xsddService;
    @Autowired	
	private CpService<CpBean> cpService;
    @Autowired	
	private CpgxmxService<CpgxmxBean> cpgxmxService;
    @Autowired	
	private SbService<SbBean> sbService;
    @Autowired	
	private JtService<JtBean> jtService;
    @Autowired	
	private GsdeService<GsdeBean> gsdeService;

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/jtjh")
	public ModelAndView  list(JtjhModel model,HttpServletRequest request) throws Exception{

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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhManage",context); 

	}
	
	@RequestMapping("/jtjhwghb_xl")  //���¹���
	public ModelAndView  list_xl(JtjhModel model,HttpServletRequest request) throws Exception{

		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.MONTH,-1);//����-1����,�����¼ƻ�
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    model.setDeleted(DELETED.NO.key);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhWgReport_xl",context); 

	}	
	@RequestMapping("/jtjhwghb_xlpl")  //���¹���
	public ModelAndView  list_xlpl(JtjhModel model,HttpServletRequest request) throws Exception{

		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.MONTH,-1);//����-1����,�����¼ƻ�
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    model.setDeleted(DELETED.NO.key);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhWgReport_xlpl",context); 

	}	

	@RequestMapping("/jtjhwghb_bs")  //��˿����
	public ModelAndView  list_bs(JtjhModel model,HttpServletRequest request) throws Exception{

		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.MONTH,-1);//����-1����,�����¼ƻ�
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    model.setDeleted(DELETED.NO.key);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhWgReport_bs",context); 

	}	
	@RequestMapping("/jtjhwghb_bspl")  //��˿��������
	public ModelAndView  list_bspl(JtjhModel model,HttpServletRequest request) throws Exception{

		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.MONTH,-1);//����-1����,�����¼ƻ�
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    model.setDeleted(DELETED.NO.key);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhWgReport_bspl",context); 

	}	

	@RequestMapping("/jtjhwghb_dy")  //��ѹ����
	public ModelAndView  list_dy(JtjhModel model,HttpServletRequest request) throws Exception{

		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.MONTH,-1);//����-1����,�����¼ƻ�
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    model.setDeleted(DELETED.NO.key);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhWgReport_dy",context); 

	}	

	@RequestMapping("/jtjhwghb_dypl")  //��ѹ���������㱨
	public ModelAndView  list_dypl(JtjhModel model,HttpServletRequest request) throws Exception{

		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.MONTH,-1);//����-1����,�����¼ƻ�
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    model.setDeleted(DELETED.NO.key);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhWgReport_dypl",context); 

	}	

	@RequestMapping("/jtjhwghb_gy")  //��ѹ����
	public ModelAndView  list_gy(JtjhModel model,HttpServletRequest request) throws Exception{

		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.MONTH,-1);//����-1����,�����¼ƻ�
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    model.setDeleted(DELETED.NO.key);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhWgReport_gy",context); 

	}	
	
	@RequestMapping("/jtjhwghb_gypl")  //��ѹ����
	public ModelAndView  list_gypl(JtjhModel model,HttpServletRequest request) throws Exception{

		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//���۶�������ʱ�䣬����
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
         // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
         rightNow.add(Calendar.MONTH,-1);//����-1����,�����¼ƻ�
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//���۶�����ʼʱ��
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    model.setDeleted(DELETED.NO.key);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhWgReport_gypl",context); 

	}	

    /**
	 * ��̨�ƻ����
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/jtjhbrow")
	public ModelAndView  jtjhbrow(JtjhModel model,HttpServletRequest request) throws Exception{
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//����ʱ�䣬����
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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/jtjhBrow",context); 
	}	


	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList")  //��ʾȫ����̨�ƻ��б�
	public void  dataList(JtjhModel model,HttpServletResponse response) throws Exception{
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		List<JtjhBean> result = new ArrayList<JtjhBean>();
		// ��װVO����
		for (Object ele : dataList)
		{
			JtjhBean st = (JtjhBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);
	}


	@RequestMapping("/dataListLinkForGd")   //DataGrid url ���������깤�㱨(Wghb)���ݿ⣬�����Ϣ
	public void  dataListLinkForGd(Integer gdid,JtjhModel model,HttpServletResponse response) throws Exception{
		//List<JtjhBean> dataList = jtjhService.queryByListNoPage(model);//jtjhService.queryByAll();
		String gd="";
        switch (gdid)
        {
            case 1:
                gd="��˿����";
                break;
            case 2:
                gd="���¹���";
                break;
            case 3:
                gd="��ѹ����";
                break;
            case 4:
                gd="��ѹ����";
                break;
		    default:
				gd="";
			    break;
        }
	/**
		List<JtjhBean> dataListpre = jtjhService.queryByAll();//queryByList(model)
		for (Object ele : dataListpre)
		{
			JtjhBean st = (JtjhBean)ele;
		    SbBean sbbean =sbService.queryBySbmc(st.getSbmc());
		    String jtmc="";
		    if (sbbean!=null )
		    {
			    jtmc=sbbean.getJt()==null?"":sbbean.getJt();
		    }
		    String gdmc="";
		    JtBean jtbean = jtService.queryByJtmc(jtmc);
		    if (jtbean!=null)
		    {
			    gdmc=jtbean.getGd()==null?"":jtbean.getGd();
		    }
		    //if (gd.equals(gdmc.trim()))   //���ǲ������ݵĹ���,����ʾ

		    //{
//System.out.println("����ɽ��1"+gdmc);
			    st.setGd(gdmc);  
				jtjhService.update(st);
		    //}
		}
*/
		model.setGd(gd);
		List<JtjhBean> dataList = jtjhService.queryByListGd(model);
		//List<JtjhBean> dataList = jtjhService.queryByAll();


  		List<JtjhBean> result = new ArrayList<JtjhBean>();	
	    int totaljh=0;
		int noontimejh=0;
		DecimalFormat  df2   = new DecimalFormat("######0.00");
		for (Object ele : dataList)
		{
			JtjhBean st = (JtjhBean)ele;

		    DecimalFormat  df0   = new DecimalFormat("######0");
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            Integer qbwg=0;
	        String sdate=sdf.format(new Date());
	        Date ud= sdf.parse(sdate);
	        java.sql.Date sd = new java.sql.Date(ud.getTime());   
	        long cqts=-9999;
		    Double jhsl=st.getJhsl_o()==null?0:st.getJhsl_o();
			Integer  jhid=st.getId();
		    List<JtjhWghbBean> wghbList = jtjhWghbService.queryListByJhId(jhid);

		    if (wghbList!=null && wghbList.size()>0 )
		    {
                 if ((wghbList.get(0).getWgrq())!=null)
                 {
	                  st.setMaxWgrq(wghbList.get(0).getWgrq());
     	         }
			     Double sumwgsl=0.0;
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
			     else{
			         if (jhsl!=0)
			         {
				         if ((jhsl-sumwgsl)/jhsl<=0.1)  //С��10%��Ϊ���
				         {
					         qbwg=1;
				         }
			         }
			     }	
			     if (st.getJhrq()!=null && !"".equals(st.getJhrq()))
			     {
	
			         if (qbwg==1)
			         {
                          if (st.getMaxWgrq()==null || "".equals(st.getMaxWgrq()))
                          {
                              cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
                          }
						  else{
						      cqts=(st.getMaxWgrq().getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
                          }

					 }
			         else{
				         if ((sd.getTime()-st.getJhrq().getTime())<=0)
					     {
						      //cqts=-9999;
							  cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
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
			     st.setSumWgslds("��"+sumwgslds.toString()+"��");
				 if (qbwg==0 && st.getState()==1)
				 {
					 st.setWwgsl(jhsl-sumwgsl);
				 }
			     st.setQbWg(qbwg);
				 if (st.getState()==1)
				 { 
					 st.setCqts(cqts);
				 }
			    
				 //st.setWgflag(qbwg==1?"���깤":"δ�깤");
				 //jtjhService.update(st); //�㱨ʱ���㱣�� 
		    }
		    else{
			     if (st.getJhrq()!=null && !"".equals(st.getJhrq())){
			         if ((sd.getTime()-st.getJhrq().getTime())<=0)
			         {
				         //cqts=-9999;
						 cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
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
					 st.setWwgsl(jhsl);
				 }
			   	 
				 //st.setWgflag(qbwg==1?"���깤":"δ�깤");
				 //jtjhService.update(st);//����ʱ�����ó�ʼֵ��δ�깤��
		    } 
			if (st.getState()==1)
			{
			    totaljh++;
			}
		    if (cqts > 0 && st.getState()==1)
		    {
		        noontimejh++;
		    }				    
		    result.add(st);
		}

    	//����ҳ������

	    if (totaljh!=0)
	    {	    
			JtjhBean stnew = new JtjhBean();
            stnew.setJhbh("�ƻ������=");
	        stnew.setSbmc((totaljh-noontimejh)+"/"+totaljh+"="+df2.format(100*(totaljh-noontimejh)/totaljh)+"%");
            result.add(stnew);
	    }
	    JSONArray jsonArr= new JSONArray(result);
	    String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��JtjhAction--dataList"+jsonStr);
        response.setCharacterEncoding("UTF-8");
	    response.getWriter().print(jsonStr);
    }


    @RequestMapping("/dataListLinkNoPage")   //����ҳ�ģ��������
	public void  dataListLinkNoPage(JtjhModel model,HttpServletResponse response) throws Exception{

		//List<JtjhBean> dataList = jtjhService.queryByListNoPage(model);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		List<JtjhBean> result = new ArrayList<JtjhBean>();	
	    int totaljh=0;
		int noontimejh=0;
		DecimalFormat  df2   = new DecimalFormat("######0.00");

		    for (Object ele : dataList)
		    {
	            JtjhBean st = (JtjhBean)ele;

				Double jhsl=st.getJhsl_o()==null?0:st.getJhsl_o();
			    DecimalFormat  df0   = new DecimalFormat("######0");
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                Integer qbwg=0;
		        String sdate=sdf.format(new Date());
		        Date ud= sdf.parse(sdate);
		        java.sql.Date sd = new java.sql.Date(ud.getTime());   
		        long cqts=-9999;
				Integer  jhid=st.getId();
			    List<JtjhWghbBean> wghbList = jtjhWghbService.queryListByJhId(jhid);


			    if (wghbList!=null && wghbList.size()>0 )
			    {

                     if ((wghbList.get(0).getWgrq())!=null)
                     {
		                  st.setMaxWgrq(wghbList.get(0).getWgrq());
	     	         }

				     Double sumwgsl=0.0;
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
				     else{
				         if (jhsl!=0)
				         {
					         if ((jhsl-sumwgsl)/jhsl<=0.1)  //С��10%��Ϊ���
					         {
						         qbwg=1;
					         }
				         }
				     }	
				     if (st.getJhrq()!=null && !"".equals(st.getJhrq()))
				     {
		
				         if (qbwg==1)
				         {
                              if (st.getMaxWgrq()==null || "".equals(st.getMaxWgrq()))
                              {
                                  cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
                              }
							  else{
							      cqts=(st.getMaxWgrq().getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
                              }

						 }
				         else{
					         if ((sd.getTime()-st.getJhrq().getTime())<=0)
						     {
							     cqts=-9999;
								 //cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
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
				     st.setSumWgslds("��"+sumwgslds.toString()+"��");
				     if (qbwg==0 && st.getState()==1)
				     {
					    st.setWwgsl(jhsl-sumwgsl);
				     }
				     st.setQbWg(qbwg);
				     if (st.getState()==1)
				     { 
					    st.setCqts(cqts);
				     }

					// st.setWgflag(qbwg==1?"���깤":"δ�깤");
					// jtjhService.update(st);

			    }
			    else{
				     if (st.getJhrq()!=null && !"".equals(st.getJhrq())){
				         if ((sd.getTime()-st.getJhrq().getTime())<=0)
				         {
					          cqts=-9999;
							  //cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
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
						st.setWwgsl(jhsl);
				     }
					 
			    } 
			    if (st.getState()==1)
			    {
				    totaljh++;
			    }
			    if (cqts > 0  && st.getState()==1)
			    {
  			        noontimejh++;
			    }				
			    result.add(st);
		}
		if (totaljh!=0)
		{
		    JtjhBean st = new JtjhBean();
            st.setJhbh("�ƻ������=");
		    st.setSbmc((totaljh-noontimejh)+"/"+totaljh+"="+df2.format(100*(totaljh-noontimejh)/totaljh)+"%");
            result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��XsddAction--dataList"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	}


	@RequestMapping("/dataListForDdId")   //�������۶���ID����ѯ��̨�ƻ���ϸ��ʾDataGrid 
	public void  dataListForDdId(Integer id,HttpServletResponse response) throws Exception{

		JSONObject context = new JSONObject();	
		List<JtjhBean> dataList = jtjhService.queryListByDdId(id);
		List<JtjhBean> result = new ArrayList<JtjhBean>();

		for (Object ele : dataList)
		{
			JtjhBean st = (JtjhBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
        context.put("jtjh",jsonStr);
		context.put(SUCCESS, true);	
	    HtmlUtil.writerJson(response, context);
        //response.setCharacterEncoding("UTF-8");
		//response.getWriter().print(jsonStr);
	
		return ;
	}

   //���ݻ�̨�ƻ�ID����ѯ�깤�㱨����ʾDataGrid 
	@RequestMapping("/dataListWghbForJhId")   //���ݻ�̨�ƻ�ID����ѯ�깤�㱨����ʾDataGrid 
	public void  dataListWghbForJhid(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<JtjhWghbBean> dataList = jtjhWghbService.queryListByJhId(id);
		List<JtjhWghbBean> result = new ArrayList<JtjhWghbBean>();

		for (Object ele : dataList)
		{
			JtjhWghbBean st = (JtjhWghbBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��JtjhAction--dataListWghbForDdid"+jsonStr);
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
		 String xsddid  = request.getParameter("ddid");
		 String jhbh  = request.getParameter("xsddjhbh");


//System.out.println("����ɽ��XsddAction--add:insert"+inserted);
//System.out.println("����ɽ��XsddAction--add:delete"+deleted);
//System.out.println("����ɽ��XsddAction--add:update"+updated);

         String username = SessionUtils.getUser(request).getNickName();
         Integer row=0;
		 Integer sortFlag=0;//�Ƿ������滻row
         if(deleted != null){  
             //��json�ַ���ת���ɶ���  
             sortFlag=1;
             List<JtjhBean> listDeleted = new ArrayList<JtjhBean>();	
             listDeleted = JSON.parseArray(deleted, JtjhBean.class);  
             //TODO ����Ϳ��Ը���ת����Ķ��������Ӧ�Ĳ����� 
			 Integer [] ids=new Integer[listDeleted.size()];

             row=0;
		     for (Object ele : listDeleted )
		     {
				 JtjhBean bean = (JtjhBean)ele;	  

                  Integer ddid=bean.getId();
                  ids[row]=ddid;
				  row++;
			 } 
			 jtjhService.delete(ids);
         }  
 
         if(inserted != null){  
			 sortFlag=1;
             //��json�ַ���ת���ɶ���  
             List<JtjhBean> listInserted = new ArrayList<JtjhBean>();	
		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		     String sdate;
			 Date dxdrq=null;
	         sdate=sdf.format(new Date());	
			 try {
 			        
       		     dxdrq = sdf.parse(sdate);
       
              } catch (ParseException e) {
                 e.printStackTrace();
              }	
			 listInserted = JSON.parseArray(inserted, JtjhBean.class);  
			 for (Object ele : listInserted )
		     {
			     JtjhBean bean = (JtjhBean)ele;	 
				 bean.setId(null);
				 bean.setDdid(Integer.valueOf(xsddid));
				 bean.setJhbh(jhbh);
		      	 bean.setDeleted(DELETED.NO.key);
				 bean.setGxxh_o(bean.getGxxh());
				 bean.setGxgg_o(bean.getGxgg());
				 bean.setGxdy_o(bean.getGxdy());
				 Double dsl=0.0;
				 if ((bean.getJhsl_xs()).indexOf("+")>-1 ) //[195+3]
				 {
                    String[] strsl = bean.getJhsl_xs().split("\\+"); //[195,3]
					dsl= Double.valueOf(strsl[0])+ Double.valueOf(strsl[1]);
				 }
				 else if ((bean.getJhsl_xs()).indexOf("-")>-1 ) //[195-3])
                 {
					 String[] strsl = bean.getJhsl_xs().split("\\-"); //[195,3]
					 dsl= Double.valueOf(strsl[0])- Double.valueOf(strsl[1]);
				 }
				 else{
                     dsl =Double.valueOf(bean.getJhsl_xs());
				 }

				 bean.setJhsl(dsl);
				 if (bean.getJhsl_o()==null || "".equals(bean.getJhsl_o()))
				 {
					 bean.setJhsl_o(dsl);
				 }
				 
				 bean.setState(1);
                 bean.setXdrq(new java.sql.Date(dxdrq.getTime()));  //java.util.Date -->java.sql.Date 			    
			     bean.setCreateBy(username);
				 jtjhService.add(bean);
				

		     }
         }  

         if(updated != null){  
            //��json�ַ���ת���ɶ���  
			sortFlag=1;
		    List<JtjhBean> listUpdated = new ArrayList<JtjhBean>();	
            listUpdated = JSON.parseArray(updated, JtjhBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 JtjhBean bean = (JtjhBean)ele;	
                 bean.setUpdateBy(username);				 
 				 jtjhService.update(bean);
		     }
          }
		  //�滻����
		  List<JtjhBean> dataListNew = jtjhService.queryListByJhbh(jhbh);
		  for (Object ele : dataListNew )
		  {
			  JtjhBean st = (JtjhBean)ele;
			  SbBean sbbean =sbService.queryBySbmc(st.getSbmc());
	          String jtmc="";
	          if (sbbean!=null )
	          {
	              jtmc=sbbean.getJt()==null?"":sbbean.getJt();
                  st.setSbmcdek(sbbean.getDeksbmc()); 
	          }
			  else{
			     sendFailureMessage(response, "����(�����豸��Ϣά��)�У������豸��"+st.getSbmc()+"������Ϣ!");
			     return;
			  }
	          String gdmc="";
	          JtBean jtbean = jtService.queryByJtmc(jtmc);
	          if (jtbean!=null)
	          {
	              gdmc=jtbean.getGd()==null?"":jtbean.getGd();
	          }
			  else{
			     sendFailureMessage(response, "����(��̨ά��)�У������豸��"+jtmc+"���빤�Ρ�����Ĺ�����Ϣ��");
			     return;
			  }
	          st.setGd(gdmc);  
		      jtjhService.update(st);
		   }

        XsddBean ddbean =xsddService.queryById(Integer.valueOf(xsddid));
        if(ddbean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}

        List<JtjhBean> dataList = jtjhService.queryListByJhbh(jhbh);
		if (dataList==null || dataList.size()<1)
		{
			  ddbean.setXdjt(0);
		} 
		else{
             ddbean.setXdjt(1);
  		     row=1;
	         for (Object ele : dataList )
		     {
			     JtjhBean bean = (JtjhBean)ele;	    
                 bean.setRow(row);
				 jtjhService.update(bean);
				 row++;
			 }
		}
        xsddService.update(ddbean);
	    sendSuccessMessage(response, "����ɹ�~");
		return ;
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		
		//Map<String,Object>  context = new HashMap<String,Object> ();
		XsddBean bean  = xsddService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
	    if(bean.getState()  != 1 || bean.getJhrq()==null){
			sendFailureMessage(response, "�ö�������ͣ�����ϡ�������δȷ����������,���ֽܷ��´��̨!");
			return;
		}
	
        bean.setXdjt(1);
		xsddService.update(bean);
        String username = SessionUtils.getUser(request).getNickName();
		Integer ddid=bean.getId();
		
		String xh=(bean.getXh()).toUpperCase().trim();
		String oldxh=xh;
		String gg=bean.getGg().replace("X","*").replace("x","*").replace("��","*");
		String oldgg=gg.trim().replace("*","��");
		String dy=bean.getDy().toUpperCase().trim();
		String gy=bean.getGy();
		String dw=bean.getDw();
		String jsyq=bean.getJsyq();
		String ph=bean.getPh();
		Double sl=bean.getSl();
		String jhbh=bean.getJhbh()+"-"+(bean.getRow()<10?"0"+bean.getRow():bean.getRow());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        String sdate;
	    Date ddate=null;
	    sdate=df.format(new Date());	
		try {
 			        
            ddate = df.parse(sdate);
       
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date xdrq= bean.getXdrq()!=null?df.parse(df.format(bean.getXdrq())):ddate;		
		Date jhrq=null;
		if (bean.getJhrq()!=null)
		{
			String sjhrq=df.format(bean.getJhrq());
		    try {
                jhrq = df.parse(sjhrq);
            } catch (ParseException e) {
                e.printStackTrace();
            }	
		}

        Map<String,Object>  data = new HashMap<String,Object> ();
        data.put("xsddjhbh", bean.getJhbh());
		data.put("xsddxdrq", bean.getXdrq()!=null?(df.format(bean.getXdrq())):"");
		data.put("xsddrow",bean.getRow());	
		data.put("xsddjhrq",bean.getJhrq()!=null?(df.format(bean.getJhrq())):"");
		data.put("xsddsl",bean.getSl());
		data.put("id",bean.getId());	
		context.put("data", data);
		List<JtjhBean> dataList = jtjhService.queryListByDdId(ddid);
//System.out.println("����ɽ-ddid"+ddid);
//System.out.println("����ɽ-ddid"+dataList);
		if (dataList!=null && dataList.size()>0  )
		{
		    List<JtjhBean> result = new ArrayList<JtjhBean>();

		    for (Object ele : dataList)
		    {
			    JtjhBean st = (JtjhBean)ele;
	
			    result.add(st);
		    }	
		    JSONArray jsonArr= new JSONArray(result);
		    String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-XsddAction--dataListById-jsonStr"+jsonArr.toString());
		    context.put("json",jsonStr);		
		    context.put(SUCCESS, true);	
		    HtmlUtil.writerJson(response, context);
			return ;

		}
		else{
		   Integer jhqtsfs=2;//���������㷽ʽ
	       List<ParaBean> paraBeanDataList;
	       List<JtjhBean> result = new ArrayList<JtjhBean>();

		   paraBeanDataList = paraService.queryByFlag("ȥǰ׺1");  //#, YFD-,NAIHOU
		   for (Object paraele : paraBeanDataList )
		   {
			   ParaBean  parabean = (ParaBean) paraele;
			   if (xh.startsWith(parabean.getPara1()))
			   {
				  //xh=xh.substring(xh.indexOf(parabean.getPara1())+1);
				  xh=StringUtils.remove(xh,parabean.getPara1());
				  break;

			   }
		   }
		   paraBeanDataList = paraService.queryByFlag("ȥǰ׺2");  //ZR-,ZA-,ZB-,ZC-,ZRA,ZRB,ZRC
		   for (Object paraele : paraBeanDataList )
		   {
			   ParaBean  parabean = (ParaBean) paraele;
			   if (xh.startsWith(parabean.getPara1()))
			   {
				  //xh=xh.substring(xh.indexOf(parabean.getPara1())+1);
				  xh=StringUtils.remove(xh,parabean.getPara1());
				  break;

			   }
		   }
		   Integer noReplacesuff=0;
		   paraBeanDataList = paraService.queryByFlag("��ȥ��׺");  //NG-A etc. 
		   for (Object paraele : paraBeanDataList )
		   {
			   ParaBean  parabean = (ParaBean) paraele;
			   if (xh.equals(parabean.getPara1()))
			   {

				  noReplacesuff=1;
				  break;

			   }
		   }
		   if (noReplacesuff==0)
		   {
		   
		       paraBeanDataList = paraService.queryByFlag("ȥ��׺");  //-A,-B
		       for (Object paraele : paraBeanDataList )
		       {
			       ParaBean  parabean = (ParaBean) paraele;
			       if (xh.endsWith(parabean.getPara1()))
			       {
				      //xh=xh.substring(xh.indexOf(parabean.getPara1())+1);
				      xh=StringUtils.remove(xh,parabean.getPara1());
				      break;

			       }
		       }
		   }
		   paraBeanDataList = paraService.queryByFlag("�滻ǰ׺");  //WDZA-->WDZetc.
		   for (Object paraele : paraBeanDataList )
		   {
			   ParaBean  parabean = (ParaBean) paraele;
			   if (xh.startsWith(parabean.getPara1()))
			   {
				  xh=parabean.getPara2()+StringUtils.remove(xh,parabean.getPara1());
				  break;
			   }
		   }
		   paraBeanDataList = paraService.queryByFlag("�滻�ͺ�");  //BV��7о��-->BV(7о) etc. 
		   for (Object paraele : paraBeanDataList )
		   {
			   ParaBean  parabean = (ParaBean) paraele;
			   if (xh.equals(parabean.getPara1()))
			   {
				  //xh=xh.substring(xh.indexOf(parabean.getPara1())+1);
				  xh=parabean.getPara2();
				  break;

			   }
		   }
		   paraBeanDataList = paraService.queryByFlag("10KV�ܿս����ӵ�ѹ���");  
		   for (Object paraele : paraBeanDataList )
		   {
			   ParaBean  parabean = (ParaBean) paraele;
			   if (xh.equals(parabean.getPara1()) && dy.equals(parabean.getPara2()))
			   {
				  //xh=xh.substring(xh.indexOf(parabean.getPara1())+1);
				  xh=parabean.getPara3();
				  
				  break;

			   }
		   }
		   paraBeanDataList = paraService.queryByFlag("���������ӵ�ѹ");  
		   for (Object paraele : paraBeanDataList )
		   {
			   ParaBean  parabean = (ParaBean) paraele;
			   if ( dy.equals(parabean.getPara1()))
			   {
				  //xh=xh.substring(xh.indexOf(parabean.getPara1())+1);
				  xh=xh+"-"+parabean.getPara1();
				  break;

			   }
		   }
System.out.println("����ɽ-�滻���xh="+xh+",gg="+gg);
            DecimalFormat  df0   = new DecimalFormat("######0");
            int cpFindFlag=1;//��Ʒ���ݿ��ѯ���
			int thLxFlag=0;//�Ǳ������滻���
			String thLxGg="";//���滻�����ߵ�����
			CpBean cpbean;
			cpbean=cpService.queryByXhAndGg(xh,gg);
            if (cpbean==null)
            {

			    if (gg.indexOf("+")>-1) //3*70+2*25 
			    {
                    String[] strzl = gg.split("\\+"); //[3*70,2*25]
				    String[] strzx=strzl[0].split("\\*");//[3,70]
				    String strzdt=strzx[1];//70
				    String[] strlx=strzl[1].split("\\*");//[2,25]
				    String strlxs=strlx[0];//2
				    String strldt=strlx[1];//25
					String strthldt="";
		            paraBeanDataList = paraService.queryByFlag("�Ǳ��滻����");  
		            for (Object paraele : paraBeanDataList )
		            {
			            ParaBean  parabean = (ParaBean) paraele;
			            if (strzdt.equals(parabean.getPara1()))
			            {
				           thLxGg=strldt;//���滻�����ߵ�����
						   strthldt=parabean.getPara2();
						   
				           break;
			            }
		            }
					String strczgg=strzl[0]+"+"+strlxs+"*"+strthldt;

			        cpbean=cpService.queryByXhAndGg(xh,strczgg);
                    if (cpbean==null)
                    {
 				       // sendFailureMessage(response, "��Ʒ���ݿ�--����ƥ�䵽�ò�Ʒ!");
                        cpFindFlag=0;
					}
					else{
                        cpFindFlag=1;
						thLxFlag=1;//�Ǳ������滻���
					}
			    }
				else{
				    //sendFailureMessage(response, "��Ʒ���ݿ�--����ƥ�䵽�ò�Ʒ!");
                    cpFindFlag=0;
				//return ;
			    }
            }
			else{
                cpFindFlag=1;
			}
			if ( cpFindFlag==1 )
			{

				Integer cpid=cpbean.getId();
//System.out.println("����ɽ- cpid"+cpid);				 
				List<CpgxmxBean> dataListCpgxmx=cpgxmxService.queryListByCpId(cpid);
				if (dataListCpgxmx==null ||  dataListCpgxmx.size()<1 )
				{
			        //sendFailureMessage(response, "�������ݿ�--û���ҵ��ò�Ʒ�Ĺ�����Ϣ!");
					//return ;
				}
				else{

				   paraBeanDataList= paraService.queryByFlag("���������㷽ʽ");//���������㷽ʽ;1==�ӵ�������+�豸�����������㣻2==������깤ʱ��-1����ǰ����
                   for (Object paraele : paraBeanDataList )
			       {
				       ParaBean  parabean = (ParaBean) paraele;
				       jhqtsfs=Integer.valueOf(parabean.getPara1());
			       }				  
                   int row=1;
				   String tmpfgg="";
				   String tmpSbmc="";
				   String tmpIszl="";
				   Integer countDtts=0;
		           for (Object ele : dataListCpgxmx)
		           {
			           CpgxmxBean gxmxbean = (CpgxmxBean)ele;
                       int noDoFlag=0; //�ų����
					   paraBeanDataList = paraService.queryByFlag("���´��̨�ƻ�����");//��˿�ȹ���
					   for (Object paraele : paraBeanDataList )
					   {
						   ParaBean  parabean = (ParaBean) paraele;
						   if ((parabean.getPara1()).equals(gxmxbean.getSbmc()))
						   {
							   noDoFlag=1;
							   break;
						   }
					   }
					   paraBeanDataList = paraService.queryByFlag("���߹����´�ƻ�");//10-35���岻�´�ƻ�
					   for (Object paraele : paraBeanDataList )
					   {
						   ParaBean  parabean = (ParaBean) paraele;
						   if ((parabean.getPara1().indexOf(gxmxbean.getGxxh())>-1) && (parabean.getPara2().indexOf(gxmxbean.getGxgg()) >-1) 
							   && "����".equals(gxmxbean.getGxlb())  && "".equals(gxmxbean.getGxdy()) )
						   {
							   noDoFlag=1;
							   break;
						   }
					   }
					   if (noDoFlag==1 )  //�ų��Ĳ��´��̨�ƻ��Ĺ������磬��˿,���Ϊ10-50�����
					   {
						   continue;
					   }

		               JtjhBean jtjhbean =new JtjhBean(); 
					   jtjhbean.setXdrq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date 
					   jtjhbean.setDdid(ddid);
                       jtjhbean.setRow(row);
					   jtjhbean.setState(1);
					   jtjhbean.setDeleted(0);
					   jtjhbean.setJhbh(jhbh);
					   jtjhbean.setCreateBy(username);
					   String fsbmcdek=gxmxbean.getSbmc()==null?"":gxmxbean.getSbmc();//���ҹ�ʱ����
					   jtjhbean.setSbmcdek(fsbmcdek);	
					   jtjhbean.setIszl(gxmxbean.getIszl());
					   jtjhbean.setGxxh(oldxh);
					   String fxh=gxmxbean.getGxxh();//���ҹ�ʱ����
					   jtjhbean.setGxxh_o(fxh);//���ҹ�ʱ����
                       jtjhbean.setDtts(1);
					   String fgg=gxmxbean.getGxgg()==null?"":gxmxbean.getGxgg();
					   String jtgg=oldgg;
					   //��ԭ���滻������
					   if (thLxFlag==1)
					   {
						   if (fgg.indexOf("+")>-1)
						   {
							   fgg=oldgg;
						   }
						   else{
							   if (oldgg.indexOf(fgg)==-1)
							   {
								   fgg=thLxGg;
							   }
						   }
					   }
					   
					   if (!fgg.equals(oldgg) && oldgg.indexOf("+")!=-1 )
					   {
					      Integer endC=fgg.endsWith("C")?1:0;//200�ܽʻ� 6C ,�����ǳ�Ʒ

						  jtgg=oldgg+"("+("".equals(fgg.trim())?fxh:fgg)+")";
						  Double ifgg="".equals(fgg)?0:Double.valueOf(fgg.replace("N","").replace("C","")); //ȥ��N�ڲ�,C��Ʒ
						  Double itmpfgg="".equals(tmpfgg)?0:Double.valueOf(tmpfgg.replace("N","").replace("C",""));

						  if (ifgg<itmpfgg && itmpfgg!=0.0 && ifgg!=0.0 )
						  {
							  jtjhbean.setDtts(0);
							  jtjhbean.setIszl("��");
						  }
						  else{
							  if ("".equals(fgg.trim()))
							  {
								  jtjhbean.setIszl(fxh);
							  }
							  else{
								  if (endC==1) //200�ܽʻ� 6C ,��Ʒ
								  {
									  jtjhbean.setIszl(tmpIszl);  //��ǰһ�����������
								  }
								  else{
									  jtjhbean.setIszl("��");
								  }
							      
							  }
						  }
					   }
					   else{
						    if (fgg.endsWith("C") || fgg.endsWith("N"))
						    {
								jtgg=oldgg+"("+fgg+")";
						    }
					   }

                       if (tmpSbmc.equals(jtjhbean.getSbmcdek())){
						   jtjhbean.setDtts(0);
					   }					   
					   jtjhbean.setGxgg(jtgg);	
					   jtjhbean.setGxgg_o(fgg);
					   jtjhbean.setGxdy(dy);
					   String fdy=gxmxbean.getGxdy()==null?"":gxmxbean.getGxdy();//���ҹ�ʱ����
					   jtjhbean.setGxdy_o(fdy);
					   String flb=gxmxbean.getGxlb()==null?"":gxmxbean.getGxlb();//���ҹ�ʱ����
					   jtjhbean.setGxlb(flb);
					   jtjhbean.setGxgy(gy);
					   jtjhbean.setGxdw(dw);
					   jtjhbean.setGxjsyq(jsyq);
					   jtjhbean.setGxph(ph);
					   //�����ƻ�����
					   Double fjhsl=gxmxbean.getGxxs()*sl;
					   jtjhbean.setJhsl(sl);
					   if ((sl-Math.ceil(sl))==0)
					   {
						   jtjhbean.setJhsl_xs(df0.format(sl)+"");
					   }
					   else{
						   jtjhbean.setJhsl_xs(sl+"");
					   }
					   jtjhbean.setJhsl_xs((sl-Math.ceil(sl))==0?(df0.format(sl)+""):(sl+""));
					   jtjhbean.setJhsl_o(fjhsl);
                       List<SbBean> sbbeanDataList = sbService.queryListByDeksbmc(gxmxbean.getSbmc());
				       if (sbbeanDataList==null || sbbeanDataList.size()<1 )
				       {
					       sendFailureMessage(response, "�豸���ݿ�--û���ҵ��ù����豸�������Ϣ!");
				           //return ;
				       }
				       else{
                          SbBean sbbean = sbbeanDataList.get(0);
						  jtjhbean.setSbmc(sbbean.getSbmc());

				          int jmFlag=0; //���׻�̨���
					      paraBeanDataList = paraService.queryByFlag("���׻�̨");//���׻�̨

                          for (Object paraele : paraBeanDataList )
			              {
				              ParaBean  parabean = (ParaBean) paraele;
                              if ((parabean.getPara1()).indexOf(jtjhbean.getSbmc())>-1)
					          {
						          jmFlag=1;
						          break;
					          }
			              }
//System.out.println("����ɽ- jmFlag"+jmFlag);
					     if (jmFlag==1)
					     {
					         Double jmxs=0.0;
					         Double jmsl=0.0;
						     paraBeanDataList = paraService.queryByFlag("���׹���");//���յ�ѹȷ��ϵ����������
					         for (Object paraele : paraBeanDataList )
					         {
						         ParaBean  parabean = (ParaBean) paraele;
//System.out.println("����ɽ- getGxdy"+jtjhbean.getGxdy());
						         if ((jtjhbean.getGxdy()).equals(parabean.getPara1())) 
						         {
						            jmxs=Double.valueOf(parabean.getPara2());
						   	        jmsl=Double.valueOf(parabean.getPara3());
							        break;
						         }
					          }
						      if (jmsl>0)
						      {
                                 
						          //������������
							      Double tmpsjjm=(sl-sl/jmxs)+jmsl;
                                  Double sjjm = Math.ceil(tmpsjjm); //����ȡ������
					              Double jtjhsl=gxmxbean.getGxxs()*(sl+sjjm);
					              jtjhbean.setJhsl(sl+sjjm);
							      String strjm="";
							      if (sjjm>0.0 )
							      {
								      if ((sl-Math.ceil(sl))==0)
								      {
										  strjm=df0.format(sl)+"+"+df0.format(sjjm);
								      }
									  else{
									      strjm=sl+"+"+df0.format(sjjm);
									  }
							      }
							      else
							      {
                                      if ((sl-Math.ceil(sl))==0)
								      {
                                          strjm=df0.format(sl)+"-"+df0.format(-1*sjjm);
									  }
									  else{
										  strjm=sl+"-"+df0.format(-1*sjjm);
									  }
							      }
//System.out.println("����ɽ- strjm"+strjm);
							      jtjhbean.setJhsl_xs(strjm);
					              jtjhbean.setJhsl_o(jtjhsl);
						      }
				          }
                          if (jhqtsfs==1)//1==�ӵ�������+�豸������������
                          {
                               String fsbmc=sbbean.getSbmc();
//System.out.println("����ɽ-sbmc="+fsbmcdek+",xh="+fxh+",gg="+fgg+",dy="+fdy+",lb="+flb+",sl="+fjhsl);
                               Double bpjhde=getJtgs(fsbmcdek,fxh,fgg,fdy,flb,fjhsl/1000);//�����ƻ�����

					           Double yyjhde=getYyfh(fsbmc);//���и���
//System.out.println("����ɽ-bpjhde"+bpjhde);
//System.out.println("����ɽ-yyjhde"+yyjhde);
						       sbbean.setBpjhfh(bpjhde);
						       sbbean.setYyfh(yyjhde);

						       sbService.update(sbbean);
						       Double yyfh=0.0;
						       if ( sbbean.getYyfh()!=null )
						       {
							       yyfh=sbbean.getYyfh();
						       }
						       Double bpjhfh=0.0;
						       if ( sbbean.getBpjhfh()!=null )
						       {
							       bpjhfh=sbbean.getBpjhfh();
						       }
                               Double tzxs=0.0;
						       if ( sbbean.getTzxs()!=null )
						       {
							       tzxs=sbbean.getTzxs();
						       }
						       Double sjfh=(yyfh+bpjhfh)*tzxs;
						       Double sjfhts=sjfh/((sbbean.getSbsl())*(sbbean.getBc())*(sbbean.getCqgs()));
						       Double tsts=sjfhts+row*1;  //ʵ�ʸ�������+ÿһ����������1��
						       Integer itsts  =  (new Double(tsts)).intValue(); 
		                       Calendar rightNow = Calendar.getInstance();
                               rightNow.setTime(new Date());
                               // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
                               // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
                               rightNow.add(Calendar.DAY_OF_YEAR,itsts);//���ڼ���
						       Date djhrq=rightNow.getTime();
					           jtjhbean.setJhrq(new java.sql.Date(djhrq.getTime()));  //java.util.Date -->java.sql.Date 

					       }
                       }
				
					   
                       jtjhService.add(jtjhbean);
					   countDtts=countDtts+jtjhbean.getDtts();
					   tmpfgg=fgg;
					   tmpSbmc=jtjhbean.getSbmcdek();
					   tmpIszl=jtjhbean.getIszl();
                       row++;
		           }	
				   List<JtjhBean> dataListNew = jtjhService.queryListByDdId(ddid);

				   if (jhqtsfs==2)//2==������깤ʱ��-1����ǰ����
				   {
                       Calendar rightNow = Calendar.getInstance();
					   rightNow.setTime(jhrq);
                       // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
                       // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
                       rightNow.add(Calendar.DAY_OF_YEAR,-countDtts-1);//��һ�����򽻻���
				       jhrq=rightNow.getTime();	

					  
					   if (xdrq.after(jhrq)){ //ʱ��ȽϺ��� a>b xdrq(2017.8.28) > jhrq(2017.8.27)
						   rightNow.setTime(xdrq);
						   rightNow.add(Calendar.DAY_OF_YEAR,-1); //�´�����-1�죬��Ϊ�����Ҫ+1��
						   xdrq=rightNow.getTime();	
						   jhrq=xdrq;
					   }
					   for (int i=0;i<dataListNew.size();i++)
				       {
			               JtjhBean st = (JtjhBean)dataListNew.get(i);
						   
					       rightNow = Calendar.getInstance();
                           rightNow.setTime(jhrq);
                           // righNow.add(Calendar.YEAR,-1);//���ڼ�1��
                           // rightNow.add(Calendar.MONTH,3);//���ڼ�3����
                           rightNow.add(Calendar.DAY_OF_YEAR,st.getDtts());//����ÿ�����������
					       Date djhrq=rightNow.getTime();
						   jhrq=rightNow.getTime();	
                   
					       st.setJhrq(new java.sql.Date(djhrq.getTime()));  //java.util.Date -->java.sql.Date 
						   jtjhService.update(st);
					   }
				   }
                   /**
                   String tmpSbmc="";
				   JtjhBean st1=null;
		           for (int i=0;i<dataListNew.size();i++)
		           {

						JtjhBean st = (JtjhBean)dataListNew.get(i);

			            if (tmpSbmc.equals(st.getSbmc()))
			            {
							if (st.getJhrq().after(st1.getJhrq())) //ʱ��ȽϺ���
							{
								st1.setJhrq(st.getJhrq());
								jtjhService.update(st1);
							}
							else{
								st.setJhrq(st1.getJhrq());
								jtjhService.update(st);
							}
			            }


						st1 = (JtjhBean)dataListNew.get(i);
						tmpSbmc=st1.getSbmc();
							
	
		           }	
				   */

				   for (Object ele : dataListNew )
				   {
					  JtjhBean st = (JtjhBean)ele;
					  SbBean sbbean =sbService.queryBySbmc(st.getSbmc());
		              String jtmc="";
		              if (sbbean!=null )
		              {
			              jtmc=sbbean.getJt()==null?"":sbbean.getJt();
						  st.setSbmcdek(sbbean.getDeksbmc()); 
		              }
			          else{
			            sendFailureMessage(response, "����(�����豸��Ϣά��)�У������豸��"+st.getSbmc()+"������Ϣ!");
			            return;
			          }
		              String gdmc="";
		              JtBean jtbean = jtService.queryByJtmc(jtmc);
		              if (jtbean!=null)
		              {
			              gdmc=jtbean.getGd()==null?"":jtbean.getGd();
		              }
			          else{
			              sendFailureMessage(response, "����(��̨ά��)�У������豸��"+jtmc+"���빤�Ρ�����Ĺ�����Ϣ��");
			              return;
			          }

			          st.setGd(gdmc);  
				      jtjhService.update(st);
					  result.add(st);
				   }

				}
			}

		    JSONArray jsonArr= new JSONArray(result);
		    String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
		    context.put("json",jsonStr);	
		    context.put(SUCCESS, true);
//System.out.println("����ɽ-XsddAction--dataListById-context"+context.toString());
		    HtmlUtil.writerJson(response, context);
			return ;
		  
		}
	}

    //�����豸���и���
	private Double getYyfh(String fsbmc){
		List<JtjhBean> dataList = jtjhService.queryListBySbmc(fsbmc);
		Double yyfh=0.0;
		for (Object ele : dataList)
		{
                JtjhBean st = (JtjhBean)ele;
				Double jhsl=st.getJhsl();
				Double sumwgsl=0.0;
				Integer qbwg=0;
			    Integer jhid=st.getId();
			    List<JtjhWghbBean> wghbList = jtjhWghbService.queryListByJhId(jhid);

			    if (wghbList!=null && wghbList.size()>0 )
			    {
		      	     for(int i=0;i<wghbList.size();i++){
			             JtjhWghbBean wghb = wghbList.get(i);
					     sumwgsl=sumwgsl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
					     if (wghb.getWg()!=null)
					     {
					
					         if (wghb.getWg()==1)
					         {
						         qbwg=1;
					         } 
					     }
		             }
				     if (sumwgsl>=jhsl )
				     {
					     qbwg=1;
				     }
			    }
				if (qbwg==0 )
				{
				    String fsbmcdek=st.getSbmcdek();
					String fxh=st.getGxxh_o();
					String fgg=st.getGxgg();
 				    String fdy=st.getGxdy_o();
				    String flb=st.getGxlb();
					Double fgsde=0.0;
					yyfh=yyfh+getJtgs(fsbmcdek,fxh,fgg,fdy,flb,(jhsl-sumwgsl)/1000);
				}
    		}
		return yyfh;
	}

    //�����̨��ʱ����
	private Double getJtgs(String fsbmc,String fxh,String fgg,String fdy,String flb,Double fsl){
		Double jtgs=0.0;
System.out.println("����ɽ-sbmc="+fsbmc+",xh="+fxh+",gg="+fgg+",dy="+fdy+",lb="+flb+",sl="+fsl);
		GsdeBean gsbean = gsdeService.queryListByDeksbmcEtc(fsbmc,fxh,fgg,fdy,flb);
		if (gsbean!=null)
		{
            List<ParaBean> paraBeanDataList;
			Double fthpgs=12.0;
			paraBeanDataList= paraService.queryByFlag("��׼�๤ʱ");//��׼�๤ʱ
            for (Object paraele : paraBeanDataList )
			{
				ParaBean  parabean = (ParaBean) paraele;
		
				fthpgs=Double.valueOf(parabean.getPara1());

			}
//System.out.println("����ɽ-fthpgs="+fthpgs);
			int doFlag=0; //��Ҫ��̯׼��ʱ��Ļ�̨
		    paraBeanDataList = paraService.queryByFlag("��̯��Ʒ����׼����ʱ��̨");//500�ܽʻ���
		    for (Object paraele : paraBeanDataList )
			{
				ParaBean  parabean = (ParaBean) paraele;
				if ((parabean.getPara1()).equals(fsbmc))
				{
					doFlag=1;
					break;
				}
			}
			Double despgs=0.0;

			if (doFlag==1)
			{
				despgs=gsbean.getZbgs()==null?0.0:gsbean.getZbgs()/60;
			}
			Double dezbgs=gsbean.getZbgs()==null?0.0:gsbean.getZbgs()/60;
			Double degdzbgs=gsbean.getGdzbgs()==null?0.0:gsbean.getGdzbgs()/60;
			Double dehpgs=gsbean.getHsxpgs()==null?0.0:gsbean.getHsxpgs()/60;
			Double deqygs=gsbean.getQygs()==null?0.0:gsbean.getQygs()/60;
			Double fxprl=gsbean.getFxprl()==null?0.0:gsbean.getFxprl();

			if (gsbean.getFthpgs()!=null &&  gsbean.getFthpgs()>0 )
			{
				fthpgs=gsbean.getFthpgs();
			}
//System.out.println("����ɽ-fthpgs2="+fthpgs);
			if (doFlag==1)
			{
				dezbgs=deqygs+dehpgs;
			}
			else{
				dezbgs=dezbgs+deqygs+dehpgs;
			}
			Double desdgs=gsbean.getSdgs()==null?0.0:gsbean.getSdgs();
			Double defzgs=gsbean.getFzgs()==null?0.0:gsbean.getFzgs()/60;
			desdgs=desdgs+defzgs;
			Double bzbgs=0.0;
//System.out.println("����ɽ-doFlag="+doFlag);
			if (doFlag==1)
			{
                 bzbgs=fthpgs-degdzbgs;
			}
			else{
                 bzbgs=fthpgs-dezbgs-degdzbgs;
			}
//System.out.println("����ɽ-bzbgs="+bzbgs);
			Double sjsdgs=fsl*desdgs;
			Double hpcs=sjsdgs/bzbgs;
			Double sjzbgs=dezbgs;
			Double sjspgs=0.0;
			if (doFlag==1)
			{
                 sjspgs=hpcs*despgs;
			}
			Double sjgdzbgs=hpcs*degdzbgs;
			jtgs=sjspgs+sjgdzbgs+sjzbgs+sjsdgs;
System.out.println("����ɽ-jtgs"+jtgs);
		}
		return jtgs;
	}

    //��̨�ƻ��깤�㱨
	@RequestMapping("/getWghb")
	public void getWghb(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		JtjhBean bean  = jtjhService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
		List<JtjhWghbBean> dataList = jtjhWghbService.queryListByJhId(id);   //��ѯjhid 
		List<JtjhWghbBean> result = new ArrayList<JtjhWghbBean>();	
		for (Object ele : dataList)
		{
			JtjhWghbBean elebean = (JtjhWghbBean)ele;
			result.add(elebean);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-JtjhAction--getWghb-jsonArr"+jsonStr);
		context.put("json",jsonStr);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ

		Map<String,Object>  data = new HashMap<String,Object> ();
	    data.put("jhid", bean.getId());
	    data.put("jhbh", bean.getJhbh());
	    data.put("row", bean.getRow());
		data.put("state",bean.getState());	
		data.put("jhrq",bean.getJhrq()!=null?(df.format(bean.getJhrq())):"");
	    data.put("gxxh", bean.getGxxh_o());
	    data.put("gxgg", bean.getGxgg_o());
		data.put("gxdy",bean.getGxdy());	
		data.put("gxgy",bean.getGxgy());	
	    data.put("jhsl", bean.getJhsl_o());
		data.put("gxjsyq",bean.getGxjsyq());	
		data.put("gxph",bean.getGxph());	

        context.put("data", data);
		context.put(SUCCESS, true);	
		
//System.out.println("����ɽ-JtjhAction---getWghb-context"+context.toString());
		HtmlUtil.writerJson(response, context);

	}

  //�����̨�깤�㱨
	@RequestMapping("/saveWghb")
	public void  saveWghb(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    
		request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 String jhid  = request.getParameter("jhid");

//System.out.println("����ɽ��XsddAction--savewghb:ddid="+jhid);
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
	     JtjhBean jhbean  = jtjhService.queryById(Integer.valueOf(jhid));
		 if(jhbean  == null){
			 sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			 return;
		 }
		 String gd=jhbean.getGd();
		 String jhbh=jhbean.getJhbh();
		 String sbmc=jhbean.getSbmc();
		 String sbmcdek=jhbean.getSbmcdek();
         String gxxh_o=jhbean.getGxxh_o();
		 String gxgg_o=jhbean.getGxgg_o();
		 String gxdy_o=jhbean.getGxdy_o();
		 String gxlb=jhbean.getGxlb();
         if(deleted != null){  
             //��json�ַ���ת���ɶ���  

             List<JtjhWghbBean> listDeleted = new ArrayList<JtjhWghbBean>();	
             listDeleted = JSON.parseArray(deleted, JtjhWghbBean.class);  
             //TODO ����Ϳ��Ը���ת����Ķ��������Ӧ�Ĳ����� 
			 Integer [] ids=new Integer[listDeleted.size()];

             row=0;
		     for (Object ele : listDeleted )
		     {
				 JtjhWghbBean bean = (JtjhWghbBean)ele;	  

                  Integer id1=bean.getId();
                  ids[row]=id1;
				  row++;
			 } 
			 jtjhWghbService.delete(ids);
         }  
 
         if(inserted != null){  
             //��json�ַ���ת���ɶ���  
             List<JtjhWghbBean> listInserted = new ArrayList<JtjhWghbBean>();	
	
			 listInserted = JSON.parseArray(inserted, JtjhWghbBean.class);  
	
			 for (Object ele : listInserted )
		     {
				 JtjhWghbBean bean = (JtjhWghbBean)ele;
				 bean.setBc(bean.getBc()==null?1:bean.getBc());
				 bean.setGd(gd);
				 bean.setSbmc(sbmc);
				 bean.setSbmcdek(sbmcdek);
				 bean.setJhbh(jhbh);
				 bean.setGxxh_o(gxxh_o);
				 bean.setGxgg_o(gxgg_o);
				 bean.setGxdy_o(gxdy_o);
				 bean.setGxlb(gxlb);
			     bean.setLrBy(username);
                 bean.setLrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
				 bean.setJhid(Integer.valueOf(jhid));
				 jtjhWghbService.add(bean);
		     }
         }  

         if(updated != null){  
            //��json�ַ���ת���ɶ���  
		    List<JtjhWghbBean> listUpdated = new ArrayList<JtjhWghbBean>();	
            listUpdated = JSON.parseArray(updated, JtjhWghbBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 
				 JtjhWghbBean bean = (JtjhWghbBean)ele;	
				 bean.setGd(gd);
				 bean.setBc(bean.getBc()==null?1:bean.getBc());
				 bean.setSbmc(sbmc);
				 bean.setSbmcdek(sbmcdek);
				 bean.setJhbh(jhbh);
				 bean.setGxxh_o(gxxh_o);
				 bean.setGxgg_o(gxgg_o);
				 bean.setGxdy_o(gxdy_o);
				 bean.setGxlb(gxlb);
                 bean.setLrBy(username);	
                 bean.setLrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
 				 jtjhWghbService.update(bean);
		     }
        }

        Integer qbwg=0;
	    Double jhsl=jhbean.getJhsl_o()==null?0:jhbean.getJhsl_o();

		List<JtjhWghbBean> wghbList = jtjhWghbService.queryListByJhId(Integer.valueOf(jhid));
        if (wghbList!=null && wghbList.size()>0 )
		{
 
		     Double sumwgsl=0.0;
      	     for(int i=0;i<wghbList.size();i++){
	             JtjhWghbBean wghb = wghbList.get(i);
			     sumwgsl=sumwgsl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
			     if (wghb.getWg()!=null)
			     {
				
			         if (wghb.getWg()==1)
			         {
				         qbwg=1;
			         } 
			     }
             }
	
	         if (sumwgsl>=jhsl )
		     {
			     qbwg=1;
		     }
		     else{
		         if (jhsl!=0)
		         {
			         if ((jhsl-sumwgsl)/jhsl<=0.1)  //С��10%��Ϊ���
			         {
				         qbwg=1;
			         }
		         }
		     }	

		    if (qbwg==1)
		    {
			    jhbean.setWgflag("���깤");
				jtjhService.update(jhbean);
		     }

	    }
	    sendSuccessMessage(response, "����ɹ�~");
		return ;
	}

   //�����̨�����깤�㱨
	@RequestMapping("/saveWghbpl")
	public void  saveWghbpl(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    
		request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
         //String deleted = request.getParameter("deleted");  
        // String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 //String jhid  = request.getParameter("jhid");

//System.out.println("����ɽ��XsddAction--savewghb:ddid="+jhid);
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
         //ֻ�����޸ĵ�����
         if(updated != null){  
            //��json�ַ���ת���ɶ���  
		    List<JtjhBean> listUpdated = new ArrayList<JtjhBean>();	
            listUpdated = JSON.parseArray(updated, JtjhBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 JtjhBean jhbean = (JtjhBean)ele;	
                 Integer qbwg=0;
	             Double jhsl=jhbean.getJhsl_o()==null?0:jhbean.getJhsl_o();
				 String gd=jhbean.getGd();
				 Integer jhid=jhbean.getId();
				 Integer bc=jhbean.getBc()==null?1:jhbean.getBc();
		         String jhbh=jhbean.getJhbh();
		         String sbmc=jhbean.getSbmc();
				 String sbmcdek=jhbean.getSbmcdek();
	             String gxxh_o=jhbean.getGxxh_o();
		         String gxgg_o=jhbean.getGxgg_o();
		         String gxdy_o=jhbean.getGxdy_o();
		         String gxlb=jhbean.getGxlb();

                 //List<JtjhWghbBean> wghbList = jtjhWghbService.queryListByJhId(jhid);
//System.out.println("����ɽ��XsddAction--savewghb:update"+jhbean.getWgrq()+jhbean.getWgsl());
			     if (jhbean.getWgrq()!=null && (jhbean.getWgsl()!=null || jhbean.getWg()!=null))
			     {
					 JtjhWghbBean wgbean=new JtjhWghbBean();
					 wgbean.setJhid(jhid);
					 wgbean.setBc(bc);
					 wgbean.setWgrq(jhbean.getWgrq());
					 wgbean.setWgsl(jhbean.getWgsl());
					 wgbean.setWgsm(jhbean.getWgsm());
					 wgbean.setWg(jhbean.getWg());
					 wgbean.setGd(gd);
                     wgbean.setSbmc(sbmc);
					 wgbean.setSbmcdek(sbmcdek);
				     wgbean.setJhbh(jhbh);
				     wgbean.setGxxh_o(gxxh_o);
				     wgbean.setGxgg_o(gxgg_o);
				     wgbean.setGxdy_o(gxdy_o);
				     wgbean.setGxlb(gxlb);
                     wgbean.setLrBy(username);	
                     wgbean.setLrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
 				     jtjhWghbService.add(wgbean);

					 List<JtjhWghbBean> wghbList = jtjhWghbService.queryListByJhId(jhid);
                     if (wghbList!=null && wghbList.size()>0 )
		             {
 
		                  Double sumwgsl=0.0;
      	                  for(int i=0;i<wghbList.size();i++){
	                          JtjhWghbBean wghb = wghbList.get(i);
			                  sumwgsl=sumwgsl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
			                  if (wghb.getWg()!=null)
			                  {
				
			                      if (wghb.getWg()==1)
			                      {
				                      qbwg=1;
			                      } 
			                  }
                          }
	
	                      if (sumwgsl>=jhsl )
		                  {
			                  qbwg=1;
		                  }
		                  else{
		                     if (jhsl!=0)
		                     {
			                     if ((jhsl-sumwgsl)/jhsl<=0.1)  //С��10%��Ϊ���
			                     {
				                     qbwg=1;
			                     }
		                     }
		                  }	
 
			              if (qbwg==1)
			              {
							  jhbean.setWgflag("���깤");
							  jtjhService.update(jhbean);
			              }
			          }
				 }
		     }
 	         sendSuccessMessage(response, "����ɹ�~");
		     return ; 
		}

	}
  //���ظ����ͺ�
	@RequestMapping("/getUniXh")
	public void getUniXh(HttpServletResponse response) throws Exception{
	
		List<JtjhBean> dataList = jtjhService.queryByAll();
	
	    List<String> xh1 = new ArrayList();
		for (Object ele : dataList)
		{
			JtjhBean st = (JtjhBean)ele;
			xh1.add(st.getGxxh());
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
	
		List<JtjhBean> dataList = jtjhService.queryByAll();

		List<String> gg1 = new ArrayList();

		for (Object ele : dataList)
		{
			JtjhBean st = (JtjhBean)ele;
			gg1.add(st.getGxgg());
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

	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		jtjhService.delete(id);
		sendSuccessMessage(response, "ɾ���ɹ�");
	}

    //����Excel
	@RequestMapping("/exportExcel")
	public void  exportExcel(HttpServletRequest request,HttpServletResponse response,HttpSession session)  
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
		     List<JtjhBean> listSelected = new ArrayList<JtjhBean>();	
             listSelected = JSON.parseArray(selected, JtjhBean.class);  
             String [] sortNameArr = {"jhbh","row"};
			 ListUtils.sort(listSelected, true, "jhbh","row"); 

             String[] titles = new String[]{"�´�����", "��̨", "�ƻ���", "�ͺ�", "���", "��ѹ�ȼ�", "��λ"
			 , "����", "����", "�������", "����Ҫ��", "����", "����",};
             String[] fieldNames = new String[]{"xdrq", "sbmc", "jhbh", "gxxh", "gxgg", "gxdy", "gxdw",
                "jhsl_xs", "gxlb", "jhrq", "gxjsyq", "gxph", "gxgy"};
             try {
				String excelname="��̨�ƻ�������ӡ_("+sdate+")";
				//String  serverFilePath=session.getServletContext().getRealPath("/uploadfiles/")+excelname+".xls";
				//serverFilePath=request.getRealPath("/")+ "\\uploadfiles\\" +excelname+".xls"; //getServletContext().getRealPath("/")
//System.out.println("����ɽold��"+request.getRealPath("/")+ "\\uploadfiles\\" +excelname+".xls");
				String path = session.getServletContext().getRealPath("/uploadfiles/");
//System.out.println("����ɽnew��"+path+File.separator+excelname+".xls");
                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                //eh1.writeExcel(JtjhBean.class, listSelected);
                eh1.writeExcel(JtjhBean.class, listSelected, fieldNames, titles);
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
			
		     return;
         }
		 sendFailureMessage(response, "����ʧ�ܣ������ԣ�");
	}

   //����Excel
	@RequestMapping("/exportExcelGd")
	public void  exportExcelGd(HttpServletRequest request,HttpServletResponse response,HttpSession session)  
		throws Exception{
	   	JSONObject context = new JSONObject();
        //��ȡ�༭���� �����ȡ������json�ַ���  

         String selected = request.getParameter("selected"); 
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
	     String sdate=sdf.format(new Date());	
		 if ( selected!=null )
		 {
		     List<JtjhBean> listSelected = new ArrayList<JtjhBean>();	
             listSelected = JSON.parseArray(selected, JtjhBean.class);  
             String [] sortNameArr = {"sbmc","jhrq"};
			 ListUtils.sort(listSelected, true, "sbmc","jhrq"); 

             String[] titles = new String[]{"״̬","�´�����", "��̨", "�ƻ���", "�ͺ�", "���", "��ѹ�ȼ�", "��λ"
			 , "��Ʒ����", "��������", "����", "�ƻ�����", "����Ҫ��", "����", "����", "����깤����", "�깤�����ϼ�", "δ�����ϼ�"
			 , "�깤����", "ȫ���깤", "��������"};
             String[] fieldNames = new String[]{"state","xdrq", "sbmc", "jhbh", "gxxh", "gxgg", "gxdy", "gxdw",
                "jhsl_xs", "jhsl_o", "gxlb", "jhrq", "gxjsyq", "gxph", "gxgy", "maxWgrq", "sumWgsl", "wwgsl", "sumWgslds","qbWg", "cqts"};

             try {
				String excelname="���λ�̨�ƻ�����_("+sdate+")";

				String path = session.getServletContext().getRealPath("/uploadfiles/");

                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                //eh1.writeExcel(JtjhBean.class, listSelected);
                eh1.writeExcel(JtjhBean.class, listSelected, fieldNames, titles);
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

