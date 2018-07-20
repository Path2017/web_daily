package com.e3expo.e3.model.view;

import java.io.Serializable;
import java.util.List;

public class DesignImagesView implements Serializable {
    private String leftViewPath;
    private String rightViewPath;
    private String planViewPath;
    private String frontGridViewPath;
    private String planGridViewPath;
    private List<String> otherViewsPath;


    public String getLeftViewPath() {
        return leftViewPath;
    }

    public void setLeftViewPath(String leftViewPath) {
        this.leftViewPath = leftViewPath;
    }

    public String getRightViewPath() {
        return rightViewPath;
    }

    public void setRightViewPath(String rightViewPath) {
        this.rightViewPath = rightViewPath;
    }

    public String getPlanViewPath() {
        return planViewPath;
    }

    public void setPlanViewPath(String planViewPath) {
        this.planViewPath = planViewPath;
    }

    public String getFrontGridViewPath() {
        return frontGridViewPath;
    }

    public void setFrontGridViewPath(String frontGridViewPath) {
        this.frontGridViewPath = frontGridViewPath;
    }

    public String getPlanGridViewPath() {
        return planGridViewPath;
    }

    public void setPlanGridViewPath(String planGridViewPath) {
        this.planGridViewPath = planGridViewPath;
    }

    public List<String> getOtherViewsPath() {
        return otherViewsPath;
    }

    public void setOtherViewsPath(List<String> otherViewsPath) {
        this.otherViewsPath = otherViewsPath;
    }
}
