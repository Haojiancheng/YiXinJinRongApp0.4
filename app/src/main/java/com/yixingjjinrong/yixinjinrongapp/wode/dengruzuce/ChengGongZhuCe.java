package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DengruData;
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
    private int userid;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private String password;
    private String dengrufanhuizhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chenggong_zhuce);
        
        getchengg_id();
        
        
        final Intent intent = getIntent();
        if (intent != null) {
            shoujihao = intent.getStringExtra("Phone_my");
            userid = (int) intent.getSerializableExtra("user_id");
            password = intent.getStringExtra("password");
        }
        
        
        wodezhanghu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                double zonge =0.00;//总额
//                EventBus.getDefault().post(new User_data(shoujihao,  userid));
                finish();
                getHttp_zhucechenggong();
            }
        });
        lijishiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//实名认证
                Intent it=new Intent(ChengGongZhuCe.this,ShiMingrenzheng.class);
                startActivity(it);
            }
        });

    }

    private void getHttp_zhucechenggong() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("username", shoujihao);
            js_request.put("password", password);
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
