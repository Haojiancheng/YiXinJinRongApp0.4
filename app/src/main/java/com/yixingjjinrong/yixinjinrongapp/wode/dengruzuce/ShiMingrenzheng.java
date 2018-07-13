package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiMingRenZengJieGuo_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class ShiMingrenzheng extends AutoLayoutActivity {
    private EditText zhen_name,user_idcard;
    private Button renzheng_goin;
    private int user_ird;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private TextView jinggao;//错误消息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_mingrenzheng);
        Bundle b = getIntent().getExtras();
        user_ird = b.getInt("user_ird");
        getzhen_id();
        getonrenzheng();

    }

    private void getonrenzheng() {
        renzheng_goin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHttp();
            }
        });
    }

    private void getHttp() {
        String my_name = zhen_name.getText().toString();
        final String zhen_id = user_idcard.getText().toString();
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_ird);
            js_request.put("idNo", zhen_id);
            js_request.put("realName", my_name);
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

        RequestParams params = new RequestParams(Urls.BASE_URL+"yxb_mobile/yxbApp/userAuth.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("实名认证的GSOn", ""+result);
                ShiMingRenZengJieGuo_gson data = new Gson().fromJson(result, ShiMingRenZengJieGuo_gson.class);
                String message = data.getMessage().toString();
//                Toast.makeText(ShiMingrenzheng.this, ""+message, Toast.LENGTH_SHORT).show();

                String zhuangtai = data.getState();
                Log.e("实名认证", zhuangtai);
                if (zhuangtai.equals("success")){
                    String realName = data.getResult().getRealName();
                    String idNo = data.getResult().getIdNo();
                    Intent intent=new Intent(ShiMingrenzheng.this,ShiMingRenZhengKO.class);
                    startActivity(intent);
                }else {
                    jinggao.setVisibility(View.VISIBLE);
                    jinggao.setText(message);
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

    private void getzhen_id() {
        zhen_name=findViewById(R.id.zhen_name);
        user_idcard=findViewById(R.id.user_idcard);
        renzheng_goin=findViewById(R.id.renzheng_goin);
        jinggao=findViewById(R.id.jinggao11);
    }
}
