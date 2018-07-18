package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yixingjjinrong.yixinjinrongapp.R;

public class QianYueOk extends AppCompatActivity {
    private WebView qy_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qian_yue_ok);
        qy_web=findViewById(R.id.qy_web);
        Intent it=getIntent();
        String html = it.getStringExtra("HTML");
        Log.e("签约HTMl", html);
        qy_web.getSettings().setSupportZoom(true);
        qy_web.getSettings().setBuiltInZoomControls(true);
        WebSettings webSettings = qy_web.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存

        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 //重点是这个设置
        qy_web.loadData(html.toString(), "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。
    }
}
