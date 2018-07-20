package com.e3expo.e3.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("userAuditLog")
public class UserAuditLog implements Serializable{
    private Integer id;

    private Integer userAuditId;

    private Integer adminUserId;

    private Long createTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserAuditId() {
        return userAuditId;
    }

    public void setUserAuditId(Integer userAuditId) {
        this.userAuditId = userAuditId;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}