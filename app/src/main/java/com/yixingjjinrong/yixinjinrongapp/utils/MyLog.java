package com.yixingjjinrong.yixinjinrongapp.utils;

import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;

import com.yixingjjinrong.yixinjinrongapp.BuildConfig;

public class MyLog {
    //读取BuildConfig.LOG_DEBUG 签名时为FALSE 不打印 debug时为true 打印
//    public static void i(String tag,String message){
//        if(BuildConfig.LOG_DEBUG) ThemedSpinnerAdapter.Helper.logi(tag,message);
//    }
    public static void d(String tag,String message){
        if(BuildConfig.LOG_DEBUG) Log.d(tag,message);
    }
    public static void e(String tag,String message){
        if(BuildConfig.LOG_DEBUG)Log.e(tag,message);
    }
}
