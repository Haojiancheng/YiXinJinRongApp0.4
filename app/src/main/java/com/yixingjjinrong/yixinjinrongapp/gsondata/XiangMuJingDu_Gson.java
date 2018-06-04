package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class XiangMuJingDu_Gson {

    /**
     * message : 成功了
     * result : {"amount":"500.00","auditFullTime":"","auditTime":"","borrowStatus":"2","deadline":"3","hadRepaymentMoneyMap":{"dhje":"","dhqs":"","yhje":"","yhqs":""},"publishTime":"2018-05-29 09:56:35"}
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
        /**
         * amount : 500.00
         * auditFullTime : 
         * auditTime : 
         * borrowStatus : 2
         * deadline : 3
         * hadRepaymentMoneyMap : {"dhje":"","dhqs":"","yhje":"","yhqs":""}
         * publishTime : 2018-05-29 09:56:35
         */

        private String amount;
        private String auditFullTime;
        private String auditTime;
        private String borrowStatus;
        private String deadline;
        private HadRepaymentMoneyMapBean hadRepaymentMoneyMap;
        private String publishTime;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAuditFullTime() {
            return auditFullTime;
        }

        public void setAuditFullTime(String auditFullTime) {
            this.auditFullTime = auditFullTime;
        }

        public String getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(String auditTime) {
            this.auditTime = auditTime;
        }

        public String getBorrowStatus() {
            return borrowStatus;
        }

        public void setBorrowStatus(String borrowStatus) {
            this.borrowStatus = borrowStatus;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public HadRepaymentMoneyMapBean getHadRepaymentMoneyMap() {
            return hadRepaymentMoneyMap;
        }

        public void setHadRepaymentMoneyMap(HadRepaymentMoneyMapBean hadRepaymentMoneyMap) {
            this.hadRepaymentMoneyMap = hadRepaymentMoneyMap;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public static class HadRepaymentMoneyMapBean {
            /**
             * dhje : 
             * dhqs : 
             * yhje : 
             * yhqs : 
             */

            private String dhje;
            private String dhqs;
            private String yhje;
            private String yhqs;

            public String getDhje() {
                return dhje;
            }

            public void setDhje(String dhje) {
                this.dhje = dhje;
            }

            public String getDhqs() {
                return dhqs;
            }

            public void setDhqs(String dhqs) {
                this.dhqs = dhqs;
            }

            public String getYhje() {
                return yhje;
            }

            public void setYhje(String yhje) {
                this.yhje = yhje;
            }

            public String getYhqs() {
                return yhqs;
            }

            public void setYhqs(String yhqs) {
                this.yhqs = yhqs;
            }
        }
    }
}
