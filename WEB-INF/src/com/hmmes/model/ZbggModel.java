package com.hmmes.model;

import java.util.Date;


public class ZbggModel extends BaseModel {
	
	
private Integer id;//   ����
	
	private java.sql.Date fbrq;//   �б깫�淢������
	private String ggbh;//   �б깫����
	private Integer row;//    �кţ��б�������ϸ�к�
	private Integer state;//    ״̬��2=��ͣ��3=���ϣ�1=����
    private Integer kb;//    �Ƿ񿪱꣬1=�ѿ��꣬0=δ����
	private Integer deleted;//   �Ƿ�ɾ��,0=δɾ����1=��ɾ��
	private java.sql.Timestamp yxrq;//   �б깫�����Ч����
	private String wlfl;//   �ɹ����ʷ���	
	private String wlmc;//   �ɹ���������
	private String wlgg;//   ���Ϲ��
	private String wldw;//   ������λ
	private Double wlsl;//   ��ɹ�����
	private String jsyq;//   �ɹ�����Ҫ��
	private java.sql.Date jhrq_xq;//   ����Ľ�������

	private String createBy;//   ������	
	private java.sql.Timestamp createTime;//   ����ʱ��
	private String updateBy;//   �޸���	
	private java.sql.Timestamp updateTime;//   �޸�ʱ��
	private Integer qd;//    ȷ����Ӧ�̣�1==��ȷ��
	private String qdBy;//   �б�ȷ����	
	private java.sql.Timestamp qdTime;//   �б�ȷ��ʱ��
	private Integer sp;//    ����ȷ���Ĺ�Ӧ�̣�1==������
	private String spBy;//   ������	
	private java.sql.Timestamp spTime;//   ����ʱ��

    private Date fromfbrq;//   ��ѯ�ã��б깫�淢����ʼ����
    private Date tofbrq;//   ��ѯ�ã��б깫�淢����������
    private Date fromyxrq;//   ��ѯ�ã��б깫����Чʱ��Ŀ�ʼ����
    private Date toyxrq;//   ��ѯ�ã��б깫����Чʱ��Ľ�������


	public Date getFromfbrq() {
	    return this.fromfbrq;
	}
	public void setFromfbrq(Date fromfbrq) {
	    this.fromfbrq=fromfbrq;
	}
	public Date getTofbrq() {
	    return this.tofbrq;
	}
	public void setTofbrq(Date tofbrq) {
	    this.tofbrq=tofbrq;
	}	

	public Date getFromyxrq() {
	    return this.fromyxrq;
	}
	public void setFromyxrq(Date fromyxrq) {
	    this.fromyxrq=fromyxrq;
	}
	public Date getToyxrq() {
	    return this.toyxrq;
	}
	public void setToyxrq(Date toyxrq) {
	    this.toyxrq=toyxrq;
	}	
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.sql.Date getFbrq() {
	    return this.fbrq;
	}
	public void setFbrq(java.sql.Date fbrq) {
	    this.fbrq=fbrq;
	}
	public String getGgbh() {
		return this.ggbh;
	}
	public void setGgbh(String ggbh) {
		this.ggbh = ggbh;
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
	public Integer getKb() {
	    return this.kb;
	}
	public void setKb(Integer kb) {
	    this.kb=kb;
	}
	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public java.sql.Timestamp getYxrq() {
		return this.yxrq;
	}
	public void setYxrq(java.sql.Timestamp yxrq) {
		this.yxrq = yxrq;
	}

	public String getWlfl() {
	    return this.wlfl;
	}
	public void setWlfl(String wlfl) {
	    this.wlfl=wlfl;
	}
	public String getWlmc() {
	    return this.wlmc;
	}
	public void setWlmc(String wlmc) {
	    this.wlmc=wlmc;
	}
	public String getWlgg() {
	    return this.wlgg;
	}
	public void setWlgg(String wlgg) {
	    this.wlgg=wlgg;
	}
	public String getWldw() {
	    return this.wldw;
	}
	public void setWldw(String wldw) {
	    this.wldw=wldw;
	}
	public Double getWlsl() {
	    return this.wlsl;
	}
	public void setWlsl(Double wlsl) {
	    this.wlsl=wlsl;
	}
	public String getJsyq() {
	    return this.jsyq;
	}
	public void setJsyq(String jsyq) {
	    this.jsyq=jsyq;
	}
	public java.sql.Date getJhrq_xq() {
	    return this.jhrq_xq;
	}
	public void setJhrq_xq(java.sql.Date jhrq_xq) {
	    this.jhrq_xq=jhrq_xq;
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
	public Integer getQd() {
	    return this.qd;
	}
	public void setQd(Integer qd) {
	    this.qd=qd;
	}
	public String getQdBy() {
		return this.qdBy;
	}
	public void setQdBy(String qdBy) {
		this.qdBy = qdBy;
	}

	public java.sql.Timestamp getQdTime() {
		return this.qdTime;
	}
	public void setQdTime(java.sql.Timestamp qdTime) {
		this.qdTime = qdTime;
	}
	public Integer getSp() {
	    return this.sp;
	}
	public void setSp(Integer sp) {
	    this.sp=sp;
	}
	public String getSpBy() {
		return this.spBy;
	}
	public void setSpBy(String spBy) {
		this.spBy = spBy;
	}

	public java.sql.Timestamp getSpTime() {
		return this.spTime;
	}
	public void setSpTime(java.sql.Timestamp spTime) {
		this.spTime = spTime;
	}	
}
