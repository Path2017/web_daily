package com.e3expo.e3.webapp.services;

import com.e3expo.e3.model.DesignerPriceConfig;
import com.e3expo.e3.model.form.DesignerPriceConfigForm;
import com.e3expo.e3.service.interfaces.IDesignerBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignerBidService {

    @Autowired
    private IDesignerBid designerBid;

    public List<DesignerPriceConfig> getDesignerPriceConfig(Integer userId) {
        return designerBid.getDesignerPriceConfig(userId);
    }

    public void setPriceConfigAndBid(DesignerPriceConfigForm form) {
        designerBid.createOrUpdateDesignerPriceConfig(form);
        designerBid.bid(form);
    }
}
