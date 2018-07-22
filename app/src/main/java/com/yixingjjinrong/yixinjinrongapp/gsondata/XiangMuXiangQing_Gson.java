package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.io.Serializable;
import java.util.List;

public class XiangMuXiangQing_Gson implements Serializable{

    /**
     * message : 成功了
     * result : {"userMap":{"usertype":"1","usableSum":"188322.00"},"redList1":{"ranaa":6,"ableTenderDate":"2018-06-28 15:51","paymentMode":"","borrowStatus":2,"borrowStatusStr":"立即出借","ran":6,"timeFlag":"0","borrowSum":"800元","rans":0,"surplus":"400.00","deadline":"3个月"},"juan":[{"useCondition":"","startTime":"2018-07-18","id":368,"quota":1000,"useRange":"房屋抵押,助学计划,项目集,车辆抵押,","remark":"满1000减108元","activitype":6,"isHot":"1","endTime":"2018-07-27","info":108},{"useCondition":"","startTime":"2018-07-18","id":369,"quota":5000,"useRange":"房屋抵押,助学计划,项目集,车辆抵押,","remark":"满5000减66元","activitype":6,"isHot":"1","endTime":"2018-07-27","info":66},{"useCondition":"","startTime":"2018-07-18","id":370,"quota":1000,"useRange":"房屋抵押,助学计划,项目集,车辆抵押,","remark":"满1000减15元","activitype":6,"isHot":"1","endTime":"2018-07-27","info":15},{"useCondition":"","startTime":"2018-07-18","id":371,"quota":500,"useRange":"房屋抵押,助学计划,项目集,车辆抵押,","remark":"满500减10元","activitype":6,"isHot":"1","endTime":"2018-07-27","info":10}],"borrowRandomId":"0c244be7-eaf2-4e4e-81a8-8f61f3988eab"}
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

    public static class ResultBean implements Serializable{
        /**
         * userMap : {"usertype":"1","usableSum":"188322.00"}
         * redList1 : {"ranaa":6,"ableTenderDate":"2018-06-28 15:51","paymentMode":"","borrowStatus":2,"borrowStatusStr":"立即出借","ran":6,"timeFlag":"0","borrowSum":"800元","rans":0,"surplus":"400.00","deadline":"3个月"}
         * juan : [{"useCondition":"","startTime":"2018-07-18","id":368,"quota":1000,"useRange":"房屋抵押,助学计划,项目集,车辆抵押,","remark":"满1000减108元","activitype":6,"isHot":"1","endTime":"2018-07-27","info":108},{"useCondition":"","startTime":"2018-07-18","id":369,"quota":5000,"useRange":"房屋抵押,助学计划,项目集,车辆抵押,","remark":"满5000减66元","activitype":6,"isHot":"1","endTime":"2018-07-27","info":66},{"useCondition":"","startTime":"2018-07-18","id":370,"quota":1000,"useRange":"房屋抵押,助学计划,项目集,车辆抵押,","remark":"满1000减15元","activitype":6,"isHot":"1","endTime":"2018-07-27","info":15},{"useCondition":"","startTime":"2018-07-18","id":371,"quota":500,"useRange":"房屋抵押,助学计划,项目集,车辆抵押,","remark":"满500减10元","activitype":6,"isHot":"1","endTime":"2018-07-27","info":10}]
         * borrowRandomId : 0c244be7-eaf2-4e4e-81a8-8f61f3988eab
         */

        private UserMapBean userMap;
        private RedList1Bean redList1;
        private String borrowRandomId;
        private List<JuanBean> juan;

        public UserMapBean getUserMap() {
            return userMap;
        }

        public void setUserMap(UserMapBean userMap) {
            this.userMap = userMap;
        }

        public RedList1Bean getRedList1() {
            return redList1;
        }

        public void setRedList1(RedList1Bean redList1) {
            this.redList1 = redList1;
        }

        public String getBorrowRandomId() {
            return borrowRandomId;
        }

        public void setBorrowRandomId(String borrowRandomId) {
            this.borrowRandomId = borrowRandomId;
        }

        public List<JuanBean> getJuan() {
            return juan;
        }

        public void setJuan(List<JuanBean> juan) {
            this.juan = juan;
        }

        public static class UserMapBean implements Serializable{
            /**
             * usertype : 1
             * usableSum : 188322.00
             */

            private String usertype;
            private String usableSum;

            public String getUsertype() {
                return usertype;
            }

            public void setUsertype(String usertype) {
                this.usertype = usertype;
            }

            public String getUsableSum() {
                return usableSum;
            }

            public void setUsableSum(String usableSum) {
                this.usableSum = usableSum;
            }
        }

        public static class RedList1Bean implements Serializable{
            /**
             * ranaa : 6.0
             * ableTenderDate : 2018-06-28 15:51
             * paymentMode :
             * borrowStatus : 2
             * borrowStatusStr : 立即出借
             * ran : 6.0
             * timeFlag : 0
             * borrowSum : 800元
             * rans : 0.0
             * surplus : 400.00
             * deadline : 3个月
             */

            private double ranaa;
            private String ableTenderDate;
            private String paymentMode;
            private int borrowStatus;
            private String borrowStatusStr;
            private double ran;
            private String timeFlag;
            private String borrowSum;
            private double rans;
            private String surplus;
            private String deadline;

            public double getRanaa() {
                return ranaa;
            }

            public void setRanaa(double ranaa) {
                this.ranaa = ranaa;
            }

            public String getAbleTenderDate() {
                return ableTenderDate;
            }

            public void setAbleTenderDate(String ableTenderDate) {
                this.ableTenderDate = ableTenderDate;
            }

            public String getPaymentMode() {
                return paymentMode;
            }

            public void setPaymentMode(String paymentMode) {
                this.paymentMode = paymentMode;
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

            public double getRan() {
                return ran;
            }

            public void setRan(double ran) {
                this.ran = ran;
            }

            public String getTimeFlag() {
                return timeFlag;
            }

            public void setTimeFlag(String timeFlag) {
                this.timeFlag = timeFlag;
            }

            public String getBorrowSum() {
                return borrowSum;
            }

            public void setBorrowSum(String borrowSum) {
                this.borrowSum = borrowSum;
            }

            public double getRans() {
                return rans;
            }

            public void setRans(double rans) {
                this.rans = rans;
            }

            public String getSurplus() {
                return surplus;
            }

            public void setSurplus(String surplus) {
                this.surplus = surplus;
            }

            public String getDeadline() {
                return deadline;
            }

            public void setDeadline(String deadline) {
                this.deadline = deadline;
            }
        }

        public static class JuanBean implements Serializable{
            /**
             * useCondition :
             * startTime : 2018-07-18
             * id : 368
             * quota : 1000
             * useRange : 房屋抵押,助学计划,项目集,车辆抵押,
             * remark : 满1000减108元
             * activitype : 6
             * isHot : 1
             * endTime : 2018-07-27
             * info : 108.0
             */

            private String useCondition;
            private String startTime;
            private int id;
            private int quota;
            private String useRange;
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

            public int getQuota() {
                return quota;
            }

            public void setQuota(int quota) {
                this.quota = quota;
            }

            public String getUseRange() {
                return useRange;
            }

            public void setUseRange(String useRange) {
                this.useRange = useRange;
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
}
