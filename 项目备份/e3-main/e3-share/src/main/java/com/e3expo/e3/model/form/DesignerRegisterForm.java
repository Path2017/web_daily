package com.e3expo.e3.model.form;

import com.e3expo.e3.model.UserInfoFile;
import com.e3expo.e3.model.User;
import com.e3expo.e3.validation.constraints.MultipartFileArrayMaxSize;
import com.e3expo.e3.validation.constraints.MultipartFileArrayType;
import com.e3expo.e3.validation.constraints.MultipartFileMaxSize;
import com.e3expo.e3.validation.constraints.MultipartFileType;
import com.e3expo.e3.validation.group.UserGroup;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import static com.e3expo.e3.common.Constants.MEGABYTES;
import static com.e3expo.e3.enumration.UploadFileTypeEnum.*;

/**
 * 对应设计师注册表单：包含User中的基本信息，Designer中的一些设计师特有的信息，还有一个包含DesignerImage的列表，以及对应图片列表的
 * 图片的base64编码的字符串列表
 */
public class DesignerRegisterForm extends User implements Serializable, MultipartDataForm{

    @NotNull(groups = {UserGroup.RegisterDesigner.class})
    @MultipartFileArrayMaxSize(groups = {UserGroup.RegisterDesigner.class}, value = 50 * MEGABYTES)
    @MultipartFileArrayType(groups = {UserGroup.RegisterDesigner.class}, value = {JPG, PNG, PDF, JPEG})
    private MultipartFile[] representativeWork;

    @MultipartFileArrayMaxSize(groups = {UserGroup.RegisterDesigner.class}, value = 50 * MEGABYTES)
    @MultipartFileArrayType(groups = {UserGroup.RegisterDesigner.class}, value = {JPG, PNG, PDF, JPEG})
    private MultipartFile[] education;

    @MultipartFileArrayMaxSize(groups = {UserGroup.RegisterDesigner.class}, value = 50 * MEGABYTES)
    @MultipartFileArrayType(groups = {UserGroup.RegisterDesigner.class}, value = {JPG, PNG, PDF, JPEG})
    private MultipartFile[] title;

    @MultipartFileArrayMaxSize(groups = {UserGroup.RegisterDesigner.class}, value = 50 * MEGABYTES)
    @MultipartFileArrayType(groups = {UserGroup.RegisterDesigner.class}, value = {JPG, PNG, PDF, JPEG})
    private MultipartFile[] award;

    @NotNull(groups = {UserGroup.RegisterDesigner.class})
    @MultipartFileMaxSize(groups = {UserGroup.RegisterDesigner.class}, value = 50 * MEGABYTES)
    @MultipartFileType(groups = {UserGroup.RegisterDesigner.class}, value = {JPG, PNG, PDF, JPEG})
    private MultipartFile idCardFront;

    @NotNull(groups = {UserGroup.RegisterDesigner.class})
    @MultipartFileMaxSize(groups = {UserGroup.RegisterDesigner.class}, value = 50 * MEGABYTES)
    @MultipartFileType(groups = {UserGroup.RegisterDesigner.class}, value = {JPG, PNG, PDF, JPEG})
    private MultipartFile idCardBack;
    /**
     * 包含各类型图片信息的列表
     */
    private List<UserInfoFile> imageInfoList;

    public DesignerRegisterForm() {
    }

    public DesignerRegisterForm(User user) {
        assert user != null;
        super.setMobile(user.getMobile());
        super.setPassword(user.getPassword());
        super.setCompanyName(user.getCompanyName());
        super.setName(user.getName());
        super.setEmail(user.getEmail());
        super.setQq(user.getQq());
        super.setProvinceId(user.getProvinceId());
        super.setCityId(user.getCityId());
    }


    public MultipartFile[] getRepresentativeWork() {
        return representativeWork;
    }

    public void setRepresentativeWork(MultipartFile[] representativeWork) {
        this.representativeWork = representativeWork;
    }

    public MultipartFile[] getEducation() {
        return education;
    }

    public void setEducation(MultipartFile[] education) {
        this.education = education;
    }

    public MultipartFile[] getTitle() {
        return title;
    }

    public void setTitle(MultipartFile[] title) {
        this.title = title;
    }

    public MultipartFile[] getAward() {
        return award;
    }

    public void setAward(MultipartFile[] award) {
        this.award = award;
    }

    public MultipartFile getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(MultipartFile idCardFront) {
        this.idCardFront = idCardFront;
    }

    public MultipartFile getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(MultipartFile idCardBack) {
        this.idCardBack = idCardBack;
    }

    public List<UserInfoFile> getImageInfoList() {
        return imageInfoList;
    }

    public void setImageInfoList(List<UserInfoFile> imageInfoList) {
        this.imageInfoList = imageInfoList;
    }

    /**
     * 设置DesignerImageList中每一条记录中的UserId
     *
     * @param userId 用户ID
     */
    public void setUserIdToDesignerImages(Integer userId) {
        if (this.imageInfoList != null) {
            for (UserInfoFile userInfoFile : imageInfoList) {
                userInfoFile.setUserId(userId);
            }
        }
    }

    /**
     * 设置DesignerImageList中每一条记录中的createTime
     *
     * @param createTime 创建时间
     */
    public void setCreateTimeToDesignerImages(long createTime) {
        if (this.imageInfoList != null) {
            for (UserInfoFile userInfoFile : imageInfoList) {
                userInfoFile.setCreateTime(createTime);
            }
        }
    }

    /**
     * 设置DesignerImageList中每一条记录中的updateTime
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTimeToDesignerImages(long updateTime) {
        if (this.imageInfoList != null) {
            for (UserInfoFile userInfoFile : imageInfoList) {
                userInfoFile.setUpdateTime(updateTime);
            }
        }
    }

    @Override
    public void clearMultiFormField() {
        this.idCardBack = null;
        this.idCardFront = null;
        this.award = null;
        this.education = null;
        this.representativeWork = null;
        this.title = null;
    }
}
