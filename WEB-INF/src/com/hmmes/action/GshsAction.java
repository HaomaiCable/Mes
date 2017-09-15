

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

import com.hmmes.model.JtjhWghbModel;
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
@RequestMapping("/gshsManage") 
public class GshsAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(GshsAction.class);
	
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
	@RequestMapping("/gshs_bs")
	public ModelAndView  list_bs(JtjhModel model,HttpServletRequest request) throws Exception{

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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gshsManage_bs",context); 

	}
	@RequestMapping("/gshs_xl")
	public ModelAndView  list_xl(JtjhModel model,HttpServletRequest request) throws Exception{

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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gshsManage_xl",context); 

	}
	@RequestMapping("/gshs_dy")
	public ModelAndView  list_dy(JtjhModel model,HttpServletRequest request) throws Exception{

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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gshsManage_dy",context); 

	}
	@RequestMapping("/gshs_gy")
	public ModelAndView  list_gy(JtjhModel model,HttpServletRequest request) throws Exception{

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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gshsManage_gy",context); 

	}
	@RequestMapping("/gshsBrow")
	public ModelAndView  listBrow(JtjhModel model,HttpServletRequest request) throws Exception{

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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gshsManageBrow",context); 

	}
	@RequestMapping("/gshsBrow_bs")
	public ModelAndView  listBrow_bs(JtjhModel model,HttpServletRequest request) throws Exception{

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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gshsManageBrow_bs",context); 

	}
	@RequestMapping("/gshsBrow_xl")
	public ModelAndView  listBrow_xl(JtjhModel model,HttpServletRequest request) throws Exception{

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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gshsManageBrow_xl",context); 

	}
	@RequestMapping("/gshsBrow_dy")
	public ModelAndView  listBrow_dy(JtjhModel model,HttpServletRequest request) throws Exception{

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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gshsManageBrow_dy",context); 

	}
	@RequestMapping("/gshsBrow_gy")
	public ModelAndView  listBrow_gy(JtjhModel model,HttpServletRequest request) throws Exception{

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
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gshsManageBrow_gy",context); 

	}
	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList")  //��ʾȫ����̨�ƻ��б�
	public void  dataList(JtjhWghbModel model,HttpServletResponse response) throws Exception{
		List<JtjhWghbBean> dataList = jtjhWghbService.queryByList(model);
		List<JtjhWghbBean> result = new ArrayList<JtjhWghbBean>();
		// ��װVO����
		for (Object ele : dataList)
		{
			JtjhWghbBean st = (JtjhWghbBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);
	}


	@RequestMapping("/dataListLinkForGd")   //DataGrid url ��̨�㱨��Ϣ���ֹ��ε�
	public void  dataListLinkForGd(Integer gdid,JtjhWghbModel model,HttpServletResponse response) throws Exception{
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
		if (!"".equals(gd))
		{
			model.setGd(gd);//����//
		}
		
		model.setYfp(2); //δ����//
		List<JtjhWghbBean> dataList = jtjhWghbService.queryByListHs(model);
		//List<JtjhWghbBean> dataList =  jtjhWghbService.queryByList(model);//
  		List<JtjhWghbBean> result = new ArrayList<JtjhWghbBean>();	
		for (Object ele : dataList)
		{
			JtjhWghbBean hbBean = (JtjhWghbBean)ele;
            JtjhBean jhBean= jtjhService.queryById(hbBean.getJhid());
			if (jhBean==null)
			{
		        sendFailureMessage(response, "δ�ҵ���̨�ƻ���Ϣ!");
				//return ;
			}
			//hbBean.setJhbh(jhBean.getJhbh());//
			//hbBean.setSbmc(jhBean.getSbmc());//
           /**
	        SbBean sbbean =sbService.queryBySbmc(hbBean.getSbmc());
			//���²���һ��sbmcdek(������豸����)
	        String jtmc="";
	        if (sbbean!=null )
	        {
	            jtmc=sbbean.getJt()==null?"":sbbean.getJt();

	        }
			else{
			   sendFailureMessage(response, "����(�����豸��Ϣά��)�У������豸��"+hbBean.getSbmc()+"������Ϣ!");
			   //return;
			}
            hbBean.setSbmcdek(sbbean.getDeksbmc());             
			String gdmc="";
	        JtBean jtbean = jtService.queryByJtmc(jtmc);
	        if (jtbean!=null)
	        {
	            gdmc=jtbean.getGd()==null?"":jtbean.getGd();
	        }
			else{
			   sendFailureMessage(response, "����(��̨ά��)�У������豸��"+jtmc+"���빤�Ρ�����Ĺ�����Ϣ��");
			   //return;
			}
			*/
			//hbBean.setGd(gdmc);//
			//hbBean.setYhs(2);//
			//hbBean.setYfp(2);//
			hbBean.setIszl(jhBean.getIszl());
			hbBean.setGxxh(jhBean.getGxxh());
			//hbBean.setGxxh_o(jhBean.getGxxh_o());//
			hbBean.setGxgg(jhBean.getGxgg());
			//hbBean.setGxgg_o(jhBean.getGxgg_o());//
			hbBean.setGxdy(jhBean.getGxdy());
			//hbBean.setGxdy_o(jhBean.getGxdy_o());//
			hbBean.setGxgy(jhBean.getGxgy());
			hbBean.setGxdw(jhBean.getGxdw());
			hbBean.setGxjsyq(jhBean.getGxjsyq());
			hbBean.setGxph(jhBean.getGxph());
			//hbBean.setGxlb(jhBean.getGxlb());//
			hbBean.setJhsl(jhBean.getJhsl());
			hbBean.setJhsl_o(jhBean.getJhsl_o());
			hbBean.setJhsl_xs(jhBean.getJhsl_xs());
			//jtjhWghbService.update(hbBean);//
	
		    result.add(hbBean);
		}

    	//����ҳ������
	    JSONArray jsonArr= new JSONArray(result);
	    String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��JtjhAction--dataList"+jsonStr);
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
         //String deleted = request.getParameter("deleted");  
        // String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 //String jhid  = request.getParameter("jhid");

         //ֻ�����޸ĵ�����
         if(updated != null){  
            //��json�ַ���ת���ɶ���  
		    List<JtjhWghbBean> listUpdated = new ArrayList<JtjhWghbBean>();	
            listUpdated = JSON.parseArray(updated, JtjhWghbBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 JtjhWghbBean hbbean = (JtjhWghbBean)ele;	
			     jtjhWghbService.update(hbbean);
			 }
 	         sendSuccessMessage(response, "����ɹ�~");
		}		    
		return ; 

	}

   //��̨��ʱ����
	@RequestMapping("/saveHsgs")
	public void  saveHsgs(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    
		request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
         String selected = request.getParameter("selected");  

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
		 
         //ֻ�����޸ĵ�����
         if(selected != null){  
            //��json�ַ���ת���ɶ���  
		    List<JtjhWghbBean> listSelected = new ArrayList<JtjhWghbBean>();	
            listSelected = JSON.parseArray(selected, JtjhWghbBean.class);  
 	        for (Object ele : listSelected )
		    {
				 JtjhWghbBean wgbean = (JtjhWghbBean)ele;	
                 wgbean.setHsBy(username);	
                 wgbean.setHsTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
				 String fsbmcdek=wgbean.getSbmcdek()==null?"":wgbean.getSbmcdek();
				 String fxh=wgbean.getGxxh_o()==null?"":wgbean.getGxxh_o();
				 String fgg=wgbean.getGxgg_o()==null?"":wgbean.getGxgg_o();
				 String fdy=wgbean.getGxdy_o()==null?"":wgbean.getGxdy_o();
				 String flb=wgbean.getGxlb()==null?"":wgbean.getGxlb();
				 Double fwgsl=wgbean.getWgsl()==null?0.0:wgbean.getWgsl();

                 Double[] gsde=getJtgs(fsbmcdek,fxh,fgg,fdy,flb,fwgsl/1000);//���㹤ʱ
				 wgbean.setGs(gsde[0]);
				 wgbean.setGs(gsde[1]);
				 if (gsde[0]>0.0)
				 {
					 wgbean.setYhs(1);
				 }
				 
				 jtjhWghbService.update(wgbean);

		     }
			 
 	         sendSuccessMessage(response, "�������~");
		     return ; 
		}
		else{
			sendFailureMessage(response, "δ���й�ʱ�������!");
			return ;
		}

	}	
    //�����̨��ʱ����
	private Double[] getJtgs(String fsbmc,String fxh,String fgg,String fdy,String flb,Double fsl){
		Double[] jtgs={0.0,0.0};
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
			Double dgrs=gsbean.getDgrs()==null?0.0:gsbean.getDgrs();

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
			jtgs[1]=sjspgs+sjgdzbgs+sjzbgs+sjsdgs;
            jtgs[0]=(sjspgs+sjgdzbgs+sjzbgs+sjsdgs)*dgrs;
System.out.println("����ɽ-jtgs�����"+jtgs[1]+",jtgsԱ��"+jtgs[0]);
		}
		return jtgs;
	}
	


  //���ظ����ͺ�
	@RequestMapping("/getUniXh")
	public void getUniXh(HttpServletResponse response) throws Exception{
	
		List<JtjhWghbBean> dataList = jtjhWghbService.queryByAll();
	
	    List<String> xh1 = new ArrayList();
		for (Object ele : dataList)
		{
			JtjhWghbBean st = (JtjhWghbBean)ele;
			xh1.add(st.getGxxh_o());
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
	
		List<JtjhWghbBean> dataList = jtjhWghbService.queryByAll();

		List<String> gg1 = new ArrayList();

		for (Object ele : dataList)
		{
			JtjhWghbBean st = (JtjhWghbBean)ele;
			gg1.add(st.getGxgg_o());
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

