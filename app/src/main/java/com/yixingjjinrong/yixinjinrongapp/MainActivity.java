package com.yixingjjinrong.yixinjinrongapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.hjm.bottomtabbar.BottomTabBar;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Faxian;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Wode;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.XiangMu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.Shouye;
import com.zhy.autolayout.AutoLayoutActivity;

public class MainActivity extends AutoLayoutActivity {
    private BottomTabBar mbottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //获取数据
        getinit();
        
    }

    private void getinit() {
        mbottomBar=findViewById(R.id.bottom_tab_bar);
        mbottomBar.init(getSupportFragmentManager(),768,1280)
                .setImgSize(53,33)
                .setFontSize(18)
                .setChangeColor(Color.parseColor("#fe6623"),Color.parseColor("#999999"))
                .setTabPadding(10,7,10)//设置ICON图片与上部分割线的间隔、图片与文字的间隔、文字与底部的间隔
                .addTabItem("首页",R.drawable.shouyedianji,R.drawable.shouye, Shouye.class)
                .addTabItem("项目",R.drawable.xiangmudianji,R.drawable.xiangmu, XiangMu.class)
                .addTabItem("发现",R.drawable.faxiandianji,R.drawable.faxian, Faxian.class)
                .addTabItem("我的",R.drawable.wodedianji,R.drawable.wode, Wode.class)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        if (position == 1)
                            mbottomBar.setSpot(1,false);
                    
                    }
                })
        .setSpot(1,false)
        .setSpot(2,false);
    }
}
