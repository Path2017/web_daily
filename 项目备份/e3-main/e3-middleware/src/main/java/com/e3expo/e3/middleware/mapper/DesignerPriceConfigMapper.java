package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.DesignerPriceConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DesignerPriceConfigMapper {

    /**
     * 根据设计师Id（用户Id）查询配置
     * @return
     * @param designerId
     */
    List<DesignerPriceConfig> selectByDesignerId(Integer designerId);

    /**
     * 批量插入
     * @param list
     */
    void batchInsert(@Param("list") List<DesignerPriceConfig> list);

    /**
     * 更新指定配置价格
     * @param priceConfig
     */
    void updatePriceById(DesignerPriceConfig priceConfig);
}