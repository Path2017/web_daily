
package com.e3expo.e3.model.form;


import com.e3expo.e3.enumration.OrderEnum;
import com.e3expo.e3.model.param.IPagingParam;
import com.e3expo.e3.model.param.ISortParam;

/**
 * 分页和排序参数抽象类
 * @author luning
 */
public class AbstractPagingSortParam implements IPagingParam, ISortParam {
	
	/**
	 * 记录起始位置
	 */
	private int offset; 
	
	/**
	 * 返回记录数
	 */
	private int limit=10;
	
	/**
	 * 排序字段名称
	 */
	private String sortby;
	
	/**
	 * 排序顺序
	 */
	private OrderEnum order;
	
	@Override
	public void setSortby(String fieldName) {
		this.sortby = fieldName;
	}

	@Override
	public String getSortby() {
		return sortby;
	}

	@Override
	public void setOrder(OrderEnum order) {
		this.order = order;
	}

	@Override
	public OrderEnum getOrder() {
		return order;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public int getLimit() {
		return limit;
	}

	@Override
	public void setLimit(int limit) {
		this.limit = limit;
	}
	


}
