package com.e3expo.e3.webapp.common;

import java.util.Random;

public class VerificationCodeUtil {

    private static Random random = new Random(47);

    private VerificationCodeUtil() {
    }

    public static String getSixDigits() {
        return String.format("%06d",random.nextInt(1000000));
    }
}
