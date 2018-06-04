package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class IChuJieJiLu_Gson {

    /**
     * message : 成功了
     * result : {"investList":[{"id":4991,"username":"18620180301","investTime":"2018-05-31 17:13:03","borrowId":2274,"investor":11298,"isAutoBid":1,"creditedStatus":1,"investAmount":"100.00"}]}
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
        private List<InvestListBean> investList;

        public List<InvestListBean> getInvestList() {
            return investList;
        }

        public void setInvestList(List<InvestListBean> investList) {
            this.investList = investList;
        }

        public static class InvestListBean {
            /**
             * id : 4991
             * username : 18620180301
             * investTime : 2018-05-31 17:13:03
             * borrowId : 2274
             * investor : 11298
             * isAutoBid : 1
             * creditedStatus : 1
             * investAmount : 100.00
             */

            private int id;
            private String username;
            private String investTime;
            private int borrowId;
            private int investor;
            private int isAutoBid;
            private int creditedStatus;
            private String investAmount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getInvestTime() {
                return investTime;
            }

            public void setInvestTime(String investTime) {
                this.investTime = investTime;
            }

            public int getBorrowId() {
                return borrowId;
            }

            public void setBorrowId(int borrowId) {
                this.borrowId = borrowId;
            }

            public int getInvestor() {
                return investor;
            }

            public void setInvestor(int investor) {
                this.investor = investor;
            }

            public int getIsAutoBid() {
                return isAutoBid;
            }

            public void setIsAutoBid(int isAutoBid) {
                this.isAutoBid = isAutoBid;
            }

            public int getCreditedStatus() {
                return creditedStatus;
            }

            public void setCreditedStatus(int creditedStatus) {
                this.creditedStatus = creditedStatus;
            }

            public String getInvestAmount() {
                return investAmount;
            }

            public void setInvestAmount(String investAmount) {
                this.investAmount = investAmount;
            }
        }
    }
}
