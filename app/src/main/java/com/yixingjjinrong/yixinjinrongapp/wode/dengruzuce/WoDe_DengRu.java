package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.yixingjjinrong.yixinjinrongapp.HandUtils.GestureVerifyActivity;
import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.MaxLengthWatcher;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_id;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DengruData;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyConten;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.HideIMEUtil;
import com.yixingjjinrong.yixinjinrongapp.utils.PermissionHelper;
import com.yixingjjinrong.yixinjinrongapp.utils.PermissionInterface;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class WoDe_DengRu extends AutoLayoutActivity implements PermissionInterface {
    private ImageView fanhui_dengru, dengru_guanbi;//返回键
    private TextView zhuche_dengru, zhaohuimima,jg_text;//注册页面的跳转,找回密码页面的跳转,警告
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
//    private View dr_jg,shojihao_kong;
    private ImageView dr_yj_image;
    private PromptDialog promptDialog;
    public static WoDe_DengRu instance;
    private AutoRelativeLayout main;
    private ScrollView dr_scrollView;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
        setContentView(R.layout.activity_wo_de__deng_ru);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
                .init();
//        HideIMEUtil.wrap(this);//键盘管理，点击除editText外区域收起键盘
        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();
        isLogin = (boolean) SPUtils.get(this, "isLogin", false);
        getDengruId();
        getdengruOnClick();


    }

    public static boolean isMobileNo(String mobiles) {
        /*
         * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、184、187、188、147
         * 联通号码段:130、131、132、185、186、145、171/176/175
         * 电信号码段:133、153、180、181、189、173、177
         */
        String telRegex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([1-3]|[5-9]))|(18[0-9]))\\d{8}$";
        /**
         * (13[0-9])代表13号段 130-139
         * (14[5|7])代表14号段 145、147
         * (15([0-3]|[5-9]))代表15号段 150-153 155-159
         * (17([1-3][5-8]))代表17号段 171-173 175-179 虚拟运营商170屏蔽
         * (18[0-9]))代表18号段 180-189
         * d{8}代表后面可以是0-9的数字，有8位
         */
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }
    private void getdengruOnClick() {
        dengru_mima.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                changeScrollView();
                return false;
            }
        });
        dengru_phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                changeScrollView();
                return false;
            }
        });


        dengru_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0) {
                    dengru_guanbi.setVisibility(View.VISIBLE);
                }else {
                    dengru_guanbi.setVisibility(View.GONE);
                }
//                if (dengru_phone.getText().toString().isEmpty()){
//                    shojihao_kong.setVisibility(View.VISIBLE);
//                    jg_text.setText("手机号不能为空");
//                }else {
////                    shojihao_kong.setVisibility(View.GONE);
//                    if (isMobileNo(dengru_phone.getText().toString())==false) {
//                        shojihao_kong.setVisibility(View.VISIBLE);
//                        jg_text.setText("手机号格式不正确");
//                    } else if (isMobileNo(dengru_phone.getText().toString())==false) {
//                        shojihao_kong.setVisibility(View.VISIBLE);
//                        jg_text.setText("手机号格式不正确");
//                    }else {
//                        shojihao_kong.setVisibility(View.GONE);
//                    }
//                }



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dengru_mima.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length()>0) {
                    dr_yj_image.setVisibility(View.VISIBLE);
                }else {
                    dr_yj_image.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        zhuche_dengru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_dengru = new Intent(WoDe_DengRu.this, ZhuCe_PaGa.class);
                startActivity(intent_dengru);
//                finish();
            }
        });
        zhaohuimima.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent_Zhaohuimima = new Intent(WoDe_DengRu.this, ZhaoHuiMiMa.class);
                startActivity(intent_Zhaohuimima);
//                finish();
            }
        });
        dengrujoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoujihao = dengru_phone.getText().toString();
                mima = dengru_mima.getText().toString();
                if (shoujihao.isEmpty()) {
//                    ToastUtils.showToast(WoDe_DengRu.this, "手机号不能为空");
//                    shojihao_kong.setVisibility(View.VISIBLE);
//                    jg_text.setText("手机号不能为空");
                    ToastUtils.showToast(WoDe_DengRu.this, "手机号不能为空");
                } else if ( mima.isEmpty()){
//                    Toast.makeText(WoDe_DengRu.this, "成功", Toast.LENGTH_SHORT).show();
                    ToastUtils.showToast(WoDe_DengRu.this, "密码不能为空");
                }else {
                    if (isMobileNo(dengru_phone.getText().toString())==false) {
//                        shojihao_kong.setVisibility(View.VISIBLE);
                        ToastUtils.showToast(WoDe_DengRu.this, "手机号格式不正确");
                    } else if (isMobileNo(dengru_phone.getText().toString())==false) {
//                        shojihao_kong.setVisibility(View.VISIBLE);
                        ToastUtils.showToast(WoDe_DengRu.this, "手机号格式不正确");
                    }else {
                        getHttp();
                    }
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
                    dr_yj_image.setImageDrawable(getResources().getDrawable(R.drawable.xianshi));
                } else {
                    //否则隐藏密码
                    dengru_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    dr_yj_image.setImageDrawable(getResources().getDrawable(R.drawable.buxianshi));
                }
            }
        });

    }

    private void changeScrollView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dr_scrollView.scrollTo(0, dr_scrollView.getHeight());
            }
        }, 200);

    }

    private void getHttp() {
        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
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
                        promptDialog.dismiss();
                        ToastUtils.showToast(WoDe_DengRu.this, "网络连接超时，请稍后再试");
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e(">>>>登入", "" + result);
//                SPUtils.put(WoDe_DengRu.this,"isLogin",true);
//                if (isLogin==true) {
                        DengruData d_data = new Gson().fromJson(result, DengruData.class);
                        Log.e("登入Message", "" + message);

                        if (d_data.getMessage().equals("登录成功")) {
//                            dr_jg.setVisibility(View.GONE);
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
                            SPUtils.put(WoDe_DengRu.this, "shoujihao", shoujihao);
                            SPUtils.put(WoDe_DengRu.this, "inviterId", d_data.getResult().getInviterId());
                            Log.e("sdfdf", "" + loginId);
                            promptDialog.dismiss();
                            if (type!=null) {
                                if (type.equals("1")) {
                                    Intent it = new Intent(WoDe_DengRu.this, MainActivity.class);
                                    it.putExtra("id", "2");
                                    startActivity(it);
                                    finish();
                                } else {
                                    finish();
                                }
                            }else {
                                finish();
                            }

                        } else {
                            promptDialog.dismiss();
//                            dr_jg.setVisibility(View.VISIBLE);
//                            jg_text.setText(""+d_data.getMessage());
                            ToastUtils.showToast(WoDe_DengRu.this, d_data.getMessage());
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
        dr_togglePwd = findViewById(R.id.dr_togglePwd);//显示与隐藏密码按钮
//        dr_jg=findViewById(R.id.dr_jg);//警告
        jg_text=findViewById(R.id.jg_text);//警告text
        dengru_phone.setInputType( InputType.TYPE_CLASS_NUMBER);//数字键盘
        dr_yj_image=findViewById(R.id.dr_yj_image);//显示与隐藏密码图片
//        shojihao_kong=findViewById(R.id.shojihao_kong);//手机号为空判断
        dengru_phone.addTextChangedListener(new MaxLengthWatcher(11, dengru_phone));//手机号长度限制
        main=findViewById(R.id.main);
        dr_scrollView=findViewById(R.id.dr_scrollview);
        Intent it=getIntent();
        type = it.getStringExtra("type");
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
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }
}
