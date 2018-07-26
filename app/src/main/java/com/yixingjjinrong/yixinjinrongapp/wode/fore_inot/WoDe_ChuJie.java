package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment.CarDeYAn_F;
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
    private FangChanDeYAn_F mfangchandiya;               //房产出借记录 fragment
    private CarDeYAn_F mcar;                             //车辆记录 fragment
    private ImageView wo_chujie_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_wode_chujie);
        getID();
        initView();
        wo_chujie_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getID() {
        wo_chujie_fh=findViewById(R.id.wo_chujie_fh);
    }
    private void initView() {

        viewPager = findViewById(R.id.chujie_vp);
        tabLayout = findViewById(R.id.chujie_tab);
        mfangchandiya = new FangChanDeYAn_F();
        mcar=new CarDeYAn_F();
        list_fragment.add(mfangchandiya);
         list_fragment.add(mcar);

        list_title.add("房产抵押");
        list_title.add("车辆抵押");

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
