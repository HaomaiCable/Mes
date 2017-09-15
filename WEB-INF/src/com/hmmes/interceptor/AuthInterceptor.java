package com.hmmes.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hmmes.action.BaseAction;
import com.hmmes.annotation.Auth;
import com.hmmes.bean.SysUser;
import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.SessionUtils;

/**
 * Ȩ��������
 * @author lu
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private final static Logger log= Logger.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod method = (HandlerMethod)handler;
		Auth  auth = method.getMethod().getAnnotation(Auth.class);
		////��֤��½��ʱ����  auth = null��Ĭ����֤ 
		if( auth == null || auth.verifyLogin()){
			String baseUri = request.getContextPath();
			String path = request.getServletPath();
			SysUser user =SessionUtils.getUser(request);
			
		
			if(user  == null){
				if(path.endsWith(".shtml")){
					response.setStatus(response.SC_GATEWAY_TIMEOUT);
					response.sendRedirect(baseUri+"/login.shtml");
					return false;
				}else{
					response.setStatus(response.SC_GATEWAY_TIMEOUT);
					Map<String, Object> result = new HashMap<String, Object>();
					result.put(BaseAction.SUCCESS, false);
					result.put(BaseAction.LOGOUT_FLAG, true);//��¼��� true �˳�
					result.put(BaseAction.MSG, "��¼��ʱ.");
					HtmlUtil.writerJson(response, result);
					return false;
				}
			}
		}
		//��֤URLȨ��
		if( auth == null || auth.verifyURL()){	
			String username = SessionUtils.getUser(request).getNickName();
			String menuUrl="";
			//�ж��Ƿ񳬼�����Ա
			if(!SessionUtils.isAdmin(request)){
				menuUrl = StringUtils.remove( request.getRequestURI(),request.getContextPath());

                if(!SessionUtils.isAccessUrl(request, StringUtils.trim(menuUrl))){					
					//��־��¼
					String userMail = SessionUtils.getUser(request).getEmail();
					String msg ="URLȨ����֤��ͨ��:[url="+menuUrl+"][email ="+ userMail+"]" ;
					log.error(msg);
					
					response.setStatus(response.SC_FORBIDDEN);
					Map<String, Object> result = new HashMap<String, Object>();
					result.put(BaseAction.SUCCESS, false);
					result.put(BaseAction.MSG, "û��Ȩ�޷���,����ϵ����Ա.");
					HtmlUtil.writerJson(response, result);
					return false;
				}
			}

			if (!"ϵͳ����Ա".equals(username.trim()))
			{
			    System.out.println("["+username+"]----"+menuUrl);
			}
		}
		return super.preHandle(request, response, handler);
	}

	
}
