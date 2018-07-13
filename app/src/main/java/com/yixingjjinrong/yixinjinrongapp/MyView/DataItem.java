package com.yixingjjinrong.yixinjinrongapp.MyView;

public class DataItem {
    /**
     * 所占值
     */
    private int value;
    /**
     * 顶部文本
     */
    private String topText;

    /**
     * 颜色
     */
    private int color;

    public DataItem(int value, String topText, int color) {
        this.value = value;
        this.topText = topText;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTopText() {
        return topText;
    }

    public void setTopText(String topText) {
        this.topText = topText;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
