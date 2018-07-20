package com.e3expo.e3.validation.validators;

import com.e3expo.e3.validation.constraints.MultipartFileMaxSize;
import com.e3expo.e3.validation.constraints.MultipartFileMinSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFileMaxSizeValidator implements ConstraintValidator<MultipartFileMaxSize, MultipartFile> {

    private long value;

    @Override
    public void initialize(MultipartFileMaxSize constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file == null || file.getSize() < this.value;
    }
}
