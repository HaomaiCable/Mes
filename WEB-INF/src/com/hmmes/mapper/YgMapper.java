package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface YgMapper<T> extends BaseMapper<T> {
	
	/**
	 *��ѯȫ���Ĳ�����
	 * @return
	 */
	public List<T> queryAllList();
	/**
	 * ���ݻ�̨ID�ģ���ѯ����Ա��
	 * @return
	 */
	public List<T> queryListById(Integer id);

}
