package com.hmmes.bean;

import java.util.List;

public class GzTaskBean extends BaseBean {
	
	private Integer id;//   ����	
	private java.sql.Date rq;//   ���������´�����
	private String tcr;//   �������������
	private Integer state;//    ״̬��2=��ͣ��3=���ϣ�1=����
	private String ly;//   ����������Դ
	private String cyry;//   �������������Ա
	private String rwName;//  ������������
	private String rwContent;//   ����������ϸ����
	private String rwResult;//   ����������
	private java.sql.Date wcrq_yq;//   ��������Ҫ���������
	private java.sql.Date wcrq; //��������ʵ���������
	private String wcResult;//   ��ɽ��
	private String createBy; //   ������	
	private java.sql.Timestamp createTime; //   ����ʱ��
	private Integer report;//    �㱨��2==δ�㱨��1==�ѻ㱨
	private java.sql.Timestamp reportTime;//   �㱨 ʱ��
	private Integer accept;//    ȷ�ϣ�2==δȷ�ϣ�1==��ȷ��
	private String acceptBy;//   ȷ����	
	private java.sql.Timestamp acceptTime;//   ȷ��ʱ��	

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.sql.Date getRq() {
	    return this.rq;
	}
	public void setRq(java.sql.Date rq) {
	    this.rq=rq;
	}
	public Integer getState() {
	    return this.state;
	}
	public void setState(Integer state) {
	    this.state=state;
	}


	public String getTcr() {
		return this.tcr;
	}
	public void setTcr(String tcr) {
		this.tcr = tcr;
	}

	public String getLy() {
		return this.ly;
	}
	public void setLy(String ly) {
		this.ly = ly;
	}
	public String getCyry() {
		return this.cyry;
	}
	public void setCyry(String cyry) {
		this.cyry = cyry;
	}
	public String getRwName() {
		return this.rwName;
	}
	public void setRwName(String rwName) {
		this.rwName = rwName;
	}
	public String getRwContent() {
		return this.rwContent;
	}
	public void setRwContent(String rwContent) {
		this.rwContent = rwContent;
	}
	public String getRwResult() {
		return this.rwResult;
	}
	public void setRwResult(String rwResult) {
		this.rwResult = rwResult;
	}

	public java.sql.Date getWcrq_yq() {
	    return this.wcrq_yq;
	}
	public void setWcrq_yq(java.sql.Date wcrq_yq) {
	    this.wcrq_yq=wcrq_yq;
	}
	public java.sql.Date getWcrq() {
	    return this.wcrq;
	}
	public void setWcrq(java.sql.Date wcrq) {
	    this.wcrq=wcrq;
	}
	public String getWcResult() {
		return this.wcResult;
	}
	public void setWcResult(String wcResult) {
		this.wcResult = wcResult;
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
	public Integer getReport() {
	    return this.report;
	}
	public void setReport(Integer report) {
	    this.report=report;
	}


	public java.sql.Timestamp getReportTime() {
		return this.reportTime;
	}
	public void setReportTime(java.sql.Timestamp reportTime) {
		this.reportTime = reportTime;
	}
	public Integer getAccept() {
	    return this.accept;
	}
	public void setAccept(Integer accept) {
	    this.accept=accept;
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
