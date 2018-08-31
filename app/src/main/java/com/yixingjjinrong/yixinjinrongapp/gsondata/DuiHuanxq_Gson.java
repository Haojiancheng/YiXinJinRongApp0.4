package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class DuiHuanxq_Gson {


    /**
     * message : 成功了
     * result : {"goodsList":[{"createTime":"2017-02-23 15:01:40","picUrl":"upload/vipgift/20170223/20170223150139609.png","speId":6,"exchangeNum":1,"id":6,"surplusNum":11,"awardType":1,"totalNum":1,"exchangeCredits":15,"description":"SM-700S升级0重力家用全身豪华多功能零重力太空舱按摩椅 ","prizeStatus":1,"exchangeCnt":0,"prizeName":"【尚铭】","totalPrice":1}],"myIntegral":"48","path":"http://192.168.1.79:8080/yxb_oms/","RecInformation":[{"createTime":"2018-08-31 19:35:45","isDefault":1,"receiverId":"216","userId":11298,"receiverName":"你会放大","STATUS":1,"receiverAddress":"天津市 天津市 和平区的发送到发送到是发送到","receiverPhone":"157****5301"}]}
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
         * goodsList : [{"createTime":"2017-02-23 15:01:40","picUrl":"upload/vipgift/20170223/20170223150139609.png","speId":6,"exchangeNum":1,"id":6,"surplusNum":11,"awardType":1,"totalNum":1,"exchangeCredits":15,"description":"SM-700S升级0重力家用全身豪华多功能零重力太空舱按摩椅 ","prizeStatus":1,"exchangeCnt":0,"prizeName":"【尚铭】","totalPrice":1}]
         * myIntegral : 48
         * path : http://192.168.1.79:8080/yxb_oms/
         * RecInformation : [{"createTime":"2018-08-31 19:35:45","isDefault":1,"receiverId":"216","userId":11298,"receiverName":"你会放大","STATUS":1,"receiverAddress":"天津市 天津市 和平区的发送到发送到是发送到","receiverPhone":"157****5301"}]
         */

        private String myIntegral;
        private String path;
        private List<GoodsListBean> goodsList;
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

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public List<RecInformationBean> getRecInformation() {
            return RecInformation;
        }

        public void setRecInformation(List<RecInformationBean> RecInformation) {
            this.RecInformation = RecInformation;
        }

        public static class GoodsListBean {
            /**
             * createTime : 2017-02-23 15:01:40
             * picUrl : upload/vipgift/20170223/20170223150139609.png
             * speId : 6
             * exchangeNum : 1
             * id : 6
             * surplusNum : 11
             * awardType : 1
             * totalNum : 1
             * exchangeCredits : 15
             * description : SM-700S升级0重力家用全身豪华多功能零重力太空舱按摩椅
             * prizeStatus : 1
             * exchangeCnt : 0
             * prizeName : 【尚铭】
             * totalPrice : 1.0
             */

            private String createTime;
            private String picUrl;
            private int speId;
            private int exchangeNum;
            private int id;
            private int surplusNum;
            private int awardType;
            private int totalNum;
            private int exchangeCredits;
            private String description;
            private int prizeStatus;
            private int exchangeCnt;
            private String prizeName;
            private double totalPrice;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int getSpeId() {
                return speId;
            }

            public void setSpeId(int speId) {
                this.speId = speId;
            }

            public int getExchangeNum() {
                return exchangeNum;
            }

            public void setExchangeNum(int exchangeNum) {
                this.exchangeNum = exchangeNum;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSurplusNum() {
                return surplusNum;
            }

            public void setSurplusNum(int surplusNum) {
                this.surplusNum = surplusNum;
            }

            public int getAwardType() {
                return awardType;
            }

            public void setAwardType(int awardType) {
                this.awardType = awardType;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public int getExchangeCredits() {
                return exchangeCredits;
            }

            public void setExchangeCredits(int exchangeCredits) {
                this.exchangeCredits = exchangeCredits;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPrizeStatus() {
                return prizeStatus;
            }

            public void setPrizeStatus(int prizeStatus) {
                this.prizeStatus = prizeStatus;
            }

            public int getExchangeCnt() {
                return exchangeCnt;
            }

            public void setExchangeCnt(int exchangeCnt) {
                this.exchangeCnt = exchangeCnt;
            }

            public String getPrizeName() {
                return prizeName;
            }

            public void setPrizeName(String prizeName) {
                this.prizeName = prizeName;
            }

            public double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
            }
        }

        public static class RecInformationBean {
            /**
             * createTime : 2018-08-31 19:35:45
             * isDefault : 1
             * receiverId : 216
             * userId : 11298
             * receiverName : 你会放大
             * STATUS : 1
             * receiverAddress : 天津市 天津市 和平区的发送到发送到是发送到
             * receiverPhone : 157****5301
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
