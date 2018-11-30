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
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CunGuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.GeRenXingXiGson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.FengXianPingCe;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.YinHangCunGuan;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.addess.MyAddess;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.ceping.CePingJieGuo;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.shiming.WoDeShiMing;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.shiming.YiShiMing;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class My_Content extends AutoLayoutActivity {
    private TextView myphone, shiming, cunguan, ceping;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private View shouhuodi;
    private String token1;
    private String loginid;
    private ImageView mycontentfanhui;
    private GeRenXingXiGson gr_data;
    private PromptDialog promptDialog;
    private View shiming_itme,cunguan_itme,fengxian_itme;

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
                .statusBarDarkFont(true)
                .init();
        getmyconcentid();
//        getHttp();
        getonclock();


    }

    private void getonclock() {
        mycontentfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shouhuodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//收货地址
                Intent intent = new Intent(My_Content.this, MyAddess.class);
                startActivity(intent);
            }
        });
    }

    private void getHttp() {
        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
            MyLog.e("sdashf", js_request.toString());
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/userInformation.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MyLog.e("个人信息roon", "" + e);
                        promptDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        promptDialog.dismiss();
                        MyLog.e("个人信息gson", "" + response);
                        gr_data = new Gson().fromJson(response, GeRenXingXiGson.class);
                        getmy();

                    }
                });

    }

    private void getmy() {
        myphone.setText(gr_data.getUserMap().getPhone());//手机号

        if (gr_data.getUserMap().getAuth().equals("1")) { //实名认证
            shiming.setText("已认证");
            shiming_itme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(My_Content.this, YiShiMing.class);
                    startActivity(it);
                }
            });
        } else {
            shiming_itme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getshimingHTTp();

                }
            });
        }
        if (gr_data.getUserMap().getCg().equals("1")) {//银行存管
            cunguan.setText("已开通");
            cunguan_itme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(My_Content.this, YiShiMing.class);
                    startActivity(it);
                }
            });
        } else {
            cunguan.setText("未开通");

            cunguan_itme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    grthttp();
                }
            });
        }
        if (gr_data.getUserMap().getRisk().equals("1")) {//风险评测

            switch (gr_data.getUserMap().getRiskType()) {
                case "1":
                    ceping.setText("保守型");
                    break;
                case "2":
                    ceping.setText("稳健型");
                    break;
                case "3":
                    ceping.setText("积极型");
                    break;
                default:
                    break;

            }
            fengxian_itme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(My_Content.this, CePingJieGuo.class);
                    intent.putExtra("type", gr_data.getUserMap().getRiskType());
                    startActivity(intent);
                }
            });

        } else {

            ceping.setText("未测评");
            fengxian_itme.setOnClickListener(new View.OnClickListener() {
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
            MyLog.e("TAG", ">>>>加密11111!!--" + canshu);
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
                        MyLog.e("存管GSON:", "" + result);

                        CunGuan_gson data = new Gson().fromJson(result, CunGuan_gson.class);
                        if (data.getMessage().equals("存管账号开通成功")) {
                            String html = data.getResult().getHtml();
                            Intent it = new Intent(My_Content.this, YinHangCunGuan.class);
                            it.putExtra("HTML", html);
                            startActivity(it);
//                    Toast.makeText(My_Content.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
                            MyLog.e("wangy", "" + html);
                        } else {
                            ToastUtils.showToast(My_Content.this, data.getMessage());
                        }
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
                        MyLog.e("是否可实名GSON：", result);
                        ShiFouKeShiMing_gson data = new Gson().fromJson(result, ShiFouKeShiMing_gson.class);
                        String message = data.getMessage().toString();
                        if (message.equals("可以认证")) {
                            String jieguo = data.getState().toString();
                            if (jieguo.equals("success")) {
                                Intent it = new Intent(My_Content.this, WoDeShiMing.class);
                                startActivity(it);
                            }
                        } else {
                            ToastUtils.showToast(My_Content.this, message);
                        }
                    }
                });

    }

    private void getmyconcentid() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        token1 = (String) SPUtils.get(this, "Token1", "");
        loginid = (String) SPUtils.get(this, "Loginid", "");
        shiming = findViewById(R.id.shiming);
        myphone = findViewById(R.id.myphone1);
        cunguan = findViewById(R.id.cunguan);
        ceping = findViewById(R.id.ceping);
        shouhuodi = findViewById(R.id.xj31);//收货地址
        mycontentfanhui = findViewById(R.id.mycontentfanhui);
        fengxian_itme=findViewById(R.id.fengxian_itme);
        cunguan_itme=findViewById(R.id.cunguan_itme);
        shiming_itme=findViewById(R.id.shiming_itme);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getHttp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }
}
