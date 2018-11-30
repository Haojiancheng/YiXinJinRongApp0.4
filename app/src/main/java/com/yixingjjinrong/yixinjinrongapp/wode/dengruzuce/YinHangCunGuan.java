package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
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
    private ImageView kaitongzg_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinhang_cunguan);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        kaitongzg_fh=findViewById(R.id.kaitongzg_fh);
        cg_wb=findViewById(R.id.cg_wb);
        kaitongzg_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent it=getIntent();
        String htlm = it.getStringExtra("HTML");
        MyLog.e("开通存管HTML:",""+htlm);
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
                MyLog.e("onPageFinished返回的URL", url);
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
                                MyLog.e("输出====sp3[1]", "" + sp3[1]);
                                MyLog.e("输出====sp3[0]", "" + sp3[0]);
                                ss.add(sp3[1]);
                            }
                        }
                    }
                    MyLog.e("ss==========", ss.toString());
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
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }

}
