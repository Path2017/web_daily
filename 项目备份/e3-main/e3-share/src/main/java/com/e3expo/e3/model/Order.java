package com.e3expo.e3.model;

import com.e3expo.e3.validation.group.OrderGroup;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Alias("order")
public class Order implements Serializable {

    @NotNull(groups = OrderGroup.Update.class)
    private Integer id;

    @NotNull(groups = {OrderGroup.Update.class})
    private Integer userId;

    @NotNull(groups = {OrderGroup.Insert.class})
    private String nodeId;

    @NotNull(groups = {OrderGroup.Insert.class})
    private Long createTime;

    @NotNull(groups = {OrderGroup.Insert.class})
    private Long updateTime;

    @NotNull(groups = {OrderGroup.Insert.class})
    private Integer status;

    @NotNull(groups = {OrderGroup.Insert.class})
    private Integer rfpId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRfpId() {
        return rfpId;
    }

    public void setRfpId(Integer rfpId) {
        this.rfpId = rfpId;
    }
}