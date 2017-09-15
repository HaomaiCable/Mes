package com.hmmes.mapper;

import java.util.List;

/**
 *  Mapper
 * @author Administrator
 *
 */
public interface ZbggMapper<T> extends BaseMapper<T> {
	
	/**
	 * ��ѯ�������۶����б�
	 * @return
	 */
	public List<T> queryByAll();	



	/**
	 * ��ѯID���������۶�����ͷ
	 * @return
	 */

	public List<T> queryListById(Integer zbggid);
		
	/**
	 * @param jhbh 
	 * @return
	 */
	public List<T> queryListByGgbh(String ggbh);	
	
	
}
