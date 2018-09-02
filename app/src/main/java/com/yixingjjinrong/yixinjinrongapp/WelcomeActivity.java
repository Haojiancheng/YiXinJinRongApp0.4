package com.yixingjjinrong.yixinjinrongapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.HandUtils.GestureVerifyActivity;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

public class WelcomeActivity extends AutoLayoutActivity {

    private String ishand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        ishand = (String) SPUtils.get(WelcomeActivity.this, "ishand", "");
        SharedPreferences sharedPreferences = getSharedPreferences("First",MODE_PRIVATE);
        final boolean isFirst = sharedPreferences.getBoolean("isFirst",true);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFirst) {
                    Intent intent = new Intent(WelcomeActivity.this, Guide_Pager.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("hand",""+ishand);
                    if (ishand.equals("1")) {
                        Intent intent = new Intent(WelcomeActivity.this, GestureVerifyActivity.class);
                        intent.putExtra("shezhi","2");
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        },2000);

    }
}
