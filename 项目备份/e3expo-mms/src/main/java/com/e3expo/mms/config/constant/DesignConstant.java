package com.e3expo.mms.config.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DesignConstant {
    private DesignConstant() {
        throw new AssertionError();
    }

    public static int OSS_IMG_EXPIRATION_MILLISECONDS = 1000 * 60 * 10;

    public static DateFormat DESIGN_HISTORY_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
    public static DateFormat APPLICATION_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static long MILLISECONDS_IN_ONE_DAY = 1000 * 60 * 60 * 24 - 1000;
}