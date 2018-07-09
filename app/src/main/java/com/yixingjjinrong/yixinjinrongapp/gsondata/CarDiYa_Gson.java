package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class CarDiYa_Gson {

    /**
     * message : 成功了
     * result : {"userId":11210,"borrowStatus":1,"pageNumber":1,"guaranteeType":1}
     * investList : [{"paymentMode":2,"schedules":"71.00","borrowTitle":"建标优惠券","borrowStatus":2,"investTime":"2018-06-22","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"4.0","interen":0,"sortRate":9,"isDayThe":1,"borrowId":2382,"Interest":"5.0","borrowCode":"Y18D10041","investid":5186,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"922d8a5b-2f00-428b-acaa-06547c0f53a1"},{"paymentMode":4,"schedules":"100.00","borrowTitle":"借款人一次性还款05","borrowStatus":3,"investTime":"2018-06-24","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"6.0","interen":0,"sortRate":11,"isDayThe":1,"borrowId":2402,"Interest":"5.0","borrowCode":"Y18D10057","investid":5345,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"1个月","borrowRandomId":"e52b7994-c4f5-4f99-afed-3650264386bf"},{"paymentMode":4,"schedules":"100.00","borrowTitle":"借款人一次性还款05","borrowStatus":3,"investTime":"2018-06-24","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"6.0","interen":0,"sortRate":11,"isDayThe":1,"borrowId":2402,"Interest":"5.0","borrowCode":"Y18D10057","investid":5346,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"1个月","borrowRandomId":"e52b7994-c4f5-4f99-afed-3650264386bf"},{"paymentMode":4,"schedules":"100.00","borrowTitle":"借款人一次性还款05","borrowStatus":3,"investTime":"2018-06-24","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"6.0","interen":0,"sortRate":11,"isDayThe":1,"borrowId":2402,"Interest":"5.0","borrowCode":"Y18D10057","investid":5351,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"1个月","borrowRandomId":"e52b7994-c4f5-4f99-afed-3650264386bf"},{"paymentMode":4,"schedules":"100.00","borrowTitle":"借款人一次性还款05","borrowStatus":3,"investTime":"2018-06-26","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"6.0","interen":0,"sortRate":11,"isDayThe":1,"borrowId":2402,"Interest":"5.0","borrowCode":"Y18D10057","investid":5408,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"1个月","borrowRandomId":"e52b7994-c4f5-4f99-afed-3650264386bf"},{"paymentMode":4,"schedules":"100.00","borrowTitle":"借款人一次性还款05","borrowStatus":3,"investTime":"2018-06-26","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"6.0","interen":0,"sortRate":11,"isDayThe":1,"borrowId":2402,"Interest":"5.0","borrowCode":"Y18D10057","investid":5409,"subsidiesRate":5,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"1个月","borrowRandomId":"e52b7994-c4f5-4f99-afed-3650264386bf"},{"paymentMode":4,"schedules":"100.00","borrowTitle":"借款人一次性还款07","borrowStatus":3,"investTime":"2018-06-26","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"6.0","interen":0,"sortRate":6,"isDayThe":1,"borrowId":2401,"Interest":null,"borrowCode":"Y18D10056","investid":5410,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"1个月","borrowRandomId":"13f99850-d537-4432-ae51-533f1df9997f"},{"paymentMode":2,"schedules":"90.00","borrowTitle":"微信端_按月还款","borrowStatus":2,"investTime":"2018-06-26","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"6.0","interen":0,"sortRate":6,"isDayThe":1,"borrowId":2391,"Interest":null,"borrowCode":"Y18D10048","investid":5411,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"5e7e5245-6b53-4d25-8bf8-739b69e7d24d"},{"paymentMode":4,"schedules":"100.00","borrowTitle":"微信端按月还款","borrowStatus":3,"investTime":"2018-06-26","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"6.0","interen":0,"sortRate":6,"isDayThe":1,"borrowId":2394,"Interest":null,"borrowCode":"Y18D10050","investid":5412,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"2个月","borrowRandomId":"4ac5d6c3-260a-49db-a8ec-2d8574eb67b4"},{"paymentMode":2,"schedules":"90.00","borrowTitle":"微信端_按月还款","borrowStatus":2,"investTime":"2018-06-26","interey":0,"hasInterest":0,"recievedInterest":0,"annualRate":"6.0","interen":0,"sortRate":6,"isDayThe":1,"borrowId":2391,"Interest":null,"borrowCode":"Y18D10048","investid":5418,"subsidiesRate":0,"contractFileName":null,"investAmount":"100.00","lastdate":null,"deadline":"3个月","borrowRandomId":"5e7e5245-6b53-4d25-8bf8-739b69e7d24d"}]
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
         * schedules : 71.00
         * borrowTitle : 建标优惠券
         * borrowStatus : 2
         * investTime : 2018-06-22
         * interey : 0.0
         * hasInterest : 0.0
         * recievedInterest : 0.0
         * annualRate : 4.0
         * interen : 0.0
         * sortRate : 9.0
         * isDayThe : 1
         * borrowId : 2382
         * Interest : 5.0
         * borrowCode : Y18D10041
         * investid : 5186
         * subsidiesRate : 5.0
         * contractFileName : null
         * investAmount : 100.00
         * lastdate : null
         * deadline : 3个月
         * borrowRandomId : 922d8a5b-2f00-428b-acaa-06547c0f53a1
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
