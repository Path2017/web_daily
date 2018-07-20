package com.e3expo.e3.model;

import com.e3expo.e3.validation.group.RfpDetailGroup;
import com.e3expo.e3.validation.group.RfpGroup;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Alias("rfpDetail")
public class RfpDetail implements Serializable {

    @NotNull(groups = {RfpDetailGroup.UpdateDraft.class})
    private Integer id;

    @NotNull(groups = {RfpDetailGroup.Create.class, RfpDetailGroup.UpdateDraft.class, RfpDetailGroup.CreateDraft.class})
    private Integer rfpId;

    /**
     * 开口
     */
    @NotNull(groups = {RfpDetailGroup.Create.class})
    private Integer open;
    /**
     * 特殊需求：1双层，2吊顶
     */
    private Integer specialDemand;
    /**
     * 主体结构
     */
    @NotNull(groups = {RfpDetailGroup.Create.class})
    private Integer structure;
    private String structureRemark;
    /**
     * 材质
     */
    @NotNull(groups = {RfpDetailGroup.Create.class})
    private Integer material;
    /**
     * 主体颜色
     */
    @NotNull(groups = {RfpDetailGroup.Create.class})
    private String mainColor;
    /**
     * 辅助颜色
     */
    private String subColor;
    private String colorRemark;
    private String relativeLogoPath;
    private String specialDemandAndWarnings;
    private String referenceCiStandardPath;
    private String referencePosterPath;
    private String referenceHistoryGraphPath;
    private String referenceOtherFilePath;
    private Integer receptionAreaNum;
    private Integer productAreaNum;
    private Integer talkDeskNum;
    private Integer talkChairNum;
    private String talkRemark;
    private Integer seal;
    private String electronicsLedNum;
    private String electronicsIpadNum;
    private String electronicsRemark;
    private String otherEquipment;
    private String otherEquipmentRemark;
    private String storageAreaNum;
    private String storageAreaLength;
    private String storageAreaWidth;
    private String storageAreaRemark;
    private String activityAreaNum;
    private String activityAreaLength;
    private String activityAreaWidth;
    private String activityAreaRemark;
    private String exhibitionGoal;
    private String exhibitionGoalRemark;
    private String exhibitionAudience;
    private String exhibitionAudienceRemark;
    private String productDescription;
    private String rival;
    private String slogan;
    private String designConceptAdvice;
    private Long posterNum;
    private Long updateTime;
    private Integer status;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRfpId() {
        return rfpId;
    }

