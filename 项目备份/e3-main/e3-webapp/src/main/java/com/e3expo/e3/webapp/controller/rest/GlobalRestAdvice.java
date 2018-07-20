package com.e3expo.e3.webapp.controller.rest;

import com.e3expo.e3.common.ResultCode;
import com.e3expo.e3.exceptions.ImproperVeriCodeOrMobileException;
import com.e3expo.e3.exceptions.InvalidMobileFormatException;
import com.e3expo.e3.exceptions.NotFoundVeriCodeException;
import com.e3expo.e3.exceptions.ShiroUserException;
import com.e3expo.e3.exceptions.UserException;
import com.e3expo.e3.exceptions.ValidateException;
import com.e3expo.e3.webapp.common.RestfulJsonModelAndView;

import com.e3expo.e3.webapp.controller.message.WebappErrorMessage;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;


import static org.springframework.http.HttpStatus.*;
import static com.e3expo.e3.exceptions.UserException.ErrorCode;

@RestControllerAdvice
public class GlobalRestAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    public WebappErrorMessage validationException(BindException e) {
        StringBuilder sb = new StringBuilder();
        if (e.getFieldErrors() != null) {
            for (FieldError fieldError : e.getFieldErrors()) {
                sb.append("field \"" + fieldError.getField() + "\" error: " + fieldError.getDefaultMessage() + ". ");
            }
            return new WebappErrorMessage(sb.toString());
        } else {
            return new WebappErrorMessage(e.getMessage());
        }
    }

    /**
     * 验证码不存在，超时或者过期
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NotFoundVeriCodeException.class)
    @ResponseStatus(NOT_FOUND)
    public WebappErrorMessage improperVeriCodeOrMobileException(NotFoundVeriCodeException e) {
        return new WebappErrorMessage(e.getMessage());
    }

    /**
     * 验证码校验错误无效格式异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ImproperVeriCodeOrMobileException.class)
    @ResponseStatus(FORBIDDEN)
    public WebappErrorMessage improperVeriCodeOrMobileException(ImproperVeriCodeOrMobileException e) {
        return new WebappErrorMessage(e.getMessage());
    }

    /**
     * 手机号码无效格式异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidMobileFormatException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorCode invalidMobileFormat(InvalidMobileFormatException e) {
        // TODO 提出一个ENUM
        return ErrorCode.USER_MOBILE_INVALID;
    }

    /**
     * 所有的参数不正确的异常都这么设置
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public WebappErrorMessage illegalArgs(IllegalArgumentException e) {
        // todo
        return new WebappErrorMessage(e.getMessage());
    }

    @ExceptionHandler(value = {ShiroUserException.class})
    public ModelAndView ShiroUserException(ShiroUserException ex) {
        ex.printStackTrace();
        return RestfulJsonModelAndView.buildError(ex.getCode().getValue(), ex.getCode().getDesc(), ex.getMessage());
    }

    @ExceptionHandler(value = {UserException.class})
    public ModelAndView UserException(UserException ex) {
        ex.printStackTrace();
        return RestfulJsonModelAndView.buildError(ex.getCode().getValue(), ex.getCode().getDesc(), ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public ModelAndView defaultExceptionHandle(Exception ex) {
        ex.printStackTrace();
        if (ex instanceof ValidateException) {
            ValidateException e = (ValidateException) ex;
            return RestfulJsonModelAndView.buildError(e.getCode().getValue(), e.getCode().getDesc(), e.getMessage());
        }
        return RestfulJsonModelAndView.buildError(ResultCode.SYSTEM_ERROR.getValue(), ResultCode.SYSTEM_ERROR.getDesc(), ex.getMessage());
    }
}
