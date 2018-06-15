package com.e3expo.mms.bean.param;

import com.e3expo.mms.Exception.ApplicationDateFormatExcepetion;
import com.e3expo.mms.bean.model.Application;
import com.e3expo.mms.config.constant.DesignConstant;

import java.text.ParseException;
public class ApplicationParam extends PageParam<Application>{

    private final String dateFormat = "yyyy-MM-dd";

    private byte cityId;
    private String applicantName;
    // 申请时间
    private String applicationTimeLowerLimit;
    private String applicationTimeUpperLimit;

    private long timeLowerLimit;
    private long timeUpperLimit;

    private byte status = -2;

    public long getTimeLowerLimit() {
        if (this.applicationTimeLowerLimit != null && this.applicationTimeLowerLimit.length() > 0) {
            try {
                return DesignConstant.APPLICATION_DATE_FORMAT.parse(applicationTimeLowerLimit).getTime();
            } catch (ParseException e) {
                throw new ApplicationDateFormatExcepetion("correct dataFormat is 'yyyy-MM-dd'");
            }
        }
        return 0;
    }


    public long getTimeUpperLimit() {
        if (this.applicationTimeUpperLimit != null && this.applicationTimeUpperLimit.length() > 0) {
            try {
                return DesignConstant.APPLICATION_DATE_FORMAT.parse(applicationTimeUpperLimit).getTime() + DesignConstant.MILLISECONDS_IN_ONE_DAY;
            } catch (ParseException e) {
                throw new ApplicationDateFormatExcepetion("correct dataFormat is 'yyyy-MM-dd'");
            }
        }
        return 0;
    }


    public byte getCityId() {
        return cityId;
    }

    public void setCityId(byte cityId) {
        this.cityId = cityId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicationTimeLowerLimit() {
        return applicationTimeLowerLimit;
    }

    public void setApplicationTimeLowerLimit(String applicationTimeLowerLimit) {
        this.applicationTimeLowerLimit = applicationTimeLowerLimit;
    }

    public String getApplicationTimeUpperLimit() {
        return applicationTimeUpperLimit;
    }

    public void setApplicationTimeUpperLimit(String applicationTimeUpperLimit) {
        this.applicationTimeUpperLimit = applicationTimeUpperLimit;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}