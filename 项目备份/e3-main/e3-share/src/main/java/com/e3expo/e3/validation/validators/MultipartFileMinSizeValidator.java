package com.e3expo.e3.validation.validators;

import com.e3expo.e3.validation.constraints.MultipartFileMinSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFileMinSizeValidator implements ConstraintValidator<MultipartFileMinSize, MultipartFile> {

    private long value;

    @Override
    public void initialize(MultipartFileMinSize constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file != null && file.getSize() >= this.value;
    }
}
