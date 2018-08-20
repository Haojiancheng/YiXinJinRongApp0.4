package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhiOK;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.QianYueOk;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiShiBai;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiSuccers;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.MyWebChromeClient;
import com.yixingjjinrong.yixinjinrongapp.wode.yinghancunguan_jieguo.CunGuan_lose;
import com.yixingjjinrong.yixinjinrongapp.wode.yinghancunguan_jieguo.CunGuan_succser;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

public class YinHangCunGuan extends AutoLayoutActivity {
    private WebView cg_wb;
    private ArrayList<String> ss = new ArrayList<>();
    private String receiveCode;
    private String retCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_yinhang_cunguan);
        cg_wb=findViewById(R.id.cg_wb);
        Intent it=getIntent();
        String htlm = it.getStringExtra("HTML");
        Log.e("开通存管HTML:",""+htlm);
        WebSettings webSettings = cg_wb.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存

        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 //重点是这个设置
        // 设置允许JS弹窗
         webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        cg_wb.loadData(htlm.toString(), "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。
        cg_wb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("onPageFinished返回的URL", url);
                //返回http://newpay-dev-test.yxb.com/fuyouwap/appWebRegNotifyPaeseUrl.do
                //退出http://newpay-dev-test.yxb.com/fuyouwap/appWebRegNotifyPaeseUrl.do
                //http://newwei.yxb.com/yxb_mobile/fuRegUserUrl.do?receiveCode=0000&retCode=0000
                if (url.contains("fuRegUserUrl.do?receiveCode")) {
//                    finish();
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
                    if (receiveCode.equals("0000") && retCode.equals("0000")) {
                        Intent inte = new Intent(YinHangCunGuan.this, CunGuan_succser.class);
                        startActivity(inte);
                        finish();
                    } else {
                        Intent inte = new Intent(YinHangCunGuan.this, CunGuan_lose.class);
                        startActivity(inte);
                        finish();
                    }

                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }
        });
        cg_wb.setWebChromeClient(new MyWebChromeClient());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空所有Cookie
        CookieSyncManager.createInstance(YinHangCunGuan.this);  //Create a singleton CookieSyncManager within a context
        CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        CookieSyncManager.getInstance().sync(); // forces sync manager to sync now

        cg_wb.setWebChromeClient(null);
        cg_wb.setWebViewClient(null);
        cg_wb.getSettings().setJavaScriptEnabled(false);
        cg_wb.clearCache(true);
    }

}
