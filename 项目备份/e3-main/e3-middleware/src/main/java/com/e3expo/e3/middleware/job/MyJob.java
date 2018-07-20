package com.e3expo.e3.middleware.job;

import com.e3expo.e3.model.job.JobModel;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e3expo.e3.middleware.service.TestService;

@Component
public class MyJob extends AbstractAddedByJobManagerJob {

    @Autowired
    private TestService testService;


    public TestService getTestService() {
        return testService;
    }


    public void setTestService(TestService testService) {
        this.testService = testService;
    }

//    @Override
//    public void execute(JobExecutionContext arg0) throws JobExecutionException {
//        // TODO Auto-generated method stub
//        System.out.println("任务成功运行------");
//        JobModel detailInfo = (JobModel) arg0.getMergedJobDataMap().get("scheduleJob");
//        System.out.println(detailInfo.getJobName() + detailInfo.getJobGroup());
////	        arg0.getMergedJobDataMap().get("scheduleJob");
//        System.out.println("任务名称 = [" + detailInfo.getJobName() + "]");
//        if (testService == null) {
//            System.out.println("注入不成功------");
//        } else {
//            System.out.println("注入成功------");
//            testService.sayHello("鲁宁");
//        }
//    }


    @Override
    protected void doExecute(JobDataMap dataMap) {
//        System.out.println(detailInfo.getJobName() + detailInfo.getJobGroup());
////	        arg0.getMergedJobDataMap().get("scheduleJob");
//        System.out.println("任务名称 = [" + detailInfo.getJobName() + "]");
//        if (testService == null) {
//            System.out.println("注入不成功------");
//        } else {
//            System.out.println("注入成功------");
//            testService.sayHello("鲁宁");
//        }
    }
}
