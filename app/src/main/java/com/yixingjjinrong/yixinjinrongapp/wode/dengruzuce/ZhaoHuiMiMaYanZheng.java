package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ChongZiMIMA_Gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.YanZhengMa_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.MediaType;

public class ZhaoHuiMiMaYanZheng extends AutoLayoutActivity {
    private Button queding_zhaohui,zhaohui_yanzheng;//找回密码——确定
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private String phone;
    private TimeCount time;
    private TextView phonet,jinggao;
    private EditText ed_code,news_mima;
    private ImageView et_qc;
    private ToggleButton zh_togglePwd;
    public static final String REGEX_PASSWORD = "^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$";
    private ImageView zhyz_fh;
    private String message;
    private String jsessionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_zhaohui_mima_yanzheng);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        getzhaohuimima_Id();
        Intent it=getIntent();
        phone = it.getStringExtra("phone");
        String mobile = phone;
        String maskNumber = mobile.substring(0,3)+"****"+mobile.substring(7,mobile.length());
        phonet.setText("正在为"+maskNumber+"找回登入密码");
        getzhaohuimima_Onclik();

    }
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    private void getzhaohuimima_Onclik() {
        zhyz_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zhaohui_yanzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getHttP_YAnzhengMA();
            }
        });
        queding_zhaohui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lenght = news_mima.getText().toString().trim().length();
//                Toast.makeText(YanZheng_PaGa.this,"6:"+isPassword(user_mima.getText().toString()),Toast.LENGTH_SHORT).show();
                if (lenght<6||lenght>18 || !isPassword(news_mima.getText().toString())){
                    jinggao.setVisibility(View.VISIBLE);
                    Toast.makeText(ZhaoHuiMiMaYanZheng.this,"6-18位字母和数字组合",Toast.LENGTH_SHORT).show();
                }else {
                    getyanzhengHttp();
                }
            }
        });
        zh_togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    news_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    zh_togglePwd.setBackground(getResources().getDrawable(R.drawable.xianshi));
                } else {
                    //否则隐藏密码
                    news_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    zh_togglePwd.setBackground(getResources().getDrawable(R.drawable.buxianshi));
                }
            }
        });
        et_qc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_code.setText("");
            }
        });
    }

    private void getyanzhengHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("phone", phone);
            js_request.put("password", news_mima.getText().toString());
            js_request.put("code", ed_code.getText().toString());
            Log.e("找回密码",""+js_request);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {

        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL+"yxbApp/forgetPwd.do")
                .content(canshu.toString())
                .addHeader("Cookie", "JSESSIONID=" + jsessionId)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("找回密码的GSON",""+result );
                        ChongZiMIMA_Gson data = new Gson().fromJson(result, ChongZiMIMA_Gson.class);
                        String message = data.getMessage().toString();
                        String zhuangtai = data.getState().toString();
                        if (zhuangtai.equals("success")){
                            Toast.makeText(ZhaoHuiMiMaYanZheng.this, ""+message, Toast.LENGTH_SHORT).show();
                            Intent it=new Intent(ZhaoHuiMiMaYanZheng.this,WoDe_DengRu.class);
                            startActivity(it);
                            finish();
                        }else {
                            Toast.makeText(ZhaoHuiMiMaYanZheng.this, ""+message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void getHttP_YAnzhengMA() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("phone", phone);
            js_request.put("type", 3);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {

        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL+"yxbApp/sendsms.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", ">>>>成功" + response);
                        YanZhengMa_gson data = new Gson().fromJson(response, YanZhengMa_gson.class);
                        if (data.getMessage().equals("发送短信成功")) {

                            message = data.getMessage();
                            jsessionId = data.getResult().getJsessionId();
                            Log.e("jsessionId",""+ jsessionId);
                            time = new TimeCount(60000, 1000);
                            time.start();
                        } else {
                            Toast.makeText(ZhaoHuiMiMaYanZheng.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                            if (data.getMessage().equals("验证码发送次数达到上限，请明天再试")) {
                               zhaohui_yanzheng .setText("明天再试");
                                zhaohui_yanzheng.setBackgroundResource(R.color.gray);
                            }
                        }
                    }
                });

    }

    private void getzhaohuimima_Id() {
        queding_zhaohui=findViewById(R.id.zhaohuimiam_queding);//找回密码——确定
        zhaohui_yanzheng=findViewById(R.id.zhaohui_yanzheng);
        phonet=findViewById(R.id.phonet);
        ed_code=findViewById(R.id.edit_code);
        news_mima=findViewById(R.id.news_mima);
        zh_togglePwd=findViewById(R.id.zh_togglePwd);
        et_qc=findViewById(R.id.zh_guanbi);
        jinggao=findViewById(R.id.jingdao);
        zhyz_fh=findViewById(R.id.zhyz_fh);
    }
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            huoqu_yanzhengma.setBackgroundColor(Color.parseColor("#B6B6D8"));
            zhaohui_yanzheng.setClickable(false);
            zhaohui_yanzheng.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            zhaohui_yanzheng.setText("重新获取验证码");
            zhaohui_yanzheng.setClickable(true);
//            huoqu_yanzhengma.setBackgroundColor(Color.parseColor());

        }
    }
}
