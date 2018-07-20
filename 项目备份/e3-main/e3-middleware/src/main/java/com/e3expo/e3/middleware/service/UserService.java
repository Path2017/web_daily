package com.e3expo.e3.middleware.service;

import com.e3expo.e3.middleware.dao.DesignerDao;
import com.e3expo.e3.middleware.dao.DesignerImageDao;
import com.e3expo.e3.middleware.dao.ProvinceDao;
import com.e3expo.e3.model.UserInfoFile;
import com.e3expo.e3.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.e3expo.e3.middleware.dao.UserDao;
import com.e3expo.e3.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.e3expo.e3.common.Constants.*;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DesignerImageDao designerImageDao;

    @Autowired
    private DesignerDao designerDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 根据手机号获取用户信息
     *
     * @param mobile
     * @return
     */
    public User queryUserInfoByMobile(String mobile) {
        User user = userDao.queryUserInfoByMobile(mobile);
        return user;
    }

    /**
     * 新增参展商
     *
     * @param user
     * @return
     */
    public int insertExhibitor(User user) {
        int num = userDao.insertExhibitor(user);
        insertUserToRedis(user);
        return num;
    }

    /**
     * 新增设计师（工作室），将设计师基本信息插入user表，生成id后继续插入designer表
     *  @param user     用户基本信息
     *
     */
    public int insertDesigner(User user) {
        int insertUserCount = userDao.insertDesigner(user);
        // 同时插入到redis中
        insertUserToRedis(user);
        return insertUserCount;
    }

    /**
     * 将用户的信息存到Redis中，目前会存user的id，password，status，user_type，is_valid
     *
     * @param user 用户信息 id，password，status，user_type，is_valid信息。
     */
    private void insertUserToRedis(User user) {
        redisTemplate.boundHashOps(REDIS_HASH_USERS).put(user.getMobile(), user.getId().toString());
//        BoundHashOperations<String, Object, Object> userInfoOps = redisTemplate.boundHashOps(REDIS_HASH_USER_PREFIX + user.getId());
        String key = REDIS_HASH_USER_PREFIX + user.getId();
        System.out.println(key);
        redisTemplate.opsForHash().put(key, REDIS_FIELD_STATUS, user.getStatus().toString());
        redisTemplate.opsForHash().put(key, REDIS_FIELD_PASSWORD, user.getPassword());
        redisTemplate.opsForHash().put(key, REDIS_FIELD_IS_VALID, user.getIsValid().toString());
        redisTemplate.opsForHash().put(key, REDIS_FIELD_USER_TYPE, user.getUserType().toString());
//        userInfoOps.put(REDIS_FIELD_STATUS, user.getStatus().toString());
//        userInfoOps.put(REDIS_FIELD_PASSWORD, user.getPassword());
//        userInfoOps.put(REDIS_FIELD_IS_VALID, user.getIsValid().toString());
//        userInfoOps.put(REDIS_FIELD_USER_TYPE, user.getUserType().toString());
    }

    /**
     * 批量插入设计师图片
     *
     * @param imageInfoList 设计师图片列表
     * @return
     */
    public int batchInsertDesignerImages(List<UserInfoFile> imageInfoList) {
        return designerImageDao.batchInsertDesignerImages(imageInfoList);
    }

    /**
     * 查询所有省/市信息列表，列表中每个省/市对象中包含所有的市/区{@code City}的详细信息
     *
     * @return 包含完整市/区信息的所有省/市列表
     */
    public List<Province> listProvince() {
        return provinceDao.listProvince();
    }

    public List<String> listDesignerWorkingYears() {
        List<Integer> splitPoints = userDao.listDesignerWorkingYearsSplitPoint();
        if (splitPoints == null) {
            return null;
        }
        List<String> workingYears = new ArrayList<>(10);
        int pre = 0;
        for (int i = 0; i < splitPoints.size(); i++) {
            if (i == 0) {
                workingYears.add(splitPoints.get(i) + "年以下");
            } else if (i == splitPoints.size() - 1) {
                workingYears.add(splitPoints.get(i) + "年以上");
            } else {
                workingYears.add(pre + "-" + splitPoints.get(i) + "年");
            }
            pre = splitPoints.get(i);
        }
        return workingYears;
    }
}
