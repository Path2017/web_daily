package com.e3expo.e3.service.interfaces;

import com.e3expo.e3.model.form.RfpBaseInfoForm;
import com.e3expo.e3.model.form.RfpDetailForm;
import com.e3expo.e3.model.view.WebAppRfpView;

public interface IRfp {

    /**
     * 根据填写的基本信息，保存为草稿
     *
     * @param baseInfoForm 包含最基本的需求信息
     * @return 数据库中的rfp id
     */
    int createRfpBaseInfoDraft(RfpBaseInfoForm baseInfoForm);

    /**
     * 更新RFP基本信息的草稿
     *
     * @param baseInfoForm
     */
    void updateBaseInfoDraft(RfpBaseInfoForm baseInfoForm);

    /**
     * 根据填写的基本信息，创建需求，并且返回生成的需求的主键
     *
     * @param baseInfoForm 包含最基本的需求信息
     * @return 数据库中的rfp id
     */
    int saveRfpBaseInfo(RfpBaseInfoForm baseInfoForm);

    /**
     * 创建Rfp detail的草稿
     *
     * @param detailForm
     */
    void createRfpDetailDraft(RfpDetailForm detailForm);

    /**
     * 更新设计详情的草稿
     *
     * @param detailForm 详细信息
     */
    void updateRfpDetailDraft(RfpDetailForm detailForm);

    /**
     * 发布Rfp
     *
     * @param rfpId
     */
    void publishRfp(Integer rfpId, Integer userId);

    /**
     * 更新信息并且发布Rfp
     * @param detailForm
     */
    void updateAndPublishRfp(RfpDetailForm detailForm);

    /**
     * 根据rfpId查询草稿的信息
     * @param rfpId
     * @return
     */
    WebAppRfpView getRfpDraft(Integer rfpId);
}
