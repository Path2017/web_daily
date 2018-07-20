package com.e3expo.e3.webapp.services;


import com.e3expo.e3.model.form.RfpBaseInfoForm;
import com.e3expo.e3.model.form.RfpDetailForm;
import com.e3expo.e3.model.view.WebAppRfpView;
import com.e3expo.e3.service.interfaces.IRfp;
import com.e3expo.e3.webapp.bean.upload.UploadBlockingQueueManager;
import com.e3expo.e3.webapp.common.GenerateImagePathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RfpService {

    @Autowired
    private IRfp iRfp;


    /**
     * 创建RFP Detail的草稿
     * @param detailForm
     */
    public void createRfpDetailDraft(RfpDetailForm detailForm) throws IOException, InterruptedException {
        uploadRfpDetailFiles(detailForm);
        iRfp.createRfpDetailDraft(detailForm);
    }

    /**
     * 创建RFP，草稿状态
     *
     * @param baseInfoForm RFP基本信息
     * @return 对应数据库中的需求的主键
     */
    public int createRfpBaseInfoDraft(RfpBaseInfoForm baseInfoForm) throws IOException, InterruptedException {
        // 不为空， 生成path的这个方法只在web层使用
        uploadRfpBaseInfoFile(baseInfoForm);
        return iRfp.createRfpBaseInfoDraft(baseInfoForm);
    }


//    /**
//     * 填写RFP的详细信息
//     *
//     * @param detailForm rfp的详细信息
//     */
//    public void postRfpDetail(RfpDetailForm detailForm) throws Exception {
//        uploadRfpDetailFiles(detailForm);
//        iRfp.postRfpDetail(detailForm);
//    }

    /**
     * 填写RFP的详细信息并且发布出去
     *
     * @param detailForm rfp的详细信息
     */
    public void postRfpDetailAndPublish(RfpDetailForm detailForm) throws Exception{
        uploadRfpDetailFiles(detailForm);
        iRfp.updateAndPublishRfp(detailForm);
    }

    /**
     * 更新基本信息草稿
     * @param baseInfoForm
     * @throws IOException
     * @throws InterruptedException
     */
    public void updateBaseInfoDraft(RfpBaseInfoForm baseInfoForm) throws IOException, InterruptedException {
        uploadRfpBaseInfoFile(baseInfoForm);
        iRfp.updateBaseInfoDraft(baseInfoForm);
    }


    /**
     * 保存基本信息草稿，状态会更新为详细信息的草稿
     * @param baseInfoForm
     * @return 返回RFP的ID
     */
    public int saveRfpBaseInfo(RfpBaseInfoForm baseInfoForm) throws IOException, InterruptedException {
        uploadRfpBaseInfoFile(baseInfoForm);
        return iRfp.saveRfpBaseInfo(baseInfoForm);
    }


    /**
     * 更新详情的草稿
     *
     * @param detailForm
     */
    public void updateDetailDraft(RfpDetailForm detailForm) throws IOException, InterruptedException {
        uploadRfpDetailFiles(detailForm);
        iRfp.updateRfpDetailDraft(detailForm);
    }

    /**
     * 处理RFP基本信息上传的文件
     *
     * @param baseInfoForm
     * @throws InterruptedException
     * @throws IOException
     */
    private void uploadRfpBaseInfoFile(RfpBaseInfoForm baseInfoForm) throws InterruptedException, IOException {
        if(baseInfoForm.getExhibitionFloorPlan() != null) {
            String imagePath = GenerateImagePathUtil.getUniqueFilePath(baseInfoForm.getExhibitionFloorPlan().getOriginalFilename());
//        // 添加到上传任务队列。
            UploadBlockingQueueManager.putUploadTask(GenerateImagePathUtil.getFullPath(imagePath), baseInfoForm.getExhibitionFloorPlan().getInputStream(), baseInfoForm.getExhibitionFloorPlan().getSize());
            baseInfoForm.setExhibitionFloorPlanPath(imagePath);
        }
        baseInfoForm.clearMultiFormField();
    }

    /**
     * 上传Rfp设计需求详情的文件, 并将MultipartFile字段都置空
     *
     * @param detailForm 设计需求详情
     * @throws InterruptedException
     * @throws IOException
     */
    private void uploadRfpDetailFiles(RfpDetailForm detailForm) throws InterruptedException, IOException {
        if (detailForm.getRelativeLogo() != null) {
            detailForm.setRelativeLogoPath(UploadBlockingQueueManager.uploadMultipartFileAndReturnPath(detailForm.getRelativeLogo()));
        }
        if (detailForm.getReferenceCiStandard() != null) {
            detailForm.setReferenceCiStandardPath(UploadBlockingQueueManager.uploadMultipartFileAndReturnPath(detailForm.getReferenceCiStandard()));
        }
        if (detailForm.getReferencePoster() != null) {
            detailForm.setReferencePosterPath(UploadBlockingQueueManager.uploadMultipartFileAndReturnPath(detailForm.getReferencePoster()));
        }
        if (detailForm.getReferenceHistoryGraph() != null) {
            detailForm.setReferenceHistoryGraphPath(UploadBlockingQueueManager.uploadMultipartFileAndReturnPath(detailForm.getReferenceHistoryGraph()));
        }
        if (detailForm.getReferenceOtherFile() != null) {
            detailForm.setReferenceOtherFilePath(UploadBlockingQueueManager.uploadMultipartFileAndReturnPath(detailForm.getReferenceOtherFile()));
        }
        detailForm.clearMultiFormField();
    }

    /**
     * 获取Rfp的草稿
     *
     * @param rfpId
     * @return
     */
    public WebAppRfpView getRfpDraft(Integer rfpId) {

        return iRfp.getRfpDraft(rfpId);
    }
}
