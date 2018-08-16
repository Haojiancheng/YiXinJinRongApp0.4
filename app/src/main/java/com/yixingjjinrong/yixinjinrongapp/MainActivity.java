package com.yixingjjinrong.yixinjinrongapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.hjm.bottomtabbar.BottomTabBar;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore2;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyConten;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.Wode;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.XiangMu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.Faxian;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.Shouye;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AutoLayoutActivity {
    private IntentFilter filter;
    private View decorView;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4;
    /**
     * 双击退出功能是否生效（默认ture）
     */
    private boolean DOUBLECLICK_EXIT = true;

    /**
     * 双击退出函数
     */
    private long firstTime = 0;
    private List<Fragment> mfragments=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //获取数据
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getinit();
        
        

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (DOUBLECLICK_EXIT) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (System.currentTimeMillis() - firstTime > 2000) {
                    ToastUtils.showToast(this, "再按一次退出程序");
                    firstTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

    }


    public void switchFragment(int position) {
        //开启事务
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        //遍历集合
        for (int i = 0; i <mfragments.size() ; i++) {
            Fragment fragment = mfragments.get(i);
            if (i==position){
                //显示fragment
                if (fragment.isAdded()){
                    //如果这个fragment已经被事务添加,显示
                    fragmentTransaction.show(fragment);
                }else{
                    //如果这个fragment没有被事务添加过,添加
                    fragmentTransaction.add(R.id.main_frame_layout,fragment);
                }
            }else{
                //隐藏fragment
                if (fragment.isAdded()){
                    //如果这个fragment已经被事务添加,隐藏
                    fragmentTransaction.hide(fragment);
                }
            }
        }
        //提交事务
        fragmentTransaction.commit();
    }
    private void getinit() {
        radioGroup=findViewById(R.id.radio_group);
        rb1=findViewById(R.id.radiobutton1);
        rb2=findViewById(R.id.radiobutton2);
        rb3=findViewById(R.id.radiobutton3);
        rb4=findViewById(R.id.radiobutton4);
        
        mfragments.add(new Shouye());
        mfragments.add(new XiangMu());
        mfragments.add(new Faxian());
        mfragments.add(new Wode());

        Intent it=getIntent();
        String id=it.getStringExtra("id");
        if (id!=null){
            if (id.equals("1")){
                rb1.setChecked(false);
                rb2.setChecked(true);
                rb3.setChecked(false);
                rb4.setChecked(false);
                switchFragment(1);
            }
        }else {
            rb1.setChecked(true);
            rb2.setChecked(false);
            rb3.setChecked(false);
            rb4.setChecked(false);
            switchFragment(0);
        }
        
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.radiobutton1:
                        rb1.setChecked(true);
                        rb2.setChecked(false);
                        rb3.setChecked(false);
                        rb4.setChecked(false);
                        switchFragment(0);
                        break;
                    case R.id.radiobutton2:
                        rb1.setChecked(false);
                        rb2.setChecked(true);
                        rb3.setChecked(false);
                        rb4.setChecked(false);
                        switchFragment(1);
                        break;
                    case R.id.radiobutton3:
                        rb1.setChecked(false);
                        rb2.setChecked(false);
                        rb3.setChecked(true);
                        rb4.setChecked(false);
                        switchFragment(2);
                        break;
                    case R.id.radiobutton4:
                        rb1.setChecked(false);
                        rb2.setChecked(false);
                        rb3.setChecked(false);
                        rb4.setChecked(true);
                        switchFragment(3);
                        break;
                }
            }
        });
        

    }

    @Subscribe
    public void lookMore(LookMore lookMore) {
        rb1.setChecked(false);
        rb2.setChecked(true);
        rb3.setChecked(false);
        rb4.setChecked(false);
        switchFragment(1);
    }


    @Subscribe
    public void mycon(MyConten mycon) {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(true);
        switchFragment(3);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
