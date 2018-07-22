package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.io.Serializable;
import java.util.List;

public class JiaXiBean implements Serializable {
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
    private List<xianJuanBean> juan;

    public List<xianJuanBean> getJuan() {
        return juan;
    }

    public void setJuan(List<xianJuanBean> juan) {
        this.juan = juan;
    }

    public static class xianJuanBean implements Serializable {
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

