package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JieKuanZiLiao_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.JieKuanZiLiao_Adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.MyScrollView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.PublicStaticClass;
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

public class JieKuanZiLiao extends Fragment {
    private RecyclerView jihuan_rview;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private List<JieKuanZiLiao_Gson.ResultBean.QualificationListBean> list=new ArrayList<>();
    private JieKuanZiLiao_Adapter adapter;
    private String borrowRandomId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.jiekuanziliaofragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list.clear();
        initview();
        getjk_id();
        getHttp_jkzl();
    }

    private void getHttp_jkzl() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("borrowRandomId",borrowRandomId);
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
        OkHttpUtils.postString()
                .url(Urls.BASE_URL+"yxbApp/Borrowingdata.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("TAG","《借款资料》GSOn"+result);
                        JieKuanZiLiao_Gson data=new Gson().fromJson(result,JieKuanZiLiao_Gson.class);
                        String urlpaht=data.getResult().getICIMAGE();
                        list.addAll(data.getResult().getQualificationList());
                        adapter=new JieKuanZiLiao_Adapter(list,urlpaht,getActivity());
                        jihuan_rview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void getjk_id() {
        jihuan_rview=getActivity().findViewById(R.id.jihuan_rview);
        GridLayoutManager gm = new GridLayoutManager(getActivity(),3);
        jihuan_rview.setLayoutManager(gm);


    }

    private void initview() {
        borrowRandomId = (String) SPUtils.get(getActivity(),"borroFwRandomId","");
        Log.e("项目借款资料", ""+borrowRandomId);
        MyScrollView jiekuanziliaoSV=getActivity().findViewById(R.id.jiekuanziliaoScrollView);
        jiekuanziliaoSV.setScrollListener(new MyScrollView.ScrollListener() {
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
