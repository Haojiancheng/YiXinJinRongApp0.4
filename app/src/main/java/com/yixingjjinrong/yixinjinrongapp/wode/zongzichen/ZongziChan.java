package com.yixingjjinrong.yixinjinrongapp.wode.zongzichen;

import android.os.Bundle;
import android.util.Log;

import com.yixingjjinrong.yixinjinrongapp.MyView.DataItem;
import com.yixingjjinrong.yixinjinrongapp.MyView.DiscView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ZongziChan extends AutoLayoutActivity {
    private DiscView rv;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zongzi_chan);

        getzzcID();
        gethttp();

    }

    private void gethttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
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
            Log.e("我的加密：", ":"+canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/userTotalAmount.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("我的总资产Gson",result );
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

    private void getzzcID() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        rv=findViewById(R.id.disc);
        List<DataItem> items = new ArrayList<>();

        items.add(new DataItem(1,"腾讯",getResources().getColor(R.color.colorPrimary)));
        items.add(new DataItem(1,"美团",getResources().getColor(R.color.colorPrimaryDark)));
        items.add(new DataItem(23,"Google",getResources().getColor(R.color.colorTransparent)));

        rv.setItems(items);
    }
}
