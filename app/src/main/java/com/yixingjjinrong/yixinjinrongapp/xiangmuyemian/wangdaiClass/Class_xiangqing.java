package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.wangdaiClass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhiOK;
import com.zhy.autolayout.AutoLayoutActivity;

public class Class_xiangqing extends AutoLayoutActivity {
    private WebView wd_web;
    private String number;
    private ImageView class_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_xiangqing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        getid();
        getweb();
        class_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getweb() {
        switch (number){
            case "1":
                wd_web.loadUrl("http://192.168.1.219:8080/yxb_mobile/yxbApp/whatisPtoP.do");
                break;
            case "2":
                wd_web.loadUrl("http://192.168.1.219:8080/yxb_mobile/yxbApp/commonNouns.do");
                break;
            case "3":
                wd_web.loadUrl("http://192.168.1.219:8080/yxb_mobile/yxbApp/creditKnowledge.do");
                break;
            case "4":
                wd_web.loadUrl("http://192.168.1.219:8080/yxb_mobile/yxbApp/obligationOfLenders.do");
                break;
            case "5":
                wd_web.loadUrl("http://192.168.1.219:8080/yxb_mobile/yxbApp/obligationsOfBorrowers.do");
                break;
            case "6":
                wd_web.loadUrl("http://192.168.1.219:8080/yxb_mobile/yxbApp/obligationOfNetLoanPlatform.do");
                break;
            case "7":
                wd_web.loadUrl("http://192.168.1.219:8080/yxb_mobile/yxbApp/platformProhibition.do");
                break;
            case "8":
                wd_web.loadUrl("http://192.168.1.219:8080/yxb_mobile/yxbApp/selectionPlatform.do");
                break;
            case "9":
                wd_web.loadUrl("http://192.168.1.219:8080/yxb_mobile/yxbApp/interestRateRegulation.do");
                break;
        }



    }

    private void getid() {
        class_fh=findViewById(R.id.class_fh);
        wd_web=findViewById(R.id.wd_web);
        Intent it=getIntent();
        number = it.getStringExtra("number");
        WebSettings webSettings = wd_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        wd_web.getSettings().setJavaScriptEnabled(true);
//        pc_web.loadUrl("javascript:sendKey("+"'"+String.valueOf(user_id)+"'"+")");
        wd_web.setWebChromeClient(new WebChromeClient());
        // 设置允许JS弹窗
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空所有Cookie
        CookieSyncManager.createInstance(Class_xiangqing.this);  //Create a singleton CookieSyncManager within a context
        CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        CookieSyncManager.getInstance().sync(); // forces sync manager to sync now

        wd_web.setWebChromeClient(null);
        wd_web.setWebViewClient(null);
        wd_web.getSettings().setJavaScriptEnabled(false);
        wd_web.clearCache(true);

    }
}
