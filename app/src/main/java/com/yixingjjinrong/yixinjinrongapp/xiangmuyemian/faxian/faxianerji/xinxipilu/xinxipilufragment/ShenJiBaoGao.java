package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yixingjjinrong.yixinjinrongapp.R;

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
    }

    private void getid() {
        caijin16=getActivity().findViewById(R.id.caijing16);
        caijin17=getActivity().findViewById(R.id.caijing17);
    }
}
