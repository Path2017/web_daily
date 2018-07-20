package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.enumration.OrderOperationEnum;
import com.e3expo.e3.middleware.mapper.OrderLogMapper;
import com.e3expo.e3.model.OrderLog;
import com.e3expo.e3.model.view.WebAppOrderLogView;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderLogDao {

    @Autowired
    private void setSql(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(OrderLogMapper.class);
    }

    private OrderLogMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 插入order日志
     * @param orderId
     * @param userId
     * @param operation
     */
    public void insertOrderLog(Integer orderId, Integer userId, OrderOperationEnum operation) {
        assert orderId != null;
        assert userId != null;
        OrderLog orderLog = new OrderLog(operation.getId(), userId, orderId, System.currentTimeMillis(), null);
        orderLog.setCreateTime(System.currentTimeMillis());
        mapper.insert(orderLog);
    }

    /**
     * 根据orderId查询列表
     *
     * @param orderId
     * @return
     */
    public List<WebAppOrderLogView> selectByOrderId(Integer orderId) {
        return mapper.selectByOrderId(orderId);
    }
}
