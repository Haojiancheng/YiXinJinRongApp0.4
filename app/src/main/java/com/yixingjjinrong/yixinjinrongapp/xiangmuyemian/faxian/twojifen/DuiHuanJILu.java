package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.twojifen;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DuiHuanJiLu_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.DuiHuanJiLu_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
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

public class DuiHuanJILu extends AutoLayoutActivity implements XRecyclerView.LoadingListener {
    private XRecyclerView duihuan_xrview;
    private ImageView dhjl_fh;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private int a = 1;
    List<DuiHuanJiLu_gson.ResultBean.ListBean> list = new ArrayList<>();
    private DuiHuanJiLu_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_duihuan_jilu);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        getduihuanid();
        getfuihuanHTTp();
        dhjl_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getfuihuanHTTp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("userId", user_id);
            js_request.put("pageNum", a);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>SDEWSFDREREbase加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>GGGGGGGSH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("TAG", ">>>>()base()加密11111!!--" + base1);
            MyLog.e("TAG", ">>>>()sha1()加密11111!!--" + sha1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/exchangeRecord.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("兑换记录GSON:", result);
                        DuiHuanJiLu_gson data = new Gson().fromJson(result, DuiHuanJiLu_gson.class);
                        list.addAll(data.getResult().getList());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void getduihuanid() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        duihuan_xrview = findViewById(R.id.duhuan_rview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        duihuan_xrview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        duihuan_xrview.setLoadingListener(this);
        duihuan_xrview.setPullRefreshEnabled(true);
        duihuan_xrview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        dhjl_fh = findViewById(R.id.dhjl_fh);
        adapter = new DuiHuanJiLu_adapter(list);
        duihuan_xrview.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        list.clear();
        a = 1;
        getfuihuanHTTp();
        duihuan_xrview.refreshComplete();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        a++;
        getfuihuanHTTp();
         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                duihuan_xrview.loadMoreComplete();
            }
        }, 2000);
    }


}
