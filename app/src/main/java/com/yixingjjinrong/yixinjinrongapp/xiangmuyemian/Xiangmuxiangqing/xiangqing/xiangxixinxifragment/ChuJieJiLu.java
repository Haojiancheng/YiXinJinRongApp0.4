package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment;

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
import com.yixingjjinrong.yixinjinrongapp.gsondata.IChuJieJiLu_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.ChuJieJiLu_Adapter;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XiangMu_Adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.MyScrollView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.PublicStaticClass;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.fragmentuits.LazyFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class ChuJieJiLu extends LazyFragment implements XRecyclerView.LoadingListener{
    private XRecyclerView chujejilu_rview;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private List<IChuJieJiLu_Gson.ResultBean.InvestListBean> list=new ArrayList<>();
    private ChuJieJiLu_Adapter adapter;
    private String borrowRandomId;
    int a=1;
    private String token1;
    private String loginid;
    private View cjjl_dengruchakan;
    private PromptDialog promptDialog;


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.chujiejilufragment);
        list.clear();
        initView();
        chujejilu_rview=getActivity().findViewById(R.id.chujejilu_rview);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        chujejilu_rview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        chujejilu_rview.setLoadingListener(this);
        chujejilu_rview.setPullRefreshEnabled(true);
        chujejilu_rview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        chujejilu_rview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        adapter=new ChuJieJiLu_Adapter(list);
        chujejilu_rview.setAdapter(adapter);
        getHttp();
    }

    private void getHttp() {
        promptDialog = new PromptDialog(getActivity());
        promptDialog.showLoading("");
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("borrowRandomId",borrowRandomId);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
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
                .url(Urls.BASE_URL+"yxbApp/record.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                        ToastUtils.showToast(getActivity(),"网络异常" );
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        promptDialog.dismiss();
                        Log.e("TAG出借记录》GSON", "" + result);
                        IChuJieJiLu_Gson date = new Gson().fromJson(result, IChuJieJiLu_Gson.class);
                        String message = date.getMessage();
                        if (message.equals("用户未登录。")) {
                            Toast.makeText(getActivity(), "请先登入再查看", Toast.LENGTH_SHORT).show();
                            cjjl_dengruchakan.setVisibility(View.VISIBLE);
                            cjjl_dengruchakan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent it=new Intent(getActivity(), WoDe_DengRu.class);
                                    startActivity(it);
                                    getActivity().finish();
                                }
                            });
                        } else {
                            cjjl_dengruchakan.setVisibility(View.GONE);
                            list.addAll(date.getResult().getInvestList());

                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }



    private void initView() {
        token1 = (String) SPUtils.get(getActivity(), "Token1", "");
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
        borrowRandomId = (String) SPUtils.get(getActivity(),"borroFwRandomId","");
        cjjl_dengruchakan=getActivity().findViewById(R.id.cjjl_dengruchakan);
        Log.e("项目出借记录", ""+borrowRandomId);
        MyScrollView chujiejiluSV= (MyScrollView) findViewById(R.id.chujiejiluScrollView);
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

    @Override
    public void onRefresh() {
        adapter.notifyDataSetChanged();
        a=1;
        getHttp();
        chujejilu_rview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        a++;
        getHttp();
        chujejilu_rview.loadMoreComplete();
    }
}
