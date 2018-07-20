package com.e3expo.e3.model;

import com.e3expo.e3.validation.group.OrderDesignerLogGroup;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Alias("orderDesignerLog")
public class OrderDesignerLog implements Serializable {

    private Integer id;
    @NotNull(groups = {OrderDesignerLogGroup.ApplyForModifyingDesign.class})
    private Integer orderId;
    private String nodeId;
    private BigDecimal price;
    private Integer designerId;
    private Integer type;
    private Integer status;
    private Integer isValid;
    private Long createTime;
    private List<UploadFile> uploadFiles;

    @NotNull(groups = {OrderDesignerLogGroup.ApplyForModifyingDesign.class})
    private String suggestion;


    public OrderDesignerLog() {
    }

    public OrderDesignerLog(Integer orderId, String nodeId, BigDecimal price, Integer designerId, Integer type) {
        this.orderId = orderId;
        this.nodeId = nodeId;
        this.price = price;
        this.designerId = designerId;
        this.type = type;
    }

    public OrderDesignerLog(Integer orderId, String nodeId, Integer designerId) {
        this.orderId = orderId;
        this.nodeId = nodeId;
        this.designerId = designerId;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
	public List<UploadFile> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<UploadFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}
	
    
}
