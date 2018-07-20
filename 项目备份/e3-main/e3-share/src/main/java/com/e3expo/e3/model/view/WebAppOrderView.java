package com.e3expo.e3.model.view;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Alias("webAppOrderView")
public class WebAppOrderView implements Serializable{

    /**
     * RFP相关信息
     */
    private Integer orderId;
    private Long createTime;
    private String orderNo;
    private String relativeLogoPath;
    private String exhibitionName;
    private BigDecimal boothWidth;
    private BigDecimal boothLength;
    private BigDecimal budget;
    // todo 这个bid count
    private Integer bidCount;

    private String nodeId;
    private Integer status;
    private Long designDeadline;
    private Long workingDrawingDeadline;

    /**
     * designer 相关属性
     */
    private Integer isSuccess;

    /**
     * 和订单绑定的设计师
     */
    private WebAppOrderDesignerView orderDesigner;

    /**
     * 未选择设计师前的竞标列表
     */
    private List<WebAppOrderDesignerView> biddingList;

    /**
     * 每次设计稿的上传记录
     */
    private List<WebAppOrderDesignerLogView> designUploadLog;

    /**
     * order的操作日志
     */
    private List<WebAppOrderLogView> logList;


    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public WebAppOrderDesignerView getOrderDesigner() {
        return orderDesigner;
    }

    public void setOrderDesigner(WebAppOrderDesignerView orderDesigner) {
        this.orderDesigner = orderDesigner;
    }

    public List<WebAppOrderDesignerLogView> getDesignUploadLog() {
        return designUploadLog;
    }

    public void setDesignUploadLog(List<WebAppOrderDesignerLogView> designUploadLog) {
        this.designUploadLog = designUploadLog;
    }

    public List<WebAppOrderDesignerView> getBiddingList() {
        return biddingList;
    }

    public void setBiddingList(List<WebAppOrderDesignerView> biddingList) {
        this.biddingList = biddingList;
    }

    public List<WebAppOrderLogView> getLogList() {
        return logList;
    }

    public void setLogList(List<WebAppOrderLogView> logList) {
        this.logList = logList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRelativeLogoPath() {
        return relativeLogoPath;
    }

    public void setRelativeLogoPath(String relativeLogoPath) {
        this.relativeLogoPath = relativeLogoPath;
    }

    public String getExhibitionName() {
        return exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    public BigDecimal getBoothWidth() {
        return boothWidth;
    }

    public void setBoothWidth(BigDecimal boothWidth) {
        this.boothWidth = boothWidth;
    }

    public BigDecimal getBoothLength() {
        return boothLength;
    }

    public void setBoothLength(BigDecimal boothLength) {
        this.boothLength = boothLength;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Integer getBidCount() {
        return bidCount;
    }

    public void setBidCount(Integer bidCount) {
        this.bidCount = bidCount;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDesignDeadline(Long designDeadline) {
        this.designDeadline = designDeadline;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Long getDesignDeadline() {
        return designDeadline;
    }

    public Long getWorkingDrawingDeadline() {
        return workingDrawingDeadline;
    }

    public void setWorkingDrawingDeadline(Long workingDrawingDeadline) {
        this.workingDrawingDeadline = workingDrawingDeadline;
    }


}
