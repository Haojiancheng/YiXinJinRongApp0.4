package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class HuiKuanJiHua_Gson {

    /**
     * message : 成功了
     * result : {"borrowStatus":2,"repaymentList":[{"borrowId":2270,"id":7283,"repayDate":"2018-06-29","repayPeriod":"1/3","repayStatus":1,"stillInterest":1.25,"stillPrincipal":0},{"borrowId":2270,"id":7284,"repayDate":"2018-07-29","repayPeriod":"2/3","repayStatus":1,"stillInterest":1.25,"stillPrincipal":0},{"borrowId":2270,"id":7285,"repayDate":"2018-08-29","repayPeriod":"3/3","repayStatus":1,"stillInterest":1.25,"stillPrincipal":500}]}
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
         * borrowStatus : 2
         * repaymentList : [{"borrowId":2270,"id":7283,"repayDate":"2018-06-29","repayPeriod":"1/3","repayStatus":1,"stillInterest":1.25,"stillPrincipal":0},{"borrowId":2270,"id":7284,"repayDate":"2018-07-29","repayPeriod":"2/3","repayStatus":1,"stillInterest":1.25,"stillPrincipal":0},{"borrowId":2270,"id":7285,"repayDate":"2018-08-29","repayPeriod":"3/3","repayStatus":1,"stillInterest":1.25,"stillPrincipal":500}]
         */

        private int borrowStatus;
        private List<RepaymentListBean> repaymentList;

        public int getBorrowStatus() {
            return borrowStatus;
        }

        public void setBorrowStatus(int borrowStatus) {
            this.borrowStatus = borrowStatus;
        }

        public List<RepaymentListBean> getRepaymentList() {
            return repaymentList;
        }

        public void setRepaymentList(List<RepaymentListBean> repaymentList) {
            this.repaymentList = repaymentList;
        }

        public static class RepaymentListBean {
            /**
             * borrowId : 2270
             * id : 7283
             * repayDate : 2018-06-29
             * repayPeriod : 1/3
             * repayStatus : 1
             * stillInterest : 1.25
             * stillPrincipal : 0.0
             */

            private int borrowId;
            private int id;
            private String repayDate;
            private String repayPeriod;
            private int repayStatus;
            private double stillInterest;
            private double stillPrincipal;

            public int getBorrowId() {
                return borrowId;
            }

            public void setBorrowId(int borrowId) {
                this.borrowId = borrowId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRepayDate() {
                return repayDate;
            }

            public void setRepayDate(String repayDate) {
                this.repayDate = repayDate;
            }

            public String getRepayPeriod() {
                return repayPeriod;
            }

            public void setRepayPeriod(String repayPeriod) {
                this.repayPeriod = repayPeriod;
            }

            public int getRepayStatus() {
                return repayStatus;
            }

            public void setRepayStatus(int repayStatus) {
                this.repayStatus = repayStatus;
            }

            public double getStillInterest() {
                return stillInterest;
            }

            public void setStillInterest(double stillInterest) {
                this.stillInterest = stillInterest;
            }

            public double getStillPrincipal() {
                return stillPrincipal;
            }

            public void setStillPrincipal(double stillPrincipal) {
                this.stillPrincipal = stillPrincipal;
            }
        }
    }
}
