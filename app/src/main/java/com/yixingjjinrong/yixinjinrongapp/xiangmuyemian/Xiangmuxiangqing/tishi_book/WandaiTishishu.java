package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.zhy.autolayout.AutoLayoutActivity;

public class WandaiTishishu extends AutoLayoutActivity {
    private WebView web;
    private TextView title1;
    private ImageView web_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wandai_tishishu);
        web = findViewById(R.id.webwang);
        title1 = findViewById(R.id.title1);
        web_fanhui = findViewById(R.id.web_fanhui);
//        web.loadUrl("http://192.168.1.201:8080/yxb_mobile/financeapp/agreement.do");//加载url
        Intent it = getIntent();
        String url = it.getStringExtra("url");
        title1.setText(it.getStringExtra("title1"));
        String bid = it.getStringExtra("b_id");
        web.loadUrl("http://192.168.1.201:8080/" + url+"borrowRandomId="+bid);
        web_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
