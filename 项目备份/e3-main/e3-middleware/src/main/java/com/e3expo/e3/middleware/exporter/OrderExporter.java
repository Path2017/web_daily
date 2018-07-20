package com.e3expo.e3.middleware.exporter;

import com.e3expo.e3.common.Constants;
import com.e3expo.e3.enumration.DesignerPriceConfigEnum;
import com.e3expo.e3.enumration.EnumOrderStatus;
import com.e3expo.e3.enumration.OrderNodeEnum;
import com.e3expo.e3.enumration.OrderOperationEnum;
import com.e3expo.e3.exceptions.OrderException;
import com.e3expo.e3.middleware.common.RedisUtil;
import com.e3expo.e3.middleware.common.redis.expiremessage.OrderExpireMessageTypeEnum;
import com.e3expo.e3.middleware.dao.RfpDao;
import com.e3expo.e3.middleware.service.DesignerBidService;
import com.e3expo.e3.middleware.service.OrderLogService;
import com.e3expo.e3.middleware.service.OrderService;
import com.e3expo.e3.middleware.service.UploadFileService;
import com.e3expo.e3.model.DesignerBid;
import com.e3expo.e3.model.DesignerOrder;
import com.e3expo.e3.model.Order;
import com.e3expo.e3.model.OrderDesignerLog;
import com.e3expo.e3.model.OrderDesignerPrice;
import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.model.form.UploadDesignForm;
import com.e3expo.e3.model.form.UploadWorkingDrawingForm;
import com.e3expo.e3.model.param.PageParam;
import com.e3expo.e3.model.view.WebAppOrderDesignerLogView;
import com.e3expo.e3.model.view.WebAppOrderDesignerView;
import com.e3expo.e3.model.view.WebAppOrderLogView;
import com.e3expo.e3.model.view.WebAppOrderView;
import com.e3expo.e3.service.interfaces.IOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static com.e3expo.e3.enumration.DesignerPriceConfigEnum.DESIGN_PRICE;
import static com.e3expo.e3.enumration.DesignerPriceConfigEnum.MODIFY_DESIGN_PRICE;
import static com.e3expo.e3.enumration.DesignerPriceConfigEnum.WORKING_DRAWING_PRICE;

@Component
public class OrderExporter implements IOrder {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private RfpDao rfpDao;

    @Autowired
    private DesignerBidService designerBidService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OrderLogService orderLogService;


    @Override
    public PageParam<WebAppOrderView> pageQueryOnGoingOrder(PageParam<WebAppOrderView> page, Integer userId) {
        // 查询正在进行的订单，userId，status = 0
        return orderService.pageQueryOnGoingOrder(page, userId);
    }

    @Override
    public void endBidding(Integer orderId, Integer userId) {
        Order order = orderService.getById(orderId);
        assertOrderNotNull(order);
        if (order.getStatus() == null || EnumOrderStatus.ORDER_NORMAL.getValue() != order.getStatus()) {
            throw new OrderException(OrderException.ErrorCode.ORDER_ILLEGAL_STATUS);
        }
        if (!OrderNodeEnum.BIDDING.getNodeId().equals(order.getNodeId())) {
            throw new OrderException(OrderException.ErrorCode.ORDER_BIDDING_FINISHED);
        }
        Rfp rfp = rfpDao.getById(order.getRfpId());
        if (!userId.equals(rfp.getUserId())) {
            throw new OrderException(OrderException.ErrorCode.ORDER_USER_MISMATCH);
        }
        // 删除redis中的结构
        redisTemplate.opsForHash().delete(Constants.REDIS_HASH_BIDDING_ORDER, orderId.toString());
        redisTemplate.delete(Constants.REDIS_SET_BIDDING_ORDER_LIST_PREFIX + orderId);
        // 查询是否有人投标
        List<DesignerBid> bidList = designerBidService.getDesignerBidsByOrderId(orderId);
        if (bidList == null || bidList.size() == 0) {
            // 无人竞标
            orderService.updateOrderStatusById(orderId, EnumOrderStatus.ORDER_CANCEL);
            orderLogService.insert(orderId, userId, OrderOperationEnum.CANCEL);
        } else {
            // 有人竞标，
            orderService.updateOrderNodeById(orderId, OrderNodeEnum.DESIGN_WAITING_PAY);
            orderLogService.insert(orderId, userId, OrderOperationEnum.WAIT_PAY);
            // 发送定时任务消息
            RedisUtil.sendOrderTaskExpireMessage(redisTemplate, OrderExpireMessageTypeEnum.ORDER_END_PAY_FOR_DESIGN_TASK, orderId);
        }
    }

