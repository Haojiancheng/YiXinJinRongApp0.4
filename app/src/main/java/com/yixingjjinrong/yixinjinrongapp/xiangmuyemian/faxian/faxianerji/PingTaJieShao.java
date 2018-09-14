package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ZhuCe_PaGa;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.ZhuCeH5;
import com.zhy.autolayout.AutoLayoutActivity;

public class PingTaJieShao extends AutoLayoutActivity {
    private ImageView ptjs_fh;
    private TextView zcxy_h5;
    private TextView gzh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_ping_ta_jie_shao);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        ptjs_fh = findViewById(R.id.ptjs_fh);
        zcxy_h5 = findViewById(R.id.zcxy_h5);
        gzh=findViewById(R.id.gzh);
        ptjs_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zcxy_h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zhuce_page = new Intent(PingTaJieShao.this, ZhuCeH5.class);
                startActivity(zhuce_page);
            }
        });
        gzh.setText("微信订阅号：亿信金融（微信号：yixinjinrong2015）"+"\n"+"微信服务号：亿信宝（微信号：yxb-com）");

    }
}
