package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;


public class WebView1 extends AutoLayoutActivity {
    private android.webkit.WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        wb = findViewById(R.id.webwang1);
        wb.loadUrl("https://wechat.yxb.com/yxbApp/threeAgreement.do");
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wb.addJavascriptInterface(new JsInteration(), "android");
    }
    public class JsInteration {
        @JavascriptInterface
        public void closePop() {
            SPUtils.put(WebView1.this, "finsh", "nofinsh");
            finish();
        }
        @JavascriptInterface
        public void checkedIcon(){
            SPUtils.put(WebView1.this, "checked", "yes");
            SPUtils.put(WebView1.this, "finsh", "nofinsh");
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }
}
