package com.e3expo.e3.webapp.services;

import com.e3expo.e3.common.SmsHttpSender;
import com.e3expo.e3.enumration.DesignerImageTypeEnum;
import com.e3expo.e3.enumration.EnumAuditStatus;
import com.e3expo.e3.enumration.EnumUserType;
import com.e3expo.e3.exceptions.UserException;
import com.e3expo.e3.exceptions.UserException.ErrorCode;
import com.e3expo.e3.model.UserInfoFile;
import com.e3expo.e3.model.Province;
import com.e3expo.e3.model.User;
import com.e3expo.e3.model.form.DesignerRegisterForm;
import com.e3expo.e3.service.interfaces.IUser;
import com.e3expo.e3.util.PasswordUtil;
import com.e3expo.e3.webapp.bean.upload.UploadBlockingQueueManager;
import com.e3expo.e3.webapp.common.GenerateImagePathUtil;
import com.e3expo.e3.webapp.common.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.e3expo.e3.common.Constants.*;


@Service
public class AuthService {
	
	@Autowired
	private StringRedisTemplate template;
	
	public void checkUser(String username,String password) {
		Object id = template.opsForHash().get(REDIS_HASH_USERS,  username);
    	if(id == null) {
    		throw new UserException(ErrorCode.USER_MOBILE_IS_NULL);
    	}
    	int userId = Integer.parseInt(id.toString());
    	Object pass = template.opsForHash().get(REDIS_HASH_USER_PREFIX + userId, REDIS_FIELD_PASSWORD);
    	String basePassword = null;
    	if(pass != null) {
    		basePassword = pass.toString();
    	}
    	password = PasswordUtil.encryptPassword(password);
    	if(!password.equals(basePassword)) {
    		throw new UserException(ErrorCode.USER_PASSWORD_ERROR);
    	}
    	Object tempStatus = template.opsForHash().get(REDIS_HASH_USER_PREFIX+userId, REDIS_FIELD_STATUS);
    	if(tempStatus == null || Integer.parseInt(tempStatus.toString()) != EnumAuditStatus.AUDIT_SUCCESS.getValue()) {
    		throw new UserException(ErrorCode.USER_NOT_AUDIT);
    	}
    	Object tempIsValid =  template.opsForHash().get("user:"+userId, REDIS_FIELD_STATUS);
    	if(tempIsValid == null || Integer.parseInt(tempIsValid.toString()) == -1) {
    		throw new UserException(ErrorCode.USER_IS_STOP);
    	}
    	
	}

    /**
     * 查询所有设计师工作年限的列表
     *
     * @return
     */
    public List<String> listDesignerWorkingYears(){
        return iUser.listDesignerWorkingYears();
    }


    /**
     * 查询所有省/市信息列表，包含市/区的详细信息
     *
     * @return
     */
    public List<Province> listProvince() {
        return iUser.listProvince();
    }

    /**
     * 校验设计师电话是否可以使用
     *
     * @param mobile
     * @return
     */
    public boolean checkDesignerMobileIsAvailable(String mobile) {
        return iUser.checkRegisterMobileIsAvailable(mobile, EnumUserType.DESIGNER.getValue());
//        return false;
    }

    /**
     * 判断展览公司电话是否可用
     *
     * @param mobile 电话号码
     * @return
     */
    public boolean checkExhibitionCompanyMobileIsAvailable(String mobile) {
        return iUser.checkRegisterMobileIsAvailable(mobile, EnumUserType.EXHIBITOR.getValue());
    }


    /**
     * 发送并返回验证码
     *
     * @param mobile 电话号码
     * @return
     */
    public String sendVeriCodeToMobile(String mobile) throws IOException {
        // 获取验证码
        String veriCode = VerificationCodeUtil.getSixDigits();
        // 发送短信
        String message = String.format("您的验证码为%s,有效时间为10分钟", veriCode);
        SmsHttpSender.getInstance().send(mobile, message);
        return veriCode;
    }

    /**
     * 用户登陆
     *
     * @param user 用户信息
     */
    public User login(User user) {
        User userLogin = iUser.userLogin(user.getMobile(), user.getPassword());
        if (userLogin == null) {
//            throw new
        }
        return userLogin;
    }


