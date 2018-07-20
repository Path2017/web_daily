package com.e3expo.e3.model.job;

import java.util.Map;

/**
 * 只执行一次的Job的Model
 */
public class CronJobModel extends JobModel{
    /**
     * cron表达式
     */
    private String cronExpression;

    public CronJobModel(String jobName, String jobGroup, String cronExpression) {
        super(jobName, jobGroup);
        this.cronExpression = cronExpression;
    }

    public CronJobModel(String jobName, String jobGroup, Map<String, Object> map, String cronExpression) {
        super(jobName, jobGroup, map);
        this.cronExpression = cronExpression;
    }

    public String getCronExpression() {
        return cronExpression;
    }
}
