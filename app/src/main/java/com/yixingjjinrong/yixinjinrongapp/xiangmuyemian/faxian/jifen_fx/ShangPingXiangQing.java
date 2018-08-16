package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.zhy.autolayout.AutoLayoutActivity;

public class ShangPingXiangQing extends AutoLayoutActivity {
    private ImageView dh_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_shang_ping_xiang_qing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        dh_fh=findViewById(R.id.dh_fh);
        dh_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
