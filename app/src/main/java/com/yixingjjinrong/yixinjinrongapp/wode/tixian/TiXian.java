package com.yixingjjinrong.yixinjinrongapp.wode.tixian;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CunGuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.TiXianOk_GSON;
import com.yixingjjinrong.yixinjinrongapp.gsondata.TiXian_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.HideIMEUtil;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.KUaiJieZhiFu;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.YinHangCunGuan;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ZhaoHuiMiMa;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.shiming.WoDeShiMing;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class TiXian extends AutoLayoutActivity {
    private ImageView t_yh_img;
    private TextView t_yh_name, t_yh_number, t_cz_keyong;
    private EditText t_cz_money;
    private Button cz_ok;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private String keyong;
    private String loginid;
    private String token;
    private ImageView tx_fh;
    private View kftelephone;
    private static final int PERMISSION_REQUESTCODE = 1;
    private View tixin_kongbai;
    private PromptDialog promptDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {               //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));//需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_ti_xian);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        HideIMEUtil.wrap(this);//键盘管理，点击除editText外区域收起键盘
        getID();
//        gethttp();
        tx_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hintKbTwo();
                finish();
            }
        });
        kftelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoualertdialog();
            }
        });
    }

    private void shoualertdialog() {
        //自定义AlertDialog
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.krfuphone, null);
        Button btn_qux = view1.findViewById(R.id.btn_qux);
        Button btn_hujiao = view1.findViewById(R.id.btn_hujiao);

        final AlertDialog dialog = new AlertDialog.Builder(TiXian.this)
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
        if (ContextCompat.checkSelfPermission(TiXian.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //没有授权
            ActivityCompat.requestPermissions(TiXian.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUESTCODE);
        } else {
            //已经授权
            diallPhone("4001838818");
        }
    }

    private void diallPhone(String s) {
        Intent tent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + s);
        tent.setData(data);
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
        startActivity(tent);
    }

    private void gethttp() {
        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());

            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/withdrawInitMobilefApp.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        promptDialog.dismiss();
                        Log.e("提现GSon", result);
                        final TiXian_Gson data = new Gson().fromJson(result, TiXian_Gson.class);
                        String msg = data.getMsg();
                        if (msg.equals("")) {
                            tixin_kongbai.setVisibility(View.GONE);
                            t_yh_name.setText(data.getBankName());
                            t_yh_number.setText(data.getCardNum());
                            x.image().bind(t_yh_img, data.getImage());
                            data.getFreeMoney();
                            t_cz_keyong.setText("可提现金额:  " + data.getFreeMoney() + "元");
                            cz_ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {//可以提现，下一步
                                    if (t_cz_money.getText().toString().equals("")) {
                                        ToastUtils.showToast(TiXian.this, "提现金额不为空");
                                    } else {
                                        if (Integer.valueOf(t_cz_money.getText().toString().trim()) < 100) {//金额不能小于100
                                            ToastUtils.showToast(TiXian.this, "提现金额不能小于100元");
                                        } else {
                                            getokHTTp();
                                        }
                                    }

                                }
                            });
                        } else {
                            tixin_kongbai.setVisibility(View.VISIBLE);
//                    t_yhcard.setVisibility(View.GONE);//影藏布局
                            if (msg.equals("auth")) {
                                AlertDialog dialog1 = new AlertDialog.Builder(TiXian.this)
                                        .setTitle("提示")
                                        .setMessage("您还未实名认证，是否实名认证")
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        })
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                getshimingHTTp();

                                            }
                                        })
                                        .create();
                                dialog1.setCanceledOnTouchOutside(false);
                                dialog1.show();

                            }
                            if (msg.equals("bank_link")) {
                                AlertDialog dialog1 = new AlertDialog.Builder(TiXian.this)
                                        .setTitle("提示")
                                        .setMessage("您还未开通银行存管，是否开通")
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        })
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                getchHTTP();

                                            }
                                        })
                                        .create();
                                dialog1.setCanceledOnTouchOutside(false);
                                dialog1.show();

                            }
                            if (msg.equals("sign_card")) {
                                AlertDialog dialog3 = new AlertDialog.Builder(TiXian.this)
                                        .setTitle("提示")
                                        .setMessage("您还未没有签约")
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        })
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent it = new Intent(TiXian.this, KUaiJieZhiFu.class);
                                                startActivity(it);

                                            }
                                        })
                                        .create();
                                dialog3.setCanceledOnTouchOutside(false);
                                dialog3.show();

                            }


                        }
                    }
                });

    }

    private void getokHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("money", t_cz_money.getText().toString());
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            Log.e("提现的金额", "" + js_request);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());

            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/addWithdrawInfoMobilefApp.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("ok提现：", result);
                        TiXianOk_GSON data = new Gson().fromJson(result, TiXianOk_GSON.class);
                        if (data.getMessage().equals("提现成功！")) {
                            String html = data.getHtml();
                            Intent itcz = new Intent(TiXian.this, TiXian_OK.class);
                            itcz.putExtra("tixianhtml", html);
                            Log.e("提现HTML!:", "" + html.toString());
                            startActivity(itcz);
                            finish();
                        } else {
                            ToastUtils.showToast(TiXian.this, "" + data.getMessage());
                        }
                    }
                });

    }

    private void getshimingHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
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
                .url(Urls.BASE_URL + "yxbApp/queryUserAuthInfo.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("是否可实名GSON：", result);
                        ShiFouKeShiMing_gson data = new Gson().fromJson(result, ShiFouKeShiMing_gson.class);
                        String message = data.getMessage().toString();
                        String jieguo = data.getState().toString();
                        if (jieguo.equals("success")) {
                            Intent it = new Intent(TiXian.this, WoDeShiMing.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("user_ird", user_id);
                            it.putExtras(bundle);
                            startActivity(it);
                        }else {
                            if (message.equals("认证失败！您今日的认证次数已达上限，请明天再进行认证！")){
                                AlertDialog dialog1 = new AlertDialog.Builder(TiXian.this)
                                        .setTitle("提示")
                                        .setMessage("认证失败！您今日的认证次数已达上限，请明天再进行认证！")
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        })
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();

                                            }
                                        })
                                        .create();
                                dialog1.setCanceledOnTouchOutside(false);
                                dialog1.show();
                            }else {
                                ToastUtils.showToast(TiXian.this, message);
                            }

                        }
                    }
                });
    }

    private void getchHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userid", String.valueOf(user_id));
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());

            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/accountReg.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("存管GSON:", "" + result);
                        CunGuan_gson data = new Gson().fromJson(result, CunGuan_gson.class);
                        String html = data.getResult().getHtml();
                        Intent it = new Intent(TiXian.this, YinHangCunGuan.class);
                        it.putExtra("HTML", html);
                        Log.e("我的页面银行存管HTML:", "" + it);
                        startActivity(it);

                        Log.e("wangy", "" + html);
                    }
                });
    }

    //此方法只是关闭软键盘 可以在finish之前调用一下
    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    private void getID() {
        Intent itzc = getIntent();
        user_id = (int) SPUtils.get(this, "userId", 0);
        loginid = (String) SPUtils.get(TiXian.this, "Loginid", "");
        token = (String) SPUtils.get(TiXian.this, "Token1", "");
        keyong = itzc.getStringExtra("keyong2");
        Log.e("提现----》", "" + keyong);
        t_yh_img = findViewById(R.id.t_yh_img);
        t_yh_name = findViewById(R.id.t_yh_name);
        t_yh_number = findViewById(R.id.t_yh_number);
        t_cz_keyong = findViewById(R.id.t_cz_keyong);
        t_cz_money = findViewById(R.id.t_cz_money);
        cz_ok = findViewById(R.id.cz_ok);
        tx_fh = findViewById(R.id.tx_fh);
        kftelephone = findViewById(R.id.kftelephone);
        tixin_kongbai = findViewById(R.id.tixin_kongbai);

    }

    @Override
    protected void onResume() {
        super.onResume();
        gethttp();
    }
}
