package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.MaxLengthWatcher;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.YanZhengShouJiHao_Data;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.h5.ZhuCeH5;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import okhttp3.Call;
import okhttp3.MediaType;

public class ZhuCe_PaGa extends AutoLayoutActivity {
    private ImageView zhuce_fanhui, zhuceqingchu;//返回按钮,清除手机号
    private Button zhuce_xiayibu;//下一步跳转
    private EditText zhucephone;//需要注册的手机号
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String myphone;
    private String phonezhuangtai;
    private TextView zz_h5, zz_dr;
    private CheckBox zc_check;//复选框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_zhuce__pa_ga);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        getzhuce_pagerId();

        getZhuCeOnClik();
    }

    public static boolean isMobileNo(String mobiles) {
        /*
         * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、184、187、188、147
         * 联通号码段:130、131、132、185、186、145、171/176/175
         * 电信号码段:133、153、180、181、189、173、177
         */
        String telRegex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([1-3]|[5-9]))|(18[0-9]))\\d{8}$";
        /**
         * (13[0-9])代表13号段 130-139
         * (14[5|7])代表14号段 145、147
         * (15([0-3]|[5-9]))代表15号段 150-153 155-159
         * (17([1-3][5-8]))代表17号段 171-173 175-179 虚拟运营商170屏蔽
         * (18[0-9]))代表18号段 180-189
         * d{8}代表后面可以是0-9的数字，有8位
         */
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    private void getZhuCeOnClik() {
        zz_dr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ZhuCe_PaGa.this, WoDe_DengRu.class);
                startActivity(it);
//                finish();
            }
        });
        zz_h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zhuce_page = new Intent(ZhuCe_PaGa.this, ZhuCeH5.class);

                startActivity(zhuce_page);
            }
        });

        zhucephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if (s.length()>0){
                   zhuceqingchu.setVisibility(View.VISIBLE);
               }else {
                   zhuceqingchu.setVisibility(View.GONE);
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

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

                if (myphone.equals("")) {
                    Toast.makeText(ZhuCe_PaGa.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (myphone.length() < 11||isMobileNo(myphone)==false) {
                        Toast.makeText(ZhuCe_PaGa.this, "手机号非法", Toast.LENGTH_SHORT).show();
                    } else if (myphone.length() > 11||isMobileNo(myphone)==false) {
                        Toast.makeText(ZhuCe_PaGa.this, "手机号非法", Toast.LENGTH_SHORT).show();
                    } else {
                        if (zc_check.isChecked()) {
                            getHttp();
                        } else {
                            ToastUtils.showToast(ZhuCe_PaGa.this, "请阅读并勾选注册协议");
                        }

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
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/PhoneVerify.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("TAG", "Gson:" + result);
                        YanZhengShouJiHao_Data data = new Gson().fromJson(result, YanZhengShouJiHao_Data.class);
                        phonezhuangtai = data.getResult().getMapPhone();
                        if (phonezhuangtai.equals("1")) {
                            Intent zhuce_page = new Intent(ZhuCe_PaGa.this, YanZheng_PaGa.class);
                            zhuce_page.putExtra("user_Phone", zhucephone.getText().toString());
                            startActivity(zhuce_page);
                            finish();
                        } else {
                           ToastUtils.showToast(ZhuCe_PaGa.this, data.getMessage());
                        }
                    }
                });

    }

    private void getzhuce_pagerId() {
        zc_check = findViewById(R.id.zc_check);
        zhuce_fanhui = findViewById(R.id.zhucefanhui);
        zhuce_xiayibu = findViewById(R.id.zhuce_xiayibu);
        zhucephone = findViewById(R.id.zucePhone);//需要注册的手机号
        zhuceqingchu = findViewById(R.id.zhuce_qingchu);//清除手机号
        zz_h5 = findViewById(R.id.zz_h5);
        zz_dr = findViewById(R.id.zz_dr);
        zhucephone.addTextChangedListener(new MaxLengthWatcher(11, zhucephone));
        zhucephone.setInputType( InputType.TYPE_CLASS_NUMBER);//数字键盘
    }
}
