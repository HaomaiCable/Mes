package com.hmmes.model;//import java.util.Date;

public class XsddRkhbModel extends BaseModel {
			private Integer id;//   主键	private Integer ddid;//    销售订单关联ID xsdd.id	private java.sql.Date rkrq;//   入库日期	private Double rksl;//   入库数量	private String rksm;//   入库信息的文字性说明	private Integer rk;//   是否全部入库完毕，1=全部入库完毕    private String lrBy;//   录入人		private java.sql.Timestamp lrTime;//   录入时间	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getDdid() {	    return this.ddid;	}	public void setDdid(Integer ddid) {	    this.ddid=ddid;	}	public java.sql.Date getRkrq() {	    return this.rkrq;	}	public void setRkrq(java.sql.Date rkrq) {	    this.rkrq=rkrq;	}	public Double getRksl() {	    return this.rksl;	}	public void setRksl(Double rksl) {	    this.rksl=rksl;	}	public String getRksm() {	    return this.rksm;	}	public void setRksm(String rksm) {	    this.rksm=rksm;	}	public Integer getRk() {	    return this.rk;	}	public void setRk(Integer rk) {	    this.rk=rk;	}	public String getLrBy() {		return this.lrBy;	}	public void setLrBy(String lrBy) {		this.lrBy = lrBy;	}	public java.sql.Timestamp getLrTime() {		return this.lrTime;	}	public void setLrTime(java.sql.Timestamp lrTime) {		this.lrTime = lrTime;	}
	
}
