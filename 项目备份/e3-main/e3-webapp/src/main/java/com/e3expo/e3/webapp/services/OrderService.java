package com.e3expo.e3.webapp.services;

import com.e3expo.e3.enumration.DesignerPriceConfigEnum;
import com.e3expo.e3.model.OrderDesignerLog;
import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.model.form.UploadDesignForm;
import com.e3expo.e3.model.form.UploadWorkingDrawingForm;
import com.e3expo.e3.model.param.PageParam;
import com.e3expo.e3.model.view.WebAppOrderView;
import com.e3expo.e3.service.interfaces.IOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private IOrder iOrder;


    /**
     * 结束竞标
     *
     * @param orderId
     * @param userId
     */
    public void endBidding(Integer orderId, Integer userId) {
        iOrder.endBidding(orderId, userId);
    }

    /**
     * 设计稿付款成功
     *
     * @param orderId
     * @param userId
     */
    public void payForDesignCreation(Integer orderId, Integer userId) {
        iOrder.moneyPaid(DesignerPriceConfigEnum.DESIGN_PRICE, orderId, userId);
    }

    /**
     * 修改设计稿付款成功
     *
     * @param orderId
     * @param userId
     */
    public void payForUpdatingDesign(Integer orderId, Integer userId) {
        iOrder.moneyPaid(DesignerPriceConfigEnum.MODIFY_DESIGN_PRICE, orderId, userId);
    }

    /**
     * 上传设计图
     *
     * @param form
     */
    public void uploadDesign(UploadDesignForm form) {
        iOrder.uploadDesign(form);
    }


    /**
     * 申请修改设计稿
     *  @param orderDesignerLog
     *
     */
    public void applyForModifyingDesign(OrderDesignerLog orderDesignerLog) {
        iOrder.applyForModifyingDesign(orderDesignerLog);
    }

    /**
     * 设计稿确认收货
     *
     * @param orderId
     * @param userId
     */
    public void confirmDesign(Integer orderId, Integer userId) {
        iOrder.confirmDesign(orderId, userId);
    }

    /**
     * 施工图付款成功
     *
     * @param orderId
     * @param userId
     */
    public void payForWorkingDrawingCreation(Integer orderId, Integer userId) {
        //
        iOrder.moneyPaid(DesignerPriceConfigEnum.WORKING_DRAWING_PRICE, orderId, userId);
    }

    /**
     * 上传施工图
     *
     * @param form
     */
    public void uploadWorkingDrawing(UploadWorkingDrawingForm form) {
        iOrder.uploadWorkingDrawing(form);
    }

    /**
     * 分页查询正在执行的订单
     *
     * @param page
     * @param userId
     */
    public PageParam<WebAppOrderView> pageQueryOngoingOrders(PageParam<WebAppOrderView> page, Integer userId) {
        return iOrder.pageQueryOnGoingOrder(page, userId);
    }

    /**
     * 分页查询所有订单
     *
     * @param page
     * @param userId
     * @return
     */
    public PageParam<WebAppOrderView> pageQueryAllOrders(PageParam<WebAppOrderView> page, Integer userId) {
        return iOrder.pageQueryAllOrder(page, userId);
    }

    /**
     * 分页查询已完成的订单
     *
     * @param page
     * @param userId
     * @return
     */
    public PageParam<WebAppOrderView> pageQueryCompletedOrders(PageParam<WebAppOrderView> page, Integer userId) {
        return iOrder.pageQueryCompletedOrders(page, userId);
    }

    /**
     * 分页查询未发布的需求
     *
     * @param page
     * @param userId
     * @return
     */
    public PageParam<Rfp> pageQuerySavedRfp(PageParam<Rfp> page, Integer userId) {
        return iOrder.pageQuerySavedRfp(page, userId);
    }

    /**
     * 参展商确认收稿施工图
     *
     * @param userId
     * @param form
     */
    public void confirmWorkingDrawing(Integer userId, UploadWorkingDrawingForm form) {
        iOrder.confirmWorkingDrawing(userId, form);
    }

    /**
     * 查询订单的详情
     *
     * @param orderId
     * @return
     */
    public WebAppOrderView getOrderDetail(Integer orderId) {
        return iOrder.getOrderDetail(orderId);
    }

    /**
     * 分页查询抢单中心订单列表
     *
     * @param page
     * @param designerId
     * @return
     */
    public PageParam<WebAppOrderView> pageQueryGrabOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
        return iOrder.pageQueryGrabOrderList(page, designerId);
    }
    /**
     * 分页查询抢单中心
     *
     * @param page
     * @param designerId
     * @return
     */
    public PageParam<WebAppOrderView> pageQueryAllOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
        return iOrder.pageQueryDesignerAllOrderList(page, designerId);
    }

    /**
     * 分页查询已完成的订单
     *
     * @param page
     * @param designerId
     * @return
     */
    public PageParam<WebAppOrderView> pageQueryCompletedOrderList(PageParam<WebAppOrderView> page, Integer designerId) {

        return iOrder.pageQueryDesignerCompletedOrderList(page, designerId);
    }

    public PageParam<WebAppOrderView> pageQueryDesignerOngoingOrderList(PageParam<WebAppOrderView> page, Integer designerId) {

        return iOrder.pageQueryDesignerOngoingOrderList(page, designerId);
    }
}
