package com.e3expo.e3.middleware.config.hessian.serializer;

import com.caucho.hessian.io.AbstractStringValueDeserializer;

import java.math.BigDecimal;

public class BigDecimalDeserializer extends AbstractStringValueDeserializer {

    @Override
    public Class getType() {
        return BigDecimal.class;
    }

    @Override
    protected Object create(String value) {
        if (null != value) {
            return new BigDecimal(value);
        } else {
            return null;
        }
    }
}