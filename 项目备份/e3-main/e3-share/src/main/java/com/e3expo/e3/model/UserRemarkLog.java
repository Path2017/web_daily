package com.e3expo.e3.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("userRemarkLog")
public class UserRemarkLog implements Serializable{
    private Integer id;

    private Integer userId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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