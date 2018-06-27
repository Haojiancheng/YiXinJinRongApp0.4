package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class YinHangCunGuan extends AutoLayoutActivity {
    private WebView cg_wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinhang_cunguan);
        cg_wb=findViewById(R.id.cg_wb);
        Intent it=getIntent();
        String htlm = it.getStringExtra("HTML");
        Log.e("HTML:", ""+htlm);
        cg_wb.loadDataWithBaseURL(null, htlm, "text/html", "utf-8", null);
        cg_wb.getSettings().setJavaScriptEnabled(true);
        cg_wb.setWebChromeClient(new WebChromeClient());
    }

}
