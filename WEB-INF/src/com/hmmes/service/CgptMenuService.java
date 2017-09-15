package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.CgptMenu;
import com.hmmes.bean.CgptMenuBtn;
import com.hmmes.bean.CgptRoleRel;
import com.hmmes.bean.CgptRoleRel.RelType;
import com.hmmes.mapper.CgptMenuMapper;

/**
 * 
 * <br>
 * <b>���ܣ�</b>CgptMenuService<br>
 * <b>���ߣ�</b>�����<br>
 * <b>���ڣ�</b> Dec 9, 2011 <br>
 * <b>��Ȩ���У�<b>��Ȩ����(C) 2011��WWW.VOWO.COM<br>
 */
@Service("cgptMenuService")
public class CgptMenuService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CgptMenuService.class);


	@Autowired
	private CgptRoleRelService<CgptRoleRel> cgptRoleRelService;
	
	@Autowired
	private CgptMenuBtnService<CgptMenuBtn> cgptMenuBtnService;
	
	@Autowired
    private CgptMenuMapper<T> mapper;
	
	/**
	 * ����˵�btn
	 * @param btns
	 * @throws Exception 
	 */
	public void saveBtns(Integer menuid,List<CgptMenuBtn> btns) throws Exception{
//System.out.println("����ɽserver��"+ btns.get(0).getBtnName());
		if(btns == null || btns.isEmpty() ||  "null".equals(btns.get(0).getBtnName())){
			return;
		}
		for (CgptMenuBtn btn : btns) {
			if(btn.getId()!= null && "1".equals(btn.getDeleteFlag())){
				cgptMenuBtnService.delete(btn.getId());
				continue;
			}
			btn.setMenuid(menuid);
			if(btn.getId() == null){
				cgptMenuBtnService.add(btn);
			}else{
				cgptMenuBtnService.update(btn);
			}
		}
		
	}
	

	public void addBean(CgptMenu menu) throws Exception {
		super.add((T)menu);

		saveBtns(menu.getId(),menu.getBtns());		}


	public void updateBean(CgptMenu menu) throws Exception {
		super.update((T)menu);
		saveBtns(menu.getId(),menu.getBtns());	

	}


	/**
	 * ��ѯ����ϵͳ�˵��б�
	 * @return
	 */
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}
	
	/**
	 * ��ȡ�����˵�
	 * @return
	 */
	public List<T> getRootMenu(Integer menuId){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("menuId", menuId);
		return mapper.getRootMenu(map);
	}
	
	/**
	 * ��ȡ�Ӳ˵�
	 * @return
	 */
	public List<T> getChildMenu(){
		return mapper.getChildMenu();
	}
	
	/**
	 * �����û�id��ѯ���˵�
	 * @param roleId
	 * @return
	 */
	public List<T> getRootMenuByUser(Integer userId){
		return getMapper().getRootMenuByUser(userId);
	}
	
	
	/**
	 * �����û�id��ѯ�Ӳ˵�
	 * @param roleId
	 * @return
	 */
	public List<T> getChildMenuByUser(Integer userId){
		return getMapper().getChildMenuByUser(userId);
	}
	
	
	/**
	 * ����Ȩ��id��ѯ�˵�
	 * @param roleId
	 * @return
	 */
	public List<T> getMenuByRoleId(Integer roleId){
		return getMapper().getMenuByRoleId(roleId);
	}
	
	
	
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
		//ɾ��������ϵ
		for(Object id : ids){
			cgptRoleRelService.deleteByObjId((Integer)id, RelType.MENU.key);
			cgptMenuBtnService.deleteByMenuid((Integer)id);
		}
	}

	
	
	
	public CgptMenuMapper<T> getMapper() {
		return mapper;
	}

}
