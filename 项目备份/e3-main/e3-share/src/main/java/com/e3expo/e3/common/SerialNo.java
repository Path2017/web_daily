package com.e3expo.e3.common;

import java.util.Date;
import java.util.Random;

public class SerialNo {
	private static Random random = new Random(100);
	/**
	 * 暂时用时间戳作为工号的生成规则，后续需要修改再做调整
	 * @return
	 */
	public static String createJobNumber() {
		Date date = new Date();
		String serialNo = String.valueOf(date.getTime() / 1000);
		return serialNo;
	}
	public static String getSixDigits() {
        return String.format("%06d",random.nextInt(1000000));
    }
	public static void main(String[] args) {
		System.out.println(createJobNumber());
	}
}
