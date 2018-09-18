package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.wodechujie_one.yidaoqi;

import android.content.Intent;
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
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyChuJIe_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.MyChuJie_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment.ChuJIeXiangQing;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.wodechujie_one.MyChuJie_one;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class YiDaoQiXiangMu extends AutoLayoutActivity implements XRecyclerView.LoadingListener {
    private XRecyclerView yidaoqi_xview;
    private ImageView chujie_yidaoqi_fh;
    private int user_id;
    private String loginid;
    private String token;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private int a=1;
    private List<MyChuJIe_gson.InvestListBean> list = new ArrayList<>();
    private MyChuJie_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_dao_qi_xiang_mu);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        getid();
        adapter = new MyChuJie_adapter(list, this);
        yidaoqi_xview.setAdapter(adapter);
        gethttp();

        chujie_yidaoqi_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("borrowStatus", 2);
            js_request.put("pageNumber", a);
//            js_request.put("mortgageType", 1);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            MyLog.e("我的出借房产表参数：", "" + js_request);
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
            MyLog.e("我的出借房产表加密：", "" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/myInvestList.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("我的出借Gson", "" + response);
                        MyChuJIe_gson data = new Gson().fromJson(response, MyChuJIe_gson.class);
                        list.addAll(data.getInvestList());
//                        adapter.setonEveryItemClickListener(new MyChuJie_adapter.OnEveryItemClickListener() {
//                            @Override
//                            public void onEveryClick(int position) {
//                                String borrowid = String.valueOf(list.get(position).getBorrowId());
//                                String investid = String.valueOf(list.get(position).getInvestid());
//                                String typse = String.valueOf(list.get(position).getMortgageType());
//                                Intent it=new Intent(YiDaoQiXiangMu.this,ChuJIeXiangQing.class);
//                                it.putExtra("borrowid", borrowid);
//                                it.putExtra("investid", investid);
//                                it.putExtra("type", typse);
//                                startActivity(it);
//                            }
//                        });

                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void getid() {
        user_id = (int) SPUtils.get(YiDaoQiXiangMu.this, "userId", 0);
        loginid = (String) SPUtils.get(YiDaoQiXiangMu.this, "Loginid", "");
        token = (String) SPUtils.get(YiDaoQiXiangMu.this, "Token1", "");
        yidaoqi_xview=findViewById(R.id.yidaoqi_xview);
        chujie_yidaoqi_fh=findViewById(R.id.chujie_yidaoqi_fh);
        LinearLayoutManager manager = new LinearLayoutManager(YiDaoQiXiangMu.this);
        yidaoqi_xview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        yidaoqi_xview.setLoadingListener(this);
        yidaoqi_xview.setPullRefreshEnabled(true);
//        xRecyclerView.setLoadingMoreEnabled(true);
//        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        yidaoqi_xview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);

    }


    @Override
    public void onRefresh() {
        list.clear();
        adapter.notifyDataSetChanged();
        a = 1;
        gethttp();
        yidaoqi_xview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        a++;
        gethttp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                yidaoqi_xview.loadMoreComplete();
            }
        }, 3000);

    }
}
