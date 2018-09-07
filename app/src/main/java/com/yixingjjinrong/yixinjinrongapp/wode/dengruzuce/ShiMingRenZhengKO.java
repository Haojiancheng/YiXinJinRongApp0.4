package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CunGuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DengruData;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import okhttp3.Call;
import okhttp3.MediaType;

public class ShiMingRenZhengKO extends AutoLayoutActivity {
    private Button cunguan;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private int user_id;
    private String loginid;
    private String token;
    private ImageView mycontentfanhui;
    private String username;
    private String password;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_shiming_renzheng_ko);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .keyboardEnable(true)
                .init();
        getokid();
        cunguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getchHTTP();
            }
        });
        mycontentfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//返回
                getHttp_zhucechenggong();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getHttp_zhucechenggong();
    }

    private void getHttp_zhucechenggong() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("username", username);
            js_request.put("password", password);
            js_request.put("url", url);
            Log.e("实名成功(登入)：", "" + js_request);
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
            JSONObject sign = canshu.put("sign", sha1);
//            Log.e("我的账户：", ""+canshu);
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

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", ">>>>GSON" + response);
                        DengruData d_data = new Gson().fromJson(response, DengruData.class);
                        //状态值
                        String dengrufanhuizhi = d_data.getState();
                        String user_token = d_data.getResult().getToken();
                        int user_id = d_data.getResult().getUserid();
                        String loginId = d_data.getResult().getLoginId();
                        if (dengrufanhuizhi.equals("success")) {
                            EventBus.getDefault().post(new User_data(username, dengrufanhuizhi, user_token, user_id, loginId));
                            SPUtils.put(ShiMingRenZhengKO.this, "isLogin", true);
                            SPUtils.put(ShiMingRenZhengKO.this, "Loginid", loginId);
                            SPUtils.put(ShiMingRenZhengKO.this, "userId", user_id);
                            SPUtils.put(ShiMingRenZhengKO.this, "Token1", user_token);
                            ZhuCe_PaGa.zc_instance.finish();
                            YanZheng_PaGa.instance.finish();
                            WoDe_DengRu.instance.finish();
                            ChengGongZhuCe.instance.finish();
                            ShiMingrenzheng.instance.finish();
                            finish();
                        }
                    }
                });
    }

    private void getchHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userid", user_id);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            Log.e("存管参数", ""+js_request);
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
                        Log.e("存管GSON:",""+result );
                        CunGuan_gson data = new Gson().fromJson(result, CunGuan_gson.class);
                        String html = data.getResult().getHtml();
                        Intent it=new Intent(ShiMingRenZhengKO.this, YinHangCunGuan.class);
                        it.putExtra("HTML",html );
                        Log.e("我的页面银行存管HTML:",""+it);
                        startActivity(it);
                        finish();
                        Log.e("wangy",""+html );
                    }
                });
    }

    private void getokid() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        username = (String) SPUtils.get(this, "username", "");
        password = (String) SPUtils.get(this, "password", "");
        url = (String) SPUtils.get(this, "url", "");
        loginid = (String) SPUtils.get(ShiMingRenZhengKO.this, "Loginid", "");
        token = (String) SPUtils.get(ShiMingRenZhengKO.this, "Token1", "");
        cunguan=findViewById(R.id.cunguan1);
        mycontentfanhui=findViewById(R.id.mycontentfanhui);

    }
}
