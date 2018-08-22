package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
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
import com.yixingjjinrong.yixinjinrongapp.application.MaxLengthWatcher;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ChengGongzhuce_Gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.YanZhengMa_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.PermissionHelper;
import com.yixingjjinrong.yixinjinrongapp.utils.PermissionInterface;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class YanZheng_PaGa extends AutoLayoutActivity implements PermissionInterface {
    private Button yanzheng_zhuce, huoqu_yanzhengma;
    private ToggleButton zc_togglePwd;
    private TextView huode_phone;
    private String get_phone;
    private EditText phonecode, user_mima, user_yaoqingren;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private ImageView et_qc;//清除输入框
    private PermissionHelper mPermissionHelper;//动态申请权限
    public static final String REGEX_PASSWORD = "^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$";
    private TimeCount time;
    private Context context;
    private String myurl;
    private ImageView zz_fh;
    private TextView yy_yanzheng;
    private String message;
    private String jsessionId;
    private PromptDialog promptDialog;
    private ImageView zc_yj_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_yanzheng__pa_ga);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .keyboardEnable(true)
                .init();
        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();

        getyanZheng_Id();
        getyanZheng_Onclick();

//        huoqu_yanzhengma.setEnabled(true);
    }

    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    private void getyanZheng_Onclick() {
        yy_yanzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getyuyinHttp();
            }
        });

        zz_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        yanzheng_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int lenght = user_mima.getText().toString().trim().length();
