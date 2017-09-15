package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.hmmes.bean.SiteColumn;
import com.hmmes.model.SiteColumnModel;
import com.hmmes.service.SiteColumnService;
import com.hmmes.bean.SiteColumn;
import com.hmmes.model.SiteColumnModel;
 
@Controller
@RequestMapping("/sys/siteColumn") 
public class SiteColumnAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(SiteColumnAction.class);
	
	// Servrice start
	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private SiteColumnService<SiteColumn> siteColumnService; 
	
	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(SiteColumnModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		List<SiteColumn> dataList = siteColumnService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);
		return forword("siteColumn/list",context); 
	}
	

}
