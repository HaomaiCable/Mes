package com.hmmes.model;

import java.util.Date;

public class TbxxModel extends BaseModel {
	
    private Integer id;//   ����
	private Integer row;//    �кţ��б�������ϸ�к�
	private String ggbh;//   �б깫����
	private Integer gysId;//    ��Ӧ��ID
	private Integer state;//    ״̬��2=��ͣ��3=���ϣ�1=����
	private Integer deleted;//   �Ƿ�ɾ��,0=δɾ����1=��ɾ��

	private String wlfl;//   �ɹ����ʷ���	
	private String wlmc;//   �ɹ���������
	private String wlgg;//   ���Ϲ��
	private String wldw;//   ������λ
	private Double wlsl;//   ��ɹ�����
	private String jsyq;//   �ɹ�����Ҫ��
	private java.sql.Date jhrq_xq;//   ����Ľ�������
	private Double tbsl;//   Ͷ��(�б�)����
	private Double dj;//   Ͷ�굥��
	private Double je;//   ���
	private java.sql.Date jhrq;//   ��Ӧ�̳�ŵ��������
	private String tbsm;//   Ͷ��˵������Ϣ

	private String createBy;//   ������	
	private java.sql.Timestamp createTime;//   ����ʱ��
	private String updateBy;//   �޸���	
	private java.sql.Timestamp updateTime;//   �޸�ʱ��
	private Integer zb;//    �б��ǣ�1==�б�



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
	public String getGgbh() {
		return this.ggbh;
	}
	public void setGgbh(String ggbh) {
		this.ggbh = ggbh;
	}

	public Integer getGysId() {
	    return this.gysId;
	}
	public void setGysId(Integer gysId) {
	    this.gysId=gysId;
	}


	public Integer getState() {
	    return this.state;
	}
	public void setState(Integer state) {
	    this.state=state;
	}

	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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
	public Double getTbsl() {
	    return this.tbsl;
	}
	public void setTbsl(Double tbsl) {
	    this.tbsl=tbsl;
	}
	public Double getDj() {
	    return this.dj;
	}
	public void setDj(Double dj) {
	    this.dj=dj;
	}
	public Double getJe() {
	    return this.je;
	}
	public void setJe(Double je) {
	    this.je=je;
	}
	public java.sql.Date getJhrq() {
	    return this.jhrq;
	}
	public void setJhrq(java.sql.Date jhrq) {
	    this.jhrq=jhrq;
	}
	public String getTbsm() {
	    return this.tbsm;
	}
	public void setTbsm(String tbsm) {
	    this.tbsm=tbsm;
	}
	public Integer getZb() {
		return this.zb;
	}
	public void setZb(Integer zb) {
		this.zb = zb;
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



	
}
