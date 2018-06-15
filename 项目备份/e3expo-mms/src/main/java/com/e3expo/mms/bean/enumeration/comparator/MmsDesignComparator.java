package com.e3expo.mms.bean.enumeration.comparator;

import com.e3expo.mms.bean.model.Application;
import com.e3expo.mms.bean.model.Design;

import java.util.Comparator;

public interface MmsDesignComparator extends Comparator {

    default Design setDesignRefOfComparingObject(Object o) {
        if (o instanceof Design)
            return (Design) o;
        if (o instanceof Application)
            return ((Application) o).getDesign();
        return null;
    }
}
