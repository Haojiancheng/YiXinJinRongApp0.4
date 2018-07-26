package com.yixingjjinrong.yixinjinrongapp.wode.tixian;



import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.MyWebChromeClient;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TiXian_OK extends AutoLayoutActivity {
    private WebView tixian_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_ti_xian__ok);
        tixian_web=findViewById(R.id.tixian_web);
        final Intent intent=getIntent();
        String html = intent.getStringExtra("tixianhtml");
        Log.e("提现成功HTM：L", html);
        WebSettings webSettings = tixian_web.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 /
        tixian_web.loadData(html, "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。


        tixian_web.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                tixian_web.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("提现返回的URL", ""+url);
                //http://localhost:8080/yxb_mobile/yxbApp/frontWithdrawUrlApp.do?receiveCode=0000&receiveJson={%22amt%22:%2220000%22,%22login_id%22:%2217720181111%22,%22mchnt_cd%22:%220003310F0351795%22,%22mchnt_txn_ssn%22:%22ZJWD20180725142222457471%22,%22rem%22:%22%22,%22resp_code%22:%220000%22,%22resp_desc%22:%22??%22,%22signature%22:%22z+UJ+oZhZUhNvwtkRN0RUECIBgjtHOi6FHrGQrLLAGtMgX0rH+ZsZFtO1W1Cx20yezXhxAGAMv2YhhwxrD0H5KsDxee53Xx4rikWJhktQadpOJ1dtqka+rTaQeIy1Rz3WvCr/LjHp7SbEfH8ZZAiEU0AyCd2/xAAQCcIofD5BtY=%22}
                if (url.indexOf("http://localhost:8080/yxb_mobile/yxbApp/frontWithdrawUrlApp.do?")!=-1){
                    String leng="http://localhost:8080/yxb_mobile/yxbApp/frontWithdrawUrlApp.do?receiveCode=0000&receiveJson=";
                    Log.e("包含","继续……" +leng.length());
                    String one = url.substring(75, 79);//substring是截取2个位置之间及start-end之间的字符串
                    Log.e("receiveCode", ""+one);
                    String json = url.substring(92, url.length());

                    try {
                        String strGBK = URLEncoder.encode(json, "UTF-8");
                        Log.e("receiveJson", ""+strGBK);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        tixian_web.setWebChromeClient(new MyWebChromeClient());
    }
}
