package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyConten;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;

public class ChuJie_OK extends AutoLayoutActivity {

    private TextView cj_money,cj_time;
    private ImageView cjcg_fh;
    private Button jxcj,cjcg_zh;
    private String mortgageType;
    //    private String borid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.chujiechenggong);

        getcjokID();
        getonclocl();

    }

    private void getonclocl() {
        cjcg_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//返回
                finish();
            }
        });
        jxcj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//继续出借
          Intent intent=new Intent(ChuJie_OK.this,XiangMuXiangQing.class);
                intent.putExtra("mortgageType", mortgageType);
                startActivity(intent);
                finish();
            }
        });
        cjcg_zh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//项目列表
                finish();
            }
        });
    }

    private void getcjokID() {
        Intent it=getIntent();
        String mycj_money = it.getStringExtra("money");
        String mycj_time = it.getStringExtra("time");
        mortgageType = it.getStringExtra("mortgageType");
//        borid = it.getStringExtra("borid");
        cj_money=findViewById(R.id.cj_money);
        cj_time=findViewById(R.id.cj_time);
        cjcg_fh=findViewById(R.id.cjcg_fh);
        jxcj=findViewById(R.id.jxcj);
        cjcg_zh=findViewById(R.id.cjcg_zh);
        cj_time.setText(mycj_time);
        cj_money.setText(mycj_money);
    }
}
