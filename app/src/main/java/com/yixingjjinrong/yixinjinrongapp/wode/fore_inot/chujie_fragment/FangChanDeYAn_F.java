package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_id;
import com.yixingjjinrong.yixinjinrongapp.gsondata.FangChanDiYa_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.Fangchandiya_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.yaoqing.YaoQingXiangQing;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class FangChanDeYAn_F extends Fragment implements XRecyclerView.LoadingListener {
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private XRecyclerView rview_fcdy;
    private List<FangChanDiYa_Gson.InvestListBean> list = new ArrayList<>();
    private Fangchandiya_adapter adapter;
    private int a = 1;
    private int user_id;
    private String loginid;
    private String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fangchandiyan_f, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list.clear();
        getfcdy_id();
        adapter = new Fangchandiya_adapter(list);
        rview_fcdy.setAdapter(adapter);
        getHttp();

    }

    private void getfcdy_id() {
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
        token = (String) SPUtils.get(getActivity(), "Token1", "");
        rview_fcdy = getActivity().findViewById(R.id.fangchan_diya_rview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rview_fcdy.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_fcdy.setLoadingListener(this);
        rview_fcdy.setPullRefreshEnabled(true);
        rview_fcdy.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        rview_fcdy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }


    private void getHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("borrowStatus", 1);
            js_request.put("pageNumber", a);
            js_request.put("mortgageType", 1);
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

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("TAG<>?<>Gsaonm", "" + result);
                        FangChanDiYa_Gson data = new Gson().fromJson(result, FangChanDiYa_Gson.class);
                        list.addAll(data.getInvestList());

                        adapter.notifyDataSetChanged();
                        adapter.setonEveryItemClickListener(new Fangchandiya_adapter.OnEveryItemClickListener() {
                            @Override
                            public void onEveryClick(int position) {
                                String borrowid = String.valueOf(list.get(position).getBorrowId());
                                String investid = String.valueOf(list.get(position).getInvestid());
                                Intent it = new Intent(getActivity(), ChuJIeXiangQing.class);
                                it.putExtra("borrowid", borrowid);
                                it.putExtra("investid", investid);
                                it.putExtra("type", "fang");
                                startActivity(it);

                            }
                        });
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        adapter.notifyDataSetChanged();
        a = 1;
        getHttp();
        rview_fcdy.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        a++;
        getHttp();
        rview_fcdy.loadMoreComplete();
    }
}
