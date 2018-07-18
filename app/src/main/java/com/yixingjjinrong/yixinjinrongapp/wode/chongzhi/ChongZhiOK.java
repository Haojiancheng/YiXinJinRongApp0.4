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
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiShiBai;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.MyWebChromeClient;
import com.zhy.autolayout.AutoLayoutActivity;

public class ChongZhiOK extends AutoLayoutActivity {
    private WebView chongzok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_zhi_ok);
        chongzok=findViewById(R.id.chongzok);
        final Intent intent=getIntent();
        String html = intent.getStringExtra("HTML");
        Log.e("充值成功HTM：L", html);
        WebSettings webSettings = chongzok.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
        // 设置允许JS弹窗        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 /
        chongzok.loadData(html, "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。


        chongzok.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("onPageFinished返回的URL",url );
                if(url.indexOf("http://192.168.1.79:8080/yxb_mobile/yxbApp/frontFuonlineUrlApp.do?") != -1)
                {
//http://192.168.1.79:8080/yxb_mobile/yxbApp/frontFuonlineUrlApp.do?receiveCode=0000&retCode=1007&amt=10000
                    String r="http://192.168.1.79:8080/yxb_mobile/yxbApp/frontFuonlineUrlApp.do?receiveCode=0000&retCode=1007&amt=";
                    Log.e("TAG", "包含该字符串"+r.length());
                    String one = url.substring(78, 82);//substring是截取2个位置之间及start-end之间的字符串
                    Log.e("one_截取", ""+one);
                    String two = url.substring(91, 95);//substring是截取2个位置之间及start-end之间的字符串
                    Log.e("two_截取", ""+two);
                    String money = url.substring(100, url.length());
                    int my_money= Integer.parseInt(money)/100;
                    Log.e("我冲的钱",""+my_money);

                    if (one.equals("0000")&&two.equals("0000")){
                        Toast.makeText(ChongZhiOK.this, "成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ChongZhiOK.this, "失败", Toast.LENGTH_SHORT).show();
                        Intent inte=new Intent(ChongZhiOK.this, ChongZhiShiBai.class);
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
        chongzok.setWebChromeClient(new MyWebChromeClient());
    }
}
