package com.e3expo.e3.validation.constraints;

import com.e3expo.e3.enumration.UploadFileTypeEnum;
import com.e3expo.e3.validation.validators.MultipartFileTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验上传文件类型的校验器，如果字段不为空而且有文件名，则必须是注解中配置的违建类型
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MultipartFileTypeValidator.class)
public @interface MultipartFileType {

    //下面这两个属性必须添加
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "文件格式必须是如下：{value}。";

    UploadFileTypeEnum[] value();

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        MultipartFileType[] value();
    }

}
