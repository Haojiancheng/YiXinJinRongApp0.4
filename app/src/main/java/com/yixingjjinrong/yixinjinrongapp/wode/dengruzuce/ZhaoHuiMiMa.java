package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class ZhaoHuiMiMa extends AutoLayoutActivity {
    private Button zhaohuimima_xiayibu;//找回密码下一步

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhaohui_mima);
        getzhaohuimimaId();
        getzhaohuimimaOnCilk();
    }

    private void getzhaohuimimaOnCilk() {
        zhaohuimima_xiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zhaohuimima_it=new Intent(ZhaoHuiMiMa.this,ZhaoHuiMiMaYanZheng.class);
                startActivity(zhaohuimima_it);
            }
        });
    }

    private void getzhaohuimimaId() {
        zhaohuimima_xiayibu=findViewById(R.id.zhaohuimiam_xiayibu);//找回密码下一步
    }
}
