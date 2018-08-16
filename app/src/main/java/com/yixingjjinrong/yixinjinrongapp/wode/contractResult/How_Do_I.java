package com.yixingjjinrong.yixinjinrongapp.wode.contractResult;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class How_Do_I extends AutoLayoutActivity {
    private ImageView guanbi_why;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how__do__i);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .keyboardEnable(true)
                .init();
        guanbi_why=findViewById(R.id.guanbi_why);
        guanbi_why.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
