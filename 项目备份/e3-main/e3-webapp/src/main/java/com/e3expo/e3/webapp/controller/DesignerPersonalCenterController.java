package com.e3expo.e3.webapp.controller;

import com.e3expo.e3.model.User;
import com.e3expo.e3.model.param.PageParam;
import com.e3expo.e3.model.view.WebAppOrderView;
import com.e3expo.e3.webapp.services.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller("designerPersonalCenterController")
@RequestMapping("designer")
public class DesignerPersonalCenterController extends BaseController {

    @Autowired
    private OrderService orderService;


    /**
     * 设计师的抢单中心列表
     *
     * @return
     */
    @RequestMapping(value = "ongoingOrderList", method = GET)
    @ResponseBody
    public PageParam<WebAppOrderView> ongoingOrderList(Model model, PageParam<WebAppOrderView> page) {
        User user = getUserFromSession();
//        orderService.pageQueryOngoingOrders(page, user.getId());
        return orderService.pageQueryDesignerOngoingOrderList(page, 110);
//        return "";
    }

    /**
     * 设计师的所有订单列表
     *
     * @return
     */
    @RequestMapping(value = "completedOrderList", method = GET)
    @ResponseBody
    public PageParam<WebAppOrderView> completedOrderList(Model model, PageParam<WebAppOrderView> page) {
        User user = getUserFromSession();
//        orderService.pageQueryOngoingOrders(page, user.getId());
        return orderService.pageQueryCompletedOrderList(page, 110);
//        return "";
    }

    /**
     * 设计师的所有订单列表
     *
     * @return
     */
    @RequestMapping(value = "orderList", method = GET)
    @ResponseBody
    public PageParam<WebAppOrderView> allOrderList(Model model, PageParam<WebAppOrderView> page) {
        User user = getUserFromSession();
//        orderService.pageQueryOngoingOrders(page, user.getId());
        return orderService.pageQueryAllOrderList(page, 110);
//        return "";
    }

    /**
     * 设计师的抢单中心列表
     *
     * @return
     */
    @RequestMapping(value = "grabOrderList", method = GET)
    @ResponseBody
    public PageParam<WebAppOrderView> grabOrderList(Model model, PageParam<WebAppOrderView> page) {
        User user = getUserFromSession();
//        orderService.pageQueryOngoingOrders(page, user.getId());
        return orderService.pageQueryGrabOrderList(page, 110);
//        return "";
    }

}
