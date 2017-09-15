package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenu Mapper
 * @author Administrator
 *
 */
public interface XsddMapper<T> extends BaseMapper<T> {
	
	/**
	 * ��ѯ�������۶����б�
	 * @return
	 */
	public List<T> queryByAll();

	/**
	 * ��ѯID���������۶�����ͷ
	 * @return
	 */
	public List<T> queryListById(Integer xsddid);
		
	/**
	 * @param jhbh 
	 * @return
	 */
	public List<T> queryListByJhbh(String jhbh);	
	
	
}
