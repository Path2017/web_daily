package com.e3expo.e3.webapp.controller.rest;


import com.e3expo.e3.enumration.DesignFileTypeEnum;
import com.e3expo.e3.model.UploadFile;
import com.e3expo.e3.model.UserInfoFile;
import com.e3expo.e3.model.form.UploadDesignForm;
import com.e3expo.e3.validation.group.UploadDesignGroup;
import com.e3expo.e3.webapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * 设计师抢单中心Rest控制
 */
@RestController("designerPersonalCenterRestController")
@RequestMapping("designer")
public class DesignerPersonalCenterRestController {

    @Autowired
    private OrderService orderService;




}

