package com.e3expo.e3.util;

import java.util.Random;

/**
 * 该类用于创建系统所需的业务编号，如唯一订单号，唯一工号等
 * @author lizy
 *
 */
public class NoUtil {
	private static Random random = new Random(47);

    private NoUtil() {
    }

    /**
     * 获取后台用户所需的七位识别号
     * @return
     */
    public static String getSevenDigits() {
        return String.format("%07d",random.nextInt(10000000));
    }
}
