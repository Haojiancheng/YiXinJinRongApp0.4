package com.yixingjjinrong.yixinjinrongapp.wode.tixian;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.GsonBean;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhiOK;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.QianYueOk;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiShiBai;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiSuccers;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.MyWebChromeClient;
import com.yixingjjinrong.yixinjinrongapp.wode.tixian.jieguo.Putforward_fail;
import com.yixingjjinrong.yixinjinrongapp.wode.tixian.jieguo.Putforward_ok;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

public class TiXian_OK extends AutoLayoutActivity {
    private WebView tixian_web;
    private ArrayList<String> ss = new ArrayList<>();
    private String docode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_ti_xian__ok);
        tixian_web = findViewById(R.id.tixian_web);
        final Intent intent = getIntent();
        String html = intent.getStringExtra("tixianhtml");
        MyLog.e("提现成功HTM：L", html);
        WebSettings webSettings = tixian_web.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 /
        tixian_web.loadData(html, "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。


        tixian_web.setWebViewClient(new WebViewClient() {

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
                MyLog.e("提现返回的URL", "" + url);
                //http://localhost:8080/yxb_mobile/yxbApp/frontWithdrawUrlApp.do?receiveCode=0000&receiveJson={%22amt%22:%2220000%22,%22login_id%22:%2217720181111%22,%22mchnt_cd%22:%220003310F0351795%22,%22mchnt_txn_ssn%22:%22ZJWD20180725142222457471%22,%22rem%22:%22%22,%22resp_code%22:%220000%22,%22resp_desc%22:%22??%22,%22signature%22:%22z+UJ+oZhZUhNvwtkRN0RUECIBgjtHOi6FHrGQrLLAGtMgX0rH+ZsZFtO1W1Cx20yezXhxAGAMv2YhhwxrD0H5KsDxee53Xx4rikWJhktQadpOJ1dtqka+rTaQeIy1Rz3WvCr/LjHp7SbEfH8ZZAiEU0AyCd2/xAAQCcIofD5BtY=%22}
                if (url.indexOf(Urls.BASE_URL+"yxbApp/frontWithdrawUrlApp.do?") != -1) {
                    String[] sp = url.split("[?]");
                    for (int i = 0; i < sp.length; i++) {

                        String str = sp[1].toString();
                        String[] sp2 = str.split("[&]");
                        for (int j = 0; j < sp2.length; j++) {
                            String[] sp3 = sp2[j].toString().split("[=]");
                            for (int a = 0; a < sp3.length; a++) {
                                MyLog.e("输出====sp3[1]", "" + sp3[1]);
                                MyLog.e("输出====sp3[0]", "" + sp3[0]);
                                ss.add(sp3[1]);

                            }
                        }
                    }
                    MyLog.e("json===========", ss.toString());
                    try {
                        docode = URLDecoder.decode(ss.get(2), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
//                    Log.e("转码",""+docode+"\""+"}");
                    Gson gson = new Gson();
                    GsonBean data = gson.fromJson(docode + "\"" + "}", GsonBean.class);

                    MyLog.e("readlly", docode + "\"" + "}");
                    MyLog.e("amt", data.getAmt() + "");
                    String receiveCode = ss.get(1);//第一个

                    String retCode = data.getResp_code();//第二个

                    int amt = Integer.valueOf(data.getAmt());
                    int y = amt / 100;
                    if (receiveCode.equals("0000") && retCode.equals("0000")) {
                        Intent inte = new Intent(TiXian_OK.this, Putforward_ok.class);
                        inte.putExtra("jine", String.valueOf(y).toString());
                        startActivity(inte);
                        finish();
                    } else {
                        Intent inte = new Intent(TiXian_OK.this, Putforward_fail.class);
                        startActivity(inte);
                        finish();
                    }


                }
            }
        });
        tixian_web.setWebChromeClient(new MyWebChromeClient());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空所有Cookie
        CookieSyncManager.createInstance(TiXian_OK.this);  //Create a singleton CookieSyncManager within a context
        CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        CookieSyncManager.getInstance().sync(); // forces sync manager to sync now

        tixian_web.setWebChromeClient(null);
        tixian_web.setWebViewClient(null);
        tixian_web.getSettings().setJavaScriptEnabled(false);
        tixian_web.clearCache(true);
    }
}
