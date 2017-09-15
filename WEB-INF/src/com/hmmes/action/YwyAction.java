
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

import com.hmmes.bean.YwyBean;
import com.hmmes.model.YwyModel;
import com.hmmes.service.YwyService;
import com.hmmes.utils.HtmlUtil;
//import com.hmmes.utils.json.JsonDateValueProcessor;
 
@Controller
@RequestMapping("/ywyManage") 
public class YwyAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(YwyAction.class);
	
	// Servrice start
	@Autowired(required=false) 
	private YwyService<YwyBean> YwyService; 
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/ywy")
	public ModelAndView  list(YwyModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		//List<YwyBean> dataList = YwyService.queryByList(model);
		//����ҳ������
		//context.put("dataList", dataList);
		return forword("business/ywyManage",context); 
	}
	
	
	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(YwyModel model,HttpServletResponse response) throws Exception{
		List<YwyBean> dataList = YwyService.queryByList(model);
		List<YwyBean> result = new ArrayList<YwyBean>();
		// ��װVO����
		for (Object ele : dataList)
		{
			// ÿ������Ԫ�ض���StockBean����
			YwyBean st = (YwyBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
//System.out.println("����ɽ-role-Array"+jsonArr.toString());

		//JSONObject jsonMap = new JSONObject();
		//Map<String,Object> jsonMap = new HashMap<String,Object>();
		//����ҳ������
		//jsonMap.put("total",model.getPager().getRowCount());
		//jsonMap.put("rows", result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-role-Json"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);

	
	}
	@RequestMapping("/getYwyList")  //ҵ��Աcombobox ѡ������
	public void  ywyList(YwyModel model,HttpServletResponse response) throws Exception{
		List<YwyBean> dataList = YwyService.queryAllList();
		List<YwyBean> result = new ArrayList<YwyBean>();
		// ��װVO����
		for (Object ele : dataList)
		{
			// ÿ������Ԫ�ض���StockBean����
			YwyBean st = (YwyBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
//System.out.println("����ɽ-ҵ��Ա-List"+jsonArr.toString());
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
	public void save(YwyBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			YwyService.add(bean);
		}else{
			YwyService.update(bean);
		}
		sendSuccessMessage(response, "����ɹ�~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object> ();
		YwyBean bean  = YwyService.queryById(id);
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
		YwyService.delete(id);
		sendSuccessMessage(response, "ɾ���ɹ�");
	}
	

}

