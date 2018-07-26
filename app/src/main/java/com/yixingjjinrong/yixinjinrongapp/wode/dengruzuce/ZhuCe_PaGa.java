package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.YanZhengShouJiHao_Data;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.ZhuCeH5;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class ZhuCe_PaGa extends AutoLayoutActivity {
    private ImageView zhuce_fanhui, zhuceqingchu;//返回按钮,清除手机号
    private Button zhuce_xiayibu;//下一步跳转
    private EditText zhucephone;//需要注册的手机号
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String myphone;
    private String phonezhuangtai;
    private TextView zz_h5,zz_dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_zhuce__pa_ga);
        getzhuce_pagerId();

        getZhuCeOnClik();
    }


    private void getZhuCeOnClik() {
        zz_dr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ZhuCe_PaGa.this,WoDe_DengRu.class);
                startActivity(it);
                finish();
            }
        });
        zz_h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zhuce_page = new Intent(ZhuCe_PaGa.this, ZhuCeH5.class);

                startActivity(zhuce_page);
            }
        });


        zhuce_fanhui.setOnClickListener(new View.OnClickListener() {//返回按钮
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zhuce_xiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myphone = zhucephone.getText().toString();
                if (myphone.length() < 11) {
                    Toast.makeText(ZhuCe_PaGa.this, "手机号非法", Toast.LENGTH_SHORT).show();
                } else if (myphone.length() > 11) {
                    Toast.makeText(ZhuCe_PaGa.this, "手机号非法", Toast.LENGTH_SHORT).show();
                } else {

                    if (myphone.isEmpty()) {
                        Toast.makeText(ZhuCe_PaGa.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        getHttp();

//                        Intent zhuce_page=new Intent(ZhuCe_PaGa.this,YanZheng_PaGa.class);
//                        zhuce_page.putExtra("user_Phone",zhucephone.getText().toString());
//                        startActivity(zhuce_page);

                    }
                }
            }
        });
        zhuceqingchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                zhuceqingchu.setImageDrawable(getResources().getDrawable(R.drawable.biyan));
                zhucephone.setText("");
            }
        });

    }

    private void getHttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("phone", myphone);
            js_request.put("type", 4);
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
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxbApp/PhoneVerify.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "Gson:" + result);
                YanZhengShouJiHao_Data data = new Gson().fromJson(result, YanZhengShouJiHao_Data.class);
                phonezhuangtai = data.getResult().getMapPhone();
                if (phonezhuangtai.equals("1")) {
                    Intent zhuce_page = new Intent(ZhuCe_PaGa.this, YanZheng_PaGa.class);
                    zhuce_page.putExtra("user_Phone", zhucephone.getText().toString());
                    startActivity(zhuce_page);
                    finish();
                } else {
                    Toast.makeText(ZhuCe_PaGa.this, "该手机号已注册，请登入", Toast.LENGTH_SHORT).show();
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

    private void getzhuce_pagerId() {
        zhuce_fanhui = findViewById(R.id.zhucefanhui);
        zhuce_xiayibu = findViewById(R.id.zhuce_xiayibu);
        zhucephone = findViewById(R.id.zucePhone);//需要注册的手机号
        zhuceqingchu = findViewById(R.id.zhuce_qingchu);//清除手机号
        zz_h5 = findViewById(R.id.zz_h5);
        zz_dr=findViewById(R.id.zz_dr);
    }
}
