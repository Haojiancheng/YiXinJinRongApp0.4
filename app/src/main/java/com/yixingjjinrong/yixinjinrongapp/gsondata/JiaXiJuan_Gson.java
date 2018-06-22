package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class JiaXiJuan_Gson {

    /**
     * message : 成功了
     * result : {"userId":11208,"activitype":"3","staut":"1"}
     * queryVouchersList : [{"useCondition":"","startTime":"2018-05-21","id":206,"useRange":"无限制","quota":100,"remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-20","info":5},{"useCondition":"","startTime":"2018-05-21","id":206,"useRange":"无限制","quota":100,"remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-20","info":5},{"useCondition":"","startTime":"2018-05-25","id":206,"useRange":"无限制","quota":100,"remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-24","info":5},{"useCondition":"","startTime":"2018-05-25","id":206,"useRange":"无限制","quota":100,"remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-24","info":5},{"useCondition":"","startTime":"2018-05-25","id":206,"useRange":"无限制","quota":100,"remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-24","info":5}]
     * state : success
     */

    private String message;
    private String result;
    private String state;
    private List<QueryVouchersListBean> queryVouchersList;

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

    public List<QueryVouchersListBean> getQueryVouchersList() {
        return queryVouchersList;
    }

    public void setQueryVouchersList(List<QueryVouchersListBean> queryVouchersList) {
        this.queryVouchersList = queryVouchersList;
    }

    public static class QueryVouchersListBean {
        /**
         * useCondition :
         * startTime : 2018-05-21
         * id : 206
         * useRange : 无限制
         * quota : 100
         * remark : 满100元加息5%
         * activitype : 3
         * isHot :
         * endTime : 2018-09-20
         * info : 5.0
         */

        private String useCondition;
        private String startTime;
        private int id;
        private String useRange;
        private int quota;
        private String remark;
        private int activitype;
        private String isHot;
        private String endTime;
        private double info;

        public String getUseCondition() {
            return useCondition;
        }

        public void setUseCondition(String useCondition) {
            this.useCondition = useCondition;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUseRange() {
            return useRange;
        }

        public void setUseRange(String useRange) {
            this.useRange = useRange;
        }

        public int getQuota() {
            return quota;
        }

        public void setQuota(int quota) {
            this.quota = quota;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getActivitype() {
            return activitype;
        }

        public void setActivitype(int activitype) {
            this.activitype = activitype;
        }

        public String getIsHot() {
            return isHot;
        }

        public void setIsHot(String isHot) {
            this.isHot = isHot;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public double getInfo() {
            return info;
        }

        public void setInfo(double info) {
            this.info = info;
        }
    }
}
