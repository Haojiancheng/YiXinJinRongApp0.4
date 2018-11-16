package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;


public class XinXiPiLu extends AutoLayoutActivity {
//    private ArrayList<Fragment> pilulist_fragment = new ArrayList<>();//定义要装fragment的列表
//    private ArrayList<String> pululist_title = new ArrayList<>();  //定义要装fragment的列表
//    private SimpleFragmentPagerAdapter piluypagerAdapter;
//    private ViewPager piluviewPager;
//    private TabLayout pilutabLayout;
//    private FengXiangCuoShi mfengXiangCuoShi;//风险措施
//    private JiBanXinXi mjiBanXinXi;          //基本信息
//    private ShenJiBaoGao mshenJiBaoGao;      //审计报告
//    private ShouFeiBiaoZhun mshouFeiBiaoZhun;//收费标准
//    private YunYinShuJu myunYinShuJu;        //运营数据
//    private ZhongDaShiJian mzhongDaShiJian;  //重大事件
    private ImageView xxpl_fh;
    private WebView xxpl_web;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));//需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_xin_xi_pi_lu);
//        getPiLuId();//获取资源ID
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        getid();
        getonclick();

        //***********************
        WebSettings webSettings = xxpl_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        xxpl_web.addJavascriptInterface(this, "android");
        // 设置允许JS弹窗

        xxpl_web.setWebChromeClient(new WebChromeClient());
        xxpl_web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        xxpl_web.loadUrl("http://newwei.yxb.com/disclosure.do?nav=1-1");

//        getinitview();//获取视图
    }

    private void getonclick() {
        xxpl_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getid() {
        xxpl_fh=findViewById(R.id.xxpl_fh);
        xxpl_web=findViewById(R.id.xxpl_web);
    }

//    private void getinitview() {
//        mjiBanXinXi=new JiBanXinXi();//基本信息
//        myunYinShuJu=new YunYinShuJu();//运营数据
//        mshenJiBaoGao=new ShenJiBaoGao();//审计报告
//        mshouFeiBiaoZhun=new ShouFeiBiaoZhun();//收费标准
//        mzhongDaShiJian=new ZhongDaShiJian();//重大事件
//        mfengXiangCuoShi=new FengXiangCuoShi();//风险措施
//
//        //加入集合
//        pilulist_fragment.add(mjiBanXinXi);//加入集合
//        pilulist_fragment.add(myunYinShuJu);
//        pilulist_fragment.add(mshenJiBaoGao);
//        pilulist_fragment.add(mshouFeiBiaoZhun);
//        pilulist_fragment.add(mzhongDaShiJian);
//        pilulist_fragment.add(mfengXiangCuoShi);
//
//        //加入票标题
//        pululist_title.add("基本信息");
//        pululist_title.add("运营数据");
//        pululist_title.add("审计报告");
//        pululist_title.add("收费标准");
//        pululist_title.add("重大事件");
//        pululist_title.add("风险措施");
//        piluypagerAdapter=new SimpleFragmentPagerAdapter(getSupportFragmentManager(),this,pilulist_fragment,pululist_title);
//        piluviewPager=findViewById(R.id.xinxipiluvp);
//        pilutabLayout=findViewById(R.id.xinxipilutab);
//        piluviewPager.setAdapter(piluypagerAdapter);
//        pilutabLayout.setupWithViewPager(piluviewPager);
//        pilutabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        piluviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//    }

//    private void getPiLuId() {
//       
//    }
}
