package com.hmmes.mapper;

import java.util.List;

/**
 * SiteType Mapper
 * @author Administrator
 *
 */
public interface SiteTypeMapper<T> extends BaseMapper<T> {
	
	/**
	 * ɾ������վ������
	 * @param siteTypeId
	 * @param siteId
	 */	
	public void deleteSiteRel(Integer siteTypeId);
	
	
	/**
	 * ������վ������
	 * @return
	 */
	public List<T> queryByAll();
	
	
	public List<T> queryBySiteId(Integer siteId);
	
}
