package com.hmmes.model;
import java.util.Date;

public class JtjhModel extends BaseModel {
	
 	
    private Integer id;//   ����
	private Integer row;//    ��̨�ƻ�������˳��ţ�������	
	private Integer ddid;//    ���۶�������ID xsdd.id
	private java.sql.Date xdrq;//   ��̨�ƻ��´�����
	private String jhbh;//   ��̨�ƻ����
	private Integer state;//    ״̬��1=��ͣ��2=���ϣ�0=����
    private String gd;//��������
	private String sbmc;//   ������豸����
	private String sbmcdek;//   ��ʱ�������ҵ��豸����
	private String iszl;//   ��=���ߣ���=����
	private String gxxh;//   ����Ĳ�Ʒ�ͺ�
	private String gxxh_o;//   ����������ϸ��Ĳ�Ʒ�ͺţ������Ժ���㹤ʱ
	private String gxgg;//   ����Ĳ�Ʒ���
	private String gxgg_o;//   ����������ϸ��Ĳ�Ʒ��񣬱��ڲ��ҡ����㹤ʱ
	private String gxdy;//   ����ĵ�ѹ�ȼ�
	private String gxdy_o;//   ����������ϸ��ĵ�ѹ�ȼ��������Ժ���㹤ʱ
	private String gxgy;//   ����Ĺ���
	private String gxdw;//   ����ļ�����λ��ͬ���۶���������λ��xsdd.dw
	private String gxjsyq;//   ����Ҫ��ͬ���۶�������Ҫ��,xsdd.jsyq
	private String gxph;//   ���ţ�ͬ���۶�������Ҫ��,xsdd.ph
	private String gxlb;//   ��𣬼��㹤ʱ��
	private Double jhsl;//   ����ļƻ�����
	private Double jhsl_o;//   ��������о���ļƻ����������ڲ��ҡ����㹤ʱ�������깤�㱨����
	private String  jhsl_xs;//   �ַ����ͣ����ڻ�̨�ƻ���ʾ�ʹ�ӡ
	private String  wgflag;//   �ַ����ͣ��깤��ǣ���Ϊ���깤��δ�깤
	private java.sql.Date jhrq;//   �����깤����
	private String createBy;//   ������	
	private java.sql.Timestamp createTime;//   ����ʱ��
	private String updateBy;//   �޸���	
	private java.sql.Timestamp updateTime;//   �޸�ʱ��
	private Integer deleted;//   �Ƿ�ɾ��,0=δɾ����1=��ɾ��
	private Integer dtts;//    ��������������



    private Date fromxdrq;//   ��ѯ�ã����۶�����ʼ����
    private Date toxdrq;//   ��ѯ�ã����۶�����������
    private Date fromjhrq;//   ��ѯ�ã��ƻ����ڿ�ʼ����
    private Date tojhrq;//   ��ѯ�ã��ƻ����ڽ�������

    private Date fromcreateTime;//   ������ӡ�ƻ��ã��ƻ����ɵĿ�ʼ����
    private Date tocreateTime;//   ������ӡ�ƻ��ã��ƻ����ɵĽ�������



	public Date getFromxdrq() {
	    return this.fromxdrq;
	}
	public void setFromxdrq(Date fromxdrq) {
	    this.fromxdrq=fromxdrq;
	}
	public Date getToxdrq() {
	    return this.toxdrq;
	}
	public void setToxdrq(Date toxdrq) {
	    this.toxdrq=toxdrq;
	}
	public Date getFromjhrq() {
	    return this.fromjhrq;
	}
	public void setFromjhrq(Date fromjhrq) {
	    this.fromjhrq=fromjhrq;
	}
	public Date getTojhrq() {
	    return this.tojhrq;
	}
	public void setTojhrq(Date tojhrq) {
	    this.tojhrq=tojhrq;
	}	

