package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_id;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DengruData;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyConten;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.PermissionHelper;
import com.yixingjjinrong.yixinjinrongapp.utils.PermissionInterface;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import okhttp3.Call;
import okhttp3.MediaType;

public class WoDe_DengRu extends AutoLayoutActivity implements PermissionInterface {
    private ImageView fanhui_dengru, dengru_guanbi;//返回键
    private TextView zhuche_dengru, zhaohuimima;//注册页面的跳转,找回密码页面的跳转
    private TextInputEditText dengru_phone, dengru_mima;//用户登入的手机号,用户登入的密码
    private Button dengrujoin;//登入
    private String shoujihao;
    private String mima;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private String dengrufanhuizhi;
    private boolean isLogin;
    private ToggleButton dr_togglePwd;//显示与隐藏密码
    private Context context;
    private PermissionHelper mPermissionHelper;//动态申请权限
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_wo_de__deng_ru);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .keyboardEnable(true)
                .init();
        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();
        isLogin = (boolean) SPUtils.get(this, "isLogin", false);
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
                    Toast.makeText(WoDe_DengRu.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
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

        String myurl = getid(context);
//        Log.e("唯一标识", "" + myurl);

        try {
            js_request.put("username", shoujihao);
            js_request.put("password", mima);
            js_request.put("url", myurl);

            base1 = Base64JiaMI.AES_Encode(js_request.toString());
//            Log.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
//            Log.e("TAG", ">>>>SH!!" + sha1);
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
                .url(Urls.BASE_URL + "yxbApp/Applogin.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("我的登入", "" + e);
                        Toast.makeText(WoDe_DengRu.this, "网络连接超时，请稍后再试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e(">>>>登入", "" + result);
//                SPUtils.put(WoDe_DengRu.this,"isLogin",true);
//                if (isLogin==true) {
                        DengruData d_data = new Gson().fromJson(result, DengruData.class);
                        Log.e("登入Message", "" + message);

                        if (d_data.getMessage().equals("登录成功")) {
                            dengrufanhuizhi = d_data.getState(); //状态值
                            message = d_data.getMessage();
                            String user_token = d_data.getResult().getToken();
                            String loginId = d_data.getResult().getLoginId();
                            int user_id = d_data.getResult().getUserid();
                            EventBus.getDefault().post(new User_data(shoujihao, dengrufanhuizhi, user_token, user_id, loginId));
                            EventBus.getDefault().post(new User_id(user_id));
                            SPUtils.put(WoDe_DengRu.this, "isLogin", true);
                            SPUtils.put(WoDe_DengRu.this, "Loginid", loginId);
                            SPUtils.put(WoDe_DengRu.this, "userId", user_id);
                            SPUtils.put(WoDe_DengRu.this, "Token1", user_token);
                            Log.e("sdfdf", "" + loginId);
                            finish();

                        } else {
                            Toast.makeText(WoDe_DengRu.this, "" + d_data.getMessage(), Toast.LENGTH_SHORT).show();
                        }

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
        dengru_guanbi = findViewById(R.id.dengru_guanbi);//清除账号
        dr_togglePwd = findViewById(R.id.dr_togglePwd);//显示与隐藏密码
    }

    public synchronized String getid(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String ID = TelephonyMgr.getDeviceId();
        return ID;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)) {
            //权限请求结果，并已经处理了该回调
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int getPermissionsRequestCode() {
        //设置权限请求requestCode，只有不跟onRequestPermissionsResult方法中的其他请求码冲突即可。
        return 10000;
    }

    @Override
    public String[] getPermissions() {
        //设置该界面所需的全部权限
        return new String[]{
                Manifest.permission.READ_PHONE_NUMBERS,
                Manifest.permission.READ_PHONE_STATE

        };
    }

    @Override
    public void requestPermissionsSuccess() {
        //权限请求用户已经全部允许
        initViews();
    }

    @Override
    public void requestPermissionsFail() {
        //权限请求不被用户允许。可以提示并退出或者提示权限的用途并重新发起权限申请。
//        finish();
    }

    private void initViews() {
        //已经拥有所需权限，可以放心操作任何东西了
//        Toast.makeText(this, "已经拥有所需权限，可以放心操作任何东西了", Toast.LENGTH_SHORT).show();

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
