package com.hmmes.bean;


public class CgptMenuBtn extends BaseBean {
	
		private Integer id;//   ����	private Integer menuid;//    �˵�id���� sys_menu.id	private String btnName;//   ��ť����	private String btnType;//   ��ť���ͣ������б�ҳ��ʾ�İ�ť	private String actionUrls;//   urlע�ᣬ��"," �ָ� ������Ȩ�޿���URL
	
	private String deleteFlag; //ɾ����ǣ������ݿ��ֶ��޹� 1=ɾ��,������ɾ��
	public String getDeleteFlag() {
		return this.deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getMenuid() {	    return this.menuid;	}	public void setMenuid(Integer menuid) {	    this.menuid=menuid;	}	public String getBtnName() {	    return this.btnName;	}	public void setBtnName(String btnName) {	    this.btnName=btnName;	}	public String getBtnType() {	    return this.btnType;	}	public void setBtnType(String btnType) {	    this.btnType=btnType;	}	public String getActionUrls() {	    return this.actionUrls;	}	public void setActionUrls(String actionUrls) {	    this.actionUrls=actionUrls;	}
}
