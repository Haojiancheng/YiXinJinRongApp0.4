package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class YiRenZheng_GSON {

    /**
     * message : 已经认证
     * result : {"idNo":"320***********0811","realName":"张**"}
     * state : yes
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
         * idNo : 320***********0811
         * realName : 张**
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
