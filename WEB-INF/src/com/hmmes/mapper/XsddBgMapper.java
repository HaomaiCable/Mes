package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenu Mapper
 * @author Administrator
 *
 */
public interface XsddBgMapper<T> extends BaseMapper<T> {
	
	/**
	 * ��ѯ�����б�
	 * @return
	 */
	public List<T> queryByAll();

	/**
	 * ��ѯID���б�
	 * @return
	 */
	public List<T> queryListById(Integer ddid);
		
	/**
	 * @param bh 
	 * @return
	 */
	public List<T> queryListByBh(String bh);	
	
	
}
