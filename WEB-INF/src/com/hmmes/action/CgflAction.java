package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.hmmes.bean.CgflBean;
import com.hmmes.model.CgflModel;
import com.hmmes.service.CgflService;
import com.hmmes.utils.HtmlUtil;
//import com.hmmes.bean.BaseBean.DELETED;
import com.hmmes.bean.BaseBean.STATE;

 
@Controller
@RequestMapping("/cgflManage") 
public class CgflAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(CgflAction.class);
	
	// Servrice start
	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private CgflService<CgflBean> cgflService; 
	
	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/cgfl") 
	public ModelAndView  list(CgflModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("cgpt/cgfl",context); 
	}
	
	
	/**
	 * json �б�ҳ��
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(CgflModel model,HttpServletResponse response) throws Exception{
		List<CgflBean> dataList = cgflService.queryByList(model);
		//����ҳ������
		//Map<String,Object> jsonMap = new HashMap<String,Object>();
		JSONObject jsonMap = new JSONObject();	
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	
	/**
	 * json �б�ҳ��
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/cgflListJson")  //�ɹ����ʷ���combobox ѡ������
	public void  cgflListJson(HttpServletResponse response) throws Exception{
		List<CgflBean> dataList = cgflService.queryByAll();
		JSONArray jsonArr= new JSONArray(dataList);
		HtmlUtil.writerJson(response, jsonArr.toString());
		//HtmlUtil.writerJson(response, dataList);
	}
	/**
	 * ��ӻ��޸�����
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(CgflBean bean,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap<String,Object>();
		if(bean.getId() == null){
			cgflService.add(bean);
		}else{
			cgflService.update(bean);
		}
		sendSuccessMessage(response, "����ɹ�~");
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap();
		JSONObject context = new JSONObject();
		CgflBean bean  = cgflService.queryById(id);
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
		cgflService.delete(id);
		sendSuccessMessage(response, "ɾ���ɹ�");
	}
	
}
