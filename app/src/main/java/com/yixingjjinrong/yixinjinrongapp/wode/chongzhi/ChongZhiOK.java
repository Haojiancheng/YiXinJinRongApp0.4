package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiShiBai;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiSuccers;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.MyWebChromeClient;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

public class ChongZhiOK extends AutoLayoutActivity {
    private WebView chongzok;
    private ArrayList<String> ss = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_chong_zhi_ok);
        chongzok = findViewById(R.id.chongzok);
        final Intent intent = getIntent();
        String html = intent.getStringExtra("HTML");
        Log.e("充值成功HTM：L", html);
        WebSettings webSettings = chongzok.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
        // 设置允许JS弹窗        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 /
        chongzok.loadData(html, "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。
        chongzok.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("onPageFinished返回的URL", url);
                //http://192.168.1.79:8080/yxb_mobile/yxbApp/frontFuonlineUrlApp.do?receiveCode=0000&retCode=0000&amt=10000receiveCode=0000&retCode=0000&amt=10000
                if (url.indexOf("yxbApp/frontFuonlineUrlApp.do?") != -1) {
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
                    String receiveCode = ss.get(1);//第一个

                    String retCode = ss.get(2);//第二个
                    String mymoney = ss.get(5);//充的钱
                    int money = Integer.valueOf(mymoney);
                    int y = money / 100;

                    if (receiveCode.equals("0000") && retCode.equals("0000")) {
                        Toast.makeText(ChongZhiOK.this, "成功", Toast.LENGTH_SHORT).show();
                        Intent inte = new Intent(ChongZhiOK.this, ChongZhiSuccers.class);
                        inte.putExtra("jine", String.valueOf(y).toString());
                        startActivity(inte);
                        finish();
                    } else {
                        Toast.makeText(ChongZhiOK.this, "失败", Toast.LENGTH_SHORT).show();
                        Intent inte = new Intent(ChongZhiOK.this, ChongZhiShiBai.class);
                        startActivity(inte);
                        finish();
                    }
                }
//                } else {
//                    Toast.makeText(ChongZhiOK.this, "失败", Toast.LENGTH_SHORT).show();
//                    Intent inte = new Intent(ChongZhiOK.this, ChongZhiShiBai.class);
//                    startActivity(inte);
//                    finish();
//
//                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }
        });
        chongzok.setWebChromeClient(new MyWebChromeClient());
    }
}
