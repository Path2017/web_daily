package com.e3expo.e3.model.form;

import com.e3expo.e3.model.UploadFile;
import com.e3expo.e3.validation.group.UploadDesignGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 设计师上传设计稿
 */
public class UploadDesignForm implements Serializable {

    /**
     * 图片的路径
     */
    @NotNull(groups = {UploadDesignGroup.Upload.class})
    private String designLeftView;
    @NotNull(groups = {UploadDesignGroup.Upload.class})
    private String designRightView;
    @NotNull(groups = {UploadDesignGroup.Upload.class})
    private String designPlanView;
    @NotNull(groups = {UploadDesignGroup.Upload.class})
    private String designFrontGridView;
    @NotNull(groups = {UploadDesignGroup.Upload.class})
    private String designPlanGridView;
    @Size(min = 0, max = 10, groups = {UploadDesignGroup.Upload.class})
    private String[] designOtherView;
    @NotNull(groups = {UploadDesignGroup.Upload.class})
    private String design3DMax;
    @NotNull(groups = {UploadDesignGroup.Upload.class})
    private Integer orderId;

    private Integer designerId;

    private List<UploadFile> uploadFiles;

    public List<UploadFile> getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(List<UploadFile> uploadFiles) {
        this.uploadFiles = uploadFiles;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public String getDesignLeftView() {
        return designLeftView;
    }

    public void setDesignLeftView(String designLeftView) {
        this.designLeftView = designLeftView;
    }

    public String getDesignRightView() {
        return designRightView;
    }

    public void setDesignRightView(String designRightView) {
        this.designRightView = designRightView;
    }

    public String getDesignPlanView() {
        return designPlanView;
    }

    public void setDesignPlanView(String designPlanView) {
        this.designPlanView = designPlanView;
    }

    public String getDesignFrontGridView() {
        return designFrontGridView;
    }

    public void setDesignFrontGridView(String designFrontGridView) {
        this.designFrontGridView = designFrontGridView;
    }

    public String getDesignPlanGridView() {
        return designPlanGridView;
    }

    public void setDesignPlanGridView(String designPlanGridView) {
        this.designPlanGridView = designPlanGridView;
    }

    public String[] getDesignOtherView() {
        return designOtherView;
    }

    public void setDesignOtherView(String[] designOtherView) {
        this.designOtherView = designOtherView;
    }

    public String getDesign3DMax() {
        return design3DMax;
    }

    public void setDesign3DMax(String design3DMax) {
        this.design3DMax = design3DMax;
    }
}
