package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore2;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMu_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XiangMu_Adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
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

public class XiangMu extends Fragment implements XRecyclerView.LoadingListener {
    private XRecyclerView xRecyclerView;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private List<XiangMu_Gson.ResultBean> list = new ArrayList<>();
    private XiangMu_Adapter adapter;
    private int a = 1;
    private XiangMu_Gson data;
    private PromptDialog promptDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xianmu, container, false);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xRecyclerView = getActivity().findViewById(R.id.xrecycview);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLoadingListener(this);
        xRecyclerView.setPullRefreshEnabled(true);
//        xRecyclerView.setLoadingMoreEnabled(true);
//        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        adapter = new XiangMu_Adapter(list, getActivity());
        xRecyclerView.setAdapter(adapter);

        getHttp();
    }

    private void getHttp() {
        promptDialog = new PromptDialog(getActivity());
        promptDialog.showLoading("");
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("pageNum", a);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
//            MyLog.e("TAG", ">>>>SDEWSFDREREbase加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
//            MyLog.e("TAG", ">>>>GGGGGGGSH!!" + sha1);/
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("TAG", ">>>>()base()加密11111!!--" + canshu);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/yxbAppProjectList.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                        ToastUtils.showToast(getActivity(), "网络错误，请稍后再试");
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("TAG", "项目列表GSON>" + result);
                        data = new Gson().fromJson(result, XiangMu_Gson.class);
                        list.addAll(data.getResult());
                        promptDialog.dismiss();
                        adapter.setOnEveryItemClickListener2(new XiangMu_Adapter.OnEveryItemClickListener() {
                            @Override
                            public void onEveryClick(int position) {//等级提示
                                switch (data.getResult().get(position).getBorrowSafelevel()){
                                    case 1:
                                        gettishipop("低风险","AAA","借款人市场竞争能力极强，融资能力和还款能力极强，还款意愿很好。");
                                        break;
                                    case 2:
                                        gettishipop("低风险","AA","借款人市场竞争能力很强，融资能力和还款能力很强，还款意愿很好。");
                                        break;
                                    case 3:
                                        gettishipop("低风险","A", "借款人市场竞争能力较强，融资能力和还款能力较强，还款意愿良好。");
                                        break;
                                    case 4:
                                        gettishipop("中低风险","BBB","借款人市场竞争能力一般，借款人还款能力较强，还款意愿良好。" );
                                        break;
                                    case 5:
                                        gettishipop("中低风险","BB","借款人市场竞争能力一般，借款人还款能力正常，还款意愿良好。" );
                                        break;
                                    case 6:
                                        gettishipop("中低风险","B", "借款人市场竞争能力一般，借款人还款能力尚可，还款意愿良好。");
                                        break;
                                    case 7:
                                        gettishipop("高风险","CCC","出现可能影响借款人还款能力的不利因素，存在较低概率不能按期足额偿还债务本息。" );
                                        break;
                                    case 8:
                                        gettishipop("高风险","CC","出现影响借款人还款能力的不利因素，存在较高概率不能按期足额偿还债务本息。" );
                                        break;
                                    case 9:
                                        gettishipop("高风险","C","出现影响借款人还款能力的重大不利因素，存在很高概率不能按期足额偿还债务本息。" );
                                        break;
                                    default:
                                        break;
                                }


                            }
                        });
                        adapter.setonEveryItemClickListener(new XiangMu_Adapter.OnEveryItemClickListener() {
                            @Override
                            public void onEveryClick(int position) {//项目详情
                                String mtype = String.valueOf(list.get(position).getMortgageType());
                                String xiangmu_id = list.get(position).getBorrowRandomId();
                                Intent intent = new Intent(getActivity(), XiangMuXiangQing.class);
                                intent.putExtra("mortgageType", mtype);
                                intent.putExtra("bt_name", list.get(position).getBorrowStatusStr());
                                SPUtils.put(getActivity(), "borroFwRandomId", xiangmu_id);
                                MyLog.e("TASG", "立即出借id:" + xiangmu_id);
                                startActivity(intent);
                            }
                        });

                        adapter.notifyDataSetChanged();
                    }
                });

    }

    private void gettishipop(String cc, String dengji,String message) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        View parent=((ViewGroup)getActivity().findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(getActivity(), R.layout.dengjitishi, null);
        TextView b_dengji = popView.findViewById(R.id.b_dengji);
        TextView b_message = popView.findViewById(R.id.b_messages);
        TextView fx_cengci = popView.findViewById(R.id.fx_cengci);
//        int width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        b_dengji.setText(dengji);
        b_message.setText(message);
        fx_cengci.setText(cc);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
    }

    @Subscribe
    public void lookMore(LookMore2 lookMore2) {
        this.onRefresh();
    }

    @Override
    public void onRefresh() {
        list.clear();
        adapter.notifyDataSetChanged();
        a = 1;
        getHttp();
        xRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        a++;
        getHttp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xRecyclerView.loadMoreComplete();
            }
        }, 2000);
        xRecyclerView.loadMoreComplete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
