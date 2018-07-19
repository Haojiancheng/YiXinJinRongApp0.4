package com.yixingjjinrong.yixinjinrongapp.wode.tixian;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.MyWebChromeClient;
import com.zhy.autolayout.AutoLayoutActivity;

public class TiXian_OK extends AutoLayoutActivity {
    private WebView tixian_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_xian__ok);
        tixian_web=findViewById(R.id.tixian_web);
        final Intent intent=getIntent();
        String html = intent.getStringExtra("HTML");
        Log.e("提现成功HTM：L", html);
        WebSettings webSettings = tixian_web.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 /
        tixian_web.loadData(html, "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。


        tixian_web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("onPageFinished返回的URL",url );
                if(url.indexOf(" http://localhost:8080/yxb_mobile/yxbApp/frontWithdrawUrlApp.do?")!=-1) {
//http://localhost:8080/yxb_mobile/yxbApp/frontWithdrawUrlApp.do?receiveCode=0000&receiveJson={%22amt%22:%2220000%22,%22login_id%22:%2218820170910%22,%22mchnt_cd%22:%220003310F0351795%22,%22mchnt_txn_ssn%22:%22ZJWD20180718101853361451%22,%22rem%22:%22%22,%22resp_code%22:%220000%22,%22resp_desc%22:%22??%22,%22signature%22:%22OVgFyrAL/pga0sdcEYmJI9mBD/stx9t5+6+TFschRi1vrlRPRlbWjsvJawpJMnr7CBrcHyMilqWaBmyrjlI9gBRrQE264wSdSTDyNM71d3XyMoY50t75N5ZhwnU/3xuH5uF/VQYMnVQBtZRHgb6TCyGKK+QFMZvfmSs7qELvCaQ=%22}
                    String r=" http://localhost:8080/yxb_mobile/yxbApp/frontWithdrawUrlApp.do?receiveCode=";
                    Log.e("TAG", "包含该字符串"+r.length());
                    String one = url.substring(78, 82);//substring是截取2个位置之间及start-end之间的字符串
                    Log.e("one_截取", ""+one);
                    String two = url.substring(91, 95);//substring是截取2个位置之间及start-end之间的字符串
                    Log.e("two_截取", ""+two);
                    String money = url.substring(100, url.length());
                    int my_money= Integer.parseInt(money)/100;
                    Log.e("我冲的钱",""+my_money);
//
//                    if (one.equals("0000")&&two.equals("0000")){
//                        Toast.makeText(TiXian_OK.this, "成功", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(TiXian_OK.this, "失败", Toast.LENGTH_SHORT).show();
//                        Intent inte=new Intent(TiXian_OK.this, ChongZhiShiBai.class);
//                        startActivity(inte);
//                        finish();
//                    }

                }else {
                    Log.e("TAG", "bu包含该字符串");
                }

            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }
        });
        tixian_web.setWebChromeClient(new MyWebChromeClient());
    }
}
