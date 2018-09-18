package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.YunYinShuJu_gson;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;

public class YunYinShuJu extends Fragment {
    private TextView pt_time, liejimoney, leijibishu, lixiyue, jiedaiyue, jiedeiyuebishu, guanlianyue, huanxibishu;//平台数据总览
    private TextView cjyonghu_time, ren_num, dqchujieren, renjunchujie_money, max_danyue, max_ten_money;//出借用户信息
    private TextView jkyonghu_time, ljjiekuan_numb, dqjiekuanren, ljjiekuan_money, max_danjiekuanyue, max_ten_jiekuanyue;//借款用户信息
    //以下逾期数据针对借款人还款情况统计
    private TextView yuequshuju_time, ljdaichangjine, ljdaichangbishu, yuqi_money, yuqibishu, jineyueqilv, xiangmuyueqilv, jine90, bishu90, xiangmufenji90, xiangmufenji180, xiangmufenji181, jinefenji90, jinefenji180, jinefenji181;
    private YunYinShuJu_gson data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yunyinshuju_f, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getid();
        gethttp();

    }

    private void gethttp() {
        OkHttpUtils.postString()
                .url(Urls.BASE_URL+"yxbApp/OperationalData.do")
                .content("")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MyLog.e("运营数据room", "" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("运营数据", "" + response);
                        data = new Gson().fromJson(response, YunYinShuJu_gson.class);
                        getshuju();
                    }
                });
    }

    private void getshuju() {
        //平台数据总览 ,,,,,,,
        pt_time.setText("（截止日期："+data.getResult().getFormattedDate()+"24:00:00）");
        liejimoney.setText(data.getResult().getList().get(0).getZong()+"元");
        leijibishu.setText(data.getResult().getList().get(0).getJiaoyicishu()+"笔");
        lixiyue.setText(data.getResult().getList().get(0).getLixiyue()+"元");
        jiedaiyue.setText(data.getResult().getList().get(0).getJiedaiyue()+"元");
        jiedeiyuebishu.setText(data.getResult().getList().get(0).getJiedaiyuecishu()+"笔");
//        guanlianyue.setText(data.getResult().getList().get(0).getRelevance()+"元");
//        huanxibishu.setText(data.getResult().getList().get(0).getRelevanceSum()+"笔");
        //出借用户信息
        cjyonghu_time.setText("（截止日期："+data.getResult().getFormattedDate()+"24:00:00）");
        ren_num.setText("亿信宝累计出借人数量为"+data.getResult().getList().get(0).getChujierenSum()+"人。");
        dqchujieren.setText(data.getResult().getList().get(0).getDangqianchuSum()+"人");
        renjunchujie_money.setText(data.getResult().getList().get(0).getRenjunchujie()+"元");
        max_danyue.setText(data.getResult().getList().get(0).getDanhuzhanbi());
        max_ten_money.setText(data.getResult().getList().get(0).getTenchujiezhanbi());
        //借款用户信息
        jkyonghu_time.setText("（截止日期："+data.getResult().getFormattedDate()+"24:00:00）");
        ljjiekuan_numb.setText("亿信宝累计借款人数量为"+data.getResult().getList().get(0).getJiekuanrenSum()+"人");
        dqjiekuanren.setText(data.getResult().getList().get(0).getDangqianjieSum()+"人");
        ljjiekuan_money.setText(data.getResult().getList().get(0).getRenjunjiekuan()+"元");
        max_danjiekuanyue.setText(data.getResult().getList().get(0).getZuidazhanbi());
        max_ten_jiekuanyue.setText(data.getResult().getList().get(0).getDaichangjine());
        //以下逾期数据针对借款人还款情况统计

        yuequshuju_time.setText("（截止日期："+data.getResult().getFormattedDate()+"24:00:00）");
        ljdaichangjine.setText(data.getResult().getList().get(0).getLeijidaichang()+"元");
        ljdaichangbishu.setText(data.getResult().getList().get(0).getDaichangcishu()+"笔");
//        yuqi_money.setText(data.getResult().getList().get(0).getYuqimonery()+"元");
//        yuqibishu.setText(data.getResult().getList().get(0).getYuqiSum()+"笔");
//        jineyueqilv.setText(data.getResult().getList().get(0).getMonerylv()+"%");
//        xiangmuyueqilv.setText(data.getResult().getList().get(0).getXiangmuyuqi()+"%");
//        jine90.setText(data.getResult().getList().get(0).getBuhanMonery()+"元");
//        bishu90.setText(data.getResult().getBuhanSum()+"笔");
//        xiangmufenji90.setText(data.getResult().getList().get(0).getFenjiyuqi()+"%");
//        xiangmufenji180.setText(data.getResult().getList().get(0).getXiangmuqi()+"%");
//        xiangmufenji181.setText(data.getResult().getList().get(0).getXiangmuyu()+"%");
//        jinefenji90.setText(data.getResult().getList().get(0).getMoneryyuqi()+"%");
//        jinefenji180.setText(data.getResult().getList().get(0).getMonerytwo()+"%");
//        jinefenji181.setText(data.getResult().getList().get(0).getMonerythree()+"%");

    }

    private void getid() {
        //平台数据总览 ,,,,,,,
        pt_time = getActivity().findViewById(R.id.pt_time);
        liejimoney = getActivity().findViewById(R.id.liejimoney);
        leijibishu = getActivity().findViewById(R.id.leijibishu);
        lixiyue = getActivity().findViewById(R.id.lixiyue);
        jiedaiyue = getActivity().findViewById(R.id.jiedaiyue);
        jiedeiyuebishu = getActivity().findViewById(R.id.jiedeiyuebishu);
        guanlianyue = getActivity().findViewById(R.id.guanlianyue);
        huanxibishu = getActivity().findViewById(R.id.huanxibishu);
        //,,,,,;//出借用户信息
        cjyonghu_time = getActivity().findViewById(R.id.cjyonghu_time);
        ren_num = getActivity().findViewById(R.id.ren_num);
        dqchujieren = getActivity().findViewById(R.id.dqchujieren);
        renjunchujie_money = getActivity().findViewById(R.id.renjunchujie_money);
        max_danyue = getActivity().findViewById(R.id.max_danyue);
        max_ten_money = getActivity().findViewById(R.id.max_ten_money);
        //,,,,,;//借款用户信息
        jkyonghu_time = getActivity().findViewById(R.id.jkyonghu_time);
        ljjiekuan_numb = getActivity().findViewById(R.id.ljjiekuan_numb);
        dqjiekuanren = getActivity().findViewById(R.id.dqjiekuanren);
        ljjiekuan_money = getActivity().findViewById(R.id.ljjiekuan_money);
        max_danjiekuanyue = getActivity().findViewById(R.id.max_danjiekuanyue);
        max_ten_jiekuanyue = getActivity().findViewById(R.id.max_ten_jiekuanyue);
        //以下逾期数据针对借款人还款情况统计
        yuequshuju_time = getActivity().findViewById(R.id.yuequshuju_time);
        ljdaichangjine = getActivity().findViewById(R.id.ljdaichangjine);
        ljdaichangbishu = getActivity().findViewById(R.id.ljdaichangbishu);
        yuqi_money = getActivity().findViewById(R.id.yuqi_money);
        yuqibishu = getActivity().findViewById(R.id.yuqibishu);
        jineyueqilv = getActivity().findViewById(R.id.jineyueqilv);
        xiangmuyueqilv = getActivity().findViewById(R.id.xiangmuyueqilv);
        jine90 = getActivity().findViewById(R.id.jine90);
        bishu90 = getActivity().findViewById(R.id.bishu90);
        xiangmufenji90 = getActivity().findViewById(R.id.xiangmufenji90);
        xiangmufenji180 = getActivity().findViewById(R.id.xiangmufenji180);
        xiangmufenji181 = getActivity().findViewById(R.id.xiangmufenji181);
        jinefenji90 = getActivity().findViewById(R.id.jinefenji90);
        jinefenji180 = getActivity().findViewById(R.id.jinefenji180);
        jinefenji181 = getActivity().findViewById(R.id.jinefenji181);

    }
}
