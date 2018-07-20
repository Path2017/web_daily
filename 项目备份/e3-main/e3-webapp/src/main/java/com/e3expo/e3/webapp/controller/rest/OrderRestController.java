package com.e3expo.e3.webapp.controller.rest;

import com.e3expo.e3.enumration.DesignFileTypeEnum;
import com.e3expo.e3.model.OrderDesignerLog;
import com.e3expo.e3.model.UploadFile;
import com.e3expo.e3.model.User;
import com.e3expo.e3.model.form.UploadDesignForm;
import com.e3expo.e3.model.form.UploadWorkingDrawingForm;
import com.e3expo.e3.validation.group.OrderDesignerLogGroup;
import com.e3expo.e3.validation.group.UploadDesignGroup;
import com.e3expo.e3.validation.group.UploadWorkingDrawingGroup;
import com.e3expo.e3.webapp.controller.BaseController;
import com.e3expo.e3.webapp.services.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * 设计师抢单中心Rest控制
 */
@RestController("orderRestController")
@RequestMapping("order")
public class OrderRestController extends BaseController {

    @Autowired
    private OrderService orderService;


    /**
     * 参展商确认收取施工图稿
     */
    @RequestMapping(value = "/workingDrawing/confirm", method = RequestMethod.POST, produces = "application/json")
    public void confirmWorkingDesign(@RequestBody @Validated({UploadWorkingDrawingGroup.Confirm.class}) UploadWorkingDrawingForm form) {
        // 参展商确认收稿，订单状态更改为完成
        User user = getUserFromSession();
//        orderService.confirmWorkingDrawing(user.getId(), form);
        orderService.confirmWorkingDrawing(123, form);
    }

    /**
     * 上传施工图
     */
    @RequestMapping(value = "/workingDrawing/upload", method = RequestMethod.POST, produces = "application/json")
    public void uploadWorkingDrawing(@RequestBody @Validated({UploadWorkingDrawingGroup.Upload.class}) UploadWorkingDrawingForm form) {
        User user = getUserFromSession();
//        form.setDesignerId(user.getId());
        form.setDesignerId(110);
        form.setUploadFile(new UploadFile(DesignFileTypeEnum.WORKING_DRAWING.getId(), form.getWorkingDrawingPath(),
                form.getDesignerId(), form.getOrderId()));
        orderService.uploadWorkingDrawing(form);
    }


    /**
     * 施工图付款成功
     *
     * @param orderId
     * @param userId
     */
    @RequestMapping(value = "/workingDrawing/paid", method = RequestMethod.GET)
    public void payForWorkingDrawingCreation(Integer orderId, Integer userId) {
        orderService.payForWorkingDrawingCreation(orderId, userId);
    }

    /**
     * 确认设计稿
     *
     * @param orderId
     */
    @RequestMapping(value = "/design/confirm", method = RequestMethod.GET)
    public void confirmDesign(Integer orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("orderId should not be null");
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
//        orderService.confirmDesign(orderId, user.getId());
        orderService.confirmDesign(orderId, 123);
    }

    /**
     * 设计稿创建付款成功
     *
     * @param orderId
     * @param userId
     */
    @RequestMapping(value = "/design/paid", method = RequestMethod.GET)
    public void payForDesignCreation(Integer orderId, Integer userId) {
        orderService.payForDesignCreation(orderId, userId);
    }


    /**
     * 设计稿修改付款成功
     *
     * @param orderId
     * @param userId
     */
    @RequestMapping("/updateDesign/paid")
    public void payForUpdatingDesign(Integer orderId, Integer userId) {
        orderService.payForUpdatingDesign(orderId, userId);
    }

    /**
     * 上传或者修改设计稿
     */
    @RequestMapping(value = "/upload/design", method = RequestMethod.POST)
    public void uploadDesign(@Validated(UploadDesignGroup.Upload.class) UploadDesignForm form) {
        List<UploadFile> fileList = new LinkedList<>();
        form.setUploadFiles(fileList);
        fileList.add(new UploadFile(DesignFileTypeEnum.DESIGN_LEFT_VIEW.getId(), form.getDesignLeftView(),
                form.getDesignerId(), form.getOrderId()));
        fileList.add(new UploadFile(DesignFileTypeEnum.DESIGN_RIGHT_VIEW.getId(), form.getDesignRightView(),
                form.getDesignerId(), form.getOrderId()));
        fileList.add(new UploadFile(DesignFileTypeEnum.DESIGN_PLAN_VIEW.getId(), form.getDesignPlanView(),
                form.getDesignerId(), form.getOrderId()));
        fileList.add(new UploadFile(DesignFileTypeEnum.DESIGN_FRONT_GRID_VIEW.getId(), form.getDesignFrontGridView(),
                form.getDesignerId(), form.getOrderId()));
        fileList.add(new UploadFile(DesignFileTypeEnum.DESIGN_PLAN_GRID_VIEW.getId(), form.getDesignPlanGridView(),
                form.getDesignerId(), form.getOrderId()));
        fileList.add(new UploadFile(DesignFileTypeEnum.DESIGN_3DMAX.getId(), form.getDesign3DMax(),
                form.getDesignerId(), form.getOrderId()));
        if (form.getDesignOtherView() != null) {
            for (String filePath : form.getDesignOtherView()) {
                fileList.add(new UploadFile(DesignFileTypeEnum.DESIGN_OTHER_VIEW.getId(), filePath,
                        form.getDesignerId(), form.getOrderId()));
            }
        }
        User user = getUserFromSession();
//        form.setDesignerId(user.getId());
        form.setDesignerId(110);
        orderService.uploadDesign(form);

    }

    /**
     * 申请改稿
     *
     * @param orderDesignerLog
     */
    @RequestMapping(value = "/modify/design", method = RequestMethod.POST, produces = "application/json")
    public void applyForModifyingDesign(@RequestBody
                                        @Validated(OrderDesignerLogGroup.ApplyForModifyingDesign.class)
                                                OrderDesignerLog orderDesignerLog) {
//        User user = getUserFromSession();
        // 申请修改设计稿
        orderService.applyForModifyingDesign(orderDesignerLog);
    }

}
