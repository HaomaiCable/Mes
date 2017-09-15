package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.CgptRole;
import com.hmmes.bean.CgptRoleRel;
import com.hmmes.bean.CgptRoleRel.RelType;
import com.hmmes.mapper.CgptRoleMapper;

/**
 * 
 * <br>
 * <b>���ܣ�</b>CgptRoleService<br>
 * <b>���ߣ�</b>�����<br>
 * <b>���ڣ�</b> Dec 9, 2011 <br>
 * <b>��Ȩ���У�<b>��Ȩ����(C) 2011��WWW.VOWO.COM<br>
 */
@Service("cgptRoleService")
public class CgptRoleService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CgptRoleService.class);
	
	@Autowired
	private CgptRoleRelService<CgptRoleRel> cgptRoleRelService;
	
	/**
	 * ��ӽ�ɫ&�˵���ϵ
	 */
	public void addRoleMenuRel(Integer roleId,Integer[] menuIds) throws Exception{
		if(roleId == null ||  menuIds == null || menuIds.length < 1 ){ 
			return;
		}
		for(Integer menuid :menuIds ){ 
			CgptRoleRel rel = new CgptRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(menuid);
			rel.setRelType(RelType.MENU.key);
			cgptRoleRelService.add(rel);
		}
	}
		
	/**
	 * ��ӽ�ɫ&��ť��ϵ
	 */
	public void addRoleBtnRel(Integer roleId,Integer[] btnIds) throws Exception{
		if(roleId == null ||  btnIds == null || btnIds.length < 1 ){ 
			return;
		}
		for(Integer btnid : btnIds ){ 
			CgptRoleRel rel = new CgptRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(btnid);
			rel.setRelType(RelType.BTN.key);
			cgptRoleRelService.add(rel);
		}
	}
		
	
	/**
	 * ���
	 * @param role
	 * @param menuIds
	 * @throws Exception
	 */
	public void add(CgptRole role,Integer[] menuIds,Integer[] btnIds) throws Exception {
		super.add((T)role);
//System.out.println("����ɽrole:add"+role.getId());
		addRoleMenuRel(role.getId(),menuIds);
		addRoleBtnRel(role.getId(),btnIds);
	}

	/**
	 * ɾ��
	 * @param id
	 * @throws Exception
	 */
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
		for(Object id : ids){
			//���������ϵ
			cgptRoleRelService.deleteByRoleId((Integer)id);
		}
	}

	/**
	 * �޸�
	 * @param role
	 * @param menuIds
	 * @throws Exception
	 */
	public void update(CgptRole role,Integer[] menuIds,Integer[] btnIds) throws Exception {
		super.update((T)role);
		//���������ϵ
		cgptRoleRelService.deleteByRoleId(role.getId(),RelType.MENU.key);
		cgptRoleRelService.deleteByRoleId(role.getId(),RelType.BTN.key);
		addRoleMenuRel(role.getId(),menuIds);
		addRoleBtnRel(role.getId(),btnIds);
	}

	
	/**
	 *��ѯȫ����Ч��Ȩ��
	 * @return
	 */
	public List<T> queryAllList(){
		return getMapper().queryAllList();
	}

	

	/**
	 *��ѯȫ����Ч��Ȩ��
	 * @return
	 */
	public List<T> queryByUserid(Integer userid){
		return getMapper().queryByUserid(userid);
	}

	@Autowired
    private CgptRoleMapper<T> mapper;

		
	public CgptRoleMapper<T> getMapper() {
		return mapper;
	}

}
