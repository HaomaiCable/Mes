package com.hmmes.mapper;

import java.util.List;

/**
 * CgptRole Mapper
 * @author Administrator
 *
 */
public interface CgptRoleMapper<T> extends BaseMapper<T> {
	
	/**
	 *��ѯȫ����Ч��Ȩ��
	 * @return
	 */
	public List<T> queryAllList();
	
	
	/**
	 *�����û�Id��ѯȨ��
	 * @return
	 */
	public List<T> queryByUserid(Integer userid);
}
