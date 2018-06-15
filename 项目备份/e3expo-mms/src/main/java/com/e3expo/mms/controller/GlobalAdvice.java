package com.e3expo.mms.controller;

import com.e3expo.mms.Exception.AccountIsNotExistsException;
import com.e3expo.mms.Exception.UserAlreadyLoginException;
import com.e3expo.mms.controller.message.HouseError;
import com.e3expo.mms.controller.message.HouseErrorCode;
import com.e3expo.mms.controller.message.HouseErrorMessage;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


//处理所有的异常之类的
@ControllerAdvice
public class GlobalAdvice {

//    @ExceptionHandler(UserAlreadyLoginException.class)
////    @ResponseStatus(HttpStatus.OK)
//    public String getHome() {
//        return "redirect:/index";
//    }


//    @ExceptionHandler({IncorrectCredentialsException.class, AuthenticationException.class})
//    //    @ResponseStatus(HttpStatus.OK)
//    public String loginError() {
//        return "redirect:/index";
//    }


    //帐号不存在
    @ExceptionHandler({AccountIsNotExistsException.class,
            UnknownAccountException.class
    })
//	@ResponseStatus(HttpStatus.NOT_FOUND)
    public String accountIsNotExists(Model model) {

        HouseError error = new HouseError(HouseErrorCode.ERR_CODE_USER_IS_NOT_EXISTS,
                HouseErrorMessage.ERR_STRING_UESR_IS_NOT_EXISTS);
        model.addAttribute("error", error);
        return "/login.html";
    }

    //密码错误
    @ExceptionHandler(IncorrectCredentialsException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String passwordIsNotCorrect(Model model) {

        HouseError error = new HouseError(HouseErrorCode.ERR_CODE_PASSWORD_INCORRENT,
                HouseErrorMessage.ERR_STRING_PASSWORD_INCORRECT
        );
        model.addAttribute("error", error);
        return "/login.html";
    }


    //登录失败
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String badLogin(Model model) {
        HouseError error = new HouseError(HouseErrorCode.ERR_CODE_LOGIN_FAILED,
                HouseErrorMessage.ERR_STRING_LOGIN_FAILED
        );
        model.addAttribute("error", error);
        return "/login.html";
    }

    @ExceptionHandler(value = {Exception.class})
    public String handleOtherExceptions() {
        return "error";
    }

}
