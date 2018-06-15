package com.e3expo.mms.bean.enumeration;

import com.e3expo.mms.bean.enumeration.comparator.AreaAscComparator;
import com.e3expo.mms.bean.enumeration.comparator.AreaDescComparator;
import com.e3expo.mms.bean.enumeration.comparator.DownloadsDescComparator;
import com.e3expo.mms.bean.enumeration.comparator.PriceAscComparator;
import com.e3expo.mms.bean.enumeration.comparator.PriceDescComparator;
import com.e3expo.mms.bean.enumeration.comparator.ViewDescComparator;


import java.util.Comparator;
import java.util.EnumSet;

/**
 * 设计图排序方式枚举
 */
public enum DesignOrderTypeEnum {
    DOWNLOADS_DESC(0, "根据下载次数排序", DownloadsDescComparator.instance),
    VIEWS_DESC(1, "根据查看次数排序", ViewDescComparator.instance),
    PRICE_DESC(2, "价格从高到低排序", PriceDescComparator.instance),
    PRICE_ASC(3, "价格从低到高排序", PriceAscComparator.instance),
    AREA_DESC(4, "面积从高到低排序", AreaDescComparator.instance),
    AREA_ASC(5, "面积从低到高排序", AreaAscComparator.instance);

    private final byte value;
    private final String name;
    private final Comparator comparator;

    DesignOrderTypeEnum(int value, String name, Comparator comparator) {
        this.value = (byte) value;
        this.name = name;
        this.comparator = comparator;
    }
//    DesignOrderTypeEnum(int value, String name) {
//        this.value = value;
//        this.name = name;
//    }

    public byte getValue() {
        return value;
    }


    public String getName() {
        return name;
    }

    public Comparator getComparator() {
        return comparator;
    }

    public static DesignOrderTypeEnum getDesignOrderTypeEnumByValue(byte value) {
        EnumSet<DesignOrderTypeEnum> enums = EnumSet.allOf(DesignOrderTypeEnum.class);
        for (DesignOrderTypeEnum anEnum : enums) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }
}