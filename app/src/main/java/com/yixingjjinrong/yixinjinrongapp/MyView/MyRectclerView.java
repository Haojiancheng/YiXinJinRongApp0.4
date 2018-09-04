package com.yixingjjinrong.yixinjinrongapp.MyView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class MyRectclerView extends RecyclerView {
    public MyRectclerView(Context context) {
        super(context);
    }

    public MyRectclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRectclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