	public Date getFromcreateTime() {
	    return this.fromcreateTime;
	}
	public void setFromcreateTime(Date fromcreateTime) {
	    this.fromcreateTime=fromcreateTime;
	}
	public Date getTocreateTime() {
	    return this.tocreateTime;
	}
	public void setTocreateTime(Date tocreateTime) {
	    this.tocreateTime=tocreateTime;
	}	
	
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRow() {
	    return this.row;
	}
	public void setRow(Integer row) {
	    this.row=row;
	}
	public Integer getDdid() {
	    return this.ddid;
	}
	public void setDdid(Integer ddid) {
	    this.ddid=ddid;
	}
	public java.sql.Date getXdrq() {
	    return this.xdrq;
	}
	public void setXdrq(java.sql.Date xdrq) {
	    this.xdrq=xdrq;
	}
	public String getJhbh() {
		return this.jhbh;
	}
	public void setJhbh(String jhbh) {
		this.jhbh = jhbh;
	}
	public Integer getState() {
	    return this.state;
	}
	public void setState(Integer state) {
	    this.state=state;
	}
	public String getGd() {
		return this.gd;
	}
	public void setGd(String gd) {
		this.gd = gd;
	}

	public String getSbmc() {
		return this.sbmc;
	}
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}
	public String getSbmcdek() {
		return this.sbmcdek;
	}
	public void setSbmcdek(String sbmcdek) {
		this.sbmcdek = sbmcdek;
	}
	public String getIszl() {
	    return this.iszl;
	}
	public void setIszl(String iszl) {
	    this.iszl=iszl;
	}
	public String getGxxh() {
	    return this.gxxh;
	}
	public void setGxxh(String gxxh) {
	    this.gxxh=gxxh;
	}
	public String getGxxh_o() {
	    return this.gxxh_o;
	}
	public void setGxxh_o(String gxxh_o) {
	    this.gxxh_o=gxxh_o;
	}
	public String getGxgg() {
	    return this.gxgg;
	}
	public void setGxgg(String gxgg) {
	    this.gxgg=gxgg;
	}
	public String getGxgg_o() {
	    return this.gxgg_o;
	}
	public void setGxgg_o(String gxgg_o) {
	    this.gxgg_o=gxgg_o;
	}
	public String getGxdy() {
	    return this.gxdy;
	}
	public void setGxdy(String gxdy) {
	    this.gxdy=gxdy;
	}
	public String getGxdy_o() {
	    return this.gxdy_o;
	}
	public void setGxdy_o(String gxdy_o) {
	    this.gxdy_o=gxdy_o;
	}
	public String getGxgy() {
	    return this.gxgy;
	}
	public void setGxgy(String gxgy) {
	    this.gxgy=gxgy;
	}
	public String getGxdw() {
	    return this.gxdw;
	}
	public void setGxdw(String gxdw) {
	    this.gxdw=gxdw;
	}
	public String getGxjsyq() {
	    return this.gxjsyq;
	}
	public void setGxjsyq(String gxjsyq) {
	    this.gxjsyq=gxjsyq;
	}
	public String getGxph() {
	    return this.gxph;
	}
	public void setGxph(String gxph) {
	    this.gxph=gxph;
	}
	public String getGxlb() {
	    return this.gxlb;
	}
	public void setGxlb(String gxlb) {
	    this.gxlb=gxlb;
	}

	public Double getJhsl() {
	    return this.jhsl;
	}
	public void setJhsl(Double jhsl) {
	    this.jhsl=jhsl;
	}
	public Double getJhsl_o() {
	    return this.jhsl_o;
	}
	public void setJhsl_o(Double jhsl_o) {
	    this.jhsl_o=jhsl_o;
	}
	public String getJhsl_xs() {
	    return this.jhsl_xs;
	}
	public void setJhsl_xs(String jhsl_xs) {
	    this.jhsl_xs=jhsl_xs;
	}
	public String getWgflag() {
	    return this.wgflag;
	}
	public void setWgflag(String wgflag) {
	    this.wgflag=wgflag;
	}
	public java.sql.Date getJhrq() {
	    return this.jhrq;
	}
	public void setJhrq(java.sql.Date jhrq) {
	    this.jhrq=jhrq;
	}


	public String getCreateBy() {
		return this.createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return this.updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Integer getDtts() {
		return this.dtts;
	}
	public void setDtts(Integer dtts) {
		this.dtts = dtts;
	}
	
}
