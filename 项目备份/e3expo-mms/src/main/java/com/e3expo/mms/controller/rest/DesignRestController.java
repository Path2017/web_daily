package com.e3expo.mms.controller.rest;


import com.e3expo.mms.bean.model.FileUploadStatus;
import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.DesignHistoryListParam;
import com.e3expo.mms.bean.param.DesignParam;
import com.e3expo.mms.bean.service.DesignService;
import com.e3expo.mms.controller.ParentController;
import com.e3expo.mms.controller.message.MmsMessage;
import com.e3expo.mms.controller.message.RestfulData;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("design")
public class DesignRestController extends ParentController {

    private final DesignService designService;

    @Autowired
    public DesignRestController(DesignService designService) {
        this.designService = designService;
    }


//    @RequestMapping(value = "/set-cover/{sketchId}")
//    public MmsMessage setCover(@PathVariable("sketchId") int sketchId) {
//        if (sketchId <= 0) {
//            throw new IllegalArgumentException("Illegal sketchId.");
//        }
//        designService.setCover(sketchId, sketchId, user);
//        return new MmsMessage(200, "success");
//    }

    /**
     * 创建新design
     *
     * @param param 设计图参数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = POST, headers = "content-type=multipart/*")
    public MmsMessage createDesign (DesignParam param) throws Exception {
        if (param.getPriceLowerLimit() - param.getPriceUpperLimit() > 0) {
            throw new IllegalArgumentException("lower limit is higher than upper limit");
        }
        User user = getUserFromSession();
        designService.create(param, user);
        // 直接插入到设计图表
        return new MmsMessage(200, "successful");
    }
    /**
     * 覆盖压缩文件
     *
     * @param designId
     * @param zipFile
     * @return
     * @throws Exception
     */
    @RequestMapping("/setZipFile")
    public MmsMessage setZipFile(@RequestParam("designId") int designId,
                                 @RequestParam("zipFile")  MultipartFile zipFile) throws Exception {
        User user = getUserFromSession();
        designService.setZipFile(designId, zipFile, user);
        return new MmsMessage(200, "successful");
    }

    /**
     * 替换，或者追加图片。
     * 替换，只能替换单张。被替换的图ID必填
     * 追加，可以是多张。
     *
     * @param designId    设计图id
     * @param sketchId    被替换的图片id
     * @param sketchFiles 替换的图片文件，或者是追加文件数组
     * @return
     * @throws Exception
     */
    @RequestMapping("/setSketch")
    public MmsMessage setSketch(@RequestParam("designId") int designId,
                                @RequestParam("sketchId") int sketchId,
                                @RequestParam("setSketchFile") MultipartFile[] sketchFiles) throws Exception {
        User user = getUserFromSession();
        if (sketchId == 0) {
            designService.appendSketches(designId, sketchFiles, user);
        } else {
            designService.setSketch(sketchId, designId, sketchFiles, user);
        }
        return new MmsMessage(200, "successful");
    }

    /**
     * 获取亚索文件的上传状态
     *
     * @return
     */
    @RequestMapping(value = "/uploadStatus", method = GET)
    public RestfulData<FileUploadStatus> getUploadStatus() {
        FileUploadStatus status = (FileUploadStatus) SecurityUtils.getSubject().getSession().getAttribute(FileUploadStatus.SESSION_KEY);
        if (status == null) {
            return new RestfulData<>(500, null);
        } else {
            return new RestfulData<>(200, status);
        }
    }

    @RequestMapping(value = "/history/{designId}/{pageIndex}", method = GET)
    public DesignHistoryListParam getDesignHistoryPage(@PathVariable("designId") Integer designId,
                                                       @PathVariable("pageIndex") Integer pageIndex,
                                                       Model model) throws Exception {
        if (designId == null || designId <= 0) {
            throw new IllegalArgumentException("Illegal designId");
        }
        if (pageIndex <= 0) {
            throw new IllegalArgumentException("Illegal pageIndex");
        }

        DesignHistoryListParam param = new DesignHistoryListParam();
        param.setPageIndex(pageIndex);
        param.setDesignId(designId);
        designService.pageQueryDesignHistory(param, model);
        param.getTotalPages();

        return param;
    }


}
