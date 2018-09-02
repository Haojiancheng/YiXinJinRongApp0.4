package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<ImageView> mylist = new ArrayList<>();
    ViewPager viewPager;

    public ViewPagerAdapter(List<ImageView> mylist, ViewPager viewPager) {
        this.mylist = mylist;
        this.viewPager=viewPager;
    }

    @Override
    public int getCount() {
        //返回有效的View的个数
        return mylist.size();
    }
    //判断是否page view与 instantiateItem(ViewGroup, int)返回的object的key 是否相同，以提供给其他的函数使用
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    //instantiateItem该方法的功能是创建指定位置的页面视图。finishUpdate(ViewGroup)返回前，页面应该保证被构造好
    //返回值：返回一个对应该页面的object，这个不一定必须是View，但是应该是对应页面的一些其他容器
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        viewPager.addView(mylist.get(position));
        return mylist.get(position);
    }
    //该方法的功能是移除一个给定位置的页面。
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        viewPager.removeView(mylist.get(position));
    }


}
