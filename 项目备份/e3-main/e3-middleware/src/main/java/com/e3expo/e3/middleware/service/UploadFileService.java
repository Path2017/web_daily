package com.e3expo.e3.middleware.service;

import com.e3expo.e3.enumration.EnumValidStatus;
import com.e3expo.e3.middleware.dao.UploadFileDao;
import com.e3expo.e3.model.UploadFile;
import com.e3expo.e3.model.form.UploadDesignForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadFileService {

    @Autowired
    private UploadFileDao uploadFileDao;

    public void batchInsert(UploadDesignForm form, Integer uploadId, String nodeId) {
        assert form != null;
        assert uploadId != null;
        for (UploadFile uploadFile : form.getUploadFiles()) {
            uploadFile.setCreateTime(System.currentTimeMillis());
            uploadFile.setIsValid(EnumValidStatus.VALID.getValue());
            uploadFile.setUserId(form.getDesignerId());
            uploadFile.setNodeId(nodeId);
            uploadFile.setUploadId(uploadId);
        }
        uploadFileDao.batchInsert(form.getUploadFiles());
    }

    /**
     * 将原有的删除
     *
     * @param uploadIdList
     */
    public void deleteUploadFilesByUploadId(List<Integer> uploadIdList) {
        if (uploadIdList != null && uploadIdList.size() > 0) {
            uploadFileDao.deleteUploadFilesByUploadId(uploadIdList);
        }
    }
}
