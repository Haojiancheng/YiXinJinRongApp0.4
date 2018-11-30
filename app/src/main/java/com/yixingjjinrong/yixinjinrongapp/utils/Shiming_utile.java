package com.yixingjjinrong.yixinjinrongapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiMingRenZengJieGuo_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class Shiming_utile {
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String message;
    private PromptDialog promptDialog;
    private String zhuangtai;
    private String message1;

    public String lgetshimingHTTp(int user_id, String token1, String loginid, final Context context) {
        promptDialog = new PromptDialog((Activity) context);
        promptDialog.showLoading("");
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
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
                .url(Urls.BASE_URL + "yxbApp/queryUserAuthInfo.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                        ToastUtils.showToast(context, "网络连接失败，请稍后再试");
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("是否可实名GSON：", result);
                        ShiFouKeShiMing_gson data = new Gson().fromJson(result, ShiFouKeShiMing_gson.class);
                        message = data.getMessage().toString();
//                        promptDialog.dismiss();
//                        ToastUtils.showToast(XiangMuXiangQing.this, "" + message);
//                        String jieguo = data.getState().toString();
//                        if (message.equals("可以认证")) {
//                            getsmHttp();
//                        } else {
//                            promptDialog.dismiss();
//                            if (message.equals("认证失败！您今日的认证次数已达上限，请明天再进行认证！")) {
//                                AlertDialog dialog1 = new AlertDialog.Builder(XiangMuXiangQing.this)
//                                        .setTitle("提示")
//                                        .setMessage("认证失败！您今日的认证次数已达上限，请明天再进行认证！")
//                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.dismiss();
//                                            }
//                                        })
//                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.dismiss();
//
//                                            }
//                                        })
//                                        .create();
//                                dialog1.setCanceledOnTouchOutside(false);
//                                dialog1.show();
//                            } else {
//                                ToastUtils.showToast(XiangMuXiangQing.this, message);
//                            }
//                        }
                    }
                });
        return message;
    }

    public String lgetsmHttp(int user_id, String popidcard, String popname, String token1, String loginid, final Context context) {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("idNo", popidcard);
            js_request.put("realName", popname);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
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
                .url(Urls.BASE_URL + "yxbApp/userAuth.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast(context, "网络连接失败，请稍后再试");
                        promptDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("实名认证的GSOn", "" + result);
                        ShiMingRenZengJieGuo_gson data = new Gson().fromJson(result, ShiMingRenZengJieGuo_gson.class);
                        message1 = data.getMessage().toString();
//                Toast.makeText(ShiMingrenzheng.this, ""+message, Toast.LENGTH_SHORT).show();
                        zhuangtai = data.getState();
                        MyLog.e("实名认证", zhuangtai);
//                        Map<String, String> stringStringMap = new Map<String, String>();
                    }
                });
        return zhuangtai;

    }


}
