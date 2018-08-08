package com.yixingjjinrong.yixinjinrongapp.wode.yaoqing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.YaoQingXiangQing_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.Yaoqing_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.zongzichen.ZongziChan;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class YaoQingXiangQing extends AutoLayoutActivity {

    private String mancount;
    private String countmoney;
    private TextView xq_mymancount,xq_mycount_money;
    private View wushuju,youshuju;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private RecyclerView yaoqing_rview;
    private Yaoqing_adapter adapter;
    private List<YaoQingXiangQing_Gson.QueryAwardListBean> list=new ArrayList<>();
    private String loginid;
    private String token;
    private ImageView yqxq_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_yaoqing_xiangqing);
        list.clear();
        yqxq_fh=findViewById(R.id.yqxq_fh);
        getinterview();
        getHTTp();
        yqxq_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getHTTp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象

        try {
            js_request.put("userId", user_id);
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
            Log.e("我的加密：", ":"+canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/myInvite.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("我的邀请详情Gson",""+result );
                        YaoQingXiangQing_Gson data = new Gson().fromJson(result, YaoQingXiangQing_Gson.class);
                        int inviteAmount = data.getQueryAwardList().size();//邀请人数
                        String totalEarn = data.getTotalEarn();//总收益
                        xq_mycount_money.setText(totalEarn);//接到的钱
                        xq_mymancount.setText(""+inviteAmount);//接到的人数
                        if(inviteAmount==0){
                            wushuju.setVisibility(View.VISIBLE);
                            youshuju.setVisibility(View.GONE);
                        }else {
                            wushuju.setVisibility(View.GONE);
                            youshuju.setVisibility(View.VISIBLE);
                            list.addAll(data.getQueryAwardList());
                            adapter=new Yaoqing_adapter(list);
                            yaoqing_rview.setAdapter(adapter);
                        }
                    }
                });
    }

    private void getinterview() {
        user_id = (int) SPUtils.get(this,"userId",0);
        loginid = (String) SPUtils.get(YaoQingXiangQing.this, "Loginid", "");
        token = (String) SPUtils.get(YaoQingXiangQing.this, "Token1", "");
        xq_mycount_money=findViewById(R.id.xq_mycount_money);
        xq_mymancount=findViewById(R.id.xq_mymancount);

        wushuju=findViewById(R.id.wushuju);//无数据
        youshuju=findViewById(R.id.youshuju);//有数据
        yaoqing_rview=findViewById(R.id.yaoqing_rview);
        LinearLayoutManager manager=new LinearLayoutManager(YaoQingXiangQing.this);
        yaoqing_rview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);


    }
}
