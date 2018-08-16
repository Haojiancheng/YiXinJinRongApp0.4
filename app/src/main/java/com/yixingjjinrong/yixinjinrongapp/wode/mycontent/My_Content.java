package com.yixingjjinrong.yixinjinrongapp.wode.mycontent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CunGuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.FengXianPingCe;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.YinHangCunGuan;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.addess.MyAddess;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.shiming.YiShiMing;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class My_Content extends AutoLayoutActivity {
    private TextView myphone, shiming, cunguan, ceping;
    private String fx;
    private String s_name;
    private String blank;
    private String telephone;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private String riskType;
    private ImageView shouhuodi;
    private String token1;
    private String loginid;
    private ImageView mycontentfanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_my_content);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .keyboardEnable(true)
                .init();
        getmyconcentid();
        myphone.setText(telephone);
        mycontentfanhui=findViewById(R.id.mycontentfanhui);
        mycontentfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (s_name.equals("1")) {
            shiming.setText("已认证");
            shiming.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent(My_Content.this, YiShiMing.class);
                    startActivity(it);
                }
            });
        } else {
            shiming.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getshimingHTTp();
                    finish();
                }
            });
        }
        if (blank.equals("1")) {
            cunguan.setText("已开通");
            cunguan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent(My_Content.this, YiShiMing.class);
                    startActivity(it);
                }
            });
        } else {
            cunguan.setText("未开通");

            cunguan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    grthttp();
                }
            });
        }
        if (fx.equals("1")) {
            ceping.setText(riskType);
        } else {
            ceping.setText("未测评");
            ceping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(My_Content.this, FengXianPingCe.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("user_ird", user_id);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });
        }
        shouhuodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//收货地址
                Intent intent=new Intent(My_Content.this, MyAddess.class);
                startActivity(intent);
            }
        });
    }

    private void grthttp() {

            JSONObject js_request = new JSONObject();//服务器需要传参的json对象
            try {
                js_request.put("userid", String.valueOf(user_id));
                js_request.put("token", token1);
                js_request.put("loginId", loginid);
                base1 = Base64JiaMI.AES_Encode(js_request.toString());

                sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject canshu = new JSONObject();
            try {
                canshu.put("param", base1);
                canshu.put("sign", sha1);
                Log.e("TAG", ">>>>加密11111!!--" + canshu);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/accountReg.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("存管GSON:",""+result );
                        CunGuan_gson data = new Gson().fromJson(result, CunGuan_gson.class);
                        String html = data.getResult().getHtml();
                        Intent it=new Intent(My_Content.this, YinHangCunGuan.class);
                        it.putExtra("HTML",html );
                        startActivity(it);
//                    Toast.makeText(My_Content.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("wangy",""+html );
                    }
                });

    }

    private void getshimingHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
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
                .url(Urls.BASE_URL + "yxbApp/queryUserAuthInfo.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("是否可实名GSON：", result);
                        ShiFouKeShiMing_gson data = new Gson().fromJson(result, ShiFouKeShiMing_gson.class);
                        String message = data.getMessage().toString();

                        Toast.makeText(My_Content.this, "" + message, Toast.LENGTH_SHORT).show();
                        String jieguo = data.getState().toString();
                        if (jieguo.equals("success")) {
                            Intent it = new Intent(My_Content.this, ShiMingrenzheng.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("user_ird", user_id);
                            it.putExtras(bundle);
                            startActivity(it);
                        }
                    }
                });

    }

    private void getmyconcentid() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        token1 = (String) SPUtils.get(this, "Token1", "");
        loginid = (String) SPUtils.get(this, "Loginid", "");
        Intent intent = getIntent();//获取传来的intent对象
        //风险评测
        fx = intent.getStringExtra("fx");
        //实名认证
        s_name = intent.getStringExtra("s_name");
        //银行存管
        blank = intent.getStringExtra("blank");
        //手机号
        telephone = intent.getStringExtra("telephone");
        //测评结果
        riskType = intent.getStringExtra("riskType");
        Log.e("个人中心接到的数值", "风险评测:" + fx + "实名认证:" + s_name + "银行存管:" + blank + "手机号:" + telephone);
        shiming = findViewById(R.id.shiming);
        myphone = findViewById(R.id.myphone1);
        cunguan = findViewById(R.id.cunguan);
        ceping = findViewById(R.id.ceping);
        shouhuodi=findViewById(R.id.xj31);//收货地址
    }
}
