package com.e3expo.mms.bean.enumeration.comparator;

import com.e3expo.mms.bean.model.Design;


public class ViewDescComparator implements MmsDesignComparator{
    private ViewDescComparator() {}
    public static ViewDescComparator instance = new ViewDescComparator();

    @Override
    public int compare(Object o1, Object o2) {
        Design d1 = setDesignRefOfComparingObject(o1);
        Design d2 = setDesignRefOfComparingObject(o2);
        assert d1 != null && d2 != null;

        if (d2.getViews() == d1.getViews())
            return d2.getDownloads() - d1.getDownloads();
        return d2.getViews() - d1.getViews();
    }
}
