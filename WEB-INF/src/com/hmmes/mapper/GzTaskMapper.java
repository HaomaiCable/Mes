package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenu Mapper
 * @author Administrator
 *
 */
public interface GzTaskMapper<T> extends BaseMapper<T> {
	
	/**
	 * ��ѯ�б�
	 * @return
	 */
	public List<T> queryByAll();

	/**
	 * ��ѯID������
	 * @return
	 */
	public List<T> queryListById(Integer id);
	
}
