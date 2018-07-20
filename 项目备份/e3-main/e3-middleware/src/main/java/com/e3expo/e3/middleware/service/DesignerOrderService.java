package com.e3expo.e3.middleware.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3expo.e3.enumration.EnumDesignerGenre;
import com.e3expo.e3.middleware.dao.DesignerDao;
import com.e3expo.e3.middleware.dao.DesignerOrderDao;
import com.e3expo.e3.middleware.dao.SingleTimerConfigDao;
import com.e3expo.e3.middleware.job.DesignerMarryJob;
import com.e3expo.e3.middleware.job.JobManager;
import com.e3expo.e3.model.DesignerConfig;
import com.e3expo.e3.model.DesignerModel;
import com.e3expo.e3.model.DesignerOrder;
import com.e3expo.e3.model.OrderModel;
import com.e3expo.e3.model.SingleTimerConfig;
import com.e3expo.e3.model.param.OrderParam;
import com.e3expo.e3.util.DateUtils;

/**
 * 设计师订单
 * @author luning
 *
 */
@Service
public class DesignerOrderService {
	
	@Autowired
	private DesignerDao designerDao;
	
	@Autowired
	private DesignerOrderDao designerOrderDao;
	
	@Autowired
	private SingleTimerConfigDao singleConfigDao;
	
	
	 @Autowired
    private JobManager jobManager;  
      
    public JobManager getJobManager() {  
        return jobManager;  
    }  
    public void setJobManager(JobManager jobManager) {  
        this.jobManager = jobManager;  
    }  
	
	
	/*@Transactional
	public void releaseOrder(int designerLeve,int rfpId) {
		// TODO 插入订单表
		
		//关联设计师
		likeDesigner(designerLeve, rfpId);
		SingleTimerConfig config = singleConfigDao.selectInfo();
		//查询该设计师级别以上的设计师
		List<DesignerConfig> designerLevelList = designerDao.selectDesignerLevelUp(designerLeve);
		if(designerLevelList != null && designerLevelList.size()>0) {


			List<Integer> limitList = new ArrayList<>();
			limitList.add(config.getFirstTimeLimit());
			List<JobModel> jobList = new ArrayList<>();
			if(designerLevelList.size() >1) {
				for(DesignerConfig designerConfig : designerLevelList) {
					if(designerConfig.getType().intValue() ==  EnumDesignerGenre.DESIGNER_OUTSIDE.getValue()) {
						JobModel jobModel = new JobModel();
						jobModel.setJobId(designerConfig.getLevel()+"");
						jobModel.setJobName(rfpId+"");
						jobModel.setJobGroup(designerConfig.getLevel()+"OrderMarryDesigner");
						//获取日期
						Date date = DateUtils.nextMinute(new Date(), config.getFirstTimeLimit());
						jobModel.setJobTime(DateUtils.getCron(date));
						jobList.add(jobModel);
						
					}else if(designerConfig.getType().intValue() ==  EnumDesignerGenre.DESIGNER_SELF.getValue()){
						JobModel jobModel = new JobModel();
						jobModel.setJobId(designerConfig.getLevel()+"");
						jobModel.setJobName(rfpId+"");
						jobModel.setJobGroup(designerConfig.getLevel()+"OrderMarryDesigner");
						//获取日期
						Date date = DateUtils.nextMinute(new Date(), config.getSecondTimeLimit());
						jobModel.setJobTime(DateUtils.getCron(date));
						jobList.add(jobModel);
					}
				}
			}else {
				JobModel jobModel = new JobModel();
				jobModel.setJobId(designerLevelList.get(0).getLevel()+"");
				jobModel.setJobName(rfpId+"");
				jobModel.setJobGroup(designerLevelList.get(0).getLevel()+":OrderMarryDesigner");
				//获取日期
				Date date = DateUtils.nextMinute(new Date(), config.getFirstTimeLimit());
				jobModel.setJobTime(DateUtils.getCron(date));
				jobList.add(jobModel);
			}
			for(JobModel jobModel : jobList) {
				try {
					jobManager.addJob(jobModel, DesignerMarryJob.class);
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//TODO  如果超过匹配时间，还未有接单，清空当前的可以匹配的设计师关联表，修改当前订单状态
		JobModel jobModel = new JobModel();
		jobModel.setJobId("");
		jobModel.setJobName(rfpId+"");
		jobModel.setJobGroup("OrderMarryDesignerDel");
		//获取日期
		Date date = DateUtils.nextMinute(new Date(), config.getFirstTimeLimit());
		jobModel.setJobTime(DateUtils.getCron(date));
//		jobList.add(jobModel,);
	}*/

	/**
	 * 关联设计师
	 */
	/*@Transactional
	public void likeDesigner(int designerLeve,int orderId) {

		//根据设计师类型选择设计师
		List<DesignerModel> designerList = designerDao.selectDesignerByLevel(designerLeve);
		List<DesignerOrder> insertList = new ArrayList<DesignerOrder>();
		if(designerList != null && designerList.size()>0) {
			for(DesignerModel designer : designerList) {
				int orderCount = designerOrderDao.selectCountOrderingDesigner(designer.getUserId());
				if(orderCount<designer.getTotalNum()) {
					//可以添加到可抢单设计师列表
					DesignerOrder designerOrder = new DesignerOrder();
					designerOrder.setOrderId(orderId);
					designerOrder.setDesignerId(designer.getUserId());
					insertList.add(designerOrder);
				}
			}
		}
		if(insertList != null && insertList.size()>0) {
			designerOrderDao.insertDesignerLinkOrder(insertList);
		}
	}*/
	/**
	 * 获取可抢单列表
	 * @param designerId
	 * @return List<OrderModel>
	 */
//	public List<OrderModel> querySingleOrderList(OrderParam param){
//		List<OrderModel> orderList = designerOrderDao.selectSingleOrderListByDesignerId(param);
//		return orderList;
//	}
	
	
}
