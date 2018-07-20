package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.OrderDesignerLog;
import com.e3expo.e3.model.OrderDesignerPrice;

import java.util.List;

public interface OrderDesignerPriceMapper {
    /**
     * 批量插入
     * @param list
     */
    void batchInsert(List<OrderDesignerPrice> list);

    /**
     * 根据ID增加update_num
     *
     * @param price
     */
    void increaseNumById(OrderDesignerPrice price);

    /**
     * 根据orderID，Type，NodeId查询
     * @param orderDesignerPrice
     * @return
     */
    OrderDesignerPrice selectByTypeAndNodeIdAndOrderId(OrderDesignerPrice orderDesignerPrice);

    /**
     * 查询订单设计稿更新次数
     *
     * @param log
     * @return
     */
    Integer selectUpdateDesignCountByOrderId(OrderDesignerLog log);
}
