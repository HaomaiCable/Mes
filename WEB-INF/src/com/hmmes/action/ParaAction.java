
package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections; 
//import java.util.Date;

import net.sf.json.JSONObject;
import org.json.JSONArray;
//import net.sf.json.JsonConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hmmes.bean.JsonBean;
import com.hmmes.bean.ParaBean;
import com.hmmes.model.ParaModel;
import com.hmmes.service.ParaService;
import com.hmmes.utils.HtmlUtil;

 
@Controller
@RequestMapping("/paraManage") 
public class ParaAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(ParaAction.class);
	
	// Servrice start
	// Servrice start
	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private ParaService<ParaBean> paraService;
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/para")
	public ModelAndView  list(ParaModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("business/paraManage",context); 
	}
	
	
	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(ParaModel model,HttpServletResponse response) throws Exception{
		List<ParaBean> dataList = paraService.queryByList(model);
		List<ParaBean> result = new ArrayList<ParaBean>();
		for (Object ele : dataList)
		{
			ParaBean st = (ParaBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-role-Json"+jsonStr);
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
	public void save(ParaBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			paraService.add(bean);
		}else{
			paraService.update(bean);
		}
		sendSuccessMessage(response, "����ɹ�~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object> ();
		ParaBean bean  = paraService.queryById(id);
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
		paraService.delete(id);
		sendSuccessMessage(response, "ɾ���ɹ�");
	}

 

}

