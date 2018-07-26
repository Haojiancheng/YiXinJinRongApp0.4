package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.adapter.SimpleFragmentPagerAdapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment.FengXiangCuoShi;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment.JiBanXinXi;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment.ShenJiBaoGao;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment.ShouFeiBiaoZhun;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment.YunYinShuJu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment.ZhongDaShiJian;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

public class XinXiPiLu extends AutoLayoutActivity {
    private ArrayList<Fragment> pilulist_fragment = new ArrayList<>();//定义要装fragment的列表
    private ArrayList<String> pululist_title = new ArrayList<>();  //定义要装fragment的列表
    private SimpleFragmentPagerAdapter piluypagerAdapter;
    private ViewPager piluviewPager;
    private TabLayout pilutabLayout;
    private FengXiangCuoShi mfengXiangCuoShi;//风险措施
    private JiBanXinXi mjiBanXinXi;          //基本信息
    private ShenJiBaoGao mshenJiBaoGao;      //审计报告
    private ShouFeiBiaoZhun mshouFeiBiaoZhun;//收费标准
    private YunYinShuJu myunYinShuJu;        //运营数据
    private ZhongDaShiJian mzhongDaShiJian;  //重大事件
    private ImageView xxpl_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_xin_xi_pi_lu);
//        getPiLuId();//获取资源ID
        xxpl_fh=findViewById(R.id.xxpl_fh);
        xxpl_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getinitview();//获取视图
    }

    private void getinitview() {
        mjiBanXinXi=new JiBanXinXi();//基本信息
        myunYinShuJu=new YunYinShuJu();//运营数据
        mshenJiBaoGao=new ShenJiBaoGao();//审计报告
        mshouFeiBiaoZhun=new ShouFeiBiaoZhun();//收费标准
        mzhongDaShiJian=new ZhongDaShiJian();//重大事件
        mfengXiangCuoShi=new FengXiangCuoShi();//风险措施
        
        //加入集合
        pilulist_fragment.add(mjiBanXinXi);//加入集合
        pilulist_fragment.add(myunYinShuJu);
        pilulist_fragment.add(mshenJiBaoGao);
        pilulist_fragment.add(mshouFeiBiaoZhun);
        pilulist_fragment.add(mzhongDaShiJian);
        pilulist_fragment.add(mfengXiangCuoShi);
        
        //加入票标题
        pululist_title.add("基本信息");
        pululist_title.add("运营数据");
        pululist_title.add("审计报告");
        pululist_title.add("收费标准");
        pululist_title.add("重大事件");
        pululist_title.add("风险措施");
        piluypagerAdapter=new SimpleFragmentPagerAdapter(getSupportFragmentManager(),this,pilulist_fragment,pululist_title);
        piluviewPager=findViewById(R.id.xinxipiluvp);
        pilutabLayout=findViewById(R.id.xinxipilutab);
        piluviewPager.setAdapter(piluypagerAdapter);
        pilutabLayout.setupWithViewPager(piluviewPager);
        pilutabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        piluviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

//    private void getPiLuId() {
//       
//    }
}
