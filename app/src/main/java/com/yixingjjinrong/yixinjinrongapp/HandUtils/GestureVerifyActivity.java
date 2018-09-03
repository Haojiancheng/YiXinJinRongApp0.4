package com.yixingjjinrong.yixinjinrongapp.HandUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.A2bigA;
import com.yixingjjinrong.yixinjinrongapp.application.MaxLengthWatcher;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_id;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DengruData;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiSuccers;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class GestureVerifyActivity extends AutoLayoutActivity implements android.view.View.OnClickListener {
    /**
     * 手机号码
     */
    public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
    /**
     * 意图
     */
    public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
    private RelativeLayout mTopLayout;
    private TextView mTextTitle;
    private TextView mTextCancel;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private ImageView mImgUserLogo;
    private PopupWindow popview;
    private TextView mTextTip;
    private FrameLayout mGestureContainer;
    private GestureContentView mGestureContentView;
    private TextView mTextForget;
    private TextView mTextOther;
    private String mParamPhoneNumber;
    private long mExitTime = 0;
    private int mParamIntentCode;
    private String mFirstPassword;
    private String shezhi;
    private String phone;
    private EditText shmm_password;
    private String myphone;
    private String message;
    private String dengrufanhuizhi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_verify);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        ObtainExtraData();
        setUpViews();
        setUpListeners();
    }

    private void ObtainExtraData() {
        mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
        mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);
    }

    private void setUpViews() {
        phone = (String) SPUtils.get(this, "myphone", "");
        myphone = (String) SPUtils.get(this, "shoujihao", "");
        mTopLayout = (RelativeLayout) findViewById(R.id.top_layout);
        mTextTitle = (TextView) findViewById(R.id.text_title);
        mTextCancel = (TextView) findViewById(R.id.text_cancel);
        mImgUserLogo = (ImageView) findViewById(R.id.user_logo);
        mTextTip = (TextView) findViewById(R.id.text_tip);
        mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
        mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
        mTextOther = (TextView) findViewById(R.id.text_other_account);
        mFirstPassword = (String) SPUtils.get(GestureVerifyActivity.this, "mFirstPassword", "");
        Intent it = getIntent();
        shezhi = it.getStringExtra("shezhi");
        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, true, mFirstPassword,
                new GestureDrawline.GestureCallBack() {

                    @Override
                    public void onGestureCodeInput(String inputCode) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void checkedSuccess() {
                        mGestureContentView.clearDrawlineState(0L);
                        ToastUtils.showToast(GestureVerifyActivity.this, "密码正确");
                        if (shezhi.equals("1")) {
                            SPUtils.remove(GestureVerifyActivity.this, "ishand");
                            SPUtils.put(GestureVerifyActivity.this, "ishand", "2");
                            finish();
                        } else {
                            Intent intent = new Intent(GestureVerifyActivity.this, MainActivity.class);
                            startActivity(intent);
                            GestureVerifyActivity.this.finish();
                        }
                    }

                    @Override
                    public void checkedFail() {
                        int i = 5;
                        mGestureContentView.clearDrawlineState(1300L);
                        mTextTip.setVisibility(View.VISIBLE);
                        mTextTip.setText(Html
                                .fromHtml("<font color='#c70c1e'>密码错误</font>"));
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
                        mTextTip.startAnimation(shakeAnimation);
//                        for (int j = 0; j < i; j--) {
//                            checkedFail();
//                        }
                    }
                });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }

    private void setUpListeners() {
        mTextCancel.setOnClickListener(this);
        mTextForget.setOnClickListener(this);
        mTextOther.setOnClickListener(this);
    }

    private String getProtectedMobile(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(phoneNumber.subSequence(0, 3));
        builder.append("****");
        builder.append(phoneNumber.subSequence(7, 11));
        return builder.toString();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_cancel:
                this.finish();
                break;
            case R.id.text_forget_gesture:
                showpopwindow();
//                this.finish();
                break;
            case R.id.text_other_account:
                SPUtils.remove(this, "userId");
                SPUtils.put(this, "userId", 0);
                Intent intent = new Intent(this, WoDe_DengRu.class);
                startActivity(intent);
                this.finish();
                break;
            default:
                break;
        }
    }

    @SuppressLint("WrongConstant")
    private void showpopwindow() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View view = LayoutInflater.from(this).inflate(R.layout.shoushimimadengru, null);
        final TextView pop_name = view.findViewById(R.id.shaoshi_shoujihao);
        shmm_password = view.findViewById(R.id.shmm_password);
        Button shmm_puxia = view.findViewById(R.id.shmm_quxiao);
        Button shmm_queding =view.findViewById(R.id.shmm_queding);
