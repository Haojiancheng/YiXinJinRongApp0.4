package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class YinHangCunGuan extends AutoLayoutActivity {
    private WebView cg_wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinhang_cunguan);
        cg_wb=findViewById(R.id.cg_wb);
        Intent it=getIntent();
        String htlm = it.getStringExtra("HTML");
        Log.e("开通存管HTML:",""+htlm);
        WebSettings webSettings = cg_wb.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存

        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 //重点是这个设置
        cg_wb.loadData(htlm.toString(), "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。

    }

}
