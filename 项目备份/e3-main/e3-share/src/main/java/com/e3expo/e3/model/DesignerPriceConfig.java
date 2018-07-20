package com.e3expo.e3.model;

import com.e3expo.e3.enumration.DesignerPriceConfigEnum;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Alias("designerPriceConfig")
public class DesignerPriceConfig {

    private Integer id;
    private String nodeId;
    private Integer designerId;
    private BigDecimal price;
    private Integer type;
    private Integer isValid;

    private Long createTime;
    private Long updateTime;

    public DesignerPriceConfig() {
    }

    public DesignerPriceConfig(String nodeId, Integer designerId, BigDecimal price, Integer type) {
        this.nodeId = nodeId;
        this.designerId = designerId;
        this.price = price;
        this.type = type;
    }

    public DesignerPriceConfig(DesignerPriceConfigEnum designPriceConfig, BigDecimal price, Integer designerId) {
        this(designPriceConfig.getNodeId(), designerId, price,designPriceConfig.getType());
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

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