    @Override
    public void moneyPaid(DesignerPriceConfigEnum type, Integer orderId, Integer designerId) {
        Order order = orderService.getById(orderId);
        switch (type) {
            case DESIGN_PRICE:
                //
                if (!OrderNodeEnum.DESIGN_WAITING_PAY.getNodeId().equals(order.getNodeId())) {
                    throw new OrderException(OrderException.ErrorCode.ORDER_ALREADY_PAID);
                }
                // 设计稿付款
                // 选择报价
                designerBidService.selectBid(orderId, designerId);
                // 插入te_designer_order
                orderService.insertDesignerOrder(designerId, orderId);
                // 修改te_order_designer_price 插入te_order_designer_log
                increaseOrderDesignerPriceNumIfExistAndInsertLog(orderId, DESIGN_PRICE, OrderNodeEnum.DESIGN_WAITING_PAY);
                // 修改order node
                orderService.updateOrderNodeById(orderId, OrderNodeEnum.DESIGN_STAGE_WAIT_UPLOAD);
                // 插入orderLog
                orderLogService.insert(orderId, null, OrderOperationEnum.PAID);
                break;
            case MODIFY_DESIGN_PRICE:
                if (!OrderNodeEnum.DESIGN_WAITING_PAY.getNodeId().equals(order.getNodeId())) {
                    throw new OrderException(OrderException.ErrorCode.ORDER_ALREADY_PAID);
                }
                // 修改设计图付款
                // 修改te_order_designer_price
                increaseOrderDesignerPriceNumIfExistAndInsertLog(orderId, MODIFY_DESIGN_PRICE, OrderNodeEnum.DESIGN_WAITING_PAY);
                // 插入到日志表中
                orderLogService.insert(orderId, null, OrderOperationEnum.PAID);
                break;
            case WORKING_DRAWING_PRICE:
                if (!OrderNodeEnum.WORKING_DRAWING_WAIT_PAY.getNodeId().equals(order.getNodeId())) {
                    throw new OrderException(OrderException.ErrorCode.ORDER_ALREADY_PAID);
                }
                // 施工图付款
                // 修改te_order_designer_price
                increaseOrderDesignerPriceNumIfExistAndInsertLog(orderId, WORKING_DRAWING_PRICE, OrderNodeEnum.WORKING_DRAWING_WAIT_PAY);
                // 修改order 和 node
                updateNodeInOrderAndDesignerOrderTable(orderId, OrderNodeEnum.WORKING_DRAWING_WAIT_UPLOAD);
                // 插入到日志表中
                orderLogService.insert(orderId, null, OrderOperationEnum.PAID);
                break;
            default:
                break;

        }

    }


