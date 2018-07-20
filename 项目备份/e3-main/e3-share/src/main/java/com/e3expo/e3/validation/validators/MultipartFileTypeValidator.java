package com.e3expo.e3.validation.validators;

import com.e3expo.e3.enumration.UploadFileTypeEnum;
import com.e3expo.e3.validation.constraints.MultipartFileType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFileTypeValidator implements ConstraintValidator<MultipartFileType, MultipartFile> {

    private UploadFileTypeEnum[] value;


    @Override
    public void initialize(MultipartFileType constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null) {
            return true;
        }
        if (file.getOriginalFilename() == null) {
            return true;
        }
        for (UploadFileTypeEnum uploadFileTypeEnum : value) {
            if (file.getOriginalFilename().endsWith(uploadFileTypeEnum.getSuffix())) {
                return true;
            }
        }
        return false;
    }
}
