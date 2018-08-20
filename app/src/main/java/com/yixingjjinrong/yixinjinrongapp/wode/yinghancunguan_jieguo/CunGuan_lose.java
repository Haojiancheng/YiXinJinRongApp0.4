package com.yixingjjinrong.yixinjinrongapp.wode.yinghancunguan_jieguo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class CunGuan_lose extends AutoLayoutActivity {
    private ImageView kt_lose_fh;
    private Button kt_lose_fanhui,kt_lose_mycontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cun_guan_lose);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();
        getclock();
    }

    private void getclock() {
        kt_lose_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        kt_lose_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        kt_lose_mycontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getid() {
        kt_lose_fh=findViewById(R.id.kt_lose_fh);
        kt_lose_fanhui=findViewById(R.id.kt_lose_fanhui);
        kt_lose_mycontent=findViewById(R.id.kt_lose_mycontent);
    }
}
