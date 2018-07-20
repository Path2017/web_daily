package com.e3expo.e3.model.form;

import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.validation.constraints.MultipartFileMaxSize;
import com.e3expo.e3.validation.constraints.MultipartFileType;
import org.springframework.web.multipart.MultipartFile;

import static com.e3expo.e3.common.Constants.MEGABYTES;
import static com.e3expo.e3.enumration.UploadFileTypeEnum.*;
import static com.e3expo.e3.validation.group.RfpGroup.*;
/**
 *
 */
public class RfpBaseInfoForm extends Rfp implements MultipartDataForm{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8270501709137895550L;
	/**
     * 展会平面图
     */
	@MultipartFileMaxSize(groups = {Create.class}, value = 50 * MEGABYTES)
    @MultipartFileType(value = {JPEG, JPG, PNG}, groups = {Create.class})
    private MultipartFile exhibitionFloorPlan;

    public MultipartFile getExhibitionFloorPlan() {
        return exhibitionFloorPlan;
    }

    public void setExhibitionFloorPlan(MultipartFile exhibitionFloorPlan) {
        this.exhibitionFloorPlan = exhibitionFloorPlan;
    }

    @Override
    public void clearMultiFormField() {
        this.exhibitionFloorPlan = null;
    }
}