    /**
     * 上传设计稿
     *
     * @param form
     */
    @Override
    public void uploadDesign(UploadDesignForm form) {
        // 查询order
        Order order = orderService.getById(form.getOrderId());
        assertOrderNotNull(order);
        assertOrderNodeIn(EnumSet.of(OrderNodeEnum.DESIGN_STAGE_WAIT_UPLOAD), order.getNodeId());
        // 查询price
        OrderDesignerPrice price = orderService.selectOrderDesignerPriceByTypeAndOrderId(order.getId(), DesignerPriceConfigEnum.DESIGN_PRICE);
        if (price == null) {
            throw new OrderException(OrderException.ErrorCode.ORDER_DESIGNER_PRICE_NOT_EXIST);
        }
        // 插入orderDesignerLog DESIGN_STAGE_WAIT_UPLOAD
        OrderDesignerLog orderDesignerLog = new OrderDesignerLog(form.getOrderId(),
                OrderNodeEnum.DESIGN_STAGE_WAIT_UPLOAD.getNodeId(),
                form.getDesignerId());
        orderDesignerLog.setType(price.getType());
        orderDesignerLog.setPrice(price.getPrice());
        orderService.insertOrderDesignerLog(orderDesignerLog);
        // 插入uploadFile, 不仅仅是插入，还要讲原来的状态置为删除
        List<Integer> uploadIdList = orderService.selectUploadIdByOrderId(order.getId());
        uploadFileService.deleteUploadFilesByUploadId(uploadIdList);
        uploadFileService.batchInsert(form, orderDesignerLog.getId(), order.getNodeId());
        // 更新状态 te_order,te_designer_order的状态
        updateNodeInOrderAndDesignerOrderTable(form.getOrderId(), OrderNodeEnum.DESIGN_STAGE_WAIT_CONFIRM);
        // 记录日志
        orderLogService.insert(form.getOrderId(), form.getDesignerId(), OrderOperationEnum.UPLOAD_DESIGN);

    }


    /**
     * 申请修改设计稿
     *
     * @param orderDesignerLog
     */
    @Override
    public void applyForModifyingDesign(OrderDesignerLog orderDesignerLog) {
        // 检查是否可以修改
        Integer orderId = orderDesignerLog.getOrderId();
        Order order = orderService.getById(orderId);
        assertOrderNotNull(order);
        OrderDesignerPrice price = orderService.selectOrderDesignerPriceByTypeAndOrderId(orderId, MODIFY_DESIGN_PRICE);
        if (price == null) {
            throw new OrderException(OrderException.ErrorCode.ORDER_DESIGNER_PRICE_NOT_EXIST);
        }
        Integer designerId = price.getDesignerId();
        if (price.getUpdateNum() < 0) {
            // 还在免费中，修改免费次数
            OrderDesignerPrice temp = new OrderDesignerPrice();
            temp.setId(price.getId());
            orderService.increaseOrderDesignerPriceNum(temp);
        } else {
            // 免费次数使用完毕
            Integer updateCount = orderService.selectUpdateDesignCountByOrderId(orderId);
            if (updateCount < Constants.FREE_MODIFY_DESIGN_TIMES) {
                // 修改少于两次
                throw new OrderException(OrderException.ErrorCode.ORDER_ILLEGAL_UPDATE_DESIGN_COUNT);
            }
            if (updateCount - Constants.FREE_MODIFY_DESIGN_TIMES > price.getUpdateNum()) {
                throw new OrderException(OrderException.ErrorCode.ORDER_UPDATE_DESIGN_PRICE_NOT_PAID);
            }

        }
        // 执行申请修改
        // 修改order order_designer的状态
        updateNodeInOrderAndDesignerOrderTable(orderId, OrderNodeEnum.DESIGN_STAGE_WAIT_UPLOAD);
        // 插入order_designer_log 和 order_log DESIGN_STAGE_WAIT_CONFIRM
        OrderDesignerLog applyModifyingLog = new OrderDesignerLog(orderId, OrderNodeEnum.DESIGN_STAGE_WAIT_CONFIRM.getNodeId(), designerId);
        applyModifyingLog.setSuggestion(orderDesignerLog.getSuggestion());
        orderService.insertOrderDesignerLog(applyModifyingLog);
        orderLogService.insert(orderId, order.getUserId(), OrderOperationEnum.APPLY_MODIFY);
    }

    @Override
    public void confirmDesign(Integer orderId, Integer userId) {
        // 确认收货
        // 订单信息状态节点校验。
        Order order = orderService.getById(orderId);
        assertOrderNotNull(order);
        if (!userId.equals(order.getUserId())) {
            // 确认收货的人不是本人
            throw new OrderException(OrderException.ErrorCode.ORDER_USER_MISMATCH);
        }
        if (!OrderNodeEnum.DESIGN_STAGE_WAIT_CONFIRM.getNodeId().equals(order.getNodeId())) {
            throw new OrderException(OrderException.ErrorCode.ORDER_WRONG_NODE_ID);
        }
        // 确认收货
        updateNodeInOrderAndDesignerOrderTable(orderId, OrderNodeEnum.WORKING_DRAWING_WAIT_PAY);
        // 插入order_log
        orderLogService.insert(orderId, order.getUserId(), OrderOperationEnum.COMPLETE);
        // 发送定时任务消息
        RedisUtil.sendOrderTaskExpireMessage(redisTemplate,
                OrderExpireMessageTypeEnum.ORDER_END_PAY_FOR_WORKING_DRAWING_TASK, orderId);
    }

