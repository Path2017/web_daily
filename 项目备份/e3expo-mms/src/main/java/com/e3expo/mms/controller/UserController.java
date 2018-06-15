package com.e3expo.mms.controller;

import com.e3expo.mms.Exception.AccountIsAlreadyRegisteredException;
import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.UserListParam;
import com.e3expo.mms.bean.service.UserService;
import com.e3expo.mms.bean.param.UserParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("user")
public class UserController extends ParentController{


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/update")
    public String updateUser(UserParam param) throws AccountIsAlreadyRegisteredException {

        if (param.getRoleID() == 0) {
            throw new IllegalArgumentException("role id is 0 !");
        }
        userService.updateUser(param);
        return "redirect:/user/list";
    }

    @RequestMapping("/page/update")
    public String updateUserPage(Model model,
                                 @RequestParam("userID") Integer userID) {
        if (userID == null || userID == 0) {
            throw new IllegalArgumentException();
        }
        Subject subject = SecurityUtils.getSubject();
        User loginUser = (User)subject.getSession().getAttribute("user");
        userService.createUserPage(model, loginUser);
        userService.setUserInfoByID(model, userID);
        return "update-user";
    }

    @RequestMapping("list")
    public String pageConditionQuery(Model model, UserListParam param) throws Exception {
        if (param.getPageIndex() < 1) {
            param.setPageIndex(1);
        }
        User user = getUserFromSession();
        userService.pageConditionQuery(param, model, user);
        userService.createUserPage(model, user);
        return "zhanghaoguanli";
    }

    /**
     * 根据登陆用户角色不同获取创建用户页面
     *
     * @param model model
     * @return 创建页面
     */
    @GetMapping("page/create")
    public String createUserPage(Model model) throws Exception {
        // 查询页面上需要显示的一些信息
        User user = getUserFromSession();
        userService.createUserPage(model, user);

        return "register";
    }

    /**
     * 创建用户
     *
     * @param param 表单参数
     * @return 重定向列表页面
     */
    @PostMapping("create")
    public String create(UserParam param) throws AccountIsAlreadyRegisteredException {
        if (param.getCityID() == 0) {
            throw new IllegalArgumentException("city id is 0 !");
        }
        if (param.getRoleID() == 0) {
            throw new IllegalArgumentException("role id is 0 !");
        }
        userService.createUser(param);
        return "redirect:/user/list";
    }



//    /**
//     * 生成用户列表的参数部分URL
//     *
//     * @param param 分页查询参数
//     * @return 参数部分URL
//     */
//    private String generateUserListParamURL(UserListParam param) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("pageIndex=").append(param.getPageIndex()).append('&')
//                .append("cityID=").append(param.getCityID()).append('&')
//                .append("name=").append(param.getName() == null ? "" : param.getName()).append('&')
//                .append("roleID=").append(param.getRoleID()).append('&')
//                .append("isResigned=").append(param.getIsResigned());
//        return sb.toString();
//    }

}
