package com.yixingjjinrong.yixinjinrongapp.MyView;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class MyEditTextView extends AppCompatEditText {
    public MyEditTextView(Context context) {
        super(context);
    }

    public MyEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (selStart == selEnd) { // 防止不能多选
            setSelection(getText().length()); // 保证光标始终在最后面
        }

    }
}
