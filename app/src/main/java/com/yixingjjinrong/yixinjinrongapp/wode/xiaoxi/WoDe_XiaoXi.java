package com.yixingjjinrong.yixinjinrongapp.wode.xiaoxi;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.wode.xiaoxi.xiaoxi.User_GG_fragment;
import com.yixingjjinrong.yixinjinrongapp.wode.xiaoxi.xiaoxi.User_XX_fragment;

import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.adapter.SimpleFragmentPagerAdapter;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class WoDe_XiaoXi extends AutoLayoutActivity {



    private ViewPager xx_vp;
    private TabLayout xx_tab;
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ArrayList<Fragment> list_fragment = new ArrayList<>();//定义要装fragment的列表
    private ArrayList<String> list_title = new ArrayList<>();  //定义要装fragment的列表
    private User_XX_fragment mxx; //消息fragment
    private User_GG_fragment mgg; //公告fragment
    private ImageView xx_fh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_wode__xiaoxi);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .keyboardEnable(true)
                .init();
        getid();
        xx_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getid() {
        xx_fh=findViewById(R.id.xx_fh);
        xx_vp=findViewById(R.id.xx_vp);
        xx_tab=findViewById(R.id.xx_tab);
        mxx = new User_XX_fragment();
        mgg = new User_GG_fragment();
        list_fragment.add(mxx);
        list_fragment.add(mgg);

        list_title.add("消息");
        list_title.add("公告");
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, list_fragment, list_title);
        xx_vp.setAdapter(pagerAdapter);
        xx_tab.setupWithViewPager(xx_vp);
        xx_tab.setTabMode(TabLayout.MODE_FIXED);
        xx_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
