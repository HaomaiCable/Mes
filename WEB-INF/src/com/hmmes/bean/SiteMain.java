package com.hmmes.bean;

import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * @author luozejun
 *
 */
public class SiteMain extends BaseBean{

	private Integer id;// ����
	private String name;// NULL��վ���
	private String domain;//���� ����http://
	private String  link;//��ַ���� ��http://
	private Integer state;//״̬ 0���� 1���� 2�����
	private Timestamp createTime;//����ʱ��
	private Timestamp updateTime;//�޸�ʱ��
	private Integer rank = 1;//NULL����
	private String pic;//ͼƬURL
	private Integer deleted;//ɾ��״̬ 0=δɾ�� 1=ɾ��
	 
	private String types ; //վ�����ͣ� ��","����
	
	private int[] typeIds = {}; //����id 
	
	
	
	public int[] getTypeIds() {
		return typeIds;
	}
	public void setTypeIds(int[] typeIds) {
		this.typeIds = typeIds;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	
	
	
}
