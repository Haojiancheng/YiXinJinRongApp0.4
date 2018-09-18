package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.zijinliushui;

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
import com.yixingjjinrong.yixinjinrongapp.gsondata.ZiJInLiuShu_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.ZiJinLiuShui_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.wodechujie_one.MyChuJie_one;
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

public class ZiJinliushui extends AutoLayoutActivity implements XRecyclerView.LoadingListener{
    private XRecyclerView xrview;
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private int a=1;
    private List<ZiJInLiuShu_gson.FundRecordlistBean> list=new ArrayList<>();
    private ZiJinLiuShui_adapter adapter;
    private String loginid;
    private String token;
    private ImageView wo_zjls_fh;
    private View zijinliushui_wushuju;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_zi_jinliushui);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        list.clear();
        getlsID();
        adapter=new ZiJinLiuShui_adapter(list);
        xrview.setAdapter(adapter);
        getlsHTTP();
        wo_zjls_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getlsHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("pageNumber", 1);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
//            js_request.put("pageNumber", a);
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
            MyLog.e("我的资金流水加密：",""+canshu );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/getFundrecordList.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("我的资金流水GSon","" +result);
                        ZiJInLiuShu_gson data = new Gson().fromJson(result, ZiJInLiuShu_gson.class);
                        if (data.getMessage().equals("用户未登录。")) {
                            ToastUtils.showToast(ZiJinliushui.this, "用户未登录。");
                        } else {
                            if (data.getMessage().equals("没有可用券!")) {
                                zijinliushui_wushuju.setVisibility(View.VISIBLE);
                            } else {
                                zijinliushui_wushuju.setVisibility(View.GONE);
                                list.addAll(data.getFundRecordlist());
                                adapter.notifyDataSetChanged();
                            }
                        }



                    }
                });

    }

    private void getlsID() {
        user_id = (int) SPUtils.get(this,"userId",0);
        loginid = (String) SPUtils.get(ZiJinliushui.this, "Loginid", "");
        token = (String) SPUtils.get(ZiJinliushui.this, "Token1", "");
        xrview=findViewById(R.id.liushuirview);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        xrview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xrview.setLoadingListener(this);
        xrview.setPullRefreshEnabled(true);
        wo_zjls_fh=findViewById(R.id.wo_zjls_fh);
        xrview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        xrview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        zijinliushui_wushuju=findViewById(R.id.zijinliushui_wushuju);
    }

    @Override
    public void onRefresh() {
        adapter.notifyDataSetChanged();
        list.clear();
        a=1;
        getlsHTTP();
        xrview.refreshComplete();

    }

    @Override
    public void onLoadMore() {
        a++;
        getlsHTTP();
        xrview.loadMoreComplete();
    }
}
