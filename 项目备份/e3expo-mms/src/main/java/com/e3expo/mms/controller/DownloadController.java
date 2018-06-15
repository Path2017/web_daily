package com.e3expo.mms.controller;

import com.aliyun.oss.model.OSSObject;
import com.e3expo.mms.bean.enumeration.DesignOrderTypeEnum;
import com.e3expo.mms.bean.model.Design;
import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.DesignListParam;
import com.e3expo.mms.bean.param.DownloadListParam;
import com.e3expo.mms.bean.service.DesignService;
import com.e3expo.mms.bean.service.MyDownloadService;
import com.e3expo.mms.bean.service.oss.OSSManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.util.EnumSet;

@Controller
@RequestMapping("download")
public class DownloadController extends ParentController{

    private final MyDownloadService downloadService;
    private final DesignService designService;

    @Autowired
    public DownloadController(MyDownloadService downloadService, DesignService designService) {
        this.downloadService = downloadService;
        this.designService = designService;
    }

    @RequestMapping("execute")
    public void executeDownload(@RequestParam("designId") int designId,
                                final HttpServletRequest request,
                                final HttpServletResponse response) throws Exception {
        User user = getUserFromSession();
        Design design = downloadService.getDownloadDesign(user, designId);

        OSSObject download = OSSManager.getInstance().getInternalClient().getObject(design.getCity().getBucket(), design.getOssKey());
        String userAgent =  request.getHeader("User-Agent");
        String fileName = design.getPrimitiveName();
        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentLength((int) download.getObjectMetadata().getContentLength());
        response.setContentType("application/octet-stream;charset=UTF-8");
        FileCopyUtils.copy(new BufferedInputStream(download.getObjectContent()), response.getOutputStream());
    }

    @RequestMapping("list")
    public String pageConditionQuery(DownloadListParam param, Model model) throws Exception {
        User user = getUserFromSession();
        // todo 表单校验
        if (param.getPageIndex() < 1) {
            param.setPageIndex(1);
        }
        if (param.getAreaLowerLimit() - param.getAreaUpperLimit() > 0
                || param.getPriceLowerLimit() - param.getPriceUpperLimit() > 0) {
            throw new IllegalArgumentException("lower limit is higher than upper limit.");
        }
        downloadService.pageConditionQuery(user, param, model);
        designService.setCityList(model);
        designService.setDesignDictionary(model);
        model.addAttribute("designOrderTypes", EnumSet.allOf(DesignOrderTypeEnum.class));
        return "download";
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
//                .append("designID=").append(param.getDesignID()).append('&')
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
