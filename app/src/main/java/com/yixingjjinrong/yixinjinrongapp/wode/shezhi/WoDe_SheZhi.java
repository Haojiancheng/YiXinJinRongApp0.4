package com.yixingjjinrong.yixinjinrongapp.wode.shezhi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtil;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.zhy.autolayout.AutoLayoutActivity;

public class WoDe_SheZhi extends AutoLayoutActivity {
    private Button user_tuichu;//退出按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode_shezhi);
        getSheZhi_Id();
        getonclick_shezhi();
    }

    private void getonclick_shezhi() {
        user_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shezhi_tuichu=new Intent(WoDe_SheZhi.this, WoDe_DengRu.class);
                startActivity(shezhi_tuichu);
                SPUtil.put(WoDe_SheZhi.this,"isLogin",false);
                finish();
            }
        });
    }

    private void getSheZhi_Id() {
        user_tuichu=findViewById(R.id.user_tuichu);//退出
    }
}
