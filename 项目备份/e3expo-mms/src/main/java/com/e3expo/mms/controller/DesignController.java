package com.e3expo.mms.controller;

import com.e3expo.mms.Exception.ApprovedApplicationMultipleException;
import com.e3expo.mms.Exception.EmptyFileException;
import com.e3expo.mms.Exception.PermissionDeniedException;
import com.e3expo.mms.bean.enumeration.DesignOrderTypeEnum;
import com.e3expo.mms.bean.enumeration.RoleEnum;
import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.DesignListParam;
import com.e3expo.mms.bean.param.DesignParam;
import com.e3expo.mms.bean.service.DesignService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.EnumSet;

import static com.e3expo.mms.bean.enumeration.RoleEnum.SYS_ADMIN;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
@Controller
@RequestMapping("design")
public class DesignController extends ParentController {


    @Autowired
    DesignService designService;




    @RequestMapping("/deleteSketch")
    public String deleteSketch(@RequestParam("designId") int designId,
                               @RequestParam("sketchId") int sketchId) throws Exception {
        User user = getUserFromSession();
        designService.deleteSketchById(sketchId, designId, user);
        return "redirect:/design/page/modify?designID=" + designId;
    }

    @RequestMapping("setCover")
    public String setCover(@RequestParam("designId") int designId,
                           @RequestParam("sketchId") int sketchId) throws Exception {
        User user = getUserFromSession();
        designService.setCover(sketchId, designId, user);
        return "redirect:/design/page/modify?designID=" + designId;
    }

//    @RequestMapping("appendSketch")
    @Deprecated
    public String appendSketch(@RequestParam("designId") int designId,
                                     @RequestParam("sketchFile") MultipartFile sketchFile) throws Exception {
        User user = getUserFromSession();
        designService.appendSketch(designId, sketchFile, user);
        return "redirect:/design/page/modify?designID=" + designId;
    }


    @RequestMapping("delete")
    public String setDesignIsVisible(DesignListParam param) throws Exception {
        if (param.getDesignID() <= 0) {
            throw new IllegalArgumentException("illegal designId");
        }
//        loginUserHasRole(SYS_ADMIN);
        Subject subject = SecurityUtils.getSubject();
        User user = getUserFromSession();
        designService.deleteDesignById(param.getDesignID(), user);
        return "redirect:/design/list?" + generateDesignListParamURL(param);
    }
    @RequestMapping("setIsVisible")
    public String setDesignIsVisible(@RequestParam("designID") Integer designId,
                                     @RequestParam("isVisible") Byte isVisible,
                                     DesignListParam param) throws Exception {
        if (designId == null || isVisible == null) {
            throw new IllegalArgumentException("designId or isVisible is null");
        }
        loginUserHasRole(SYS_ADMIN);
        designService.setDesignIsVisible(designId, isVisible);
        return "redirect:/design/list?" + generateDesignListParamURL(param);

    }


    @RequestMapping("modify")
    public String modifyDesign(DesignParam param) throws Exception {
        User user = getUserFromSession();
        designService.modifyDesign(param, user);
//        return "redirect:/design/page/modify?designID=" + param.getDesignID();
        return "redirect:/design/list?";
    }


    @RequestMapping("page/modify")
    public String getDesignModifyPage(DesignListParam param, Model model) throws Exception {
        // 查询指定的设计图详情
        User user = getUserFromSession();
        designService.getDesignDetailById(param.getDesignID(), model, user);
        designService.setDesignDictionary(model);
        return "update-design";
    }


    @RequestMapping("/detail")
    public String designDetail(DesignListParam param, Model model) throws Exception {
        User user = getUserFromSession();
        if (param.getDesignID() == 0) {
            throw new IllegalArgumentException("design id is 0.");
        }
        // 设计图的详细信息
        designService.getDesignDetailByIdIncreaseViews(param.getDesignID(), model, user);
//        designService.pageQueryDesignHistory(new DesignHistoryListParam(param.getDesignID()), model);
        return "xiangqing";
    }


    @RequestMapping("/list")
    public String designList(DesignListParam param, Model model) throws Exception {
        User user = getUserFromSession();
        // todo 表单校验
        if(param.getPageIndex() < 1) {
            param.setPageIndex(1);
        }
        if (param.getAreaLowerLimit() - param.getAreaUpperLimit() > 0
                || param.getPriceLowerLimit() - param.getPriceUpperLimit() > 0) {
            throw new IllegalArgumentException("lower limit is higher than upper limit.");
        }
        if (user.getRole().getName().equals(SYS_ADMIN.getRoleName())&& param.getInvisible() == 0) {
            // 如果是管理员，而且没有勾选只看隐藏，就把所有的全部显示出来
            param.setInvisible((byte) -1);
        }
        if (param.getCreatedByMyself() == 1) {
            param.setOwnerID(user.getId());
        }
        designService.pageConditionQuery(param, model, user);
//        designService.createUserPage(model, user);
        designService.setDesignDictionary(model);
        designService.setCityList(model);
        model.addAttribute("designOrderTypes", EnumSet.allOf(DesignOrderTypeEnum.class));
        return "tukucenter";
    }



    @RequestMapping("page/create")
    public String getDesignCreatePage(Model model) throws Exception {
        User user = getUserFromSession();
        model.addAttribute("user", user);
        designService.setDesignDictionary(model);
        return "tukuregister";
    }

    /**
     * 生成设计列表的参数部分URL
     *
     * @param param 分页查询参数
     * @return 参数部分URL
     */
    private String generateDesignListParamURL(DesignListParam param) {
        StringBuilder sb = new StringBuilder();
        sb.append("pageIndex=").append(param.getPageIndex()).append('&')
                .append("designID=").append(param.getDesignID()).append('&')
                .append("cityID=").append(param.getCityID()).append('&')
                .append("professionID=").append(param.getProfessionID()).append('&')
                .append("exhibitionTypeID=").append(param.getExhibitionTypeID()).append('&')
                .append("structureID=").append(param.getStructureID()).append('&')
                .append("openSides=").append(param.getOpenSides()).append('&')
                .append("priceLowerLimit=").append(param.getPriceLowerLimit()).append('&')
                .append("priceUpperLimit=").append(param.getPriceUpperLimit()).append('&')
                .append("areaLowerLimit=").append(param.getAreaLowerLimit()).append('&')
                .append("areaUpperLimit=").append(param.getAreaUpperLimit()).append('&')
                .append("invisible=").append(param.getInvisible()).append('&')
                .append("createdByMyself=").append(param.getCreatedByMyself());
        return sb.toString();
    }


}
