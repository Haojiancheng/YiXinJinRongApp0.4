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
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.HuiKuanJiHua_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.HuiKuanJH_adapter;
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

public class HuiKuanJiHua extends LazyFragment {
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private RecyclerView hkjh_rview;//RecyclerView
    private List<HuiKuanJiHua_Gson.ResultBean.RepaymentListBean> hkjhlist=new ArrayList<>();
    private HuiKuanJH_adapter adapter;
    private String borrowRandomId;
    private String token1;
    private String loginid;
    private int user_id;
    private View  h_dengruchakan,w_dongru_chakan;
    private PromptDialog promptDialog;


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.huikuanjihuafragment);
        hkjhlist.clear();
        initview();
        getid_kh();
        String s = String.valueOf(user_id);
        if (s.equals("0")) {
//            Toast.makeText(getActivity(), "请先登入再查看", Toast.LENGTH_SHORT).show();
            h_dengruchakan.setVisibility(View.VISIBLE);
            w_dongru_chakan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(it);
                }
            });
        } else {
            h_dengruchakan.setVisibility(View.GONE);
            getHttp();
        }
       
    }

   
   

    private void getid_kh() {
        hkjh_rview= (RecyclerView) findViewById(R.id.hkjh_rview);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        hkjh_rview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        h_dengruchakan=findViewById(R.id.h_dengruchakan);
        w_dongru_chakan=findViewById(R.id.w_dongru_chakan);
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
                .url(Urls.BASE_URL+"yxbApp/returnedmoney.do")
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
                    public void onResponse(String response, int id) {
                        promptDialog.dismiss();
                        Log.e("回款计划Gason", "<><>,>?>?GSOn" + response);
                        HuiKuanJiHua_Gson data = new Gson().fromJson(response, HuiKuanJiHua_Gson.class);
                        String message = data.getMessage();
                        if (message.equals("用户未登录。")) {
                            Toast.makeText(getActivity(), "请先登入再查看", Toast.LENGTH_SHORT).show();
                        } else {
//                Toast.makeText(getActivity(), ""+data.getMessage(), Toast.LENGTH_SHORT).show();
                            hkjhlist.addAll(data.getResult().getRepaymentList());
                            adapter = new HuiKuanJH_adapter(hkjhlist, data.getResult().getBorrowStatus());
                            hkjh_rview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }

    private void initview() {
        borrowRandomId = (String) SPUtils.get(getActivity(),"borroFwRandomId","");
        token1 = (String) SPUtils.get(getActivity(), "Token1", "");
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
//        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
//        token = (String) SPUtils.get(getActivity(), "Token1", "");
        Log.e("项目回款计划", ""+borrowRandomId);
        MyScrollView huikuanjihuaSV= (MyScrollView) findViewById(R.id.huikuanjihuaScrollView);
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

    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
//        token1 = (String) SPUtils.get(getActivity(), "Token1", "");
//        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
//        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
//        String s = String.valueOf(user_id);
//        if (s.equals("0")) {
////            Toast.makeText(getActivity(), "请先登入再查看", Toast.LENGTH_SHORT).show();
//            h_dengruchakan.setVisibility(View.VISIBLE);
//            w_dongru_chakan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent it=new Intent(getActivity(), WoDe_DengRu.class);
//                    startActivity(it);
//                }
//            });
//        } else {
//            h_dengruchakan.setVisibility(View.GONE);
//            getHttp();
//        }
    }

  

}
