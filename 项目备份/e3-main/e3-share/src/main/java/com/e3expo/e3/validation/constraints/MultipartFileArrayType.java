package com.e3expo.e3.validation.constraints;

import com.e3expo.e3.enumration.UploadFileTypeEnum;
import com.e3expo.e3.validation.validators.MultipartFileArrayTypeValidator;

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
 * 文件数组的文件类型的校验器
 * 校验MultipartFile[]中如果有元素不为空，而且有文件名，则文件名必须属于注解中配置的文件类型
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MultipartFileArrayTypeValidator.class)
public @interface MultipartFileArrayType {

    //下面这两个属性必须添加
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "文件格式必须是如下：{value}。";

    UploadFileTypeEnum[] value();

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        MultipartFileArrayType[] value();
    }

}
