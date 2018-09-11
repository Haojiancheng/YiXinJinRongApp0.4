package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.chongxinpingce;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;

public class ChongXinPingCe extends AppCompatActivity {
    private WebView cxpc_vciew;
    private int user_id;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_xin_ping_ce);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .keyboardEnable(true)
                .init();
        cxpc_vciew=findViewById(R.id.cxpc_vciew);
        WebSettings webSettings = cxpc_vciew.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        // 设置允许JS弹窗
        cxpc_vciew.loadUrl("file:///android_asset/indexNew.html?userid="+user_id);

        cxpc_vciew.addJavascriptInterface(this,"android" );
        cxpc_vciew.getSettings().setJavaScriptEnabled(true);
//      https://www.jd.com/?cu=true&utm_source=kong&utm_medium=tuiguang&utm_campaign=t_299570028_&utm_term=449b27a7d1304eb2b08e50e7bca5615d&abt=3


        cxpc_vciew.setWebViewClient(new WebViewClient());
        cxpc_vciew.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==KeyEvent.ACTION_DOWN){
                    if (keyCode==KeyEvent.KEYCODE_BACK&&cxpc_vciew.canGoBack()){
                        cxpc_vciew.canGoBack();
                        return true;
                    }
                }
                return false;
            }
        });
        cxpc_vciew.setWebChromeClient(new WebChromeClient());

    }
    @JavascriptInterface
    public void backq() {
        finish();
    }
}
