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


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.hmmes.bean.CgptMenu;
import com.hmmes.bean.CgptRole;
import com.hmmes.bean.CgptRoleRel;
import com.hmmes.bean.CgptUser;
import com.hmmes.bean.GysdaBean;
import com.hmmes.model.CgptMenuModel;
import com.hmmes.model.CgptUserModel;
import com.hmmes.service.CgptRoleService;
import com.hmmes.service.CgptUserService;
import com.hmmes.service.GysdaService;
import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.MethodUtil;
import com.hmmes.utils.SessionUtils;
import com.hmmes.bean.CgptUser;
import com.hmmes.bean.BaseBean.DELETED;
import com.hmmes.bean.BaseBean.STATE;
import com.hmmes.exception.ServiceException;
import com.hmmes.model.CgptUserModel;
import com.hmmes.utils.json.JSONUtil;

 
@Controller
@RequestMapping("/cgptUser") 
public class CgptUserAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(CgptUserAction.class);
	
	// Servrice start
	@Autowired(required=false) //�Զ�ע�룬����Ҫ����set�����ˣ�required=false��ʾû��ʵ���࣬Ҳ���ᱨ��
	private CgptUserService<CgptUser> cgptUserService; 
	
	// Servrice start
	@Autowired(required=false) 
	private CgptRoleService<CgptRole> cgptRoleService; 
	@Autowired(required=false) 
	private GysdaService<GysdaBean> gysdaService; 
	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(CgptUserModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		List<CgptUser> dataList = cgptUserService.queryByList(model);
		//����ҳ������
		context.put("dataList", dataList);

		return forword("cgpt/cgptUser",context); 
	}
	
	
	/**
	 * json �б�ҳ��
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(CgptUserModel model,HttpServletResponse response) throws Exception{
		//����ҳ������
		//Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<CgptUser> dataList =cgptUserService.queryByList(model);
		for(CgptUser user: dataList){
			List<CgptRole> list = cgptRoleService.queryByUserid(user.getId());
		    user.setRoleStr(rolesToStr(list));
			GysdaBean gysbean = gysdaService.queryById(user.getGysId());
			if (gysbean!=null)
			{	
				user.setGysStr(gysbean.getName());
			}
		
		}
		List<CgptUser> result = new ArrayList<CgptUser>();	
		for (Object ele : dataList)
		{
			CgptUser st = (CgptUser)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
//System.out.println("����ɽ-role-Array"+jsonArr.toString());
		//JSONObject jsonMap = new JSONObject();
		//jsonMap.put("total",model.getPager().getRowCount());
		//jsonMap.put("rows", dataList);
		//HtmlUtil.writerJson(response, jsonMap);
		
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-role-Json"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}
	
	/**
	 * ��ɫ�б�ת���ַ���
	 * @param list
	 * @return
	 */
	private String rolesToStr(List<CgptRole> list){
		if(list == null || list.isEmpty()){
			return null;
		}
		StringBuffer str = new StringBuffer();
		for(int i=0;i<list.size();i++){
			CgptRole role = list.get(i);
			str.append(role.getRoleName());
			if((i+1) < list.size()){
				str.append(",");
			}
		}
		return str.toString();
	}
	
	/**
	 * ��ӻ��޸�����
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(CgptUser bean,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		int count = cgptUserService.getUserCountByEmail(bean.getEmail());
		if(bean.getId() == null){
			if(count > 0){
				throw new ServiceException("�û��Ѵ���.");
			}
			bean.setDeleted(DELETED.NO.key);
			bean.setPwd(MethodUtil.MD5("123"));  //�����û���ʼ����Ϊ123 
			cgptUserService.add(bean);
		}else{
			if(count > 1){
				throw new ServiceException("�û��Ѵ���.");
			}
			cgptUserService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "����ɹ�~");
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();  //	getRootMap();
		//Map<String,Object>  context =
		CgptUser bean  = cgptUserService.queryById(id);
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
		cgptUserService.delete(id);
		sendSuccessMessage(response, "ɾ���ɹ�");
	}
	
	
	/**
	 * ��ӻ��޸�����
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updatePwd")
	public void updatePwd(Integer id,String oldPwd,String newPwd,HttpServletRequest request,HttpServletResponse response) throws Exception{
		boolean isAdmin = SessionUtils.isAdmin(request); //�Ƿ񳬼�����Ա
		CgptUser bean  = cgptUserService.queryById(id);
		if(bean.getId() == null || DELETED.YES.key == bean.getDeleted()){
			sendFailureMessage(response, "�û�������.");
			return;
		}
		if(StringUtils.isBlank(newPwd)){
			sendFailureMessage(response, "�����������.");
			return;
		}
		//���ǳ�������Ա��ƥ�������
		if(!isAdmin && !MethodUtil.ecompareMD5(oldPwd,bean.getPwd())){
			sendFailureMessage(response, "ԭ���벻��ȷ.");
			return;
		}
		bean.setPwd(MethodUtil.MD5(newPwd));
		cgptUserService.update(bean);
		sendSuccessMessage(response, "����ɹ�~");
	}
	

	
	/**
	 * �û���Ȩҳ��
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userRole") 
	public ModelAndView  userRole(HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("/cgpt/cgptUserRole", context);
	}
	
	/**
	 * �û���Ȩ�б�
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/userList") 
	public void  userList(CgptUserModel model,HttpServletResponse response) throws Exception{
		model.setState(STATE.ENABLE.key);
		List<CgptUser> dataList =cgptUserService.queryByList(model);
		for(CgptUser user: dataList){
			List<CgptRole> list = cgptRoleService.queryByUserid(user.getId());
		    user.setRoleStr(rolesToStr(list));
		}
		List<CgptUser> result = new ArrayList<CgptUser>();	
				// ��װVO����
		for (Object ele : dataList)
		{
			// ÿ������Ԫ�ض���StockBean����
			CgptUser st = (CgptUser)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
//System.out.println("����ɽ-role-Array"+jsonArr.toString());
		//JSONObject jsonMap = new JSONObject();
		//jsonMap.put("total",model.getPager().getRowCount());
		//jsonMap.put("rows", dataList);
		//HtmlUtil.writerJson(response, jsonMap);

		//dataList(model, response);

		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("����ɽ-role-Json"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	
	}

	/**
	 * ��ѯ�û���Ϣ
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getUser") 
	public void getUser(Integer id,HttpServletResponse response)  throws Exception{
		//Map<String,Object>  context = getRootMap();
		JSONObject context = new JSONObject();	 //getRootMap(); 
		CgptUser bean  = cgptUserService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "û���ҵ���Ӧ�ļ�¼!");
			return;
		}
		Integer[] roleIds = null;
//System.out.println("����ɽCgptUseruserid"+bean.getId());
		List<CgptRoleRel>  roles  =cgptUserService.getUserRole(bean.getId());
		if(roles != null){
			roleIds = new Integer[roles.size()];
			int i = 0;
			for(CgptRoleRel rel : roles ){
				roleIds[i] = rel.getRoleId();
				i++;
			}
		}
		//Map<String, Object> data = new HashMap<String, Object>();
		JSONObject data =  new JSONObject();	
		data.put("id", bean.getId());
		data.put("email", bean.getEmail());
		data.put("roleIds", roleIds);
//System.out.println("����ɽ-CgptUser-data"+data.toString());
		context.put(SUCCESS, true);
		context.put("data", data);
//System.out.println("����ɽ-CgptUser-context"+context.toString());
		HtmlUtil.writerJson(response, context);
	}
	
	/**
	 * ��ӻ��޸�����
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addUserRole")
	public void addUserRole(Integer id,Integer roleIds[],HttpServletResponse response) throws Exception{
		cgptUserService.addUserRole(id, roleIds);
		sendSuccessMessage(response, "����ɹ�");
	}
}
