package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.enumration.DesignFileTypeEnum;
import com.e3expo.e3.enumration.OrderNodeEnum;
import com.e3expo.e3.middleware.mapper.OrderDesignerLogMapper;
import com.e3expo.e3.model.OrderDesignerLog;
import com.e3expo.e3.model.UploadFile;
import com.e3expo.e3.model.view.DesignImagesView;
import com.e3expo.e3.model.view.WebAppOrderDesignerLogView;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class OrderDesignerLogDao {
    private OrderDesignerLogMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private void setSqlSessionTemplate(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(OrderDesignerLogMapper.class);
    }

    /**
     * 插入OrderDesignerLog, 并且回写主键
     *
     * @param orderDesignerLog
     */
    public void insert(OrderDesignerLog orderDesignerLog) {
        mapper.insert(orderDesignerLog);
    }

    /**
     *
     * @param orderId
     * @return
     */
    public List<WebAppOrderDesignerLogView> selectDesignUploadByOrderId(Integer orderId) {
        // 查询所有log 包括传图和申请改稿
        List<WebAppOrderDesignerLogView> logs = mapper.selectDesignLogByOrderId(orderId);
        // 只留下设计图的上传
        logs.stream().filter(logView -> OrderNodeEnum.DESIGN_STAGE_WAIT_UPLOAD.getNodeId().equals(logView.getNodeId()))
                // set images
                .forEach(this::setImages);
        return logs;
    }

    // 将图片set到view中的image对象中
    private void setImages(WebAppOrderDesignerLogView logView) {
        // 查询所有图片，根据uploadId
        List<UploadFile> fileList = mapper.selectUploadFilesByUploadId(logView.getId());
        if (fileList != null && fileList.size() > 0) {
            DesignImagesView imagesView = new DesignImagesView();
            logView.setImagesViews(imagesView);
            List<String> otherViews = new LinkedList<>();
            for (UploadFile file : fileList) {
                DesignFileTypeEnum anEnum = DesignFileTypeEnum.getById(file.getFileType());
                switch (anEnum) {
                    case DESIGN_LEFT_VIEW:
                        imagesView.setLeftViewPath(file.getFilePath());
                        break;
                    case DESIGN_RIGHT_VIEW:
                        imagesView.setRightViewPath(file.getFilePath());
                        break;
                    case DESIGN_PLAN_VIEW:
                        imagesView.setPlanViewPath(file.getFilePath());
                        break;
                    case DESIGN_FRONT_GRID_VIEW:
                        imagesView.setFrontGridViewPath(file.getFilePath());
                        break;
                    case DESIGN_PLAN_GRID_VIEW:
                        imagesView.setPlanGridViewPath(file.getFilePath());
                        break;
                    case DESIGN_OTHER_VIEW:
                        otherViews.add(file.getFilePath());
                        break;
                    case DESIGN_3DMAX:
                        break;
                    default:
                        break;

                }
                imagesView.setOtherViewsPath(otherViews);
            }
        }
    }

    public List<Integer> selectIdByOrderId(Integer orderId) {
        return mapper.selectIdByOrderId(orderId);
    }

    public List<OrderDesignerLog> selectByOrderId(Integer orderId) {
        return mapper.selectByOrderId(orderId);
    }
}
