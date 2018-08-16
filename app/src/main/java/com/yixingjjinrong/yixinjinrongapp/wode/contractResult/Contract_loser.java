package com.yixingjjinrong.yixinjinrongapp.wode.contractResult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.KUaiJieZhiFu;
import com.zhy.autolayout.AutoLayoutActivity;

public class Contract_loser extends AutoLayoutActivity {
    private ImageView contract_sb_fh;
    private TextView way_loser;
    private Button bt_oncemore, bt_myaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_loser);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();
        getonclock();

    }

    private void getonclock() {
        contract_sb_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//返回
                finish();
            }
        });
        way_loser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//怎么办
                Intent it=new Intent(Contract_loser.this,How_Do_I.class);
                startActivity(it);
                finish();
            }
        });
        bt_oncemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//继续
                Intent intent = new Intent(Contract_loser.this, KUaiJieZhiFu.class);
                startActivity(intent);
                finish();
            }
        });
        bt_myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//我的账户
                finish();
            }
        });
    }

    private void getid() {
        contract_sb_fh = findViewById(R.id.contract_sb_fh);//返回
        way_loser = findViewById(R.id.way_loser);//怎么办
        bt_oncemore = findViewById(R.id.bt_oncemore);//继续
        bt_myaccount = findViewById(R.id.bt_myaccount);//我的账户
    }
}
