package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.juan_fragment;

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
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiaXiJuan_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.JiaXiJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XiangMuJiaXiJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class XiangMuJianXiJuan_Fragment extends Fragment {
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private RecyclerView jiaxi_rview;
    private List<JiaXiJuan_Gson.QueryVouchersListBean> mlist=new ArrayList<>();
    private XiangMuJiaXiJuan_adapter myadapter;
    private int user_id;
    private int a=2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jiaxijuan_f, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getid();
        user_id = (int) SPUtils.get(getActivity(),"userId",0);
        Log.e("加息卷user_id", "" + user_id);
        getHttp();
    }

    private void getHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("activitype", "3");
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
            Log.e("加密参数", "" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/queryAll.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("加息卷GSON", result);
                JiaXiJuan_Gson data = new Gson().fromJson(result, JiaXiJuan_Gson.class);
                mlist.addAll(data.getQueryVouchersList());
                myadapter=new XiangMuJiaXiJuan_adapter(mlist);
                Log.e("条目数", mlist.size()+"");
                jiaxi_rview.setAdapter(myadapter);
                myadapter.setonEveryItemClickListener(new XiangMuJiaXiJuan_adapter.OnEveryItemClickListener() {
                    @Override
                    public void onEveryClick(int position) {
//                        SPUtils.put(getActivity(), "juan", mlist.get(position).getRemark());
                        Intent it=new Intent(getActivity(), XiangMuXiangQing.class);
                        it.putExtra("juan", mlist.get(position).getRemark());
                        startActivity(it);
                    }
                });

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

    private void getid() {
        jiaxi_rview = getActivity().findViewById(R.id.jiaxi_rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        jiaxi_rview.setLayoutManager(linearLayoutManager);
    }
}
