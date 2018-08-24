package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.wangdaiClass.Class_xiangqing;
import com.zhy.autolayout.AutoLayoutActivity;

public class WangDaiKeTang extends AutoLayoutActivity {
    private ImageView wdclass_fh;
    private View whatP2P, cade, zhishi, chujieren_web,jiekuanren_web,pingtaiyiwu;
    private View good_web;
    private View no_things;
    private View guiding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_wang_dai_ke_tang);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();
        getonclick();
    }

    private void getonclick() {
        wdclass_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        whatP2P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(WangDaiKeTang.this, Class_xiangqing.class);
                it.putExtra("number", "1");
                startActivity(it);
            }
        });
        cade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(WangDaiKeTang.this, Class_xiangqing.class);
                it.putExtra("number", "2");
                startActivity(it);
            }
        });
        zhishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(WangDaiKeTang.this, Class_xiangqing.class);
                it.putExtra("number", "3");
                startActivity(it);
            }
        });
        chujieren_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
                it.putExtra("number", "4");
                startActivity(it);
            }
        });
        jiekuanren_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
                it.putExtra("number", "5");
                startActivity(it);
            }
        });
        pingtaiyiwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
                it.putExtra("number", "6");
                startActivity(it);
            }
        });
        good_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
                it.putExtra("number", "7");
                startActivity(it);
            }
        });
        no_things.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
                it.putExtra("number", "8");
                startActivity(it);
            }
        });
        guiding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(WangDaiKeTang.this, Class_xiangqing.class);
                it.putExtra("number", "9");
                startActivity(it);
            }
        });

    }

    private void getid() {
        whatP2P = findViewById(R.id.whatP2P);
        cade = findViewById(R.id.cade);
        wdclass_fh = findViewById(R.id.wdclass_fh);
        zhishi = findViewById(R.id.zhishi);
        chujieren_web = findViewById(R.id.chujieren_web);
        jiekuanren_web=findViewById(R.id.jiekuanren_web);
        pingtaiyiwu=findViewById(R.id.pingtaiyiwu);
        good_web = findViewById(R.id.good_web);
        no_things = findViewById(R.id.no_things);
        guiding = findViewById(R.id.guiding);
    }

}
