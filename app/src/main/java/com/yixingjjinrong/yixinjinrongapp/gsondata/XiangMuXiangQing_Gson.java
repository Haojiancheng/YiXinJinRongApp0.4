package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class XiangMuXiangQing_Gson {


    /**
     * message : 成功了
     * result : {"userMap":{"usertype":"1","usableSum":"5058610.00"},"redList1":{"ranaa":6,"paymentMode":"","ran":6,"borrowSum":"300元","rans":0,"surplus":"300.00","deadline":"3个月"},"juan":[{"useCondition":"","startTime":"2018-05-21","id":206,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-20","info":5},{"useCondition":"","startTime":"2018-05-21","id":206,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-20","info":5},{"useCondition":"","startTime":"2018-05-25","id":206,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-24","info":5},{"useCondition":"","startTime":"2018-05-25","id":206,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-24","info":5},{"useCondition":"","startTime":"2018-05-25","id":206,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-24","info":5},{"useCondition":"","startTime":"2018-05-25","id":363,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100减10元","activitype":6,"isHot":"1","endTime":"2019-05-24","info":10},{"useCondition":"","startTime":"2018-05-25","id":363,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100减10元","activitype":6,"isHot":"1","endTime":"2019-05-24","info":10},{"useCondition":"","startTime":"2018-05-25","id":363,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100减10元","activitype":6,"isHot":"1","endTime":"2019-05-24","info":10}]}
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
         * userMap : {"usertype":"1","usableSum":"5058610.00"}
         * redList1 : {"ranaa":6,"paymentMode":"","ran":6,"borrowSum":"300元","rans":0,"surplus":"300.00","deadline":"3个月"}
         * juan : [{"useCondition":"","startTime":"2018-05-21","id":206,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-20","info":5},{"useCondition":"","startTime":"2018-05-21","id":206,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-20","info":5},{"useCondition":"","startTime":"2018-05-25","id":206,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-24","info":5},{"useCondition":"","startTime":"2018-05-25","id":206,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-24","info":5},{"useCondition":"","startTime":"2018-05-25","id":206,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100元加息5%","activitype":3,"isHot":"","endTime":"2018-09-24","info":5},{"useCondition":"","startTime":"2018-05-25","id":363,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100减10元","activitype":6,"isHot":"1","endTime":"2019-05-24","info":10},{"useCondition":"","startTime":"2018-05-25","id":363,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100减10元","activitype":6,"isHot":"1","endTime":"2019-05-24","info":10},{"useCondition":"","startTime":"2018-05-25","id":363,"quota":100,"useRange":"房屋抵押,助学计划,项目集,","remark":"满100减10元","activitype":6,"isHot":"1","endTime":"2019-05-24","info":10}]
         */

        private UserMapBean userMap;
        private RedList1Bean redList1;
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

        public List<JuanBean> getJuan() {
            return juan;
        }

        public void setJuan(List<JuanBean> juan) {
            this.juan = juan;
        }

        public static class UserMapBean {
            /**
             * usertype : 1
             * usableSum : 5058610.00
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

        public static class RedList1Bean {
            /**
             * ranaa : 6.0
             * paymentMode :
             * ran : 6.0
             * borrowSum : 300元
             * rans : 0.0
             * surplus : 300.00
             * deadline : 3个月
             */

            private double ranaa;
            private String paymentMode;
            private double ran;
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

            public String getPaymentMode() {
                return paymentMode;
            }

            public void setPaymentMode(String paymentMode) {
                this.paymentMode = paymentMode;
            }

            public double getRan() {
                return ran;
            }

            public void setRan(double ran) {
                this.ran = ran;
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

        public static class JuanBean {
            /**
             * useCondition :
             * startTime : 2018-05-21
             * id : 206
             * quota : 100
             * useRange : 房屋抵押,助学计划,项目集,
             * remark : 满100元加息5%
             * activitype : 3
             * isHot :
             * endTime : 2018-09-20
             * info : 5.0
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
