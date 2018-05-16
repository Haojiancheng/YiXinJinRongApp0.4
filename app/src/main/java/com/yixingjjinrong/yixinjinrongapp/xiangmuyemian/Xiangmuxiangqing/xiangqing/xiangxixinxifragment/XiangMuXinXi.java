package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.MyScrollView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.PublicStaticClass;

public class XiangMuXinXi extends Fragment {
    private TextView tv_click,show,hidden;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.xiangmuxingxifragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
        settextzhenkai();
      
    }

    private void settextzhenkai() {
        tv_click=getActivity().findViewById(R.id.main_tv_click);
        show=getActivity().findViewById(R.id.main_tv_show);
        hidden=getActivity().findViewById(R.id.main_tv_hidden);
        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hidden.getVisibility()==View.VISIBLE){
                    hidden.setVisibility(View.GONE);
                    show.setVisibility(View.VISIBLE);
                    tv_click.setText("【收起】");
                }else{
                    hidden.setVisibility(View.VISIBLE);
                    show.setVisibility(View.GONE);
                    tv_click.setText("【展开】");
                }
            }
        });
    }

    private void initview() {
        MyScrollView xiangmuxingxiSV=getActivity().findViewById(R.id.xiangmuxinxScrollView);
        xiangmuxingxiSV.setScrollListener(new MyScrollView.ScrollListener() {
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
}
