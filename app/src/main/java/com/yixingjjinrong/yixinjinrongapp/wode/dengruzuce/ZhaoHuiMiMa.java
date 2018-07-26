package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.zhy.autolayout.AutoLayoutActivity;

public class ZhaoHuiMiMa extends AutoLayoutActivity {
    private Button zhaohuimima_xiayibu;//找回密码下一步
    private EditText myphonet;
    private ImageView zh_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_zhaohui_mima);
        getzhaohuimimaId();
        getzhaohuimimaOnCilk();
    }

    private void getzhaohuimimaOnCilk() {
        zhaohuimima_xiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zhaohuimima_it=new Intent(ZhaoHuiMiMa.this,ZhaoHuiMiMaYanZheng.class);
                zhaohuimima_it.putExtra("phone",myphonet.getText().toString() );
                startActivity(zhaohuimima_it);
                finish();
            }
        });
        zh_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getzhaohuimimaId() {
        zhaohuimima_xiayibu=findViewById(R.id.zhaohuimiam_xiayibu);//找回密码下一步
        myphonet=findViewById(R.id.myphonet);
        zh_fh=findViewById(R.id.zh_fh);
    }
}
