package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DengruData;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import okhttp3.Call;
import okhttp3.MediaType;

public class ChengGongZhuCe extends AutoLayoutActivity {
    private Button lijishiming,wodezhanghu;
    private String shoujihao;

    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private String password;
    private String dengrufanhuizhi;
    private int userid1;
    private String myurl;
    private String logid;
    private String token;
//    private ImageView zhucefanhui;
    public static ChengGongZhuCe instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
        setContentView(R.layout.activity_chenggong_zhuce);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .keyboardEnable(true)
                .init();
        getchengg_id();


        final Intent intent = getIntent();
        if (intent != null) {
            shoujihao = intent.getStringExtra("Phone_my");
            logid = intent.getStringExtra("logid");
            token = intent.getStringExtra("token");
            myurl = intent.getStringExtra("url");
            password = intent.getStringExtra("password");
            Bundle b = getIntent().getExtras();
            userid1 = b.getInt("user_id");
            MyLog.e("成功注册：", "Phone_my:"+shoujihao+"__password:"+password+"__url:"+myurl);
        }

//        zhucefanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {//返回到我的页面
//                getfanhuiHttp();
//            }
//        });
        wodezhanghu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                double zonge =0.00;//总额
//                EventBus.getDefault().post(new User_data(shoujihao,  userid));
                getHttp_zhucechenggong();

            }
        });
        lijishiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//实名认证
                getconrHttp();

            }
        });

    }

    private void getfanhuiHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("username", shoujihao);
            js_request.put("password", password);
            js_request.put("url", myurl);
            MyLog.e("实名成功(登入)：", "" + js_request);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            JSONObject sign = canshu.put("sign", sha1);
//            Log.e("我的账户：", ""+canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/Applogin.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("TAG", ">>>>GSON" + response);
                        DengruData d_data = new Gson().fromJson(response, DengruData.class);
                        //状态值
                        String dengrufanhuizhi = d_data.getState();
                        String user_token = d_data.getResult().getToken();
                        int user_id = d_data.getResult().getUserid();
                        String loginId = d_data.getResult().getLoginId();
                        if (dengrufanhuizhi.equals("success")) {
                            EventBus.getDefault().post(new User_data(shoujihao, dengrufanhuizhi, user_token, user_id, loginId));
                            SPUtils.put(ChengGongZhuCe.this, "isLogin", true);
                            SPUtils.put(ChengGongZhuCe.this, "Loginid", loginId);
                            SPUtils.put(ChengGongZhuCe.this, "userId", user_id);
                            SPUtils.put(ChengGongZhuCe.this, "Token1", user_token);
                            ZhuCe_PaGa.zc_instance.finish();
                            YanZheng_PaGa.instance.finish();
                            WoDe_DengRu.instance.finish();
                            finish();
                        }
                    }
                });
    }

    private void getconrHttp() {
        SPUtils.put(ChengGongZhuCe.this, "username", shoujihao);
        SPUtils.put(ChengGongZhuCe.this, "password", password);
        SPUtils.put(ChengGongZhuCe.this, "url", myurl);
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", userid1);
            js_request.put("token", token);
            js_request.put("loginId", logid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>SH!!" + sha1);
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
                .url(Urls.BASE_URL+"yxbApp/queryUserAuthInfo.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("是否可实名GSON：", response);
                        ShiFouKeShiMing_gson data = new Gson().fromJson(response, ShiFouKeShiMing_gson.class);
                        String message = data.getMessage().toString();
                        ToastUtils.showToast(ChengGongZhuCe.this, ""+message);
                        String jieguo = data.getState().toString();
                        if (jieguo.equals("success")){
                            Intent it=new Intent(ChengGongZhuCe.this,ShiMingrenzheng.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("user_ird", userid1);
                            SPUtils.put(ChengGongZhuCe.this, "userId", userid1);
                            it.putExtras(bundle);

                            startActivity(it);

                        }
                    }
                });

    }

    private void getHttp_zhucechenggong() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("username", shoujihao);
            js_request.put("password", password);
            js_request.put("url", myurl);

            MyLog.e("成功注册(登入)：", "" + js_request);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            JSONObject sign = canshu.put("sign", sha1);
//            Log.e("我的账户：", ""+canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/Applogin.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("TAG", ">>>>GSON" + response);
                        DengruData d_data = new Gson().fromJson(response, DengruData.class);
                        //状态值
                        dengrufanhuizhi = d_data.getState();
                        String user_token = d_data.getResult().getToken();
                        int user_id = d_data.getResult().getUserid();
                        String loginId = d_data.getResult().getLoginId();
                        if (dengrufanhuizhi.equals("success")) {
                            EventBus.getDefault().post(new User_data(shoujihao, dengrufanhuizhi, user_token, user_id, loginId));
                            SPUtils.put(ChengGongZhuCe.this, "isLogin", true);
                            SPUtils.put(ChengGongZhuCe.this, "Loginid", loginId);
                            SPUtils.put(ChengGongZhuCe.this, "userId", user_id);
                            SPUtils.put(ChengGongZhuCe.this, "Token1", user_token);
                            SPUtils.put(ChengGongZhuCe.this, "inviterId", d_data.getResult().getInviterId());
                            ZhuCe_PaGa.zc_instance.finish();
                            YanZheng_PaGa.instance.finish();
                            WoDe_DengRu.instance.finish();
                            finish();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getfanhuiHttp();
    }

    private void getchengg_id() {
        lijishiming=findViewById(R.id.lijishiming);
        wodezhanghu=findViewById(R.id.wodezhanghu);
//        zhucefanhui=findViewById(R.id.zhucefanhui1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }
}
