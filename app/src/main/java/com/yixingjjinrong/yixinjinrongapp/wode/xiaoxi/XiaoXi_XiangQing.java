package com.yixingjjinrong.yixinjinrongapp.wode.xiaoxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XXxiangqing_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class XiaoXi_XiangQing extends AutoLayoutActivity {
    private int xx_ird;
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private WebView xxweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoxi_xiangqing);

        getxx_xqid();
        getxxxqHTTP();
    }

    private void getxxxqHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("msgId", xx_ird);
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
            Log.e("我的消息",""+canshu );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/getMsgDetailById.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("消息详情GSON：",""+result);
                XXxiangqing_gson data = new Gson().fromJson(result, XXxiangqing_gson.class);
                String result1 = data.getResult();
                WebSettings webSettings = xxweb.getSettings();

                webSettings.setJavaScriptEnabled(true);
                // 设置允许JS弹窗
                webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

                xxweb.loadUrl(result1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void getxx_xqid() {
        Bundle b = getIntent().getExtras();
        xx_ird = b.getInt("xx_ird");
        user_id = (int) SPUtils.get(XiaoXi_XiangQing.this,"userId",0);
        xxweb=findViewById(R.id.xxweb);
    }
}
