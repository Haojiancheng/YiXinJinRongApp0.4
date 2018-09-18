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
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiFenJiLu_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.JiFenJiLu_adapter;
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

public class JIFenJiLu extends AutoLayoutActivity implements XRecyclerView.LoadingListener{
    private XRecyclerView jilu_xrview;

    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private int a = 1;
    List<JiFenJiLu_gson.ResultBean.ListBean> list=new ArrayList<>();
    private JiFenJiLu_adapter adapter;
    private ImageView jfjl_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_jifen_jilu);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        getlilu_id();

        getjiluHTTp();
        jfjl_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getjiluHTTp() {
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
                .url(Urls.BASE_URL + "yxbApp/integralRecord.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("积分记录列表GSON:",""+result);
                        JiFenJiLu_gson data = new Gson().fromJson(result, JiFenJiLu_gson.class);
                        list.addAll(data.getResult().getList());

                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void getlilu_id() {
        user_id = (int) SPUtils.get(this,"userId",0);
        jilu_xrview=findViewById(R.id.jilu_rview);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        jilu_xrview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        jilu_xrview.setLoadingListener(this);
        jilu_xrview.setPullRefreshEnabled(true);
        jfjl_fh=findViewById(R.id.jfjl_fh);
        jilu_xrview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        adapter=new JiFenJiLu_adapter(list);
        jilu_xrview.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        list.clear();
        a=1;
        getjiluHTTp();
        jilu_xrview.refreshComplete();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        a++;
        getjiluHTTp();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jilu_xrview.loadMoreComplete();
            }
        }, 2000);

    }
}
