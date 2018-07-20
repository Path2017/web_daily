package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.enumration.DesignerPriceConfigEnum;
import com.e3expo.e3.enumration.OrderNodeEnum;
import com.e3expo.e3.middleware.mapper.DesignerMapper;
import com.e3expo.e3.middleware.mapper.OrderDesignerPriceMapper;
import com.e3expo.e3.model.OrderDesignerLog;
import com.e3expo.e3.model.OrderDesignerPrice;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDesignerPriceDao {
    private OrderDesignerPriceMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private void setSqlSessionTemplate(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(OrderDesignerPriceMapper.class);
    }

    public void batchInsert(List<OrderDesignerPrice> list) {
        mapper.batchInsert(list);
    }


    /**
     * 增加指定order的某个报价的修改次数
     * @param price
     */
    public void increaseNumById(OrderDesignerPrice price) {
        mapper.increaseNumById(price);
    }

    /**
     * 根据价格类型和orderId查询指定的价格信息
     * @param type
     * @param orderId
     * @return
     */
    public OrderDesignerPrice selectByPriceTypeAndOrderId(DesignerPriceConfigEnum type, Integer orderId) {
        OrderDesignerPrice orderDesignerPrice = new OrderDesignerPrice();
        orderDesignerPrice.setOrderId(orderId);
        orderDesignerPrice.setNodeId(type.getNodeId());
        orderDesignerPrice.setType(type.getType());
        return mapper.selectByTypeAndNodeIdAndOrderId(orderDesignerPrice);
    }

    /**
     * 查询订单更新次数
     *
     * @param orderId
     * @return
     */
    public Integer selectUpdateDesignCountByOrderId(Integer orderId) {
        OrderDesignerLog log = new OrderDesignerLog();
        log.setOrderId(orderId);
        log.setNodeId(OrderNodeEnum.DESIGN_WAITING_PAY.getNodeId());
        log.setType(DesignerPriceConfigEnum.MODIFY_DESIGN_PRICE.getType());
        return mapper.selectUpdateDesignCountByOrderId(log);
    }
}
