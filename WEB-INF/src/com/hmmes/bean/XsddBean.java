package com.hmmes.bean;

import java.util.List;
import java.util.Date;


public class XsddBean extends BaseBean {
	
	private Integer id;//   ����	
	private java.sql.Date xdrq;//   �ƻ��´�����
	private String jhbh;//   �ƻ����
	private String ywy;//   ҵ��Ա	
	private Integer row;//    �кţ�������ϸ�к�
	private Integer state;//    ״̬��2=��ͣ��3=���ϣ�1=����
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

	private String deleteFlag; //ɾ����ǣ������ݿ��ֶ��޹� 1=ɾ��,������ɾ��

    private int subCountWg;//�깤�㱨��¼��,�����ݿ��ֶ��޹�
    private int subCountRk;//���㱨��¼��,�����ݿ��ֶ��޹�
    private int subCountBg;//�����¼��,�����ݿ��ֶ��޹�
    private int subCountJtjh;//��̨�ƻ���¼��,�����ݿ��ֶ��޹�

	//�����������깤�㱨xsdd_wghb

    private Double sumWgsl;//�ϼ��깤����,�����ݿ��ֶ��޹�
    private String sumWgslds;//�ֶε��깤����,�ַ��ͣ������ݿ��ֶ��޹�
    private String sumXpgg;//��ʹ�õ����̻���,�ַ��ͣ������ݿ��ֶ��޹�
    private Double sumCmsl;//�ϼƳ��׸�ȥ����,�����ݿ��ֶ��޹�

    //����ͳ�����㱨xsdd_rkhb
    private java.sql.Date maxRkrq;//���������ڣ������ݿ��ֶ��޹�
	private Double sumRksl;//�ϼ��������,�����ݿ��ֶ��޹�
    private String sumRkslds;//�ֶε������ϸ,�ַ��ͣ������ݿ��ֶ��޹�
    private Integer qbRk;//�Ƿ�ȫ����⣬1=�ƻ���ȫ��������,2==δ�깤,�Ѽ��뵽���ݿ�(xsdd)��
    private long cqts;//��������,�����ݿ��ֶ��޹�

	private java.sql.Date wgrq;//   �깤���ڣ������ݿ��ֶ��޹�
	private Double wgsl;//   �깤�����������ݿ��ֶ��޹�
	private String wgslss;//   ��ע��˵���������ݿ��ֶ��޹�
	private String czg;//   �깤��̨���֣������ݿ��ֶ��޹�
	private String xpgg;//   ���̹�������ݿ��ֶ��޹�
	private Double cmsl;//   ���׸�ȥ�����������ݿ��ֶ��޹�
	
	//�������깤�㱨
	private List<XsddWghbBean> wghbs;
	//ͳ�����㱨
	private List<XsddRkhbBean> rkhbs;
	//�������
	private List<XsddBgBean> bgs;


	//������	
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
	public Double getSumWgsl() {
	    return this.sumWgsl;
	}
	public void setSumWgsl(Double sumWgsl) {
	    this.sumWgsl=sumWgsl;
	}
	public String getSumWgslds() {
		return this.sumWgslds;
	}
	public void setSumWgslds(String sumWgslds) {
		this.sumWgslds = sumWgslds;
	}
	public void setSumXpgg(String sumXpgg) {
		this.sumXpgg = sumXpgg;
	}
	public String getSumXpgg() {
		return this.sumXpgg;
	}
	public Double getSumCmsl() {
	    return this.sumCmsl;
	}
	public void setSumCmsl(Double sumCmsl) {
	    this.sumCmsl=sumCmsl;
	}

	//ͳ��
	public java.sql.Date getMaxRkrq() {
		return this.maxRkrq;
	}
	public void setMaxRkrq(java.sql.Date maxRkrq) {
		this.maxRkrq = maxRkrq;
	}
	public Double getSumRksl() {
	    return this.sumRksl;
	}
	public void setSumRksl(Double sumRksl) {
	    this.sumRksl=sumRksl;
	}
	public String getSumRkslds() {
		return this.sumRkslds;
	}
	public void setSumRkslds(String sumRkslds) {
		this.sumRkslds = sumRkslds;
	}
	public Integer getQbRk() {
		return this.qbRk;
	}
	public void setQbRk(Integer qbRk) {
		this.qbRk = qbRk;
	}
	public long getCqts() {
		return this.cqts;
	}
	public void setCqts(long cqts) {
		this.cqts = cqts;
	}

	public java.sql.Date getWgrq() {
	    return this.wgrq;
	}
	public void setWgrq(java.sql.Date wgrq) {
	    this.wgrq=wgrq;
	}
	public Double getWgsl() {
	    return this.wgsl;
	}
	public void setWgsl(Double wgsl) {
	    this.wgsl=wgsl;
	}
	public String getWgslss() {
	    return this.wgslss;
	}
	public void setWgslss(String wgslss) {
	    this.wgslss=wgslss;
	}

	public String getCzg() {
	    return this.czg;
	}
	public void setCzg(String czg) {
	    this.czg=czg;
	}
	public String getXpgg() {
	    return this.xpgg;
	}
	public void setXpgg(String xpgg) {
	    this.xpgg=xpgg;
	}
	public Double getCmsl() {
	    return this.cmsl;
	}
	public void setCmsl(Double cmsl) {
	    this.cmsl=cmsl;
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
	public int getSubCountWg() {
		return this.subCountWg;
	}
	public void setSubCountWg(int subCountWg) {
		this.subCountWg = subCountWg;
	}	
	public int getSubCountRk() {
		return this.subCountRk;
	}
	public void setSubCountRk(int subCountRk) {
		this.subCountRk = subCountRk;
	}
	public int getSubCountBg() {
		return this.subCountBg;
	}
	public void setSubCountBg(int subCountBg) {
		this.subCountBg = subCountBg;
	}
	public int getSubCountJtjh() {
		return this.subCountJtjh;
	}
	public void setSubCountJtjh(int subCountJtjh) {
		this.subCountJtjh = subCountJtjh;
	}

				
	public List<XsddWghbBean> getWghbs() {
		return this.wghbs;
	}
	public void setWghbs(List<XsddWghbBean> wghbs) {
		this.wghbs = wghbs;
	}	
	public List<XsddRkhbBean> getRkhbs() {
		return this.rkhbs;
	}
	public void setRkhbs(List<XsddRkhbBean> rkhbs) {
		this.rkhbs = rkhbs;
	}	
	public List<XsddBgBean> getBgs() {
		return this.bgs;
	}
	public void setBgs(List<XsddBgBean> bgs) {
		this.bgs = bgs;
	}	


}
