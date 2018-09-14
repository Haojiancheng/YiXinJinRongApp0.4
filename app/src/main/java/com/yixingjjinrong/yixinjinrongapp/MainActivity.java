package com.yixingjjinrong.yixinjinrongapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.hjm.bottomtabbar.BottomTabBar;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore2;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyConten;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.Wode;
import com.yixingjjinrong.yixinjinrongapp.wode.shezhi.WoDe_SheZhi;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.XiangMu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.Faxian;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.Shouye;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.MediaType;

public class MainActivity extends AutoLayoutActivity {
    private BottomTabBar mbottomBar;
    private IntentFilter filter;
    private View decorView;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    /**
     * 双击退出功能是否生效（默认ture）
     */
    private boolean DOUBLECLICK_EXIT = true;

    /**
     * 双击退出函数
     */
    private long firstTime = 0;
    private String str;
    private Date curDate1;
    private int user_id;
    private String userToken;


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
        Intent it = getIntent();
        String id = it.getStringExtra("id");
        if (id != null) {
            if (id.equals("1")) {
                mbottomBar.setCurrentTab(1);
            } else if (id.equals("2")) {
                mbottomBar.setCurrentTab(3);
            }
        }
        user_id = (int) SPUtils.get(this, "userId", 0);
        userToken = (String) SPUtils.get(this, "Token1", "");
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        curDate1 = new Date(System.currentTimeMillis());
        //获取当前时间
        str = formatter.format(curDate1);
        Log.e("我进入后台了：", str);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        //获取当前时间
        String str2 = formatter.format(curDate);
        Log.e("我进入前台了：", str2);
        //时间差
        if (curDate1!=null) {
            long diff = curDate.getTime() - curDate1.getTime();//这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            Log.e("时间差：", "" + minutes);
            if (minutes>=30){
//                gethttp();
            }
        }
    }

    private void gethttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        String myurl = getid(this);
        try {

//            SPUtils.remove(WoDe_SheZhi.this,"userId");
//            SPUtils.remove(WoDe_SheZhi.this,"Token1");
            js_request.put("userid", user_id);
            js_request.put("token", userToken);
            js_request.put("url", myurl);
            Log.e("useridddd", user_id + "");
            Log.e("token", userToken + "");
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/quitLogin.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast(MainActivity.this, "网络错误，请售后再试");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("退出登入的GSON:", response);
//                Intent shezhi_tuichu=new Intent(WoDe_SheZhi.this, WoDe_DengRu.class);
//                startActivity(shezhi_tuichu);
                        SPUtils.put(MainActivity.this, "isLogin", false);
//                EventBus.getDefault().post(new UnLogin());
                        SPUtils.remove(MainActivity.this, "userId");
                        SPUtils.remove(MainActivity.this, "Loginid");
//                        promptDialog.dismiss();
//                        finish();
                    }
                });
    }
    public synchronized String getid(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String ID = TelephonyMgr.getDeviceId();
        return ID;
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
        mbottomBar.init(getSupportFragmentManager(), 0, 3000)
                .setChangeColor(Color.parseColor("#fe6623"), Color.parseColor("#999999"))
                .setFontSize(9)
                .setTabPadding(16, 12, 18)//设置ICON图片与上部分割线的间隔、图片与文字的间隔、文字与底部的间隔
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
        EventBus.getDefault().post(new LookMore2("1"));
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
