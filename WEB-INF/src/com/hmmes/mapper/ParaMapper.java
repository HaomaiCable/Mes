package com.hmmes.mapper;

import java.util.List;

/**
 * JhbhManage Mapper
 * @author Administrator
 *
 */
public interface ParaMapper<T> extends BaseMapper<T> {
	
	/**
	 *����Flag����ѯ����
	 * @return
	 */
	public List<T> queryByFlag(String flag);
	

}
