package com.e3expo.mms.controller;

import com.e3expo.mms.Exception.AccountIsNullException;
import com.e3expo.mms.Exception.EmptyPasswordException;
import com.e3expo.mms.Exception.PasswordCannotBeNullException;
import com.e3expo.mms.Exception.UserIsNotInSessionException;
import com.e3expo.mms.Exception.UserIsNotLoginException;
import com.e3expo.mms.bean.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController extends ParentController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("resetPassword")
    public String resetPassword(String newPassword) throws UserIsNotInSessionException, UserIsNotLoginException {
        if (newPassword == null || newPassword.length() == 0) {
            throw new EmptyPasswordException();
        }
        // 修改密码
        userService.updatePassword(newPassword);
        return "redirect:/design/list";
    }

    @GetMapping("/login.html")
    public String login() throws Exception {

//        User user = getUserFromSession();
//
//        if (user != null) {
//            throw new UserAlreadyLoginException();
//        }
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("password") String password,
            Model model) throws Exception {

        if (phoneNumber.trim().length() <= 0) {
            throw new AccountIsNullException();
        }

        if (password.trim().length() <= 0) {
            throw new PasswordCannotBeNullException();
        }
        userService.login(phoneNumber, password);
        model.addAttribute("phoneNumber", phoneNumber);
        return "redirect:/design/list";
    }


    @GetMapping("/logout")
    public String logout() throws Exception {
        Subject subject = SecurityUtils.getSubject();

        subject.logout();

        return "redirect:/login.html";
    }






}
