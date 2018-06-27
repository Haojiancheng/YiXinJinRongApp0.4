package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class ShiMingRenZhengKO extends AutoLayoutActivity {
    private Button cunguan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiming_renzheng_ko);
        getokid();
        cunguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShiMingRenZhengKO.this, "cunguandddd", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getokid() {
        cunguan=findViewById(R.id.cunguan1);

    }
}
