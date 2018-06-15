package com.e3expo.mms.bean.param;

import com.e3expo.mms.bean.model.DesignHistory;

public class DesignHistoryListParam extends PageParam<DesignHistory> {
    private int designId;

    public DesignHistoryListParam() {
    }

    public DesignHistoryListParam(int designId) {
        this.designId = designId;
    }

    public int getDesignId() {
        return designId;
    }

    public void setDesignId(int designId) {
        this.designId = designId;
    }
}