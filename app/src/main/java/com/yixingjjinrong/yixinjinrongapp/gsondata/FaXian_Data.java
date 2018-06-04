package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class FaXian_Data {


    /**
     * message : 成功了
     * result : {"bannerList":[{"hrefurl":"","l_name":"引导页app","l_total_count":3,"picurl":"upload/imageManage/20180524/201805241607397172.png","tid":114},{"hrefurl":"","l_name":"引导页app","l_total_count":3,"picurl":"upload/imageManage/20180524/201805241608063343.png","tid":114},{"hrefurl":"","l_name":"引导页app","l_total_count":3,"picurl":"upload/imageManage/20180524/201805241608346978.png","tid":114}],"goodsList":[{"awardType":1,"createTime":"2017-02-20 15:57:58","description":"金手链0.5g/999纯金","exchangeCnt":"0","exchangeCredits":123456,"exchangeNum":6,"id":2,"picUrl":"/upload/vipgift/20170221/201702211146548284.jpg","prizeName":"金手链","prizeStatus":"1","speId":2,"surplusNum":20,"totalNum":26,"totalPrice":"7100.00"},{"awardType":1,"createTime":"2017-02-23 11:43:57","description":"包装清单  量杯、蒸笼、电源线、汤勺、饭勺、说明书 ","exchangeCnt":"0","exchangeCredits":123456,"exchangeNum":0,"id":4,"picUrl":"/upload/vipgift/20170223/201702231149206554.jpg","prizeName":"苏泊尔电饭锅CFXB40FC833-75","prizeStatus":"1","speId":4,"surplusNum":787,"totalNum":787,"totalPrice":"10777.00"},{"awardType":1,"createTime":"2017-02-23 11:54:45","description":"家那发","exchangeCnt":"0","exchangeCredits":123456,"exchangeNum":0,"id":5,"picUrl":"/upload/vipgift/20170223/201702231154316559.gif","prizeName":"加拿大","prizeStatus":"1","speId":5,"surplusNum":8,"totalNum":8,"totalPrice":"8900.00"},{"awardType":1,"createTime":"2017-02-23 15:01:40","description":"SM-700S升级0重力家用全身豪华多功能零重力太空舱按摩椅 ","exchangeCnt":"0","exchangeCredits":123456,"exchangeNum":0,"id":6,"picUrl":"/upload/vipgift/20170223/20170223150139609.png","prizeName":"【尚铭】","prizeStatus":"1","speId":6,"surplusNum":1,"totalNum":1,"totalPrice":"1.00"},{"awardType":1,"createTime":"2017-02-28 11:08:40","description":"每个U盘都配有钥匙链方便挂钥匙上，皮套+金属款 方便 经久 耐用 ","exchangeCnt":"1","exchangeCredits":300,"exchangeNum":1,"id":8,"picUrl":"/upload/vipgift/20170228/201702281108381375.jpg","prizeName":"商务礼品U盘","prizeStatus":"1","speId":8,"surplusNum":67,"totalNum":68,"totalPrice":"1700.00"},{"awardType":1,"createTime":"2017-02-23 15:03:17","description":"智能零重力全自动电动按摩沙发椅1 ","exchangeCnt":"0","exchangeCredits":3,"exchangeNum":5,"id":7,"picUrl":"/upload/vipgift/20170223/201702231630523508.jpg","prizeName":"重力太空舱按摩椅","prizeStatus":"1","speId":7,"surplusNum":0,"totalNum":5,"totalPrice":"70103.00"},{"awardType":1,"createTime":"2017-02-20 14:10:54","description":"实物礼品","exchangeCnt":"0","exchangeCredits":2,"exchangeNum":3,"id":1,"picUrl":"/upload/vipgift/20170220/201702201410511260.jpg","prizeName":"new","prizeStatus":"1","speId":1,"surplusNum":19,"totalNum":22,"totalPrice":"414.00"},{"awardType":1,"createTime":"2017-03-16 14:45:59","description":"香葱鸡片非常美味","exchangeCnt":"0","exchangeCredits":2,"exchangeNum":1,"id":9,"picUrl":"/upload/vipgift/20170316/20170316144557509.png","prizeName":"香葱鸡片","prizeStatus":"1","speId":9,"surplusNum":0,"totalNum":1,"totalPrice":"1.00"},{"awardType":1,"createTime":"2017-02-20 15:59:06","description":"17春款系高跟鞋女尖头亮面水钻单鞋细跟性感晏会时尚女鞋婚鞋 ","exchangeCnt":"0","exchangeCredits":1,"exchangeNum":3,"id":3,"picUrl":"/upload/vipgift/20170220/201702201559059949.jpg","prizeName":"two","prizeStatus":"1","speId":3,"surplusNum":999,"totalNum":1002,"totalPrice":"1002.00"},{"awardType":2,"createTime":"2018-09-24","description":"2018-05-17 10:39:18","exchangeCnt":"无限制","exchangeCredits":1,"exchangeNum":3,"id":38,"picUrl":"加息券","prizeName":"396","prizeStatus":"2018-05-25","speId":979,"surplusNum":1,"totalNum":206,"totalPrice":"满100加息5%"},{"awardType":2,"createTime":"2018-06-03","description":"2017-08-31 11:28:20","exchangeCnt":"无限制","exchangeCredits":24,"exchangeNum":3,"id":35,"picUrl":"加息券","prizeName":"396","prizeStatus":"2018-05-25","speId":9994,"surplusNum":1,"totalNum":211,"totalPrice":"满100加息10%"},{"awardType":2,"createTime":"2019-05-24","description":"2018-05-11 14:36:15","exchangeCnt":"无限制","exchangeCredits":26,"exchangeNum":6,"id":37,"picUrl":"代金券","prizeName":"396","prizeStatus":"2018-05-25","speId":1039,"surplusNum":1,"totalNum":363,"totalPrice":"满100减￥10"}],"path":"[图片]http://192.168.1.118:8080/yxb_omsb/"}
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
         * bannerList : [{"hrefurl":"","l_name":"引导页app","l_total_count":3,"picurl":"upload/imageManage/20180524/201805241607397172.png","tid":114},{"hrefurl":"","l_name":"引导页app","l_total_count":3,"picurl":"upload/imageManage/20180524/201805241608063343.png","tid":114},{"hrefurl":"","l_name":"引导页app","l_total_count":3,"picurl":"upload/imageManage/20180524/201805241608346978.png","tid":114}]
         * goodsList : [{"awardType":1,"createTime":"2017-02-20 15:57:58","description":"金手链0.5g/999纯金","exchangeCnt":"0","exchangeCredits":123456,"exchangeNum":6,"id":2,"picUrl":"/upload/vipgift/20170221/201702211146548284.jpg","prizeName":"金手链","prizeStatus":"1","speId":2,"surplusNum":20,"totalNum":26,"totalPrice":"7100.00"},{"awardType":1,"createTime":"2017-02-23 11:43:57","description":"包装清单  量杯、蒸笼、电源线、汤勺、饭勺、说明书 ","exchangeCnt":"0","exchangeCredits":123456,"exchangeNum":0,"id":4,"picUrl":"/upload/vipgift/20170223/201702231149206554.jpg","prizeName":"苏泊尔电饭锅CFXB40FC833-75","prizeStatus":"1","speId":4,"surplusNum":787,"totalNum":787,"totalPrice":"10777.00"},{"awardType":1,"createTime":"2017-02-23 11:54:45","description":"家那发","exchangeCnt":"0","exchangeCredits":123456,"exchangeNum":0,"id":5,"picUrl":"/upload/vipgift/20170223/201702231154316559.gif","prizeName":"加拿大","prizeStatus":"1","speId":5,"surplusNum":8,"totalNum":8,"totalPrice":"8900.00"},{"awardType":1,"createTime":"2017-02-23 15:01:40","description":"SM-700S升级0重力家用全身豪华多功能零重力太空舱按摩椅 ","exchangeCnt":"0","exchangeCredits":123456,"exchangeNum":0,"id":6,"picUrl":"/upload/vipgift/20170223/20170223150139609.png","prizeName":"【尚铭】","prizeStatus":"1","speId":6,"surplusNum":1,"totalNum":1,"totalPrice":"1.00"},{"awardType":1,"createTime":"2017-02-28 11:08:40","description":"每个U盘都配有钥匙链方便挂钥匙上，皮套+金属款 方便 经久 耐用 ","exchangeCnt":"1","exchangeCredits":300,"exchangeNum":1,"id":8,"picUrl":"/upload/vipgift/20170228/201702281108381375.jpg","prizeName":"商务礼品U盘","prizeStatus":"1","speId":8,"surplusNum":67,"totalNum":68,"totalPrice":"1700.00"},{"awardType":1,"createTime":"2017-02-23 15:03:17","description":"智能零重力全自动电动按摩沙发椅1 ","exchangeCnt":"0","exchangeCredits":3,"exchangeNum":5,"id":7,"picUrl":"/upload/vipgift/20170223/201702231630523508.jpg","prizeName":"重力太空舱按摩椅","prizeStatus":"1","speId":7,"surplusNum":0,"totalNum":5,"totalPrice":"70103.00"},{"awardType":1,"createTime":"2017-02-20 14:10:54","description":"实物礼品","exchangeCnt":"0","exchangeCredits":2,"exchangeNum":3,"id":1,"picUrl":"/upload/vipgift/20170220/201702201410511260.jpg","prizeName":"new","prizeStatus":"1","speId":1,"surplusNum":19,"totalNum":22,"totalPrice":"414.00"},{"awardType":1,"createTime":"2017-03-16 14:45:59","description":"香葱鸡片非常美味","exchangeCnt":"0","exchangeCredits":2,"exchangeNum":1,"id":9,"picUrl":"/upload/vipgift/20170316/20170316144557509.png","prizeName":"香葱鸡片","prizeStatus":"1","speId":9,"surplusNum":0,"totalNum":1,"totalPrice":"1.00"},{"awardType":1,"createTime":"2017-02-20 15:59:06","description":"17春款系高跟鞋女尖头亮面水钻单鞋细跟性感晏会时尚女鞋婚鞋 ","exchangeCnt":"0","exchangeCredits":1,"exchangeNum":3,"id":3,"picUrl":"/upload/vipgift/20170220/201702201559059949.jpg","prizeName":"two","prizeStatus":"1","speId":3,"surplusNum":999,"totalNum":1002,"totalPrice":"1002.00"},{"awardType":2,"createTime":"2018-09-24","description":"2018-05-17 10:39:18","exchangeCnt":"无限制","exchangeCredits":1,"exchangeNum":3,"id":38,"picUrl":"加息券","prizeName":"396","prizeStatus":"2018-05-25","speId":979,"surplusNum":1,"totalNum":206,"totalPrice":"满100加息5%"},{"awardType":2,"createTime":"2018-06-03","description":"2017-08-31 11:28:20","exchangeCnt":"无限制","exchangeCredits":24,"exchangeNum":3,"id":35,"picUrl":"加息券","prizeName":"396","prizeStatus":"2018-05-25","speId":9994,"surplusNum":1,"totalNum":211,"totalPrice":"满100加息10%"},{"awardType":2,"createTime":"2019-05-24","description":"2018-05-11 14:36:15","exchangeCnt":"无限制","exchangeCredits":26,"exchangeNum":6,"id":37,"picUrl":"代金券","prizeName":"396","prizeStatus":"2018-05-25","speId":1039,"surplusNum":1,"totalNum":363,"totalPrice":"满100减￥10"}]
         * path : [图片]http://192.168.1.118:8080/yxb_omsb/
         */

        private String path;
        private List<BannerListBean> bannerList;
        private List<GoodsListBean> goodsList;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<BannerListBean> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<BannerListBean> bannerList) {
            this.bannerList = bannerList;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class BannerListBean {
            /**
             * hrefurl : 
             * l_name : 引导页app
             * l_total_count : 3
             * picurl : upload/imageManage/20180524/201805241607397172.png
             * tid : 114
             */

            private String hrefurl;
            private String l_name;
            private int l_total_count;
            private String picurl;
            private int tid;

            public String getHrefurl() {
                return hrefurl;
            }

            public void setHrefurl(String hrefurl) {
                this.hrefurl = hrefurl;
            }

            public String getL_name() {
                return l_name;
            }

            public void setL_name(String l_name) {
                this.l_name = l_name;
            }

            public int getL_total_count() {
                return l_total_count;
            }

            public void setL_total_count(int l_total_count) {
                this.l_total_count = l_total_count;
            }

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }
        }

        public static class GoodsListBean {
            /**
             * awardType : 1
             * createTime : 2017-02-20 15:57:58
             * description : 金手链0.5g/999纯金
             * exchangeCnt : 0
             * exchangeCredits : 123456
             * exchangeNum : 6
             * id : 2
             * picUrl : /upload/vipgift/20170221/201702211146548284.jpg
             * prizeName : 金手链
             * prizeStatus : 1
             * speId : 2
             * surplusNum : 20
             * totalNum : 26
             * totalPrice : 7100.00
             */

            private int awardType;
            private String createTime;
            private String description;
            private String exchangeCnt;
            private int exchangeCredits;
            private int exchangeNum;
            private int id;
            private String picUrl;
            private String prizeName;
            private String prizeStatus;
            private int speId;
            private int surplusNum;
            private int totalNum;
            private String totalPrice;

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

            public String getExchangeCnt() {
                return exchangeCnt;
            }

            public void setExchangeCnt(String exchangeCnt) {
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

            public String getPrizeStatus() {
                return prizeStatus;
            }

            public void setPrizeStatus(String prizeStatus) {
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

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }
        }
    }
}
