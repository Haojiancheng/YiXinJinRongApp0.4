package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.wodechujie_one;

import android.content.Intent;
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
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyChuJIe_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.MyChuJie_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment.ChuJIeXiangQing;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.wodechujie_one.yidaoqi.YiDaoQiXiangMu;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class MyChuJie_one extends AutoLayoutActivity implements XRecyclerView.LoadingListener {
    private XRecyclerView chujie_one_xview;
    private ImageView chujie_one_fh;
    private int user_id;
    private String loginid;
    private String token;
    private int a = 1;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private List<MyChuJIe_gson.InvestListBean> list = new ArrayList<>();
    private MyChuJie_adapter adapter;
    private TextView yidaoqixiangmu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chu_jie_one);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();
        adapter = new MyChuJie_adapter(list, this);
        chujie_one_xview.setAdapter(adapter);
        gethttp();
        chujie_one_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        yidaoqixiangmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MyChuJie_one.this, YiDaoQiXiangMu.class);
                startActivity(it);
            }
        });
    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("borrowStatus", 1);
            js_request.put("pageNumber", a);
//            js_request.put("mortgageType", 1);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            Log.e("我的出借房产表参数：", "" + js_request);
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
            Log.e("我的出借房产表加密：", "" + canshu);
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
                        Log.e("我的出借roon", "" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("我的出借Gson", "" + response);
                        MyChuJIe_gson data = new Gson().fromJson(response, MyChuJIe_gson.class);
                        if (data.getMessage().equals("用户未登录。")){
                            ToastUtils.showToast(MyChuJie_one.this,"用户未登录。" );
                        }else {
                            list.addAll(data.getInvestList());
                            adapter.setonEveryItemClickListener(new MyChuJie_adapter.OnEveryItemClickListener() {
                                @Override
                                public void onEveryClick(int position) {
                                    String borrowid = String.valueOf(list.get(position).getBorrowId());
                                    String investid = String.valueOf(list.get(position).getInvestid());
                                    String typse = String.valueOf(list.get(position).getMortgageType());
                                    Intent it = new Intent(MyChuJie_one.this, ChuJIeXiangQing.class);
                                    it.putExtra("borrowid", borrowid);
                                    it.putExtra("investid", investid);
                                    it.putExtra("type", typse);
                                    startActivity(it);
                                }
                            });

                            adapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    private void getid() {
        user_id = (int) SPUtils.get(MyChuJie_one.this, "userId", 0);
        loginid = (String) SPUtils.get(MyChuJie_one.this, "Loginid", "");
        token = (String) SPUtils.get(MyChuJie_one.this, "Token1", "");
        chujie_one_fh = findViewById(R.id.chujie_one_fh);
        chujie_one_xview = findViewById(R.id.chujie_one_xview);
        LinearLayoutManager manager = new LinearLayoutManager(MyChuJie_one.this);
        chujie_one_xview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        chujie_one_xview.setLoadingListener(this);
        chujie_one_xview.setPullRefreshEnabled(true);
//        xRecyclerView.setLoadingMoreEnabled(true);
//        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        yidaoqixiangmu=findViewById(R.id.yidaoqixiangmu);
        chujie_one_xview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
    }

    @Override
    public void onRefresh() {
        list.clear();
        adapter.notifyDataSetChanged();
        a = 1;
        gethttp();
        chujie_one_xview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        a++;
        gethttp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                chujie_one_xview.loadMoreComplete();
            }
        }, 3000);

    }
}
