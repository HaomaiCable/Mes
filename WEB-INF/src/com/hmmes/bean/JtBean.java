package com.hmmes.bean;
import java.util.List;

public class JtBean extends BaseBean {

		private Integer id;//   id����	private String jtmc;//   ��̨����	private String gd;//   ��������	private String cj;//   ��������	private String searchname;//   ��̨��ʱ���ҵ��豸��	private Integer state;//   ״̬0=���� 1=����
	//��̨������Ա��	private List<YgBean> ygs;	public List<YgBean> getYgs() {		return this.ygs;	}	public void setYgs(List<YgBean> ygs) {		this.ygs = ygs;	}	
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getJtmc() {	    return this.jtmc;	}	public void setJtmc(String jtmc) {	    this.jtmc=jtmc;	}	public String getGd() {	    return this.gd;	}	public void setGd(String gd) {	    this.gd=gd;	}	public String getCj() {	    return this.cj;	}	public void setCj(String cj) {	    this.cj=cj;	}	public String getSearchname() {	    return this.searchname;	}	public void setSearchname(String searchname) {	    this.searchname=searchname;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
}
