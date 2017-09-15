package com.hmmes.mapper;

import java.util.Map;


public interface SiteMainMapper<T> extends BaseMapper<T>{
	/**
	 * ����վ������
	 * @param siteTypeId
	 * @param siteId
	 */	
	public void addTypeRel(Map<String,Object> map);
	
	
	/**
	 * ɾ������������
	 * @param siteTypeId
	 * @param siteId
	 */	
	public void deleteTypeRel(Integer siteId);
}
