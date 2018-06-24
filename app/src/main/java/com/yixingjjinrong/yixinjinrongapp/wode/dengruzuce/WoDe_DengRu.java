package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
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
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_id;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DengruData;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class WoDe_DengRu extends AutoLayoutActivity {
    private ImageView fanhui_dengru,dengru_guanbi;//返回键
    private TextView zhuche_dengru, zhaohuimima;//注册页面的跳转,找回密码页面的跳转
    private EditText dengru_phone, dengru_mima;//用户登入的手机号,用户登入的密码
    private Button dengrujoin;//登入
    private String shoujihao;
    private String mima;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private String dengrufanhuizhi;
    private boolean isLogin;
    private ToggleButton dr_togglePwd;//显示与隐藏密码
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_de__deng_ru);

        isLogin = (boolean) SPUtils.get(this,"isLogin",false);
        getDengruId();
        getdengruOnClick();
        

    }


    private void getdengruOnClick() {

        zhuche_dengru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_dengru = new Intent(WoDe_DengRu.this, ZhuCe_PaGa.class);
                startActivity(intent_dengru);
                finish();
            }
        });
        zhaohuimima.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent_Zhaohuimima = new Intent(WoDe_DengRu.this, ZhaoHuiMiMa.class);
                startActivity(intent_Zhaohuimima);
                finish();
            }
        });
        dengrujoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoujihao = dengru_phone.getText().toString();
                mima = dengru_mima.getText().toString();
                if (shoujihao.isEmpty() || mima.isEmpty()) {
                    Toast.makeText(WoDe_DengRu.this, "用户名或密码有误", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(WoDe_DengRu.this, "成功", Toast.LENGTH_SHORT).show();
                    getHttp();
                  
                   
                }
            }
        });
        fanhui_dengru.setOnClickListener(new View.OnClickListener() {//返回键
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dengru_guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dengru_phone.setText("");
            }
        });
        dr_togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    dengru_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    dr_togglePwd.setBackground(getResources().getDrawable(R.drawable.xianshi));
                } else {
                    //否则隐藏密码
                    dengru_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    dr_togglePwd.setBackground(getResources().getDrawable(R.drawable.buxianshi));
                }
            }
        });
        
    }

    private void getHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        String myurl= getid(context);
        Log.e("唯一标识",""+myurl);
        
        try {
            js_request.put("username", shoujihao);
            js_request.put("password", mima);
            js_request.put("url", myurl);
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
                Log.e("TAG", ">>>>成功" + result);
                SPUtils.put(WoDe_DengRu.this,"isLogin",true);
                if (isLogin==true) {
                    DengruData d_data = new Gson().fromJson(result, DengruData.class);
                    dengrufanhuizhi = d_data.getState(); //状态值
                    String  user_token=d_data.getResult().getToken();
                    int user_id=d_data.getResult().getUserid();
                    
                    if (dengrufanhuizhi.equals("success")) {
                        EventBus.getDefault().post(new User_data(shoujihao, dengrufanhuizhi,user_token,user_id));
                        EventBus.getDefault().post(new User_id(user_id));
                        finish();
                    }


                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                if (ex instanceof HttpException) { // 网络错误  
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    Log.e("TAG", ">>>>" + responseMsg);
                    Log.e("TAG", ">>>>" + errorResult);
                    // ...  
                } else { // 其他错误  
                    // ...  
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }



    private void getDengruId() {
        fanhui_dengru = findViewById(R.id.dengrufanhui);//返回键
        zhuche_dengru = findViewById(R.id.zhuce_dengrupage);//注册页面的跳转
        zhaohuimima = findViewById(R.id.zhaohuimima);//找回密码页面的跳转
        dengru_phone = findViewById(R.id.dengru_Phone);//用户登入的手机号
        dengru_mima = findViewById(R.id.dengru_mima);//用户登入的密码
        dengrujoin = findViewById(R.id.dengru_goin);//登入
        dengru_guanbi=findViewById(R.id.dengru_guanbi);//清除账号
        dr_togglePwd=findViewById(R.id.dr_togglePwd);//显示与隐藏密码
    }
    public synchronized String getid(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String ID= TelephonyMgr.getDeviceId();
        return ID;
    }

  
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}