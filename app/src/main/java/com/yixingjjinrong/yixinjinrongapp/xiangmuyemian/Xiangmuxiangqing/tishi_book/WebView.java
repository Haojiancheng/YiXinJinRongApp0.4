package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.TextView;
import android.widget.Toast;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.zhy.autolayout.AutoLayoutActivity;


public class WebView extends AutoLayoutActivity {
    private android.webkit.WebView wb;
    private TextView title2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        wb = findViewById(R.id.webwang1);
        title2=findViewById(R.id.title2);
        Intent it = getIntent();
        String url = it.getStringExtra("url");
        String title1 = it.getStringExtra("title1");
        title2.setText(title1);
        WebSettings webSettings = wb.getSettings();

        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        wb.loadUrl(Urls.BASE_URL+url);
    }
    @JavascriptInterface
    public void backq() {
        Toast.makeText(this, "调用back()",Toast.LENGTH_LONG ).show();
        finish();
    }
}
