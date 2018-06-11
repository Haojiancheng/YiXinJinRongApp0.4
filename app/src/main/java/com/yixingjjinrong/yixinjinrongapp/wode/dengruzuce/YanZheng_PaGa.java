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
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ChengGongzhuce_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Pattern;

public class YanZheng_PaGa extends AutoLayoutActivity {
    private Button yanzheng_zhuce,huoqu_yanzhengma;
    private ToggleButton zc_togglePwd;
    private TextView huode_phone;
    private String get_phone;
    private EditText phonecode,user_mima,user_yaoqingren;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private ImageView et_qc;//清除输入框
    public static final String REGEX_PASSWORD = "^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$";
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanzheng__pa_ga);
        getyanZheng_Id();
        getyanZheng_Onclick();
        time = new TimeCount(60000, 1000);
    }
   
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
    private void getyanZheng_Onclick() {
        yanzheng_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                int lenght = user_mima.getText().toString().trim().length();
//                Toast.makeText(YanZheng_PaGa.this,"6:"+isPassword(user_mima.getText().toString()),Toast.LENGTH_SHORT).show();
                if (lenght<6||lenght>18 || !isPassword(user_mima.getText().toString())){
                    Toast.makeText(YanZheng_PaGa.this,"6-18位字母和数字组合",Toast.LENGTH_SHORT).show();
                }else{
                    gethttp_zhuce();

                }
                
            }
        });
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null) {
            get_phone = intent.getStringExtra("user_Phone");
            huode_phone.setText(get_phone);
        }
        huoqu_yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.start();
                getHttP_YAnzhengMA();
                
            }
        });

        zc_togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    user_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    zc_togglePwd.setBackground(getResources().getDrawable(R.drawable.xianshi));
                } else {
                    //否则隐藏密码
                    user_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    zc_togglePwd.setBackground(getResources().getDrawable(R.drawable.buxianshi));
                }
            }
        });
        
        et_qc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonecode.setText("");
            }
        });
    }

    private void gethttp_zhuce() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("phone", get_phone);
            js_request.put("password", user_mima.getText().toString());
            js_request.put("phonecode",phonecode.getText().toString());
            js_request.put("address","1");
            js_request.put("phonemap",user_yaoqingren.getText().toString());

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
        RequestParams params = new RequestParams(Urls.BASE_URL+"yxb_mobile/yxbApp/register.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", ">>>>z成功" + result);
                ChengGongzhuce_Gson date=new Gson().fromJson(result,ChengGongzhuce_Gson.class);
               int userid= date.getResult().getUserid();
                
                Intent intent_dengru=new Intent(YanZheng_PaGa.this,ChengGongZhuCe.class);
                intent_dengru.putExtra("Phone_my",get_phone);
                intent_dengru.putExtra("password",user_mima.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("user_id",userid);

                intent_dengru.putExtras(bundle);
                startActivity(intent_dengru);
                finish();
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

    private void getHttP_YAnzhengMA() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("phone", get_phone);
            js_request.put("type", 1);
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
        RequestParams params = new RequestParams(Urls.BASE_URL+"yxb_mobile/yxbApp/sendsms.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", ">>>>成功" + result);
                
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

    private void getyanZheng_Id() {
        yanzheng_zhuce=findViewById(R.id.yanzheng_zhuce);
        huode_phone=findViewById(R.id.huode_Phone);//获得手机号
        huoqu_yanzhengma=findViewById(R.id.huoqu_yanzhengma);//获取验证码
        phonecode=findViewById(R.id.edit_phonecode);//手机验证码
        user_mima=findViewById(R.id.user_mima);//用户密码
        user_yaoqingren=findViewById(R.id.yaoqingren);//yaoqingren
        zc_togglePwd=findViewById(R.id.zc_togglePwd);//显示或隐藏密码
        et_qc=findViewById(R.id.yanz_guanbi);//清除输入框
    }
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            huoqu_yanzhengma.setBackgroundColor(Color.parseColor("#B6B6D8"));
            huoqu_yanzhengma.setClickable(false);
            huoqu_yanzhengma.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            huoqu_yanzhengma.setText("重新获取验证码");
            huoqu_yanzhengma.setClickable(true);
//            huoqu_yanzhengma.setBackgroundColor(Color.parseColor());

        }
    }
}
