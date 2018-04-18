package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.myView.NoticeView;

import java.util.ArrayList;
import java.util.List;

public class Shouye extends Fragment {

    //公告栏
    private NoticeView noticeView,noticeTime;
   
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye, container, false);
        return view;
    }
    

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //公告栏
        noticeView=getActivity().findViewById(R.id.notice_view);
        noticeTime=getActivity().findViewById(R.id.noticeTime);
        List<String> notices = new ArrayList<>();
        notices.add("大促销下单拆福袋，亿万新年红包随便拿");
        notices.add("家电五折团，抢十亿无门槛现金红包dddddd");
        notices.add("星球大战剃须刀首发送200元代金券ddddd");
        noticeView.addNotice(notices);
        noticeView.startFlipping();
        //时间
        List<String> notTime=new ArrayList<>();
        notTime.add("2018-09-25");
        notTime.add("2018-09-25");
        notTime.add("2018-09-25");
        noticeTime.addNotice(notTime);
        noticeTime.startFlipping();
        
    }
}
