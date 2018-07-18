package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class LJCJtwo_gson {


    /**
     * message : 成功了
     * result : {"investDate":"2018-07-17","investAmount":"100","investId":"5695","msg":"1"}
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
         * investDate : 2018-07-17
         * investAmount : 100
         * investId : 5695
         * msg : 1
         */

        private String investDate;
        private String investAmount;
        private String investId;
        private String msg;

        public String getInvestDate() {
            return investDate;
        }

        public void setInvestDate(String investDate) {
            this.investDate = investDate;
        }

        public String getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(String investAmount) {
            this.investAmount = investAmount;
        }

        public String getInvestId() {
            return investId;
        }

        public void setInvestId(String investId) {
            this.investId = investId;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
