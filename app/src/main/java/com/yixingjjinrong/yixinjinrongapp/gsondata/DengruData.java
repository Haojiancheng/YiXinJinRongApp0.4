package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class DengruData {


    /**
     * message : 登录成功
     * result : {"loginId":"login:11298","token":"login:864711326104376","inviterId":"33e84","userid":11298}
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
         * loginId : login:11298
         * token : login:864711326104376
         * inviterId : 33e84
         * userid : 11298
         */

        private String loginId;
        private String token;
        private String inviterId;
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

        public String getInviterId() {
            return inviterId;
        }

        public void setInviterId(String inviterId) {
            this.inviterId = inviterId;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }
}
