package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class YaoQingXiangQing_Gson {

    /**
     * message : 成功了
     * result :
     * totalEarn : 0.00
     * state : success
     * queryAwardList : [{"cellphone":"158******00","realName":"被*","investAmount":2000},{"cellphone":"155******01","realName":"新*","investAmount":300},{"cellphone":"158******00","realName":"被*","investAmount":1000},{"cellphone":"155******01","realName":"新*","investAmount":500},{"cellphone":"155******01","realName":"新*","investAmount":500},{"cellphone":"155******01","realName":"新*","investAmount":500}]
     * inviteAmount : 0
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
         * cellphone : 158******00
         * realName : 被*
         * investAmount : 2000
         */

        private String cellphone;
        private String realName;
        private int investAmount;

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

        public int getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(int investAmount) {
            this.investAmount = investAmount;
        }
    }
}
