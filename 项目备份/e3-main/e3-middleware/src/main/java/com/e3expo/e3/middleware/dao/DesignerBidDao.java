package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.enumration.DesignerBidIsSuccessfulEnum;
import com.e3expo.e3.enumration.DesignerPriceConfigEnum;
import com.e3expo.e3.enumration.EnumValidStatus;
import com.e3expo.e3.middleware.mapper.DesignerBidMapper;
import com.e3expo.e3.model.DesignerBid;
import com.e3expo.e3.model.OrderDesignerPrice;
import com.e3expo.e3.model.view.WebAppOrderDesignerView;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DesignerBidDao {

    private DesignerBidMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private void setSqlSessionTemplate(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(DesignerBidMapper.class);
    }

    /**
     * 根据orderId查询所有的bid
     * @param orderId
     * @return
     */
    public List<DesignerBid> selectByOrderId(Integer orderId) {
        return mapper.selectByOrderId(orderId);
    }

    /**
     * 插入一条竞标记录
     * @param orderId    订单id
     * @param designerId 设计师id
     */
    public void insert(Integer orderId, Integer designerId) {
        DesignerBid bid = new DesignerBid();
        bid.setOrderId(orderId);
        bid.setDesignerId(designerId);
        bid.setBidTime(System.currentTimeMillis());
        bid.setIsSuccess(DesignerBidIsSuccessfulEnum.UNSUCCESSFUL.getValue());
        bid.setIsValid(EnumValidStatus.VALID.getValue());
        mapper.insert(bid);
    }

    /**
     * 更新投标是否成功
     * @param anEnum
     * @param designerIds
     */
    public void updateIsSuccessByDesignerId(DesignerBidIsSuccessfulEnum anEnum, List<Integer> designerIds) {
        assert designerIds != null;
        if (designerIds.size() > 0) {
            mapper.updateIsSuccessByDesignerId(anEnum.getValue(), designerIds);
        }
    }

    /**
     * 根据orderId查询竞标的详情
     * @param orderId
     */
    public List<WebAppOrderDesignerView> selectBidDetailByOrderId(Integer orderId) {
        List<WebAppOrderDesignerView> bidList = mapper.selectBidDetailByOrderId(orderId);
        if (bidList != null && bidList.size() > 0) {
            for (WebAppOrderDesignerView view : bidList) {
                List<OrderDesignerPrice> prices = mapper.selectDesignerBiddingPrice(view.getUserId(), orderId);
                setOrderPriceMethod(prices, view);
            }
        }
        return bidList;
    }

    public static void setOrderPriceMethod(List<OrderDesignerPrice> priceList, WebAppOrderDesignerView view) {
        if (priceList != null && priceList.size() > 0) {
            for (OrderDesignerPrice price : priceList) {
                DesignerPriceConfigEnum anEnum = DesignerPriceConfigEnum.getEnum(price.getNodeId(), price.getType());
                if (anEnum != null) {
                    switch (anEnum) {
                        case DESIGN_PRICE:
                            view.setDesignPrice(price.getPrice());
                            break;
                        case MODIFY_DESIGN_PRICE:
                            view.setModifyDesignPrice(price.getPrice());
                            break;
                        case WORKING_DRAWING_PRICE:
                            view.setWorkingDrawingPrice(price.getPrice());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public List<Integer> selectBadeOrderIdByDesignerId(Integer designerId) {
        return mapper.selectBadeOrderIdByDesignerId(designerId);
    }
}
