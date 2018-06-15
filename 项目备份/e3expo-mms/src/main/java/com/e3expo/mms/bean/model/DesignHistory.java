package com.e3expo.mms.bean.model;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

import static com.e3expo.mms.config.constant.DesignConstant.DESIGN_HISTORY_DATE_FORMAT;
@Alias("designHistory")
public class DesignHistory implements Serializable {

    private final String dateFormat = "yyyy/MM/dd";

    private int id;
    private long createTime;
    private int userId;
    private int designId;
    private byte operationId;
//    private String remoteIp;
    private String timeStr;

    private DesignOperation operation;
    private User user;
    private City city;

    public DesignHistory(long createTime, int userId, int designId, byte operationId) {
        this.createTime = createTime;
        this.userId = userId;
        this.designId = designId;
        this.operationId = operationId;
    }

    public DesignHistory() {
    }

    public String getTimeStr() {
        return DESIGN_HISTORY_DATE_FORMAT.format(new Date(createTime));
    }


    public DesignOperation getOperation() {
        return operation;
    }

    public void setOperation(DesignOperation operation) {
        this.operation = operation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDesignId() {
        return designId;
    }

    public void setDesignId(int designId) {
        this.designId = designId;
    }

    public byte getOperationId() {
        return operationId;
    }

    public void setOperationId(byte operationId) {
        this.operationId = operationId;
    }

    public Date getCreateTimeDate() {
        return new Date(createTime);
    }

    public String getDateFormat() {
        return dateFormat;
    }
}