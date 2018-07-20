package com.e3expo.e3.model.job;

import java.util.Map;

public abstract class JobModel {
	/**
     * jobName
	 */
    private String jobName;
    /**
     * jobGroup
	 */
	private String jobGroup;

    /**
	 * 封装job使用的参数
	 */
	private Map<String, Object> jobDataMap;

	/**
	 * 联合name和Group才能组成唯一的key，暂停，更新，取消等都使用这个key进行
	 *
	 * @param jobName
	 * @param jobGroup
	 */
	protected JobModel(String jobName, String jobGroup) {
		this.jobName = jobName;
		this.jobGroup = jobGroup;
	}
	protected JobModel(String jobName, String jobGroup, Map<String, Object> map) {
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.jobDataMap = map;
	}

	public String getJobName() {
		return jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public Map<String, Object> getJobDataMap() {
		return jobDataMap;
	}
}
