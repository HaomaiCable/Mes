package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface YwyMapper<T> extends BaseMapper<T> {
	
	/**
	 *��ѯȫ����ҵ��Ա
	 * @return
	 */
	public List<T> queryAllList();
	

}
