package com.e3expo.e3.common.alipay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016082600313465";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCQ4UNo6ikgfp3KxZrALeSPoe0i7VSGwsvixrvTqdFBqJvYRsawL8L02ritGAKKba2DOnnpnrZA3OmWRKNVjf6RbUZEzSrCSVcWPrZmP9yOVyAJq7sBzF8Y3SQ/2oGGjDEPqCnDa53PvnpUR/wE8D8rruescjLkUki1OTC6gUJ4lwprF5xHxGQtEu4J1tG/jRk1UXrUoUPtT5JpZaFLq892B+6twE4vxm+coxRS6wwUN0k1OWlMINMdllmRGkDFczt1iDCs33ZF6fVucH66OPKIiAIEhT8a3cV0kzAcA+qkHU019m6lmJq754CNJ6PP+AV64t+4XlnrEU6OTmO91jHNAgMBAAECggEAFkv0ueK/rZaO5jP/GvB1Kn27JxLrXUoBYsc/BNYvtDEkuuY5kcfn8xLuNQVq7q7uczG+rWuVlOnqsQujL3mI4gy1McV5fSkYU/4WyCmv4eAauCJLx30SbZsrBwCzymcs7cHtm3umb+Bapznk2UW4Exr54Jo6ptTbB7jjfPCDsFch9DR2wO+nEmE4Gp5lM1ZHM/FLwfTD+yefVuWQFzFYMh3f5haM3Fr1EG0TOnPb7ItkGjEsKU+swJYA3RZq6PzrMaOtOwnR5LKFLpBABXK6tObEtt1A9aa/oXPsL7iIRStYQEGYfgEzRY5/f7rixydNk/y8Cww6BlG09p1rmexpwQKBgQDrVkCokX3F5Qd4ty1by3z6atJdEMeyaDO0MsyrMh9sCGpfZFVwtf9ZI6MhTeOl9pdYLZNcgocE3pNFrxtO4sQH8Zqx3Cu4VoSJpF9B6Kt4cn7X3Bo/6CoLi95X88EEDKwEg02ynAZR7PBjRDgrQIamaKsBy9URUcZBBp/h4KB8aQKBgQCdmcdK41ftrLOGFDXThGWKm2Abf+pFbXaR2hVogFvSxB1UtOxJriuQOBHgp7+2G9COdTbreXMSUDOfGWWjrJMs2eYc0tu4SYqiOyHFiDsveSOsRh2JTWk5RPR1yWrAEdfKQZzjPKWxxJ2KeKSu+kw+pr59oZpDGj02b+omGbgtxQKBgD3hy1TIFSuBYuqSOEIoCvAKQNCqBejaz6W1qe3mWy7cM6RrzK+7m66D2XR7d73/TqKoMVSlGf3kzRMIzYR7ypJQLJ5SR8jML0PGTioVlWkxqjr2rIxbbxrkJ443EW0QK/dv03GA54SJalif6qks6Xs+kKEDST6poxkcNdR+bZTpAoGACR3PIG7cNDxsOhJLNswOz6DDQ3P3ykJVvIQf/uaF6UA87hx1GqH/lR0ZM6Sge1lKjCMNpTtmQ6XuBoMClSAzd0G3hO1Rlmxyn4XKI1IJEmrwfFOl+75FmvF5LqMTEL83e1SmTaLBdvIHpu3poNGLvYgJtty1PrJy3gZFRiTwuX0CgYBPl+hqCrQvOkOmEtIfumzQILnTEkEIPl4U+yADGptga3LW6it+RJiNd3e1HJQiXN+/bcu9sJFOon8yuyyXgQ7iQvhGEdbpqL0FOh96myFgnfRT9z/h5ls4DeWgtq03WRY47eZhZshdkB1CXleb7pobF1s0PUhU7YXLsbQww9EwRg==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkOFDaOopIH6dysWawC3kj6HtIu1UhsLL4sa706nRQaib2EbGsC/C9Nq4rRgCim2tgzp56Z62QNzplkSjVY3+kW1GRM0qwklXFj62Zj/cjlcgCau7AcxfGN0kP9qBhowxD6gpw2udz756VEf8BPA/K67nrHIy5FJItTkwuoFCeJcKaxecR8RkLRLuCdbRv40ZNVF61KFD7U+SaWWhS6vPdgfurcBOL8ZvnKMUUusMFDdJNTlpTCDTHZZZkRpAxXM7dYgwrN92Ren1bnB+ujjyiIgCBIU/Gt3FdJMwHAPqpB1NNfZupZiau+eAjSejz/gFeuLfuF5Z6xFOjk5jvdYxzQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//	public static String notify_url = "http://1733g5a837.iok.la/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//	public static String return_url = "http://1733g5a837.iok.la/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	public static String format="json";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "E:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

