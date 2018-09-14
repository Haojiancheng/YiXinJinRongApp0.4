package com.yixingjjinrong.yixinjinrongapp.wode.yaoqing;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingRenZhengKO;
import com.zhy.autolayout.AutoLayoutActivity;

import gdut.bsx.share2.Share2;
import gdut.bsx.share2.ShareContentType;

public class MyYaoQing extends AutoLayoutActivity {

    private String mancount;
    private String countmoney;
    private TextView mycountmoney, mymancount, yaoqingxiangqing;
    private ImageView yq_fh, guize;
    private Button yuanqing_haoyou;
    private Uri shareFileUrl = null;
    private String inviterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_my_yao_qing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        getinterview();
        getonclonk();
    }

    private void getonclonk() {
        yq_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        yaoqingxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MyYaoQing.this, YaoQingXiangQing.class);
                startActivity(it);
            }
        });
        guize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MyYaoQing.this, HuoDong_GuiZe.class);
                startActivity(it);

            }
        });
        yuanqing_haoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Share2.Builder(MyYaoQing.this)
                        .setContentType(ShareContentType.TEXT)
                        .setTextContent(Urls.BASE_URL+"inviteFriendReg.do?inviteCode="+inviterId)
                        .setTitle("Share Text")
                        .setShareFileUri(getShareFileUri())
                        // .forcedUseSystemChooser(false)
                        .build()
                        .shareBySystem();
            }
        });

    }

    private Uri getShareFileUri() {
        if (shareFileUrl == null) {

        }
        return shareFileUrl;
    }

    private void getinterview() {
        Intent it = getIntent();
        mancount = it.getStringExtra("mancount");
        countmoney = it.getStringExtra("countmoney");
        mycountmoney = findViewById(R.id.mycount_money);
        mymancount = findViewById(R.id.mymancount);
        yaoqingxiangqing = findViewById(R.id.yaoqingxiangqing);
        mycountmoney.setText(countmoney);
        mymancount.setText(mancount);
        yq_fh = findViewById(R.id.yq_fh);
        guize = findViewById(R.id.guize);
//        SPUtils.put(ShiMingRenZhengKO.this, "inviterId", d_data.getResult().getInviterId());
        inviterId = (String) SPUtils.get(MyYaoQing.this, "inviterId", "");
        yuanqing_haoyou=findViewById(R.id.yuanqing_haoyou);
    }
}
