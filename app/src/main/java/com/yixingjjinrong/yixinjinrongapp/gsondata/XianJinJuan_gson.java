package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class XianJinJuan_gson {

    /**
     * message : 成功了
     * result : {"userId":11208,"activitype":"6","staut":"1"}
     * queryVouchersList : [{"useCondition":"","startTime":"2018-05-25","id":363,"useRange":"无限制","quota":100,"remark":"满100减10元","activitype":6,"isHot":1,"endTime":"2019-05-24","info":10},{"useCondition":"","startTime":"2018-05-25","id":363,"useRange":"无限制","quota":100,"remark":"满100减10元","activitype":6,"isHot":1,"endTime":"2019-05-24","info":10},{"useCondition":"","startTime":"2018-05-25","id":363,"useRange":"无限制","quota":100,"remark":"满100减10元","activitype":6,"isHot":1,"endTime":"2019-05-24","info":10}]
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
         * startTime : 2018-05-25
         * id : 363
         * useRange : 无限制
         * quota : 100
         * remark : 满100减10元
         * activitype : 6
         * isHot : 1
         * endTime : 2019-05-24
         * info : 10
         */

        private String useCondition;
        private String startTime;
        private int id;
        private String useRange;
        private int quota;
        private String remark;
        private int activitype;
        private int isHot;
        private String endTime;
        private int info;

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

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getInfo() {
            return info;
        }

        public void setInfo(int info) {
            this.info = info;
        }
    }
}
