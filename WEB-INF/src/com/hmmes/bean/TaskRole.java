package com.hmmes.bean;


public class TaskRole extends BaseBean {
	
		private Integer id;//   id����    private Integer userId;//   �ƻ�������ID����	private String userName;//   �û���¼��	private Integer state;//   ״̬ 0=����,1=����
	
	private String roleStrs;//�û�Ȩ��, ��","����,�����ݿ��޹�	private int[] roleIds = {}; //�ɹ����ʷ����id,�����ݿ��޹� 	private String nickName;//�û���	,�����ݿ��޹�	private Integer viewId ;//�ƻ��������û�ID	,�����ݿ��޹�
		public String getRoleStrs() {
		return this.roleStrs;
	}
	public void setRoleStrs(String roleStrs) {
		this.roleStrs = roleStrs;
	}	public int[] getRoleIds() {		return roleIds;	}	public void setRoleIds(int[] roleIds) {		this.roleIds = roleIds;	}	public String getNickName() {		return this.nickName;	}	public void setNickName(String nickName) {		this.nickName = nickName;	}	public Integer getViewId() {	    return this.viewId;	}	public void setViewId(Integer userId) {	    this.viewId=viewId;	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getUserId() {	    return this.userId;	}	public void setUserId(Integer userId) {	    this.userId=userId;	}	public String getUserName() {	    return this.userName;	}	public void setUserName(String userName) {	    this.userName=userName;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
}
