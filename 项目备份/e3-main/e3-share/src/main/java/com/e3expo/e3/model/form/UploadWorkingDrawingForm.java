package com.e3expo.e3.model.form;

import com.e3expo.e3.model.UploadFile;
import com.e3expo.e3.validation.group.UploadWorkingDrawingGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 设计师上传施工图
 */
public class UploadWorkingDrawingForm implements Serializable {

    /**
     * 文件的路径
     */
    @NotNull(groups = {UploadWorkingDrawingGroup.Upload.class})
    private String workingDrawingPath;

    @NotNull(groups = {UploadWorkingDrawingGroup.Upload.class, UploadWorkingDrawingGroup.Confirm.class})
    private Integer orderId;

    private Integer designerId;

    private UploadFile uploadFile;

    public UploadFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
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

    public String getWorkingDrawingPath() {
        return workingDrawingPath;
    }

    public void setWorkingDrawingPath(String workingDrawingPath) {
        this.workingDrawingPath = workingDrawingPath;
    }
}
