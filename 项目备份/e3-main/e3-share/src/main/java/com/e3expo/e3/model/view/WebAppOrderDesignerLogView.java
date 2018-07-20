package com.e3expo.e3.model.view;

import com.e3expo.e3.model.OrderDesignerLog;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("webAppOrderDesignerLogView")
public class WebAppOrderDesignerLogView extends OrderDesignerLog implements Serializable{

    private DesignImagesView imagesViews;


    public DesignImagesView getImagesViews() {
        return imagesViews;
    }

    public void setImagesViews(DesignImagesView imagesViews) {
        this.imagesViews = imagesViews;
    }
}
