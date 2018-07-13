package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class ChongZhiOK extends AutoLayoutActivity {
    private WebView chongzok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_zhi_ok);
        chongzok=findViewById(R.id.chongzok);
        Intent intent=getIntent();
        String html = intent.getStringExtra("HTML");
        Log.e("充值成功HTM：L", html.toString());
        WebSettings webSettings = chongzok.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存

        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 //重点是这个设置
        chongzok.loadData(html.toString(), "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。
    }
}
