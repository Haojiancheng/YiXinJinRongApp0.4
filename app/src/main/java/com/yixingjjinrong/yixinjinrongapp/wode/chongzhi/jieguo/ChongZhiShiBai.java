package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhq;
import com.zhy.autolayout.AutoLayoutActivity;

public class ChongZhiShiBai extends AutoLayoutActivity {
    private Button bt_onesagen,bt_fanhui;
    private ImageView cz_sb_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_chongzhi_shibai);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        bt_fanhui=findViewById(R.id.bt_fanhui);
        cz_sb_fh=findViewById(R.id.cz_sb_fh);
        bt_onesagen=findViewById(R.id.bt_onesagen);
        bt_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_onesagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itcz = new Intent(ChongZhiShiBai.this, ChongZhq.class);
                itcz.putExtra("keyong2", "3336");
                startActivity(itcz);
            }
        });
        cz_sb_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }
}
