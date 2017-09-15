package com.hmmes.bean;

import com.hmmes.bean.BaseBean.DELETED;


public class CgptRoleRel extends BaseBean {
		private Integer roleId;// ��ɫ���� sys_role.id	
	    private Integer objId; // �������� type=0����sys_menu.id, type=1����sys_user.id
	  	private Integer relType; // �������� 0=�˵�,1=�û�
	
	 
	/**
 	 * ö��
 	 * @author lu
 	 *
 	 */
 	public static enum RelType {
		MENU(0, "�˵�"), USER(1,"�û�"),BTN(2,"��ť");
		public int key;
		public String value;
		private RelType(int key, String value) {
			this.key = key;
			this.value = value;
		}
		public static RelType get(int key) {
			RelType[] values = RelType.values();
			for (RelType object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}
	
		public Integer getRoleId() {	    return this.roleId;	}		
	    public void setRoleId(Integer roleId) {	    this.roleId=roleId;	}	
		public Integer getObjId() {	    return this.objId;	}	
		public void setObjId(Integer objId) {	    this.objId=objId;	}	
		public Integer getRelType() {	    return this.relType;	}	
		public void setRelType(Integer relType) {	    this.relType=relType;	}
}
