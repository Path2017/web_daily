package com.e3expo.e3.middleware.service;

import com.e3expo.e3.common.Constants;
import com.e3expo.e3.enumration.DesignerBidIsSuccessfulEnum;
import com.e3expo.e3.enumration.DesignerPriceConfigEnum;
import com.e3expo.e3.enumration.EnumValidStatus;
import com.e3expo.e3.enumration.OrderOperationEnum;
import com.e3expo.e3.exceptions.OrderException;
import com.e3expo.e3.middleware.dao.DesignerBidDao;
import com.e3expo.e3.middleware.dao.DesignerPriceConfigDao;
import com.e3expo.e3.middleware.dao.OrderDao;
import com.e3expo.e3.middleware.dao.OrderDesignerPriceDao;
import com.e3expo.e3.middleware.dao.OrderLogDao;
import com.e3expo.e3.model.DesignerBid;
import com.e3expo.e3.model.DesignerPriceConfig;
import com.e3expo.e3.model.OrderDesignerPrice;
import com.e3expo.e3.model.OrderLog;
import com.e3expo.e3.model.form.DesignerPriceConfigForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.e3expo.e3.common.Constants.REDIS_HASH_BIDDING_ORDER;
import static com.e3expo.e3.common.Constants.REDIS_SET_BIDDING_ORDER_LIST_PREFIX;

@Service
public class DesignerBidService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DesignerPriceConfigDao designerPriceConfigDao;

    @Autowired
    private DesignerBidDao designerBidDao;

    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public List<DesignerPriceConfig> getDesignerPriceConfig(Integer userId) {
        return designerPriceConfigDao.selectDesignerPriceConfigListByUserId(userId);
    }

    /**
     * 批量插入DesignerPriceConfig
     *
     * @param designerPriceConfig
     */
    public void batchInsertDesignerPriceConfig(List<DesignerPriceConfig> designerPriceConfig) {
        for (DesignerPriceConfig priceConfig : designerPriceConfig) {
            long current = System.currentTimeMillis();
            priceConfig.setCreateTime(current);
            priceConfig.setUpdateTime(current);
            priceConfig.setIsValid(EnumValidStatus.VALID.getValue());
        }
        designerPriceConfigDao.batchInsert(designerPriceConfig);
    }


    public void modifyDesignerPriceConfig(List<DesignerPriceConfig> designerPriceConfig) {
        for (DesignerPriceConfig priceConfig : designerPriceConfig) {
            priceConfig.setUpdateTime(System.currentTimeMillis());
            designerPriceConfigDao.modifyPriceById(priceConfig);
        }
    }

    /**
     * 在redis中进行投标
     *
     * @param orderId
     * @param designerId
     * @return 剩余可以投标人数
     */
    public int bidIfOrderCanBeBade(Integer orderId, Integer designerId) {
        BoundHashOperations<String, Object, Object> hash = redisTemplate.boundHashOps(REDIS_HASH_BIDDING_ORDER);
        SetOperations<String, String> set = redisTemplate.opsForSet();
        // 判断是否可以抢单
        synchronized (this) {
            String remainingString = (String) hash.get(orderId.toString());

            if (remainingString == null) {
                throw new OrderException(OrderException.ErrorCode.ORDER_BIDDING_FINISHED);
            }
            Integer remaining = Integer.parseInt(remainingString);
//        if (remaining == null || remaining <= 0) {
            if (remaining <= 0) {
                throw new OrderException(OrderException.ErrorCode.ORDER_BIDDING_FINISHED);
            }
            // 判断是否已经抢过
            Set<String> members = set.members(REDIS_SET_BIDDING_ORDER_LIST_PREFIX);
            if (members != null) {
                if (members.contains(designerId.toString())) {
                    // 已抢
                    throw new OrderException(OrderException.ErrorCode.ORDER_ALREADY_BADE);
                }
            }
            // 执行抢单：count-1，set中新增
            if (remaining > 1) {
                // 如果有超过一个的额度，直接修改和新增
                hash.put(orderId.toString(), Integer.toString(remaining - 1));
                set.add(REDIS_SET_BIDDING_ORDER_LIST_PREFIX + orderId, designerId.toString());
            } else {
                // 如果只有一个额度了，判断为抢单结束，直接删除抢单信息。
                hash.delete(orderId.toString());
                redisTemplate.delete(REDIS_SET_BIDDING_ORDER_LIST_PREFIX + orderId);
            }
            return remaining - 1;
        }
    }


    /**
     * 将设计师投标记录插入到表中
     *
     * @param orderId
     * @param designerId
     */
    public void insertDesignerBid(Integer orderId, Integer designerId) {
        designerBidDao.insert(orderId, designerId);
        // 插入日志表
        orderLogDao.insertOrderLog(orderId, designerId, OrderOperationEnum.BID);
    }

    /**
     * 根据OrderId查询所有的投标
     *
     * @param orderId
     * @return
     */
    public List<DesignerBid> getDesignerBidsByOrderId(Integer orderId) {
        return designerBidDao.selectByOrderId(orderId);
    }

    /**
     * 选择一人中标
     * @param orderId
     * @param userId
     */
    public void selectBid(Integer orderId, Integer userId) {
        List<DesignerBid> designerBids = designerBidDao.selectByOrderId(orderId);
        if (designerBids == null || designerBids.size() == 0) {
            throw new OrderException(OrderException.ErrorCode.ORDER_NO_BIDS);
        }
        // 更新bid状态
        List<Integer> successful = new ArrayList<>(2);
        List<Integer> unsuccessful = new ArrayList<>(5);
        for (DesignerBid designerBid : designerBids) {
            if (designerBid.getDesignerId().equals(userId)) {
                successful.add(designerBid.getDesignerId());
            } else {
                unsuccessful.add(designerBid.getDesignerId());
            }
        }
        designerBidDao.updateIsSuccessByDesignerId(DesignerBidIsSuccessfulEnum.SUCCESSFUL, successful);
        designerBidDao.updateIsSuccessByDesignerId(DesignerBidIsSuccessfulEnum.UNSUCCESSFUL, unsuccessful);
    }

}
