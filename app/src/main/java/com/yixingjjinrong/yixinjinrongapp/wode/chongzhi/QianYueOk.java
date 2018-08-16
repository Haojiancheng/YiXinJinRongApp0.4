package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiShiBai;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiSuccers;
import com.yixingjjinrong.yixinjinrongapp.wode.contractResult.Contract_OK;
import com.yixingjjinrong.yixinjinrongapp.wode.contractResult.Contract_loser;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.MyWebChromeClient;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QianYueOk extends AppCompatActivity {
    private WebView qy_web;
    private ArrayList<String> ss = new ArrayList<>();
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String receiveCode;
    private String retCode;
    private String myphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content)); //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_qian_yue_ok);
        qy_web = findViewById(R.id.qy_web);
        Intent it = getIntent();
        String html = it.getStringExtra("HTML");
        Log.e("签约HTMl", html);
        WebSettings webSettings = qy_web.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
        // 设置允许JS弹窗        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 /
        qy_web.loadData(html.toString(), "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。
        qy_web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                qy_web.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("onPageFinished返回url", url);
                //http://newwei.yxb.com/yxbApp/appSignCardNoticeUrls.do?receiveCode=0000&retCode=5360&phone=17338108873

                if (url.contains("appSignCardNoticeUrls.do?receiveCode=")) {
                    String[] sp = url.split("[?]");
                    for (int i = 0; i < sp.length; i++) {
//            Log.e("输出====",""+sp[1]);
                        String str = sp[1].toString();
                        String[] sp2 = str.split("[&]");
                        for (int j = 0; j < sp2.length; j++) {
//                    Log.e("输出====sp2",""+sp2[j]);
                            String[] sp3 = sp2[j].toString().split("[=]");
                            for (int a = 0; a < sp3.length; a++) {
                                Log.e("输出====sp3[1]", "" + sp3[1]);
                                Log.e("输出====sp3[0]", "" + sp3[0]);
                                ss.add(sp3[1]);
                            }
                        }
                    }
                    Log.e("ss==========", ss.toString());
                    //第一个
                    receiveCode = ss.get(1);

                    //第二个
                    retCode = ss.get(2);
                    //电话号
                    myphone = ss.get(5);
                    Log.e("大会不为广大", "phone" + myphone + "receiveCode" + receiveCode + "retCode" + retCode);
                    if (receiveCode.equals("0000") && retCode.equals("0000")) {
                        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
                        try {
                            js_request.put("receiveCode", receiveCode);
                            js_request.put("retCode", retCode);
                            js_request.put("phone", myphone);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RequestParams params = new RequestParams(sp[0]);
                        params.setAsJsonContent(true);
                        params.setBodyContent(js_request.toString());
                        x.http().post(params, new Callback.CommonCallback<String>() {

                            @Override
                            public void onSuccess(String result) {
                                Log.e("签约成功Gson",""+result );
                                Intent inte = new Intent(QianYueOk.this, Contract_OK.class);
                                startActivity(inte);
                                finish();
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {

                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });

                    } else {
                        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
                        try {
                            js_request.put("receiveCode", receiveCode);
                            js_request.put("retCode", retCode);
                            js_request.put("phone", myphone);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RequestParams params = new RequestParams(sp[0]);
                        params.setAsJsonContent(true);
                        params.setBodyContent(js_request.toString());
                        x.http().post(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Log.e("签约失败Gson", result);
                                Intent inte = new Intent(QianYueOk.this, Contract_loser.class);
                                startActivity(inte);
                                finish();
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {

                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                    }

                } else {

                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }
        });
        qy_web.setWebChromeClient(new MyWebChromeClient());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空所有Cookie
        CookieSyncManager.createInstance(QianYueOk.this);  //Create a singleton CookieSyncManager within a context
        CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        CookieSyncManager.getInstance().sync(); // forces sync manager to sync now

        qy_web.setWebChromeClient(null);
        qy_web.setWebViewClient(null);
        qy_web.getSettings().setJavaScriptEnabled(false);
        qy_web.clearCache(true);
    }
}
