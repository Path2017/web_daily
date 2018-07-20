package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.middleware.mapper.DesignerMapper;
import com.e3expo.e3.middleware.mapper.UploadFileMapper;
import com.e3expo.e3.model.UploadFile;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UploadFileDao {
    private UploadFileMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private void setSqlSessionTemplate(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(UploadFileMapper.class);
    }


    public void batchInsert(List<UploadFile> uploadFiles) {
        mapper.batchInsert(uploadFiles);
    }

    public void deleteUploadFilesByUploadId(List<Integer> uploadIdList) {
        mapper.deleteByUploadId(uploadIdList);
    }

    public void insert(UploadFile uploadFile) {
        mapper.insertSelective(uploadFile);
    }
}
