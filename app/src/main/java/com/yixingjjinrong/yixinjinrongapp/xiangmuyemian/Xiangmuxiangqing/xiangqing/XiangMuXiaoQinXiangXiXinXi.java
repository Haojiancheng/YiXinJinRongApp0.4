package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.adapter.TitleFragmentPagerAdapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.ChuJieJiLu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.HuiKuanJiHua;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.JieKuanZiLiao;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuJingDu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuXinXi;

import java.util.ArrayList;
import java.util.List;

public class XiangMuXiaoQinXiangXiXinXi extends Fragment {
    private TabLayout tab;
    private ViewPager vp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.xiangmuxiangxingxiangxixinxi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tab=getActivity().findViewById(R.id.tab);
        
        vp=getActivity().findViewById(R.id.vp);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new XiangMuXinXi());
        fragments.add(new HuiKuanJiHua());
        fragments.add(new XiangMuJingDu());
        fragments.add(new JieKuanZiLiao());
        fragments.add(new ChuJieJiLu());
        
        TitleFragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getFragmentManager(), fragments, new String[]{"项目信息", "回款计划", "项目进度","借款资料","出借记录"});
        vp.setAdapter(adapter);

        tab.setupWithViewPager(vp);
        
    }
    
}
