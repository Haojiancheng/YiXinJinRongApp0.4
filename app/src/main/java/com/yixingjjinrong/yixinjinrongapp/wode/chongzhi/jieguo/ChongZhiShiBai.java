package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhq;
import com.zhy.autolayout.AutoLayoutActivity;

public class ChongZhiShiBai extends AutoLayoutActivity {
    private Button bt_onesagen,bt_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi_shibai);
        bt_fanhui=findViewById(R.id.bt_fanhui);
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
    }
}
