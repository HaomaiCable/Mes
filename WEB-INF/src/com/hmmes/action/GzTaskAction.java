

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


import com.hmmes.bean.GzTaskBean;
import com.hmmes.bean.SysUser;
import com.hmmes.bean.JsonBean;
import com.hmmes.bean.TaskRole;


import com.hmmes.model.GzTaskModel;


import com.hmmes.service.GzTaskService;
import com.hmmes.service.SysUserService;
import com.hmmes.service.TaskRoleService;


import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.SessionUtils;
import com.hmmes.utils.DateUtil;
import com.hmmes.utils.StringUtil;
import com.hmmes.utils.excelutils.ExcelHelper;
import com.hmmes.utils.excelutils.JxlExcelHelper;
import com.hmmes.utils.ListUtils;
 
@Controller
@RequestMapping("/gzTaskManage") 
public class GzTaskAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(GzTaskAction.class);
	
	// Servrice start
	// Servrice start
	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private GzTaskService<GzTaskBean> gzTaskService; 
	@Autowired
	private SysUserService<SysUser> sysUserService;
    @Autowired	
	private TaskRoleService<TaskRole> taskRoleService; 

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/gzTask")
	public ModelAndView  list(GzTaskModel model,HttpServletRequest request) throws Exception{

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
		List<GzTaskBean> dataList = gzTaskService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gzTaskManage",context); 

	}
	

    /**
	 * ��̨�ƻ����
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/gzTaskBrow")
	public ModelAndView  gzTaskBrow(GzTaskModel model,HttpServletRequest request) throws Exception{
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
		//model.setDeleted(DELETED.NO.key);
		List<GzTaskBean> dataList = gzTaskService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("business/gzTaskBrow",context); 
	}	


	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList")  //��ʾȫ����̨�ƻ��б�
	public void  dataList(GzTaskModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String username = SessionUtils.getUser(request).getNickName();
		model.setCreateBy(username);
		List<GzTaskBean> dataList = gzTaskService.queryByList(model);
		List<GzTaskBean> result = new ArrayList<GzTaskBean>();
		// ��װVO����
		for (Object ele : dataList)
		{
			GzTaskBean st = (GzTaskBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);
	}

	@RequestMapping("/dataListForBrow")  
	public void  dataListForBrow(GzTaskModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String username = SessionUtils.getUser(request).getNickName();
		List<GzTaskBean> dataList = gzTaskService.queryByListNoPage(model);
		List<GzTaskBean> result = new ArrayList<GzTaskBean>();
        SysUser userbean = sysUserService.queryByName(username);
	    TaskRole taskRoleBean = taskRoleService.queryByUserId(userbean.getId());
		String roleStrs="";
		if (taskRoleBean!=null )
		{
			roleStrs= getRoleStrs(taskRoleBean.getId());
		}

			
		roleStrs=roleStrs+","+username;

		for (Object ele : dataList)
		{
			GzTaskBean st = (GzTaskBean)ele;
			if ((roleStrs.indexOf(st.getCreateBy()))!=-1)
			{
				result.add(st);
			}
		}

		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+dataList.size()+",\"rows\":"+jsonArr.toString()+"}";
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);
	}
	/**
	 * ��ӻ��޸�����
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(Integer id,String rq,String tcr,String ly,String cyry,String rwName,String rwContent,String rwResult,
		String wcrq_yq,String wcrq,String wcResult,
		HttpServletRequest request,HttpServletResponse response) throws Exception{

		//Map<String,Object>  context = new HashMap<String,Object>();
         //��ȡ�༭����  
		/**String rq  = request.getParameter("rq");
		String tcr  = request.getParameter("tcr");
		String ly  = request.getParameter("ly");
		String cyry  = request.getParameter("cyry");
		String rwName  = request.getParameter("rwName");
		String rwContent  = request.getParameter("rwContent");
		String rwResult  = request.getParameter("rwResult");
		String wcrq_yq  = request.getParameter("wcrq_yq");
		String wcrq  = request.getParameter("wcrq");
        String wcResult  = request.getParameter("wcResult");*/
		GzTaskBean bean =new GzTaskBean();
		GzTaskBean taskbean  = gzTaskService.queryById(id);
        
		if (taskbean!=null)
		{
			bean=taskbean;
		}


		String username = SessionUtils.getUser(request).getNickName();
        bean.setCreateBy(username);
		bean.setTcr(tcr);
		bean.setLy(ly);
		bean.setCyry(cyry);
		bean.setRwName(rwName);
		bean.setRwContent(rwContent);
		bean.setRwResult(rwResult);
        bean.setWcResult(wcResult);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        String sdate;
		Date ddate=null;
	    if (StringUtils.isNotBlank(rq) )
		{
			sdate=rq;
		    	
		}
		else 
		{  
			sdate=sdf.format(new Date());
			 
		}
		try {
 			        
       		 ddate = sdf.parse(sdate);
       
        } catch (ParseException e) {
             e.printStackTrace();
        }
        bean.setRq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date 

		ddate=null;
		if (StringUtils.isNotBlank(wcrq_yq) )
	    //if (bean.getWcrq_yq()==null )
		{
			 sdate=wcrq_yq;
		     	

		}
		else 
		{ 
			sdate=sdf.format(new Date());
			
		}
		try {
 			        
       		 ddate = sdf.parse(sdate);
       
        } catch (ParseException e) {
             e.printStackTrace();
        }
        bean.setWcrq_yq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date 
		ddate=null;
