package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class XiangMu_Gson {

    /**
     * message : 查询成功
     * result : [{"ableTenderDate":"2018-05-23 11:23 ","amount":"10,000,000.00","annualRate":6,"borrowCode":"X18D10521","borrowFrom":2,"borrowRandomId":"1de0c7e6-7c80-463d-8f35-96e631ee3fe3","borrowSpe":"公司借款","borrowStatus":2,"borrowStatusStr":"募集中","borrowTitle":"企业房","deadlineNew":"2个月","id":2259,"investNumTotal":"9,983,100.00","isDayThe":1,"mortgageType":"房产抵押","schedules":"0.16%","subsidies":6,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-05-02 18:09 ","amount":"1,000.00","annualRate":1,"borrowCode":"X18D10440","borrowFrom":1,"borrowRandomId":"b1b6e85a-c782-41f1-be14-138ceb197ba2","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"募集中","borrowTitle":"0502第三标","deadlineNew":"3个月","id":2172,"investNumTotal":"900.00","isDayThe":1,"mortgageType":"房产抵押","schedules":"10.00%","subsidies":1,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-05-01 17:57","amount":"1,000.00","annualRate":1,"borrowCode":"X18D10439","borrowFrom":1,"borrowRandomId":"af2cefdb-cbde-4599-b336-8a74d4b6fc3d","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"募集中","borrowTitle":"0502第四标","deadlineNew":"3个月","id":2171,"investNumTotal":"900.00","isDayThe":1,"mortgageType":"房产抵押","schedules":"10.00%","subsidies":1,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-05-01 17:50","amount":"1,000.00","annualRate":1,"borrowCode":"X18D10438","borrowFrom":1,"borrowRandomId":"b868c7a6-986e-47cb-b377-6a1b53f7f84d","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"募集中","borrowTitle":"0502第三标","deadlineNew":"3个月","id":2170,"investNumTotal":"1,000.00","isDayThe":1,"mortgageType":"房产抵押","schedules":"0%","subsidies":1,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-05-01 17:05","amount":"1,000.00","annualRate":1,"borrowCode":"X18D10437","borrowFrom":1,"borrowRandomId":"ac6d5ae2-e018-4cb8-9d25-531997b8730c","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"募集中","borrowTitle":"0501第一标","deadlineNew":"3个月","id":2167,"investNumTotal":"800.00","isDayThe":1,"mortgageType":"房产抵押","schedules":"20.00%","subsidies":1,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-04-30 20:22","amount":"1,000.00","annualRate":1,"borrowCode":"X18D10436","borrowFrom":1,"borrowRandomId":"9ad1c368-87c0-4957-828a-11d0b9670dd8","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"募集中","borrowTitle":"0430第三标","deadlineNew":"3个月","id":2163,"investNumTotal":"1,000.00","isDayThe":1,"mortgageType":"房产抵押","schedules":"0%","subsidies":1,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-05-01 19:35 ","amount":"1,000.00","annualRate":1,"borrowCode":"X18D10434","borrowFrom":1,"borrowRandomId":"7cdf6e39-ccea-4e73-bf26-85e9a9f30335","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"募集中","borrowTitle":"0430第一标","deadlineNew":"1个月","id":2161,"investNumTotal":"900.00","isDayThe":1,"mortgageType":"房产抵押","schedules":"10.00%","subsidies":1,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-04-20 13:51 ","amount":"10,000.00","annualRate":1,"borrowCode":"X18D10387","borrowFrom":1,"borrowRandomId":"edc79438-5986-4b9f-a144-2e7d608f00fa","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"募集中","borrowTitle":"测试00000","deadlineNew":"3个月","id":2090,"investNumTotal":"10,000.00","isDayThe":1,"mortgageType":"房产抵押","schedules":"0%","subsidies":1,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-04-20 11:35 ","amount":"100,000.00","annualRate":1,"borrowCode":"X18D10386","borrowFrom":1,"borrowRandomId":"3723fa50-eef2-49aa-998e-d783d442f50b","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"募集中","borrowTitle":"测试99999","deadlineNew":"3个月","id":2089,"investNumTotal":"99,500.00","isDayThe":1,"mortgageType":"房产抵押","schedules":"0.50%","subsidies":1,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-04-19 16:55 ","amount":"10,000.00","annualRate":1,"borrowCode":"X18D10378","borrowFrom":1,"borrowRandomId":"8a1822b6-cce6-43aa-bd82-1a30d8779d58","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"募集中","borrowTitle":"55555","deadlineNew":"3个月","id":2081,"investNumTotal":"9,900.00","isDayThe":1,"mortgageType":"房产抵押","schedules":"1.00%","subsidies":1,"subsidiesRate":0,"timeFlag":0}]
     * state : success
     */

    private String message;
    private String state;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * ableTenderDate : 2018-05-23 11:23
         * amount : 10,000,000.00
         * annualRate : 6
         * borrowCode : X18D10521
         * borrowFrom : 2
         * borrowRandomId : 1de0c7e6-7c80-463d-8f35-96e631ee3fe3
         * borrowSpe : 公司借款
         * borrowStatus : 2
         * borrowStatusStr : 募集中
         * borrowTitle : 企业房
         * deadlineNew : 2个月
         * id : 2259
         * investNumTotal : 9,983,100.00
         * isDayThe : 1
         * mortgageType : 房产抵押
         * schedules : 0.16%
         * subsidies : 6
         * subsidiesRate : 0
         * timeFlag : 0
         */

        private String ableTenderDate;
        private String amount;
        private int annualRate;
        private String borrowCode;
        private int borrowFrom;
        private String borrowRandomId;
        private String borrowSpe;
        private int borrowStatus;
        private String borrowStatusStr;
        private String borrowTitle;
        private String deadlineNew;
        private int id;
        private String investNumTotal;
        private int isDayThe;
        private String mortgageType;
        private String schedules;
        private int subsidies;
        private int subsidiesRate;
        private int timeFlag;

        public String getAbleTenderDate() {
            return ableTenderDate;
        }

        public void setAbleTenderDate(String ableTenderDate) {
            this.ableTenderDate = ableTenderDate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getAnnualRate() {
            return annualRate;
        }

        public void setAnnualRate(int annualRate) {
            this.annualRate = annualRate;
        }

        public String getBorrowCode() {
            return borrowCode;
        }

        public void setBorrowCode(String borrowCode) {
            this.borrowCode = borrowCode;
        }

        public int getBorrowFrom() {
            return borrowFrom;
        }

        public void setBorrowFrom(int borrowFrom) {
            this.borrowFrom = borrowFrom;
        }

        public String getBorrowRandomId() {
            return borrowRandomId;
        }

        public void setBorrowRandomId(String borrowRandomId) {
            this.borrowRandomId = borrowRandomId;
        }

        public String getBorrowSpe() {
            return borrowSpe;
        }

        public void setBorrowSpe(String borrowSpe) {
            this.borrowSpe = borrowSpe;
        }

        public int getBorrowStatus() {
            return borrowStatus;
        }

        public void setBorrowStatus(int borrowStatus) {
            this.borrowStatus = borrowStatus;
        }

        public String getBorrowStatusStr() {
            return borrowStatusStr;
        }

        public void setBorrowStatusStr(String borrowStatusStr) {
            this.borrowStatusStr = borrowStatusStr;
        }

        public String getBorrowTitle() {
            return borrowTitle;
        }

        public void setBorrowTitle(String borrowTitle) {
            this.borrowTitle = borrowTitle;
        }

        public String getDeadlineNew() {
            return deadlineNew;
        }

        public void setDeadlineNew(String deadlineNew) {
            this.deadlineNew = deadlineNew;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInvestNumTotal() {
            return investNumTotal;
        }

        public void setInvestNumTotal(String investNumTotal) {
            this.investNumTotal = investNumTotal;
        }

        public int getIsDayThe() {
            return isDayThe;
        }

        public void setIsDayThe(int isDayThe) {
            this.isDayThe = isDayThe;
        }

        public String getMortgageType() {
            return mortgageType;
        }

        public void setMortgageType(String mortgageType) {
            this.mortgageType = mortgageType;
        }

        public String getSchedules() {
            return schedules;
        }

        public void setSchedules(String schedules) {
            this.schedules = schedules;
        }

        public int getSubsidies() {
            return subsidies;
        }

        public void setSubsidies(int subsidies) {
            this.subsidies = subsidies;
        }

        public int getSubsidiesRate() {
            return subsidiesRate;
        }

        public void setSubsidiesRate(int subsidiesRate) {
            this.subsidiesRate = subsidiesRate;
        }

        public int getTimeFlag() {
            return timeFlag;
        }

        public void setTimeFlag(int timeFlag) {
            this.timeFlag = timeFlag;
        }
    }
}
