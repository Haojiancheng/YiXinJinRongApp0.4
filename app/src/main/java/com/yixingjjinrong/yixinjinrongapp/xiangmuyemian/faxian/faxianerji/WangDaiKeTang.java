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

import java.net.URL;

public class WangDaiKeTang extends AutoLayoutActivity {
    private ImageView wdclass_fh;
    private WebView wdkt_web;
//    private View whatP2P, cade, zhishi, chujieren_web,jiekuanren_web,pingtaiyiwu;
//    private View good_web;
//    private View no_things;
//    private View guiding;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_wang_dai_ke_tang);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();
        getonclick();
        //***********************
        WebSettings webSettings = wdkt_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        wdkt_web.addJavascriptInterface(this, "android");
        // 设置允许JS弹窗

        wdkt_web.setWebChromeClient(new WebChromeClient());
        wdkt_web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        wdkt_web.loadUrl(Urls.BASE_URL+"Netcreditcourses.do?nav=5-0");
    }

    private void getonclick() {
        wdclass_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        whatP2P.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent(WangDaiKeTang.this, Class_xiangqing.class);
//                it.putExtra("number", "1");
//                startActivity(it);
//            }
//        });
//        cade.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent(WangDaiKeTang.this, Class_xiangqing.class);
//                it.putExtra("number", "2");
//                startActivity(it);
//            }
//        });
//        zhishi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent(WangDaiKeTang.this, Class_xiangqing.class);
//                it.putExtra("number", "3");
//                startActivity(it);
//            }
//        });
//        chujieren_web.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
//                it.putExtra("number", "4");
//                startActivity(it);
//            }
//        });
//        jiekuanren_web.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
//                it.putExtra("number", "5");
//                startActivity(it);
//            }
//        });
//        pingtaiyiwu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
//                it.putExtra("number", "6");
//                startActivity(it);
//            }
//        });
//        good_web.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
//                it.putExtra("number", "7");
//                startActivity(it);
//            }
//        });
//        no_things.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
//                it.putExtra("number", "8");
//                startActivity(it);
//            }
//        });
//        guiding.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
//                it.putExtra("number", "9");
//                startActivity(it);
//            }
//        });

    }

    private void getid() {
        wdclass_fh = findViewById(R.id.wdclass_fh);
        wdkt_web=findViewById(R.id.wdkt_web);
//        whatP2P = findViewById(R.id.whatP2P);
//        cade = findViewById(R.id.cade);
//        zhishi = findViewById(R.id.zhishi);
//        chujieren_web = findViewById(R.id.chujieren_web);
//        jiekuanren_web=findViewById(R.id.jiekuanren_web);
//        pingtaiyiwu=findViewById(R.id.pingtaiyiwu);
//        good_web = findViewById(R.id.good_web);
//        no_things = findViewById(R.id.no_things);
//        guiding = findViewById(R.id.guiding);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }
}
