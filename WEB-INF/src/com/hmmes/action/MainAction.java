package com.hmmes.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hmmes.annotation.Auth;
import com.hmmes.bean.SysMenu;
import com.hmmes.bean.SysMenuBtn;
import com.hmmes.bean.SysUser;
import com.hmmes.bean.TreeNode;
import com.hmmes.bean.BaseBean.DELETED;
import com.hmmes.bean.BaseBean.STATE;
import com.hmmes.model.SiteMainModel;
import com.hmmes.service.SysMenuBtnService;
import com.hmmes.service.SysMenuService;
import com.hmmes.service.SysUserService;
import com.hmmes.utils.DateUtil;
import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.MethodUtil;
import com.hmmes.utils.SessionUtils;
import com.hmmes.utils.TreeUtil;
import com.hmmes.utils.URLUtils;
import com.hmmes.utils.Constant.SuperAdmin;

@Controller
public class MainAction extends BaseAction {

	
	private final static Logger log= Logger.getLogger(MainAction.class);
	
	// Servrice start
	@Autowired(required=false) 
	private SysMenuService<SysMenu> sysMenuService; 
	
	@Autowired(required=false) 
	private SysUserService<SysUser> sysUserService; 
	
	@Autowired(required=false) 
	private SysMenuBtnService<SysMenuBtn> sysMenuBtnService;
	
