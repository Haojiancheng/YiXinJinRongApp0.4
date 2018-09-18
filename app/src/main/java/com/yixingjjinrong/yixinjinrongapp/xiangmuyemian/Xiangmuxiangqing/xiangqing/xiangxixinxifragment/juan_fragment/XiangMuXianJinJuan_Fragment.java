package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.juan_fragment;

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
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XianJinBean;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XianJinJuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuXiangQing_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XiangMuXianjinJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XianjinJuan_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class XiangMuXianJinJuan_Fragment extends Fragment {
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private RecyclerView xianjinjun_rview;
    private int user_id;
    private XiangMuXianjinJuan_adapter myadapter;
    private int a = 0;
    private XiangMuXiangQing_Gson.ResultBean juan;
    private List<XianJinBean.xianJuanBean> xianJuanBeanList;
    private String mymoney;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xianjinjuan_f, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        juan = (XiangMuXiangQing_Gson.ResultBean) getArguments().getSerializable("juan");
        XianJinBean xianJinBean=new XianJinBean();
        mymoney = (String) SPUtils.get(getActivity(), "mymoney", "");
        xianJuanBeanList = new ArrayList<XianJinBean.xianJuanBean>();
        getfcdy_id();

        MyLog.e("现金券user_id", "" + user_id);
    }

    private void getfcdy_id() {
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
        xianjinjun_rview = getActivity().findViewById(R.id.xianjinjuan_rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xianjinjun_rview.setLayoutManager(linearLayoutManager);
        List<XiangMuXiangQing_Gson.ResultBean.JuanBean> juanBeanList = juan.getJuan();
        for (int i = 0; i < juanBeanList.size(); i++) {
            if (juanBeanList.get(i).getActivitype() == 6) {
                XianJinBean.xianJuanBean xianJuanBean = new XianJinBean.xianJuanBean();
                xianJuanBean.setActivitype(juanBeanList.get(i).getActivitype());
                xianJuanBean.setEndTime(juanBeanList.get(i).getEndTime());
                xianJuanBean.setId(juanBeanList.get(i).getId());
                xianJuanBean.setInfo(juanBeanList.get(i).getInfo());
                xianJuanBean.setIsHot(juanBeanList.get(i).getIsHot());
                xianJuanBean.setQuota(juanBeanList.get(i).getQuota());
                xianJuanBean.setRemark(juanBeanList.get(i).getRemark());
                xianJuanBean.setStartTime(juanBeanList.get(i).getStartTime());
                xianJuanBean.setUseCondition(juanBeanList.get(i).getUseCondition());
                xianJuanBean.setUseRange(juanBeanList.get(i).getUseRange());
                xianJuanBeanList.add(xianJuanBean);
               
            }
        }
        final int i = Integer.parseInt(mymoney);
        MyLog.e("XianJinBean", xianJuanBeanList.size()+"");
        myadapter = new XiangMuXianjinJuan_adapter(xianJuanBeanList,getActivity(),i);
        xianjinjun_rview.setAdapter(myadapter);


        myadapter.setonEveryItemClickListener(new XiangMuXianjinJuan_adapter.OnEveryItemClickListener() {
            @Override
            public void onEveryClick(int position) {
                if (i>=xianJuanBeanList.get(position).getQuota()){
                    int juanId= xianJuanBeanList.get(position).getId();
                    int juantype= xianJuanBeanList.get(position).getActivitype();
                    String juanmake=xianJuanBeanList.get(position).getRemark();
                    SPUtils.put(getActivity(), "juanId", juanId);
                    SPUtils.put(getActivity(), "juantype", juantype);
                    SPUtils.put(getActivity(), "juanmake", juanmake);
                    String quota = String.valueOf(xianJuanBeanList.get(position).getQuota());
                    SPUtils.put(getActivity(), "juanjine", quota);
                    getActivity().finish();
                }else {
//                    ToastUtils.showLongToast(getActivity(), "此卷不可用");
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
