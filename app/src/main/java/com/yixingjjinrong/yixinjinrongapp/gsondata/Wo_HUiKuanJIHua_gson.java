package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class Wo_HUiKuanJIHua_gson {

    /**
     * message : 成功了
     * result : {"userId":11210,"borrowId":"2393","investId":"5417"}
     * repayList : [{"id":7606,"recivedInterest":4.16,"borrowId":2393,"recivedPrincipal":0,"repayStatus":1,"repayDate":"2018-07-23","repayPeriod":"1"},{"id":7607,"recivedInterest":4.16,"borrowId":2393,"recivedPrincipal":0,"repayStatus":1,"repayDate":"2018-08-23","repayPeriod":"2"},{"id":7608,"recivedInterest":4.18,"borrowId":2393,"recivedPrincipal":1000,"repayStatus":1,"repayDate":"2018-09-23","repayPeriod":"3"}]
     * state : success
     */

    private String message;
    private String result;
    private String state;
    private List<RepayListBean> repayList;

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

    public List<RepayListBean> getRepayList() {
        return repayList;
    }

    public void setRepayList(List<RepayListBean> repayList) {
        this.repayList = repayList;
    }

    public static class RepayListBean {
        /**
         * id : 7606
         * recivedInterest : 4.16
         * borrowId : 2393
         * recivedPrincipal : 0.0
         * repayStatus : 1
         * repayDate : 2018-07-23
         * repayPeriod : 1
         */

        private int id;
        private double recivedInterest;
        private int borrowId;
        private double recivedPrincipal;
        private int repayStatus;
        private String repayDate;
        private String repayPeriod;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getRecivedInterest() {
            return recivedInterest;
        }

        public void setRecivedInterest(double recivedInterest) {
            this.recivedInterest = recivedInterest;
        }

        public int getBorrowId() {
            return borrowId;
        }

        public void setBorrowId(int borrowId) {
            this.borrowId = borrowId;
        }

        public double getRecivedPrincipal() {
            return recivedPrincipal;
        }

        public void setRecivedPrincipal(double recivedPrincipal) {
            this.recivedPrincipal = recivedPrincipal;
        }

        public int getRepayStatus() {
            return repayStatus;
        }

        public void setRepayStatus(int repayStatus) {
            this.repayStatus = repayStatus;
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
    }
}
