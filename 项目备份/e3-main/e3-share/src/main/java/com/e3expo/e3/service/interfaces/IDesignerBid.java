package com.e3expo.e3.service.interfaces;

import com.e3expo.e3.model.DesignerPriceConfig;
import com.e3expo.e3.model.form.DesignerPriceConfigForm;

import java.util.List;

public interface IDesignerBid {

    /**
     * 根据用户ID查询对应的报价配置
     *
     * @param userId
     * @return
     */
    List<DesignerPriceConfig> getDesignerPriceConfig(Integer userId);

    /**
     * 创建或者修改designerPriceConfig
     *
     * @param form Price的
     */
    void createOrUpdateDesignerPriceConfig(DesignerPriceConfigForm form);

    /**
     * 直接竞标
     * @param form
     */
     void bid(DesignerPriceConfigForm form);
}
