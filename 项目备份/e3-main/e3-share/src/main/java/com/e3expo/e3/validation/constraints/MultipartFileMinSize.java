package com.e3expo.e3.validation.constraints;

import com.e3expo.e3.validation.validators.MultipartFileMinSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义的文件大小的最小值校验器
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MultipartFileMinSizeValidator.class)
public @interface MultipartFileMinSize {

    //下面这两个属性必须添加
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "上传文件不能小于{value}字节";

    long value();

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        MultipartFileMinSize[] value();
    }

}
