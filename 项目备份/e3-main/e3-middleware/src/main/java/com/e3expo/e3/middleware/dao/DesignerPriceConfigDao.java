package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.middleware.mapper.DesignerPriceConfigMapper;
import com.e3expo.e3.model.DesignerPriceConfig;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignerPriceConfigDao {

    private DesignerPriceConfigMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private void setSqlSessionTemplate(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(DesignerPriceConfigMapper.class);
    }

    /**
     * 根据userId查询设计师价格模板的配置
     * @param designerId
     * @return
     */
    public List<DesignerPriceConfig> selectDesignerPriceConfigListByUserId(Integer designerId) {
        return mapper.selectByDesignerId(designerId);
    }

    /**
     * 批量插入
     * @param designerPriceConfig
     */
    public void batchInsert(List<DesignerPriceConfig> designerPriceConfig) {
        mapper.batchInsert(designerPriceConfig);
    }

    /**
     * 更新指定配置的price字段
     * @param priceConfig
     */
    public void modifyPriceById(DesignerPriceConfig priceConfig) {
        mapper.updatePriceById(priceConfig);
    }
}
