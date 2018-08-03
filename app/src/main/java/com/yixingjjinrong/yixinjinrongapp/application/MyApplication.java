package com.yixingjjinrong.yixinjinrongapp.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import org.xutils.BuildConfig;
import org.xutils.x;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

    }
}
