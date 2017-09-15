
package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections; 
//import java.util.Date;

import net.sf.json.JSONObject;
//import net.sf.json.JSON;
import org.json.JSONArray;
import com.alibaba.fastjson.JSON;  
//import com.alibaba.fastjson.JSONObject;  
import com.alibaba.fastjson.annotation.JSONField;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hmmes.bean.JsonBean;
import com.hmmes.bean.ClcpBean;
import com.hmmes.bean.ClcpCljzBean;
import com.hmmes.model.ClcpModel;
import com.hmmes.model.ClcpCljzModel;
import com.hmmes.service.ClcpService;
import com.hmmes.service.ClcpCljzService;
import com.hmmes.utils.HtmlUtil;

 
@Controller
@RequestMapping("/cljzManage") 
public class CljzAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(CljzAction.class);
	
	// Servrice start
	// Servrice start
	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private ClcpService<ClcpBean> clcpService; 
	
	@Autowired
	private ClcpCljzService<ClcpCljzBean> clcpCljzService;
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/cljz")
	public ModelAndView  list(ClcpModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("business/cljzManage",context); 
	}
	
	
	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(ClcpModel model,HttpServletResponse response) throws Exception{
		List<ClcpBean> dataList = clcpService.queryByList(model);
		List<ClcpBean> result = new ArrayList<ClcpBean>();
		// ��װVO����
		for (Object ele : dataList)
		{
			ClcpBean st = (ClcpBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-role-Json"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);
	}

    @RequestMapping("/dataListForCpId")   //���ݲ�ƷID����ѯ���Ͼ�����ʾDataGrid 
	public void  dataListForCpId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<ClcpCljzBean> dataList = clcpCljzService.queryListByCpId(id);
		List<ClcpCljzBean> result = new ArrayList<ClcpCljzBean>();

		for (Object ele : dataList)
		{
			ClcpCljzBean st = (ClcpCljzBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ��XsddAction--dataListchangeForDdid"+jsonStr);
        context.put("cljzmx",jsonStr);
		context.put(SUCCESS, true);	
	    HtmlUtil.writerJson(response, context);
   
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
	public void save(ClcpBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			clcpService.add(bean);
		}else{
			clcpService.update(bean);
		}
		sendSuccessMessage(response, "����ɹ�~");
	}

	//������Ͼ��ص��޸�
    @RequestMapping("/saveCljz")
	public void  saveCljz(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	     request.setCharacterEncoding("UTF-8");  
        //��ȡ�༭���� �����ȡ������json�ַ���  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 String cpid  = request.getParameter("cpid");
//System.out.println("����ɽ-getCljzForCpId--cpid"+cpid);
//System.out.println("����ɽ��XsddAction--savewghb:ddid="+ddid);
//System.out.println("����ɽ��XsddAction--savewghb:insert"+inserted);
//System.out.println("����ɽ��XsddAction--savewghb:delete"+deleted);
//System.out.println("����ɽ��XsddAction--savewghb:update"+updated);


		 
         Integer row=0;
         if(deleted != null){  
             //��json�ַ���ת���ɶ���  

             List<ClcpCljzBean> listDeleted = new ArrayList<ClcpCljzBean>();	
             listDeleted = JSON.parseArray(deleted, ClcpCljzBean.class);  
             //TODO ����Ϳ��Ը���ת����Ķ��������Ӧ�Ĳ����� 
			 Integer [] ids=new Integer[listDeleted.size()];

             row=0;
		     for (Object ele : listDeleted )
		     {
				 ClcpCljzBean bean = (ClcpCljzBean)ele;	  

                  Integer id1=bean.getId();
                  ids[row]=id1;
				  row++;
			 } 
			 clcpCljzService.delete(ids);
         }  
 
         if(inserted != null){  
             //��json�ַ���ת���ɶ���  
             List<ClcpCljzBean> listInserted = new ArrayList<ClcpCljzBean>();	
	
			 listInserted = JSON.parseArray(inserted, ClcpCljzBean.class);  
	
			 for (Object ele : listInserted )
		     {
				 ClcpCljzBean bean = (ClcpCljzBean)ele;	
				 bean.setCpid(Integer.valueOf(cpid));
				 clcpCljzService.add(bean);
		     }
         }  

         if(updated != null){  
            //��json�ַ���ת���ɶ���  
		    List<ClcpCljzBean> listUpdated = new ArrayList<ClcpCljzBean>();	
            listUpdated = JSON.parseArray(updated, ClcpCljzBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 ClcpCljzBean bean = (ClcpCljzBean)ele;	
 				 clcpCljzService.update(bean);
		     }
          }
	    sendSuccessMessage(response, "����ɹ�~");
		return ;
	}	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object> ();
		ClcpBean bean  = clcpService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}

	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		clcpService.deleteBean(id);
		sendSuccessMessage(response, "ɾ���ɹ�");
	}

    //���Ͼ���DataGrid
	@RequestMapping("/getCljzForCpId")
	public void getCljzForCpId(Integer id,HttpServletResponse response) throws Exception{

		JSONObject context = new JSONObject();
		ClcpBean bean  = clcpService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
		List<ClcpCljzBean> dataList = clcpCljzService.queryListByCpId(id);
		List<ClcpCljzBean> result = new ArrayList<ClcpCljzBean>();	
		for (Object ele : dataList)
		{
			ClcpCljzBean elebean = (ClcpCljzBean)ele;
			result.add(elebean);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-XsddAction--getWghb-jsonArr"+jsonStr);
		context.put("json",jsonStr);

		context.put(SUCCESS, true);	
		
//System.out.println("����ɽ-XsddAction---getWghb-context"+context.toString());
		HtmlUtil.writerJson(response, context);

	}
    //���ظ����ͺ�
	@RequestMapping("/getUniXh")
	public void getUniXh(HttpServletResponse response) throws Exception{
	
		List<ClcpBean> dataList = clcpService.queryByAll();
	
	    List<String> xh1 = new ArrayList();
		for (Object ele : dataList)
		{
			ClcpBean st = (ClcpBean)ele;
			xh1.add(st.getCpxh());
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
    //���ظ��Ĺ��
	@RequestMapping("/getUniGg")
	public void getUniGg(HttpServletResponse response) throws Exception{
	
		List<ClcpBean> dataList = clcpService.queryByAll();
	
	    List<String> gg1= new ArrayList();
		for (Object ele : dataList)
		{
			ClcpBean st = (ClcpBean)ele;
			gg1.add(st.getCpgg());
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
    //���ظ��ĵ�ѹ
	@RequestMapping("/getUniDy")
	public void getUniDy(HttpServletResponse response) throws Exception{
	
		List<ClcpBean> dataList = clcpService.queryByAll();
	
	    List<String> dy1= new ArrayList();
		for (Object ele : dataList)
		{
			ClcpBean st = (ClcpBean)ele;
			dy1.add(st.getCpdy());
		}
		

		//ɾ���ظ���
		List<String> unidy1=new ArrayList();
		unidy1=removeDuplicate(dy1);
		Collections.sort(unidy1);
		List<JsonBean> unidy = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unidy1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unidy1.get(i));
            id++;
			unidy.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unidy);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}
   //���ظ��Ĳ�������
	@RequestMapping("/getUniCl")
	public void getUniCl(HttpServletResponse response) throws Exception{
	
		List<ClcpCljzBean> dataList = clcpCljzService.queryByAll();
	
	    List<String> cl1= new ArrayList();
		for (Object ele : dataList)
		{
			ClcpCljzBean st = (ClcpCljzBean)ele;
			cl1.add(st.getClmc());
		}
		

		//ɾ���ظ���
		List<String> unicl1=new ArrayList();
		unicl1=removeDuplicate(cl1);
		Collections.sort(unicl1);
		List<JsonBean> unicl = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unicl1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unicl1.get(i));
            id++;
			unicl.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unicl);
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


}

