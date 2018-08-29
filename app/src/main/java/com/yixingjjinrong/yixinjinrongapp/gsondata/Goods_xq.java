package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class Goods_xq {

    /**
     * message : 成功了
     * result : {"goodsList":[{"awardType":1,"createTime":"2017-02-28 11:08:40","description":"每个U盘都配有钥匙链方便挂钥匙上，皮套+金属款 方便 经久 耐用 ","exchangeCnt":0,"exchangeCredits":30,"exchangeNum":2,"id":8,"picUrl":"upload/vipgift/20170228/201702281108381375.jpg","prizeName":"商务礼品U盘","prizeStatus":1,"speId":8,"surplusNum":66,"totalNum":68,"totalPrice":1700}],"path":"http://192.168.1.79:8080/yxb_oms/"}
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
         * goodsList : [{"awardType":1,"createTime":"2017-02-28 11:08:40","description":"每个U盘都配有钥匙链方便挂钥匙上，皮套+金属款 方便 经久 耐用 ","exchangeCnt":0,"exchangeCredits":30,"exchangeNum":2,"id":8,"picUrl":"upload/vipgift/20170228/201702281108381375.jpg","prizeName":"商务礼品U盘","prizeStatus":1,"speId":8,"surplusNum":66,"totalNum":68,"totalPrice":1700}]
         * path : http://192.168.1.79:8080/yxb_oms/
         */

        private String path;
        private List<GoodsListBean> goodsList;

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

        public static class GoodsListBean {
            /**
             * awardType : 1
             * createTime : 2017-02-28 11:08:40
             * description : 每个U盘都配有钥匙链方便挂钥匙上，皮套+金属款 方便 经久 耐用
             * exchangeCnt : 0
             * exchangeCredits : 30
             * exchangeNum : 2
             * id : 8
             * picUrl : upload/vipgift/20170228/201702281108381375.jpg
             * prizeName : 商务礼品U盘
             * prizeStatus : 1
             * speId : 8
             * surplusNum : 66
             * totalNum : 68
             * totalPrice : 1700.0
             */

            private int awardType;
            private String createTime;
            private String description;
            private int exchangeCnt;
            private int exchangeCredits;
            private int exchangeNum;
            private int id;
            private String picUrl;
            private String prizeName;
            private int prizeStatus;
            private int speId;
            private int surplusNum;
            private int totalNum;
            private double totalPrice;

            public int getAwardType() {
                return awardType;
            }

            public void setAwardType(int awardType) {
                this.awardType = awardType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getExchangeCnt() {
                return exchangeCnt;
            }

            public void setExchangeCnt(int exchangeCnt) {
                this.exchangeCnt = exchangeCnt;
            }

            public int getExchangeCredits() {
                return exchangeCredits;
            }

            public void setExchangeCredits(int exchangeCredits) {
                this.exchangeCredits = exchangeCredits;
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

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getPrizeName() {
                return prizeName;
            }

            public void setPrizeName(String prizeName) {
                this.prizeName = prizeName;
            }

            public int getPrizeStatus() {
                return prizeStatus;
            }

            public void setPrizeStatus(int prizeStatus) {
                this.prizeStatus = prizeStatus;
            }

            public int getSpeId() {
                return speId;
            }

            public void setSpeId(int speId) {
                this.speId = speId;
            }

            public int getSurplusNum() {
                return surplusNum;
            }

            public void setSurplusNum(int surplusNum) {
                this.surplusNum = surplusNum;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
            }
        }
    }
}
