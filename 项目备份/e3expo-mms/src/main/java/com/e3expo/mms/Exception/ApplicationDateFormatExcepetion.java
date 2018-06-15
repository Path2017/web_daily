package com.e3expo.mms.Exception;

public class ApplicationDateFormatExcepetion extends RuntimeException {

    private String message;

    public ApplicationDateFormatExcepetion() {
    }

    public ApplicationDateFormatExcepetion(String s) {
        this.message = s;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApplicationDateFormatExcepetion{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}