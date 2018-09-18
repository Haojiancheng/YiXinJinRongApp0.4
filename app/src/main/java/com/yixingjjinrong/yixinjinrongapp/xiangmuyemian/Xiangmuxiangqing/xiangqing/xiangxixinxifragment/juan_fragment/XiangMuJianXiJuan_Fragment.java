package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.juan_fragment;

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

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiaXiBean;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiaXiJuan_Gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XianJinBean;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuXiangQing_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.JiaXiJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XiangMuJiaXiJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class XiangMuJianXiJuan_Fragment extends Fragment {
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private RecyclerView jiaxi_rview;
    private XiangMuJiaXiJuan_adapter myadapter;
    private int user_id;
    private int a=0;
    private XiangMuXiangQing_Gson.ResultBean juan;
    private List<JiaXiBean.xianJuanBean> jiaXiBeanList;
    private String mymoney;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jiaxijuan_f, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        juan = (XiangMuXiangQing_Gson.ResultBean) getArguments().getSerializable("juan");
        JiaXiBean jiaXiBean=new JiaXiBean();
        jiaXiBeanList = new ArrayList<JiaXiBean.xianJuanBean>();
        mymoney = (String) SPUtils.get(getActivity(), "mymoney", "");
        getid();

        MyLog.e("加息卷user_id", "" + user_id);
    }

    private void getid() {
        user_id = (int) SPUtils.get(getActivity(),"userId",0);
        jiaxi_rview = getActivity().findViewById(R.id.jiaxi_rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        jiaxi_rview.setLayoutManager(linearLayoutManager);
        List<XiangMuXiangQing_Gson.ResultBean.JuanBean> juanBeanList=juan.getJuan();
        for (int i=0;i<juanBeanList.size();i++){
            if (juanBeanList.get(i).getActivitype()==3) {
                JiaXiBean.xianJuanBean jiaXiaBean = new JiaXiBean.xianJuanBean();
                jiaXiaBean.setActivitype(juanBeanList.get(i).getActivitype());
                jiaXiaBean.setEndTime(juanBeanList.get(i).getEndTime());
                jiaXiaBean.setId(juanBeanList.get(i).getId());
                jiaXiaBean.setInfo(juanBeanList.get(i).getInfo());
                jiaXiaBean.setIsHot(juanBeanList.get(i).getIsHot());
                jiaXiaBean.setQuota(juanBeanList.get(i).getQuota());
                jiaXiaBean.setRemark(juanBeanList.get(i).getRemark());
                jiaXiaBean.setStartTime(juanBeanList.get(i).getStartTime());
                jiaXiaBean.setUseCondition(juanBeanList.get(i).getUseCondition());
                jiaXiaBean.setUseRange(juanBeanList.get(i).getUseRange());
                jiaXiBeanList.add(jiaXiaBean);
            }
        }
        final int i = Integer.parseInt(mymoney);

        myadapter=new XiangMuJiaXiJuan_adapter(jiaXiBeanList,i);
        jiaxi_rview.setAdapter(myadapter);
        myadapter.setonEveryItemClickListener(new XiangMuJiaXiJuan_adapter.OnEveryItemClickListener() {
            @Override
            public void onEveryClick(int position) {
                if (i>=jiaXiBeanList.get(position).getQuota()) {
                    int juanId = jiaXiBeanList.get(position).getId();
                    int juantype = jiaXiBeanList.get(position).getActivitype();
                    String juanmake = jiaXiBeanList.get(position).getRemark();
                    SPUtils.put(getActivity(), "juanId", juanId);
                    SPUtils.put(getActivity(), "juantype", juantype);
                    SPUtils.put(getActivity(), "juanmake", juanmake);
                    String quota = String.valueOf(jiaXiBeanList.get(position).getQuota());
                    SPUtils.put(getActivity(), "juanjine", quota);
                    getActivity().finish();
                }else {

                }
            }
        });
    }
}
