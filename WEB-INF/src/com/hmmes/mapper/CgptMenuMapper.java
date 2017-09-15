package com.hmmes.mapper;

import java.util.List;

/**
 * CgptMenu Mapper
 * @author Administrator
 *
 */
public interface CgptMenuMapper<T> extends BaseMapper<T> {
	
	/**
	 * ��ѯ����ϵͳ�˵��б�
	 * @return
	 */
	public List<T> queryByAll();
	
	
	/**
	 * ��ȡ�����˵�
	 * @return
	 */
	public List<T> getRootMenu(java.util.Map map);
	
	/**
	 * ��ȡ�Ӳ˵�
	 * @return
	 */
	public List<T> getChildMenu();
	
	

	/**
	 * ����Ȩ��id��ѯ�˵�
	 * @param roleId
	 * @return
	 */
	public List<T> getMenuByRoleId(Integer roleId);
	
	
	/**
	 * �����û�id��ѯ���˵��˵�
	 * @param userId
	 * @return
	 */
	public List<T> getRootMenuByUser(Integer userId);
	
	/**
	 * �����û�id��ѯ�Ӳ˵��˵�
	 * @param userId
	 * @return
	 */
	public List<T> getChildMenuByUser(Integer userId);
	
}
