package com.e3expo.mms.bean.dao;


import com.e3expo.mms.bean.mapper.RoleMapper;
import com.e3expo.mms.bean.model.Role;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class RoleDao {


    private Collection<? extends Role> allRoles;

    @Autowired
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
        this.mapper = this.sqlSession.getMapper(RoleMapper.class);
    }

    private RoleMapper mapper;
    private SqlSessionTemplate sqlSession;

    public List<Role> getAllRoles() {
        return mapper.getAllRoles();
    }

    /**
     * 更新用户角色信息
     *
     * @param userID 用户ID
     * @param roleID 角色ID
     */
    public void updateUserRole(int userID, int roleID) {
        int oldRole = mapper.getRoleIDByUserID(userID);
        if (oldRole == roleID) return;
        mapper.updateUserRoleByUserID(userID, roleID);
    }
}