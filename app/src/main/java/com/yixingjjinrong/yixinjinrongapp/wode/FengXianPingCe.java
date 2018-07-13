package com.yixingjjinrong.yixinjinrongapp.wode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class FengXianPingCe extends AutoLayoutActivity {
    private WebView pc_web;
    private int user_id;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fengxian_pingce);
        pc_web=findViewById(R.id.pc_web);

        user_id = (int) SPUtils.get(this,"userId",0);


        WebSettings webSettings = pc_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        // 设置允许JS弹窗
        pc_web.getSettings().setJavaScriptEnabled(true);
        pc_web.addJavascriptInterface(this, "android");
        JSONObject json = new JSONObject();
        try {
            json.put("userid",user_id );
            pc_web.loadUrl("javascript:sendKey(\""+json.toString()+ "\")");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        pc_web.loadUrl("file:///android_asset/index.html");
//        pc_web.loadUrl("javascript:sendKey("+"'"+String.valueOf(user_id)+"'"+")");



        pc_web.setWebViewClient(new WebViewClient());
        pc_web.setWebChromeClient(new WebChromeClient());
        pc_web.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==KeyEvent.ACTION_DOWN){
                    if (keyCode==KeyEvent.KEYCODE_BACK&&pc_web.canGoBack()){
                        pc_web.canGoBack();
                        return true;
                    }
                }
                return false;
            }
        });
        pc_web.setWebChromeClient(new WebChromeClient());
    }

    @JavascriptInterface
    public void backq() {
        Toast.makeText(this, "调用back()",Toast.LENGTH_LONG ).show();
        finish();
    }

}
