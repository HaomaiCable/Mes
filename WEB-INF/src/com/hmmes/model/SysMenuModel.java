package com.hmmes.model;

public class SysMenuModel extends BaseModel {
	

	private Integer id;//   ����
	private String name;//   �˵�����
	private String url;//   ϵͳurl
	private Integer parentId;//   ��id ����sys_menu.id
	private Integer deleted;//   �Ƿ�ɾ��,0=δɾ����1=��ɾ��
	private java.sql.Timestamp createTime;//   ����ʱ��
	private java.sql.Timestamp updateTime;//   �޸�ʱ��
	private Integer rank;//   ����
	private String actions; //ע��Action ��ť|�ָ�

	public String getActions() {
		return this.actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getParentId() {
		return this.parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getRank() {
		return this.rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	
	
}
