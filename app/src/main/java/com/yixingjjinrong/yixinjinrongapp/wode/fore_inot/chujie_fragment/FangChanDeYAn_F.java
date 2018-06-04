package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment;

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
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_id;
import com.yixingjjinrong.yixinjinrongapp.gsondata.FangChanDiYa_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.Fangchandiya_adapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class FangChanDeYAn_F extends Fragment {
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private int user_id;
    private RecyclerView rview_fcdy;
    private List<FangChanDiYa_Gson.InvestListBean> list=new ArrayList<>();
    private Fangchandiya_adapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fangchandiyan_f, container, false);
        EventBus.getDefault().register(this);//注册
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getfcdy_id();
        getHttp();
    }

    private void getfcdy_id() {
        rview_fcdy=getActivity().findViewById(R.id.fangchan_diya_rview);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        rview_fcdy.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myMethoeed(User_id event) {

        user_id = event.getUser_id();
//        userToken = event.getUserToken();
//        getHttp();

    }

    private void getHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", 11208);
            js_request.put("borrowStatus", 1);
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

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/myInvestList.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "<>?<>Gsaonm" + result);
                FangChanDiYa_Gson data = new Gson().fromJson(result, FangChanDiYa_Gson.class);
                list.addAll(data.getInvestList());
                adapter=new Fangchandiya_adapter(list);
                rview_fcdy.setAdapter(adapter);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册
    }
}
