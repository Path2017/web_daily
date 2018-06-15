package com.e3expo.mms.bean.mapper;


import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.UserListParam;
import com.e3expo.mms.bean.param.UserParam;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface UserMapper {

    public User getUserById(int id);


    public int getCountByUserName(String username);

    public User getUserByName(String username);

    public int getUserIdByName(String name);

    public void updatePassword(@Param(value = "id") int id,
                               @Param(value = "password") String password);

    public int getTotalNumber();


    public ArrayList<User> getUserListByPage(
            @Param(value = "limitNum") int limitNum,
            @Param(value = "offsetNum") int offsetNum
    );


    /**
     * 插入用户表并且回写主键
     *
     * @param user 用户信息
     */
    void insertUser(User user);

    /**
     * 插入用户角色关系表
     *
     * @param userID     用户ID
     * @param roleID     角色ID
     * @param createTime 创建时间
     */
    void insertIntoUserRole(@Param(value = "userID") int userID,
                            @Param(value = "roleID") int roleID,
                            @Param(value = "createTime") long createTime);

    /**
     * 获取条件查询结果总条目数
     *
     * @param param 条件参数
     * @return 符合条件的总条目数
     */
    int getUserTotalNumberByParam(UserListParam param);

    /**
     * 分页条件查询用户列表
     *
     * @param param param
     * @return 分页条件数据
     */
    List<User> pageConditionQuery(UserListParam param);

    /**
     * 更新用户数据
     * 为空就不更新，city role ID 为0就不更新， isResigned为-1就不更新
     *
     * @param param 参数
     */
    void updateUserInfo(UserParam param);

    /**
     * 获取系统管理员用户ID
     *
     * @return
     */
    int getSystemUserId();

    /**
     * 根据cityID查询该城市管理员
     *
     * @param cityId   城市id
     * @param roleName 角色名
     * @return
     */
    List<User> getUsersByCityIdAndRoleName(@Param("cityId") byte cityId,
                                           @Param("roleName") String roleName);

    /**
     * 通过电话查询用户
     * @param phoneNumber 电话
     * @return
     */
    User getUserByPhoneNumber(String phoneNumber);
}
