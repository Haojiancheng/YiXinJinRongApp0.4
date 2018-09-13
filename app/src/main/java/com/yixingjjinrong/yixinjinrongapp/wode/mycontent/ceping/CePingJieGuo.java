package com.yixingjjinrong.yixinjinrongapp.wode.mycontent.ceping;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.MyView.KeywordsUtil;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.chongxinpingce.ChongXinPingCe;
import com.zhy.autolayout.AutoLayoutActivity;

public class CePingJieGuo extends AutoLayoutActivity {
    private TextView cp_jieguo,cp_message;
    private ImageView pcjg_fanhui;
    private Button bt_cp_age;
    private String type;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ce_ping_jie_guo);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        getid();
        getjieguo();
        getonclock();
    }

    private void getonclock() {
        pcjg_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_cp_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CePingJieGuo.this, ChongXinPingCe.class);
                startActivity(it);
            }
        });
    }

    private void getjieguo() {
        switch (type) {
            case "1":
                cp_jieguo.setText("保守型");
                String tx="您的风险承受能力较低，对回报要求不高，但追求本金绝对安全。适合您的产品为：AAA、AA、A安全等级的产品。";
                SpannableString spannableString = KeywordsUtil.matcherSearchTitle(Color.parseColor("#F36934"), tx, "AAA、AA、A");
                cp_message.setText(spannableString);//信息
                break;
            case "2":
                cp_jieguo.setText("稳健型");
                String txt="您的风险承受度适中，偏向于资产均衡配置，能够承受一定的风险，同时，对资产回报要求高于稳健型者。适合您的产品为：AAA、AA、A、BBB、BB、B安全等级的产品。";
                SpannableString spannableString1 = KeywordsUtil.matcherSearchTitle(Color.parseColor("#F36934"), txt, "AAA、AA、A、BBB、BB、B");
                cp_message.setText(spannableString1);//信息
                break;
            case "3":
                cp_jieguo.setText("积极型");
                String text="您偏向于激进的资产配置，对风险有较高的承受能力，回报预期相对较高。您可以出借亿信宝平台所有产品。";
                break;
            default:
                break;

        }
    }

    private void getid() {
        cp_jieguo=findViewById(R.id.cp_jieguo);
        cp_message=findViewById(R.id.cp_message);
        pcjg_fanhui=findViewById(R.id.pcjg_fanhui);
        bt_cp_age=findViewById(R.id.bt_cp_age);
        Intent it=getIntent();
        type = it.getStringExtra("type");
        user_id = (int) SPUtils.get(this, "userId", 0);
    }
}
