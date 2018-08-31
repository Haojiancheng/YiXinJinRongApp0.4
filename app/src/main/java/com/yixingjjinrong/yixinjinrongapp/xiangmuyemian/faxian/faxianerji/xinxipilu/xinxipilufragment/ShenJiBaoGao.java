package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment.baogaoPDF.BaoGaoXiangQing;

public class ShenJiBaoGao extends Fragment {
    private ImageView caijin16,caijin17;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shenjibaogao_f, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getid();
        onclock();
    }

    private void onclock() {
        caijin16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), BaoGaoXiangQing.class);
                it.putExtra("pdfurl","https://www.yxb.com/pdf/2016.pdf" );
                it.putExtra("bgtitle","2016审计报告" );
                startActivity(it);
            }
        });
        caijin17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), BaoGaoXiangQing.class);
                it.putExtra("pdfurl","https://www.yxb.com/pdf/2017.pdf" );
                it.putExtra("bgtitle","2017审计报告" );
                startActivity(it);
            }
        });
    }

    private void getid() {
        caijin16=getActivity().findViewById(R.id.caijing16);
        caijin17=getActivity().findViewById(R.id.caijing17);
    }
}