//System.out.println("����ɽ��wcrq"+wcrq);
		if (StringUtils.isNotBlank(wcrq) || !"".equals(wcrq.trim()))
	    //if (bean.getWcrq()==null &&  "".equals(wcrq.trim()))
		{
		     //sdate=sdf.format(new Date());	
			 sdate=wcrq;
			 

		}
		else 
		{ 
			sdate="";
		}
		try {
 			        
       		 if (!"".equals(sdate))
			 {
			 
			     ddate = sdf.parse(sdate);
			 }
       
        } catch (ParseException e) {
             e.printStackTrace();
        }
//System.out.println("����ɽ��sdate"+sdate);
        if (!"".equals(sdate.trim()))
        {
			bean.setWcrq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date 
		}
		else
		{
			bean.setWcrq(null);
		}
        if (bean.getWcrq()!=null)
        {
			bean.setReport(1);
		    SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdate=sdf1.format(new Date());

		    try {
    	        ddate = sdf1.parse(sdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
			bean.setReportTime(new java.sql.Timestamp(ddate.getTime()));
	
        }
		else{
			bean.setReport(0);
			bean.setReportTime(null);
		}
		if(bean.getId() == null){
			gzTaskService.add(bean);
		}else{
			gzTaskService.update(bean);
		}
		sendSuccessMessage(response, "����ɹ�~");
	}
	
	@RequestMapping("/saveConfirm")
	public void saveConfirm(Integer id,	HttpServletRequest request,HttpServletResponse response) throws Exception{
//System.out.println("����ɽ��id"+id);
		GzTaskBean taskbean  = gzTaskService.queryById(id);
        
		if (taskbean!=null)
		{
			if (taskbean.getReport()!=1)
			{
				 sendFailureMessage(response, "�ù���������δ���л㱨�����ܽ�������ȷ�ϣ�");
		         return;

			}
			if (taskbean.getAccept()==1)
			{
				 sendFailureMessage(response, "�ù�������("+taskbean.getAcceptBy()+")ȷ�����ۣ����ܽ�����ȷ�ϣ�");
		         return;

			}
			String username = SessionUtils.getUser(request).getNickName();
		    String lrr=taskbean.getCreateBy();	
		    if (username.equals(lrr))
		    {
		         sendFailureMessage(response, "�Լ������ƻ������Լ�����ȷ��!");
		         return;
		    }

			taskbean.setAccept(1);
            taskbean.setAcceptBy(username);
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sdate=sdf1.format(new Date());
			Date ddate=null;
		    try {
    	        ddate = sdf1.parse(sdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
			taskbean.setReportTime(new java.sql.Timestamp(ddate.getTime()));
			gzTaskService.update(taskbean);

		    sendSuccessMessage(response, "ȷ�ϳɹ�~");
		    return ;
		}
		else{
			sendFailureMessage(response, "ȷ��ʧ�ܣ�");
		    return;
		}


	}
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap();
		JSONObject context = new JSONObject();
		Map<String,Object>  data = new HashMap<String,Object> ();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		String sdate=df.format(new Date());
		GzTaskBean bean  = gzTaskService.queryById(id);
		if(bean  == null){
            data.put("rq", sdate);
		    data.put("tcr", "");
		    data.put("ly","");
			data.put("cyry","");
		    data.put("rwName","");
		    data.put("rwContent","");
		    data.put("rwResult","");
		    data.put("wcrq_yq","");
			data.put("wcrq","");
			data.put("cwResult","");
		}
		else{
            String username = SessionUtils.getUser(request).getNickName();
		    String lrr=bean.getCreateBy();	
		    if (!username.equals(lrr))
		    {
		         sendFailureMessage(response, "�ù�������Ϊ("+lrr+")�����������޸�!");
		         return;
		    }
	        if(bean.getAccept()  == 1  ){
		         String message="IDΪ("+id+")�Ĺ�������,�ѱ�("+bean.getAcceptBy()+")ȷ�ϣ��������޸ģ�";
                 sendFailureMessage(response, message);
			     return;  
		    } 
  

			data.put("id", bean.getId());
			data.put("rq", bean.getRq()!=null?(df.format(bean.getRq())):"");
		    data.put("tcr", bean.getTcr());
		    data.put("ly", bean.getLy());
			data.put("cyry",bean.getCyry());
		    data.put("rwName",bean.getRwName());
		    data.put("rwContent",bean.getRwContent());
		    data.put("rwResult",bean.getRwResult());
		    data.put("wcrq_yq",bean.getWcrq_yq()!=null?(df.format(bean.getWcrq_yq())):"");
			data.put("wcrq",bean.getWcrq()!=null?(df.format(bean.getWcrq())):"");
			data.put("wcResult",bean.getWcResult());
		}

        context.put("data", data);
		context.put(SUCCESS, true);	

		//context.put(SUCCESS, true);
		//context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(id != null && id.length > 0){
			for (int i=0;i<id.length;i++ )
			{
				  String message;

				  GzTaskBean bean  =  gzTaskService.queryById(id[i]);
	              String username = SessionUtils.getUser(request).getNickName();
		          String lrr=bean.getCreateBy();	
		          if (!username.equals(lrr))
		          {
		              sendFailureMessage(response, "�ù�������Ϊ("+lrr+")����������ɾ��!");
		              continue ;
				  }
				  if(bean.getReport()  == 1  ){
					  message="IDΪ("+id[i]+")�Ĺ�������,�ѱ��㱨������ɾ����";
                      sendFailureMessage(response, message);
					  continue ;
					  
		           } 
				  if(bean.getAccept()  == 1  ){
					  message="IDΪ("+id[i]+")�Ĺ�������,�ѱ�("+bean.getAcceptBy()+")ȷ�ϲ���ɾ����";
                      sendFailureMessage(response, message);
					  continue ;
					  
		           } 
				
				   else{
			           gzTaskService.delete(id[i]); 
					   message="IDΪ("+id[i]+")�Ĺ�������,ɾ���ɹ���";
   		               sendSuccessMessage(response,  message);	
	
				   }
			}

		}else{
			sendFailureMessage(response, "δѡ�м�¼");
			return; 
		}

	}
 //���ظ����ͺ�
	@RequestMapping("/getUniCdr")
	public void getUniCdr(HttpServletResponse response) throws Exception{
	
		List<GzTaskBean> dataList = gzTaskService.queryByAll();
	
	    List<String> createBy1 = new ArrayList();
		for (Object ele : dataList)
		{
			GzTaskBean st = (GzTaskBean)ele;
			createBy1.add(st.getCreateBy());
		}
		

		//ɾ���ظ���
		List<String> unicreateBy1=new ArrayList();
	
		unicreateBy1=removeDuplicate(createBy1);
		
		
		Collections.sort(unicreateBy1);
		List<JsonBean> unicreateBy = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unicreateBy1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unicreateBy1.get(i));
            id++;
			unicreateBy.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unicreateBy);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

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

	private String getRoleStrs(Integer roleId) throws Exception{
		List<TaskRole> users= taskRoleService.queryByGlzId(roleId);
		if(users == null || users.isEmpty()){
			return null;
		}
		StringBuffer str = new StringBuffer();
		int i=1;
		for(TaskRole user : users){
			str.append(user.getNickName());
			if(i < users.size()){
				str.append(",");
			}
			i++;
		}
//System.out.println("����ɽ-SysUser"+str.toString());
        return str.toString();

	}
    //����Excel
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
		     List<GzTaskBean> listSelected = new ArrayList<GzTaskBean>();	
             listSelected = JSON.parseArray(selected, GzTaskBean.class);  
             String [] sortNameArr = {"rq"};
			 ListUtils.sort(listSelected, true, "rq"); 

             String[] titles = new String[]{"�´�����", "״̬", "�������", "������Դ", "����е���", "�μ���Ա", "������������", "����������ϸ����"
			 , "����������", "Ҫ���������", "ʵ���������", "��ɽ���㱨", "�㱨ʱ��", "ȷ����", "ȷ��ʱ��"};
             String[] fieldNames = new String[]{"rq", "state", "tcr", "ly", "createBy", "cyry", "rwName",
                "rwContent", "rwResult", "wcrq_yq", "wcrq", "wcResult", "reportTime", "acceptBy", "acceptTime"};
             try {
				String excelname="�����ƻ�����_("+sdate+")";

				String path = session.getServletContext().getRealPath("/uploadfiles/");
                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                //eh1.writeExcel(GzTaskBean.class, listSelected);
                eh1.writeExcel(GzTaskBean.class, listSelected, fieldNames, titles);
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
			
		     return;
         }
		 sendFailureMessage(response, "����ʧ�ܣ������ԣ�");
	}

}

