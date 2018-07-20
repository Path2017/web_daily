package com.e3expo.e3.middleware.service;

import com.e3expo.e3.enumration.OrderOperationEnum;
import com.e3expo.e3.enumration.RfpStatusEnum;
import com.e3expo.e3.exceptions.RfpException;
import com.e3expo.e3.middleware.dao.OrderDao;
import com.e3expo.e3.middleware.dao.OrderLogDao;
import com.e3expo.e3.middleware.dao.RfpDao;
import com.e3expo.e3.middleware.dao.RfpDetailDao;
import com.e3expo.e3.model.Order;
import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.model.RfpDetail;
import com.e3expo.e3.model.view.WebAppRfpView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.e3expo.e3.common.Constants.ORDER_BIDDING_UPPER_LIMIT;
import static com.e3expo.e3.common.Constants.REDIS_HASH_BIDDING_ORDER;

@Service
public class RfpService {

    @Autowired
    private RfpDao rfpDao;

    @Autowired
    private RfpDetailDao rfpDetailDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 插入Rfp，回写主键到ID
     *
     * @param rfp
     * @return
     */
    public int insertRfp(Rfp rfp) {
        rfp.setCreateTime(System.currentTimeMillis());
        rfp.setUpdateTime(System.currentTimeMillis());
        return rfpDao.insertBaseInfo(rfp);
    }

    /**
     * 插入RfpDetail表，
     *
     * @param detail
     */
    public void insertRfpDetail(RfpDetail detail) {
        Rfp rfp = rfpDao.getById(detail.getRfpId());
        if (rfp == null) {
            // todo throw new Exception
            throw new RfpException(RfpException.ErrorCode.RFP_NOT_EXISTED);
        }
        RfpDetail detailGetByRfpId = rfpDetailDao.getByRfpId(detail.getRfpId());
        if (detailGetByRfpId != null) {
            // todo throw new Exception
            throw new RfpException(RfpException.ErrorCode.RFP_DETAIL_ALREADY_EXISTED);
        }
        rfpDetailDao.insertRfpDetail(detail);
    }

    /**
     * 更新Rfp的状态
     *
     * @param rfpId      rfpId
     * @param statusEnum 状态的枚举
     */
    public void updateRfpStatus(Integer rfpId, RfpStatusEnum statusEnum) {
        rfpDao.updateRfpStatusByRfpId(rfpId, statusEnum.getValue());
    }

    /**
     * 根据id获取Rfp
     *
     * @param rfpId
     */
    public Rfp getRfpById(Integer rfpId) {
        return rfpDao.getById(rfpId);
    }

    /**
     * 插入一条Order数据并回写主键
     *
     * @param order
     */
    public int insertOrder(Order order) {
        Order byRfpId = orderDao.getOrderByRfpId(order.getRfpId());
        if (byRfpId != null) {
            throw new RfpException(RfpException.ErrorCode.RFP_ALREADY_PUBLISHED);
        }
        orderDao.insert(order);
        orderLogDao.insertOrderLog(order.getId(), order.getUserId(), OrderOperationEnum.CREATE);
        return order.getId();
    }

    /**
     * 只是根据主键更新rfp信息
     *
     * @param rfp
     */
    public void updateRfp(Rfp rfp) {
        rfp.setUpdateTime(System.currentTimeMillis());
        rfpDao.updateRfpById(rfp);
    }

    /**
     * 更新RfpDetail
     *
     * @param detail
     */
    public void updateRfpDetail(RfpDetail detail) {
        detail.setUpdateTime(System.currentTimeMillis());
        rfpDetailDao.updateRfpDetailById(detail);
    }

    /**
     * 将待抢订单放到Redis中待抢
     *
     * @param id
     */
    public void publishRfpToRedis(Integer id) {
        assert id != null;
        redisTemplate.opsForHash().put(REDIS_HASH_BIDDING_ORDER, id.toString(), ORDER_BIDDING_UPPER_LIMIT + "");
    }

    /**
     * 根据RfpId查询是否
     *
     * @param rfpId
     * @return
     */
    public RfpDetail getRfpDetailByRfpId(Integer rfpId) {
        return rfpDetailDao.getByRfpId(rfpId);
    }

    /**
     * 获取Rfp草稿
     *
     * @param rfpId
     * @return
     */
    public WebAppRfpView getRfpDraftById(Integer rfpId) {

        return rfpDao.getRfpDraftById(rfpId);
    }
}
