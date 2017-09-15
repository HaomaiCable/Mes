package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenu Mapper
 * @author Administrator
 *
 */
public interface CpMapper<T> extends BaseMapper<T> {
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<T> queryByAll();

	/**
	 * ��ѯID��
	 * @return
	 */
	public List<T> queryListById(Integer id);

	/**
	 *����xh��gg��ѯ��Ʒ
	 * @return
	 */
	public T queryByXhAndGg(String xh, String gg);
	
	
}
