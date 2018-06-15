package com.e3expo.mms.controller;


import com.e3expo.mms.Exception.PermissionDeniedException;
import com.e3expo.mms.bean.enumeration.RoleEnum;
import com.e3expo.mms.bean.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ParentController {

    @ModelAttribute("user")
    protected User getUserFromSession() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Session session = subject.getSession();
            return (User) session.getAttribute("user");
        } else {
            return null;
        }
    }

    /**
     * 登录需要用户需要有指定权限
     * 如果没有指定权限就会抛出{@code PermissionDeniedException}
     *
     * @param role 角色枚举
     */
    protected void loginUserHasRole(RoleEnum role) throws Exception {
        User user = getUserFromSession();
        if (!user.getRole().getName().equals(role.getRoleName())) {
            throw new PermissionDeniedException();
        }
    }


}
