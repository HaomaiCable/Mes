package com.hmmes.bean;


public class ParaBean extends BaseBean {
	
		private Integer id;//   id主键	private String flag;//   标记，参数标记	private String para1;//   参数1	private String para2;//   参数2	private String para3;//   参数3	private String descript;//   备注说明信息	private Integer state;//  状态， 0=可用，1=不可用
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getFlag() {	    return this.flag;	}	public void setFlag(String flag) {	    this.flag=flag;	}	public String getPara1() {	    return this.para1;	}	public void setPara1(String para1) {	    this.para1=para1;	}	public String getPara2() {	    return this.para2;	}	public void setPara2(String para2) {	    this.para2=para2;	}	public String getPara3() {	    return this.para3;	}	public void setPara3(String para3) {	    this.para3=para3;	}	public String getDescript() {	    return this.descript;	}	public void setDescript(String descript) {	    this.descript=descript;	}	public Integer getState() {		return this.state;	}	public void setState(Integer state) {		this.state = state;	}
}
