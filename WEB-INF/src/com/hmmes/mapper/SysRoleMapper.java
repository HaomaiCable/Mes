package com.hmmes.mapper;

import java.util.List;

/**
 * SysRole Mapper
 * @author Administrator
 *
 */
public interface SysRoleMapper<T> extends BaseMapper<T> {
	
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
