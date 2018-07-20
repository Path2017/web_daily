package  com.e3expo.e3.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;



/**
 * Created by Administrator on 2016/3/24.
 */
public class EncodeUtil {
   
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
//    public static void main(String[] args) {
//    	createPassword("100001");
//    	String rr = "F3C70A24CDF0FC01278CE828E7A8BB80CE7F00E3";
//    	
//	}
    
    public static String createPassword(String password){
    	String minPassword = "Jsl5eyA"+password;
    	minPassword = MixEncode(minPassword);
    	String result = SHA1(minPassword);
    	System.out.println(result);
    	return result;
    }
    public static String MixEncode(String str)
    {
        char[] strChar = str.toCharArray();
        String result = "";
        double l = Math.ceil((double) strChar.length/3);
        for (int i = 0; i < l; i++)
        {
            int j = 3*i;
            String a = String.valueOf(strChar[j]);
            String b = strChar.length > j + 1 ? String.valueOf(strChar[j+1]) : "";
            String c = strChar.length > j + 2 ? String.valueOf(strChar[j + 2]) : "";

            result = result + b + c + a;
        }

        return result;
    }
    
    public static void main(String[] args) {
		String uid = UUID.randomUUID().toString();
		System.out.println(System.currentTimeMillis());
		System.out.println(uid);
//		com.thoughtworks.xstream.converters.basic.BigIntegerConverter.bu
	}
}
