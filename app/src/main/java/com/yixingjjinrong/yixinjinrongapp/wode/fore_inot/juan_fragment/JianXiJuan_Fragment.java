package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment;

import android.os.Bundle;
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
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiaXiJuan_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.JiaXiJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class JianXiJuan_Fragment extends Fragment implements XRecyclerView.LoadingListener{
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private XRecyclerView jiaxi_rview;
    private List<JiaXiJuan_Gson.QueryVouchersListBean> mlist=new ArrayList<>();
    private JiaXiJuan_adapter myadapter;
    private int user_id;
    private int a=1;
    private String loginid;
    private String token;
    private View jiaxi_wushuju;
    private TextView wnr_text;

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
                        if (data.getMessage().equals("成功了")) {
                            if (data.getQueryVouchersList().size() <= 0) {
                                jiaxi_wushuju.setVisibility(View.VISIBLE);
                                wnr_text.setText("暂无可用加息卷");
                            } else {
                                jiaxi_wushuju.setVisibility(View.GONE);
                                mlist.addAll(data.getQueryVouchersList());

                                myadapter.notifyDataSetChanged();
                            }
                        }else {
                            ToastUtils.showToast(getActivity(),data.getMessage() );
                            jiaxi_wushuju.setVisibility(View.VISIBLE);
                            wnr_text.setText(""+data.getMessage());
                        }
                    }
                });
    }

    private void getid() {
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
        token = (String) SPUtils.get(getActivity(), "Token1", "");
        jiaxi_wushuju=getActivity().findViewById(R.id.jiaxi_wushuju);
        wnr_text=getActivity().findViewById(R.id.wnr_text);
        jiaxi_rview = getActivity().findViewById(R.id.jiaxi_rview);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        jiaxi_rview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        jiaxi_rview.setLoadingListener(this);
        jiaxi_rview.setPullRefreshEnabled(true);
        jiaxi_rview.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        jiaxi_rview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
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
        jiaxi_rview.setNoMore(true);//数据加载完成
        jiaxi_rview.loadMoreComplete();
    }
}
