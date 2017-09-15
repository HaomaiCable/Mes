package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenu Mapper
 * @author Administrator
 *
 */
public interface JtjhMapper<T> extends BaseMapper<T> {
	
	/**
	 * ��ѯ�б�
	 * @return
	 */
	public List<T> queryByAll();

	/**
	 * ��ѯID������
	 * @return
	 */
	public List<T> queryListById(Integer id);
	/**
	 * ����ddid��ѯ
	 * @return
	 */
	public List<T> queryListByDdId(Integer ddid);
		
	/**
	 * @param jhbh 
	 * @return
	 */
	public List<T> queryListByJhbh(String jhbh);	
	/**
	 * ����sbmcdek(������豸����)��ѯ
	 * @return
	 */
	public List<T> queryListBySbmcdek(String sbmcdek);
	/**
	 * ����sbmc(�´�ƻ����豸����)��ѯ
	 * @return
	 */
	public List<T> queryListBySbmc(String sbmc);	
	public T queryByJhbhAndRow(String jhbh,Integer row);
	
}
