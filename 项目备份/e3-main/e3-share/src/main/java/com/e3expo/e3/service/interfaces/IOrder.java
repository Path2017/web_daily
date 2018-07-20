package com.e3expo.e3.service.interfaces;

import com.e3expo.e3.enumration.DesignerPriceConfigEnum;
import com.e3expo.e3.model.OrderDesignerLog;
import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.model.form.UploadDesignForm;
import com.e3expo.e3.model.form.UploadWorkingDrawingForm;
import com.e3expo.e3.model.param.PageParam;
import com.e3expo.e3.model.view.WebAppOrderView;

public interface IOrder {



	/**
	 * 分页查询展览公司正在进行中的订单
	 */
	PageParam<WebAppOrderView> pageQueryOnGoingOrder(PageParam<WebAppOrderView> page, Integer userId);


	/**
	 * 结束竞标
	 * @param orderId orderID
	 * @param userId  userID
	 */
    void endBidding(Integer orderId, Integer userId);

	/**
	 * 付款成功
	 * @param type    付款的类型
	 * @param orderId orderId
	 * @param userId  这个是什么用户？？？？
	 */
    void moneyPaid(DesignerPriceConfigEnum type, Integer orderId, Integer userId);

	/**
	 * 上传设计稿
	 *
	 * @param form
	 */
	void uploadDesign(UploadDesignForm form);

	/**
	 * 申请修改设计稿
	 *
	 * @param orderDesignerLog
	 */
	void applyForModifyingDesign(OrderDesignerLog orderDesignerLog);

	/**
	 * 确认收货
	 *
	 * @param orderId
	 * @param userId
	 */
    void confirmDesign(Integer orderId, Integer userId);

	/**
	 * 分页查询所有订单
	 *
	 * @param page
	 * @param id
	 * @return
	 */
	PageParam<WebAppOrderView> pageQueryAllOrder(PageParam<WebAppOrderView> page, Integer id);


	/**
	 * 分页查询已完成的订单
	 *
	 * @param page
	 * @param userId
	 * @return
	 */
	PageParam<WebAppOrderView> pageQueryCompletedOrders(PageParam<WebAppOrderView> page, Integer userId);

	/**
	 * 分页查询保存的Rfp
	 *
	 * @param page
	 * @param userId
	 * @return
	 */
	PageParam<Rfp> pageQuerySavedRfp(PageParam<Rfp> page, Integer userId);

	/**
	 * 施工图确认收货
	 * @param userId  参展商ID
	 * @param form 订单ID
     */
	void confirmWorkingDrawing(Integer userId, UploadWorkingDrawingForm form);

	/**
	 * 查询订单的详情
	 *
	 * @param orderId
	 * @return
	 */
    WebAppOrderView getOrderDetail(Integer orderId);

	/**
	 * 设计师上传working drawing
	 * @param form
	 */
	void uploadWorkingDrawing(UploadWorkingDrawingForm form);

	/**
	 * 分页查询抢单中心列表
	 * @param page
	 * @param userId
	 * @return
	 */
	PageParam<WebAppOrderView> pageQueryGrabOrderList(PageParam<WebAppOrderView> page, Integer userId);

	/**
	 * 分页查询设计师所有订单列表
	 *
	 * @param page
	 * @param designerId
	 * @return
	 */
	PageParam<WebAppOrderView> pageQueryDesignerAllOrderList(PageParam<WebAppOrderView> page, Integer designerId);

	/**
	 * 分页查询完成的订单列表
	 *
	 * @param page
	 * @param designerId
	 * @return
	 */
	PageParam<WebAppOrderView> pageQueryDesignerCompletedOrderList(PageParam<WebAppOrderView> page, Integer designerId);

	/**
	 * 分页查询设计师正在进行的订单
	 * @param page
	 * @param designerId
	 * @return
	 */
    PageParam<WebAppOrderView> pageQueryDesignerOngoingOrderList(PageParam<WebAppOrderView> page, Integer designerId);
}
