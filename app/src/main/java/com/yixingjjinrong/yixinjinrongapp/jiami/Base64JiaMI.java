package com.yixingjjinrong.yixinjinrongapp.jiami;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Base64JiaMI {
    public static byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    private static String key = "hiydkutfpolkwqdrjiknmcxgyz684927";
    public static String ivStr="dkdhuyfn25644558";
    public static String AES_Encode(String str) {
        String newStr = "";
        byte[] textBytes;
        try {
            textBytes = str.getBytes("UTF-8");
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivStr.getBytes());
            SecretKeySpec newKey;
            newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = null;
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
            byte[] strBytes = cipher.doFinal(textBytes);
            newStr = Basess.encode(strBytes);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newStr;
    }


}
