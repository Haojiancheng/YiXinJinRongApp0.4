package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.zhy.autolayout.AutoLayoutActivity;

public class WandaiTishishu extends AutoLayoutActivity {
    private WebView web;
    private TextView title1;
    private ImageView web_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));//需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_wandai_tishishu);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        web = findViewById(R.id.webwang);
        title1 = findViewById(R.id.title1);
        web_fanhui = findViewById(R.id.web_fanhui);
//        web.loadUrl("http://192.168.1.201:8080/yxb_mobile/financeapp/agreement.do");//加载url
        Intent it = getIntent();
        String url = it.getStringExtra("url");
        title1.setText(it.getStringExtra("title1"));
        String bid = it.getStringExtra("b_id");
        WebSettings webSettings = web.getSettings();

        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        web.loadUrl(Urls.BASE_URL + url+"borrowRandomId="+bid);
        MyLog.e("ddddfd_-ff", ""+Urls.BASE_URL + url+"borrowRandomId="+bid);
        web_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @JavascriptInterface
    public void backq() {
        Toast.makeText(this, "调用back()",Toast.LENGTH_LONG ).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }
}
