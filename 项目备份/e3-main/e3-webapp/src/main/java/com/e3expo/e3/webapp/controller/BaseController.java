package com.e3expo.e3.webapp.controller;

import com.e3expo.e3.model.User;
import org.apache.shiro.SecurityUtils;

public class BaseController {

    protected User getUserFromSession(){
        return (User) SecurityUtils.getSubject().getSession().getAttribute("user");
    }
}
