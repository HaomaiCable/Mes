
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
//import java.util.Date;

import net.sf.json.JSONObject;
import org.json.JSONArray;
//import net.sf.json.JsonConfig;

import com.alibaba.fastjson.JSON;  
//import com.alibaba.fastjson.JSONObject;  
import com.alibaba.fastjson.annotation.JSONField;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hmmes.bean.JhbhBean;
import com.hmmes.bean.SbBean;
import com.hmmes.bean.JtBean;

import com.hmmes.model.JtjhModel;


import com.hmmes.service.JtjhService;
import com.hmmes.service.JtjhWghbService;
import com.hmmes.service.ParaService;
import com.hmmes.service.XsddService;
import com.hmmes.service.CpService;
import com.hmmes.service.CpgxmxService;
import com.hmmes.service.SbService;
import com.hmmes.service.JtService;
import com.hmmes.service.JhbhService;
import com.hmmes.service.JtService;

import com.hmmes.bean.JsonBean;
import com.hmmes.service.SbService;

import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.SessionUtils;
import com.hmmes.utils.DateUtil;
import com.hmmes.utils.StringUtil;

 
@Controller
@RequestMapping("/bcpjhManage") 
public class BcpjhAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(JtjhAction.class);
	
	// Servrice start

	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private JtjhService<JtjhBean> jtjhService; 
	
	@Autowired
	private JtjhWghbService<JtjhWghbBean> jtjhWghbService;


    @Autowired	
	private ParaService<ParaBean> paraService;
 
    @Autowired	
	private CpService<CpBean> cpService;
    @Autowired	
	private CpgxmxService<CpgxmxBean> cpgxmxService;

    @Autowired	
	private JhbhService<JhbhBean> jhbhService;
	@Autowired	
	private SbService<SbBean> sbService;
    @Autowired	
	private JtService<JtBean> jtService;

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping("/bcpjh")
	public ModelAndView  list_bcpjh(JtjhModel model,HttpServletRequest request) throws Exception{

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
		return forword("business/bcpjhManage",context); 

	}



    @RequestMapping("/dataList")   //DataGrid url 
	public void  dataList(JtjhModel model,HttpServletResponse response) throws Exception{
		List<JtjhBean> dataList = jtjhService.queryByListBcp(model);
		List<JtjhBean> result = new ArrayList<JtjhBean>();	

		for (Object ele : dataList)
		{
			Integer flag=0;  //�Ƿ���ʾ���
			JtjhBean st = (JtjhBean)ele;
			//if ((st.getJhbh()).startsWith("SC"))
		    //{			
			Double jhsl=st.getJhsl()==null?0:st.getJhsl();
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
				 if (qbwg==0)
				 {
					 st.setWwgsl(jhsl-sumwgsl);
				 }
				 st.setQbWg(qbwg);

			}
			else{
                st.setWwgsl(jhsl);
			}
			result.add(st);
			//}

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
	@SuppressWarnings("unchecked")
	public void  save(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
        String deleted = request.getParameter("deleted");  
        String inserted = request.getParameter("inserted");  
        String updated = request.getParameter("updated");  
	    String xdrq  = request.getParameter("xdrq");
		String jhbh  = request.getParameter("jhbh");
		String jhrq  = request.getParameter("jhrq");

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
			 JhbhBean bean  = jhbhService.queryNoByYearAndMonth(iyear, imonth,3);  //3--���Ʒ�ƻ����
			 //@Param("uuid")String uuid, @Param("portletid")String portletid
  		     if(bean  == null){
				  JhbhBean newbean=new JhbhBean();
                  newbean.setNian(iyear);
				  newbean.setYue(imonth);
				  newbean.setFlag(3);
				  newbean.setJhno(1);
				  jhbhService.add(newbean);
				  String sjhno=StringUtil.fillZero("1", 4);
				  jhbh="SC"+syear+smonth+sjhno;
		    }
			else 
			{
				 Integer newno=bean.getJhno()+1;
				 bean.setJhno(newno);
				 jhbhService.update(bean);
                 String sjhno=StringUtil.fillZero(newno+"", 4);
				 jhbh="SC"+syear+smonth+sjhno;
			}
	     }
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
			 row=0;
             //��json�ַ���ת���ɶ���  
             List<JtjhBean> listInserted = new ArrayList<JtjhBean>();	      
			 listInserted = JSON.parseArray(inserted, JtjhBean.class);  
			 for (Object ele : listInserted )
		     {
			     JtjhBean bean = (JtjhBean)ele;	  
				 bean.setDdid(0);
		      	 bean.setDeleted(DELETED.NO.key);
				 bean.setState(1);
		         bean.setJhbh(jhbh);
				 bean.setRow(row);
                 //bean.setXdrq(new java.sql.Date(dxdrq.getTime()));  //java.util.Date -->java.sql.Date 			    
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
	     if (sortFlag==1)
		 {
  		     row=1;
		     List<JtjhBean> dataList = new ArrayList<JtjhBean>();	

		     dataList = jtjhService.queryListByJhbh(jhbh);
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
			 Date djhrq=null;
	         int flag=0; //�滻���,��ϸ�е��깤����Ϊ�գ��ñ�ͷ���깤�����滻
             if  ( jhrq!=null && jhrq.trim().length()>=1 )
             {
                 
   		         try {
     		          djhrq = sdf.parse(jhrq);
                 } catch (ParseException e) {
                      e.printStackTrace();
                 }
				 flag=1;
			 }
		
	
	         for (Object ele : dataList )
		     {
			     JtjhBean bean = (JtjhBean)ele;	    
                 bean.setXdrq(new java.sql.Date(dxdrq.getTime()));  //java.util.Date -->java.sql.Date 

		
				 if ((bean.getJhrq()==null) && (flag==1) )
				 { 
					 bean.setJhrq(new java.sql.Date(djhrq.getTime()));   
				 }
	             bean.setRow(row);
			     bean.setJhbh(jhbh);
				 bean.setGxxh_o(bean.getGxxh());
				 bean.setGxgg_o(bean.getGxgg());
				 bean.setGxdy_o(bean.getGxdy());
				 bean.setJhsl_o(bean.getJhsl());
				 bean.setJhsl_xs(bean.getJhsl()+"");

				 String jtmc="";
				 SbBean sbbean = sbService.queryBySbmc(bean.getSbmc());
	             
	             if (sbbean!=null )
	             {
	                 jtmc=sbbean.getJt()==null?"":sbbean.getJt();
                     bean.setSbmcdek(sbbean.getDeksbmc()); 
	             }
			     else{
			        sendFailureMessage(response, "����(�����豸��Ϣά��)�У������豸��"+bean.getSbmc()+"������Ϣ!");
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
	             bean.setGd(gdmc);  

				 jtjhService.update(bean);
				 row++;
		    } 
		}
        sendSuccessMessage(response, "����ɹ�~");
		return ;
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object> ();
		JtjhBean bean  = jtjhService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
	    if(bean.getState()  != 1 ){
			sendFailureMessage(response, "�ð��Ʒ�ƻ�����ͣ������,�����޸ġ���ʹ�á��ƻ���������ܽ��б��!");
			return;
		}
	
        List<JtjhWghbBean> wghbDataList=jtjhWghbService.queryListByJhId(bean.getId());
		if(wghbDataList !=null  &&  wghbDataList.size()>0 ){
			sendFailureMessage(response, "�ð��Ʒ�ƻ��Ѿ��������깤�㱨,�����޸�!");
			return;
		}
	    String username = SessionUtils.getUser(request).getNickName();
		String lrr=bean.getCreateBy();
		if (!username.equals(lrr)){
			sendFailureMessage(response, "�ð��Ʒ�ƻ�Ϊ("+bean.getCreateBy()+")�����������޸�!");
		    return;
		}
        List<JtjhBean> dataList = jtjhService.queryListByJhbh(bean.getJhbh());
	    List<JtjhBean> result = new ArrayList<JtjhBean>();	

	    for (Object ele : dataList)
	    {
	        JtjhBean elebean = (JtjhBean)ele;
			result.add(elebean);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";

	    context.put("json",jsonStr);
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
	    String sdate=df.format(new Date());
	    Map<String,Object>  data = new HashMap<String,Object> ();
	    if (result==null|| result.size()<1 ){	
            data.put("jhbh", "");
	        data.put("xdrq", sdate);
		    data.put("jhrq","");
	    }
	    else{
            data.put("jhbh", result.get(0).getJhbh());
	        data.put("xdrq", result.get(0).getXdrq()!=null?(df.format(result.get(0).getXdrq())):"");
			data.put("jhrq",result.get(0).getJhrq()!=null?(df.format(result.get(0).getJhrq())):"");

	    }
        context.put("data", data);
        context.put(SUCCESS, true);	
		
//System.out.println("����ɽ-XsddAction--dataListById-context"+context.toString());
		HtmlUtil.writerJson(response, context);

	}
   

 
  //���ظ����ͺ�
	@RequestMapping("/getUniXh")
	public void getUniXh(HttpServletResponse response) throws Exception{
	
		List<JtjhBean> dataList = jtjhService.queryByAll();
	
	    List<String> xh1 = new ArrayList();
	    if (xh1==null || xh1.size()<1 )
	    {
			return ;
	    }	
		for (Object ele : dataList)
		{
			JtjhBean st = (JtjhBean)ele;
			if ((st.getJhbh()).startsWith("SC"))
		    {		
			    xh1.add(st.getGxxh());
			}
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
        if (gg1==null || gg1.size()<1 )
	    {
			return ;
	    }

		for (Object ele : dataList)
		{
			JtjhBean st = (JtjhBean)ele;
	        if ((st.getJhbh()).startsWith("SC"))
		    {		
			   gg1.add(st.getGxgg());
			}
			
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
	public void delete(Integer[] id,HttpServletRequest request,HttpServletResponse response) throws Exception{
	    if(id != null && id.length > 0){
			for (int i=0;i<id.length;i++ )
			{
				String message;

				 JtjhBean bean  = jtjhService.queryById(id[i]);
	             if(bean.getState()  != 1 ){
			         sendFailureMessage(response, "�ð��Ʒ�ƻ�����ͣ������,����ɾ������ʹ�á��ƻ���������ܽ��б��!");
			         continue;
		         }
	
                List<JtjhWghbBean> wghbDataList=jtjhWghbService.queryListByJhId(bean.getId());
		        if(wghbDataList !=null  &&  wghbDataList.size()>0 ){
			        sendFailureMessage(response, "�ð��Ʒ�ƻ��Ѿ��������깤�㱨,����ɾ��!");
			        continue;
		         }
	            String username = SessionUtils.getUser(request).getNickName();
		        String lrr=bean.getCreateBy();
		        if (!username.equals(lrr)){
			        sendFailureMessage(response, "�ð��Ʒ�ƻ�Ϊ("+bean.getCreateBy()+")����������ɾ��!");
		            continue;
		         }
			 
                 message="IDΪ("+id[i]+")�Ķ���,ɾ���ɹ���";
	             sendSuccessMessage(response,  message);	
  	             jtjhService.deleteById(id[i]);	
			}

		}else{
			sendFailureMessage(response, "δѡ�м�¼");
			return; 
		}

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

