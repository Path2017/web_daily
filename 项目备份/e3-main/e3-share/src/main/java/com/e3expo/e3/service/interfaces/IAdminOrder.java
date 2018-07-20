package com.e3expo.e3.service.interfaces;

import java.util.List;

import com.e3expo.e3.model.Order;
import com.e3expo.e3.model.form.OrderAdminParam;
import com.e3expo.e3.model.view.OrderDetailView;
import com.e3expo.e3.model.view.OrderView;
import com.e3expo.e3.model.view.PagedData;

/**
 * 后台订单管理
 * 
 * @author lizy
 *
 */
public interface IAdminOrder {
	/**
	 * 后台管理-订单列表
	 * 
	 * @param param
	 * @return
	 */
	OrderAdminParam getPagedOrderByAdmin(OrderAdminParam param);

	/**
	 * 获取订单的所有详情
	 * 
	 * @param orderId
	 * @return
	 */
	OrderDetailView getOrderDetailView(Integer orderId);
	/**
	 * 更新订单
	 * @param record 订单记录
	 */

	void updateOrder(Order record);
}
