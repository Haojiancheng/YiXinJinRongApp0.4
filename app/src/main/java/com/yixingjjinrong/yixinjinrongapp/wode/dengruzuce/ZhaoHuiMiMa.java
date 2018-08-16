package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.MaxLengthWatcher;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ZhaoHuiMIma_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.Call;
import okhttp3.MediaType;

public class ZhaoHuiMiMa extends AutoLayoutActivity {
    private Button zhaohuimima_xiayibu;//找回密码下一步
    private EditText myphonet;
    private ImageView zh_fh;
    private String sha1;//SHA1加密
    private String base1;//Base64加

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_zhaohui_mima);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        getzhaohuimimaId();
        getzhaohuimimaOnCilk();

    }



    private void getzhaohuimimaOnCilk() {
        myphonet.addTextChangedListener(new MaxLengthWatcher(11, myphonet));
        zhaohuimima_xiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myphonet.getText().toString().equals(""))
                    Toast.makeText(ZhaoHuiMiMa.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                else if (myphonet.getText().toString().length() < 11) {
                    Toast.makeText(ZhaoHuiMiMa.this, "手机号非法", Toast.LENGTH_SHORT).show();
                } else if (myphonet.getText().toString().length() > 11) {
                    Toast.makeText(ZhaoHuiMiMa.this, "手机号非法", Toast.LENGTH_SHORT).show();
                } else {
                    gethttp();

                }

            }
        });

        zh_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void gethttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("phone", myphonet.getText().toString());
            js_request.put("type", 2);
//            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>SDEWSFDREREbase加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>GGGGGGGSH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>()base()加密11111!!--" + base1);
            Log.e("TAG", ">>>>()sha1()加密11111!!--" + sha1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL+"yxbApp/PhoneVerify.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("ssss",""+result );

                        ZhaoHuiMIma_Gson data = new Gson().fromJson(result, ZhaoHuiMIma_Gson.class);
                        Log.e("ddddd", ""+data.getMessage());
                        if (data.getMessage().equals("该手机已注册!")){
                            Intent zhaohuimima_it = new Intent(ZhaoHuiMiMa.this, ZhaoHuiMiMaYanZheng.class);
                            zhaohuimima_it.putExtra("phone", myphonet.getText().toString());
                            startActivity(zhaohuimima_it);
                            finish();
                        }else {
                            Toast.makeText(ZhaoHuiMiMa.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void getzhaohuimimaId() {
        zhaohuimima_xiayibu = findViewById(R.id.zhaohuimiam_xiayibu);//找回密码下一步
        myphonet = findViewById(R.id.myphonet);
        zh_fh = findViewById(R.id.zh_fh);
    }
}
