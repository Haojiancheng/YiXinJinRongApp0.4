package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class MyChuJIe_gson {

    /**
     * message : 成功了
     * result : {"userId":11298,"borrowStatus":1,"pageNumber":1,"token":"login:864711326104376","loginId":"login:11298"}
     * investList : [{"paymentMode":4,"schedules":"10.00","borrowTitle":"测试0906002","borrowStatus":2,"investTime":"2018-09-06","interey":0,"mortgageType":4,"hasInterest":0,"recievedInterest":0,"annualRate":"9.0","interen":0,"sortRate":9,"isDayThe":1,"borrowId":2947,"Interest":null,"borrowCode":"aaaa-0906002","investid":6510,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"7da9a312-9c0c-48e6-9d73-ac078fb20c95"},{"paymentMode":4,"schedules":"100.00","borrowTitle":"测试0831005","borrowStatus":3,"investTime":"2018-09-03","interey":0,"mortgageType":1,"hasInterest":0,"recievedInterest":0,"annualRate":"9.0","interen":0,"sortRate":9,"isDayThe":1,"borrowId":2920,"Interest":null,"borrowCode":"aaaa-0831005","investid":6473,"subsidiesRate":0,"contractFileName":null,"investAmount":"900.00","lastdate":null,"deadline":"3个月","borrowRandomId":"77b67cb6-ce98-44d5-9bff-b2fb09426b05"},{"paymentMode":2,"schedules":"53.33","borrowTitle":"微信010","borrowStatus":2,"investTime":"2018-08-21","interey":0,"mortgageType":1,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":5,"isDayThe":1,"borrowId":2804,"Interest":null,"borrowCode":"YXB20210211","investid":6418,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"c7f58f96-80e6-49bf-8b0b-35e14eab2f28"},{"paymentMode":2,"schedules":"53.33","borrowTitle":"微信010","borrowStatus":2,"investTime":"2018-08-21","interey":0,"mortgageType":1,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":5,"isDayThe":1,"borrowId":2804,"Interest":null,"borrowCode":"YXB20210211","investid":6417,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"c7f58f96-80e6-49bf-8b0b-35e14eab2f28"},{"paymentMode":2,"schedules":"53.33","borrowTitle":"微信010","borrowStatus":2,"investTime":"2018-08-21","interey":0,"mortgageType":1,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":5,"isDayThe":1,"borrowId":2804,"Interest":null,"borrowCode":"YXB20210211","investid":6416,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"c7f58f96-80e6-49bf-8b0b-35e14eab2f28"},{"paymentMode":2,"schedules":"53.33","borrowTitle":"微信010","borrowStatus":2,"investTime":"2018-08-21","interey":0,"mortgageType":1,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":5,"isDayThe":1,"borrowId":2804,"Interest":null,"borrowCode":"YXB20210211","investid":6415,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"c7f58f96-80e6-49bf-8b0b-35e14eab2f28"},{"paymentMode":2,"schedules":"53.33","borrowTitle":"微信010","borrowStatus":2,"investTime":"2018-08-21","interey":0,"mortgageType":1,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":5,"isDayThe":1,"borrowId":2804,"Interest":null,"borrowCode":"YXB20210211","investid":6414,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"c7f58f96-80e6-49bf-8b0b-35e14eab2f28"},{"paymentMode":2,"schedules":"53.33","borrowTitle":"微信010","borrowStatus":2,"investTime":"2018-08-21","interey":0,"mortgageType":1,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":5,"isDayThe":1,"borrowId":2804,"Interest":null,"borrowCode":"YXB20210211","investid":6413,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"c7f58f96-80e6-49bf-8b0b-35e14eab2f28"},{"paymentMode":2,"schedules":"53.33","borrowTitle":"微信010","borrowStatus":2,"investTime":"2018-08-21","interey":0,"mortgageType":1,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":5,"isDayThe":1,"borrowId":2804,"Interest":null,"borrowCode":"YXB20210211","investid":6412,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"c7f58f96-80e6-49bf-8b0b-35e14eab2f28"},{"paymentMode":2,"schedules":"53.33","borrowTitle":"微信010","borrowStatus":2,"investTime":"2018-08-21","interey":0,"mortgageType":1,"hasInterest":0,"recievedInterest":0,"annualRate":"5.0","interen":0,"sortRate":5,"isDayThe":1,"borrowId":2804,"Interest":null,"borrowCode":"YXB20210211","investid":6411,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"c7f58f96-80e6-49bf-8b0b-35e14eab2f28"}]
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
         * paymentMode : 4
         * schedules : 10.00
         * borrowTitle : 测试0906002
         * borrowStatus : 2
         * investTime : 2018-09-06
         * interey : 0.0
         * mortgageType : 4
         * hasInterest : 0.0
         * recievedInterest : 0.0
         * annualRate : 9.0
         * interen : 0.0
         * sortRate : 9.0
         * isDayThe : 1
         * borrowId : 2947
         * Interest : null
         * borrowCode : aaaa-0906002
         * investid : 6510
         * subsidiesRate : 0.0
         * contractFileName : null
         * investAmount : 100.00
         * lastdate : null
         * deadline : 3个月
         * borrowRandomId : 7da9a312-9c0c-48e6-9d73-ac078fb20c95
         */

        private int paymentMode;
        private String schedules;
        private String borrowTitle;
        private int borrowStatus;
        private String investTime;
        private double interey;
        private int mortgageType;
        private double hasInterest;
        private double recievedInterest;
        private String annualRate;
        private double interen;
        private double sortRate;
        private int isDayThe;
        private int borrowId;
        private Object Interest;
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

        public int getMortgageType() {
            return mortgageType;
        }

        public void setMortgageType(int mortgageType) {
            this.mortgageType = mortgageType;
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

        public Object getInterest() {
            return Interest;
        }

        public void setInterest(Object Interest) {
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
