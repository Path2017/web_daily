package com.e3expo.e3.model.form;

import com.e3expo.e3.model.RfpDetail;
import com.e3expo.e3.validation.constraints.MultipartFileMaxSize;
import com.e3expo.e3.validation.constraints.MultipartFileType;
import com.e3expo.e3.validation.group.RfpDetailGroup;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

import static com.e3expo.e3.common.Constants.MEGABYTES;
import static com.e3expo.e3.enumration.UploadFileTypeEnum.*;

/**
 *
 */
public class RfpDetailForm extends RfpDetail implements MultipartDataForm{


    private Integer userId;

    /**
     * 相关logo
     */
    @NotNull(groups = {RfpDetailGroup.Create.class})
    @MultipartFileType(groups = {RfpDetailGroup.Create.class}, value = {AI,PNG,JPG,JPEG,PDF,ZIP,RAR})
    @MultipartFileMaxSize(groups = {RfpDetailGroup.Create.class}, value = 200 * MEGABYTES)
    private MultipartFile relativeLogo;

    @MultipartFileType(groups = {RfpDetailGroup.Create.class}, value = {AI,PNG,JPG,JPEG,PDF,ZIP,RAR})
    @MultipartFileMaxSize(groups = {RfpDetailGroup.Create.class}, value = 200 * MEGABYTES)
    private MultipartFile referenceCiStandard;

    @MultipartFileType(groups = {RfpDetailGroup.Create.class}, value = {AI,PNG,JPG,JPEG,PDF,ZIP,RAR})
    @MultipartFileMaxSize(groups = {RfpDetailGroup.Create.class}, value = 200 * MEGABYTES)
    private MultipartFile referencePoster;

    @MultipartFileType(groups = {RfpDetailGroup.Create.class}, value = {AI,PNG,JPG,JPEG,PDF,ZIP,RAR})
    @MultipartFileMaxSize(groups = {RfpDetailGroup.Create.class}, value = 200 * MEGABYTES)
    private MultipartFile referenceHistoryGraph;

    @MultipartFileType(groups = {RfpDetailGroup.Create.class}, value = {AI,PNG,JPG,JPEG,PDF,ZIP,RAR})
    @MultipartFileMaxSize(groups = {RfpDetailGroup.Create.class}, value = 200 * MEGABYTES)
    private MultipartFile referenceOtherFile;

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public MultipartFile getRelativeLogo() {
        return relativeLogo;
    }

    public void setRelativeLogo(MultipartFile relativeLogo) {
        this.relativeLogo = relativeLogo;
    }

    public MultipartFile getReferenceCiStandard() {
        return referenceCiStandard;
    }

    public void setReferenceCiStandard(MultipartFile referenceCiStandard) {
        this.referenceCiStandard = referenceCiStandard;
    }

    public MultipartFile getReferencePoster() {
        return referencePoster;
    }

    public void setReferencePoster(MultipartFile referencePoster) {
        this.referencePoster = referencePoster;
    }

    public MultipartFile getReferenceHistoryGraph() {
        return referenceHistoryGraph;
    }

    public void setReferenceHistoryGraph(MultipartFile referenceHistoryGraph) {
        this.referenceHistoryGraph = referenceHistoryGraph;
    }

    public MultipartFile getReferenceOtherFile() {
        return referenceOtherFile;
    }

    public void setReferenceOtherFile(MultipartFile referenceOtherFile) {
        this.referenceOtherFile = referenceOtherFile;
    }

    @Override
    public void clearMultiFormField() {
        this.relativeLogo = null;
        this.referenceCiStandard = null;
        this.referencePoster = null;
        this.referenceHistoryGraph = null;
        this.referenceOtherFile = null;
    }
}
