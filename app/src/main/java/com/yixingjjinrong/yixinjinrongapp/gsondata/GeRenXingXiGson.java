package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class GeRenXingXiGson {

    /**
     * message : 成功了
     * result : {"userId":11298,"token":"login:864711326104376","loginId":"login:11298"}
     * userMap : {"hasPaySum":"10512.05","phone":"186****0301","hasRePayPrincipal":"0.00","otherEarnAmount":"0.00","usableAmount":"1051228.90","freezeAmount":"493649.65","usableCreditLimit":"0.00","creditLimit":"0.00","forRePayInterest":"0.00","earnSum":"403.83","riskType":"3","forPayPrincipal":"15600.00","hasPayPrincipal":"10400.00","hasPayInterest":"112.05","risk":"1","forRePaySum":"0.00","hasRePaySum":"0.00","cashFreezeAmount":"0.00","forRePayPrincipal":"0.00","hasRePayInterest":"0.00","forPaySum":"15891.78","accountSum":"1560770.33","cg":"1","auth":"1","forPayInterest":"291.78","rateEarnAmount":"403.83"}
     * state : success
     */

    private String message;
    private String result;
    private UserMapBean userMap;
    private String state;

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

    public static class UserMapBean {
        /**
         * hasPaySum : 10512.05
         * phone : 186****0301
         * hasRePayPrincipal : 0.00
         * otherEarnAmount : 0.00
         * usableAmount : 1051228.90
         * freezeAmount : 493649.65
         * usableCreditLimit : 0.00
         * creditLimit : 0.00
         * forRePayInterest : 0.00
         * earnSum : 403.83
         * riskType : 3
         * forPayPrincipal : 15600.00
         * hasPayPrincipal : 10400.00
         * hasPayInterest : 112.05
         * risk : 1
         * forRePaySum : 0.00
         * hasRePaySum : 0.00
         * cashFreezeAmount : 0.00
         * forRePayPrincipal : 0.00
         * hasRePayInterest : 0.00
         * forPaySum : 15891.78
         * accountSum : 1560770.33
         * cg : 1
         * auth : 1
         * forPayInterest : 291.78
         * rateEarnAmount : 403.83
         */

        private String hasPaySum;
        private String phone;
        private String hasRePayPrincipal;
        private String otherEarnAmount;
        private String usableAmount;
        private String freezeAmount;
        private String usableCreditLimit;
        private String creditLimit;
        private String forRePayInterest;
        private String earnSum;
        private String riskType;
        private String forPayPrincipal;
        private String hasPayPrincipal;
        private String hasPayInterest;
        private String risk;
        private String forRePaySum;
        private String hasRePaySum;
        private String cashFreezeAmount;
        private String forRePayPrincipal;
        private String hasRePayInterest;
        private String forPaySum;
        private String accountSum;
        private String cg;
        private String auth;
        private String forPayInterest;
        private String rateEarnAmount;

        public String getHasPaySum() {
            return hasPaySum;
        }

        public void setHasPaySum(String hasPaySum) {
            this.hasPaySum = hasPaySum;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getRiskType() {
            return riskType;
        }

        public void setRiskType(String riskType) {
            this.riskType = riskType;
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

        public String getRisk() {
            return risk;
        }

        public void setRisk(String risk) {
            this.risk = risk;
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

        public String getCg() {
            return cg;
        }

        public void setCg(String cg) {
            this.cg = cg;
        }

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
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
}
