package com.hmmes.model;

import java.util.Date;

public class JtjhBgModel extends BaseModel {
	
	
	
private Integer id;//   ����
	
	private Integer jhid;//   ��̨�ƻ�ID��xsdd.id
	private String bh;//   ��̨�ƻ���������
	private Integer row;//    �����ϸ�к�
	private String gd;//  ����豸���ڹ���
	private String sbmc;//  ������豸����
	private String jhbh;//   ��̨�ƻ����
	private Integer jhbhrow;//    ��̨�ƻ���ϸ�к�
	private String field;//   �����Ŀ
	private String oldContent;//  ���ǰ����
	private String newContent;//   ���������
	private String createBy;//   �����������	
	private java.sql.Timestamp createTime;//  ���������ʱ��
	private Integer accept;//    ���������ȷ�ϣ�1=��ȷ��
	private String acceptBy;//   ����ȷ����	
	private java.sql.Timestamp acceptTime;//   ����ȷ��ʱ��

    private Date frombgrq;//   ��ѯ�ã��������ʼ����
    private Date tobgrq;//   ��ѯ�ã��������������

	public Date getFrombgrq() {
	    return this.frombgrq;
	}
	public void setFrombgrq(Date frombgrq) {
	    this.frombgrq=frombgrq;
	}
	public Date getTobgrq() {
	    return this.tobgrq;
	}
	public void setTobgrq(Date tobgrq) {
	    this.tobgrq=tobgrq;
	}	

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getJhid() {
		return this.jhid;
	}
	public void setJhid(Integer jhid) {
		this.jhid = jhid;
	}

	public String getBh() {
		return this.bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public Integer getRow() {
		return this.row;
	}
	public void setRow(Integer row) {
		this.row = row;
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

	public String getJhbh() {
		return this.jhbh;
	}
	public void setJhbh(String jhbh) {
		this.jhbh = jhbh;
	}

	public Integer getJhbhrow() {
		return this.jhbhrow;
	}
	public void setJhbhrow(Integer jhbhrow) {
		this.jhbhrow = jhbhrow;
	}


	public String getField() {
		return this.field;
	}
	public void setField(String field) {
		this.field =field;
	}


	public String getOldContent() {
	    return this.oldContent;
	}
	public void setOldContent(String oldContent) {
	    this.oldContent=oldContent;
	}
	public String getNewContent() {
	    return this.newContent;
	}
	public void setNewContent(String newContent) {
	    this.newContent=newContent;
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
	public Integer getAccept() {
		return this.accept;
	}
	public void setAccept(Integer accept) {
		this.accept = accept;
	}
	public String getAcceptBy() {
		return this.acceptBy;
	}
	public void setAcceptBy(String acceptBy) {
		this.acceptBy = acceptBy;
	}
	public java.sql.Timestamp getAcceptTime() {
		return this.acceptTime;
	}
	public void setAcceptTime(java.sql.Timestamp acceptTime) {
		this.acceptTime = acceptTime;
	}	
}
