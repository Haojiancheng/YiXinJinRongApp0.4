package com.yixingjjinrong.yixinjinrongapp.gsondata;


public class LJCJONR_GSon {


    /**
     * message : 成功了
     * state : success
     * msg : {"aaa":2}
     */

    private String message;
    private String state;
    private MsgBean msg;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * aaa : 2
         */

        private int aaa;

        public int getAaa() {
            return aaa;
        }

        public void setAaa(int aaa) {
            this.aaa = aaa;
        }
    }
}
