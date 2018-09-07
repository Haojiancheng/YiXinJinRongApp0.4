package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiaXiJuan_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.JiaXiJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhq;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.jieguo.ChongZhiSuccers;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment.juan_daoqi.JiaxiJuan_DaoQi;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class JianXiJuan_Fragment extends Fragment implements XRecyclerView.LoadingListener{
    private XRecyclerView jiaxi_rview;
    private List<JiaXiJuan_Gson.QueryVouchersListBean> mlist=new ArrayList<>();
    private JiaXiJuan_adapter myadapter;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private int user_id;
    private int a=1;
    private String loginid;
    private String token;
    private View jiaxi_wushuju;
    private TextView wnr_text,xj_daoqi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jiaxijuan_f, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mlist.clear();
        user_id = (int) SPUtils.get(getActivity(),"userId",0);
        getid();
        Log.e("加息卷user_id", "" + user_id);
        myadapter=new JiaXiJuan_adapter(mlist);
        Log.e("条目数", mlist.size()+"");
        jiaxi_rview.setAdapter(myadapter);
        getHttp();
        xj_daoqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), JiaxiJuan_DaoQi.class);
                startActivity(it);
            }
        });
    }

    private void getHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("activitype", "3");
            js_request.put("staut", "1");
            js_request.put("pageNumber", a);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>base加密11111!!--" + js_request);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("加密参数", "" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                //http://192.168.1.111:8080/yxb_mobile/
                .url(Urls.BASE_URL+"yxbApp/queryAll.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                         Log.e("加息卷roon",""+e );
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("加息卷GSON", response);
                        JiaXiJuan_Gson data = new Gson().fromJson(response, JiaXiJuan_Gson.class);
                        if (data.getMessage().equals("没有可用券!")) {
                            jiaxi_wushuju.setVisibility(View.VISIBLE);
                            wnr_text.setText("暂无可用加息券");
                        } else {
                            mlist.addAll(data.getQueryVouchersList());
                            myadapter.notifyDataSetChanged();
                            jiaxi_wushuju.setVisibility(View.GONE);
                            myadapter.setonEveryItemClickListener(new JiaXiJuan_adapter.OnEveryItemClickListener() {
                                @Override
                                public void onEveryClick(int position) {
                                    Intent it=new Intent(getActivity(), MainActivity.class);
                                    it.putExtra("id","1");
                                    startActivity(it);
                                    getActivity().finish();
                                }
                            });
                        }
                    }
                });
    }

    private void getid() {
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
        token = (String) SPUtils.get(getActivity(), "Token1", "");
        jiaxi_wushuju=getActivity().findViewById(R.id.jiaxi_wushuju);
        wnr_text=getActivity().findViewById(R.id.jiax_text);
        jiaxi_rview = getActivity().findViewById(R.id.jiaxi_rview);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        jiaxi_rview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        jiaxi_rview.setLoadingListener(this);
        jiaxi_rview.setPullRefreshEnabled(true);
        jiaxi_rview.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        jiaxi_rview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xj_daoqi=getActivity().findViewById(R.id.xj_daoqi);
    }

    @Override
    public void onRefresh() {
        mlist.clear();
        myadapter.notifyDataSetChanged();
        a=1;
        getHttp();
        jiaxi_rview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        a++;
        getHttp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jiaxi_rview.loadMoreComplete();
            }
        }, 2000);
        jiaxi_rview.loadMoreComplete();
    }
}
