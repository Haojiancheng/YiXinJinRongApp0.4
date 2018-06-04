package com.yixingjjinrong.yixinjinrongapp.shaijiami;

import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
    public static String getSHA(String val) throws NoSuchAlgorithmException{
        MessageDigest md5 = MessageDigest.getInstance("SHA-1");
        md5.update(val.getBytes());
        byte[] m = md5.digest();//加密  
        return getString(m);
    }

    private static String getString(byte[] b){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < b.length; i ++){
            sb.append(b[i]);
        }
        return sb.toString();
    }
    public static String getKey(String key){
        //Key
//        String str = "hiydkutfpolkwqdrjiknmcxgyz684927";
        //base64编码  
        //String strBase64 = new String(Base64.encode(str.getBytes(), Base64.DEFAULT));  
        String strBase64 = Base64.encodeToString(key.getBytes(), Base64.DEFAULT);
        Log.e("TAG", ">>>>" + strBase64);
        //base64解码  
        String str2 = new String(Base64.decode(strBase64.getBytes(), Base64.DEFAULT));
        Log.e("TAG", ">>>>" + str2);
        return key;
    }

}
