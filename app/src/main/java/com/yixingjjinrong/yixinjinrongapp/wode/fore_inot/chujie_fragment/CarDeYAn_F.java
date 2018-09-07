package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CarDiYa_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.Cardiya_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class CarDeYAn_F extends Fragment implements XRecyclerView.LoadingListener{
    private XRecyclerView carrview;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private int a=1;
    private int user_id;
    private List<CarDiYa_Gson.InvestListBean> list=new ArrayList<>();
    private Cardiya_adapter adapter;
    private String loginid;
    private String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carchujie_f, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getcarId();
        list.clear();
        adapter=new Cardiya_adapter(list,getActivity());
        carrview.setAdapter(adapter);
        getcarHTTp();
    }

    private void getcarHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("borrowStatus", 1);
            js_request.put("pageNumber", a);
            js_request.put("mortgageType", 4);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>base加密11111!!--" + base1);
            Log.e("我的出借车辆表参数：", "" + js_request);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("我的出借车辆表加密：",""+canshu );
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
                        Log.e("车辆抵押GSON:L",""+response );

                        CarDiYa_Gson data = new Gson().fromJson(response, CarDiYa_Gson.class);
                        list.addAll(data.getInvestList());

                        adapter.setonEveryItemClickListener(new Cardiya_adapter.OnEveryItemClickListener() {
                            @Override
                            public void onEveryClick(int position) {
                                String borrowid = String.valueOf(list.get(position).getBorrowId());
                                String investid = String.valueOf(list.get(position).getInvestid());
                                Intent it=new Intent(getActivity(),ChuJIeXiangQing.class);
                                it.putExtra("borrowid", borrowid);
                                it.putExtra("investid", investid);
                                it.putExtra("type", "che");
                                startActivity(it);
                            }
                        });
                        adapter.notifyDataSetChanged();
                    }
                });

    }

    private void getcarId() {
        user_id = (int) SPUtils.get(getActivity(),"userId",0);
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
        token = (String) SPUtils.get(getActivity(), "Token1", "");
        carrview=getActivity().findViewById(R.id.car_daya);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        carrview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        carrview.setLoadingListener(this);
        carrview.setPullRefreshEnabled(true);
        carrview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        carrview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    @Override
    public void onRefresh() {
        adapter.notifyDataSetChanged();
        a=1;
        getcarHTTp();
        carrview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        a++;
        getcarHTTp();
        carrview.loadMoreComplete();
    }
}
