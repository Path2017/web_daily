package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.middleware.mapper.OrderMapper;
import com.e3expo.e3.middleware.mapper.RfpDetailMapper;
import com.e3expo.e3.model.RfpDetail;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RfpDetailDao {

    @Autowired
    private void setSql(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(RfpDetailMapper.class);
    }


    private RfpDetailMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 单条插入RfpDetail，如果字段为null就不插入
     *
     * @param detail
     */
    public void insertRfpDetail(RfpDetail detail) {
        mapper.insertSelective(detail);
    }

    public RfpDetail getByRfpId(Integer rfpId) {
        return mapper.selectByRfpId(rfpId);
    }

    /**
     * 根据ID更新RFP Detail的信息
     *
     * @param detail
     */
    public void updateRfpDetailById(RfpDetail detail) {
        mapper.updateByPrimaryKeySelective(detail);
    }
}
