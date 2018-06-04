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
import com.yixingjjinrong.yixinjinrongapp.gsondata.HuiKuanJiHua_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.HuiKuanJH_adapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.MyScrollView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.PublicStaticClass;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class HuiKuanJiHua extends Fragment {
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private RecyclerView hkjh_rview;//RecyclerView
    private List<HuiKuanJiHua_Gson.ResultBean.RepaymentListBean> hkjhlist=new ArrayList<>();
    private HuiKuanJH_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.huikuanjihuafragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
        getid_kh();
        
        getHttp();
    }

    private void getid_kh() {
        hkjh_rview=getActivity().findViewById(R.id.hkjh_rview);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        hkjh_rview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
    }

    private void getHttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("borrowRandomId","ca0b5c09-57da-4b8e-ad23-35eb9f807bb4");
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
        RequestParams params = new RequestParams(Urls.BASE_URL+"yxb_mobile/financeapp/returnedmoney.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","<><>,>?>?GSOn"+result);
                HuiKuanJiHua_Gson data=new Gson().fromJson(result,HuiKuanJiHua_Gson.class);
                hkjhlist.addAll(data.getResult().getRepaymentList());
                adapter=new HuiKuanJH_adapter(hkjhlist,data.getResult().getBorrowStatus());
                hkjh_rview.setAdapter(adapter);
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

    private void initview() {
        MyScrollView huikuanjihuaSV=getActivity().findViewById(R.id.huikuanjihuaScrollView);
        huikuanjihuaSV.setScrollListener(new MyScrollView.ScrollListener() {
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
