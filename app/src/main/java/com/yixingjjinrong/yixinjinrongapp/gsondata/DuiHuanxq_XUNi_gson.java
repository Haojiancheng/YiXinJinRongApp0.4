package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class DuiHuanxq_XUNi_gson {

    /**
     * message : 成功了
     * result : {"myIntegral":"74","path":"http://192.168.1.79:8080/yxb_oms/","voucherList":[{"createTime":"2018-07-19 09:53:59","useRange":"无限制","indexNum":43,"prizeType":3,"endTime":"2019-08-30","needPoint":1,"id":55,"activityId":396,"startTime":"2018-08-31","restNum":121,"awardType":2,"prizeMark":"满100加息7%","prizeName":"加息券","prizeId":217}],"RecInformation":[{"createTime":"2018-08-31 16:30:42","isDefault":1,"receiverId":"212","userId":11298,"receiverName":"贺培","STATUS":1,"receiverAddress":"北京市东城区let嘟嘟女嘟嘟","receiverPhone":"185****6112"}]}
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
         * myIntegral : 74
         * path : http://192.168.1.79:8080/yxb_oms/
         * voucherList : [{"createTime":"2018-07-19 09:53:59","useRange":"无限制","indexNum":43,"prizeType":3,"endTime":"2019-08-30","needPoint":1,"id":55,"activityId":396,"startTime":"2018-08-31","restNum":121,"awardType":2,"prizeMark":"满100加息7%","prizeName":"加息券","prizeId":217}]
         * RecInformation : [{"createTime":"2018-08-31 16:30:42","isDefault":1,"receiverId":"212","userId":11298,"receiverName":"贺培","STATUS":1,"receiverAddress":"北京市东城区let嘟嘟女嘟嘟","receiverPhone":"185****6112"}]
         */

        private String myIntegral;
        private String path;
        private List<VoucherListBean> voucherList;
        private List<RecInformationBean> RecInformation;

        public String getMyIntegral() {
            return myIntegral;
        }

        public void setMyIntegral(String myIntegral) {
            this.myIntegral = myIntegral;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<VoucherListBean> getVoucherList() {
            return voucherList;
        }

        public void setVoucherList(List<VoucherListBean> voucherList) {
            this.voucherList = voucherList;
        }

        public List<RecInformationBean> getRecInformation() {
            return RecInformation;
        }

        public void setRecInformation(List<RecInformationBean> RecInformation) {
            this.RecInformation = RecInformation;
        }

        public static class VoucherListBean {
            /**
             * createTime : 2018-07-19 09:53:59
             * useRange : 无限制
             * indexNum : 43
             * prizeType : 3
             * endTime : 2019-08-30
             * needPoint : 1
             * id : 55
             * activityId : 396
             * startTime : 2018-08-31
             * restNum : 121
             * awardType : 2
             * prizeMark : 满100加息7%
             * prizeName : 加息券
             * prizeId : 217
             */

            private String createTime;
            private String useRange;
            private int indexNum;
            private int prizeType;
            private String endTime;
            private int needPoint;
            private int id;
            private int activityId;
            private String startTime;
            private int restNum;
            private int awardType;
            private String prizeMark;
            private String prizeName;
            private int prizeId;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUseRange() {
                return useRange;
            }

            public void setUseRange(String useRange) {
                this.useRange = useRange;
            }

            public int getIndexNum() {
                return indexNum;
            }

            public void setIndexNum(int indexNum) {
                this.indexNum = indexNum;
            }

            public int getPrizeType() {
                return prizeType;
            }

            public void setPrizeType(int prizeType) {
                this.prizeType = prizeType;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getNeedPoint() {
                return needPoint;
            }

            public void setNeedPoint(int needPoint) {
                this.needPoint = needPoint;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public int getRestNum() {
                return restNum;
            }

            public void setRestNum(int restNum) {
                this.restNum = restNum;
            }

            public int getAwardType() {
                return awardType;
            }

            public void setAwardType(int awardType) {
                this.awardType = awardType;
            }

            public String getPrizeMark() {
                return prizeMark;
            }

            public void setPrizeMark(String prizeMark) {
                this.prizeMark = prizeMark;
            }

            public String getPrizeName() {
                return prizeName;
            }

            public void setPrizeName(String prizeName) {
                this.prizeName = prizeName;
            }

            public int getPrizeId() {
                return prizeId;
            }

            public void setPrizeId(int prizeId) {
                this.prizeId = prizeId;
            }
        }

        public static class RecInformationBean {
            /**
             * createTime : 2018-08-31 16:30:42
             * isDefault : 1
             * receiverId : 212
             * userId : 11298
             * receiverName : 贺培
             * STATUS : 1
             * receiverAddress : 北京市东城区let嘟嘟女嘟嘟
             * receiverPhone : 185****6112
             */

            private String createTime;
            private int isDefault;
            private String receiverId;
            private int userId;
            private String receiverName;
            private int STATUS;
            private String receiverAddress;
            private String receiverPhone;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public String getReceiverId() {
                return receiverId;
            }

            public void setReceiverId(String receiverId) {
                this.receiverId = receiverId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public int getSTATUS() {
                return STATUS;
            }

            public void setSTATUS(int STATUS) {
                this.STATUS = STATUS;
            }

            public String getReceiverAddress() {
                return receiverAddress;
            }

            public void setReceiverAddress(String receiverAddress) {
                this.receiverAddress = receiverAddress;
            }

            public String getReceiverPhone() {
                return receiverPhone;
            }

            public void setReceiverPhone(String receiverPhone) {
                this.receiverPhone = receiverPhone;
            }
        }
    }
}
