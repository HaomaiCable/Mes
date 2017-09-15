package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface JtMapper<T> extends BaseMapper<T> {
	
	/**
	 *��ѯȫ��List
	 * @return
	 */
	public List<T> queryAllList();
	
	/**
	 * ����jtmc(��̨����)
	 * @return
	 */
	public T queryByJtmc(String jtmc);


}