    @Override
    public PageParam<WebAppOrderView> pageQueryAllOrder(PageParam<WebAppOrderView> page, Integer id) {
        return orderService.pageQueryAllOrder(page, id);
    }

    @Override
    public PageParam<WebAppOrderView> pageQueryCompletedOrders(PageParam<WebAppOrderView> page, Integer userId) {

        return orderService.pageQueryCompletedOrders(page, userId);
    }

    @Override
    public PageParam<Rfp> pageQuerySavedRfp(PageParam<Rfp> page, Integer userId) {
        return orderService.pageQuerySavedRfp(page, userId);
    }

    @Override
    public void confirmWorkingDrawing(Integer userId, UploadWorkingDrawingForm form) {
        Integer orderId = form.getOrderId();
        // 施工图确认收货
        Order order = orderService.getById(orderId);
        assertOrderNotNull(order);
        assertOrderNodeIn(EnumSet.of(OrderNodeEnum.WORKING_DRAWING_WAIT_CONFIRM), order.getNodeId());
        // 订单处在待收货的状态，修改为完成，同时修改status
        updateNodeInOrderAndDesignerOrderTable(orderId, OrderNodeEnum.COMPLETED);
        orderService.updateOrderStatusById(orderId, EnumOrderStatus.ORDER_COMPLETE);
        // log
        orderLogService.insert(orderId, userId, OrderOperationEnum.CONFIRM_WORKING_DRAWING);
        orderLogService.insert(orderId, userId, OrderOperationEnum.COMPLETE);

    }

    @Override
    public WebAppOrderView getOrderDetail(Integer orderId) {
        // 订单详情
        Order order = orderService.getById(orderId);
        assertOrderNotNull(order);
        WebAppOrderView orderView = orderService.selectRfpWebViewByRfpId(order.getRfpId());

        OrderNodeEnum node = OrderNodeEnum.getByNodeId(order.getNodeId());
        switch (node) {
            case BIDDING:
            case DESIGN_WAITING_PAY:
                // 需要查询bidding list
                List<WebAppOrderDesignerView> biddingList = orderService.selectBiddingListByOrderId(orderId);
                orderView.setBiddingList(biddingList);
                // 查询
                break;
            case DESIGN_STAGE:
            case DESIGN_STAGE_WAIT_UPLOAD:
            case DESIGN_STAGE_WAIT_CONFIRM:
            case WORKING_DRAWING_DESIGN_STAGE:
            case WORKING_DRAWING_WAIT_PAY:
            case WORKING_DRAWING_WAIT_UPLOAD:
            case WORKING_DRAWING_WAIT_CONFIRM:
            case COMPLETED:
                // 查询设计师
                orderView.setOrderDesigner(orderService.selectOrderDesignerWebViewByOrderId(orderId));
                // 查询orderLog
                List<WebAppOrderDesignerLogView> orderUploadDesigns = orderService.selectOrderDesignerLogByOrderId(orderId);
                orderView.setDesignUploadLog(orderUploadDesigns);
                break;
            default:
                break;
        }
        // 查询log
        List<WebAppOrderLogView> logList = orderLogService.selectByOrderId(orderId);
        orderView.setLogList(logList);
        //
        return orderView;
    }