    /**
     * 将文件存到本地，调用service注册用户
     *
     * @param registerForm 封装基本用户的信息
     * @param imagesMap    包含各类设计师文件的map，key为DesignerImageTypeEnum，value是封装的Multipart对象
     * @throws IOException
     */
    public void registerDesigner(DesignerRegisterForm registerForm,
                                 HashMap<DesignerImageTypeEnum, MultipartFile[]> imagesMap) throws IOException, InterruptedException {
        List<UserInfoFile> imageInfoList = new LinkedList<>();
        registerForm.setImageInfoList(imageInfoList);
        for (DesignerImageTypeEnum type : imagesMap.keySet()) {
            MultipartFile[] multipartFiles = imagesMap.get(type);
            if (multipartFiles != null) {
                for (MultipartFile representativeWork : multipartFiles) {
                    dealWithRegisterImage(representativeWork, type.getId(), imageInfoList);
                }
            }
        }
//        registerForm.setDesignerType(designerType.getType());
        try {
            registerForm.clearMultiFormField();
            iUser.registerDesigner(registerForm);
        } catch (UserException e) {
            throw e;
        }
    }


//    @Deprecated
//    public void registerDesigner(DesignerRegisterForm registerForm,
//                                 MultipartFile[] representativeWorks,
//                                 MultipartFile[] educationImgs,
//                                 MultipartFile[] titleImgs,
//                                 MultipartFile[] awardImgs) throws IOException, InterruptedException {
//
//        // 图片文件处理：1，生成path，2，上传，3，转换base64编码，4，将信息封装好丢给service
//        // path规则:yyyyMMdd/UUID_filename;
//        List<UserInfoFile> imageInfoList = new LinkedList<>();
//        registerForm.setImageInfoList(imageInfoList);
//        for (MultipartFile representativeWork : representativeWorks) {
//            // todo fileType
//            dealWithRegisterImage(representativeWork, 1, imageInfoList);
//        }
//        for (MultipartFile educationImg : educationImgs) {
//            dealWithRegisterImage(educationImg, 2, imageInfoList);
//        }
//        for (MultipartFile titleImg : titleImgs) {
//            dealWithRegisterImage(titleImg, 3, imageInfoList);
//        }
//        for (MultipartFile awardImg : awardImgs) {
//            dealWithRegisterImage(awardImg, 4, imageInfoList);
//        }
//        // todo 设置设计师类型 注册设计师，designerType在
////        registerForm.setDesignerType(1);
//        try {
//            iUser.registerDesigner(registerForm);
//        } catch (UserException e) {
//            throw e;
//        }
//    }

    /**
     * 处理注册上传的图片文件
     *
     * @param imageFile     图片文件
     * @param fileType      文件
     * @param imageInfoList 文件信息要添加到的列表
     * @throws IOException 读取文件流时出错
     */
    private void dealWithRegisterImage(MultipartFile imageFile, Integer fileType,
                                       List<UserInfoFile> imageInfoList)
            throws IOException, InterruptedException {
        assert imageFile != null;
        if (imageFile.isEmpty()) {
            return;
        }
        // 不为空， 生成path的这个方法只在web层使用
        String imagePath = GenerateImagePathUtil.getUniqueFilePath(imageFile.getOriginalFilename());
        // 添加到上传任务队列。
        UploadBlockingQueueManager.putUploadTask(GenerateImagePathUtil.getFullPath(imagePath), imageFile.getInputStream(), imageFile.getSize());
        // 添加到信息列表中
        imageInfoList.add(new UserInfoFile(imageFile.getOriginalFilename(), imagePath, fileType));
    }



    public void registerExhibitionCompany(User user) {
        user.setUserType(EnumUserType.EXHIBITOR.getValue());
        iUser.insertExhibitor(user);
    }

    @Autowired
    private IUser iUser;

    /**
     * 上传文件并且返回path
     *
     * @param file
     * @return
     */
    public String uploadFile(MultipartFile file) throws IOException, InterruptedException {

        String filePath = GenerateImagePathUtil.getUniqueFilePath(file.getOriginalFilename());
        UploadBlockingQueueManager.putUploadTask(GenerateImagePathUtil.getFullPath(filePath), file.getInputStream(), file.getSize());

        return filePath;
    }
}
