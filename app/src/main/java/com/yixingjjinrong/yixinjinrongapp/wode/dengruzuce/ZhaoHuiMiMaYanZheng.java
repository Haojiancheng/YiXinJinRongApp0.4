package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.yixingjjinrong.yixinjinrongapp.R;

public class ZhaoHuiMiMaYanZheng extends AppCompatActivity {
    private Button queding_zhaohui;//找回密码——确定

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhaohui_mima_yanzheng);
        getzhaohuimima_Id();
        getzhaohuimima_Onclik();
    }

    private void getzhaohuimima_Onclik() {
        
    }

    private void getzhaohuimima_Id() {
        queding_zhaohui=findViewById(R.id.zhaohuimiam_queding);//找回密码——确定
    }
}
