package com.e3expo.e3.webapp.controller.rest;


import com.e3expo.e3.model.DesignerPriceConfig;
import com.e3expo.e3.model.Order;
import com.e3expo.e3.model.User;
import com.e3expo.e3.model.form.DesignerPriceConfigForm;
import com.e3expo.e3.validation.group.DesignerPriceConfigFormGroup;
import com.e3expo.e3.validation.group.OrderGroup;
import com.e3expo.e3.webapp.services.DesignerBidService;
import com.e3expo.e3.webapp.services.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 设计师抢单中心Rest控制
 */
@RestController("designerBidRestController")
@RequestMapping("bid")
public class DesignerBidRestController {

    @Autowired
    private DesignerBidService designerBidService;


    @Autowired
    private OrderService orderService;

    /**
     * 新建或者更新报价模板，并投标
     */
    @RequestMapping(value = "setPriceConfigAndBid", method = RequestMethod.POST)
    public void setPriceConfigAndBid(@Validated(DesignerPriceConfigFormGroup.SetPriceConfigAndBid.class) DesignerPriceConfigForm form) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
//        form.setDesignerId(user.getId());
        form.setDesignerId(110);
        designerBidService.setPriceConfigAndBid(form);
    }

    /**
     * 查询报价模板
     */
    @RequestMapping(value = "priceConfig", method = RequestMethod.GET)
    public void getDesignerPriceConfig() {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<DesignerPriceConfig> list = designerBidService.getDesignerPriceConfig(user.getId());
    }

    /**
     * 结束竞标
     */
    @RequestMapping(value = "end", method = RequestMethod.GET)
    public void endBidding(Integer orderId) {
        if (orderId == null) {
            throw new NullPointerException("order.id.is.null");
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
//        orderService.endBidding(order.getId(), user.getId());
        orderService.endBidding(orderId, 123);
    }
}
