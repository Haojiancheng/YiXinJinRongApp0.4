package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class XiangMu_Gson {

    /**
     * message : 查询成功
     * result : [{"ableTenderDate":"2018-06-27 16:38 ","amount":"10,000.00","annualRate":1,"borrowCode":"X18D10062","borrowFrom":1,"borrowRandomId":"d89cdc35-2497-45e7-b917-a0b70445bffe","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"143阿斯顿阿斯顿","deadlineNew":"5个月","id":2435,"investNumTotal":"9,500.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"5.00%","subsidies":1,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-06-27 16:35 ","amount":"10,000.00","annualRate":4,"borrowCode":"X18D10061","borrowFrom":1,"borrowRandomId":"94c8cea1-2f8f-4afb-9b18-6e6c9642f4a7","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"梵蒂冈","deadlineNew":"5个月","id":2434,"investNumTotal":"10,000.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"0%","subsidies":4,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-06-27 16:30 ","amount":"20,000.00","annualRate":3,"borrowCode":"X18D10060","borrowFrom":1,"borrowRandomId":"30c64044-0f84-4dec-a547-1e02a163d81e","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"阿斯顿","deadlineNew":"4个月","id":2433,"investNumTotal":"20,000.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"0%","subsidies":3,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-06-27 16:15","amount":"11,111.00","annualRate":4,"borrowCode":"X18D10059","borrowFrom":1,"borrowRandomId":"501cdd96-6575-4c1e-b45a-2608b33b949b","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"asdasdasdasdasd","deadlineNew":"9个月","id":2429,"investNumTotal":"10,911.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"1.80%","subsidies":4,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-06-27 16:42","amount":"11,111.00","annualRate":1,"borrowCode":"X18D10058","borrowFrom":1,"borrowRandomId":"03491b4e-370d-47be-9af1-bf8c3f7aa483","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"阿斯顿爱的","deadlineNew":"2个月","id":2428,"investNumTotal":"11,111.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"0%","subsidies":1,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-06-23 16:45 ","amount":"11,111.00","annualRate":2,"borrowCode":"X18D10057","borrowFrom":1,"borrowRandomId":"60046ae1-75c3-420e-995d-215d58268a3c","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"阿斯顿","deadlineNew":"12个月","id":2396,"investNumTotal":"11.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"99.90%","subsidies":1,"subsidiesRate":1,"timeFlag":0},{"ableTenderDate":"2018-06-22 14:15 ","amount":"123,123.00","annualRate":3,"borrowCode":"X18D10048","borrowFrom":1,"borrowRandomId":"a714cb94-9614-48e9-a168-b80a20f27419","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"asd","deadlineNew":"3个月","id":2371,"investNumTotal":"121,923.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"0.97%","subsidies":3,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-06-22 14:05 ","amount":"10,000.00","annualRate":2,"borrowCode":"X18D10047","borrowFrom":1,"borrowRandomId":"722e2e57-60c2-4d21-bab3-aaa6cb121707","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"阿斯顿","deadlineNew":"2个月","id":2370,"investNumTotal":"9,700.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"3.00%","subsidies":1,"subsidiesRate":1,"timeFlag":0},{"ableTenderDate":"2018-06-22 10:24","amount":"700.00","annualRate":6,"borrowCode":"X18D10041","borrowFrom":1,"borrowRandomId":"6787d50b-92ca-4cd6-95fb-a10e3757c0b5","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"房屋_公司_一次性还款","deadlineNew":"3个月","id":2363,"investNumTotal":"300.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"57.14%","subsidies":6,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-06-20 11:23","amount":"6,000.00","annualRate":6,"borrowCode":"X18D10016","borrowFrom":2,"borrowRandomId":"9f7863e6-41c0-4f8b-9f8d-04686381dad8","borrowSpe":"公司借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"企业担保公司","deadlineNew":"2个月","id":2311,"investNumTotal":"4,800.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"20.00%","subsidies":6,"subsidiesRate":0,"timeFlag":0}]
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
         * ableTenderDate : 2018-06-27 16:38
         * amount : 10,000.00
         * annualRate : 1.0
         * borrowCode : X18D10062
         * borrowFrom : 1
         * borrowRandomId : d89cdc35-2497-45e7-b917-a0b70445bffe
         * borrowSpe : 个人借款
         * borrowStatus : 2
         * borrowStatusStr : 立即出借
         * borrowTitle : 143阿斯顿阿斯顿
         * deadlineNew : 5个月
         * id : 2435
         * investNumTotal : 9,500.00
         * isDayThe : 1
         * mortgageType : 1
         * mortgageTypeStr : 房产抵押
         * schedules : 5.00%
         * subsidies : 1.0
         * subsidiesRate : 0.0
         * timeFlag : 0
         */

        private String ableTenderDate;
        private String amount;
        private double annualRate;
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
        private int mortgageType;
        private String mortgageTypeStr;
        private String schedules;
        private double subsidies;
        private double subsidiesRate;
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

        public double getAnnualRate() {
            return annualRate;
        }

        public void setAnnualRate(double annualRate) {
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

        public int getMortgageType() {
            return mortgageType;
        }

        public void setMortgageType(int mortgageType) {
            this.mortgageType = mortgageType;
        }

        public String getMortgageTypeStr() {
            return mortgageTypeStr;
        }

        public void setMortgageTypeStr(String mortgageTypeStr) {
            this.mortgageTypeStr = mortgageTypeStr;
        }

        public String getSchedules() {
            return schedules;
        }

        public void setSchedules(String schedules) {
            this.schedules = schedules;
        }

        public double getSubsidies() {
            return subsidies;
        }

        public void setSubsidies(double subsidies) {
            this.subsidies = subsidies;
        }

        public double getSubsidiesRate() {
            return subsidiesRate;
        }

        public void setSubsidiesRate(double subsidiesRate) {
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
