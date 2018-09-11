package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class ZongE_Gson {


    /**
     * message : 成功了
     * result : {"userId":11298,"token":"login:864711326104376","loginId":"login:11298"}
     * listFreeze : [{"mortgageType":1,"sumMoney":13400},{"mortgageType":4,"sumMoney":37350},{"withdrawMoney":"361437.65"}]
     * userMap : {"hasPaySum":"10615.05","hasRePayPrincipal":"0.00","otherEarnAmount":"0.00","usableAmount":"1050431.90","freezeAmount":"494449.65","usableCreditLimit":"0.00","creditLimit":"0.00","forRePayInterest":"0.00","earnSum":"406.08","forPayPrincipal":"15600.00","hasPayPrincipal":"10500.00","hasPayInterest":"115.05","forAmount":"15891.03","forRePaySum":"0.00","hasRePaySum":"0.00","cashFreezeAmount":"0.00","forRePayPrincipal":"0.00","hasRePayInterest":"0.00","forPaySum":"15891.03","accountSum":"1560772.58","forPayInterest":"291.03","rateEarnAmount":"406.08"}
     * listType : [{"mortgageType":1,"forPaySum":"3356.97"},{"mortgageType":4,"forPaySum":"10301.06"}]
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
         * hasPaySum : 10615.05
         * hasRePayPrincipal : 0.00
         * otherEarnAmount : 0.00
         * usableAmount : 1050431.90
         * freezeAmount : 494449.65
         * usableCreditLimit : 0.00
         * creditLimit : 0.00
         * forRePayInterest : 0.00
         * earnSum : 406.08
         * forPayPrincipal : 15600.00
         * hasPayPrincipal : 10500.00
         * hasPayInterest : 115.05
         * forAmount : 15891.03
         * forRePaySum : 0.00
         * hasRePaySum : 0.00
         * cashFreezeAmount : 0.00
         * forRePayPrincipal : 0.00
         * hasRePayInterest : 0.00
         * forPaySum : 15891.03
         * accountSum : 1560772.58
         * forPayInterest : 291.03
         * rateEarnAmount : 406.08
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
         * sumMoney : 13400.0
         * withdrawMoney : 361437.65
         */

        private int mortgageType;
        private double sumMoney;
        private String withdrawMoney;

        public int getMortgageType() {
            return mortgageType;
        }

        public void setMortgageType(int mortgageType) {
            this.mortgageType = mortgageType;
        }

        public double getSumMoney() {
            return sumMoney;
        }

        public void setSumMoney(double sumMoney) {
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
         * forPaySum : 3356.97
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