    @Override
    public void uploadWorkingDrawing(UploadWorkingDrawingForm form) {
        Integer orderId = form.getOrderId();
        Order order = orderService.getById(orderId);
        assertOrderNotNull(order);
        assertOrderNodeIn(EnumSet.of(OrderNodeEnum.WORKING_DRAWING_WAIT_UPLOAD, OrderNodeEnum.WORKING_DRAWING_WAIT_CONFIRM), order.getNodeId());
        // 上传文件前，删除原有施工图文件
        List<Integer> workingDrawingUploadIds = orderService.selectWorkingDrawingUploadIdByOrderId(orderId);
        orderService.deleteUploadFilesByUploadId(workingDrawingUploadIds);
        // insert upload log
        OrderDesignerLog log = new OrderDesignerLog(orderId, order.getNodeId(), form.getDesignerId());
        orderService.insertOrderDesignerLog(log);
        // 上传新文件
        form.getUploadFile().setUploadId(log.getId());
        orderService.insertUploadFile(form.getUploadFile());
        // 更新状态
        updateNodeInOrderAndDesignerOrderTable(orderId, OrderNodeEnum.WORKING_DRAWING_WAIT_CONFIRM);
        // log
        orderLogService.insert(orderId, form.getDesignerId(), OrderOperationEnum.UPLOAD_WORKING_DRAWING);
    }

    @Override
    public PageParam<WebAppOrderView> pageQueryGrabOrderList(PageParam<WebAppOrderView> page, Integer userId) {
        return orderService.pageQueryGrabOrderList(page, userId);
    }

    @Override
    public PageParam<WebAppOrderView> pageQueryDesignerAllOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
        return orderService.pageQueryDesignerAllOrderList(page, designerId);
    }

    @Override
    public PageParam<WebAppOrderView> pageQueryDesignerCompletedOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
        return orderService.pageQueryDesignerCompletedOrderList(page, designerId);
    }

    @Override
    public PageParam<WebAppOrderView> pageQueryDesignerOngoingOrderList(PageParam<WebAppOrderView> page, Integer designerId) {
        return orderService.pageQueryDesignerOngoingOrderList(page, designerId);
    }

    private void assertOrderNotNull(Order order) {
        if (order == null) {
            throw new OrderException(OrderException.ErrorCode.ORDER_NOT_EXISTED);
        }
    }

    private void assertOrderNodeIn(Set<OrderNodeEnum> nodeSet, String nodeId) {
        assert nodeId != null;
        assert nodeSet != null && nodeSet.size() > 0;
        for (OrderNodeEnum nodeEnum : nodeSet) {
            if (nodeEnum.getNodeId().equals(nodeId)) {
                return;
            }
        }
        throw new OrderException(OrderException.ErrorCode.ORDER_WRONG_NODE_ID);
    }

    /**
     * 同时更新Order和DesignerOrder表中的node
     *
     * @param orderId
     * @param node
     */
    private void updateNodeInOrderAndDesignerOrderTable(Integer orderId, OrderNodeEnum node) {
        orderService.updateOrderNodeById(orderId, node);
        DesignerOrder designerOrder = orderService.selectDesignerOrderByOrderId(orderId);
        if (designerOrder == null) {
            throw new OrderException(OrderException.ErrorCode.ORDER_DESIGNER_ORDER_NOT_EXIST);
        }
        orderService.updateDesignerOrderNodeById(orderId, node);
    }


    /**
     * OrderDesignerPrice表中对应的记录num加一，将付钱操作写入OrderDesignerLog
     *
     * @param orderId
     * @param type
     */
    private void increaseOrderDesignerPriceNumIfExistAndInsertLog(Integer orderId, DesignerPriceConfigEnum type, OrderNodeEnum orderLogNode) {
        OrderDesignerPrice price = orderService.selectByTypeAndOrderId(type, orderId);
        if (price == null) {
            throw new OrderException(OrderException.ErrorCode.ORDER_DESIGNER_PRICE_NOT_EXIST);
        }
        orderService.increaseOrderDesignerPriceNum(price);
        // 插入te_order_designer_log
        OrderDesignerLog orderDesignerLog = new OrderDesignerLog(price.getOrderId(), orderLogNode.getNodeId(), price.getPrice(),
                price.getDesignerId(), price.getType());
        orderService.insertOrderDesignerLog(orderDesignerLog);
    }
}
