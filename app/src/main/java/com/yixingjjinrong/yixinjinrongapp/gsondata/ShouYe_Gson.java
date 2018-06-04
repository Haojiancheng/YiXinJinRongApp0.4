package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class ShouYe_Gson {

    /**
     * message : 查询成功
     * result : {"bannerList":[{"l_name":"引导页app","picurl":"upload/imageManage/20180524/201805241607397172.png","l_total_count":3,"tid":114,"hrefurl":""},{"l_name":"引导页app","picurl":"upload/imageManage/20180524/201805241608063343.png","l_total_count":3,"tid":114,"hrefurl":""},{"l_name":"引导页app","picurl":"upload/imageManage/20180524/201805241608346978.png","l_total_count":3,"tid":114,"hrefurl":""}],"borrowList":[{"borrowTitle":"企业房","borrowStatus":2,"schedules":"0.16%","mortgageType":"房产抵押","deadlineNew":"2个月","timeFlag":0,"annualRate":6,"ableTenderDate":"2018-05-23 11:23 ","id":2259,"amount":"10,000,000.00","borrowStatusStr":"募集中","isDayThe":1,"borrowSpe":"公司借款","borrowFrom":2,"borrowCode":"X18D10521","subsidies":6,"subsidiesRate":0,"investNumTotal":"9,983,100.00","borrowRandomId":"1de0c7e6-7c80-463d-8f35-96e631ee3fe3"},{"borrowTitle":"微信端资质证明2","borrowStatus":2,"schedules":"80.00%","mortgageType":"房产抵押","deadlineNew":"3个月","timeFlag":0,"annualRate":6,"ableTenderDate":"2018-05-04 13:45 ","id":2191,"amount":"500.00","borrowStatusStr":"募集中","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"X18D10454","subsidies":6,"subsidiesRate":0,"investNumTotal":"100.00","borrowRandomId":"ab922a0f-15dd-4623-ad28-76b9b85fb1fc"},{"borrowTitle":"测试00010503","borrowStatus":2,"schedules":"20.00%","mortgageType":"房产抵押","deadlineNew":"3个月","timeFlag":0,"annualRate":6,"ableTenderDate":"2018-05-03 14:50","id":2182,"amount":"500.00","borrowStatusStr":"募集中","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"X18D10445","subsidies":6,"subsidiesRate":0,"investNumTotal":"400.00","borrowRandomId":"f6933a11-1be7-4c42-b004-4d438cc72c47"}],"publicMsgList":[{"article_title":"app公告","article_pub_time":"2018-05-24","article_link":"","aid":208},{"article_title":"测试页数3","article_pub_time":"2018-01-31","article_link":"","aid":204}],"path":"http://192.168.1.84:8080/yxb_oms/"}
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
         * bannerList : [{"l_name":"引导页app","picurl":"upload/imageManage/20180524/201805241607397172.png","l_total_count":3,"tid":114,"hrefurl":""},{"l_name":"引导页app","picurl":"upload/imageManage/20180524/201805241608063343.png","l_total_count":3,"tid":114,"hrefurl":""},{"l_name":"引导页app","picurl":"upload/imageManage/20180524/201805241608346978.png","l_total_count":3,"tid":114,"hrefurl":""}]
         * borrowList : [{"borrowTitle":"企业房","borrowStatus":2,"schedules":"0.16%","mortgageType":"房产抵押","deadlineNew":"2个月","timeFlag":0,"annualRate":6,"ableTenderDate":"2018-05-23 11:23 ","id":2259,"amount":"10,000,000.00","borrowStatusStr":"募集中","isDayThe":1,"borrowSpe":"公司借款","borrowFrom":2,"borrowCode":"X18D10521","subsidies":6,"subsidiesRate":0,"investNumTotal":"9,983,100.00","borrowRandomId":"1de0c7e6-7c80-463d-8f35-96e631ee3fe3"},{"borrowTitle":"微信端资质证明2","borrowStatus":2,"schedules":"80.00%","mortgageType":"房产抵押","deadlineNew":"3个月","timeFlag":0,"annualRate":6,"ableTenderDate":"2018-05-04 13:45 ","id":2191,"amount":"500.00","borrowStatusStr":"募集中","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"X18D10454","subsidies":6,"subsidiesRate":0,"investNumTotal":"100.00","borrowRandomId":"ab922a0f-15dd-4623-ad28-76b9b85fb1fc"},{"borrowTitle":"测试00010503","borrowStatus":2,"schedules":"20.00%","mortgageType":"房产抵押","deadlineNew":"3个月","timeFlag":0,"annualRate":6,"ableTenderDate":"2018-05-03 14:50","id":2182,"amount":"500.00","borrowStatusStr":"募集中","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"X18D10445","subsidies":6,"subsidiesRate":0,"investNumTotal":"400.00","borrowRandomId":"f6933a11-1be7-4c42-b004-4d438cc72c47"}]
         * publicMsgList : [{"article_title":"app公告","article_pub_time":"2018-05-24","article_link":"","aid":208},{"article_title":"测试页数3","article_pub_time":"2018-01-31","article_link":"","aid":204}]
         * path : http://192.168.1.84:8080/yxb_oms/
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
             * l_name : 引导页app
             * picurl : upload/imageManage/20180524/201805241607397172.png
             * l_total_count : 3
             * tid : 114
             * hrefurl : 
             */

            private String l_name;
            private String picurl;
            private int l_total_count;
            private int tid;
            private String hrefurl;

            public String getL_name() {
                return l_name;
            }

            public void setL_name(String l_name) {
                this.l_name = l_name;
            }

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public int getL_total_count() {
                return l_total_count;
            }

            public void setL_total_count(int l_total_count) {
                this.l_total_count = l_total_count;
            }

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }

            public String getHrefurl() {
                return hrefurl;
            }

            public void setHrefurl(String hrefurl) {
                this.hrefurl = hrefurl;
            }
        }

        public static class BorrowListBean {
            /**
             * borrowTitle : 企业房
             * borrowStatus : 2
             * schedules : 0.16%
             * mortgageType : 房产抵押
             * deadlineNew : 2个月
             * timeFlag : 0
             * annualRate : 6.0
             * ableTenderDate : 2018-05-23 11:23 
             * id : 2259
             * amount : 10,000,000.00
             * borrowStatusStr : 募集中
             * isDayThe : 1
             * borrowSpe : 公司借款
             * borrowFrom : 2
             * borrowCode : X18D10521
             * subsidies : 6.0
             * subsidiesRate : 0.0
             * investNumTotal : 9,983,100.00
             * borrowRandomId : 1de0c7e6-7c80-463d-8f35-96e631ee3fe3
             */

            private String borrowTitle;
            private int borrowStatus;
            private String schedules;
            private String mortgageType;
            private String deadlineNew;
            private int timeFlag;
            private double annualRate;
            private String ableTenderDate;
            private int id;
            private String amount;
            private String borrowStatusStr;
            private int isDayThe;
            private String borrowSpe;
            private int borrowFrom;
            private String borrowCode;
            private double subsidies;
            private double subsidiesRate;
            private String investNumTotal;
            private String borrowRandomId;

            public String getBorrowTitle() {
                return borrowTitle;
            }

            public void setBorrowTitle(String borrowTitle) {
                this.borrowTitle = borrowTitle;
            }

            public int getBorrowStatus() {
                return borrowStatus;
            }

            public void setBorrowStatus(int borrowStatus) {
                this.borrowStatus = borrowStatus;
            }

            public String getSchedules() {
                return schedules;
            }

            public void setSchedules(String schedules) {
                this.schedules = schedules;
            }

            public String getMortgageType() {
                return mortgageType;
            }

            public void setMortgageType(String mortgageType) {
                this.mortgageType = mortgageType;
            }

            public String getDeadlineNew() {
                return deadlineNew;
            }

            public void setDeadlineNew(String deadlineNew) {
                this.deadlineNew = deadlineNew;
            }

            public int getTimeFlag() {
                return timeFlag;
            }

            public void setTimeFlag(int timeFlag) {
                this.timeFlag = timeFlag;
            }

            public double getAnnualRate() {
                return annualRate;
            }

            public void setAnnualRate(double annualRate) {
                this.annualRate = annualRate;
            }

            public String getAbleTenderDate() {
                return ableTenderDate;
            }

            public void setAbleTenderDate(String ableTenderDate) {
                this.ableTenderDate = ableTenderDate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getBorrowStatusStr() {
                return borrowStatusStr;
            }

            public void setBorrowStatusStr(String borrowStatusStr) {
                this.borrowStatusStr = borrowStatusStr;
            }

            public int getIsDayThe() {
                return isDayThe;
            }

            public void setIsDayThe(int isDayThe) {
                this.isDayThe = isDayThe;
            }

            public String getBorrowSpe() {
                return borrowSpe;
            }

            public void setBorrowSpe(String borrowSpe) {
                this.borrowSpe = borrowSpe;
            }

            public int getBorrowFrom() {
                return borrowFrom;
            }

            public void setBorrowFrom(int borrowFrom) {
                this.borrowFrom = borrowFrom;
            }

            public String getBorrowCode() {
                return borrowCode;
            }

            public void setBorrowCode(String borrowCode) {
                this.borrowCode = borrowCode;
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

            public String getInvestNumTotal() {
                return investNumTotal;
            }

            public void setInvestNumTotal(String investNumTotal) {
                this.investNumTotal = investNumTotal;
            }

            public String getBorrowRandomId() {
                return borrowRandomId;
            }

            public void setBorrowRandomId(String borrowRandomId) {
                this.borrowRandomId = borrowRandomId;
            }
        }

        public static class PublicMsgListBean {
            /**
             * article_title : app公告
             * article_pub_time : 2018-05-24
             * article_link : 
             * aid : 208
             */

            private String article_title;
            private String article_pub_time;
            private String article_link;
            private int aid;

            public String getArticle_title() {
                return article_title;
            }

            public void setArticle_title(String article_title) {
                this.article_title = article_title;
            }

            public String getArticle_pub_time() {
                return article_pub_time;
            }

            public void setArticle_pub_time(String article_pub_time) {
                this.article_pub_time = article_pub_time;
            }

            public String getArticle_link() {
                return article_link;
            }

            public void setArticle_link(String article_link) {
                this.article_link = article_link;
            }

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }
        }
    }
}
