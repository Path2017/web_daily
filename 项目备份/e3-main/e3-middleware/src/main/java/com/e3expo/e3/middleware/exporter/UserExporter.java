package com.e3expo.e3.middleware.exporter;

import com.e3expo.e3.model.Designer;
import com.e3expo.e3.model.Province;
import com.e3expo.e3.model.form.DesignerRegisterForm;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.e3expo.e3.common.SerialNo;
import com.e3expo.e3.enumration.EnumAuditStatus;
import com.e3expo.e3.enumration.EnumUserType;
import com.e3expo.e3.enumration.EnumValidStatus;
import com.e3expo.e3.exceptions.UserException;
import com.e3expo.e3.exceptions.UserException.ErrorCode;
import com.e3expo.e3.middleware.service.UserService;
import com.e3expo.e3.model.User;
import com.e3expo.e3.service.interfaces.IUser;
import com.e3expo.e3.util.PasswordUtil;

import java.util.List;

@Component
public class UserExporter implements IUser {

    @Autowired
    private UserService userService;

    /**
     * 注册设计师的实现
     *
     * @param registerForm 设计师注册表单类型。
     *                     封装设计师（工作室）的基本信息，首先必须包含设计师的类型designerType, 还需包括：mobile, password,
     *                     name（companyName）, email, qq, provinceId, cityId, workingYears（companyCreateTime），
     */
    @Override
    public void registerDesigner(DesignerRegisterForm registerForm) {
        // 插入之前要校验用户
        // 判断展览公司的手机号码之前是否注册过
        User userByMobile = userService.queryUserInfoByMobile(registerForm.getMobile());
        if (userByMobile != null) {
            throw new UserException(ErrorCode.USER_MOBILE_EXSITED);
        }

        // 插入到user表中 MYSQL 和 Redis
//        // 封装designer对象
//        Designer designer = new Designer();
//        designer.setCompanyCreateTime(registerForm.getCompanyCreateTime());
//        designer.setWorkingYears(registerForm.getWorkingYears());
//        designer.setDesignerType(registerForm.getDesignerType());
        // 设置用户通用信息
        setCommonFieldsBeforeInsert(registerForm, EnumUserType.DESIGNER.getValue());
        // 插入用户和设计师信息
        userService.insertDesigner(registerForm);
        // 生成id，插入到imageFile
        registerForm.setUserIdToDesignerImages(registerForm.getId());
        registerForm.setCreateTimeToDesignerImages(System.currentTimeMillis());
        registerForm.setUpdateTimeToDesignerImages(System.currentTimeMillis());
        if (registerForm.getImageInfoList() != null) {
            // 将图片信息批量插入到表中
            userService.batchInsertDesignerImages(registerForm.getImageInfoList());
        }
        // 注册成功后将form对象发到表单中给老系统调用注册
        // todo 有错误
//        template.convertAndSend(registerForm);


    }

    public User userLogin(String mobile, String password) throws UserException {
        User user = userService.queryUserInfoByMobile(mobile);

        // 手机号码不存在
        if (user == null) {
            throw new UserException(ErrorCode.USER_MOBILE_NOT_EXSIT);
        }

        password = PasswordUtil.encryptPassword(password);
        if (!password.equals(user.getPassword())) {
            throw new UserException(ErrorCode.USER_PASSWORD_ERROR);
        }
        // 账号被停用
        if (user.getIsValid() == null || user.getIsValid() == -1) {
            throw new UserException(ErrorCode.USER_IS_STOP);
        }
        // 账号审核未通过
        if (user.getStatus() == null || user.getStatus() != EnumAuditStatus.AUDIT_SUCCESS.getValue()) {
            throw new UserException(ErrorCode.USER_NOT_AUDIT);
        }
        return user;
    }

    /**
     * 插入展览公司的信息
     *
     * @param user
     * @return
     * @throws UserException
     */
    public int insertExhibitor(User user) throws UserException {
        // 判断展览公司的手机号码之前是否注册过
        User userByMobile = userService.queryUserInfoByMobile(user.getMobile());
        if (userByMobile != null) {
            throw new UserException(ErrorCode.USER_MOBILE_EXSITED);
        }
        setCommonFieldsBeforeInsert(user, EnumUserType.EXHIBITOR.getValue());
        return userService.insertExhibitor(user);

    }

    @Override
    public boolean checkRegisterMobileIsAvailable(String mobile, int userType) {
        User user = userService.queryUserInfoByMobile(mobile);
        return user != null;
//        switch (EnumUserType.getType(userType)) {
//            case DESIGNER:
//                //TODO 如果是设计师
//
//                return result;
//            case EXHIBITOR:
//                //TODO 如果是展览公司
//                return result;
//            default:
//                throw new IllegalArgumentException("invalid userType value: " + userType);
//        }
    }

    @Override
    public List<Province> listProvince() {
        return userService.listProvince();
    }


    @Override
    public List<String> listDesignerWorkingYears() {
        return userService.listDesignerWorkingYears();
    }

    /**
     * 注册用户之前设置一些通用的域
     *
     * @param user     注册用户
     * @param userType 用户类型
     */
    private void setCommonFieldsBeforeInsert(User user, int userType) {
        String password = PasswordUtil.encryptPassword(user.getPassword());
        user.setPassword(password);
        user.setStatus(EnumAuditStatus.AUDIT_LOAD.getValue());
        user.setIsValid(EnumValidStatus.VALID.getValue());
        user.setUserType(EnumUserType.EXHIBITOR.getValue());
        user.setUserType(userType);
        //生成工号
        user.setJobNumber(SerialNo.createJobNumber());
        //时间标志
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
    }

    
    @Autowired
    @Qualifier("register_old")
    private RabbitTemplate template;

}
