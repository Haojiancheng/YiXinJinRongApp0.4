package com.yixingjjinrong.yixinjinrongapp.wode.yaoqing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class MyYaoQing extends AutoLayoutActivity {

    private String mancount;
    private String countmoney;
    private TextView mycountmoney,mymancount,yaoqingxiangqing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_yao_qing);
        getinterview();
        getonclonk();
    }

    private void getonclonk() {
        yaoqingxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MyYaoQing.this, YaoQingXiangQing.class);
                startActivity(it);
            }
        });
    }

    private void getinterview() {
        Intent it=getIntent();
        mancount = it.getStringExtra("mancount");
        countmoney = it.getStringExtra("countmoney");
        mycountmoney=findViewById(R.id.mycount_money);
        mymancount=findViewById(R.id.mymancount);
        yaoqingxiangqing=findViewById(R.id.yaoqingxiangqing);
        mycountmoney.setText(countmoney);
        mymancount.setText(mancount);
    }
}
