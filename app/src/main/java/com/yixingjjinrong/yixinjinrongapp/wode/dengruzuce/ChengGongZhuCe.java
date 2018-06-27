package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DengruData;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class ChengGongZhuCe extends AutoLayoutActivity {
    private Button lijishiming,wodezhanghu;
    private String shoujihao;

    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private String password;
    private String dengrufanhuizhi;
    private int userid1;
    private String myurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chenggong_zhuce);
        
        getchengg_id();
        
        
        final Intent intent = getIntent();
        if (intent != null) {
            shoujihao = intent.getStringExtra("Phone_my");
            myurl = intent.getStringExtra("url");
            password = intent.getStringExtra("password");
            Bundle b = getIntent().getExtras();
            userid1 = b.getInt("user_id");
            Log.e("成功注册：", "Phone_my:"+shoujihao+"__password:"+password+"__url:"+myurl);
        }
        
        
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

    private void getconrHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", userid1);
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

        RequestParams params = new RequestParams(Urls.BASE_URL+"yxb_mobile/yxbApp/queryUserAuthInfo.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("是否可实名GSON：", result);
                ShiFouKeShiMing_gson data = new Gson().fromJson(result, ShiFouKeShiMing_gson.class);
                String message = data.getMessage().toString();
                Toast.makeText(ChengGongZhuCe.this, ""+message, Toast.LENGTH_SHORT).show();
                String jieguo = data.getState().toString();
                if (jieguo.equals("success")){
                    Intent it=new Intent(ChengGongZhuCe.this,ShiMingrenzheng.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("user_ird", userid1);
                    it.putExtras(bundle);
                    startActivity(it);
                    finish();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void getHttp_zhucechenggong() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("username", shoujihao);
            js_request.put("password", password);
            js_request.put("url", myurl);
            Log.e("成功注册(登入)：", ""+js_request);
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
            JSONObject sign = canshu.put("sign", sha1);
//            Log.e("我的账户：", ""+canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(Urls.BASE_URL+"yxb_mobile/yxbApp/Applogin.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", ">>>>GSON" + result);
                DengruData d_data = new Gson().fromJson(result, DengruData.class);
                //状态值
                dengrufanhuizhi = d_data.getState();
                String  user_token=d_data.getResult().getToken();
                int user_id=d_data.getResult().getUserid();
                if (dengrufanhuizhi.equals("success")) {
                    EventBus.getDefault().post(new User_data(shoujihao, dengrufanhuizhi,user_token,user_id));
                    finish();
                }
                
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void getchengg_id() {
        lijishiming=findViewById(R.id.lijishiming);
        wodezhanghu=findViewById(R.id.wodezhanghu);
    }
}
