package com.hmmes.utils;

import java.util.ArrayList;

/**
 * <p>
 * Title: WASU Platform
 * </p>
 * <p>
 * Description: ��ҳ������ʵ�֡�
 * </p>
 * <p>
 * Copyright: 2006-2008
 * </p>
 * <p>
 * Company: �㽭��ӯ��ý���޹�˾
 * </p>
 * 
 * @author ·��
 * @version 2.0
 */
// oracle,sqlserver,mysql��ҳ����
public class Pager {

	private int pageId = 1; // ��ǰҳ
	private int rowCount = 0; // ������
	private int pageSize = 10; // ҳ��С
	private int pageCount = 0; // ��ҳ��
	private int pageOffset = 0;// ��ǰҳ��ʼ��¼
	private int pageTail = 0;// ��ǰҳ����ļ�¼
	private String orderField;
	private boolean orderDirection;

	// ҳ����ʾ��ҳ��ť����
	private int length = 6;
	// ��ʼ��ҳ����
	private int startIndex = 0;
	// ������ҳ����
	private int endIndex = 0;

	private int[] indexs;

	// int pid = navigate.getPageId() ; //��ǰҳ
	// int pcount = navigate.getPageCount(); //��ҳ��
	// int length=8; //����ʾ������ҳ����
	// int startIndex = pid - (length/2);
	// int endIndex = 0;
	// if( startIndex < 1){
	// startIndex = 1;
	// }
	// endIndex = (startIndex+length) <= pcount ? (startIndex+length) : pcount;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int[] getIndexs() {
		int len = getEndIndex() - getStartIndex() + 1;
		indexs = new int[len];
		ArrayList a;
		for (int i = 0; i < len; i++) {
			indexs[i] = (getStartIndex() + i);
		}
		return indexs;
	}

	public void setIndexs(int[] indexs) {
		this.indexs = indexs;
	}

	public int getStartIndex() {
		startIndex = pageId - (length / 2);
		if (startIndex < 1) {
			startIndex = 1;
		}
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		if (getStartIndex() < 1) {
			setStartIndex(1);
		}
		endIndex = (getStartIndex() + length) <= getPageCount() ? (getStartIndex() + length)
				: getPageCount();
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public Pager() {
		this.orderDirection = true;
	}

	protected void doPage() {
		this.pageCount = this.rowCount / this.pageSize + 1;
//System.out.println("����ɽ��pageCount"+pageCount);
		// ���ģ��==0������������1�����һ
		if ((this.rowCount % this.pageSize == 0) && pageCount > 1)
			this.pageCount--;

		// //�������Ҳҳ���ţ�pageId��������ҳ������pageId����ΪpageCount;
		// if(this.pageId> this.pageCount)
		// this.pageId = this.pageCount;
		// this.pageOffset=(this.pageId-1)*this.pageSize+1;

		// this.pageTail=this.pageOffset+this.pageSize-1;

		// Mysql �㷨
		this.pageOffset = (this.pageId - 1) * this.pageSize;
		this.pageTail = this.pageOffset + this.pageSize;
		if ((this.pageOffset + this.pageSize) > this.rowCount)
			this.pageTail = this.rowCount;
	}

	public String getOrderCondition() {
		String condition = "";
		if (this.orderField != null && this.orderField.length() != 0) {
			condition = " order by " + orderField
					+ (orderDirection ? " " : " desc ");
		}
		return condition;
	}

	public String getMysqlQueryCondition() {
		String condition = "";
		condition = " limit " + pageOffset + "," + pageSize;
//System.out.println("����ɽ��condition"+condition);
		return condition;
	}

	public void setOrderDirection(boolean orderDirection) {
		this.orderDirection = orderDirection;
	}

	public boolean isOrderDirection() {
		return orderDirection;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public int getPageOffset() {
		return pageOffset;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageTail(int pageTail) {
		this.pageTail = pageTail;
	}

	public int getPageTail() {
		return pageTail;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		this.doPage();
	}

	public int getRowCount() {
		return rowCount;
	}

}