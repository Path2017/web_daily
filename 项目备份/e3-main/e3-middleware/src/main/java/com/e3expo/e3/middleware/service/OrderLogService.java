package com.e3expo.e3.middleware.service;

import com.e3expo.e3.enumration.OrderOperationEnum;
import com.e3expo.e3.middleware.dao.OrderLogDao;
import com.e3expo.e3.model.OrderLog;
import com.e3expo.e3.model.view.WebAppOrderLogView;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderLogService {
    @Autowired
    private OrderLogDao orderLogDao;

    public void  insert(Integer orderId, Integer userId, OrderOperationEnum operation) {
        orderLogDao.insertOrderLog(orderId, userId, operation);
    }


    /**
     * 查询OrderLog并且封装到webView中
     * @param orderId
     * @return
     */
    public List<WebAppOrderLogView> selectByOrderId(Integer orderId) {
        return orderLogDao.selectByOrderId(orderId);
    }
}
