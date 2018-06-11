package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class ShiMingRenZengJieGuo_gson {

    /**
     * message : 认证成功!
     * result : {"idNo":"411***********1974","realName":"金**"}
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
         * idNo : 411***********1974
         * realName : 金**
         */

        private String idNo;
        private String realName;

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }
}
