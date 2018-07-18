package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.juan_fragment;

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
import com.yixingjjinrong.yixinjinrongapp.gsondata.XianJinJuan_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XiangMuXianjinJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XianjinJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class XiangMuXianJinJuan_Fragment extends Fragment {
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private RecyclerView xianjinjun_rview;
    private List<XianJinJuan_gson.QueryVouchersListBean> list = new ArrayList<>();
    private int user_id;
    private XiangMuXianjinJuan_adapter myadapter;
    private int a = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xianjinjuan_f, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list.clear();
        getfcdy_id();

        Log.e("现金券user_id", "" + user_id);
        getHttp();
    }


    private void getHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("activitype", "6");
            js_request.put("staut", "1");
            js_request.put("pageNumber", a);
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
            Log.e("现金券", "" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/queryAll.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("现金券GSon", "" + result);
                XianJinJuan_gson data = new Gson().fromJson(result, XianJinJuan_gson.class);
                list.addAll(data.getQueryVouchersList());
                myadapter = new XiangMuXianjinJuan_adapter(list);
                xianjinjun_rview.setAdapter(myadapter);
                myadapter.notifyDataSetChanged();

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

    private void getfcdy_id() {
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
        xianjinjun_rview = getActivity().findViewById(R.id.xianjinjuan_rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xianjinjun_rview.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
