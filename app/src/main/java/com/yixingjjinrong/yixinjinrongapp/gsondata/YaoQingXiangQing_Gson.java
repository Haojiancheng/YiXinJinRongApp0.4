package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class YaoQingXiangQing_Gson {

    /**
     * message : 成功了
     * result :
     * totalEarn : 100.55
     * state : success
     * queryAwardList : [{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"7000.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"50.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"600.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"700.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"800.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1000.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1700.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1700.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1700.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1800.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1800.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1800.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1800.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1900.00"},{"cellphone":"134****0002","realName":"*F","investAmount":"1900.00"},{"cellphone":"134****0003","realName":"*B","investAmount":"5000.00"},{"cellphone":"134****0003","realName":"*B","investAmount":"5000.00"}]
     * inviteAmount : 9
     */

    private String message;
    private String result;
    private String totalEarn;
    private String state;
    private String inviteAmount;
    private List<QueryAwardListBean> queryAwardList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTotalEarn() {
        return totalEarn;
    }

    public void setTotalEarn(String totalEarn) {
        this.totalEarn = totalEarn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInviteAmount() {
        return inviteAmount;
    }

    public void setInviteAmount(String inviteAmount) {
        this.inviteAmount = inviteAmount;
    }

    public List<QueryAwardListBean> getQueryAwardList() {
        return queryAwardList;
    }

    public void setQueryAwardList(List<QueryAwardListBean> queryAwardList) {
        this.queryAwardList = queryAwardList;
    }

    public static class QueryAwardListBean {
        /**
         * cellphone : 134****0002
         * realName : *F
         * investAmount : 900.00
         */

        private String cellphone;
        private String realName;
        private String investAmount;

        public String getCellphone() {
            return cellphone;
        }

        public void setCellphone(String cellphone) {
            this.cellphone = cellphone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(String investAmount) {
            this.investAmount = investAmount;
        }
    }
}
