package com.e3expo.mms.bean.enumeration.comparator;

import com.e3expo.mms.bean.model.Design;

public class AreaDescComparator implements MmsDesignComparator {
    private AreaDescComparator() {
    }

    public static AreaDescComparator instance = new AreaDescComparator();

    @SuppressWarnings("Duplicates")
    @Override
    public int compare(Object o1, Object o2) {
        Design d1 = setDesignRefOfComparingObject(o1);
        Design d2 = setDesignRefOfComparingObject(o2);
        assert d1 != null && d2 != null;
        if (d1.getArea() == d2.getArea()) {
            if (d1.getDownloads() == d2.getDownloads()) {
                return d2.getViews() - d1.getViews();
            } else {
                return d2.getDownloads() - d1.getDownloads();
            }
        } else {
            return d2.getArea() - d1.getArea();
        }
    }
}
