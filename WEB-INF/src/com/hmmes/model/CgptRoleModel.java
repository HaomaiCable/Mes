package com.hmmes.model;

public class CgptRoleModel extends BaseModel {
	

		private Integer id;//   id����	private String roleName;//   ��ɫ����	private java.sql.Timestamp createTime;//   ����ʱ��	private Integer createBy;//   ������	private java.sql.Timestamp updateTime;//   �޸�ʱ��	private Integer updateBy;//   �޸���	private Integer state;//   ״̬0=���� 1=����	private String descr;//   ��ɫ����	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}		public String getRoleName() {
		return this.roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public java.sql.Timestamp getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.sql.Timestamp createTime) {	    this.createTime=createTime;	}	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	public java.sql.Timestamp getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.sql.Timestamp updateTime) {	    this.updateTime=updateTime;	}	public Integer getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(Integer updateBy) {	    this.updateBy=updateBy;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}	public String getDescr() {	    return this.descr;	}	public void setDescr(String descr) {	    this.descr=descr;	}
	
}
