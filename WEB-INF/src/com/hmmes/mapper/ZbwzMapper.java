package com.hmmes.mapper;

import java.util.List;

/**
 * Mapper
 * @author Administrator
 *
 */
public interface ZbwzMapper<T> extends BaseMapper<T> {
	
	/**
	 * ������
	 * @return
	 */
	public List<T> queryByAll();

	public List<T> queryByGysId(Integer gysId);
	/**
	 * ɾ����Ӧ������
	 * @param siteTypeId
	 * @param siteId
	 */	
	public void deleteGysRel(Integer zbwzId);
	
}
