package com.e3expo.e3.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller("designerBidController")
@RequestMapping("bid")
public class DesignerBidController {

    /**
     * 抢单中心的列表页面
     *
     * @return
     */
    @RequestMapping(value = "list", method = GET)
    public String list() {
        //
        return "";
    }

}
