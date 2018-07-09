package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class FangChanDiYa_Gson {

    /**
     * message : 成功了
     * result : {"userId":11210,"borrowStatus":1,"pageNumber":1,"guaranteeType":0}
     * investList : [{"paymentMode":2,"schedules":"100.00","borrowTitle":"达拉斯加11111","borrowStatus":3,"investTime":"2018-06-23","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":10,"isDayThe":1,"borrowId":2393,"Interest":"5.0","borrowCode":"X18D10056","investid":5280,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"0f328c58-c475-4bf9-8fce-ff26d7b4f09b"},{"paymentMode":2,"schedules":"100.00","borrowTitle":"达拉斯加11111","borrowStatus":3,"investTime":"2018-06-23","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":10,"isDayThe":1,"borrowId":2393,"Interest":"5.0","borrowCode":"X18D10056","investid":5281,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"0f328c58-c475-4bf9-8fce-ff26d7b4f09b"},{"paymentMode":2,"schedules":"100.00","borrowTitle":"达拉斯加11111","borrowStatus":3,"investTime":"2018-06-23","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":10,"isDayThe":1,"borrowId":2393,"Interest":"5.0","borrowCode":"X18D10056","investid":5285,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"0f328c58-c475-4bf9-8fce-ff26d7b4f09b"},{"paymentMode":2,"schedules":"100.00","borrowTitle":"达拉斯加11111","borrowStatus":3,"investTime":"2018-06-23","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":10,"isDayThe":1,"borrowId":2393,"Interest":"5.0","borrowCode":"X18D10056","investid":5286,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"0f328c58-c475-4bf9-8fce-ff26d7b4f09b"},{"paymentMode":2,"schedules":"100.00","borrowTitle":"达拉斯加11111","borrowStatus":3,"investTime":"2018-06-26","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":10,"isDayThe":1,"borrowId":2393,"Interest":"5.0","borrowCode":"X18D10056","investid":5417,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"0f328c58-c475-4bf9-8fce-ff26d7b4f09b"}]
     * state : success
     */

    private String message;
    private String result;
    private String state;
    private List<InvestListBean> investList;

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

    public List<InvestListBean> getInvestList() {
        return investList;
    }

    public void setInvestList(List<InvestListBean> investList) {
        this.investList = investList;
    }

    public static class InvestListBean {
        /**
         * paymentMode : 2
         * schedules : 100.00
         * borrowTitle : 达拉斯加11111
         * borrowStatus : 3
         * investTime : 2018-06-23
         * interey : 0.0
         * hasInterest : 0.0
         * recievedInterest : 0.0
         * annualRate : 5.0
         * interen : 0.0
         * sortRate : 10.0
         * isDayThe : 1
         * borrowId : 2393
         * Interest : 5.0
         * borrowCode : X18D10056
         * investid : 5280
         * subsidiesRate : 5.0
         * contractFileName : null
         * investAmount : 100.00
         * lastdate : null
         * deadline : 3个月
         * borrowRandomId : 0f328c58-c475-4bf9-8fce-ff26d7b4f09b
         */

        private int paymentMode;
        private String schedules;
        private String borrowTitle;
        private int borrowStatus;
        private String investTime;
        private double interey;
        private double hasInterest;
        private double recievedInterest;
        private String annualRate;
        private double interen;
        private double sortRate;
        private int isDayThe;
        private int borrowId;
        private String Interest;
        private String borrowCode;
        private int investid;
        private double subsidiesRate;
        private Object contractFileName;
        private String investAmount;
        private Object lastdate;
        private String deadline;
        private String borrowRandomId;

        public int getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(int paymentMode) {
            this.paymentMode = paymentMode;
        }

        public String getSchedules() {
            return schedules;
        }

        public void setSchedules(String schedules) {
            this.schedules = schedules;
        }

        public String getBorrowTitle() {
            return borrowTitle;
        }

        public void setBorrowTitle(String borrowTitle) {
            this.borrowTitle = borrowTitle;
        }

        public int getBorrowStatus() {
            return borrowStatus;
        }

        public void setBorrowStatus(int borrowStatus) {
            this.borrowStatus = borrowStatus;
        }

        public String getInvestTime() {
            return investTime;
        }

        public void setInvestTime(String investTime) {
            this.investTime = investTime;
        }

        public double getInterey() {
            return interey;
        }

        public void setInterey(double interey) {
            this.interey = interey;
        }

        public double getHasInterest() {
            return hasInterest;
        }

        public void setHasInterest(double hasInterest) {
            this.hasInterest = hasInterest;
        }

        public double getRecievedInterest() {
            return recievedInterest;
        }

        public void setRecievedInterest(double recievedInterest) {
            this.recievedInterest = recievedInterest;
        }

        public String getAnnualRate() {
            return annualRate;
        }

        public void setAnnualRate(String annualRate) {
            this.annualRate = annualRate;
        }

        public double getInteren() {
            return interen;
        }

        public void setInteren(double interen) {
            this.interen = interen;
        }

        public double getSortRate() {
            return sortRate;
        }

        public void setSortRate(double sortRate) {
            this.sortRate = sortRate;
        }

        public int getIsDayThe() {
            return isDayThe;
        }

        public void setIsDayThe(int isDayThe) {
            this.isDayThe = isDayThe;
        }

        public int getBorrowId() {
            return borrowId;
        }

        public void setBorrowId(int borrowId) {
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

        public int getInvestid() {
            return investid;
        }

        public void setInvestid(int investid) {
            this.investid = investid;
        }

        public double getSubsidiesRate() {
            return subsidiesRate;
        }

        public void setSubsidiesRate(double subsidiesRate) {
            this.subsidiesRate = subsidiesRate;
        }

        public Object getContractFileName() {
            return contractFileName;
        }

        public void setContractFileName(Object contractFileName) {
            this.contractFileName = contractFileName;
        }

        public String getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(String investAmount) {
            this.investAmount = investAmount;
        }

        public Object getLastdate() {
            return lastdate;
        }

        public void setLastdate(Object lastdate) {
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
