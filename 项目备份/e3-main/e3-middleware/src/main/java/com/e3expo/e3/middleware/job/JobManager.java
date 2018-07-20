package com.e3expo.e3.middleware.job;

import com.e3expo.e3.model.job.CronJobModel;
import com.e3expo.e3.model.job.JobModel;
import com.e3expo.e3.model.job.SingleExecutionJobModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobManager {

    @Autowired
    private Scheduler scheduler;

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    private static final Logger logger = LogManager.getLogger(JobManager.class.getName());

    /**
     * 添加只会执行一次的任务
     *
     * @param clazz      Job的Class
     */
    public void addSingleExecutionJob(SingleExecutionJobModel job, Class<? extends Job> clazz) throws SchedulerException {

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
                .startAt((new Date(System.currentTimeMillis() + job.getDelay() * 1000)))
                .build();
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .usingJobData(new JobDataMap(job.getJobDataMap()))
                .withIdentity(job.getJobName(), job.getJobGroup()).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 添加一个定时任务
     */
    public void addCronJob(CronJobModel job, Class clazz) throws SchedulerException {
        logger.info("添加任务" + clazz.getName());
        //这里获取任务信息数据
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (trigger == null) {
            System.out.println("trigger is null----");
            //不存在，创建一个
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob", job);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            //按新的cronExpression表达式构建一个新的trigger
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 启动所有定时任务
     */
    public void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 启动指定任务
     *
     * @param job 定时任务信息
     * @throws SchedulerException
     */
    public void runJob(JobModel job) {
        logger.info("启动任务" + job.getJobName());
        if (job == null) {
            logger.error("任务为空，无法启动：" + job.getJobName());
            return;
        }
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        try {
            scheduler.triggerJob(jobKey);
            logger.info("启动任务成功" + job.getJobName());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("启动任务异常：" + e.getMessage(), e);
        }

    }


    /**
     * 暂停定时任务
     *
     * @param job
     */
    void pauseJob(JobModel job) {
        logger.info("暂停任务调度中的定时任务");
        try {
            if (null == job) {
                logger.info("暂停调度任务参数不正常！");
                return;
            }
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            if (null == jobKey) {
                logger.info("任务调度中不存在[" + job.getJobName() + "]定时任务，不予进行暂停！");
                return;
            }
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            logger.error("暂停任务调度中的定时任务异常！" + e.getMessage(), e);
        }
    }

    /**
     * 删除调度任务
     *
     * @param job
     */
    public void deleteJob(JobModel job) {
        logger.info("删除任务调度中的定时任务");
        try {
            if (null == job) {
                logger.info("删除调度任务参数不正常！");
                return;
            }
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            if (null == jobKey) {
                logger.info("任务调度中不存在[" + job.getJobName() + "]定时任务，不予进行删除！");
                return;
            }
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            logger.error("删除任务调度中的定时任务异常！" + e.getMessage(), e);
        }
    }
}
