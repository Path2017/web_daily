package com.e3expo.e3.middleware.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.e3expo.e3.middleware.mapper.UserMapper;
import com.e3expo.e3.model.User;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private void setSql(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(UserMapper.class);
    }
    
    
    @Autowired
    private  StringRedisTemplate stringRedisTemplate;

    private UserMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;


    /**
     * 根据手机号查询用户信息
     *
     * @param mobile
     * @return
     */
    public User queryUserInfoByMobile(String mobile) {
        User user = mapper.queryUserInfoByMobile(mobile);
//        String userId=stringRedisTemplate.opsForHash().get("users", mobile).toString();
        return user;
    }
//    
//    public void queryUser() {
//    	String userId = redisTemplate.opsForHash().get("users", "17317360310").toString();
//    	System.out.println(userId);
//    }
    /**
     * 新增展览公司
     *
     * @param user
     * @return
     */
    public int insertExhibitor(User user) {
        int num = mapper.insertSelective(user);
        return num;
    }

    /**
     * 新增用户，生成主键回写到user中
     *
     * @param user 用户信息
     * @return 影响条数
     */
    public int insertDesigner(User user) {
        return mapper.insertSelective(user);
    }

    /**
     * 查询所有工作年限的分割点
     *
     * @return
     */
    public List<Integer> listDesignerWorkingYearsSplitPoint() {
        return mapper.listDesignerWorkingYearsSplitPoint();
    }
}
