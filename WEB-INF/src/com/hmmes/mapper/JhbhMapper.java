package com.hmmes.mapper;

import java.util.List;

/**
 * JhbhManage Mapper
 * @author Administrator
 *
 */
public interface JhbhMapper<T> extends BaseMapper<T> {
	
	/**
	 *����year��month��ѯ��ǰ���ƻ����No
	 * @return
	 */
	public T queryNoByYearAndMonth(Integer nian, Integer yue, Integer flag);
	

}
