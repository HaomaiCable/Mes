package com.hmmes.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.hmmes.exception.ServiceException;

public class FormValidate {
	
	/**
	 * ������֤
	 * @param value
	 * @param msg
	 * @throws ServiceException 
	 */
	public static void validNull(String value,String msg) throws ServiceException{
		if(StringUtils.isBlank(value)){
			throw new ServiceException(msg);
		}
	} 
	
	/**
	 * �ж��ַ�������
	 * @param value
	 * @param minLen
	 * @param maxLen
	 * @param msg
	 * @throws ServiceException
	 */
	public static void  validLen(String value,Integer minLen,Integer maxLen,String msg) throws ServiceException{
		int len =StringUtils.isNotBlank(value) ? value.length(): 0; 
		if( len < minLen ||  len > maxLen){
			throw new ServiceException(msg);
		}
	}
	
	/**
	 * ��֤�Ƿ�����
	 * @param value
	 * @param minLen
	 * @param maxLen
	 * @param msg
	 * @throws ServiceException
	 */
	public static void  validNumber(String value,String msg) throws ServiceException{
		if(!NumberUtils.isNumber(value)){
			throw new ServiceException(msg);
		}
	}
		
	
	/**
	 * ��֤�Ƿ�int ����
	 * @param value
	 * @param minLen
	 * @param maxLen
	 * @param msg
	 * @throws ServiceException
	 */
	public static void  validInt(String value,String msg) throws ServiceException{
		try{
			Integer.parseInt(value);
		}catch(Exception e){
			throw new ServiceException(msg);
		}
	}
	
	
	
	/**
	 * ��֤�Ƿ�Float ����
	 * @param value
	 * @param minLen
	 * @param maxLen
	 * @param msg
	 * @throws ServiceException
	 */
	public static void  validFloat(String value,String msg) throws ServiceException{
		try{
			Float.parseFloat(value);
		}catch(Exception e){
			throw new ServiceException(msg);
		}
	}
}