//                Toast.makeText(YanZheng_PaGa.this,"6:"+isPassword(user_mima.getText().toString()),Toast.LENGTH_SHORT).show();
                if (user_mima.getText().toString().equals("")) {
                    Toast.makeText(YanZheng_PaGa.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (lenght < 6 || lenght > 18 || !isPassword(user_mima.getText().toString())) {

                        Toast.makeText(YanZheng_PaGa.this, "6-18位字母和数字组合", Toast.LENGTH_SHORT).show();
                    } else {
                        gethttp_zhuce();

                    }
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

                getHttP_YAnzhengMA();

            }
        });


        zc_togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    user_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    zc_yj_image.setImageDrawable(getResources().getDrawable(R.drawable.xianshi));
                } else {
                    //否则隐藏密码
                    user_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    zc_yj_image.setImageDrawable(getResources().getDrawable(R.drawable.buxianshi));
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

    public static String getIP(Context context) {

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void getyuyinHttp() {//语音验证码
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        String ip = getIP(YanZheng_PaGa.this);
        myurl = getid(context);
        try {
            js_request.put("phone", get_phone);
            js_request.put("ip", ip);

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
                .url(Urls.BASE_URL + "yxbApp/sendsoundSms.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("语音严重吗Gson", "" + response);
                        ToastUtils.showToast(YanZheng_PaGa.this, "成功");
                    }
                });

    }

    private void gethttp_zhuce() {
        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        myurl = getid(context);
        Log.e("唯一标识", "" + myurl);
        try {
            js_request.put("phone", get_phone);
            js_request.put("password", user_mima.getText().toString());
            js_request.put("phonecode", phonecode.getText().toString());
            js_request.put("address", "1");
            js_request.put("phonemap", user_yaoqingren.getText().toString());
            js_request.put("url", myurl);
            Log.e("祖册参数", "" + js_request);
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
                .url(Urls.BASE_URL + "yxbApp/registerApp.do?")
                .content(canshu.toString())
                .addHeader("Cookie", "JSESSIONID=" + jsessionId)
                // js_request.put("jsessionId", jsessionId);
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                        ToastUtils.showToast(YanZheng_PaGa.this,"网络错误，请稍后再试" );
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("TAG", ">>>>z成功" + result);
                        ChengGongzhuce_Gson date = new Gson().fromJson(result, ChengGongzhuce_Gson.class);
                        if (date.getMessage().equals("注册成功!")) {
                            int userid = date.getResult().getUserid();
                            Toast.makeText(YanZheng_PaGa.this, "" + date.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent_dengru = new Intent(YanZheng_PaGa.this, ChengGongZhuCe.class);
                            intent_dengru.putExtra("Phone_my", get_phone);
                            intent_dengru.putExtra("logid", date.getResult().getLoginId());
                            intent_dengru.putExtra("token", date.getResult().getToken());
                            intent_dengru.putExtra("password", user_mima.getText().toString());
                            SPUtils.put(YanZheng_PaGa.this, "Loginid", date.getResult().getLoginId());
                            SPUtils.put(YanZheng_PaGa.this, "Token1", date.getResult().getToken());
                            intent_dengru.putExtra("url", myurl);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("user_id", userid);
                            Log.e("验证注册：", "Phone_my:" + get_phone + "__password:" + user_mima.getText().toString() + "__url:" + myurl);
                            intent_dengru.putExtras(bundle);
                            startActivity(intent_dengru);
                            promptDialog.dismiss();

                        } else {
                            promptDialog.dismiss();
                            Toast.makeText(YanZheng_PaGa.this, "" + date.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void getHttP_YAnzhengMA() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("phone", get_phone);
            js_request.put("type", 4);
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
                .url(Urls.BASE_URL + "yxbApp/sendsms.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("TAG", ">>>>成功" + result);

                        YanZhengMa_gson data = new Gson().fromJson(result, YanZhengMa_gson.class);
                        if (data.getMessage().equals("发送短信成功")) {

                            message = data.getMessage();
                            jsessionId = data.getResult().getJsessionId();
                            Log.e("jsessionId",""+ jsessionId);
                            time = new TimeCount(60000, 1000);
                            time.start();
                        } else {
                            Toast.makeText(YanZheng_PaGa.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                            if (data.getMessage().equals("验证码发送次数达到上限，请明天再试")) {
                                huoqu_yanzhengma.setText("明天再试");
                                huoqu_yanzhengma.setBackgroundResource(R.drawable.bt_shape);
                            }
                        }
                    }
                });
    }

    private void getyanZheng_Id() {
        yanzheng_zhuce = findViewById(R.id.yanzheng_zhuce);
        huode_phone = findViewById(R.id.huode_Phone);//获得手机号
        huoqu_yanzhengma = findViewById(R.id.huoqu_yanzhengma);//获取验证码
        phonecode = findViewById(R.id.edit_phonecode);//手机验证码
        user_mima = findViewById(R.id.user_mima);//用户密码
        user_yaoqingren = findViewById(R.id.yaoqingren);//yaoqingren
        zc_togglePwd = findViewById(R.id.zc_togglePwd);//显示或隐藏密码
        et_qc = findViewById(R.id.yanz_guanbi);//清除输入框
        zz_fh = findViewById(R.id.zz_fh);
        yy_yanzheng = findViewById(R.id.yy_yanzheng);
        phonecode.addTextChangedListener(new MaxLengthWatcher(6, phonecode));
        user_mima.addTextChangedListener(new MaxLengthWatcher(18, user_mima));
        zc_yj_image=findViewById(R.id.zc_yj_image);
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
        finish();
    }

    private void initViews() {
        //已经拥有所需权限，可以放心操作任何东西了
//        Toast.makeText(this, "已经拥有所需权限，可以放心操作任何东西了", Toast.LENGTH_SHORT).show();

    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            huoqu_yanzhengma.setBackgroundColor(Color.parseColor("#cccccc"));
            huoqu_yanzhengma.setBackgroundResource(R.drawable.bt_huise);
            huoqu_yanzhengma.setClickable(false);
            huoqu_yanzhengma.setText("再次发送(" + millisUntilFinished / 1000 + ") ");
        }

        @Override
        public void onFinish() {
            huoqu_yanzhengma.setText("再次发送");
            huoqu_yanzhengma.setClickable(true);
            huoqu_yanzhengma.setBackgroundResource(R.drawable.bt_shape);

        }
    }
}
