package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.myView.MarqueeTextView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.myView.MarqueeTextViewClickListener;

public class Shouye extends Fragment {
    public static FragmentManager fm;
    //公告栏
    private MarqueeTextView marqueeTv;
    private String[] textArrays = new String[]{"一月又一月晕晕晕晕晕晕晕晕晕晕晕晕", "this is content No.2", "this is content No.3"};

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
        marqueeTv = getActivity().findViewById(R.id.marqueeTv);
        marqueeTv.setTextArraysAndClickListener(textArrays, new MarqueeTextViewClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了公告", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
