package com.e3expo.e3.middleware.job;

import com.e3expo.e3.model.job.CronJobModel;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobService {

    @Autowired
    private JobManager jobManager;

    public JobManager getJobManager() {
        return jobManager;
    }

    public void setJobManager(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    /**
     * 初始化定时任务
     *
     * @throws SchedulerException
     */
    public void startJobB() throws SchedulerException {
        System.out.println("init---");
//        JobModel job = new JobModel();
//        job.setJobId("Id1");
//        job.setJobName("Name1");
//        job.setJobGroup("linGroup");
//        job.setJobTime("0/30 * * * * ?");
        jobManager.addCronJob(new CronJobModel("Name1", "linGroup", "0/30 * * * * ?"),
                MyJob.class);
        jobManager.startJobs();
    }
}
