package com.e3expo.e3.webapp.controller.rest;

import com.e3expo.e3.common.Constants;
import com.e3expo.e3.enumration.DesignerImageTypeEnum;
import com.e3expo.e3.enumration.DesignerTypeEnum;
import com.e3expo.e3.exceptions.ImproperVeriCodeOrMobileException;
import com.e3expo.e3.exceptions.InvalidMobileFormatException;
import com.e3expo.e3.exceptions.NotFoundVeriCodeException;
import com.e3expo.e3.exceptions.UserException;
import com.e3expo.e3.model.Province;
import com.e3expo.e3.model.User;
import com.e3expo.e3.model.form.DesignerRegisterForm;
import com.e3expo.e3.model.form.MultipartDataForm;
import com.e3expo.e3.validation.constraints.MultipartFileArrayType;
import com.e3expo.e3.validation.group.UserGroup;
import com.e3expo.e3.webapp.bean.AutoDeleteSessionVeriCode;
import com.e3expo.e3.webapp.bean.model.VeriCodeModel;
import com.e3expo.e3.webapp.common.RestfulJsonModelAndView;
import com.e3expo.e3.webapp.services.AuthService;

import com.e3expo.e3.webapp.validation.group.VeriCodeGroup;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import static com.e3expo.e3.enumration.DesignerImageTypeEnum.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController("authRestController")
public class AuthRestController {

    @Autowired
    private AuthService authService;

    /**
     * 上传单个文件, 假设以的形式上传
     */
    @RequestMapping(value = "/uploadSingleFile", method = POST)
    @ResponseBody
    public String uploadFile(MultipartFile file) throws IOException, InterruptedException {
        if (file == null) {
            throw new NullPointerException("file.is.null");
        }
        if (file.getSize() == 0) {
            throw new IllegalArgumentException("empty.file");
        }
        return authService.uploadFile(file);
    }


    /**
     * 注册展览公司
     * <p>
     * 请求类型：formdata
     *
     * @return
     */
    @RequestMapping(value = "/register/exhibitionCompany", method = POST)
    public void registerExhibitionCompany(@Validated({UserGroup.RegisterExhibitionCompany.class}) User user,
                                          HttpServletResponse response) throws Exception {
        authService.registerExhibitionCompany(user);
        response.setStatus(NO_CONTENT.value());
    }

