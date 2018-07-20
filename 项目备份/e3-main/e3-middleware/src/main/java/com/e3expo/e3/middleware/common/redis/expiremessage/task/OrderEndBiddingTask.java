package com.e3expo.e3.middleware.common.redis.expiremessage.task;

import com.e3expo.e3.common.Constants;
import com.e3expo.e3.enumration.EnumOrderStatus;
import com.e3expo.e3.enumration.OrderNodeEnum;
import com.e3expo.e3.enumration.OrderOperationEnum;
import com.e3expo.e3.exceptions.OrderException;
import com.e3expo.e3.middleware.dao.DesignerBidDao;
import com.e3expo.e3.middleware.dao.OrderDao;
import com.e3expo.e3.middleware.dao.OrderLogDao;
import com.e3expo.e3.model.DesignerBid;
import com.e3expo.e3.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class OrderEndBiddingTask extends AbstractOrderExpireMessageTask{

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderLogDao orderLogDao;
    @Autowired
    private DesignerBidDao designerBidDao;

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
        List<DesignerBid> bidList = designerBidDao.selectByOrderId(order.getId());
        if (bidList == null || bidList.size() == 0) {
            // 无人竞标，将状态改为已取消
            orderDao.updateOrderStatusById(order.getId(), EnumOrderStatus.ORDER_CANCEL);
            orderLogDao.insertOrderLog(order.getId(), order.getUserId(), OrderOperationEnum.CANCEL);
        } else if (bidList.size() > Constants.ORDER_BIDDING_UPPER_LIMIT) {
            throw new OrderException(OrderException.ErrorCode.ORDER_TOO_MANY_BIDS);
        } else {
            if (OrderNodeEnum.BIDDING.getNodeId().equals(order.getNodeId())) {
                // 如果仍然处于Bidding阶段，进入设计待付款
                orderDao.updateOrderNodeById(order.getId(), OrderNodeEnum.DESIGN_WAITING_PAY);
                orderLogDao.insertOrderLog(order.getId(), order.getUserId(), OrderOperationEnum.WAIT_PAY);
            }
        }
    }
}
