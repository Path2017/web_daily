package com.e3expo.e3.common;

public class Constants {
	
	public final static String USER_LOGIN = "/user/login";
	
	/**
	 * 检查用户名密码
	 */
	public final static String USER_CHECK="/checkuser";

	/**
	 * webapp 上传的图片文件保存的位置
	 */
	public final static String UPLOAD_FILE_FOLDER_FULL_PATH = "e:/upload_files";
	/**
	 * 访问上传图片文件的base url，需要拼接上path组成完整路径
	 */
	public final static String UPLOAD_IMAGEF_BASE_URL = "http://localhost:8082/uploads";

	/**
	 * Redis中users key
	 *
	 */
	public final static String REDIS_HASH_USERS = "users";
	/**
	 * Redis中user详情表的前缀
	 */
	public final static String REDIS_HASH_USER_PREFIX = "user:";
	/**
	 * Redis中user详情表的前缀
	 */
	public final static String REDIS_FIELD_STATUS = "status";
	public final static String REDIS_FIELD_PASSWORD = "password";
	public final static String REDIS_FIELD_USER_TYPE = "userType";
	public final static String REDIS_FIELD_IS_VALID = "is_valid";
	/**
	 * Redis中抢单的Hash结构对应的key
	 *
	 */
	public final static String REDIS_HASH_BIDDING_ORDER = "bidding_order";
	/**
	 * Redis中某一订单的抢单列表的前缀
	 */
	public final static String REDIS_SET_BIDDING_ORDER_LIST_PREFIX = "bidding_order_list:";

	/**
	 * 一兆
	 */
	public final static long MEGABYTES = 1024 * 1024;

	/**
	 * 一个订单最多只能有5个投标
	 */
	public final static int ORDER_BIDDING_UPPER_LIMIT = 5;

	/**
	 * 一小时的秒数
	 */
	public final static int SECONDS_OF_AN_HOUR = 60 * 60;

	/**
	 * 免费修改次数为2，数据库中对应-2
	 */
	public final static int FREE_MODIFY_DESIGN_TIMES = 2;
}
