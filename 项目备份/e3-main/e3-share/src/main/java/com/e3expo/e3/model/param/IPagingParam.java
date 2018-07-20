/**
 * 
 */
package com.e3expo.e3.model.param;

/**
 * 分页参数接口
 * @author Administrator
 */
public interface IPagingParam {
	
	/**
	 * 返回记录起始位置
	 * @return
	 */
	int getOffset();
	
	/**
	 * 设置分页其实位置
	 * @param offset 分页起始位置
	 * @return
	 */
	void setOffset(int offset);
	
	/**
	 * 返回记录数
	 * @return
	 */
	int getLimit();
	
	/**
	 * 设置返回记录数
	 * @param limit
	 * @return
	 */
	void setLimit(int limit);
}
