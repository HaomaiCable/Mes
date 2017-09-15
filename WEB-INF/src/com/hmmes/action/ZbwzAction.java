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


import com.hmmes.bean.ZbwzBean;
import com.hmmes.model.ZbwzModel;
import com.hmmes.service.ZbwzService;
import com.hmmes.utils.HtmlUtil;
//import com.hmmes.bean.BaseBean.DELETED;
import com.hmmes.bean.BaseBean.STATE;

 
@Controller
@RequestMapping("/zbwzManage") 
public class ZbwzAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(ZbwzAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ZbwzService<ZbwzBean> zbwzService; 
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/zbwz") 
	public ModelAndView  list(ZbwzModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("cgpt/zbwz",context); 
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(ZbwzModel model,HttpServletResponse response) throws Exception{
		List<ZbwzBean> dataList = zbwzService.queryByList(model);
		//设置页面数据
		//Map<String,Object> jsonMap = new HashMap<String,Object>();
		JSONObject jsonMap = new JSONObject();	
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/zbwzListJson")  //采购物资分类combobox 选择数据
	public void  zbwzListJson(HttpServletResponse response) throws Exception{
		List<ZbwzBean> dataList = zbwzService.queryByAll();
		JSONArray jsonArr= new JSONArray(dataList);
		HtmlUtil.writerJson(response, jsonArr.toString());
		//HtmlUtil.writerJson(response, dataList);
	}
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(ZbwzBean bean,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap<String,Object>();
		if(bean.getId() == null){
			zbwzService.add(bean);
		}else{
			zbwzService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap();
		JSONObject context = new JSONObject();
		ZbwzBean bean  = zbwzService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		zbwzService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
}
