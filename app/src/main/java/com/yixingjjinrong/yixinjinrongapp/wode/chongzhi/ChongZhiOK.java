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
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiShiBai;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiSuccers;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.MyWebChromeClient;
import com.zhy.autolayout.AutoLayoutActivity;

public class ChongZhiOK extends AutoLayoutActivity {
    private WebView chongzok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
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
                if(url.indexOf("http://newwei.yxb.com/yxbApp/frontFuonlineUrlApp.do?") != -1)
                {
                    //http://newwei.yxb.com/yxbApp/frontFuonlineUrlApp.do?receiveCode=0000&retCode=0000&amt=12300
                    String r="http://newwei.yxb.com/yxbApp/frontFuonlineUrlApp.do?receiveCode=0000&retCode=0000&amt=";
                    Log.e("TAG", "包含该字符串"+r.length());
                    String one = url.substring(64, 68);//substring是截取2个位置之间及start-end之间的字符串
                    Log.e("one_截取", ""+one);
                    String two = url.substring(77, 81);//substring是截取2个位置之间及start-end之间的字符串
                    Log.e("two_截取", ""+two);
                    String money = url.substring(86, url.length());
                    int my_money= Integer.parseInt(money)/100;
                    Log.e("我冲的钱",""+my_money);

                    if (one.equals("0000")&&two.equals("0000")){
                        Toast.makeText(ChongZhiOK.this, "成功", Toast.LENGTH_SHORT).show();
                        Intent inte=new Intent(ChongZhiOK.this, ChongZhiSuccers.class);
                        inte.putExtra("jine",String.valueOf(my_money).toString() );
                        startActivity(inte);
                        finish();
                    }else {
                        Toast.makeText(ChongZhiOK.this, "失败", Toast.LENGTH_SHORT).show();
                        Intent inte=new Intent(ChongZhiOK.this, ChongZhiShiBai.class);
                        startActivity(inte);
                        finish();
                    }

                }else {
                    Toast.makeText(ChongZhiOK.this, "失败", Toast.LENGTH_SHORT).show();
                    Intent inte=new Intent(ChongZhiOK.this, ChongZhiShiBai.class);
                    startActivity(inte);
                    finish();

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
