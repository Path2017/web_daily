package com.e3expo.mms.controller.message;

public class RestfulData<T> {
    private final int code;
    private final T data;

    public RestfulData(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}
