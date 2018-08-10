package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class YanZhengMa_gson {


    /**
     * message : 发送短信成功
     * result : {"jsessionId":"B71DC4450A43F45DA82989AEDC9FD8F2","succeed":"1"}
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
         * jsessionId : B71DC4450A43F45DA82989AEDC9FD8F2
         * succeed : 1
         */

        private String jsessionId;
        private String succeed;

        public String getJsessionId() {
            return jsessionId;
        }

        public void setJsessionId(String jsessionId) {
            this.jsessionId = jsessionId;
        }

        public String getSucceed() {
            return succeed;
        }

        public void setSucceed(String succeed) {
            this.succeed = succeed;
        }
    }
}
