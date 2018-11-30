package com.yixingjjinrong.yixinjinrongapp.wode.tixian.jieguo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.wode.tixian.TiXian;
import com.zhy.autolayout.AutoLayoutActivity;

public class Putforward_ok extends AutoLayoutActivity {
    private TextView putforward_money;
    private ImageView tx_sucess_fh;
    private Button tx_zhanghu,tx_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putforward_ok);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        putforward_money=findViewById(R.id.putforward_money);
        tx_sucess_fh=findViewById(R.id.tx_sucess_fh);
        tx_zhanghu=findViewById(R.id.tx_zhanghu);
        tx_continue=findViewById(R.id.tx_continue);
        tx_sucess_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent it =getIntent();
        String jine = it.getStringExtra("jine");
        putforward_money.setText(jine);
       tx_zhanghu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
       tx_continue.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent it=new Intent(Putforward_ok.this, TiXian.class);
               startActivity(it);
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