    public void setRfpId(Integer rfpId) {
        this.rfpId = rfpId;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getStructure() {
        return structure;
    }

    public void setStructure(Integer structure) {
        this.structure = structure;
    }

    public String getStructureRemark() {
        return structureRemark;
    }

    public void setStructureRemark(String structureRemark) {
        this.structureRemark = structureRemark;
    }

    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public String getSubColor() {
        return subColor;
    }

    public void setSubColor(String subColor) {
        this.subColor = subColor;
    }

    public String getColorRemark() {
        return colorRemark;
    }

    public void setColorRemark(String colorRemark) {
        this.colorRemark = colorRemark;
    }

    public String getRelativeLogoPath() {
        return relativeLogoPath;
    }

    public void setRelativeLogoPath(String relativeLogoPath) {
        this.relativeLogoPath = relativeLogoPath;
    }

    public String getSpecialDemandAndWarnings() {
        return specialDemandAndWarnings;
    }

    public void setSpecialDemandAndWarnings(String specialDemandAndWarnings) {
        this.specialDemandAndWarnings = specialDemandAndWarnings;
    }

    public String getReferenceCiStandardPath() {
        return referenceCiStandardPath;
    }

    public void setReferenceCiStandardPath(String referenceCiStandardPath) {
        this.referenceCiStandardPath = referenceCiStandardPath;
    }

    public String getReferencePosterPath() {
        return referencePosterPath;
    }

    public void setReferencePosterPath(String referencePosterPath) {
        this.referencePosterPath = referencePosterPath;
    }

    public String getReferenceHistoryGraphPath() {
        return referenceHistoryGraphPath;
    }

    public void setReferenceHistoryGraphPath(String referenceHistoryGraphPath) {
        this.referenceHistoryGraphPath = referenceHistoryGraphPath;
    }

    public String getReferenceOtherFilePath() {
        return referenceOtherFilePath;
    }

    public void setReferenceOtherFilePath(String referenceOtherFilePath) {
        this.referenceOtherFilePath = referenceOtherFilePath;
    }

    public Integer getReceptionAreaNum() {
        return receptionAreaNum;
    }

    public void setReceptionAreaNum(Integer receptionAreaNum) {
        this.receptionAreaNum = receptionAreaNum;
    }

    public Integer getProductAreaNum() {
        return productAreaNum;
    }

    public void setProductAreaNum(Integer productAreaNum) {
        this.productAreaNum = productAreaNum;
    }

    public Integer getTalkDeskNum() {
        return talkDeskNum;
    }

    public void setTalkDeskNum(Integer talkDeskNum) {
        this.talkDeskNum = talkDeskNum;
    }

    public Integer getTalkChairNum() {
        return talkChairNum;
    }

    public void setTalkChairNum(Integer talkChairNum) {
        this.talkChairNum = talkChairNum;
    }

    public String getTalkRemark() {
        return talkRemark;
    }

    public void setTalkRemark(String talkRemark) {
        this.talkRemark = talkRemark;
    }

    public Integer getSeal() {
        return seal;
    }

    public void setSeal(Integer seal) {
        this.seal = seal;
    }

    public String getElectronicsLedNum() {
        return electronicsLedNum;
    }

    public void setElectronicsLedNum(String electronicsLedNum) {
        this.electronicsLedNum = electronicsLedNum;
    }

    public String getElectronicsIpadNum() {
        return electronicsIpadNum;
    }

    public void setElectronicsIpadNum(String electronicsIpadNum) {
        this.electronicsIpadNum = electronicsIpadNum;
    }

    public String getElectronicsRemark() {
        return electronicsRemark;
    }

    public void setElectronicsRemark(String electronicsRemark) {
        this.electronicsRemark = electronicsRemark;
    }

    public String getOtherEquipment() {
        return otherEquipment;
    }

    public void setOtherEquipment(String otherEquipment) {
        this.otherEquipment = otherEquipment;
    }

    public String getOtherEquipmentRemark() {
        return otherEquipmentRemark;
    }

    public void setOtherEquipmentRemark(String otherEquipmentRemark) {
        this.otherEquipmentRemark = otherEquipmentRemark;
    }

    public String getStorageAreaNum() {
        return storageAreaNum;
    }

    public void setStorageAreaNum(String storageAreaNum) {
        this.storageAreaNum = storageAreaNum;
    }

    public String getStorageAreaLength() {
        return storageAreaLength;
    }

    public void setStorageAreaLength(String storageAreaLength) {
        this.storageAreaLength = storageAreaLength;
    }

    public String getStorageAreaWidth() {
        return storageAreaWidth;
    }

    public void setStorageAreaWidth(String storageAreaWidth) {
        this.storageAreaWidth = storageAreaWidth;
    }

    public String getStorageAreaRemark() {
        return storageAreaRemark;
    }

    public void setStorageAreaRemark(String storageAreaRemark) {
        this.storageAreaRemark = storageAreaRemark;
    }

    public String getActivityAreaNum() {
        return activityAreaNum;
    }

    public void setActivityAreaNum(String activityAreaNum) {
        this.activityAreaNum = activityAreaNum;
    }

    public String getActivityAreaLength() {
        return activityAreaLength;
    }

    public void setActivityAreaLength(String activityAreaLength) {
        this.activityAreaLength = activityAreaLength;
    }

    public String getActivityAreaWidth() {
        return activityAreaWidth;
    }

    public void setActivityAreaWidth(String activityAreaWidth) {
        this.activityAreaWidth = activityAreaWidth;
    }

    public String getActivityAreaRemark() {
        return activityAreaRemark;
    }

    public void setActivityAreaRemark(String activityAreaRemark) {
        this.activityAreaRemark = activityAreaRemark;
    }

    public String getExhibitionGoal() {
        return exhibitionGoal;
    }

    public void setExhibitionGoal(String exhibitionGoal) {
        this.exhibitionGoal = exhibitionGoal;
    }

    public String getExhibitionGoalRemark() {
        return exhibitionGoalRemark;
    }

    public void setExhibitionGoalRemark(String exhibitionGoalRemark) {
        this.exhibitionGoalRemark = exhibitionGoalRemark;
    }

    public String getExhibitionAudience() {
        return exhibitionAudience;
    }

    public void setExhibitionAudience(String exhibitionAudience) {
        this.exhibitionAudience = exhibitionAudience;
    }

    public String getExhibitionAudienceRemark() {
        return exhibitionAudienceRemark;
    }

    public void setExhibitionAudienceRemark(String exhibitionAudienceRemark) {
        this.exhibitionAudienceRemark = exhibitionAudienceRemark;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getRival() {
        return rival;
    }

    public void setRival(String rival) {
        this.rival = rival;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getDesignConceptAdvice() {
        return designConceptAdvice;
    }

    public void setDesignConceptAdvice(String designConceptAdvice) {
        this.designConceptAdvice = designConceptAdvice;
    }

    public Long getPosterNum() {
        return posterNum;
    }

    public void setPosterNum(Long posterNum) {
        this.posterNum = posterNum;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSpecialDemand() {
        return specialDemand;
    }

    public void setSpecialDemand(Integer specialDemand) {
        this.specialDemand = specialDemand;
    }
}