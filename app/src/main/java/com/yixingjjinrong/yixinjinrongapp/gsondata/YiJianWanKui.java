package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class YiJianWanKui {

    /**
     * message : 意见反馈成功!
     * result : {"contre":"1"}
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
         * contre : 1
         */

        private String contre;

        public String getContre() {
            return contre;
        }

        public void setContre(String contre) {
            this.contre = contre;
        }
    }
}
