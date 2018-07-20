package com.e3expo.e3.validation.validators;

import com.e3expo.e3.validation.constraints.MultipartFileArrayMaxSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFileArrayMaxSizeValidator implements ConstraintValidator<MultipartFileArrayMaxSize, MultipartFile[]> {

    private long value;


    @Override
    public void initialize(MultipartFileArrayMaxSize constraintAnnotation) {
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
            if (file.getSize() > this.value) {
                return false;
            }

        }
        return true;
    }
}
