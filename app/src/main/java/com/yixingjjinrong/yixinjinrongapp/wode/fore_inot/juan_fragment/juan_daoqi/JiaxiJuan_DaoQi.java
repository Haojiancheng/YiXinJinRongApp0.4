package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment.juan_daoqi;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiaXiJuan_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.JiaXiJuan_yishi_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class JiaxiJuan_DaoQi extends AutoLayoutActivity implements XRecyclerView.LoadingListener {
    private XRecyclerView ys_xj_xview;
    private ImageView ys_xjfh;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private int user_id;
    private int a = 1;
    private String loginid;
    private String token;
    private List<JiaXiJuan_Gson.QueryVouchersListBean> mlist = new ArrayList<>();
    private JiaXiJuan_yishi_adapter adapter;
    private View jx_wsys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xian_jin_juan__dao_qi);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        getid();
        adapter = new JiaXiJuan_yishi_adapter(mlist);
        ys_xj_xview.setAdapter(adapter);
        getHttp();
        ys_xjfh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("activitype", "3");
            js_request.put("staut", "2");
            js_request.put("pageNumber", a);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>base加密11111!!--" + js_request);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("加密参数", "" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                //http://192.168.1.111:8080/yxb_mobile/
                .url(Urls.BASE_URL + "yxbApp/queryAll.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("加息卷到期GSON", response);
                        JiaXiJuan_Gson data = new Gson().fromJson(response, JiaXiJuan_Gson.class);
                        if (data.getMessage().equals("没有可用券!")) {
                            jx_wsys.setVisibility(View.VISIBLE);

                        } else {
                            jx_wsys.setVisibility(View.GONE);
                            mlist.addAll(data.getQueryVouchersList());
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void getid() {
        user_id = (int) SPUtils.get(JiaxiJuan_DaoQi.this, "userId", 0);
        loginid = (String) SPUtils.get(JiaxiJuan_DaoQi.this, "Loginid", "");
        token = (String) SPUtils.get(JiaxiJuan_DaoQi.this, "Token1", "");
        ys_xj_xview = findViewById(R.id.ys_xj_xview);
        ys_xjfh = findViewById(R.id.ys_xjfh);
        jx_wsys=findViewById(R.id.jx_wsys);
        LinearLayoutManager manager = new LinearLayoutManager(JiaxiJuan_DaoQi.this);
        ys_xj_xview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        ys_xj_xview.setLoadingListener(this);
        ys_xj_xview.setPullRefreshEnabled(true);
        ys_xj_xview.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        ys_xj_xview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    @Override
    public void onRefresh() {
        mlist.clear();
        adapter.notifyDataSetChanged();
        a = 1;
        getHttp();
        ys_xj_xview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        a++;
        getHttp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ys_xj_xview.loadMoreComplete();
            }
        }, 2000);
        ys_xj_xview.loadMoreComplete();
    }
}
