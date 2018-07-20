package com.e3expo.e3.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("userAuditRemarkLog")
public class UserAuditRemarkLog implements Serializable{
    private Integer id;

    private Integer userAuditId;

    private Integer adminUserId;

    private String remark;

    private Long createTime;
    
    private OsUser osUser;
    
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public OsUser getOsUser() {
		return osUser;
	}

	public void setOsUser(OsUser osUser) {
		this.osUser = osUser;
	}
    
}