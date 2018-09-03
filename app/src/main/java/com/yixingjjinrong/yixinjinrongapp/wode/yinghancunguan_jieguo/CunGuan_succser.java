package com.yixingjjinrong.yixinjinrongapp.wode.yinghancunguan_jieguo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiSuccers;
import com.zhy.autolayout.AutoLayoutActivity;

public class CunGuan_succser extends AutoLayoutActivity {
    private ImageView kaitongok_fh;
    private Button kt_ok_myconten,kt_ok_reback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cun_guan_succser);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();
        getonclock();

    }

    private void getonclock() {
        kaitongok_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        kt_ok_myconten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CunGuan_succser.this, MainActivity.class);
                it.putExtra("id","1");
                startActivity(it);
                finish();
            }
        });
        kt_ok_reback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CunGuan_succser.this, MainActivity.class);
                it.putExtra("id","2");
                startActivity(it);
                finish();
            }
        });
    }

    private void getid() {
        kaitongok_fh=findViewById(R.id.kaitongok_fh);
        kt_ok_myconten=findViewById(R.id.kt_ok_myconten);
        kt_ok_reback=findViewById(R.id.kt_ok_reback);
    }
}
