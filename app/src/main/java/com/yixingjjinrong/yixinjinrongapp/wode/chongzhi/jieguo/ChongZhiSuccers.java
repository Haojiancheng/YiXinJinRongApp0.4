package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore2;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhq;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.XiangMu;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;

public class ChongZhiSuccers extends AutoLayoutActivity {
    private TextView chongzhiok_jinge;
    private Button bt_chujie,bt_chong_agen;
    private String jine;
    private ImageView cz_sucess_fh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_chong_zhi_succers);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getokid();
        getonclock();
    }

    private void getonclock() {
        bt_chong_agen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ChongZhiSuccers.this, ChongZhq.class);
                startActivity(it);
                finish();
            }
        });
        bt_chujie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ChongZhiSuccers.this, MainActivity.class);
                it.putExtra("id","1");
                startActivity(it);
                finish();
                
            }
        });
        cz_sucess_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getokid() {
        Intent it=getIntent();
        jine = it.getStringExtra("jine");
        MyLog.e("我冲的钱",""+jine );
        chongzhiok_jinge=findViewById(R.id.chongzhiok_jinge);
        bt_chong_agen=findViewById(R.id.bt_chong_agen);
        bt_chujie=findViewById(R.id.bt_chujie);
        chongzhiok_jinge.setText(jine);
        cz_sucess_fh=findViewById(R.id.cz_sucess_fh);
    }
}
