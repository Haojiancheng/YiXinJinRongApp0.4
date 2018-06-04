package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class DengruData {


    /**
     * message : 登录成功
     * result : {"token":"388c76698c12365cc397da6c4396578b","userid":11520}
     * state : success
     */

    private String message;
    private ResultBean result;
    private String state;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class ResultBean {
        /**
         * token : 388c76698c12365cc397da6c4396578b
         * userid : 11520
         */

        private String token;
        private int userid;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }
}
