package com.hmmes.utils;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hmmes.bean.SysUser;
import com.hmmes.utils.Constant.SuperAdmin;

/**
 * 
 * Cookie ������
 *
 */
public final class SessionUtils {
	
	protected static final Logger logger = Logger.getLogger(SessionUtils.class);
	
	private static final String SESSION_USER = "session_user";

	private static final String SESSION_VALIDATECODE = "session_validatecode";//��֤��
	
	
	private static final String SESSION_ACCESS_URLS = "session_access_urls"; //ϵͳ�ܹ����ʵ�URL
	
	
	private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; //ϵͳ�˵���ť

	
	/**
	  * ����session��ֵ
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static void setAttr(HttpServletRequest request,String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 
	 /**
	  * ��ȡsession��ֵ
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 /**
	  * ɾ��Sessionֵ
	  * @param request
	  * @param key
	  */
	 public static void removeAttr(HttpServletRequest request,String key){
		 request.getSession(true).removeAttribute(key);
	 }
	 
	 /**
	  * �����û���Ϣ ��session
	  * @param request
	  * @param user
	  */
	 public static void setUser(HttpServletRequest request,SysUser user){
		 request.getSession(true).setAttribute(SESSION_USER, user);
	 }
	 
	 
	 /**
	  * ��session�л�ȡ�û���Ϣ
	  * @param request
	  * @return SysUser
	  */
	 public static SysUser getUser(HttpServletRequest request){
		return (SysUser)request.getSession(true).getAttribute(SESSION_USER);
	 }
	 
	 
	 /**
	  * ��session�л�ȡ�û���Ϣ
	  * @param request
	  * @return SysUser
	  */
	 public static void removeUser(HttpServletRequest request){
		removeAttr(request, SESSION_USER);
	 }
	 
	 
	 /**
	  * ������֤�� ��session
	  * @param request
	  * @param user
	  */
	 public static void setValidateCode(HttpServletRequest request,String validateCode){
		 request.getSession(true).setAttribute(SESSION_VALIDATECODE, validateCode);
	 }
	 
	 
	 /**
	  * ��session�л�ȡ��֤��
	  * @param request
	  * @return SysUser
	  */
	 public static String getValidateCode(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(SESSION_VALIDATECODE);
	 }
	 
	 
	 /**
	  * ��session�л�ɾ����֤��
	  * @param request
	  * @return SysUser
	  */
	 public static void removeValidateCode(HttpServletRequest request){
		removeAttr(request, SESSION_VALIDATECODE);
	 }
	 
	 /**
	  * �жϵ�ǰ��¼�û��Ƿ񳬼�����Ա
	  * @param request
	  * @return
	  */
	 public static boolean isAdmin(HttpServletRequest request){ //�жϵ�¼�û��Ƿ񳬼�����Ա
		 SysUser user =  getUser(request);
		 if(user == null  || user.getSuperAdmin() != SuperAdmin.YES.key){
			 return false;
		 }
		 return true;
	 }
	 
	 
	 
	 /**
	  * ����ϵͳ�ܹ����ʵ�URL
	  * @param request
	  * @return
	  */
	 public static void setAccessUrl(HttpServletRequest request,List<String> accessUrls){ 
		 setAttr(request, SESSION_ACCESS_URLS, accessUrls);
	 }
	 
	 
	 
	 /**
	  * �ж�URL�Ƿ�ɷ���
	  * @param request
	  * @return
	  */
	 public static boolean isAccessUrl(HttpServletRequest request,String url){ 
		 List<String> accessUrls = (List)getAttr(request, SESSION_ACCESS_URLS);
		 //for (String str:accessUrls )
		 //{
         //System.out.println("����ɽSessionUtils:"+str);		 
	     // }
		 if(accessUrls == null ||accessUrls.isEmpty() || !accessUrls.contains(url)){
			 return false;
		 }
		 return true;
	 }
	 
	 
	 /**
	  * ���ò˵���ť
	  * @param request
	  * @param btnMap
	  */
	 public static void setMemuBtnMap(HttpServletRequest request,Map<String,List> btnMap){ 
		 setAttr(request, SESSION_MENUBTN_MAP, btnMap);
	 }
	 
	 /**
	  * ��ȡ�˵���ť
	  * @param request
	  * @param btnMap
	  */
	 public static List<String> getMemuBtnListVal(HttpServletRequest request,String menuUri){ 
		 Map btnMap  = (Map)getAttr(request, SESSION_MENUBTN_MAP);
		 if(btnMap == null || btnMap.isEmpty()){
			 return null;
		 }
		 return (List<String>)btnMap.get(menuUri);
	 }
	 
//		private static final String SESSION_ACCESS_URLS = "session_access_urls"; //ϵͳ�ܹ����ʵ�URL
//		
//		
//		private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; //ϵͳ�˵���ť
	
}