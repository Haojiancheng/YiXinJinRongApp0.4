package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.twojifen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DuiHuanJiLu_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.DuiHuanJiLu_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class DuiHuanJILu extends AutoLayoutActivity implements XRecyclerView.LoadingListener {
    private XRecyclerView duihuan_xrview;

    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private int a = 1;
    List<DuiHuanJiLu_gson.ResultBean.ListBean> list=new ArrayList<>();
    private DuiHuanJiLu_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duihuan_jilu);
        getduihuanid();
        getfuihuanHTTp();
    }

    private void getfuihuanHTTp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("userId", user_id);
            js_request.put("pageNum", a);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>SDEWSFDREREbase加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>GGGGGGGSH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>()base()加密11111!!--" + base1);
            Log.e("TAG", ">>>>()sha1()加密11111!!--" + sha1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/exchangeRecord.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("兑换记录GSON:",result );
                DuiHuanJiLu_gson data = new Gson().fromJson(result, DuiHuanJiLu_gson.class);
                list.addAll(data.getResult().getList());
                adapter=new DuiHuanJiLu_adapter(list);
                duihuan_xrview.setAdapter(adapter);

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

    private void getduihuanid() {
        user_id = (int) SPUtils.get(this,"userId",0);
        duihuan_xrview=findViewById(R.id.duhuan_rview);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        duihuan_xrview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        duihuan_xrview.setLoadingListener(this);
        duihuan_xrview.setPullRefreshEnabled(true);
        duihuan_xrview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
    }

    @Override
    public void onRefresh() {
        list.clear();
        a=1;
        getfuihuanHTTp();
        duihuan_xrview.refreshComplete();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        a++;
        getfuihuanHTTp();
        duihuan_xrview.loadMoreComplete();
    }
}