package com.e3expo.e3.middleware.exporter;

import com.e3expo.e3.enumration.DesignerPriceConfigEnum;
import com.e3expo.e3.enumration.EnumOrderStatus;
import com.e3expo.e3.enumration.OrderNodeEnum;
import com.e3expo.e3.enumration.OrderOperationEnum;
import com.e3expo.e3.exceptions.DesignerBidException;
import com.e3expo.e3.exceptions.OrderException;
import com.e3expo.e3.middleware.config.aop.annotation.OrderOperationLog;
import com.e3expo.e3.middleware.service.DesignerBidService;
import com.e3expo.e3.middleware.service.OrderLogService;
import com.e3expo.e3.middleware.service.OrderService;
import com.e3expo.e3.model.DesignerPriceConfig;
import com.e3expo.e3.model.OrderModel;
import com.e3expo.e3.model.form.DesignerPriceConfigForm;
import com.e3expo.e3.service.interfaces.IDesignerBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Component
public class DesignerBidExporter implements IDesignerBid{
    @Autowired
    private DesignerBidService designerBidService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderLogService orderLogService;

    @Override
    public List<DesignerPriceConfig> getDesignerPriceConfig(Integer userId) {

        return designerBidService.getDesignerPriceConfig(userId);
    }

    @Override
    public void createOrUpdateDesignerPriceConfig(DesignerPriceConfigForm form) {
        // 根据designerId查询
        List<DesignerPriceConfig> designerPriceConfig = designerBidService.getDesignerPriceConfig(form.getDesignerId());
        if (designerPriceConfig == null || designerPriceConfig.size() == 0) {
            // 表示用户没有价格配置，新建
            designerPriceConfig = new ArrayList<>();
            designerPriceConfig.add(new DesignerPriceConfig(DesignerPriceConfigEnum.DESIGN_PRICE, form.getDesignPrice(), form.getDesignerId()));
            designerPriceConfig.add(new DesignerPriceConfig(DesignerPriceConfigEnum.MODIFY_DESIGN_PRICE, form.getModifyDesignPrice(), form.getDesignerId()));
            designerPriceConfig.add(new DesignerPriceConfig(DesignerPriceConfigEnum.WORKING_DRAWING_PRICE, form.getWorkingDrawingPrice(), form.getDesignerId()));
            designerBidService.batchInsertDesignerPriceConfig(designerPriceConfig);
        } else if (!validateDesignerPriceConfig(designerPriceConfig)) {
            throw new DesignerBidException(DesignerBidException.ErrorCode.BID_ILLEGAL_DESIGNER_PRICE_CONFIG);
        } else {
            // 不为空，校验也通过，就进行更新
            for (DesignerPriceConfig priceConfig : designerPriceConfig) {
                switch (DesignerPriceConfigEnum.getEnum(priceConfig.getNodeId(),priceConfig.getType())){
                    case DESIGN_PRICE:
                        modifyPriceIfChanged(priceConfig, form.getDesignPrice());
                        break;
                    case MODIFY_DESIGN_PRICE:
                        modifyPriceIfChanged(priceConfig, form.getModifyDesignPrice());
                        break;
                    case WORKING_DRAWING_PRICE:
                        modifyPriceIfChanged(priceConfig, form.getWorkingDrawingPrice());
                        break;
                    default:
                        break;
                }
            }
            designerBidService.modifyDesignerPriceConfig(designerPriceConfig);
        }
    }

    @Override
    public void bid(DesignerPriceConfigForm form) {
        Integer orderId = form.getOrderId();
        OrderModel orderModel = orderService.queryOrderInfo(orderId);
        if (orderModel == null) {
            throw new OrderException(OrderException.ErrorCode.ORDER_NOT_EXISTED);
        }
        if (EnumOrderStatus.ORDER_NORMAL.getValue() != orderModel.getStatus()) {
            throw new OrderException(OrderException.ErrorCode.ORDER_ILLEGAL_STATUS);
        }
        if (!OrderNodeEnum.BIDDING.getNodeId().equals(orderModel.getNodeId())) {
            // bidding已经结束
            throw new OrderException(OrderException.ErrorCode.ORDER_BIDDING_FINISHED);
        }
        // 投标,
        int remaining = designerBidService.bidIfOrderCanBeBade(orderId, form.getDesignerId());
        // 没有抛出异常，都表示成功抢到单了，执行insert操作
        designerBidService.insertDesignerBid(orderId, form.getDesignerId());
        if (remaining == 0) {
            orderService.updateOrderNodeById(orderId, OrderNodeEnum.DESIGN_WAITING_PAY);
        }
        // 插入te_order_designer_price
        orderService.insertOrderDesignerPrice(form);
        // 插入log表中
        orderLogService.insert(orderId, form.getDesignerId(), OrderOperationEnum.BID);
    }


    /**
     * 如果改变就更新
     * @param priceConfig
     * @param designPrice
     */
    private void modifyPriceIfChanged(DesignerPriceConfig priceConfig, BigDecimal designPrice) {
        if (!priceConfig.getPrice().equals(designPrice)) {
            priceConfig.setPrice(designPrice);
        }
    }

    /**
     * 校验设计师报价配置是否合法
     *
     * @param designerPriceConfig
     * @return
     */
    private boolean validateDesignerPriceConfig(List<DesignerPriceConfig> designerPriceConfig) {
        if (designerPriceConfig == null) {
            return false;
        }
        for (DesignerPriceConfig priceConfig : designerPriceConfig) {
            if (priceConfig == null) {
                return false;
            }
        }
        EnumSet<DesignerPriceConfigEnum> enums = EnumSet.allOf(DesignerPriceConfigEnum.class);
        if (designerPriceConfig.size() != enums.size()) {
            return false;
        }
        for (DesignerPriceConfigEnum configEnum : enums) {
            DesignerPriceConfig tar = null;
            for (DesignerPriceConfig priceConfig : designerPriceConfig) {
                if (configEnum.getNodeId().equals(priceConfig.getNodeId())
                        && configEnum.getType().equals(priceConfig.getType())) {
                    tar = priceConfig;
                }
            }
            if (tar == null) {
                // 如果没有这个配置就返回 false
                return false;
            }
        }
        return true;
    }
}
