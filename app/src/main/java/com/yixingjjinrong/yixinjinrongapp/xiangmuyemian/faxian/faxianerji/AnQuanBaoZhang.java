package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class AnQuanBaoZhang extends AutoLayoutActivity {
    private WebView anquanweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_quan_bao_zhang);
        anquanweb=findViewById(R.id.anquan_web);
        WebSettings webSettings = anquanweb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        // 设置允许JS弹窗
        anquanweb.loadUrl("file:///android_asset/anquan.html");

        anquanweb.setWebChromeClient(new WebChromeClient());
    }
    @JavascriptInterface
    public void back() {
        finish();
    }
}
