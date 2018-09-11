package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.MaxLengthWatcher;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.YanZhengMa_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ZhaoHuiMIma_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.HideIMEUtil;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class ZhaoHuiMiMa extends AutoLayoutActivity {
    private Button zhaohuimima_xiayibu;//找回密码下一步
    private EditText myphonet;
    private ImageView zh_fh;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private ImageView zhaohui_image;
    private ZhaoHuiMIma_Gson data1;
    public static ZhaoHuiMiMa zhaoHuiMiMa;
    private View zhouhukefu;
    private static final int PERMISSION_REQUESTCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_zhaohui_mima);
        zhaoHuiMiMa = this;
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        HideIMEUtil.wrap(this);//键盘管理，点击除editText外区域收起键盘
        getzhaohuimimaId();
        getzhaohuimimaOnCilk();

    }


    private void getzhaohuimimaOnCilk() {
        myphonet.addTextChangedListener(new MaxLengthWatcher(11, myphonet));
        zhaohuimima_xiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myphonet.getText().toString().equals(""))
                    ToastUtils.showToast(ZhaoHuiMiMa.this, "请输入手机号");
                else if (myphonet.getText().toString().length() < 11) {
                    ToastUtils.showToast(ZhaoHuiMiMa.this, "手机号格式不正确");
                } else if (myphonet.getText().toString().length() > 11) {
                    ToastUtils.showToast(ZhaoHuiMiMa.this, "手机号格式不正确");
                } else {
                    gethttp();

                }

            }
        });
        zhaohui_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myphonet.setText("");
            }
        });

        zh_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zhouhukefu.setOnClickListener(new View.OnClickListener() {
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

        final AlertDialog dialog = new AlertDialog.Builder(ZhaoHuiMiMa.this)
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
        if (ContextCompat.checkSelfPermission(ZhaoHuiMiMa.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //没有授权
            ActivityCompat.requestPermissions(ZhaoHuiMiMa.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUESTCODE);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUESTCODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户点击了同意授权
//                    diallPhone("4001838818");
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + "4001838818");
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

                } else {
                    //用户拒绝了授权
//                    ToastUtils.showToast(ZhaoHuiMiMa.this, "权限被拒绝");
                }
                break;
            default:
                break;
        }
    }


    private void gethttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("phone", myphonet.getText().toString());
            js_request.put("type", 2);
//            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>SDEWSFDREREbase加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>GGGGGGGSH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>()base()加密11111!!--" + base1);
            Log.e("TAG", ">>>>()sha1()加密11111!!--" + sha1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/PhoneVerify.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("ssss", "" + result);

                        data1 = new Gson().fromJson(result, ZhaoHuiMIma_Gson.class);
                        Log.e("ddddd", "" + data1.getMessage());
                        if (data1.getMessage().equals("该手机已注册")) {
//                            finish();
                            AlertDialog dialog3 = new AlertDialog.Builder(ZhaoHuiMiMa.this)
                                    .setTitle("提示")
                                    .setMessage("发送验证码短信至 : " + myphonet.getText().toString())
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            getduanxinhttp();
                                        }
                                    })
                                    .create();
                            dialog3.setCanceledOnTouchOutside(false);
                            dialog3.show();
                        } else {
                            ToastUtils.showToast(ZhaoHuiMiMa.this, "" + data1.getMessage());
                        }
                    }
                });

    }

    private void getduanxinhttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("phone", myphonet.getText().toString());
            js_request.put("type", 3);
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

                            String message = data.getMessage();
                            String jsessionId = data.getResult().getJsessionId();
                            Log.e("jsessionId", "" + jsessionId);
                            String phonezhuangtai = data1.getResult().getMapPhone();
                            if (phonezhuangtai.equals("0")) {
                                Intent zhaohuimima_it = new Intent(ZhaoHuiMiMa.this, ZhaoHuiMiMaYanZheng.class);
                                zhaohuimima_it.putExtra("phone", myphonet.getText().toString());
                                zhaohuimima_it.putExtra("timer", "1");
                                zhaohuimima_it.putExtra("jsessionId", jsessionId);
                                startActivity(zhaohuimima_it);
                            } else {
                                ToastUtils.showToast(ZhaoHuiMiMa.this, data1.getMessage());
                            }

                        } else {
                            ToastUtils.showToast(ZhaoHuiMiMa.this, data.getMessage());

                        }
                    }
                });
    }


    private void getzhaohuimimaId() {
        zhaohuimima_xiayibu = findViewById(R.id.zhaohuimiam_xiayibu);//找回密码下一步
        myphonet = findViewById(R.id.myphonet);
        zh_fh = findViewById(R.id.zh_fh);
        zhouhukefu = findViewById(R.id.zhouhukefu);
        myphonet.setInputType(InputType.TYPE_CLASS_NUMBER);//数字键盘
        zhaohui_image = findViewById(R.id.zhaohui_image);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
