package com.hmmes.model;
public class CpgxmxModel extends BaseModel {
		private Integer id;//   主键	private Integer cpid;//    产品信息库关联id cp.id	private Integer row;//    工序编号	private Integer state;//    状态，0=分解机台计划时使用，1=下达机台计划不使用该工序	private String sbmc;//   工序设备名称	private String iszl;//   主=主线，零=零线	private String gxxh;//   工序产品型号	private String gxgg;//   工序产品规格	private String gxdy;//   工序电压等级	private String gxlb;//   计算工时定额用的类别	private Double jssl;//   计算工时定额用的数量洗漱	private Integer gxxs;//   该工序分解后相对于产品数量的系数	private Double gxgs;//   该公司需要的产品工时	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getCpid() {	    return this.cpid;	}	public void setCpid(Integer cpid) {	    this.cpid=cpid;	}	public Integer getRow() {	    return this.row;	}	public void setRow(Integer row) {	    this.row=row;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}	public String getSbmc() {	    return this.sbmc;	}	public void setSbmc(String sbmc) {	    this.sbmc=sbmc;	}	public String getIszl() {	    return this.iszl;	}	public void setIszl(String iszl) {	    this.iszl=iszl;	}	public String getGxxh() {	    return this.gxxh;	}	public void setGxxh(String gxxh) {	    this.gxxh=gxxh;	}	public String getGxgg() {	    return this.gxgg;	}	public void setGxgg(String gxgg) {	    this.gxgg=gxgg;	}	public String getGxdy() {	    return this.gxdy;	}	public void setGxdy(String gxdy) {	    this.gxdy=gxdy;	}	public String getGxlb() {	    return this.gxlb;	}	public void setGxlb(String gxlb) {	    this.gxlb=gxlb;	}	public Double getJssl() {	    return this.jssl;	}	public void setJssl(Double jssl) {	    this.jssl=jssl;	}	public Integer getGxxs() {	    return this.gxxs;	}	public void setGxxs(Integer gxxs) {	    this.gxxs=gxxs;	}	public Double getGxgs() {	    return this.gxgs;	}	public void setGxgs(Double gxgs) {	    this.gxgs=gxgs;	}
	
}
