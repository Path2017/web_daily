package com.e3expo.e3.middleware.job;

import java.util.ArrayList;
import java.util.List;

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
import com.e3expo.e3.middleware.service.TestService;
import com.e3expo.e3.model.DesignerModel;
import com.e3expo.e3.model.DesignerOrder;
import com.e3expo.e3.model.OrderModel;

@Component
public class DesignerMarryJob implements Job {

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
        /*System.out.println("匹配订单任务成功运行------");
        JobModel jobInfo = (JobModel) arg0.getMergedJobDataMap().get("scheduleJob");
        System.out.println(jobInfo.getJobName() + jobInfo.getJobGroup());
        //TODO 查询订单
        if (!StringUtils.isEmpty(jobInfo.getJobName())) {
            int orderId = Integer.parseInt(jobInfo.getJobName());
            OrderModel order = orderDao.selectOrderStatusById(orderId);
            if (order != null && order.getStatus() != null && order.getStatus() == EnumOrderStatus.ORDER_NORMAL.getValue()) {
                //TODO  查询设计师
                //暂时将jobId定义为设计师级别
                if (StringUtils.isEmpty(jobInfo.getJobId())) {
                    int designerLevel = Integer.parseInt(jobInfo.getJobId());
                    //获取该级别下的设计师（审核通过并且在启用的设计师）
                    List<DesignerModel> designerModelList = designerDao.selectDesignerByLevel(designerLevel);
                    if (designerModelList != null && designerModelList.size() > 0) {
                        List<DesignerOrder> insertDesigner = new ArrayList<>();
                        for (DesignerModel designer : designerModelList) {
                            //获取当前设计师已接单数量
                            int count = designerOrderDao.selectCountOrderingDesigner(designer.getUserId());
                            if (designer.getTotalNum() > count) {
                                DesignerOrder designerModel = new DesignerOrder();
                                designerModel.setOrderId(orderId);
                                designerModel.setDesignerId(designer.getUserId());
                                insertDesigner.add(designerModel);
                            }
                        }
                        designerOrderDao.insertDesignerLinkOrder(insertDesigner);
                        logger.info("定时匹配到设计师定时任务成功执行插入！！！");
                    }
                }
            }
        }*/
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
