package com.hmmes.mapper;

import java.util.List;

/**
 * SiteColumn Mapper
 * @author Administrator
 *
 */
public interface SiteColumnMapper<T> extends BaseMapper<T> {
	

	/**
	 * ����վ��id��ѯ��Ŀ
	 * @param siteId
	 */
	public List<T> queryBySiteId(Integer siteId);
}
