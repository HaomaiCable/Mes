package com.hmmes.model;

import java.util.Date;

public class XsddBgModel extends BaseModel {
	
	
    private Integer id;//   ����
	private Integer ddid;//   ���۶���ID��xsdd.id
	private String bh;//   ���۶�����������
	private Integer row;//    �����ϸ�к�
	private String jhbh;//   ���۶������
	private Integer jhbhrow;//    ���۶�����ϸ�к�
	private String field;//   �����Ŀ
	private String oldContent;//  ���ǰ����
	private String newContent;//   ���������
	private String createBy;//   �����������	
	private java.sql.Timestamp createTime;//  ���������ʱ��
	private Integer checked;//    �������ˣ�1=�����
	private String checkBy;//   �����	
	private java.sql.Timestamp checkTime;//   ���ʱ��
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
	public Integer getDdid() {
		return this.ddid;
	}
	public void setDdid(Integer ddid) {
		this.ddid = ddid;
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
	public Integer getChecked() {
		return this.checked;
	}
	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	public String getCheckBy() {
		return this.checkBy;
	}
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	public java.sql.Timestamp getCheckTime() {
		return this.checkTime;
	}
	public void setCheckTime(java.sql.Timestamp checkTime) {
		this.checkTime = checkTime;
	}

	
}
