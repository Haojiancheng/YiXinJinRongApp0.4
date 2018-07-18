package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class ChuJie_OK extends AutoLayoutActivity {

    private TextView cj_money,cj_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chujiechenggong);
//        it.putExtra("money",toumoney);
//        it.putExtra("time",toutime);
        getcjokID();

    }

    private void getcjokID() {
        Intent it=getIntent();
        String mycj_money = it.getStringExtra("money");
        String mycj_time = it.getStringExtra("time");
        cj_money=findViewById(R.id.cj_money);
        cj_time=findViewById(R.id.cj_time);
        cj_time.setText(mycj_time);
        cj_money.setText(mycj_money);
    }
}
