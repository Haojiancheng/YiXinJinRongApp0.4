package com.yixingjjinrong.yixinjinrongapp.wode.h5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.zhy.autolayout.AutoLayoutActivity;

public class ZhuCeH5 extends AutoLayoutActivity {
    private android.webkit.WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce_h5);
        wb=findViewById(R.id.zuce1);
        WebSettings webSettings = wb.getSettings();

        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        wb.loadUrl("file:///android_asset/agreement.html");
    }
}