//        final EditText pop_idcard = view.findViewById(R.id.pop_myidcard);
//        ImageView guanbil = view.findViewById(R.id.guanbil);
//        Button popb_t = view.findViewById(R.id.pop_myb_t);
        pop_name.setText(phone);


        popview = new PopupWindow(view,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popview.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popview.setFocusable(true);
        // 设置点击其他地方就消失
        popview.setOutsideTouchable(true);
        popview.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        shmm_puxia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popview.dismiss();
            }
        });
        shmm_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shmm_password.getText().toString().isEmpty()) {
//                    Toast.makeText(WoDe_DengRu.this, "成功", Toast.LENGTH_SHORT).show();
                    ToastUtils.showToast(GestureVerifyActivity.this, "密码不能为空");
                } else {
                    gethttp();
                }
            }

        });
        popview.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popview.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popview.showAtLocation(parent, Gravity.BOTTOM
                | Gravity.CENTER_VERTICAL, 0, 0);

    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象

        final String myurl = getid(this);
//        Log.e("唯一标识", "" + myurl);

        try {
            js_request.put("username", myphone);
            js_request.put("password", shmm_password.getText().toString());
            js_request.put("url", myurl);

            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>base加密11111!!--" + js_request.toString());
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
                        ToastUtils.showToast(GestureVerifyActivity.this, "网络连接超时，请稍后再试");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(">>>>登入", "" + response);
//                SPUtils.put(WoDe_DengRu.this,"isLogin",true);
//                if (isLogin==true) {
                        DengruData d_data = new Gson().fromJson(response, DengruData.class);
                        Log.e("登入Message", "" + message);

                        if (d_data.getMessage().equals("登录成功")) {
//                            dr_jg.setVisibility(View.GONE);
                            //状态值
                            dengrufanhuizhi = d_data.getState();
                            message = d_data.getMessage();
                            String user_token = d_data.getResult().getToken();
                            String loginId = d_data.getResult().getLoginId();
                            int user_id = d_data.getResult().getUserid();
                            EventBus.getDefault().post(new User_data(myphone, dengrufanhuizhi, user_token, user_id, loginId));
                            EventBus.getDefault().post(new User_id(user_id));
                            SPUtils.put(GestureVerifyActivity.this, "isLogin", true);
                            SPUtils.put(GestureVerifyActivity.this, "Loginid", loginId);
                            SPUtils.put(GestureVerifyActivity.this, "userId", user_id);
                            SPUtils.put(GestureVerifyActivity.this, "Token1", user_token);
                            SPUtils.put(GestureVerifyActivity.this, "shoujihao", myphone);
                            Log.e("sdfdf", "" + loginId);
                            Intent it=new Intent(GestureVerifyActivity.this, MainActivity.class);
                            it.putExtra("id","2");
                            startActivity(it);
                            finish();
                        } else {
                            ToastUtils.showToast(GestureVerifyActivity.this, d_data.getMessage());
                        }

                    }
                });

    }

    public synchronized String getid(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String ID = TelephonyMgr.getDeviceId();
        return ID;
    }

}
