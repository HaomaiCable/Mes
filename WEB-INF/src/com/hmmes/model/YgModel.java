package com.hmmes.model;

public class YgModel extends BaseModel {
	
	private Integer id;//   id����	private String xm;//   ����	private Integer jtid;//  ������̨Id,jt.id	private String jtmc;//   ������̨����	private Integer state;//   ״̬0=���� 1=����	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getJtid() {	    return this.jtid;	}	public void setJtid(Integer jtid) {	    this.jtid=jtid;	}	public String getXm() {	    return this.xm;	}	public void setXm(String xm) {	    this.xm=xm;	}	public String getJtmc() {	    return this.jtmc;	}	public void setJtmc(String jtmc) {	    this.jtmc=jtmc;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
	
}
