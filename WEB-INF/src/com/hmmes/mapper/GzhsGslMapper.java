package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface GzhsGslMapper<T> extends BaseMapper<T> {
	
	/**
	 *��ѯȫ��
	 * @return
	 */
	public List<T> queryAllList();

}
