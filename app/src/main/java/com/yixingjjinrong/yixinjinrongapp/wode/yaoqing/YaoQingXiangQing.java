package com.yixingjjinrong.yixinjinrongapp.wode.yaoqing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.YaoQingXiangQing_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class YaoQingXiangQing extends AutoLayoutActivity {

    private String mancount;
    private String countmoney;
    private TextView xq_mymancount,xq_mycount_money;
    private View wushuju,youshuju;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private RecyclerView yaoqing_rview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yaoqing_xiangqing);
        getinterview();
        getHTTp();
    }

    private void getHTTp() {
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

        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/myInvite.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("我的邀请详情Gson",""+result );
                YaoQingXiangQing_Gson data = new Gson().fromJson(result, YaoQingXiangQing_Gson.class);
                int inviteAmount = data.getQueryAwardList().size();//邀请人数
                String totalEarn = data.getTotalEarn();//总收益
                xq_mycount_money.setText(totalEarn);//接到的钱
                xq_mymancount.setText(""+inviteAmount);//接到的人数
                if(inviteAmount==0){
                    wushuju.setVisibility(View.VISIBLE);
                }else {

                }

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

    private void getinterview() {
        user_id = (int) SPUtils.get(this,"userId",0);

        xq_mycount_money=findViewById(R.id.xq_mycount_money);
        xq_mymancount=findViewById(R.id.xq_mymancount);

        wushuju=findViewById(R.id.wushuju);//无数据
        youshuju=findViewById(R.id.youshuju);//有数据
        yaoqing_rview=findViewById(R.id.yaoqing_rview);

    }
}
