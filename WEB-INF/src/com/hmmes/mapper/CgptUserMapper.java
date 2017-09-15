package com.hmmes.mapper;

import com.hmmes.model.CgptUserModel;

/**
 * CgptUser Mapper
 * @author Administrator
 *
 */
public interface CgptUserMapper<T> extends BaseMapper<T> {
	
	/**
	 * ����¼
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(CgptUserModel model);
	
	
	/**
	 * ��ѯ��������������Ƿ����
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email);
}
