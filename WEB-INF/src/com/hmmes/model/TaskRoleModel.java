package com.hmmes.model;

public class TaskRoleModel extends BaseModel {
		private Integer id;//   id����    private Integer userId;//   �ƻ�������ID����	private String userName;//   �û���¼��	private Integer state;//   ״̬ 0=����,1=����	private String nickName;//�û���	,�����ݿ��޹�	public String getNickName() {		return this.nickName;	}	public void setNickName(String nickName) {		this.nickName = nickName;	}	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getUserId() {	    return this.userId;	}	public void setUserId(Integer userId) {	    this.userId=userId;	}	public String getUserName() {	    return this.userName;	}	public void setUserName(String userName) {	    this.userName=userName;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
}
