package com.hmmes.model;

public class CgflModel extends BaseModel {	private Integer id;//   id ����	private String name;//   �ɹ���������	private String descr;//   ����	private Integer state;//   ״̬ 0=����,1=����	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getDescr() {	    return this.descr;	}	public void setDescr(String descr) {	    this.descr=descr;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
}
