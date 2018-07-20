package com.e3expo.e3.model;

import org.apache.ibatis.type.Alias;

/**
 * 设计师
 * @author luning
 *
 */
@Alias("designerModel")
public class DesignerModel extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1412253397009877491L;
	
	//用户ID
	private Integer userId;
	
	//设计师最大可接单数量
	private Integer totalNum;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
	
	
}
