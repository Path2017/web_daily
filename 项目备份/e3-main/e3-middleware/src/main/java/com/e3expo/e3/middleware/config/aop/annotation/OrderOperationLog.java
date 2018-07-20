package com.e3expo.e3.middleware.config.aop.annotation;

import com.e3expo.e3.enumration.OrderOperationEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderOperationLog {
    /**
     * 方法描述
     * @return
     */
     OrderOperationEnum value();

}
