package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment;

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
import com.yixingjjinrong.yixinjinrongapp.gsondata.IChuJieJiLu_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.ChuJieJiLu_Adapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.MyScrollView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.PublicStaticClass;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ChuJieJiLu extends Fragment {
    private RecyclerView chujejilu_rview;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private List<IChuJieJiLu_Gson.ResultBean.InvestListBean> list=new ArrayList<>();
    private ChuJieJiLu_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chujiejilufragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getid_cjjl();
        getHttp();

    }

    private void getHttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("borrowRandomId","722e2e57-60c2-4d21-bab3-aaa6cb121707");
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
        RequestParams params = new RequestParams(Urls.BASE_URL+"yxb_mobile/financeapp/record.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "出借记录》GSON"+result);
                IChuJieJiLu_Gson date = new Gson().fromJson(result, IChuJieJiLu_Gson.class);
                list.addAll(date.getResult().getInvestList());
                adapter=new ChuJieJiLu_Adapter(list);
                chujejilu_rview.setAdapter(adapter);
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

    private void getid_cjjl() {
        chujejilu_rview=getActivity().findViewById(R.id.chujejilu_rview);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        chujejilu_rview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
    }

    private void initView() {
        MyScrollView chujiejiluSV=getActivity().findViewById(R.id.chujiejiluScrollView);
        chujiejiluSV.setScrollListener(new MyScrollView.ScrollListener() {
            @Override
            public void onScrollToBottom() {
                
            }

            @Override
            public void onScrollToTop() {

            }

            @Override
            public void onScroll(int scrollY) {
                if (scrollY == 0) {
                    PublicStaticClass.IsTop = true;
                } else {
                    PublicStaticClass.IsTop = false;
                }
            }

            @Override
            public void notBottom() {

            }
        });
    }
}
