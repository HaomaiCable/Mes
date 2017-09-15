package com.hmmes.bean;

import java.util.List;
import java.sql.Timestamp;
import java.sql.Date;

public class JtjhBean extends BaseBean {
	
	private Integer id;//   ����	
	private Integer row;//    ��̨�ƻ�������˳��ţ�������
	private Integer ddid;//    ���۶�������ID xsdd.id
	private java.sql.Date xdrq;//   ��̨�ƻ��´�����
	private String jhbh;//   ��̨�ƻ����
	private Integer state;//    ״̬��2=��ͣ��3=���ϣ�1=����
	private String sbmc;//   ������豸����
	private String sbmcdek;//   ��ʱ�������ҵ��豸����
	private String iszl;//   ��=���ߣ���=����
	private String gxxh;//   ����Ĳ�Ʒ�ͺ�
	private String gxxh_o;//   ����������ϸ��Ĳ�Ʒ�ͺţ����ڲ��ҡ����㹤ʱ
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
    private String gd;//��������
	private Integer dtts;//    ��������������

	//�깤�㱨
	private List<JtjhWghbBean> wghbs;
	//�ƻ����
	private List<JtjhBgBean> bgs;

    private java.sql.Date maxWgrq;//����깤���ڣ������ݿ��ֶ��޹� 
    private Double sumWgsl;//�ϼ��깤����,�����ݿ��ֶ��޹�
    private Double wwgsl;//��δ�깤����,�����ݿ��ֶ��޹أ�=jhsl-sumWgsl
    private String sumWgslds;//�ֶε��깤����,�ַ��ͣ������ݿ��ֶ��޹�
	private Integer qbWg;//�Ƿ�ȫ����ɣ�1=�ƻ���ȫ�����,�����ݿ��ֶ��޹�
	private long cqts;//��������,�����ݿ��ֶ��޹�
	
	//�����깤�㱨��,��jtjh���ݿ��޹�
	private java.sql.Date wgrq;//   �깤����,�����ݿ��ֶ��޹�
	private Integer bc;//   ��Σ�1=�װ࣬2==ҹ�ࡣ�����ݿ��ֶ��޹�
	private Double wgsl;//   �깤����,�����ݿ��ֶ��޹�
	private String wgsm;//   �깤���������ԣ��ַ���,�����ݿ��ֶ��޹�
	private Integer wg;//   ȫ���깤��1=ȫ���깤�����ƻ��깤������Ϊ�п��ȵ�������С�ڼƻ�����ʱ����д1,,�����ݿ��ֶ��޹�


	private int subCountWg;//�깤�㱨��¼��,�����ݿ��ֶ��޹�
    private int subCountBg;//�����¼��,�����ݿ��ֶ��޹�

/**
    public JtjhBean() {
 
    }
 
    public JtjhBean(Integer id, Integer row, Integer ddid, Date xdrq,String jhbh,Integer state,String sbmc,String sbmcdek
		,String iszl,String gxxh,String gxxh_o,String gxgg,	String gxgg_o,String gxdy,String gxdy_o,String gxgy,String gxdw,String gxjsyq
		,String gxph,String gxlb,Double jhsl,Double jhsl_o,String  jhsl_xs,String  wgflag,java.sql.Date jhrq,String createBy
		,java.sql.Timestamp createTime,String updateBy,java.sql.Timestamp updateTime,Integer deleted,String gd,Integer dtts
		,java.sql.Date maxWgrq,Double sumWgsl,Double wwgsl,String sumWgslds,Integer qbWg,long cqts) {
        super();
        this.id = id;
        this.row = row;
        this.ddid = ddid;
        this.xdrq = xdrq;
        this.jhbh = jhbh;
        this.state = state;
        this.sbmc = sbmc;
        this.sbmcdek = sbmcdek;
        this.iszl = iszl;
        this.gxxh = gxxh;
        this.gxxh_o = gxxh_o;
        this.gxgg = gxgg;
        this.gxgg_o = gxgg_o;
        this.gxdy = gxdy;
        this.gxdy_o = gxdy_o;
        this.gxgy = gxgy;
        this.gxdw = gxdw;
        this.gxjsyq = gxjsyq;
        this.gxph = gxph;
        this.gxlb = gxlb;
        this.jhsl = jhsl;
        this.jhsl_o = jhsl_o;
        this.jhsl_xs = jhsl_xs;
        this.wgflag = wgflag;
        this.jhrq = jhrq;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.gd = gd;
        this.dtts = dtts;
        this.maxWgrq = maxWgrq;
        this.sumWgsl = sumWgsl;
        this.wwgsl = wwgsl;
        this.sumWgslds = sumWgslds;
        this.qbWg = qbWg;
        this.cqts = cqts;

    }
*/
	public List<JtjhWghbBean> getWghbs() {
		return this.wghbs;
	}
	public void setWghbs(List<JtjhWghbBean> wghbs) {
		this.wghbs = wghbs;
	}
	public List<JtjhBgBean> getBgs() {
		return this.bgs;
	}
	public void setBgs(List<JtjhBgBean> bgs) {
		this.bgs = bgs;
	}	

	public java.sql.Date getMaxWgrq() {
		return this.maxWgrq;
	}
	public void setMaxWgrq(java.sql.Date maxWgrq) {
		this.maxWgrq = maxWgrq;
	}
	public Double getSumWgsl() {
	    return this.sumWgsl;
	}
	public void setSumWgsl(Double sumWgsl) {
	    this.sumWgsl=sumWgsl;
	}
	public Double getWwgsl() {
	    return this.wwgsl;
	}
	public void setWwgsl(Double wwgsl) {
	    this.wwgsl=wwgsl;
	}
	public String getSumWgslds() {
		return this.sumWgslds;
	}
	public void setSumWgslds(String sumWgslds) {
		this.sumWgslds = sumWgslds;
	}

	public Integer getQbWg() {
		return this.qbWg;
	}
	public void setQbWg(Integer qbWg) {
		this.qbWg = qbWg;
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
	public Integer getBc() {
	    return this.bc;
	}
	public void setBc(Integer bc) {
	    this.bc=bc;
	}	
	public Double getWgsl() {
	    return this.wgsl;
	}
	public void setWgsl(Double wgsl) {
	    this.wgsl=wgsl;
	}
	public String getWgsm() {
	    return this.wgsm;
	}
	public void setWgsm(String wgsm) {
	    this.wgsm=wgsm;
	}

	public Integer getWg() {
	    return this.wg;
	}
	public void setWg(Integer wg) {
	    this.wg=wg;
	}	

	public int getSubCountWg() {
		return this.subCountWg;
	}
	public void setSubCountWg(int subCountWg) {
		this.subCountWg = subCountWg;
	}	
	
	public int getSubCountBg() {
		return this.subCountBg;
	}
	public void setSubCountBg(int subCountBg) {
		this.subCountBg = subCountBg;
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
