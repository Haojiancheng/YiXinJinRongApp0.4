package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class XiangMuJingDu_Gson {


    /**
     * message : 成功了
     * result : {"amount":"1000.00","hadRepaymentMoneyMap":{"yhqs":"0","yhje":"0.00"},"borrowStatus":"2","notYetRepaymentMoneyMap":{"dhqs":"0","dhje":"0.00"},"publishTime":"2018-07-24 14:46:09","auditTime":"","auditFullTime":"","notYetRepaymentMoneys":{"dhqs":"3","dhje":"1022.50"},"deadline":"3"}
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
         * amount : 1000.00
         * hadRepaymentMoneyMap : {"yhqs":"0","yhje":"0.00"}
         * borrowStatus : 2
         * notYetRepaymentMoneyMap : {"dhqs":"0","dhje":"0.00"}
         * publishTime : 2018-07-24 14:46:09
         * auditTime :
         * auditFullTime :
         * notYetRepaymentMoneys : {"dhqs":"3","dhje":"1022.50"}
         * deadline : 3
         */

        private String amount;
        private HadRepaymentMoneyMapBean hadRepaymentMoneyMap;
        private String borrowStatus;
        private NotYetRepaymentMoneyMapBean notYetRepaymentMoneyMap;
        private String publishTime;
        private String auditTime;
        private String auditFullTime;
        private NotYetRepaymentMoneysBean notYetRepaymentMoneys;
        private String deadline;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public HadRepaymentMoneyMapBean getHadRepaymentMoneyMap() {
            return hadRepaymentMoneyMap;
        }

        public void setHadRepaymentMoneyMap(HadRepaymentMoneyMapBean hadRepaymentMoneyMap) {
            this.hadRepaymentMoneyMap = hadRepaymentMoneyMap;
        }

        public String getBorrowStatus() {
            return borrowStatus;
        }

        public void setBorrowStatus(String borrowStatus) {
            this.borrowStatus = borrowStatus;
        }

        public NotYetRepaymentMoneyMapBean getNotYetRepaymentMoneyMap() {
            return notYetRepaymentMoneyMap;
        }

        public void setNotYetRepaymentMoneyMap(NotYetRepaymentMoneyMapBean notYetRepaymentMoneyMap) {
            this.notYetRepaymentMoneyMap = notYetRepaymentMoneyMap;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(String auditTime) {
            this.auditTime = auditTime;
        }

        public String getAuditFullTime() {
            return auditFullTime;
        }

        public void setAuditFullTime(String auditFullTime) {
            this.auditFullTime = auditFullTime;
        }

        public NotYetRepaymentMoneysBean getNotYetRepaymentMoneys() {
            return notYetRepaymentMoneys;
        }

        public void setNotYetRepaymentMoneys(NotYetRepaymentMoneysBean notYetRepaymentMoneys) {
            this.notYetRepaymentMoneys = notYetRepaymentMoneys;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public static class HadRepaymentMoneyMapBean {
            /**
             * yhqs : 0
             * yhje : 0.00
             */

            private String yhqs;
            private String yhje;

            public String getYhqs() {
                return yhqs;
            }

            public void setYhqs(String yhqs) {
                this.yhqs = yhqs;
            }

            public String getYhje() {
                return yhje;
            }

            public void setYhje(String yhje) {
                this.yhje = yhje;
            }
        }

        public static class NotYetRepaymentMoneyMapBean {
            /**
             * dhqs : 0
             * dhje : 0.00
             */

            private String dhqs;
            private String dhje;

            public String getDhqs() {
                return dhqs;
            }

            public void setDhqs(String dhqs) {
                this.dhqs = dhqs;
            }

            public String getDhje() {
                return dhje;
            }

            public void setDhje(String dhje) {
                this.dhje = dhje;
            }
        }

        public static class NotYetRepaymentMoneysBean {
            /**
             * dhqs : 3
             * dhje : 1022.50
             */

            private String dhqs;
            private String dhje;

            public String getDhqs() {
                return dhqs;
            }

            public void setDhqs(String dhqs) {
                this.dhqs = dhqs;
            }

            public String getDhje() {
                return dhje;
            }

            public void setDhje(String dhje) {
                this.dhje = dhje;
            }
        }
    }
}
