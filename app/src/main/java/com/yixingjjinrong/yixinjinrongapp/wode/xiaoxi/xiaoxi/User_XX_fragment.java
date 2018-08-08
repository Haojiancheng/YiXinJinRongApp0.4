package com.yixingjjinrong.yixinjinrongapp.wode.xiaoxi.xiaoxi;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.User_XiaoXi_GSON;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XX_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng;
import com.yixingjjinrong.yixinjinrongapp.wode.xiaoxi.XiaoXi_XiangQing;
import com.yixingjjinrong.yixinjinrongapp.wode.zongzichen.ZongziChan;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class User_XX_fragment extends Fragment implements XRecyclerView.LoadingListener{
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private List<User_XiaoXi_GSON.ResultBean> list=new ArrayList<>();
    private XX_adapter adapter;
    int a=1;
    private XRecyclerView xxrview;
    private String loginid;
    private String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xiaoxi_f, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getxiaoxiid();
        getxiaoxiHTTP();
    }
    private void getxiaoxiHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("pageNum", a);
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
            Log.e("我的消息",""+canshu );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/queryMsgListByUserId.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("我的消息GSON:",result );
                        User_XiaoXi_GSON data = new Gson().fromJson(result, User_XiaoXi_GSON.class);
                        list.addAll(data.getResult());
                        adapter=new XX_adapter(list);
                        xxrview.setAdapter(adapter);
                        adapter.setonEveryItemClickListener(new XX_adapter.OnEveryItemClickListener() {
                            @Override
                            public void onEveryClick(int position) {
                                Toast.makeText(getActivity(), "点击了"+list.get(position).getMailTitle(), Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(getActivity(), XiaoXi_XiangQing.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("xx_ird", list.get(position).getId());
                                it.putExtras(bundle);
                                startActivity(it);
                            }
                        });
                        adapter.notifyDataSetChanged();
                    }
                });

    }

    private void getxiaoxiid() {
        user_id = (int) SPUtils.get(getActivity(),"userId",0);
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
        token = (String) SPUtils.get(getActivity(), "Token1", "");
        xxrview=getActivity().findViewById(R.id.xxrview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xxrview.setLayoutManager(linearLayoutManager);
        xxrview.setLoadingListener(this);
        xxrview.setPullRefreshEnabled(true);
        xxrview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
    }

    @Override
    public void onRefresh() {
        list.clear();
        a=1;
       getxiaoxiHTTP();
        xxrview.refreshComplete();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        a++;
        getxiaoxiHTTP();

        xxrview.loadMoreComplete();
    }
}
