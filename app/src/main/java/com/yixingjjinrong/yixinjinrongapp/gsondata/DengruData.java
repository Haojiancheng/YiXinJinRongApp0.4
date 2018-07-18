package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class DengruData {

    /**
     * message : 登录成功
     * result : {"loginId":"login:11817","token":"login:868550035271969","userid":11817}
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
         * loginId : login:11817
         * token : login:868550035271969
         * userid : 11817
         */

        private String loginId;
        private String token;
        private int userid;

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

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
