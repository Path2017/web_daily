package com.e3expo.e3.model.form;

import java.io.Serializable;

public abstract class AbstractPage implements Serializable {
	/**
	 * 分页插件传过来的页码
	 */
	private int pageIndex=1;
	/**
	 * 返回的对应条件下的总条数
	 */
	private int total;
	/**
	 * 返回的分页数据
	 */
	private Object data;
	/**
	 * 偏移量
	 */
	private int offset;
	/**
	 * 每页的数据量大小
	 */
	private int limit = 10;
	/**
	 * 排序
	 */
	private String sortby;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getOffset() {
		return this.getLimit() * (this.getPageIndex() - 1);
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getSortby() {
		return sortby;
	}

	public void setSortby(String sortby) {
		this.sortby = sortby;
	}

}
