package com.e3expo.e3.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("designerWorkingYears")
public class DesignerWorkingYears implements Serializable {
    private Integer id;

    private Integer splitPoint;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSplitPoint() {
        return splitPoint;
    }

    public void setSplitPoint(Integer splitPoint) {
        this.splitPoint = splitPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}