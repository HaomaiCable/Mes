package com.hmmes.bean;



public class SbBean extends BaseBean {
	
	private Integer id;//   ����	
	private String sbmc;//  �豸���ƣ��´��̨�ƻ���ʾ
	private String deksbmc;//  ��Ʒ��ʱ����(��Ʒ�������ݿ�)�е��豸���ƣ���ѯ������
	private Double sbsl;//   �豸̨��
	private Double bc;//   ���豸�Ĺ�����Σ����磬ÿ��1�࣬ÿ������
	private Double cqgs;//   ÿ����ڹ�ʱ
	private Double yyfh;//   ��δ�깤���ɣ���λ����ʱ��
    private Double bpjhfh;//   ���μƻ���Ҫ���ɣ���λ����ʱ��
    private Double tzxs;//   ���ɵ���ϵ����ʵ�ʸ���=(yyfh+bpjhfh)*tzxx,��������=ʵ�ʸ���/(sbsl*bc*cqgs)
	private String jt;//   ������̨����
	private Integer state;//  �豸״̬�� 0=���ã�1=������

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getSbmc() {
		return this.sbmc;
	}
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}

	public String getDeksbmc() {
	    return this.deksbmc;
	}
	public void setDeksbmc(String deksbmc) {
	    this.deksbmc=deksbmc;
	}

	public Double getSbsl() {
	    return this.sbsl;
	}
	public void setSbsl(Double sbsl) {
	    this.sbsl=sbsl;
	}
	public Double getBc() {
	    return this.bc;
	}
	public void setBc(Double bc) {
	    this.bc=bc;
	}

	public Double getCqgs() {
	    return this.cqgs;
	}
	public void setCqgs(Double cqgs) {
	    this.cqgs=cqgs;
	}
	public Double getYyfh() {
	    return this.yyfh;
	}
	public void setYyfh(Double yyfh) {
	    this.yyfh=yyfh;
	}
	public Double getBpjhfh() {
	    return this.bpjhfh;
	}
	public void setBpjhfh(Double bpjhfh) {
	    this.bpjhfh=bpjhfh;
	}
	public Double getTzxs() {
	    return this.tzxs;
	}
	public void setTzxs(Double tzxs) {
	    this.tzxs=tzxs;
	}
	public String getJt() {
	    return this.jt;
	}
	public void setJt(String jt) {
	    this.jt=jt;
	}
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

}
