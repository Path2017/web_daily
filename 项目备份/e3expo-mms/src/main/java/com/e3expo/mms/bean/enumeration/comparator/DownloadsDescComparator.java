package com.e3expo.mms.bean.enumeration.comparator;

import com.e3expo.mms.bean.model.Application;
import com.e3expo.mms.bean.model.Design;

import java.util.Comparator;

public class DownloadsDescComparator implements MmsDesignComparator{
    private DownloadsDescComparator() {}
    public static DownloadsDescComparator instance = new DownloadsDescComparator();

    @Override
    public int compare(Object o1, Object o2) {
        Design d1 = setDesignRefOfComparingObject(o1);
        Design d2 = setDesignRefOfComparingObject(o2);
        assert d1 != null && d2 != null;
        if (d1.getDownloads() == d2.getDownloads())
            return d2.getViews() - d1.getViews();
        return d2.getDownloads() - d1.getDownloads();
    }
}
