package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;

public class JiFenGongLv extends AppCompatActivity {
    private WebView jfgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_ji_fen_gong_lv);
        jfgl=findViewById(R.id.jfgl_web);
        WebSettings webSettings = jfgl.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        jfgl.addJavascriptInterface(this, "android");
        // 设置允许JS弹窗
        jfgl.loadUrl("file:///android_asset/strategy.html");
        jfgl.setWebChromeClient(new WebChromeClient());
    }
    @JavascriptInterface
    public void backq() {
        Toast.makeText(this, "js_调用了", Toast.LENGTH_SHORT).show();
        finish();
    }
}
