package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.OrderDesignerLog;
import com.e3expo.e3.model.UploadFile;
import com.e3expo.e3.model.view.WebAppOrderDesignerLogView;

import java.util.List;

public interface OrderDesignerLogMapper {
    void insert(OrderDesignerLog orderDesignerLog);

    /**
     * 根据orderId查询
     * @param orderId
     * @return
     */
    List<WebAppOrderDesignerLogView> selectDesignLogByOrderId(Integer orderId);

    /**
     * 根据uploadID查询所有的文件
     *
     * @param id
     * @return
     */
    List<UploadFile> selectUploadFilesByUploadId(Integer id);

    /**
     * 查询order下所有log的id
     * @param orderId
     * @return
     */
    List<Integer> selectIdByOrderId(Integer orderId);

    /**
     * 根据orderId查询log列表
     * @param orderId
     * @return
     */
    List<OrderDesignerLog> selectByOrderId(Integer orderId);
}
