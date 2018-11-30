package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.zhy.autolayout.AutoLayoutActivity;

public class PingTaJieShao extends AutoLayoutActivity {
    private ImageView ptjs_fh;
    private WebView ptjs_web;
//    private TextView zcxy_h5;
//    private TextView gzh;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_ta_jie_shao);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();
        getonclick();

//        zcxy_h5 = findViewById(R.id.zcxy_h5);
//        gzh=findViewById(R.id.gzh);

//        zcxy_h5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent zhuce_page = new Intent(PingTaJieShao.this, ZhuCeH5.class);
//                startActivity(zhuce_page);
//            }
//        });
//        gzh.setText("微信订阅号：亿信金融（微信号：yixinjinrong2015）"+"\n"+"微信服务号：亿信宝（微信号：yxb-com）");

        //***********************
        WebSettings webSettings = ptjs_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        ptjs_web.addJavascriptInterface(this, "android");
        // 设置允许JS弹窗

        ptjs_web.setWebChromeClient(new WebChromeClient());
        ptjs_web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        ptjs_web.loadUrl(Urls.BASE_URL+"disclosure.do?nav=1-1");

    }

    private void getonclick() {
        ptjs_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getid() {
        ptjs_fh = findViewById(R.id.ptjs_fh);
        ptjs_web=findViewById(R.id.ptjs_web);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }
}