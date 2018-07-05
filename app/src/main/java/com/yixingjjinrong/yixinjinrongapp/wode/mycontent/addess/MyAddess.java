package com.yixingjjinrong.yixinjinrongapp.wode.mycontent.addess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyAddass_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.Myaddass_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MyAddess extends AutoLayoutActivity implements XRecyclerView.LoadingListener{
    private XRecyclerView addass_rview;
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private Button addass_instat;

    int a=1;
    private List<MyAddass_Gson.ResultBean.AddressListBean> list=new ArrayList<>();
    private Myaddass_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addess);
        getaddassid();
        getaddAssHTTP();
        addass_instat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MyAddess.this,AddAddass.class);
                startActivity(it);
            }
        });
    }


    private void getaddAssHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("pageNum", a);
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
            Log.e("我的消息",""+canshu );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/addressInfoList.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("我的地址GSON", ""+result);
                MyAddass_Gson data = new Gson().fromJson(result, MyAddass_Gson.class);

                list.addAll(data.getResult().getAddressList());

                adapter=new Myaddass_adapter(list,MyAddess.this);
                addass_rview.setAdapter(adapter);
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

    private void getaddassid() {
        user_id = (int) SPUtils.get(MyAddess.this,"userId",0);
        addass_rview=findViewById(R.id.addass_rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyAddess.this, LinearLayoutManager.VERTICAL, false);
        addass_rview.setLayoutManager(linearLayoutManager);
        addass_rview.setLoadingListener(this);
        addass_rview.setPullRefreshEnabled(true);
        addass_rview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        addass_instat=findViewById(R.id.addass_instat);
    }

    @Override
    public void onRefresh() {
        list.clear();
        a=1;
        getaddAssHTTP();
        addass_rview.refreshComplete();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        a++;
        getaddAssHTTP();

        addass_rview.loadMoreComplete();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        list.clear();
        getaddAssHTTP();
        adapter.notifyDataSetChanged();
    }
}
