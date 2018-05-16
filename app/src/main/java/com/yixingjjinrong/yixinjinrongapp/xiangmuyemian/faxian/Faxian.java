package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.AnQuanBaoZhang;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.PingTaJieShao;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.WangDaiKeTang;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.XinXiPiLu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx.JiFenDuiHuan;

public class Faxian extends Fragment {
    private TextView pingtaijieshao12,anquanbaozhang,xingxipilu,wangdaiketang,fx_gengduo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faxian2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getfaxiaid();

        getOnClickq();
      
       
    }

    private void getOnClickq() {
        pingtaijieshao12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itw=new Intent(getActivity(),PingTaJieShao.class);
                startActivity(itw);
            }
        });
        anquanbaozhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), AnQuanBaoZhang.class);
                startActivity(it);
            }
        });
        xingxipilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), XinXiPiLu.class);
                startActivity(it);
            }
        });
        wangdaiketang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), WangDaiKeTang.class);
                startActivity(it);
            }
        });
        fx_gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), JiFenDuiHuan.class);
                startActivity(it);
            }
        });
    }

    private void getfaxiaid() {
        pingtaijieshao12=getActivity().findViewById(R.id.pintaijieshao1);
        anquanbaozhang=getActivity().findViewById(R.id.anquanbaozhang);
        xingxipilu= getActivity().findViewById(R.id.xinxipilu);
        wangdaiketang=getActivity().findViewById(R.id.wangdaiketang);
        fx_gengduo=getActivity().findViewById(R.id.fx_gengduo);
    }

    
}
