package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.OrderLog;
import com.e3expo.e3.model.view.WebAppOrderLogView;

import java.util.List;

public interface OrderLogMapper {

    /**
     *
     *
     * @param orderLog
     */
    void insert(OrderLog orderLog);

    /**
     * 根据orderId查询日志列表
     *
     * @param orderId
     * @return
     */
    List<WebAppOrderLogView> selectByOrderId(Integer orderId);
}
