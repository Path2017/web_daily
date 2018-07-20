package com.e3expo.e3.middleware.job;

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
import com.e3expo.e3.model.job.JobModel;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 结束订单投标的任务
 */
@Component
public class OrderEndBiddingJob extends AbstractAddedByJobManagerJob {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderLogDao orderLogDao;
    @Autowired
    private DesignerBidDao designerBidDao;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void doExecute(JobDataMap dataMap) {
        // 根据ID查询order，查看order的状态，根据ID查询bid，判断bid数量
        Integer orderId = (Integer) dataMap.get("orderId");
        Integer userId = (Integer) dataMap.get("userId");
        // 删除redis中的投标记录
        redisTemplate.opsForHash().delete(Constants.REDIS_HASH_BIDDING_ORDER, orderId.toString());
        redisTemplate.delete(Constants.REDIS_SET_BIDDING_ORDER_LIST_PREFIX + orderId);
        if (orderId == null) {
            throw new NullPointerException("orderId is null");
        }
        Order order = orderDao.selectById(orderId);
        if (order == null) {
            throw new OrderException(OrderException.ErrorCode.ORDER_NOT_EXISTED);
        }
        if (order.getNodeId() == null) {
            throw new OrderException(OrderException.ErrorCode.ORDER_NODE_ID_IS_NULL);
        }
        List<DesignerBid> bidList = designerBidDao.selectByOrderId(orderId);
        if (bidList == null || bidList.size() == 0) {
            // 无人竞标，将状态改为已取消
            orderDao.updateOrderStatusById(orderId, EnumOrderStatus.ORDER_CANCEL);
            orderLogDao.insertOrderLog(orderId, userId, OrderOperationEnum.CANCEL);
        } else if (bidList.size() > Constants.ORDER_BIDDING_UPPER_LIMIT) {
            throw new OrderException(OrderException.ErrorCode.ORDER_TOO_MANY_BIDS);
        } else {
            if (OrderNodeEnum.BIDDING.getNodeId().equals(order.getNodeId())) {
                // 如果仍然处于Bidding阶段，进入设计待付款
                orderDao.updateOrderNodeById(orderId, OrderNodeEnum.DESIGN_WAITING_PAY);
                orderLogDao.insertOrderLog(orderId, userId, OrderOperationEnum.WAIT_PAY);
            }
        }
    }

}
