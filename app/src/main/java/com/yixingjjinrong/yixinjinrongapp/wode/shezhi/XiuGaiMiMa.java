package com.yixingjjinrong.yixinjinrongapp.wode.shezhi;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiuGaiMiMa_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ZhaoHuiMiMa;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ZhaoHuiMiMaYanZheng;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.regex.Pattern;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

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
    private ImageView xgmm_yj_image,update_yuan;
    private PromptDialog promptDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_xiugai_mima);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
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
                if (lenght<6||lenght>18){
                    ToastUtils.showToast(XiuGaiMiMa.this,"请输入6-18位字母和数字组合");
                }else{
                    gethttp_repassword();

                }
            }
        });
        old_mima.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0) {
                    update_yuan.setVisibility(View.VISIBLE);
                }else {
                    update_yuan.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        update_yuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                old_mima.setText("");
            }
        });
        new_mima.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0) {
                    xgmm_yj_image.setVisibility(View.VISIBLE);
                }else {
                    xgmm_yj_image.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        xiugai_togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    new_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    xgmm_yj_image.setImageDrawable(getResources().getDrawable(R.drawable.xianshi));
                } else {
                    //否则隐藏密码
                    new_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    xgmm_yj_image.setImageDrawable(getResources().getDrawable(R.drawable.buxianshi));
                }
            }
        });

    }

    private void gethttp_repassword() {
        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("password", old_mima.getText().toString());
            js_request.put("newpwd", new_mima.getText().toString());
            js_request.put("userid", user_ird);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>SH!!" + sha1);
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
                .url(Urls.BASE_URL+"yxbApp/updatePwd.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                            promptDialog.dismiss();
                            ToastUtils.showToast(XiuGaiMiMa.this, "网络连接失败");
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        promptDialog.dismiss();
                        MyLog.e("=反回的GSON",""+result );
                        XiuGaiMiMa_Gson data = new Gson().fromJson(result, XiuGaiMiMa_Gson.class);
                        String token = data.getResult().getToken();
                        String state = data.getState();
//                        ToastUtils.showToast(XiuGaiMiMa.this, ""+data.getMessage());
                        if (state.equals("success")){
//                    EventBus.getDefault().post(new User_data("", "",token,Integer.parseInt("")));
//                            ToastUtils.showToast(XiuGaiMiMa.this, "修改成功,请重新登入");

                            showpopwindow();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent it=new Intent(XiuGaiMiMa.this,WoDe_DengRu.class);
                                    startActivity(it);
                                    WoDe_SheZhi.wodezhezhi.finish();
                                    finish();
                                }
                            }, 1000);

                        }else {
                            ToastUtils.showToast(XiuGaiMiMa.this, data.getMessage());
                        }
                    }
                });
    }

    private void showpopwindow() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        final View popView = View.inflate(this, R.layout.xgch, null);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow = new PopupWindow(popView, width, height);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);// 设置同意在外点击消失
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
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
        update_yuan=findViewById(R.id.update_yuan);
        re_password=findViewById(R.id.re_password);
        xg_mima=findViewById(R.id.xg_mima);
        xgmm_yj_image=findViewById(R.id.xgmm_yj_image);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }
}
