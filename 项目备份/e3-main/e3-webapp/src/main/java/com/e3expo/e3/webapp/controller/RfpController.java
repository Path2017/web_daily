package com.e3expo.e3.webapp.controller;

import com.e3expo.e3.model.view.WebAppRfpView;
import com.e3expo.e3.webapp.services.RfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller("rfpController")
@RequestMapping("rfp")
public class RfpController {

    @Autowired
    private RfpService rfpService;


    /**
     * 展览公司发布需求页面
     *
     * @return
     */
    @RequestMapping(value = "publish", method = GET)
    public String publishRfpPage() {
        return "exhibitor/post_requirement_step1";
    }

    /**
     * 展览公司继续编辑需求页面
     *
     * @return
     */
    @RequestMapping(value = "modify", method = GET)
    public String modifyRfpPage(Model model, Integer rfpId) {
        WebAppRfpView rfpView = rfpService.getRfpDraft(rfpId);
        model.addAttribute("rfpView", rfpView);
        // 返回step1
        return "exhibitor/post_requirement_step1";
    }


    /**
     * 展览公司发布需求，填写详细设计需求页面
     *
     * @return
     */
    @RequestMapping(value = "detail", method = GET)
    public String fillInDetailRfpPage(Model model, Integer rfpId) {
        WebAppRfpView rfpView = rfpService.getRfpDraft(rfpId);
        model.addAttribute("rfpView", rfpView);
        // 返回step1
        return "exhibitor/post_requirement_step2";
    }


}
