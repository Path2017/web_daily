package com.e3expo.e3.service.interfaces;

import com.e3expo.e3.exceptions.UserException;
import com.e3expo.e3.model.Province;
import com.e3expo.e3.model.User;
import com.e3expo.e3.model.form.DesignerRegisterForm;

import java.util.List;

public interface IUser {


    /**
     * 注册设计师,以及设计师工作室
     * 将User中封装的用户通用数据插入到user表并生成userId，
     * 拿到这个userId后：
     * 将designer中封装的设计师特有的信息插入designer表
     * 将images列表中的每张图片信息插入到designer_image表中。
     * <p>
     * 同时将注册的设计师所有信息，以及images中的图片信息和base64编码后的图片信息，送入MQ中
     *
     * @param registerForm 设计师注册表单类型。
     *                     封装设计师（工作室）的基本信息，首先必须包含设计师的类型designerType, 还需包括：mobile, password,
     *                     name（companyName）, email, qq, provinceId, cityId, workingYears（companyCreateTime），
     *                     以及注册时上传的各种图片信息列表imageInfoList，以及与之对应的base64编码图片的列表。
     */
    void registerDesigner(DesignerRegisterForm registerForm);

    /**
     * @api {no} userLogin
     * @apiGroup User
     * @apiDescription 用户使用手机和密码进行登录
     * @apiParam {String} mobile 必填
     * @apiParam {String} password 必填
     * @apiSuccess {User} user 实体对象
     */
    public User userLogin(String mobile, String password) throws UserException;

    /**
     * @param param
     * @return
     * @throws Exception
     * @api {no} insertExhibitor
     * @apiGroup User
     * @apiDescription 插入展览公司信息
     * @apiParam {User} user 必填
     * @apiParam {User} user.mobile 必填
     * @apiParam {User} user.password 必填
     * @apiParam {User} user.companyName 必填
     * @apiSuccess {int} flag 0插入失败，1插入成功
     * @apiError UserException 手机号码已存在
     * @apiErrorExample {json} Error-Response:
     * HTTP/1.1 404 Not Found
     * {
     * code:200019,
     * msg:'User.mobile.has.exsited',
     * }
     */
    public int insertExhibitor(User user) throws UserException;


    /**
     * 注册用户时，判断该电话号码是否可以使用
     *
     * @param mobile   要检查的电话号码，13位，不需要加区号，默认是+86
     * @param userType 要检查的用户类型，分为两种：展览公司和设计师
     * @return true：电话号码可以使用，false：电话号码不可以使用
     */
    boolean checkRegisterMobileIsAvailable(String mobile, int userType);

    /**
     * 查询所有省/市信息列表，列表中每个省/市对象中包含所有的市/区{@code City}的详细信息
     *
     * @return 包含完整市/区信息的所有省/市列表
     */
    List<Province> listProvince();


    /**
     * 列出设计师工作年限列表
     *
     * @return 设计师工作年限列表，只包含完整的工作年限描述字符串如：1年以下，1-3年，10年以上
     */
    List<String> listDesignerWorkingYears();

}
