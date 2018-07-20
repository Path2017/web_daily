package com.e3expo.e3.webapp.controller;

import com.e3expo.e3.model.Rfp;
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

@Controller("exhibitionCompanyPersonalCenterController")
@RequestMapping("exhibitionCompany")
public class ExhibitionCompanyPersonalCenterController {

    @Autowired
    private OrderService orderService;


    /**
     * 订单详情页面
     *
     * @param model
     * @param orderId
     * @return
     */
    @RequestMapping(value = "orderDetail", method = GET)
    @ResponseBody
    public WebAppOrderView orderDetailPage(Model model, Integer orderId) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");

        return orderService.getOrderDetail(orderId);
        // 参展商查询订单addAttribute内容
//        PageParam<Rfp> page = orderService.pageQuerySavedRfp(param, user.getId());
//        model.addAttribute("page", page);
//        return "";
    }


    /**
     * 已保存的需求，订单
     *
     * @return
     */
    @RequestMapping(value = "savedRfp", method = GET)
    @ResponseBody
    public PageParam<Rfp> savedRfpList(Model model, PageParam<Rfp> param) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        // 参展商查询订单addAttribute内容
//        return orderService.pageQuerySavedRfp(param, user.getId());
        return orderService.pageQuerySavedRfp(param, 123);
//        model.addAttribute("page", page);
//        return "";
    }

    /**
     * 已完成的订单
     *
     * @return
     */
    @RequestMapping(value = "completedOrder", method = GET)
    @ResponseBody
    public PageParam<WebAppOrderView> completedOrder(Model model, PageParam<WebAppOrderView> param) {
        if (param == null) {
            throw new NullPointerException("param is null");
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        // 参展商查询订单addAttribute内容
//        PageParam<WebAppOrderView> page = orderService.pageQueryCompletedOrders(param, user.getId());
        return orderService.pageQueryCompletedOrders(param, user.getId());
//        model.addAttribute("page", page);
//        return "";
    }


    /**
     * 展览公司个人中心订单管理，所有订单
     *
     * @return
     */
    @RequestMapping(value = "order", method = GET)
    @ResponseBody
    public PageParam<WebAppOrderView> allOrders(Model model, PageParam<WebAppOrderView> param) {
        if (param == null) {
            throw new NullPointerException("param is null");
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        // 参展商查询订单addAttribute内容
//        PageParam<WebAppOrderView> page = orderService.pageQueryAllOrders(param, user.getId());
        return orderService.pageQueryAllOrders(param, user.getId());
//        model.addAttribute("page", page);
//        return "";
    }


    /**
     * 展览公司个人中心首页，进行中的订单
     *
     * @return
     */
    @RequestMapping(value = "ongoingOrder", method = GET)
    @ResponseBody
    public PageParam<WebAppOrderView> home(Model model, PageParam<WebAppOrderView> param) {
        if (param == null) {
            throw new NullPointerException("param is null");
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        // 参展商查询订单addAttribute内容
//        PageParam<WebAppOrderView> page = orderService.pageQueryOngoingOrders(param, user.getId());
        PageParam<WebAppOrderView> page = orderService.pageQueryOngoingOrders(param, 123);
//        model.addAttribute("page", page);
//        return "";
        return page;
    }

}
