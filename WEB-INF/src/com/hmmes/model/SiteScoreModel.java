package com.hmmes.model;

public class SiteScoreModel extends BaseModel {
	

		private Integer siteId;//   վ��id ����site_main.id	private Integer viewNum;//   �������	private Integer likeNum;//   ϲ������	private Integer shareNum;//   �������	private Integer clickNum;//   �������	private Integer collectNum;//   �ղ�����	private Integer commentNum;//   ��������	public Integer getSiteId() {	    return this.siteId;	}	public void setSiteId(Integer siteId) {	    this.siteId=siteId;	}	public Integer getViewNum() {	    return this.viewNum;	}	public void setViewNum(Integer viewNum) {	    this.viewNum=viewNum;	}	public Integer getLikeNum() {	    return this.likeNum;	}	public void setLikeNum(Integer likeNum) {	    this.likeNum=likeNum;	}	public Integer getShareNum() {	    return this.shareNum;	}	public void setShareNum(Integer shareNum) {	    this.shareNum=shareNum;	}	public Integer getClickNum() {	    return this.clickNum;	}	public void setClickNum(Integer clickNum) {	    this.clickNum=clickNum;	}	public Integer getCollectNum() {	    return this.collectNum;	}	public void setCollectNum(Integer collectNum) {	    this.collectNum=collectNum;	}	public Integer getCommentNum() {	    return this.commentNum;	}	public void setCommentNum(Integer commentNum) {	    this.commentNum=commentNum;	}
	
}
