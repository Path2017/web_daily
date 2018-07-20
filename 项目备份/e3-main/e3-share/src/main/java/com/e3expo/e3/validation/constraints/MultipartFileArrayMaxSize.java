package com.e3expo.e3.validation.constraints;

import com.e3expo.e3.validation.validators.MultipartFileArrayMaxSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义的文件大小的最大值校验器
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MultipartFileArrayMaxSizeValidator.class)
public @interface MultipartFileArrayMaxSize {

    //下面这两个属性必须添加
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "上传文件不能大于{value}字节";

    long value();

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        MultipartFileArrayMaxSize[] value();
    }

}
