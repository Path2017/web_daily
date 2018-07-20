package com.e3expo.e3.model.view;

import java.io.Serializable;

public class PagedData<T> implements Serializable{
	private T data;
	private Integer totalNum;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
	

}
