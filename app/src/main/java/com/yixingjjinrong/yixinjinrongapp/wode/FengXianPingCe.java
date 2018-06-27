package com.yixingjjinrong.yixinjinrongapp.wode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebSettings;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;


public class FengXianPingCe extends AutoLayoutActivity {
    private WebView pc_web;
    private int user_ird;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fengxian_pingce);
        pc_web=findViewById(R.id.pc_web);

        Bundle b = getIntent().getExtras();
        user_ird = b.getInt("user_ird");


        WebSettings webSettings = pc_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        // 设置允许JS弹窗
        pc_web.addJavascriptInterface(user_ird, "sendKey");
        pc_web.loadUrl("file:///android_asset/index.html");

        pc_web.setWebChromeClient(new WebChromeClient());
    }
}
