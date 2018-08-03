package com.yixingjjinrong.yixinjinrongapp.wode.shezhi;

import android.content.Intent;
import android.os.Bundle;
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
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiuGaiMiMa_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ZhaoHuiMiMa;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ZhaoHuiMiMaYanZheng;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Pattern;

public class XiuGaiMiMa extends AutoLayoutActivity {
    private ImageView re_fenhui;//返回
    private EditText old_mima,new_mima;
    private Button re_password;
    private ToggleButton xiugai_togglePwd;//,隐藏密码
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private TextView xg_mima;
    public static final String REGEX_PASSWORD = "^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$";
    private int user_ird;
    private String loginid;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_xiugai_mima);
        getre_id();
        Bundle b = getIntent().getExtras();
        user_ird = b.getInt("user_ird");
        getoncolik();
        xg_mima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_Zhaohuimima = new Intent(XiuGaiMiMa.this, ZhaoHuiMiMa.class);
                startActivity(intent_Zhaohuimima);
            }
        });
        re_fenhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getoncolik() {
        re_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lenght = new_mima.getText().toString().trim().length();
//                Toast.makeText(YanZheng_PaGa.this,"6:"+isPassword(user_mima.getText().toString()),Toast.LENGTH_SHORT).show();
                if (lenght<6||lenght>18 || !isPassword(new_mima.getText().toString())){
                    Toast.makeText(XiuGaiMiMa.this,"6-18位字母和数字组合",Toast.LENGTH_SHORT).show();
                }else{
                    gethttp_repassword();

                }
            }
        });
        xiugai_togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    new_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    xiugai_togglePwd.setBackground(getResources().getDrawable(R.drawable.xianshi));
                } else {
                    //否则隐藏密码
                    new_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    xiugai_togglePwd.setBackground(getResources().getDrawable(R.drawable.buxianshi));
                }
            }
        });

    }

    private void gethttp_repassword() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("password", old_mima.getText().toString());
            js_request.put("newpwd", new_mima.getText().toString());
            js_request.put("userid", user_ird);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
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
        RequestParams params = new RequestParams(Urls.BASE_URL+"yxbApp/updatePwd.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("=反回的GSON",""+result );
                XiuGaiMiMa_Gson data = new Gson().fromJson(result, XiuGaiMiMa_Gson.class);
                String token = data.getResult().getToken();
                String state = data.getState();
                Toast.makeText(XiuGaiMiMa.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
                if (state.equals("success")){
//                    EventBus.getDefault().post(new User_data("", "",token,Integer.parseInt("")));
                    Toast.makeText(XiuGaiMiMa.this, "修改成功,请重新登入", Toast.LENGTH_SHORT).show();
                    Intent it=new Intent(XiuGaiMiMa.this,WoDe_DengRu.class);
                    startActivity(it);
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

    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    private void getre_id() {
        loginid = (String) SPUtils.get(XiuGaiMiMa.this, "Loginid", "");
        token = (String) SPUtils.get(XiuGaiMiMa.this, "Token1", "");
        re_fenhui=findViewById(R.id.re_fenhui);
        xiugai_togglePwd=findViewById(R.id.xiugai_togglePwd);
        old_mima=findViewById(R.id.old_mima);
        new_mima=findViewById(R.id.new_mima);
        re_password=findViewById(R.id.re_password);
        xg_mima=findViewById(R.id.xg_mima);
    }
}
