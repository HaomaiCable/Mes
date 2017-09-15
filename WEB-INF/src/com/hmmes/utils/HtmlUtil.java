package com.hmmes.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.hmmes.utils.json.JSONUtil;

/**
 * <br>
 * <b>���ܣ�</b>��ϸ�Ĺ�������<br>
 * <b>���ߣ�</b>�����<br>
 * <b>���ڣ�</b> Dec 14, 2011 <br>
 * <b>�����ߣ�</b><br>
 * <b>���ڣ�</b> <br>
 * <b>�������ݣ�</b><br>
 */
public class HtmlUtil {
	
	/**
	 * 
	 * <br>
	 * <b>���ܣ�</b>���json��ʽ<br>
	 * <b>���ߣ�</b>�����<br>
	 * <b>���ڣ�</b> Dec 14, 2011 <br>
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void writerJson(HttpServletResponse response,String jsonStr) {
			writer(response,jsonStr);
	}
	
	public static void writerJson(HttpServletResponse response,Object object){
			try {
				response.setContentType("application/json");
				writer(response,JSONUtil.toJSONString(object));
			} catch (JSONException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 
	 * <br>
	 * <b>���ܣ�</b>���HTML����<br>
	 * <b>���ߣ�</b>�����<br>
	 * <b>���ڣ�</b> Dec 14, 2011 <br>
	 * @param response
	 * @param htmlStr
	 * @throws Exception
	 */
	public static void writerHtml(HttpServletResponse response,String htmlStr) {
		writer(response,htmlStr);
	}
	
	private static void writer(HttpServletResponse response,String str){
		try {
			StringBuffer result = new StringBuffer();
			//����ҳ�治����
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
//System.out.println("����ɽ����:"+str);
			PrintWriter out= null;

			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	} 
}
