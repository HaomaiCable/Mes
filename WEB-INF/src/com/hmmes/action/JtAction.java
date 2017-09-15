
package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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

import com.hmmes.bean.JtBean;
import com.hmmes.model.JtModel;
import com.hmmes.service.JtService;
import com.hmmes.utils.HtmlUtil;
//import com.hmmes.utils.json.JsonDateValueProcessor;

 
@Controller
@RequestMapping("/jtManage") 
public class JtAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(JtAction.class);
	
	// Servrice start
	@Autowired(required=false) 
	private JtService<JtBean> JtService; 
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/jt")
	public ModelAndView  list(JtModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		//List<JtBean> dataList = JtService.queryByList(model);
		//����ҳ������
		//context.put("dataList", dataList);
		return forword("business/jtManage",context); 
	}
	
	
	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(JtModel model,HttpServletResponse response) throws Exception{
		List<JtBean> dataList = JtService.queryByList(model);
		List<JtBean> result = new ArrayList<JtBean>();
		// ��װVO����
		for (Object ele : dataList)
		{

			JtBean st = (JtBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-Jt--dataListById-jsonArr"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	}

	@RequestMapping("/getJtList")  //��̨combobox ѡ������
	public void  jtList(JtModel model,HttpServletResponse response) throws Exception{
		List<JtBean> dataList = JtService.queryAllList();
		List<JtBean> result = new ArrayList<JtBean>();
		// ��װVO����
		for (Object ele : dataList)
		{
			// ÿ������Ԫ�ض���StockBean����
			JtBean st = (JtBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonArr.toString());
	

	
	}
	
	/**
	 * ��ӻ��޸�����
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(JtBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			JtService.add(bean);
		}else{
			JtService.update(bean);
		}
		sendSuccessMessage(response, "����ɹ�~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();

		JtBean bean  = JtService.queryById(id);
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
		JtService.delete(id);
		sendSuccessMessage(response, "ɾ���ɹ�");
	}
	

}

