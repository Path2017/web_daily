package com.e3expo.mms.controller;

import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.ApplicationParam;
import com.e3expo.mms.bean.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("application")
public class ApplicationController extends ParentController{

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping("list")
    public String pageConditionQuery(ApplicationParam param,
                                     Model model) throws Exception {
        if (param.getPageIndex() < 1) {
            param.setPageIndex(1);
        }
        // 创建申请 查询申请表，判断申请状态
//        User user = getUserFromSession();
        applicationService.pageConditionQuery(param, model);
        applicationService.setCitiesAndApplicationStatuses(model);
        return "shenpi";
    }


    @RequestMapping("create")
    public String createApplication(@RequestParam("designId") int designId) throws Exception {
        // 创建申请 查询申请表，判断申请状态
        User user = getUserFromSession();
        applicationService.createApplication(user, designId);
        return "redirect:/design/detail?designID=" + designId;
    }

//    /**
//     * 生成设计列表的参数部分URL
//     *
//     * @param param 分页查询参数
//     * @return 参数部分URL
//     */
//    private String generateDesignListParamURL(DesignListParam param) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("pageIndex=").append(param.getPageIndex()).append('&')
//                .append("designID=").append(param.getDesignID())
//                .append("cityID=").append(param.getCityID()).append('&')
//                .append("professionID=").append(param.getProfessionID()).append('&')
//                .append("exhibitionTypeID=").append(param.getExhibitionTypeID()).append('&')
//                .append("structureID=").append(param.getStructureID()).append('&')
//                .append("openSides=").append(param.getOpenSides()).append('&')
//                .append("openSides=").append(param.getOpenSides()).append('&')
//                .append("priceLowerLimit=").append(param.getPriceLowerLimit()).append('&')
//                .append("priceUpperLimit=").append(param.getPriceUpperLimit()).append('&')
//                .append("areaLowerLimit=").append(param.getAreaLowerLimit()).append('&')
//                .append("areaUpperLimit=").append(param.getAreaUpperLimit()).append('&');
//        return sb.toString();
//    }
//


}
