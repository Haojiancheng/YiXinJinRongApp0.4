package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment.FangChanDeYAn_F;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.adapter.SimpleFragmentPagerAdapter;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

public class WoDe_ChuJie extends AutoLayoutActivity {
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> list_fragment = new ArrayList<>();//定义要装fragment的列表
    private ArrayList<String> list_title = new ArrayList<>();  //定义要装fragment的列表
    private FangChanDeYAn_F mfangchandiya;               //出借记录 fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode_chujie);
        getID();
        initView();
    }

    private void getID() {

    }
    private void initView() {

        viewPager = findViewById(R.id.chujie_vp);
        tabLayout = findViewById(R.id.chujie_tab);
        mfangchandiya = new FangChanDeYAn_F();
        list_fragment.add(mfangchandiya);


        list_title.add("房产抵押");


        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, list_fragment, list_title);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
