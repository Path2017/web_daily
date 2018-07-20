package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.middleware.mapper.UserInfoFileMapper;
import com.e3expo.e3.model.UserInfoFile;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignerImageDao {

    private UserInfoFileMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private void setSqlSessionTemplate(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(UserInfoFileMapper.class);
    }

    /**
     * 批量插入设计师图片
     *
     * @param userInfoFileList 图片信息列表
     * @return 插入的条数
     */
    public int batchInsertDesignerImages(List<UserInfoFile> userInfoFileList) {
        return mapper.batchInsert(userInfoFileList);
    }
}
