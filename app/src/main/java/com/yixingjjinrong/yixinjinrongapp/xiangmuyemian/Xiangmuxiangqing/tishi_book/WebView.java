package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.zhy.autolayout.AutoLayoutActivity;


public class WebView extends AutoLayoutActivity {
    private android.webkit.WebView wb;
    private TextView title2;
    private ImageView web_fanhui_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {   //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content)); //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_web_view);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        wb = findViewById(R.id.webwang1);
        title2=findViewById(R.id.title2);
        web_fanhui_fh=findViewById(R.id.web_fanhui_fh);
        web_fanhui_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent it = getIntent();
        String url = it.getStringExtra("url");
        String title1 = it.getStringExtra("title1");
        title2.setText(title1);
        WebSettings webSettings = wb.getSettings();

        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        wb.loadUrl(Urls.BASE_URL+url);
    }
    @JavascriptInterface
    public void backq() {
        Toast.makeText(this, "调用back()",Toast.LENGTH_LONG ).show();
        finish();
    }
}
