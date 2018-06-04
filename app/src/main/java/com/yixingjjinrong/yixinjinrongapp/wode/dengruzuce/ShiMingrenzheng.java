package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class ShiMingrenzheng extends AutoLayoutActivity {
    private EditText zhen_name,user_idcard;
    private Button renzheng_goin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_mingrenzheng);
        getzhen_id();
        getonrenzheng();

    }

    private void getonrenzheng() {
        renzheng_goin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHttp();
            }
        });
    }

    private void getHttp() {
        String my_name = zhen_name.getText().toString();
        String zhen_id = user_idcard.getText().toString();


    }

    private void getzhen_id() {
        zhen_name=findViewById(R.id.zhen_name);
        user_idcard=findViewById(R.id.user_idcard);
        renzheng_goin=findViewById(R.id.renzheng_goin);

    }
}
