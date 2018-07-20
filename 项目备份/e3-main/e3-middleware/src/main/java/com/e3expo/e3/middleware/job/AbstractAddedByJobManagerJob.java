package com.e3expo.e3.middleware.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class AbstractAddedByJobManagerJob implements Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getMergedJobDataMap();
        doExecute(dataMap);
    }

    protected abstract void doExecute(JobDataMap dataMap);

}
