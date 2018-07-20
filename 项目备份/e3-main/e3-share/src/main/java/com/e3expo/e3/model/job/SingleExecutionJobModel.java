package com.e3expo.e3.model.job;

import java.util.Map;

/**
 * 只执行一次的Job的Model
 */
public class SingleExecutionJobModel extends JobModel{
    /**
     * 延迟时间
     */
    private Integer delay;

    public SingleExecutionJobModel(String jobName, String jobGroup, Integer delay) {
        super(jobName, jobGroup);
        this.delay = delay;
    }

    public SingleExecutionJobModel(String jobName, String jobGroup, Map<String, Object> map, Integer delay) {
        super(jobName, jobGroup, map);
        this.delay = delay;
    }

    public Integer getDelay() {
        return delay;
    }
}
