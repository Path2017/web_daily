package com.e3expo.e3.model.param;

import com.e3expo.e3.enumration.OrderEnum;

/**
 * @author Administrator
 *
 */
public interface ISortParam {
	
	/**
	 * 设置排序字段名称
	 * @param fieldName
	 */
	void setSortby(String fieldName);
	
	/**
	 * 获取排序字段名称
	 * @return
	 */
	String getSortby();
	
	/**
	 * 设置排序顺序
	 * @param order
	 */
	void setOrder(OrderEnum order);
	
	/**
	 * 返回排序顺序
	 * @return
	 */
	OrderEnum getOrder();
}
