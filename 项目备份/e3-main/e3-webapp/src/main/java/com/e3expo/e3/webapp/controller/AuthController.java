package com.e3expo.e3.webapp.controller;

import com.e3expo.e3.model.User;
import com.e3expo.e3.validation.group.UserGroup;
import com.e3expo.e3.webapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 注册登录相关控制器
 * 这个类里没有RESTful的接口，主要处理的是使用Thymeleaf做显示的请求。
 */
@Controller("authController")
public class AuthController {

    @Autowired
    private AuthService authService;





    /**
     * 获取设计师注册页面
     *
     * @return
     */
    @RequestMapping(value = "/register/designer", method = GET)
    public String designerRegisterPage(Model model) throws Exception {
        // 设计师页面
//        List<Province> provinceList = authService.getAllProvinces();
        List<String> workingYearList = authService.listDesignerWorkingYears();
//        List<Bank> bankList = authService.getAllBanks();

//        model.addAttribute("provinceList", provinceList);
        model.addAttribute("workingYearsList", workingYearList);
//        model.addAttribute("bankList", bankList);
        return "designer_register";
    }

    /**
     * 获取设计师工作室注册页面
     *
     * @return
     */
    @RequestMapping(value = "/register/designerStudio", method = GET)
    public String designerStudioRegisterPage() throws Exception {
        return "registered";
    }

    /**
     * 获取展览公司注册页面
     *
     * @return
     */
    @RequestMapping(value = "/register/exhibitionCompany", method = GET)
    public String exhibitionCompanyRegisterPage() throws Exception {
        return "user_register";
    }

}
