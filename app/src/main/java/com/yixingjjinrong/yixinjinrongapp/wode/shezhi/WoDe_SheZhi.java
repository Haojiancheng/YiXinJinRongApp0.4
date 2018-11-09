package com.yixingjjinrong.yixinjinrongapp.wode.shezhi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.HandUtils.GestureEditActivity;
import com.yixingjjinrong.yixinjinrongapp.HandUtils.GestureVerifyActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.DataCleanManager;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ZhaoHuiMiMa;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class WoDe_SheZhi extends AutoLayoutActivity {
    private Button user_tuichu;//退出按钮
    private View repassword;//修改密码
    private View fankui;//意见反馈
    private TextView hz;//缓存
    private int user_ird;
    private String token;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private Context context;
    private ImageView shezhi_fh;
    private PromptDialog promptDialog;
    private TextView hand_pass;
    private String ishand;
    private View shoushi_bt;//手势密码
    private View hz_bt;
    public static WoDe_SheZhi wodezhezhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode_shezhi);
        wodezhezhi = this;
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        getSheZhi_Id();
        Bundle b = getIntent().getExtras();
        user_ird = b.getInt("user_ird");

        token = b.getString("token");
        user_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog3 = new AlertDialog.Builder(WoDe_SheZhi.this)
                        .setTitle("提示")
                        .setMessage("您确定要退出当前账户？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gethttp();
                            }
                        })
                        .create();
                dialog3.setCanceledOnTouchOutside(false);
                dialog3.show();


            }
        });
        repassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shezhi_tuichu = new Intent(WoDe_SheZhi.this, XiuGaiMiMa.class);
                Bundle bundle = new Bundle();
                bundle.putInt("user_ird", user_ird);
                shezhi_tuichu.putExtras(bundle);
                startActivity(shezhi_tuichu);
//                finish();
            }
        });

        fankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shezhi_tuichu = new Intent(WoDe_SheZhi.this, FanKui.class);
                Bundle bundle = new Bundle();
                bundle.putInt("user_ird", user_ird);
                shezhi_tuichu.putExtras(bundle);
                startActivity(shezhi_tuichu);

            }
        });
        shezhi_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gethuancun();
        getonclick();
    }

    private void getonclick() {
        ishand = (String) SPUtils.get(WoDe_SheZhi.this, "ishand", "");
        if (ishand != null) {
            if (ishand.equals("1")) {
                hand_pass.setText("已开启");
                shoushi_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog dialog1 = new AlertDialog.Builder(WoDe_SheZhi.this)
                                .setTitle("提示")
                                .setMessage("您是否关闭手势密码")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(WoDe_SheZhi.this, GestureVerifyActivity.class);
                                        intent.putExtra("shezhi", "1");
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .create();
                        dialog1.setCanceledOnTouchOutside(false);
                        dialog1.show();

                    }
                });

            } else {
                hand_pass.setText("未开启");
                shoushi_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog dialog1 = new AlertDialog.Builder(WoDe_SheZhi.this)
                                .setTitle("提示")
                                .setMessage("您是否开起手势密码")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent it = new Intent(WoDe_SheZhi.this, GestureEditActivity.class);
                                        startActivity(it);
                                    }
                                })
                                .create();
                        dialog1.setCanceledOnTouchOutside(false);
                        dialog1.show();
                    }
                });
            }
        } else {
            shoushi_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog dialog1 = new AlertDialog.Builder(WoDe_SheZhi.this)
                            .setTitle("提示")
                            .setMessage("您是否开起手势密码")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(WoDe_SheZhi.this, GestureEditActivity.class);
                                    startActivity(it);
                                }
                            })
                            .create();
                    dialog1.setCanceledOnTouchOutside(false);
                    dialog1.show();
                }
            });
        }
    }

    private void gethuancun() {
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(WoDe_SheZhi.this);
            hz.setText(totalCacheSize + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        hz_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog1 = new AlertDialog.Builder(WoDe_SheZhi.this)
                        .setTitle("提示")
                        .setMessage("您是否清理缓存")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataCleanManager.clearAllCache(WoDe_SheZhi.this);
                                hz.setText("0KB");
                            }
                        })
                        .create();
                dialog1.setCanceledOnTouchOutside(false);
                dialog1.show();
            }
        });
    }

    private void gethttp() {
        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        String myurl = getid(context);
        try {

//            SPUtils.remove(WoDe_SheZhi.this,"userId");
//            SPUtils.remove(WoDe_SheZhi.this,"Token1");
            js_request.put("userid", user_ird);
            js_request.put("token", token);
            js_request.put("url", myurl);
            MyLog.e("useridddd", user_ird + "");
            MyLog.e("token", token + "");
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>SH!!" + sha1);
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
                .url(Urls.BASE_URL + "yxbApp/quitLogin.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                        ToastUtils.showToast(WoDe_SheZhi.this, "网络错误，请售后再试");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("退出登入的GSON:", response);
//                Intent shezhi_tuichu=new Intent(WoDe_SheZhi.this, WoDe_DengRu.class);
//                startActivity(shezhi_tuichu);
                        SPUtils.put(WoDe_SheZhi.this, "isLogin", false);
//                EventBus.getDefault().post(new UnLogin());
                        SPUtils.remove(WoDe_SheZhi.this, "userId");
                        SPUtils.remove(WoDe_SheZhi.this, "Loginid");
                        promptDialog.dismiss();
                        finish();
                    }
                });
    }

    public synchronized String getid(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String ID = TelephonyMgr.getDeviceId();
        return ID;
    }

    private void getSheZhi_Id() {
        user_tuichu = findViewById(R.id.user_tuichu);//退出
        repassword = findViewById(R.id.repassword);//修改密码
        fankui = findViewById(R.id.fankui);//意见反馈
        hz = findViewById(R.id.hz);//缓存
        shezhi_fh = findViewById(R.id.shezhi_fh);
        hand_pass = findViewById(R.id.hand_pass);
        shoushi_bt = findViewById(R.id.shoushi_bt);//手势密码
        hz_bt = findViewById(R.id.hz_bt);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}
