package com.e3expo.e3.model.view;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("webAppOrderLogView")
public class WebAppOrderLogView implements Serializable{

    private Long createTime;
    private String name;
    private String description;
    private String timeStr;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }
}
