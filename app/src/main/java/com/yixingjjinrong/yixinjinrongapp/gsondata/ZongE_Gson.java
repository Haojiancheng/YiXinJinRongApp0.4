package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class ZongE_Gson {


    /**
     * message : 成功了
     * result : {"userId":12108,"token":"login:864711326104376","loginId":"login:12108"}
     * listFreeze : [{"mortgageType":"1","sumMoney":"2700.00"},{"mortgageType":"4","sumMoney":"1000.00"},{"withdrawMoney":"200.00"}]
     * userMap : {"hasPaySum":"0.00","hasRePayPrincipal":"0.00","otherEarnAmount":"0.00","usableAmount":"10996392.18","freezeAmount":"284.00","usableCreditLimit":"0.00","creditLimit":"0.00","forRePayInterest":"0.00","earnSum":"37.03","forPayPrincipal":"3600.00","hasPayPrincipal":"0.00","hasPayInterest":"0.00","forAmount":"3637.03","forRePaySum":"0.00","hasRePaySum":"0.00","cashFreezeAmount":"0.00","forRePayPrincipal":"0.00","hasRePayInterest":"0.00","forPaySum":"3637.03","accountSum":"11000313.21","forPayInterest":"37.03","rateEarnAmount":"37.03"}
     * listType : [{"mortgageType":1,"forPaySum":"2614.53"},{"mortgageType":4,"forPaySum":"1022.50"}]
     * state : success
     */

    private String message;
    private String result;
    private UserMapBean userMap;
    private String state;
    private List<ListFreezeBean> listFreeze;
    private List<ListTypeBean> listType;

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

    public UserMapBean getUserMap() {
        return userMap;
    }

    public void setUserMap(UserMapBean userMap) {
        this.userMap = userMap;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ListFreezeBean> getListFreeze() {
        return listFreeze;
    }

    public void setListFreeze(List<ListFreezeBean> listFreeze) {
        this.listFreeze = listFreeze;
    }

    public List<ListTypeBean> getListType() {
        return listType;
    }

    public void setListType(List<ListTypeBean> listType) {
        this.listType = listType;
    }

    public static class UserMapBean {
        /**
         * hasPaySum : 0.00
         * hasRePayPrincipal : 0.00
         * otherEarnAmount : 0.00
         * usableAmount : 10996392.18
         * freezeAmount : 284.00
         * usableCreditLimit : 0.00
         * creditLimit : 0.00
         * forRePayInterest : 0.00
         * earnSum : 37.03
         * forPayPrincipal : 3600.00
         * hasPayPrincipal : 0.00
         * hasPayInterest : 0.00
         * forAmount : 3637.03
         * forRePaySum : 0.00
         * hasRePaySum : 0.00
         * cashFreezeAmount : 0.00
         * forRePayPrincipal : 0.00
         * hasRePayInterest : 0.00
         * forPaySum : 3637.03
         * accountSum : 11000313.21
         * forPayInterest : 37.03
         * rateEarnAmount : 37.03
         */

        private String hasPaySum;
        private String hasRePayPrincipal;
        private String otherEarnAmount;
        private String usableAmount;
        private String freezeAmount;
        private String usableCreditLimit;
        private String creditLimit;
        private String forRePayInterest;
        private String earnSum;
        private String forPayPrincipal;
        private String hasPayPrincipal;
        private String hasPayInterest;
        private String forAmount;
        private String forRePaySum;
        private String hasRePaySum;
        private String cashFreezeAmount;
        private String forRePayPrincipal;
        private String hasRePayInterest;
        private String forPaySum;
        private String accountSum;
        private String forPayInterest;
        private String rateEarnAmount;

        public String getHasPaySum() {
            return hasPaySum;
        }

        public void setHasPaySum(String hasPaySum) {
            this.hasPaySum = hasPaySum;
        }

        public String getHasRePayPrincipal() {
            return hasRePayPrincipal;
        }

        public void setHasRePayPrincipal(String hasRePayPrincipal) {
            this.hasRePayPrincipal = hasRePayPrincipal;
        }

        public String getOtherEarnAmount() {
            return otherEarnAmount;
        }

        public void setOtherEarnAmount(String otherEarnAmount) {
            this.otherEarnAmount = otherEarnAmount;
        }

        public String getUsableAmount() {
            return usableAmount;
        }

        public void setUsableAmount(String usableAmount) {
            this.usableAmount = usableAmount;
        }

        public String getFreezeAmount() {
            return freezeAmount;
        }

        public void setFreezeAmount(String freezeAmount) {
            this.freezeAmount = freezeAmount;
        }

        public String getUsableCreditLimit() {
            return usableCreditLimit;
        }

        public void setUsableCreditLimit(String usableCreditLimit) {
            this.usableCreditLimit = usableCreditLimit;
        }

        public String getCreditLimit() {
            return creditLimit;
        }

        public void setCreditLimit(String creditLimit) {
            this.creditLimit = creditLimit;
        }

        public String getForRePayInterest() {
            return forRePayInterest;
        }

        public void setForRePayInterest(String forRePayInterest) {
            this.forRePayInterest = forRePayInterest;
        }

        public String getEarnSum() {
            return earnSum;
        }

        public void setEarnSum(String earnSum) {
            this.earnSum = earnSum;
        }

        public String getForPayPrincipal() {
            return forPayPrincipal;
        }

        public void setForPayPrincipal(String forPayPrincipal) {
            this.forPayPrincipal = forPayPrincipal;
        }

        public String getHasPayPrincipal() {
            return hasPayPrincipal;
        }

        public void setHasPayPrincipal(String hasPayPrincipal) {
            this.hasPayPrincipal = hasPayPrincipal;
        }

        public String getHasPayInterest() {
            return hasPayInterest;
        }

        public void setHasPayInterest(String hasPayInterest) {
            this.hasPayInterest = hasPayInterest;
        }

        public String getForAmount() {
            return forAmount;
        }

        public void setForAmount(String forAmount) {
            this.forAmount = forAmount;
        }

        public String getForRePaySum() {
            return forRePaySum;
        }

        public void setForRePaySum(String forRePaySum) {
            this.forRePaySum = forRePaySum;
        }

        public String getHasRePaySum() {
            return hasRePaySum;
        }

        public void setHasRePaySum(String hasRePaySum) {
            this.hasRePaySum = hasRePaySum;
        }

        public String getCashFreezeAmount() {
            return cashFreezeAmount;
        }

        public void setCashFreezeAmount(String cashFreezeAmount) {
            this.cashFreezeAmount = cashFreezeAmount;
        }

        public String getForRePayPrincipal() {
            return forRePayPrincipal;
        }

        public void setForRePayPrincipal(String forRePayPrincipal) {
            this.forRePayPrincipal = forRePayPrincipal;
        }

        public String getHasRePayInterest() {
            return hasRePayInterest;
        }

        public void setHasRePayInterest(String hasRePayInterest) {
            this.hasRePayInterest = hasRePayInterest;
        }

        public String getForPaySum() {
            return forPaySum;
        }

        public void setForPaySum(String forPaySum) {
            this.forPaySum = forPaySum;
        }

        public String getAccountSum() {
            return accountSum;
        }

        public void setAccountSum(String accountSum) {
            this.accountSum = accountSum;
        }

        public String getForPayInterest() {
            return forPayInterest;
        }

        public void setForPayInterest(String forPayInterest) {
            this.forPayInterest = forPayInterest;
        }

        public String getRateEarnAmount() {
            return rateEarnAmount;
        }

        public void setRateEarnAmount(String rateEarnAmount) {
            this.rateEarnAmount = rateEarnAmount;
        }
    }

    public static class ListFreezeBean {
        /**
         * mortgageType : 1
         * sumMoney : 2700.00
         * withdrawMoney : 200.00
         */

        private String mortgageType;
        private String sumMoney;
        private String withdrawMoney;

        public String getMortgageType() {
            return mortgageType;
        }

        public void setMortgageType(String mortgageType) {
            this.mortgageType = mortgageType;
        }

        public String getSumMoney() {
            return sumMoney;
        }

        public void setSumMoney(String sumMoney) {
            this.sumMoney = sumMoney;
        }

        public String getWithdrawMoney() {
            return withdrawMoney;
        }

        public void setWithdrawMoney(String withdrawMoney) {
            this.withdrawMoney = withdrawMoney;
        }
    }

    public static class ListTypeBean {
        /**
         * mortgageType : 1
         * forPaySum : 2614.53
         */

        private int mortgageType;
        private String forPaySum;

        public int getMortgageType() {
            return mortgageType;
        }

        public void setMortgageType(int mortgageType) {
            this.mortgageType = mortgageType;
        }

        public String getForPaySum() {
            return forPaySum;
        }

        public void setForPaySum(String forPaySum) {
            this.forPaySum = forPaySum;
        }
    }
}
