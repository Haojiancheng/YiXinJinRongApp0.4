package com.yixingjjinrong.yixinjinrongapp.wode.xiaoxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XXxiangqing_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.zongzichen.ZongziChan;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.wangdaiClass.Class_xiangqing;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import okhttp3.Call;
import okhttp3.MediaType;

public class XiaoXi_XiangQing extends AutoLayoutActivity {
    private int xx_ird;
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private WebView xxweb;
    private String loginid;
    private String token;
    private ImageView xxxp_fh;
    private TextView xx_xq_title;
    private String xqtitle;
    private String type;
    private String jiekou;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_xiaoxi_xiangqing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .keyboardEnable(true)
                .init();
        getxx_xqid();
        getxxxqHTTP();
        xxxp_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getxxxqHTTP() {
        if (jiekou.equals("xiaoxi")){
            type="getMsgDetailById.do";
        }else {
            type="yxbAppIndexPublicMsgDetail.do";
        }
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("pubMsgId", xx_ird);
            js_request.put("msgId", xx_ird);
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
            Log.e("我的消息", "" + canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/" + type)
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("消息详情：", Urls.BASE_URL + "yxbApp/" + type);
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("消息详情GSON：", "" + result);
                        XXxiangqing_gson data = new Gson().fromJson(result, XXxiangqing_gson.class);
                        String result1 = data.getResult();
                        WebSettings webSettings = xxweb.getSettings();
                        Log.e("消息详情：", Urls.BASE_URL + "yxbApp/" + type);
                        webSettings.setJavaScriptEnabled(true);
                        // 设置允许JS弹窗
                        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

                        xxweb.loadUrl(result1);
                    }
                });

    }

    private void getxx_xqid() {
        loginid = (String) SPUtils.get(XiaoXi_XiangQing.this, "Loginid", "");
        token = (String) SPUtils.get(XiaoXi_XiangQing.this, "Token1", "");
        Bundle b = getIntent().getExtras();
        xx_ird = b.getInt("xx_ird");
        Intent it = getIntent();
        xqtitle = it.getStringExtra("xqtitle");
        jiekou = it.getStringExtra("type");
        user_id = (int) SPUtils.get(XiaoXi_XiangQing.this, "userId", 0);
        xxweb = findViewById(R.id.xxweb);
        xxxp_fh = findViewById(R.id.xxxp_fh);
        xx_xq_title = findViewById(R.id.xx_xq_title);
        xx_xq_title.setText(xqtitle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空所有Cookie
        CookieSyncManager.createInstance(XiaoXi_XiangQing.this);  //Create a singleton CookieSyncManager within a context
        CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        CookieSyncManager.getInstance().sync(); // forces sync manager to sync now

        xxweb.setWebChromeClient(null);
        xxweb.setWebViewClient(null);
        xxweb.getSettings().setJavaScriptEnabled(false);
        xxweb.clearCache(true);

    }
}
