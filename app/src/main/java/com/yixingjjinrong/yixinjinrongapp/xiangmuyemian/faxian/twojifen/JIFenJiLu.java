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
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiFenJiLu_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.JiFenJiLu_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class JIFenJiLu extends AutoLayoutActivity implements XRecyclerView.LoadingListener{
    private XRecyclerView jilu_xrview;

    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private int a = 1;
    List<JiFenJiLu_gson.ResultBean.ListBean> list=new ArrayList<>();
    private JiFenJiLu_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifen_jilu);
        getlilu_id();
        getjiluHTTp();
    }

    private void getjiluHTTp() {
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
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/integralRecord.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
             Log.e("积分记录列表GSON:",""+result);
                JiFenJiLu_gson data = new Gson().fromJson(result, JiFenJiLu_gson.class);
                list.addAll(data.getResult().getList());
                adapter=new JiFenJiLu_adapter(list);
                jilu_xrview.setAdapter(adapter);
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

    private void getlilu_id() {
        user_id = (int) SPUtils.get(this,"userId",0);
        jilu_xrview=findViewById(R.id.jilu_rview);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        jilu_xrview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        jilu_xrview.setLoadingListener(this);
        jilu_xrview.setPullRefreshEnabled(true);
        jilu_xrview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
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
        jilu_xrview.loadMoreComplete();
    }
}
