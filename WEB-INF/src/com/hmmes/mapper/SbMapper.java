package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface SbMapper<T> extends BaseMapper<T> {
	
	/**
	 *��ѯȫ��List
	 * @return
	 */
	public List<T> queryAllList();
	/**	
	 * ��ѯID���е�
	 * @return
	 */
	public List<T> queryListById(Integer id);

    /**
	 * ����DekSbmc��������豸���ƣ�
	 * @return
	 */
	public List<T> queryListByDeksbmc(String deksbmc);
	/**
	 * ����sbmc(�豸����)
	 * @return
	 */
	public T queryBySbmc(String sbmc);

}
