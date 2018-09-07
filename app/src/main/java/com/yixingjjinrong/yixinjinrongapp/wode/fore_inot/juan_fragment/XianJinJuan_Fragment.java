package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchUIUtil;
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
import com.yixingjjinrong.yixinjinrongapp.gsondata.XianJinJuan_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XianjinJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.juan_fragment.juan_daoqi.XianJinJuan_YiDao;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class XianJinJuan_Fragment extends Fragment implements XRecyclerView.LoadingListener {
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private XRecyclerView xianjinjun_rview;
    private List<XianJinJuan_gson.QueryVouchersListBean> list = new ArrayList<>();
    private int user_id;
    private XianjinJuan_adapter myadapter;
    private int a = 1;
    private String loginid;
    private String token;
    private View xianjin_wushuju;
    private TextView wnr_text,xianjin_daoqi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xianjinjuan_f, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getfcdy_id();
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
        Log.e("现金券user_id", "" + user_id);
        myadapter = new XianjinJuan_adapter(list);
        xianjinjun_rview.setAdapter(myadapter);
        getHttp();
        xianjin_daoqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), XianJinJuan_YiDao.class);
                startActivity(in);
            }
        });
    }


    private void getHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("activitype", "6");
            js_request.put("staut", "1");
            js_request.put("pageNumber", a);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
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
        OkHttpUtils.postString()
                //http://192.168.1.111:8080/yxb_mobile/
                .url(Urls.BASE_URL+"yxbApp/queryAll.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("现金券GSon", "" + result);
                        XianJinJuan_gson data = new Gson().fromJson(result, XianJinJuan_gson.class);
                        if (data.getMessage().equals("没有可用券!")) {
                                xianjin_wushuju.setVisibility(View.VISIBLE);
                                wnr_text.setText("暂无可用现金券");
                        } else {
                            list.addAll(data.getQueryVouchersList());
                            myadapter.notifyDataSetChanged();
                            xianjin_wushuju.setVisibility(View.GONE);
                            myadapter.setonEveryItemClickListener(new XianjinJuan_adapter.OnEveryItemClickListener() {
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

    private void getfcdy_id() {
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
        token = (String) SPUtils.get(getActivity(), "Token1", "");
        xianjinjun_rview = getActivity().findViewById(R.id.xianjinjuan_rview);
        xianjin_wushuju = getActivity().findViewById(R.id.xianjin_wushuju);
        xianjin_daoqi=getActivity().findViewById(R.id.xianjin_daoqi);
        wnr_text = getActivity().findViewById(R.id.wnr_text);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        xianjinjun_rview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xianjinjun_rview.setLoadingListener(this);
        xianjinjun_rview.setPullRefreshEnabled(true);
        xianjinjun_rview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        xianjinjun_rview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        list.clear();
        myadapter.notifyDataSetChanged();
        a = 1;
        getHttp();
        xianjinjun_rview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        a++;
        getHttp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xianjinjun_rview.loadMoreComplete();
            }
        }, 2000);
        xianjinjun_rview.loadMoreComplete();
    }
}
