package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing;

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
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMu_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XiangMu_Adapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class XiangMu extends Fragment implements XRecyclerView.LoadingListener{
    private XRecyclerView xRecyclerView;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private List<XiangMu_Gson.ResultBean> list = new ArrayList<>();
    private XiangMu_Adapter adapter;
    private int a = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xianmu, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getId_xm();
        getHttp();
    }

    private void getHttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

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
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/yxbAppProjectList.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "项目列表GSON>" + result);
                XiangMu_Gson data = new Gson().fromJson(result, XiangMu_Gson.class);
                list.addAll(data.getResult());
                adapter = new XiangMu_Adapter(list);
                Log.e("条目数", ""+list.size());
                xRecyclerView.setAdapter(adapter);
                int spacingInPixels = 10;
//                xRecyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
                adapter.setonEveryItemClickListener(new XiangMu_Adapter.OnEveryItemClickListener() {
                    @Override
                    public void onEveryClick(int position) {
                        String xiangmu_id = list.get(position).getBorrowRandomId();
                        Intent intent = new Intent(getActivity(), XiangMuXiangQing.class);
                        intent.putExtra("xiangmu_id", xiangmu_id);
                        Log.e("TASG","项目id:"+xiangmu_id);
                        startActivity(intent);
                    }
                });

//                adapter.notifyDataSetChanged();
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

    private void getId_xm() {
        xRecyclerView = getActivity().findViewById(R.id.xrecycview);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLoadingListener(this);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);

//        adapter=new XiangMu_Adapter(list);
//        xRecyclerView.setAdapter(adapter);
//        getHttp();
    }

    @Override
    public void onRefresh() {
        list.clear();
        a=1;
        getHttp();
        xRecyclerView.refreshComplete();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        a++;
        getHttp();

        xRecyclerView.loadMoreComplete();
    }
}
