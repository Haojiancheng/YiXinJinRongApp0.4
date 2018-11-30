package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment.juan_daoqi;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XianJinJuan_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XianjinJuan_yishi_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
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

public class XianJinJuan_YiDao extends AutoLayoutActivity implements XRecyclerView.LoadingListener {
    private ImageView ys_xjinfh;
    private XRecyclerView yishi_xj_xview;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private List<XianJinJuan_gson.QueryVouchersListBean> list = new ArrayList<>();
    private int user_id;
    private int a = 1;
    private String loginid;
    private String token;
    private XianjinJuan_yishi_adapter adapter;
    private View xj_wsys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xian_jin_juan_yi_dao);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();

        getid();
        adapter = new XianjinJuan_yishi_adapter(list);
        yishi_xj_xview.setAdapter(adapter);
        getHttp();
        ys_xjinfh.setOnClickListener(new View.OnClickListener() {
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
            js_request.put("activitype", "6");
            js_request.put("staut", "2");
            js_request.put("pageNumber", a);
            js_request.put("token", token);
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
                        MyLog.e("现金券到期GSon", "" + response);
                        XianJinJuan_gson data = new Gson().fromJson(response, XianJinJuan_gson.class);
                        if (data.getMessage().equals("没有可用券!")) {
                            xj_wsys.setVisibility(View.VISIBLE);

                        } else {
                            xj_wsys.setVisibility(View.GONE);
                            list.addAll(data.getQueryVouchersList());
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void getid() {
        loginid = (String) SPUtils.get(XianJinJuan_YiDao.this, "Loginid", "");
        token = (String) SPUtils.get(XianJinJuan_YiDao.this, "Token1", "");
        user_id = (int) SPUtils.get(XianJinJuan_YiDao.this, "userId", 0);
        ys_xjinfh = findViewById(R.id.ys_xjinfh);
        yishi_xj_xview = findViewById(R.id.yishi_xj_xview);
        xj_wsys = findViewById(R.id.xj_wsys);
        LinearLayoutManager manager = new LinearLayoutManager(XianJinJuan_YiDao.this);
        yishi_xj_xview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        yishi_xj_xview.setLoadingListener(this);
        yishi_xj_xview.setPullRefreshEnabled(true);
        yishi_xj_xview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        yishi_xj_xview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    @Override
    public void onRefresh() {
        list.clear();
        adapter.notifyDataSetChanged();
        a = 1;
        getHttp();
        yishi_xj_xview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        a++;
        getHttp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                yishi_xj_xview.loadMoreComplete();
            }
        }, 2000);
        yishi_xj_xview.loadMoreComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }
}
