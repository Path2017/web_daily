package com.e3expo.mms.bean.enumeration.comparator;

import com.e3expo.mms.bean.model.Design;

public class PriceDescComparator implements MmsDesignComparator {
    private PriceDescComparator() {
    }

    public static PriceDescComparator instance = new PriceDescComparator();

    @SuppressWarnings("Duplicates")
    @Override
    public int compare(Object o1, Object o2) {
        Design d1 = setDesignRefOfComparingObject(o1);
        Design d2 = setDesignRefOfComparingObject(o2);
        assert d1 != null && d2 != null;
        int sum1 = d1.getPriceLowerLimit() + d1.getPriceUpperLimit();
        int sum2 = d2.getPriceLowerLimit() + d2.getPriceUpperLimit();
        if (sum1 == sum2) {
            if (d1.getDownloads() == d2.getDownloads()) {
                return d2.getViews() - d1.getViews();
            } else {
                return d2.getDownloads() - d1.getDownloads();
            }
        } else {
            return sum2 - sum1;
        }
    }
}
