package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.banner_h5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.QianYueOk;

public class ShouYe_HuoDong extends AppCompatActivity {
    private WebView hd_web;
    private String web_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_ye_huo_dong);
        getid();
        getweb();
    }

    private void getweb() {
        WebSettings webSettings = hd_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        hd_web.loadUrl(web_url);
    }

    private void getid() {
        Intent it=getIntent();
        web_url = it.getStringExtra("h5");
        hd_web=findViewById(R.id.hd_web);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空所有Cookie
        CookieSyncManager.createInstance(ShouYe_HuoDong.this);  //Create a singleton CookieSyncManager within a context
        CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        CookieSyncManager.getInstance().sync(); // forces sync manager to sync now

        hd_web.setWebChromeClient(null);
        hd_web.setWebViewClient(null);
        hd_web.getSettings().setJavaScriptEnabled(false);
        hd_web.clearCache(true);
    }
}
