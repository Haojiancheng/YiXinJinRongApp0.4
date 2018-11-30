package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TabHost;
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
import com.yixingjjinrong.yixinjinrongapp.utils.HideIMEUtil;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Pattern;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class ZhaoHuiMiMaYanZheng extends AutoLayoutActivity {
    private Button queding_zhaohui, zhaohui_yanzheng;//找回密码——确定
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private String phone;
    private TimeCount time;
    private TextView phonet;
    private EditText ed_code, news_mima;
    private ImageView et_qc;
    private ToggleButton zh_togglePwd;
    public static final String REGEX_PASSWORD = "^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$";
    private ImageView zhyz_fh;
    private String message;
    private String jsessionId;
    private ImageView zhyz_yj_image;
    private String timer;
    private View zhyz_collphone;
    private static final int PERMISSION_REQUESTCODE = 1;
    private PromptDialog promptDialog;


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
        HideIMEUtil.wrap(this);//键盘管理，点击除editText外区域收起键盘

        getzhaohuimima_Onclik();
    }

    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    private void getzhaohuimima_Onclik() {
        zhyz_collphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoualertdialog();
            }
        });

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
        news_mima.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    zhyz_yj_image.setVisibility(View.VISIBLE);
                } else {
                    zhyz_yj_image.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        queding_zhaohui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lenght = news_mima.getText().toString().trim().length();
                if (ed_code.getText().toString().isEmpty()) {
//                    jinggao.setVisibility(View.VISIBLE);
                    ToastUtils.showToast(ZhaoHuiMiMaYanZheng.this, "请输入验证码");
//                    jinggao.setText("   请输入验证码");
                } else {
                    if (news_mima.getText().toString().isEmpty()) {
//                        jinggao.setVisibility(View.VISIBLE);
//                        jinggao.setText("   请输入新密码");
                        ToastUtils.showToast(ZhaoHuiMiMaYanZheng.this, "请输入新密码");
                    } else {
                        if (lenght < 6 || lenght > 18) {
//                            jinggao.setVisibility(View.VISIBLE);
//                            jinggao.setText("   请输入6-18位字母和数字组合");
                            ToastUtils.showToast(ZhaoHuiMiMaYanZheng.this, "请输入6-18位字母和数字组合");
//                    Toast.makeText(ZhaoHuiMiMaYanZheng.this,"6-18位字母和数字组合",Toast.LENGTH_SHORT).show();
                        } else {
                            getyanzhengHttp();
                        }
                    }
                }
            }
        });
        zh_togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    news_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    zhyz_yj_image.setImageDrawable(getResources().getDrawable(R.drawable.xianshi));
                } else {
                    //否则隐藏密码
                    news_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    zhyz_yj_image.setImageDrawable(getResources().getDrawable(R.drawable.buxianshi));
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

    private void shoualertdialog() {
        //自定义AlertDialog
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.krfuphone, null);
        Button btn_qux = view1.findViewById(R.id.btn_qux);
        Button btn_hujiao = view1.findViewById(R.id.btn_hujiao);

        final AlertDialog dialog = new AlertDialog.Builder(ZhaoHuiMiMaYanZheng.this)
                .setView(view1)
                .show();
//给AlertDialog设置4个圆角
//        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbg);
        dialog.setCanceledOnTouchOutside(false);
        btn_qux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_hujiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showToast(getActivity(),"hujiao" );
                permission();
                dialog.dismiss();
            }
        });

    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(ZhaoHuiMiMaYanZheng.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //没有授权
            ActivityCompat.requestPermissions(ZhaoHuiMiMaYanZheng.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUESTCODE);
        } else {
            //已经授权
            diallPhone("4001838818");
        }
    }

    private void diallPhone(String s) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + s);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    private void getyanzhengHttp() {
        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("phone", phone);
            js_request.put("password", news_mima.getText().toString());
            js_request.put("code", ed_code.getText().toString());
            MyLog.e("找回密码", "" + js_request);
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
                .url(Urls.BASE_URL + "yxbApp/forgetPwd.do")
                .content(canshu.toString())
                .addHeader("Cookie", "JSESSIONID=" + jsessionId)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                        ToastUtils.showToast(ZhaoHuiMiMaYanZheng.this,"网络连接失败" );
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("找回密码的GSON", "" + result);
                        ChongZiMIMA_Gson data = new Gson().fromJson(result, ChongZiMIMA_Gson.class);
                        String message = data.getMessage().toString();
                        String zhuangtai = data.getState().toString();
                        promptDialog.dismiss();
                        if (zhuangtai.equals("success")) {

//                            ToastUtils.showToast(ZhaoHuiMiMaYanZheng.this, "" + message);
//                            LayoutInflater inflater = getLayoutInflater();
//                            View view1 = inflater.inflate(R.layout.xgch, null);
//                            final AlertDialog dialog = new AlertDialog.Builder(ZhaoHuiMiMaYanZheng.this)
//                                    .setView(view1)
//                                    .show();
//
//                            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//                            params.width = 300;
//                            params.height = 300 ;
//                            dialog.getWindow().setAttributes(params);
//                                    //给AlertDialog设置4个圆角
//                                    //dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbg);
//                            dialog.setCanceledOnTouchOutside(false);
                            showpopwindow();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent it = new Intent(ZhaoHuiMiMaYanZheng.this, WoDe_DengRu.class);
                                    startActivity(it);
                                    ZhaoHuiMiMa.zhaoHuiMiMa.finish();
                                    WoDe_DengRu.instance.finish();
                                    finish();
                                }
                            }, 1000);


                        } else {
                            ToastUtils.showToast(ZhaoHuiMiMaYanZheng.this, message);
//
                        }
                    }
                });

    }

    @SuppressLint("WrongConstant")
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

    private void getHttP_YAnzhengMA() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("phone", phone);
            js_request.put("type", 3);
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
                .url(Urls.BASE_URL + "yxbApp/sendsms.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("TAG", ">>>>成功" + response);
                        YanZhengMa_gson data = new Gson().fromJson(response, YanZhengMa_gson.class);
                        if (data.getMessage().equals("发送短信成功")) {

                            message = data.getMessage();
                            jsessionId = data.getResult().getJsessionId();
                            MyLog.e("jsessionId", "" + jsessionId);
                            time = new TimeCount(60000, 1000);
                            time.start();
                        } else {
                            Toast.makeText(ZhaoHuiMiMaYanZheng.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                            if (data.getMessage().equals("验证码发送次数达到上限，请明天再试")) {
                                zhaohui_yanzheng.setText("明天再试");
                                zhaohui_yanzheng.setBackgroundResource(R.color.gray);
                            }
                        }
                    }
                });

    }

    private void getzhaohuimima_Id() {
        queding_zhaohui = findViewById(R.id.zhaohuimiam_queding);//找回密码——确定
        zhaohui_yanzheng = findViewById(R.id.zhaohui_yanzheng);
        phonet = findViewById(R.id.phonet);
        ed_code = findViewById(R.id.edit_code);
        news_mima = findViewById(R.id.news_mima);
        zh_togglePwd = findViewById(R.id.zh_togglePwd);
        et_qc = findViewById(R.id.zh_guanbi);
//        jinggao=findViewById(R.id.jingdao);
        zhyz_fh = findViewById(R.id.zhyz_fh);
        zhyz_yj_image = findViewById(R.id.zhyz_yj_image);
        zhyz_collphone = findViewById(R.id.zhyz_collphone);
        ed_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    et_qc.setVisibility(View.VISIBLE);
                } else {
                    et_qc.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Intent it = getIntent();
        if (it != null) {
            phone = it.getStringExtra("phone");
            timer = it.getStringExtra("timer");
            jsessionId = it.getStringExtra("jsessionId");
            String mobile = phone;
            String maskNumber = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
            phonet.setText("正在为" + maskNumber + "找回登录密码");
            if (timer.equals("1")) {
                time = new TimeCount(60000, 1000);
                time.start();
            }
        }

    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            huoqu_yanzhengma.setBackgroundColor(Color.parseColor("#B6B6D8"));
            zhaohui_yanzheng.setClickable(false);
            zhaohui_yanzheng.setBackgroundResource(R.drawable.bt_huise);
            zhaohui_yanzheng.setText(millisUntilFinished / 1000 + "s后重新获取");
        }

        @Override
        public void onFinish() {
            zhaohui_yanzheng.setText("再次获取");
            zhaohui_yanzheng.setClickable(true);
            zhaohui_yanzheng.setBackgroundResource(R.drawable.bt_shape);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }
}
