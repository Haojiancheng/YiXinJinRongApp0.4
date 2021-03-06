package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class ChengGongzhuce_Gson {

    /**
     * message : 注册成功!
     * result : {"loginId":"login:11966","token":"login:869381039260413","userid":11966,"enroll":"1"}
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
         * loginId : login:11966
         * token : login:869381039260413
         * userid : 11966
         * enroll : 1
         */

        private String loginId;
        private String token;
        private int userid;
        private String enroll;

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

        public String getEnroll() {
            return enroll;
        }

        public void setEnroll(String enroll) {
            this.enroll = enroll;
        }
    }
}
