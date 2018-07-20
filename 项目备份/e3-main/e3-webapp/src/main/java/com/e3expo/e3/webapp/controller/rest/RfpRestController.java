package com.e3expo.e3.webapp.controller.rest;

import com.e3expo.e3.model.User;
import com.e3expo.e3.model.form.RfpBaseInfoForm;
import com.e3expo.e3.model.form.RfpDetailForm;
import com.e3expo.e3.validation.group.RfpDetailGroup;
import com.e3expo.e3.validation.group.RfpGroup;
import com.e3expo.e3.webapp.model.RfpId;
import com.e3expo.e3.webapp.services.RfpService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController("rfpRestController")
@RequestMapping("rfp")
public class RfpRestController {

    /**
     * 展览公司发布需求的表单基本信息的草稿
     */
    @RequestMapping(value = "baseInfoDraft", method = POST, headers = "content-type=multipart/*")
    public void createRfpBaseInfoDraft(RfpBaseInfoForm baseInfoForm)
            throws Exception {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        baseInfoForm.setUserId(user.getId());
        // todo userId
//        baseInfoForm.setUserId(123);
        rfpService.createRfpBaseInfoDraft(baseInfoForm);
//        return new RfpId(rfpId);
    }

    /**
     * 更新基本信息草稿状态
     */
    @RequestMapping(value = "updateBaseInfoDraft", method = POST, headers = "content-type=multipart/*")
    public void updateRfpBaseInfoDraft(@Validated({RfpGroup.UpdateDraft.class}) RfpBaseInfoForm baseInfoForm)
            throws Exception {
//        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
//        baseInfoForm.setUserId(user.getId());
        // todo userId
//        baseInfoForm.setUserId(123);
        rfpService.updateBaseInfoDraft(baseInfoForm);
//        return new RfpId(rfpId);
    }

    /**
     * 展览公司提交发布需求基本信息填完，保存，应该重定向到详情页面，如果之前编辑过就填上，如果没有就空白
     * "下一步"的按钮
     */
    @RequestMapping(value = "baseInfo", method = POST, headers = "content-type=multipart/*")
    public RfpId saveRfpBaseInfo(@Validated({RfpGroup.Create.class}) RfpBaseInfoForm baseInfoForm)
            throws Exception {
//        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
//        baseInfoForm.setUserId(user.getId());
        // todo userId
        baseInfoForm.setUserId(123);
        int rfpId = rfpService.saveRfpBaseInfo(baseInfoForm);
        // return "redirect:xx"
        return new RfpId(rfpId);
    }


    /**
     * 展览公司提详细的设计布求的表单详细设计需求，保存为草稿
     */
    @RequestMapping(value = "detailDraft", method = POST, headers = "content-type=multipart/*")
    public RfpId createRfpDetailDraft(@Validated({RfpDetailGroup.CreateDraft.class}) RfpDetailForm detailForm) throws Exception {
        rfpService.createRfpDetailDraft(detailForm);
        return new RfpId(detailForm.getRfpId());
    }


    /**
     * 展览公司提详细的设计布求的表单详细设计需求，更新草稿
     */
    @RequestMapping(value = "updateDetailDraft", method = POST, headers = "content-type=multipart/*")
    public RfpId updateRfpDetailDraft(@Validated({RfpDetailGroup.UpdateDraft.class}) RfpDetailForm detailForm) throws Exception {
        rfpService.updateDetailDraft(detailForm);
        return new RfpId(detailForm.getRfpId());
    }

    /**
     * 发布需求
     */
    @RequestMapping(value = "publish", method = POST, headers = "content-type=multipart/*")
    public RfpId postRfpDetailAndPublish(@Validated({RfpDetailGroup.Create.class}) RfpDetailForm detailForm) throws Exception {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
//        detailForm.setUserId(user.getId());
        detailForm.setUserId(123);
        rfpService.postRfpDetailAndPublish(detailForm);
        return new RfpId(detailForm.getRfpId());
    }


    @Autowired
    private RfpService rfpService;

}