	/**
	 * ��¼ҳ��
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/login")
	public ModelAndView  login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("login", context);
	}
	
	
	/**
	 * ��¼
	 * @param email �����¼�˺�
	 * @param pwd ����
	 * @param verifyCode ��֤��
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/toLogin")
	public void  toLogin(String email,String pwd,String verifyCode,HttpServletRequest request,HttpServletResponse response) throws Exception{
		////��֤��
		//String vcode  = SessionUtils.getValidateCode(request);
		//SessionUtils.removeValidateCode(request);//�����֤�룬ȷ����֤��ֻ����һ��
		//if(StringUtils.isBlank(verifyCode)){
		//	sendFailureMessage(response, "��֤�벻��Ϊ��.");
		//	return;
		//}
		////�ж���֤���Ƿ���ȷ
		//if(!verifyCode.toLowerCase().equals(vcode)){
		//	sendFailureMessage(response, "��֤���������.");
		//	return;
		//}
		if(StringUtils.isBlank(email)){
			sendFailureMessage(response, "�˺Ų���Ϊ��.");
			return;
		}
		if(StringUtils.isBlank(pwd)){
			sendFailureMessage(response, "���벻��Ϊ��.");
			return;
		}
		String msg = "�û���¼��־:";
		SysUser user = sysUserService.queryLogin(email, MethodUtil.MD5(pwd));
		if(user == null){
			//��¼�����¼��־
			log.debug(msg+"["+email+"]"+"�˺Ż��������������.");
			sendFailureMessage(response, "�˺Ż��������������.");
			return;
		}
		if(STATE.DISABLE.key == user.getState()){
			sendFailureMessage(response, "�˺��ѱ�����.");
			return;
		}
		//��¼������1 �޸ĵ�¼ʱ��
		int loginCount = 0;
		if(user.getLoginCount() != null){
			loginCount = user.getLoginCount();
		}
		user.setLoginCount(loginCount+1);
		user.setLoginTime(DateUtil.getDateByString(""));
		sysUserService.update(user);
		//����User��Session
		SessionUtils.setUser(request,user);
		//��¼�ɹ���¼��־
		log.debug(msg+"["+email+"]"+"��¼�ɹ�");
		sendSuccessMessage(response, "��¼�ɹ�.");
	}
	
	
	/**
	 * �˳���¼
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/logout")
	public void  logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		SessionUtils.removeUser(request);
		//response.sendRedirect("/login.shtml");
		response.sendRedirect(URLUtils.get("msUrl")+"/login.shtml");

	}
	
	/**
	 * ��ȡAction�µİ�ť
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyURL=false)
	@RequestMapping("/getActionBtn")
	public void  getActionBtn(String url,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> actionTypes = new ArrayList<String>();
		//�ж��Ƿ񳬼�����Ա
		if(SessionUtils.isAdmin(request)){
			result.put("allType", true);
		}else{
			String menuUrl = URLUtils.getReqUri(url);  // ��/mes/ywyMenage/ywy.shtml 
//System.out.println("����ɽmenuUrl1("+menuUrl +")");
			menuUrl = StringUtils.remove(menuUrl,request.getContextPath());  //��/ywyMenage/ywy.shtml ,ȥ����ϵͳ��·��/mes
//System.out.println("����ɽmenuUrl2("+menuUrl +")");
			//��ȡȨ�ް�ť
			actionTypes = SessionUtils.getMemuBtnListVal(request, StringUtils.trim(menuUrl));
			result.put("allType", false);
			result.put("types", actionTypes);
		}
		result.put(SUCCESS, true);
		HtmlUtil.writerJson(response, result);
	}
	 
	
	/**
	 * �޸�����
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyURL=false)
	@RequestMapping("/modifyPwd")
	public void modifyPwd(String oldPwd,String newPwd,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SysUser user = SessionUtils.getUser(request);
		if(user == null){
			sendFailureMessage(response, "�Բ���,��¼��ʱ.");
			return;
		}
		SysUser bean  = sysUserService.queryById(user.getId());
		if(bean.getId() == null || DELETED.YES.key == bean.getDeleted()){
			sendFailureMessage(response, "�Բ���,�û�������.");
			return;
		}
		if(StringUtils.isBlank(newPwd)){
			sendFailureMessage(response, "���벻��Ϊ��.");
			return;
		}
		//���ǳ�������Ա��ƥ�������
		if(!MethodUtil.ecompareMD5(oldPwd,bean.getPwd())){
			sendFailureMessage(response, "���������벻ƥ��.");
			return;
		}
		bean.setPwd(MethodUtil.MD5(newPwd));
		sysUserService.update(bean);
		sendSuccessMessage(response, "Save success.");
	}
	
	/**
	 * ilook ��ҳ
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@Auth(verifyURL=false)
	@RequestMapping("/main") 
	public ModelAndView  main(SiteMainModel model,HttpServletRequest request){
		Map<String,Object>  context = getRootMap();
		SysUser user = SessionUtils.getUser(request);
		List<SysMenu> rootMenus = null;
		List<SysMenu> childMenus = null;
		List<SysMenuBtn> childBtns = null;
		//��������Ա
		if(user != null && SuperAdmin.YES.key ==  user.getSuperAdmin()){
			rootMenus = sysMenuService.getRootMenu(null);// ��ѯ���и��ڵ�
			childMenus = sysMenuService.getChildMenu();//��ѯ�����ӽڵ�
		}else{
			rootMenus = sysMenuService.getRootMenuByUser(user.getId() );//���ڵ�
			childMenus = sysMenuService.getChildMenuByUser(user.getId());//�ӽڵ�
			childBtns = sysMenuBtnService.getMenuBtnByUser(user.getId());//��ť����
			buildData(childMenus,childBtns,request); //������Ҫ������
		}
		context.put("user", user);
		context.put("menuList", treeMenu(rootMenus,childMenus));
		return forword("main/main",context); 
	}
	
	/**
	 * ������������
	 * @return
	 */
	private List<TreeNode> treeMenu(List<SysMenu> rootMenus,List<SysMenu> childMenus){
		TreeUtil util = new TreeUtil(rootMenus,childMenus);
		return util.getTreeNode();
	}
	
	
	/**
	 * ������������
	 * @return
	 */
	private void buildData(List<SysMenu> childMenus,List<SysMenuBtn> childBtns,HttpServletRequest request){
		//�ܹ����ʵ�url�б�
		List<String> accessUrls  = new ArrayList<String>();
		//�˵���Ӧ�İ�ť
		Map<String,List> menuBtnMap = new HashMap<String,List>(); 
		for(SysMenu menu: childMenus){
			//�ж�URL�Ƿ�Ϊ��
			if(StringUtils.isNotBlank(menu.getUrl())){
				List<String> btnTypes = new ArrayList<String>();
				for(SysMenuBtn btn  : childBtns){
					if(menu.getId().equals(btn.getMenuid())){
						btnTypes.add(btn.getBtnType());
						URLUtils.getBtnAccessUrls(menu.getUrl(), btn.getActionUrls(),accessUrls);
					}
				}
				menuBtnMap.put(menu.getUrl(), btnTypes);
				URLUtils.getBtnAccessUrls(menu.getUrl(), menu.getActions(),accessUrls);
				accessUrls.add(menu.getUrl());
			}
		}
		SessionUtils.setAccessUrl(request, accessUrls);//���ÿɷ��ʵ�URL
		SessionUtils.setMemuBtnMap(request, menuBtnMap); //���ÿ��õİ�ť
	}
}
