package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class LJCJtwo_gson {

    /**
     * message : 成功了
     * result : {"msg":"可用余额为[0.00]元,不满足本轮投标,请充值"}
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
         * msg : 可用余额为[0.00]元,不满足本轮投标,请充值
         */

        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
