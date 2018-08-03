package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class ZhaoHuiMIma_Gson {

    /**
     * message : 该手机未注册!
     * result : {"mapPhone":"1"}
     * state : wrong
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
         * mapPhone : 1
         */

        private String mapPhone;

        public String getMapPhone() {
            return mapPhone;
        }

        public void setMapPhone(String mapPhone) {
            this.mapPhone = mapPhone;
        }
    }
}
