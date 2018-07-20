package com.e3expo.e3.middleware.exporter;

import com.e3expo.e3.enumration.EnumOrderStatus;
import com.e3expo.e3.enumration.OrderNodeEnum;
import com.e3expo.e3.enumration.RfpStatusEnum;
import com.e3expo.e3.exceptions.OrderException;
import com.e3expo.e3.exceptions.RfpException;
import com.e3expo.e3.middleware.common.RedisUtil;
import com.e3expo.e3.middleware.common.redis.expiremessage.OrderExpireMessageTypeEnum;
import com.e3expo.e3.middleware.job.JobManager;
import com.e3expo.e3.middleware.job.OrderEndBiddingJob;
import com.e3expo.e3.middleware.service.OrderService;
import com.e3expo.e3.middleware.service.RfpService;
import com.e3expo.e3.model.Order;
import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.model.RfpDetail;
import com.e3expo.e3.model.form.RfpBaseInfoForm;
import com.e3expo.e3.model.form.RfpDetailForm;
import com.e3expo.e3.model.job.SingleExecutionJobModel;
import com.e3expo.e3.model.view.WebAppRfpView;
import com.e3expo.e3.service.interfaces.IRfp;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.e3expo.e3.common.Constants.SECONDS_OF_AN_HOUR;
import static com.e3expo.e3.enumration.RfpStatusEnum.*;

@Component
public class RfpExporter implements IRfp {

    @Autowired
    private RfpService rfpService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 确认RFP基本信息并保存，RFP进入填写设计需求的详情的状态
     * @param baseInfoForm 包含最基本的需求信息
     * @return
     */
    @Override
    public int saveRfpBaseInfo(RfpBaseInfoForm baseInfoForm) {
        // 设置状态
        baseInfoForm.setStatus(DETAIL_DRAFT.getValue());
        if (baseInfoForm.getId() == null) {
            // 作为新增
            rfpService.insertRfp(baseInfoForm);
        } else {
            // 作为修改
            rfpService.updateRfp(baseInfoForm);
        }
        return baseInfoForm.getId();
    }


    /**
     * 发布RFP
     *
     * @param rfpId
     */
    @Override
    public void publishRfp(Integer rfpId, Integer userId) {
        assert rfpId != null;
        assert userId != null;
        Rfp rfp = rfpService.getRfpById(rfpId);
        if (rfp == null) {
            throw new RfpException(RfpException.ErrorCode.RFP_NOT_EXISTED);
        }
        if (RfpStatusEnum.BASE_INFO_DRAFT.getValue() == rfp.getStatus()) {
            throw new RfpException(RfpException.ErrorCode.RFP_DETAIL_REQUIRED);
        } else if (RfpStatusEnum.PUBLISHED.getValue() == rfp.getStatus()) {
            throw new RfpException(RfpException.ErrorCode.RFP_ALREADY_PUBLISHED);
        }
        // 新增一条Order记录
        // 查询这个rfpId有没有发布过
        Order orderByRfpId = orderService.selectOrderByRfpId(rfpId);
        if (orderByRfpId != null) {
            throw new RfpException(RfpException.ErrorCode.RFP_ALREADY_PUBLISHED);
        }
        Order order = newOrder(rfpId);
        order.setUserId(userId);
        rfpService.insertOrder(order);
        // 更新RFP记录
        rfpService.updateRfpStatus(rfpId, RfpStatusEnum.PUBLISHED);
        // 将抢单记录放到Redis中
        rfpService.publishRfpToRedis(order.getId());
        // 向redis发送带有时间的key
        RedisUtil.sendOrderTaskExpireMessage(stringRedisTemplate, OrderExpireMessageTypeEnum.ORDER_END_BIDDING_TASK, order.getId());

    }


    @Override
    public int createRfpBaseInfoDraft(RfpBaseInfoForm baseInfoForm) {
        // 设置状态
        baseInfoForm.setStatus(RfpStatusEnum.BASE_INFO_DRAFT.getValue());
        // 插入Rfp
        return rfpService.insertRfp(baseInfoForm);
    }

    /**
     * 更新Rfp基本信息并作为草稿
     *
     * @param baseInfoForm
     */
    @Override
    public void updateBaseInfoDraft(RfpBaseInfoForm baseInfoForm) {
        // 保存草稿不会改变状态
        Rfp old = rfpService.getRfpById(baseInfoForm.getId());
        if (old == null) {
            throw new RfpException(RfpException.ErrorCode.RFP_NOT_EXISTED);
        } else if (old.getStatus() == null) {
            throw new RfpException(RfpException.ErrorCode.RFP_ILLEGAL_STATUS);
        }
        rfpService.updateRfp(baseInfoForm);
    }


    @Override
    public void createRfpDetailDraft(RfpDetailForm detailForm) {
        // 此时只是将它保存为草稿
        rfpService.insertRfpDetail(detailForm);
        // 将rfp状态改为详细信息
        rfpService.updateRfpStatus(detailForm.getRfpId(), RfpStatusEnum.DETAIL_DRAFT);
    }

    @Override
    public void updateRfpDetailDraft(RfpDetailForm detailForm) {
        rfpService.updateRfpDetail(detailForm);
    }


    @Override
    public void updateAndPublishRfp(RfpDetailForm detailForm) {
        RfpDetail detail = rfpService.getRfpDetailByRfpId(detailForm.getRfpId());
        if (detail == null) {
            rfpService.insertRfpDetail(detailForm);
        } else {
            detailForm.setId(detail.getId());
            rfpService.updateRfpDetail(detailForm);
        }
        publishRfp(detailForm.getRfpId(), detailForm.getUserId());
    }

    @Override
    public WebAppRfpView getRfpDraft(Integer rfpId) {
        // 获取rfp草稿
        return rfpService.getRfpDraftById(rfpId);
    }

    /**
     * 根据RFP ID 生成一个Order对象，设置好必要的属性
     * @param rfpId
     * @return
     */
    private Order newOrder(Integer rfpId) {
        Order order = new Order();
        order.setCreateTime(System.currentTimeMillis());
        order.setUpdateTime(System.currentTimeMillis());
        order.setNodeId(OrderNodeEnum.BIDDING.getNodeId());
        order.setRfpId(rfpId);
        order.setStatus(EnumOrderStatus.ORDER_NORMAL.getValue());
        return order;
    }
}
