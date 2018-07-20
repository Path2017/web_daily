package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.middleware.mapper.ProvinceMapper;
import com.e3expo.e3.model.Province;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProvinceDao {

    private ProvinceMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private void setSqlSessionTemplate(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(ProvinceMapper.class);
    }



    /**
     * 查询所有省/市信息列表，列表中每个省/市对象中包含所有的市/区{@code City}的详细信息
     *
     * @return 包含完整市/区信息的所有省/市列表
     */
    public List<Province> listProvince() {
        return mapper.listProvince();
    }
}
