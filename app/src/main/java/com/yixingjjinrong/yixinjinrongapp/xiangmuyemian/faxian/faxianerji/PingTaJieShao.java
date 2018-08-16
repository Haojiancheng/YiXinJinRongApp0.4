package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.zhy.autolayout.AutoLayoutActivity;

public class PingTaJieShao extends AutoLayoutActivity {
    private ImageView ptjs_fh;

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
        ptjs_fh=findViewById(R.id.ptjs_fh);
        ptjs_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
