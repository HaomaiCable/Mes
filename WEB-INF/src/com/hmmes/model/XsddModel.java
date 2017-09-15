package com.hmmes.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class XsddModel extends BaseModel {
	
    private Integer id;//   ����
	private java.sql.Date xdrq;//   �ƻ��´�����
	private String jhbh;//   �ƻ����
	private String ywy;//   ҵ��Ա	
	private Integer row;//    �кţ�������ϸ�к�
	private Integer state;//    ״̬��1=��ͣ��2=���ϣ�0=����
	private String xh;//   ��Ʒ�ͺ�
	private String gg;//   ��Ʒ���
	private String dy;//   ��ѹ�ȼ�
	private String gy;//   ��������
	private String dw;//   ������λ
	private Double sl;//   �ƻ�����
	private java.sql.Date jhrq_kh;//   �ͻ�Ҫ��Ľ�������
	private java.sql.Date jhrq;//   ��������ȷ�ϵļƻ���������
	private String jsyq;//   ����Ҫ��
	private String ph;//   ����
	private String ywyy;//   ����ԭ��
	private String ywjt;//   �����̨
	private java.sql.Date ecjhq;//   ���ν�����
    private Integer xdjt;//    �Ƿ�ֽ��´��̨��1=�ѷֽ��´0=δ�ֽ��´�
	private String createBy;//   ������	
	private java.sql.Timestamp createTime;//   ����ʱ��
	private String updateBy;//   �޸���	
	private java.sql.Timestamp updateTime;//   �޸�ʱ��
	private Integer deleted;//   �Ƿ�ɾ��,0=δɾ����1=��ɾ��
    private java.sql.Date maxWgrq;//����깤���ڣ�
    private String wgzs;//����깤���깤����,�ַ���
	private Integer qbRk;//�Ƿ�ȫ����⣬1=�ƻ���ȫ��������,,2==δ�깤,�Ѽ��뵽���ݿ�(xsdd)��

    private Date fromxdrq;//   ��ѯ�ã����۶�����ʼ����
    private Date toxdrq;//   ��ѯ�ã����۶�����������
    private Date wgrq;//   ��ѯ�ã������깤����
    private Date fromjhrq;//   ��ѯ�ã��������ڿ�ʼ����
    private Date tojhrq;//   ��ѯ�ã��������ڽ�������
    private Date frommaxWgrq_Search;//   ��ѯ�ã�����깤���ڿ�ʼ����
    private Date tomaxWgrq_Search;//   ��ѯ�ã�����깤���ڽ�������

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
	public Date getWgrq() {
	    return this.wgrq;
	}
    public void setWgrq(Date wgrq) {
	    this.wgrq=wgrq;
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
	public Date getFrommaxWgrq_Search() {
	    return this.frommaxWgrq_Search;
	}
	public void setFrommaxWgrq_Search(Date frommaxWgrq_Search) {
	    this.frommaxWgrq_Search=frommaxWgrq_Search;
	}	
	public Date getTomaxWgrq_Search() {
	    return this.tomaxWgrq_Search;
	}
	public void setTomaxWgrq_Search(Date tomaxWgrq_Search) {
	    this.tomaxWgrq_Search=tomaxWgrq_Search;
	}	
	public java.sql.Date getMaxWgrq() {
		return this.maxWgrq;
	}
	public void setMaxWgrq(java.sql.Date maxWgrq) {
		this.maxWgrq = maxWgrq;
	}
	public String getWgzs() {
		return this.wgzs;
	}
	public void setWgzs(String wgzs) {
		this.wgzs = wgzs;
	}
	public Integer getQbRk() {
		return this.qbRk;
	}
	public void setQbRk(Integer qbRk) {
		this.qbRk = qbRk;
	}	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getYwy() {
		return this.ywy;
	}
	public void setYwy(String ywy) {
		this.ywy = ywy;
	}
	public Integer getRow() {
	    return this.row;
	}
	public void setRow(Integer row) {
	    this.row=row;
	}
	public Integer getState() {
	    return this.state;
	}
	public void setState(Integer state) {
	    this.state=state;
	}
	public String getXh() {
	    return this.xh;
	}
	public void setXh(String xh) {
	    this.xh=xh;
	}
	public String getGg() {
	    return this.gg;
	}
	public void setGg(String gg) {
	    this.gg=gg;
	}
	public String getDy() {
	    return this.dy;
	}
	public void setDy(String dy) {
	    this.dy=dy;
	}
	public String getGy() {
	    return this.gy;
	}
	public void setGy(String gy) {
	    this.gy=gy;
	}
	public String getDw() {
	    return this.dw;
	}
	public void setDw(String dw) {
	    this.dw=dw;
	}
	public Double getSl() {
	    return this.sl;
	}
	public void setSl(Double sl) {
	    this.sl=sl;
	}
	public java.sql.Date getJhrq_kh() {
	    return this.jhrq_kh;
	}
	public void setJhrq_kh(java.sql.Date jhrq_kh) {
	    this.jhrq_kh=jhrq_kh;
	}
	public java.sql.Date getJhrq() {
	    return this.jhrq;
	}
	public void setJhrq(java.sql.Date jhrq) {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		//java.sql.Date jhrq1=null; 
   		//try {
  	    //     jhrq1=new java.sql.Date(sdf.parse(sdf.format(jhrq)).getTime());
        //} catch (ParseException e) {
        //     e.printStackTrace();
        //}
		this.jhrq=jhrq;
	}
	public String getJsyq() {
	    return this.jsyq;
	}
	public void setJsyq(String jsyq) {
	    this.jsyq=jsyq;
	}
	public String getPh() {
	    return this.ph;
	}
	public void setPh(String ph) {
	    this.ph=ph;
	}
	public void setYwyy(String ywyy) {
	    this.ywyy=ywyy;
	}
	public String getYwyy() {
	    return this.ywyy;
	}
	public void setYwjt(String ywjt) {
	    this.ywjt=ywjt;
	}
	public String getYwjt() {
	    return this.ywjt;
	}
	public java.sql.Date getEcjhq() {
	    return this.ecjhq;
	}
	public void setEcjhq(java.sql.Date ecjhq) {
	    this.ecjhq=ecjhq;
	}
	public Integer getXdjt() {
	    return this.xdjt;
	}
	public void setXdjt(Integer xdjt) {
	    this.xdjt=xdjt;
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
	
}