    /**
     * 用户登录
     * <p>
     * 请求类型：formdata
     *
     * @return
     */
    @RequestMapping(value = "/login", method = POST)
    public void login(User user) throws Exception {
        authService.login(user);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getMobile(), user.getPassword());
        try {
            subject.login(token);
        } catch (Exception e) {
            if (e.getCause() instanceof UserException) {
                UserException ex = (UserException) e.getCause();
                throw new UserException(ex.getCode());
            } else {
                throw new Exception();
            }
        }
        Session session = SecurityUtils.getSubject().getSession();
        User userInfo = SecurityUtils.getSubject().getPrincipals().oneByType(User.class);
        //TODO  根据不同数据返回不同页面？？？
        session.setAttribute("user", userInfo);
        System.out.println(userInfo);
    }

    /**
     * 提供E3验证用户信息
     *
     * @param user
     * @return
     */
    @PostMapping(Constants.USER_CHECK)
    public ModelAndView checkUserForE3(@RequestBody User user) {
        authService.checkUser(user.getMobile(), user.getPassword());
        return RestfulJsonModelAndView.buildJsonModelAndView();
    }


    /**
     * 登录页面重定向
     *
     * @return
     */
    @GetMapping("/login")
    public ModelAndView toLogin() {
        String url = "redirect:http://www.e3-expo.com";
        return new ModelAndView(url);
    }

    /**
     * 用户登出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = GET)
    public String logout(HttpServletRequest request) throws Exception {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        }
        //TODO 通知E3系统session设置无效
        return "";
    }


    /**
     * 校验验证码。
     * <p>
     * 请求类型：RESTful
     * <p>
     * 200： 校验通过
     * 404: 验证码超时过期
     * 403: 验证吗校验错误
     *
     * @param request       req
     * @param response      res
     * @param veriCodeModel 验证码model
     */
    @RequestMapping(value = "/checkVeriCode", method = POST, produces = "application/json")
    public void checkVeriCode(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestBody @Validated({VeriCodeGroup.CheckVeriCode.class})
                                      VeriCodeModel veriCodeModel) throws Exception {
        // 校验验证码
        AutoDeleteSessionVeriCode codeObj = (AutoDeleteSessionVeriCode) request.getSession().getAttribute(AutoDeleteSessionVeriCode.VERI_CODE_IN_SESSION_KEY);
        if (codeObj == null) {
            // 404
//            response.setStatus(NOT_FOUND.value());
            throw new NotFoundVeriCodeException("vericode not found");
//            response.sendError(NOT_FOUND.value(), "veriCode expired");
//            return;
        } else {
            if (!codeObj.getMobile().equals(veriCodeModel.getMobile()) ||
                    !codeObj.getVeriCode().equals(veriCodeModel.getVeriCode())) {
                // 405
                throw new ImproperVeriCodeOrMobileException("wrong veriCode or mobile");
//                response.setStatus(METHOD_NOT_ALLOWED.value());
//                response.sendError(METHOD_NOT_ALLOWED.value(), "wrong veriCode or mibole");
            }
        }
        // 200
        response.setStatus(OK.value());
    }

    /**
     * 发送验证码
     *
     * @param request  req
     * @param response res
     * @param model    电话号码
     */
    @RequestMapping(value = "/veriCode", method = POST, produces = "application/json")
    public void sendVerificationCode(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestBody @Validated({VeriCodeGroup.PostVeriCode.class})
                                             VeriCodeModel model) throws Exception {
        // 在Controller层操作Session ，Service方法要获取这个验证码字符串
        String veriCode = authService.sendVeriCodeToMobile(model.getMobile());
        // todo 验证码有效时间，提出配置
        new AutoDeleteSessionVeriCode(request.getSession(), veriCode, model.getMobile(), 60 * 5);
        response.setStatus(OK.value());
    }

    /**
     * 注册设计师
     * <p>
     * 请求类型formdata
     * <p>
     * 200：注册成功
     */
    @RequestMapping(value = "/register/designer", method = POST, headers = "content-type=multipart/*")
    @ResponseStatus(NO_CONTENT)
    public void registerDesigner(@Validated({UserGroup.RegisterDesigner.class}) DesignerRegisterForm registerForm) throws Exception {
//        if (!checkIfFilesNumberBetween(representativeWorks, 2, 10)) {
//            throw new IllegalFilesNumberException("representative works number should be less than 10 and greater than 2");
//        }
//        if (!checkIfFilesNumberBetween(educationImgs, 0, 3)) {
//            throw new IllegalFilesNumberException("education imgs number should be less than 3");
//        }
//        if (!checkIfFilesNumberBetween(titleImgs, 0, 5)) {
//            throw new IllegalFilesNumberException("title imgs number should be less than 5");
//        }
//        if (!checkIfFilesNumberBetween(awardImgs, 0, 5)) {
//            throw new IllegalFilesNumberException("awardImgs imgs number should be less than 5");
//        }
        HashMap<DesignerImageTypeEnum, MultipartFile[]> imagesMap = new HashMap<>();
        imagesMap.put(DESIGNER_REPRESENTATIVE_WORK, registerForm.getRepresentativeWork());
        imagesMap.put(DESIGNER_EDUCATION, registerForm.getEducation());
        imagesMap.put(DESIGNER_TITLE, registerForm.getTitle());
        imagesMap.put(DESIGNER_AWARD, registerForm.getAward());
        authService.registerDesigner(registerForm, imagesMap);
    }


    /**
     * 校验设计师电话号码是否可用
     * <p>
     * 请求类型：RESTful
     * <p>
     * 200：可以使用
     * 403：不能使用
     *
     * @param response 请求相应
     * @param model    设计师电话号码
     */
    @RequestMapping(value = "/check/designerMobile", method = POST, produces = "application/json")
    public void checkDesignerMobileIsAvailable(HttpServletResponse response,
                                               @RequestBody @Validated({VeriCodeGroup.PostVeriCode.class})
                                                       VeriCodeModel model) throws Exception {
        if (authService.checkDesignerMobileIsAvailable(model.getMobile())) {
            response.setStatus(OK.value());
        } else {
            response.setStatus(FORBIDDEN.value());
        }
    }

    /**
     * 校验设计师电话号码是否可用
     * <p>
     * 请求类型：RESTful
     * <p>
     * 200：可以使用
     * 403：不能使用
     *
     * @param response 请求相应
     * @param model    展览公司电话号码
     * @throws Exception
     */
    @RequestMapping(value = "/check/exhibitionCompanyMobile", method = POST, produces = "application/json")
    public void checkExhibitionMobileIsValid(HttpServletResponse response,
                                             @RequestBody @Validated({VeriCodeGroup.PostVeriCode.class})
                                                     VeriCodeModel model) throws Exception {

        if (authService.checkExhibitionCompanyMobileIsAvailable(model.getMobile())) {
            response.setStatus(OK.value());
        } else {
            response.setStatus(FORBIDDEN.value());
        }
    }

    /**
     * 获取省市详细信息
     * <p>
     * 请求类型：RESTful
     * <p>
     *
     * @return 返回province的列表信息
     * @throws Exception
     */
    @RequestMapping(value = "/provinceList", method = GET)
    @ResponseBody
    public List<Province> listProvince() throws Exception {
        return authService.listProvince();
    }

    /*私有方法*/

    /**
     * 校验数组中非空文件数量是否在上限和下限之间，包括上下限。
     * 如果files为null，此时，如果下限大于0，返回false；如果下限小于等于0，返回true
     *
     * @param files 上传的文件数组
     * @param lower 数量下限，如果为负数，表示不设置下限
     * @param upper 数量上限，如果为负数，表示不设置上限
     * @return 符合数量要求返回true，反之为false
     */
    private boolean checkIfFilesNumberBetween(MultipartFile[] files, int lower, int upper) {
        if (lower < 0) {
            lower = 0;
        }
        if (files == null) {
            // 如果要求最少传文件数大于0，返回false，反之，返回true.
            return !(lower > 0);
        }
//        if (lower > upper) {
//            throw new IllegalArgumentException("lower should not larger than upper");
//        }
        if (upper < 0) {
            upper = Integer.MAX_VALUE;
        }
        int notEmptyFiles = 0;
        for (MultipartFile file : files) {
            if (file == null) {
                continue;
            }
            if (!file.isEmpty()) {
                notEmptyFiles++;
            }
        }
        return notEmptyFiles >= lower && notEmptyFiles <= upper;
    }


}
