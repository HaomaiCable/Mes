package com.hmmes.model;

public class GysdaModel extends BaseModel {		private Integer id;//   id 主键	private String name;//  供应商名称	private String addr;//   地址	private String fr;//  法定代表人	private String khh;//   开户银行	private String zh;//   账号	private String sh;//   税号	private String lxr;//   联系人	private String phone;//   手机	private String tel;//   电话	private String fax;//   传真	private String email;//   邮箱	private String ghfl;//   供货分类	private String ghwz;//   供货物资明细	private Integer state;//   状态 0=可用,1=禁用	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getAddr() {	    return this.addr;	}	public void setAddr(String addr) {	    this.addr=addr;	}	public String getFr() {	    return this.fr;	}	public void setFr(String fr) {	    this.fr=fr;	}	public String getKhh() {	    return this.khh;	}	public void setKhh(String khh) {	    this.khh=khh;	}	public String getZh() {	    return this.zh;	}	public void setZh(String zh) {	    this.zh=zh;	}	public String getSh() {	    return this.sh;	}	public void setSh(String sh) {	    this.sh=sh;	}	public String getLxr() {	    return this.lxr;	}	public void setLxr(String lxr) {	    this.lxr=lxr;	}	public String getPhone() {	    return this.phone;	}	public void setPhone(String phone) {	    this.phone=phone;	}	public String getTel() {	    return this.tel;	}	public void setTel(String tel) {	    this.tel=tel;	}	public String getFax() {	    return this.fax;	}	public void setFax(String fax) {	    this.fax=fax;	}	public String getEmail() {	    return this.email;	}	public void setEmail(String email) {	    this.email=email;	}	public String getGhfl() {	    return this.ghfl;	}	public void setGhfl(String ghfl) {	    this.ghfl=ghfl;	}	public String getGhwz() {	    return this.ghwz;	}	public void setGhwz(String ghwz) {	    this.ghwz=ghwz;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
}
