package com.yixingjjinrong.yixinjinrongapp.authentication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.load.model.StringLoader;
import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiMingRenZengJieGuo_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingRenZhengKO;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class RealName_two {
    private String sha1;//SHA1加密
    private String base1;//Base64加密


    public  void myrralname(final Context context, int user_ird, String zhen_id, String my_name, String token, String loginid) {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_ird);
            js_request.put("idNo", zhen_id);
            js_request.put("realName", my_name);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            Log.e("实名认证", ""+js_request);
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
            Log.e("实名认证加密", ""+canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL+"yxbApp/userAuth.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("实名认证的GSOn", ""+result);
                        ShiMingRenZengJieGuo_gson data = new Gson().fromJson(result, ShiMingRenZengJieGuo_gson.class);
                        String message = data.getMessage().toString();
//                Toast.makeText(ShiMingrenzheng.this, ""+message, Toast.LENGTH_SHORT).show();

                        String zhuangtai = data.getState();
                        Log.e("实名认证", zhuangtai);
                        if (zhuangtai.equals("success")){
//                    String realName = data.getResult().getRealName();
//                    String idNo = data.getResult().getIdNo();
                            Intent intent=new Intent(context,ShiMingRenZhengKO.class);
                            context.startActivity(intent);

                        }else {
//                            jinggao.setVisibility(View.VISIBLE);
//                            jinggao.setText(message);
                        }
                    }
                });
    }
}
