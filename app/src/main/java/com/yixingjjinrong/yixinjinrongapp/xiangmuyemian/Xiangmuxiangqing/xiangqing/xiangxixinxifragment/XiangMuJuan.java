package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuXiangQing_Gson;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment.JianXiJuan_Fragment;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment.XianJinJuan_Fragment;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.adapter.SimpleFragmentPagerAdapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.juan_fragment.XiangMuJianXiJuan_Fragment;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.juan_fragment.XiangMuXianJinJuan_Fragment;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

public class XiangMuJuan extends AutoLayoutActivity {
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> list_fragment = new ArrayList<>();//定义要装fragment的列表
    private ArrayList<String> list_title = new ArrayList<>();  //定义要装fragment的列表
    private XiangMuXianJinJuan_Fragment mxianjin;               //现金卷 fragment
    private XiangMuJianXiJuan_Fragment mjiaxi;               //加息卷 fragment
    private XiangMuXiangQing_Gson.ResultBean juan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juan);
        getid();
    }

    private void getid() {
        juan = (XiangMuXiangQing_Gson.ResultBean) getIntent().getSerializableExtra("juan");
        
        
        viewPager=findViewById(R.id.juan_vp);
        tabLayout=findViewById(R.id.juan_tab);
        mxianjin = new XiangMuXianJinJuan_Fragment();
        mjiaxi = new XiangMuJianXiJuan_Fragment();
        
        Bundle bundle=new Bundle();
        bundle.putSerializable("juan",juan);
        mxianjin.setArguments(bundle);
        mjiaxi.setArguments(bundle);
        
        list_fragment.add(mxianjin);
        list_fragment.add(mjiaxi);
        
        

        list_title.add("现金卷");
        list_title.add("加息卷");
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
