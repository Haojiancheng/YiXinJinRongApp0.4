package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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
import com.yixingjjinrong.yixinjinrongapp.gsondata.ChongZhiOk_GSon;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CunGuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.Yinhangka_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.HideIMEUtil;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.YinHangCunGuan;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.shiming.WoDeShiMing;
import com.yixingjjinrong.yixinjinrongapp.wode.tixian.TiXian;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class ChongZhq extends AutoLayoutActivity {
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private TextView yh_name, yh_number, cz_keyong;
    private ImageView yh_img;
    private EditText cz_money;

    private int user_id;
    private Button cz_ok;
    private View yhcard;
    private String loginid;
    private String token;
    private ImageView cz_fh;
    private View kongbai;
    private PromptDialog promptDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_chong_zhq);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getczid();
        HideIMEUtil.wrap(this);//键盘管理，点击除editText外区域收起键盘
//        getczHTTp();
        cz_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintKbTwo();
                finish();
            }
        });

    }


    private void getczHTTp() {
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
                .url(Urls.BASE_URL + "yxbApp/fuRechgeInitMobileApp.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("充值GSON:", "" + response);
                        Yinhangka_Gson data = new Gson().fromJson(response, Yinhangka_Gson.class);
                        cz_keyong.setText("可用余额:  " + data.getUsableSum() + "元");
                        String msg = data.getMsg();
                        if (msg.equals("")) {
                            promptDialog.dismiss();
                            kongbai.setVisibility(View.GONE);
                            yh_name.setText(data.getBankName());
                            yh_number.setText(data.getCardNum());
                            x.image().bind(yh_img, data.getBankImage());


                            cz_ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (cz_money.getText().toString().equals("")) {
                                        ToastUtils.showToast(ChongZhq.this, "请输入金额");
                                    } else {
                                        if (Integer.valueOf(cz_money.getText().toString().trim()) < 100) {//金额不能小于100
                                            ToastUtils.showToast(ChongZhq.this, "充值金额不能小于100元");
                                        } else {
                                            getokHTTp();
                                        }
                                    }
                                }
                            });
                        } else {
                            promptDialog.dismiss();
                            kongbai.setVisibility(View.VISIBLE);
                            yhcard.setVisibility(View.GONE);//影藏布局
                            if (msg.equals("auth")) {
                                AlertDialog dialog1 = new AlertDialog.Builder(ChongZhq.this)
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
//                                                finish();
                                            }
                                        })
                                        .create();
                                dialog1.setCanceledOnTouchOutside(false);
                                dialog1.show();
                            }
                            if (msg.equals("bank_link")) {
                                AlertDialog dialog1 = new AlertDialog.Builder(ChongZhq.this)
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
//                                                finish();
                                            }
                                        })
                                        .create();
                                dialog1.setCanceledOnTouchOutside(false);
                                dialog1.show();

                            }
                            if (msg.equals("sign_card")) {
//                        Toast.makeText(ChongZhq.this, "没有签约", Toast.LENGTH_SHORT).show();
                                AlertDialog dialog3 = new AlertDialog.Builder(ChongZhq.this)
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
                                                Intent it = new Intent(ChongZhq.this, KUaiJieZhiFu.class);
                                                startActivity(it);
//                                                finish();
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
                    public void onResponse(String response, int id) {
                        Log.e("是否可实名GSON：", response);
                        ShiFouKeShiMing_gson data = new Gson().fromJson(response, ShiFouKeShiMing_gson.class);
                        String message = data.getMessage().toString();
                        String jieguo = data.getState().toString();
                        if (jieguo.equals("success")) {
                            Intent it = new Intent(ChongZhq.this, WoDeShiMing.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("user_ird", user_id);
                            it.putExtras(bundle);
                            startActivity(it);
                        }else {
                            if (message.equals("认证失败！您今日的认证次数已达上限，请明天再进行认证！")){
                                AlertDialog dialog1 = new AlertDialog.Builder(ChongZhq.this)
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
                                ToastUtils.showToast(ChongZhq.this, message);
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
                        Intent it = new Intent(ChongZhq.this, YinHangCunGuan.class);
                        it.putExtra("HTML", html);
                        Log.e("我的页面银行存管HTML:", "" + it);
                        startActivity(it);

                        Log.e("wangy", "" + html);
                    }
                });

    }

    private void getokHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("money", cz_money.getText().toString());
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
                .url(Urls.BASE_URL + "yxbApp/postOnlineCZFu.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("ok充值：", result);
                        ChongZhiOk_GSon data = new Gson().fromJson(result, ChongZhiOk_GSon.class);
                        String html = data.getHtml();
                        Intent itcz = new Intent(ChongZhq.this, ChongZhiOK.class);
                        itcz.putExtra("HTML", html);
                        Log.e("HTMLone:", "" + html.toString());
                        startActivity(itcz);
                        finish();
                    }
                });

    }

    //此方法只是关闭软键盘 可以在finish之前调用一下
    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
    private void getczid() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        loginid = (String) SPUtils.get(ChongZhq.this, "Loginid", "");
        token = (String) SPUtils.get(ChongZhq.this, "Token1", "");
        yh_name = findViewById(R.id.yh_name);
        yh_number = findViewById(R.id.yh_number);
        cz_keyong = findViewById(R.id.cz_keyong);
        cz_money = findViewById(R.id.cz_money);
        yh_img = findViewById(R.id.yh_img);
        cz_ok = findViewById(R.id.cz_ok);
        yhcard = findViewById(R.id.yhcard1);
        cz_fh = findViewById(R.id.cz_fh);
        cz_money.setInputType( InputType.TYPE_CLASS_NUMBER);
        kongbai=findViewById(R.id.chongzhi_kongbai);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getczHTTp();
    }
}
