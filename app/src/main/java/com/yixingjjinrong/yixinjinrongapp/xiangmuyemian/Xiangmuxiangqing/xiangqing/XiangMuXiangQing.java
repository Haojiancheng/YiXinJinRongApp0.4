package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.adapter.SimpleFragmentPagerAdapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.ChuJieJiLu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.HuiKuanJiHua;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.JieKuanZiLiao;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuJingDu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuXinXi;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

public class XiangMuXiangQing extends AutoLayoutActivity {
    private ArrayList<Fragment> list_fragment = new ArrayList<>();//定义要装fragment的列表
    private ArrayList<String> list_title = new ArrayList<>();  //定义要装fragment的列表
    private ChuJieJiLu mChuJieJiLu;               //出借记录 fragment
    private HuiKuanJiHua mHuiKuanJiHua;           //回款计划 fragment
    private JieKuanZiLiao mJieKuanZiLiao;         //借款资料 fragment
    private XiangMuJingDu mXiangMuJingDu;         //项目进度 fragment
    private XiangMuXinXi mXiangMuXinXi;           //项目信息 fragment
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button jianhao, jiahao;//加减用户的输入金额
    private EditText jinge;//用户输入的金额

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.xiaomuxiangqing);
        
        getID();//获取资源ID
        initView();
        getjinge();//获取输入金额
    }

    private void getjinge() {
        jianhao.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(jinge.getText().toString()) <= 100) {//金额不能小于100
                    Toast.makeText(XiangMuXiangQing.this, "起投金额不能小于100元", Toast.LENGTH_SHORT).show();
                } else {
                    jinge.setText(Integer.valueOf(jinge.getText().toString()) - 100 + "");//减后的金额
                }
            }
        });

        jiahao.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                jinge.setText(Integer.valueOf(jinge.getText().toString()) + 100 + "");//加后的金额
            }
        });

    }

    private void getID() {
        jianhao = findViewById(R.id.bt_jianhao);
        jinge = findViewById(R.id.et_jinge);
        jiahao = findViewById(R.id.bt_jiahao);
    }

    private void initView() {
        mChuJieJiLu = new ChuJieJiLu();
        mHuiKuanJiHua = new HuiKuanJiHua();
        mJieKuanZiLiao = new JieKuanZiLiao();
        mXiangMuJingDu = new XiangMuJingDu();
        mXiangMuXinXi = new XiangMuXinXi();
        list_fragment.add(mXiangMuXinXi);
        list_fragment.add(mHuiKuanJiHua);
        list_fragment.add(mXiangMuJingDu);
        list_fragment.add(mJieKuanZiLiao);
        list_fragment.add(mChuJieJiLu);

        list_title.add("项目信息");
        list_title.add("回款计划");
        list_title.add("项目进度");
        list_title.add("借款资料");
        list_title.add("出借记录");

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, list_fragment, list_title);
        viewPager = findViewById(R.id.vp);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = findViewById(R.id.tab);
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
