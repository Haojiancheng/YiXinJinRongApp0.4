package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.zijinliushui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ZiJInLiuShu_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.ZiJinLiuShui_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ZiJinliushui extends AutoLayoutActivity implements XRecyclerView.LoadingListener{
    private XRecyclerView xrview;
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private int a=1;
    private List<ZiJInLiuShu_gson.FundRecordlistBean> list=new ArrayList<>();
    private ZiJinLiuShui_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_jinliushui);
        getlsID();
        getlsHTTP();
    }

    private void getlsHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
//            js_request.put("pageNumber", a);
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
            Log.e("我的资金流水加密：",""+canshu );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/getFundrecordList.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("我的资金流水GSon","" +result);
                ZiJInLiuShu_gson data = new Gson().fromJson(result, ZiJInLiuShu_gson.class);
                list.addAll(data.getFundRecordlist());
                adapter=new ZiJinLiuShui_adapter(list);
                xrview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
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

    private void getlsID() {
        user_id = (int) SPUtils.get(this,"userId",0);
        xrview=findViewById(R.id.liushuirview);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        xrview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xrview.setLoadingListener(this);
        xrview.setPullRefreshEnabled(true);
        xrview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        xrview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    @Override
    public void onRefresh() {
        adapter.notifyDataSetChanged();
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
