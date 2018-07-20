package com.e3expo.e3.middleware.job;

import java.util.ArrayList;
import java.util.List;

import com.e3expo.e3.model.job.JobModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.e3expo.e3.enumration.EnumOrderStatus;
import com.e3expo.e3.middleware.dao.DesignerDao;
import com.e3expo.e3.middleware.dao.DesignerOrderDao;
import com.e3expo.e3.middleware.dao.OrderDao;
import com.e3expo.e3.model.OrderModel;

@Component
public class OrderCancelJob implements Job {

    @Autowired
    private DesignerDao designerDao;

    @Autowired
    private DesignerOrderDao designerOrderDao;

    @Autowired
    private OrderDao orderDao;

    private static final Logger logger = LogManager.getLogger(DesignerMarryJob.class.getName());


    @Override
    @Transactional
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("匹配订单任务成功运行------");
        JobModel jobInfo = (JobModel) arg0.getMergedJobDataMap().get("scheduleJob");
        System.out.println(jobInfo.getJobName() + jobInfo.getJobGroup());
        //TODO 查询订单
        if (!StringUtils.isEmpty(jobInfo.getJobName())) {
            int orderId = Integer.parseInt(jobInfo.getJobName());
            OrderModel order = orderDao.selectOrderStatusById(orderId);
            //TODO  验证当前订单还是处于在匹配状态
            if (order != null && order.getStatus() != null && order.getStatus() == EnumOrderStatus.ORDER_NORMAL.getValue()) {
                //TODO  修改订单状态为已取消
                order.setStatus(EnumOrderStatus.ORDER_CANCEL.getValue());
//	        		order.setUpdateTime(updateTime);
                orderDao.updateOrderStatus(order);
                //TODO  删除设计师与订单可匹配关联表

            }
        }


    }


    public DesignerDao getDesignerDao() {
        return designerDao;
    }


    public void setDesignerDao(DesignerDao designerDao) {
        this.designerDao = designerDao;
    }


    public DesignerOrderDao getDesignerOrderDao() {
        return designerOrderDao;
    }


    public void setDesignerOrderDao(DesignerOrderDao designerOrderDao) {
        this.designerOrderDao = designerOrderDao;
    }

}
