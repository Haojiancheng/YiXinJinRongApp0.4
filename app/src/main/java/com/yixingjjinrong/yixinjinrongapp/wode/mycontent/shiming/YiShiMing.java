package com.yixingjjinrong.yixinjinrongapp.wode.mycontent.shiming;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.YiRenZheng_GSON;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class YiShiMing extends AutoLayoutActivity {
    private TextView rname,rcard;
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private ImageView ysm_fh;
    private String loginid;
    private String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_yi_shi_ming);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        getrid();
        gethttp();
        ysm_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", userToken);
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
                .url(Urls.BASE_URL+"yxbApp/queryUserAuthInfo.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("已认证GSON:",result );
                        YiRenZheng_GSON data = new Gson().fromJson(result, YiRenZheng_GSON.class);
                        if (data.getMessage().equals("已经认证")) {
                            String realName = data.getResult().getRealName();
                            String idNo = data.getResult().getIdNo();
                            rname.setText(realName);
                            rcard.setText(idNo);
                        }else {
                            Toast.makeText(YiShiMing.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

    }

    private void getrid() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        loginid = (String) SPUtils.get(this, "Loginid", "");
        userToken = (String) SPUtils.get(this, "Token1", "");
        rname=findViewById(R.id.rname);
        rcard=findViewById(R.id.rcard);
        ysm_fh=findViewById(R.id.ysm_fh);
    }
}
