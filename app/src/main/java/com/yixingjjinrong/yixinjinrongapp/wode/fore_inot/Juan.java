package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment.JianXiJuan_Fragment;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment.XianJinJuan_Fragment;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.adapter.SimpleFragmentPagerAdapter;

import java.util.ArrayList;

public class Juan extends AppCompatActivity {
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> list_fragment = new ArrayList<>();//定义要装fragment的列表
    private ArrayList<String> list_title = new ArrayList<>();  //定义要装fragment的列表
    private XianJinJuan_Fragment mxianjin;               //现金卷 fragment
    private JianXiJuan_Fragment mjiaxi;               //加息卷 fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juan);
        getid();
    }

    private void getid() {
        viewPager=findViewById(R.id.juan_vp);
        tabLayout=findViewById(R.id.juan_tab);
        mxianjin = new XianJinJuan_Fragment();
        mjiaxi = new JianXiJuan_Fragment();
        list_fragment.add(mjiaxi);
        list_fragment.add(mxianjin);


        list_title.add("加息卷");
        list_title.add("现金卷");
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
