package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class YanZhengMa_gson {

    /**
     * message : 验证码发送次数达到上限，请明天再试
     * result : null
     * state : wrong
     */

    private String message;
    private Object result;
    private String state;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
