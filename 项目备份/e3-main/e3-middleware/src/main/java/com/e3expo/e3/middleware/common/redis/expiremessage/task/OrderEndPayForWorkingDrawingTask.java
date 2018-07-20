package com.e3expo.e3.middleware.common.redis.expiremessage.task;

import com.e3expo.e3.enumration.EnumOrderStatus;
import com.e3expo.e3.enumration.OrderNodeEnum;
import com.e3expo.e3.enumration.OrderOperationEnum;
import com.e3expo.e3.exceptions.OrderException;
import com.e3expo.e3.middleware.dao.DesignerOrderDao;
import com.e3expo.e3.middleware.dao.OrderDao;
import com.e3expo.e3.middleware.dao.OrderLogDao;
import com.e3expo.e3.model.DesignerOrder;
import com.e3expo.e3.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class OrderEndPayForWorkingDrawingTask extends AbstractOrderExpireMessageTask{

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DesignerOrderDao designerOrderDao;
    @Autowired
    private OrderLogDao orderLogDao;

    @Override
    public void run() {
        // 根据ID查询order，查看order的状态，根据ID查询bid，判断bid数量;
        // 删除redis中的投标记录
        Order order = orderDao.selectById(getOrderId());
        if (order == null) {
            throw new OrderException(OrderException.ErrorCode.ORDER_NOT_EXISTED);
        }
        if (order.getNodeId() == null) {
            throw new OrderException(OrderException.ErrorCode.ORDER_NODE_ID_IS_NULL);
        }
        if (OrderNodeEnum.WORKING_DRAWING_WAIT_PAY.getNodeId().equals(order.getNodeId())) {
            // 如果此时订单仍处于待付款阶段，就将订单状态改为已完成
            orderDao.updateOrderStatusById(getOrderId(), EnumOrderStatus.ORDER_COMPLETE);
            orderDao.updateOrderNodeById(getOrderId(), OrderNodeEnum.COMPLETED);
            DesignerOrder designerOrder = designerOrderDao.selectByOrderId(getOrderId());
            if (designerOrder == null) {
                throw new OrderException(OrderException.ErrorCode.ORDER_DESIGNER_ORDER_NOT_EXIST);
            }
            designerOrderDao.updateNodeIdByOrderId(getOrderId(), OrderNodeEnum.COMPLETED);
            orderLogDao.insertOrderLog(getOrderId(), null, OrderOperationEnum.COMPLETE);
        }
    }
}
