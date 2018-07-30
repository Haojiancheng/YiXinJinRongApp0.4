package com.yixingjjinrong.yixinjinrongapp.wode.tixian.jieguo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.wode.tixian.TiXian;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

public class Putforward_fail extends AutoLayoutActivity {
    private ImageView tx_sb_fh;
    private Button bt_account,continue_to_do_it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putforward_fail);
        tx_sb_fh=findViewById(R.id.tx_sb_fh);
        bt_account=findViewById(R.id.bt_account);
        continue_to_do_it=findViewById(R.id.continue_to_do_it);
        tx_sb_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        continue_to_do_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Putforward_fail.this, TiXian.class);
                startActivity(it);
                finish();
            }
        });

    }
}
