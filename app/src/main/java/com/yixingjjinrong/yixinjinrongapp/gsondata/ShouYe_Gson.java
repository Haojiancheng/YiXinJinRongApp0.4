package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class ShouYe_Gson {


    /**
     * message : 查询成功
     * result : {"bannerList":[{"hrefurl":"","l_name":"引导页app","l_total_count":3,"picurl":"upload/imageManage/20180524/201805241607397172.png","tid":114},{"hrefurl":"","l_name":"引导页app","l_total_count":3,"picurl":"upload/imageManage/20180524/201805241608063343.png","tid":114},{"hrefurl":"","l_name":"引导页app","l_total_count":3,"picurl":"upload/imageManage/20180524/201805241608346978.png","tid":114}],"borrowList":[{"ableTenderDate":"2018-07-27 11:45 ","amount":"30,000.00","annualRate":3,"borrowCode":"X18D10082","borrowFrom":1,"borrowRandomId":"9651146e-fb9d-492b-9fbc-e7f5b39fbb22","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"是","deadlineNew":"2个月","id":2616,"investNumTotal":"30,000.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"0%","subsidies":3,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-07-27 10:18 ","amount":"300,000.00","annualRate":3,"borrowCode":"X18D10081","borrowFrom":1,"borrowRandomId":"a068a4cb-ca7c-4f5b-8ef4-7864d396805a","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"资料","deadlineNew":"2个月","id":2615,"investNumTotal":"300,000.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"0%","subsidies":3,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-07-26 10:16 ","amount":"1,000.00","annualRate":9,"borrowCode":"Y18D10246","borrowFrom":1,"borrowRandomId":"9d03f128-1e96-4a98-a17f-86017654cee2","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"测试app借款详情","deadlineNew":"3个月","id":2614,"investNumTotal":"600.00","isDayThe":1,"mortgageType":4,"mortgageTypeStr":"车辆抵押","schedules":"40.00%","subsidies":3,"subsidiesRate":6,"timeFlag":0}],"path":"http://192.168.1.79:8080/yxb_oms/","publicMsgList":[{"aid":220,"article_link":"","article_pub_time":"2018-07-18","article_title":"风格的规定电饭锅"},{"aid":223,"article_link":"","article_pub_time":"2018-07-17","article_title":"新标发布公告"}]}
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
         * borrowList : [{"ableTenderDate":"2018-07-27 11:45 ","amount":"30,000.00","annualRate":3,"borrowCode":"X18D10082","borrowFrom":1,"borrowRandomId":"9651146e-fb9d-492b-9fbc-e7f5b39fbb22","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"是","deadlineNew":"2个月","id":2616,"investNumTotal":"30,000.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"0%","subsidies":3,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-07-27 10:18 ","amount":"300,000.00","annualRate":3,"borrowCode":"X18D10081","borrowFrom":1,"borrowRandomId":"a068a4cb-ca7c-4f5b-8ef4-7864d396805a","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"资料","deadlineNew":"2个月","id":2615,"investNumTotal":"300,000.00","isDayThe":1,"mortgageType":1,"mortgageTypeStr":"房产抵押","schedules":"0%","subsidies":3,"subsidiesRate":0,"timeFlag":0},{"ableTenderDate":"2018-07-26 10:16 ","amount":"1,000.00","annualRate":9,"borrowCode":"Y18D10246","borrowFrom":1,"borrowRandomId":"9d03f128-1e96-4a98-a17f-86017654cee2","borrowSpe":"个人借款","borrowStatus":2,"borrowStatusStr":"立即出借","borrowTitle":"测试app借款详情","deadlineNew":"3个月","id":2614,"investNumTotal":"600.00","isDayThe":1,"mortgageType":4,"mortgageTypeStr":"车辆抵押","schedules":"40.00%","subsidies":3,"subsidiesRate":6,"timeFlag":0}]
         * path : http://192.168.1.79:8080/yxb_oms/
         * publicMsgList : [{"aid":220,"article_link":"","article_pub_time":"2018-07-18","article_title":"风格的规定电饭锅"},{"aid":223,"article_link":"","article_pub_time":"2018-07-17","article_title":"新标发布公告"}]
         */

        private String path;
        private List<BannerListBean> bannerList;
        private List<BorrowListBean> borrowList;
        private List<PublicMsgListBean> publicMsgList;

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

        public List<BorrowListBean> getBorrowList() {
            return borrowList;
        }

        public void setBorrowList(List<BorrowListBean> borrowList) {
            this.borrowList = borrowList;
        }

        public List<PublicMsgListBean> getPublicMsgList() {
            return publicMsgList;
        }

        public void setPublicMsgList(List<PublicMsgListBean> publicMsgList) {
            this.publicMsgList = publicMsgList;
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

        public static class BorrowListBean {
            /**
             * ableTenderDate : 2018-07-27 11:45
             * amount : 30,000.00
             * annualRate : 3.0
             * borrowCode : X18D10082
             * borrowFrom : 1
             * borrowRandomId : 9651146e-fb9d-492b-9fbc-e7f5b39fbb22
             * borrowSpe : 个人借款
             * borrowStatus : 2
             * borrowStatusStr : 立即出借
             * borrowTitle : 是
             * deadlineNew : 2个月
             * id : 2616
             * investNumTotal : 30,000.00
             * isDayThe : 1
             * mortgageType : 1
             * mortgageTypeStr : 房产抵押
             * schedules : 0%
             * subsidies : 3.0
             * subsidiesRate : 0.0
             * timeFlag : 0
             */

            private String ableTenderDate;
            private String amount;
            private double annualRate;
            private String borrowCode;
            private int borrowFrom;
            private String borrowRandomId;
            private String borrowSpe;
            private int borrowStatus;
            private String borrowStatusStr;
            private String borrowTitle;
            private String deadlineNew;
            private int id;
            private String investNumTotal;
            private int isDayThe;
            private int mortgageType;
            private String mortgageTypeStr;
            private String schedules;
            private double subsidies;
            private double subsidiesRate;
            private int timeFlag;

            public String getAbleTenderDate() {
                return ableTenderDate;
            }

            public void setAbleTenderDate(String ableTenderDate) {
                this.ableTenderDate = ableTenderDate;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public double getAnnualRate() {
                return annualRate;
            }

            public void setAnnualRate(double annualRate) {
                this.annualRate = annualRate;
            }

            public String getBorrowCode() {
                return borrowCode;
            }

            public void setBorrowCode(String borrowCode) {
                this.borrowCode = borrowCode;
            }

            public int getBorrowFrom() {
                return borrowFrom;
            }

            public void setBorrowFrom(int borrowFrom) {
                this.borrowFrom = borrowFrom;
            }

            public String getBorrowRandomId() {
                return borrowRandomId;
            }

            public void setBorrowRandomId(String borrowRandomId) {
                this.borrowRandomId = borrowRandomId;
            }

            public String getBorrowSpe() {
                return borrowSpe;
            }

            public void setBorrowSpe(String borrowSpe) {
                this.borrowSpe = borrowSpe;
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

            public String getBorrowTitle() {
                return borrowTitle;
            }

            public void setBorrowTitle(String borrowTitle) {
                this.borrowTitle = borrowTitle;
            }

            public String getDeadlineNew() {
                return deadlineNew;
            }

            public void setDeadlineNew(String deadlineNew) {
                this.deadlineNew = deadlineNew;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getInvestNumTotal() {
                return investNumTotal;
            }

            public void setInvestNumTotal(String investNumTotal) {
                this.investNumTotal = investNumTotal;
            }

            public int getIsDayThe() {
                return isDayThe;
            }

            public void setIsDayThe(int isDayThe) {
                this.isDayThe = isDayThe;
            }

            public int getMortgageType() {
                return mortgageType;
            }

            public void setMortgageType(int mortgageType) {
                this.mortgageType = mortgageType;
            }

            public String getMortgageTypeStr() {
                return mortgageTypeStr;
            }

            public void setMortgageTypeStr(String mortgageTypeStr) {
                this.mortgageTypeStr = mortgageTypeStr;
            }

            public String getSchedules() {
                return schedules;
            }

            public void setSchedules(String schedules) {
                this.schedules = schedules;
            }

            public double getSubsidies() {
                return subsidies;
            }

            public void setSubsidies(double subsidies) {
                this.subsidies = subsidies;
            }

            public double getSubsidiesRate() {
                return subsidiesRate;
            }

            public void setSubsidiesRate(double subsidiesRate) {
                this.subsidiesRate = subsidiesRate;
            }

            public int getTimeFlag() {
                return timeFlag;
            }

            public void setTimeFlag(int timeFlag) {
                this.timeFlag = timeFlag;
            }
        }

        public static class PublicMsgListBean {
            /**
             * aid : 220
             * article_link :
             * article_pub_time : 2018-07-18
             * article_title : 风格的规定电饭锅
             */

            private int aid;
            private String article_link;
            private String article_pub_time;
            private String article_title;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public String getArticle_link() {
                return article_link;
            }

            public void setArticle_link(String article_link) {
                this.article_link = article_link;
            }

            public String getArticle_pub_time() {
                return article_pub_time;
            }

            public void setArticle_pub_time(String article_pub_time) {
                this.article_pub_time = article_pub_time;
            }

            public String getArticle_title() {
                return article_title;
            }

            public void setArticle_title(String article_title) {
                this.article_title = article_title;
            }
        }
    }
}
