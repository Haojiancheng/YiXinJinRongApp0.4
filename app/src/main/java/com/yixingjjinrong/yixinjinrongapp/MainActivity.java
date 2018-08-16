package com.yixingjjinrong.yixinjinrongapp;

import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.hjm.bottomtabbar.BottomTabBar;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyConten;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.Wode;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.XiangMu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.Faxian;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.Shouye;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AutoLayoutActivity {
    private BottomTabBar mbottomBar;
    private IntentFilter filter;
    private View decorView;
    /**
     * 双击退出功能是否生效（默认ture）
     */
    private boolean DOUBLECLICK_EXIT = true;

    /**
     * 双击退出函数
     */
    private long firstTime = 0;


    //Fragment的跳转
//   private FragmentManager fManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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


    private void getinit() {
        EventBus.getDefault().register(this);
        mbottomBar = findViewById(R.id.bottom_tab_bar);
        mbottomBar.init(getSupportFragmentManager(), 768, 1280)
                .setImgSize(53, 33)
                .setFontSize(17)
                .setChangeColor(Color.parseColor("#fe6623"), Color.parseColor("#999999"))
                .setTabPadding(10, 7, 10)//设置ICON图片与上部分割线的间隔、图片与文字的间隔、文字与底部的间隔
                .addTabItem("首页", R.drawable.shouyedianji, R.drawable.shouye, Shouye.class)
                .addTabItem("项目", R.drawable.xiangmudianji, R.drawable.xiangmu1, XiangMu.class)
                .addTabItem("发现", R.drawable.faxiandianji, R.drawable.faxian, Faxian.class)
                .addTabItem("我的", R.drawable.wodedianji, R.drawable.wode, Wode.class)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        if (position == 1)
                            mbottomBar.setSpot(1, false);

                    }
                })
                .setSpot(1, false)
                .setSpot(2, false);


    }

    //    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        return false;
//    }
    @Subscribe
    public void lookMore(LookMore lookMore) {
        mbottomBar.setCurrentTab(1);
    }

    @Subscribe
    public void mycon(MyConten mycon) {
        mbottomBar.setCurrentTab(3);
    }

    //Fragment的跳转
//    private class mainReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals("TUIGUANG")) {
//                //开启事务
//                FragmentTransaction transaction = fManager.beginTransaction();
//                //替换fragment
//                transaction.replace(R.id.shouye_frament, fragList.get(1));
//                transaction.commit();
//                //替换tabar
//                ((RadioButton) mainTab.getChildAt(1)).setChecked(true);
//            }
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
