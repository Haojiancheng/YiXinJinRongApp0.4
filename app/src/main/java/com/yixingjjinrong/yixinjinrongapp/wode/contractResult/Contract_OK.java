package com.yixingjjinrong.yixinjinrongapp.wode.contractResult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhq;
import com.zhy.autolayout.AutoLayoutActivity;

public class Contract_OK extends AutoLayoutActivity {
    private Button bt_recharge,bt_accountcenter;
    private ImageView qy_sucess_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract__ok);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();
        getonclonck();

    }

    private void getid() {
        bt_recharge=findViewById(R.id.bt_recharge);//继续充值
        bt_accountcenter=findViewById(R.id.bt_accountcenter);//中户中心
        qy_sucess_fh=findViewById(R.id.qy_sucess_fh);//返回
    }

    private void getonclonck() {
        bt_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//继续充值
                Intent it=new Intent(Contract_OK.this, ChongZhq.class);
                startActivity(it);
                finish();
            }
        });
        bt_accountcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//中户中心
                finish();
            }
        });
        qy_sucess_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//返回
                finish();
            }
        });

    }
}
