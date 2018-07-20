package com.e3expo.e3.validation.validators;

import com.e3expo.e3.enumration.UploadFileTypeEnum;
import com.e3expo.e3.validation.constraints.MultipartFileArrayType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFileArrayTypeValidator implements ConstraintValidator<MultipartFileArrayType, MultipartFile[]> {

    private UploadFileTypeEnum[] value;

    @Override
    public void initialize(MultipartFileArrayType constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }


    @Override
    public boolean isValid(MultipartFile[] files, ConstraintValidatorContext context) {
        if (files == null) {
            return true;
        }
        for (MultipartFile file : files) {
            if (file == null) {
                continue;
            }
            if (file.getOriginalFilename() == null) {
                continue;
            }
            for (UploadFileTypeEnum uploadFileTypeEnum : value) {
                if (file.getOriginalFilename().endsWith(uploadFileTypeEnum.getSuffix())) {
                    return true;
                }
            }

        }
        return false;
    }
}
