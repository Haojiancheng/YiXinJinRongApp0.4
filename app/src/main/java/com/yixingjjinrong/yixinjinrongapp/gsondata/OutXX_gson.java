package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class OutXX_gson {

    /**
     * message : 成功了
     * result : {"userId":11298,"borrowId":"2804","investId":"6418","guaranteeType":0}
     * state : success
     * investDetails : {"t_borrow_style":"按月付息，到期还本","borrowTitle":"微信010","endTenderDate":"2018-09-08 17:42 ","line":"0/3","paymentModezi":"计算公式：每月应付利息=借款本金总金额×约定年化利率/12，最后一期（月）应付金额=当月应付利息+借款本金总金额。","annualRate":"5.0","guaranteeType":"0","id":"6418","borrowId":"2804","Interest":"","borrowCode":"YXB20210211","investid":"6418","investAmount":"100.00","lastdate":"","deadline":"3个月","borrowRandomId":"c7f58f96-80e6-49bf-8b0b-35e14eab2f28"}
     */

    private String message;
    private String result;
    private String state;
    private InvestDetailsBean investDetails;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public InvestDetailsBean getInvestDetails() {
        return investDetails;
    }

    public void setInvestDetails(InvestDetailsBean investDetails) {
        this.investDetails = investDetails;
    }

    public static class InvestDetailsBean {
        /**
         * t_borrow_style : 按月付息，到期还本
         * borrowTitle : 微信010
         * endTenderDate : 2018-09-08 17:42
         * line : 0/3
         * paymentModezi : 计算公式：每月应付利息=借款本金总金额×约定年化利率/12，最后一期（月）应付金额=当月应付利息+借款本金总金额。
         * annualRate : 5.0
         * guaranteeType : 0
         * id : 6418
         * borrowId : 2804
         * Interest :
         * borrowCode : YXB20210211
         * investid : 6418
         * investAmount : 100.00
         * lastdate :
         * deadline : 3个月
         * borrowRandomId : c7f58f96-80e6-49bf-8b0b-35e14eab2f28
         */

        private String t_borrow_style;
        private String borrowTitle;
        private String endTenderDate;
        private String line;
        private String paymentModezi;
        private String annualRate;
        private String guaranteeType;
        private String id;
        private String borrowId;
        private String Interest;
        private String borrowCode;
        private String investid;
        private String investAmount;
        private String lastdate;
        private String deadline;
        private String borrowRandomId;

        public String getT_borrow_style() {
            return t_borrow_style;
        }

        public void setT_borrow_style(String t_borrow_style) {
            this.t_borrow_style = t_borrow_style;
        }

        public String getBorrowTitle() {
            return borrowTitle;
        }

        public void setBorrowTitle(String borrowTitle) {
            this.borrowTitle = borrowTitle;
        }

        public String getEndTenderDate() {
            return endTenderDate;
        }

        public void setEndTenderDate(String endTenderDate) {
            this.endTenderDate = endTenderDate;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }

        public String getPaymentModezi() {
            return paymentModezi;
        }

        public void setPaymentModezi(String paymentModezi) {
            this.paymentModezi = paymentModezi;
        }

        public String getAnnualRate() {
            return annualRate;
        }

        public void setAnnualRate(String annualRate) {
            this.annualRate = annualRate;
        }

        public String getGuaranteeType() {
            return guaranteeType;
        }

        public void setGuaranteeType(String guaranteeType) {
            this.guaranteeType = guaranteeType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBorrowId() {
            return borrowId;
        }

        public void setBorrowId(String borrowId) {
            this.borrowId = borrowId;
        }

        public String getInterest() {
            return Interest;
        }

        public void setInterest(String Interest) {
            this.Interest = Interest;
        }

        public String getBorrowCode() {
            return borrowCode;
        }

        public void setBorrowCode(String borrowCode) {
            this.borrowCode = borrowCode;
        }

        public String getInvestid() {
            return investid;
        }

        public void setInvestid(String investid) {
            this.investid = investid;
        }

        public String getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(String investAmount) {
            this.investAmount = investAmount;
        }

        public String getLastdate() {
            return lastdate;
        }

        public void setLastdate(String lastdate) {
            this.lastdate = lastdate;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getBorrowRandomId() {
            return borrowRandomId;
        }

        public void setBorrowRandomId(String borrowRandomId) {
            this.borrowRandomId = borrowRandomId;
        }
    }
}
