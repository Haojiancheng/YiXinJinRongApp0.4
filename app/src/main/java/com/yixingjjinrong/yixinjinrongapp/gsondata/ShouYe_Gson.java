package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class ShouYe_Gson {


    /**
     * message : 查询成功
     * result : {"bannerList":[{"l_name":"引导页app","picurl":"upload/imageManage/20180918/201809181801016030.png","l_total_count":3,"tid":114,"hrefurl":""},{"l_name":"引导页app","picurl":"upload/imageManage/20180918/201809181801144302.png","l_total_count":3,"tid":114,"hrefurl":""},{"l_name":"引导页app","picurl":"upload/imageManage/20180918/201809181801353746.png","l_total_count":3,"tid":114,"hrefurl":""}],"borrowList":[{"borrowTitle":"谢庚哲专用","borrowStatus":2,"schedules":"0%","mortgageTypeStr":"车辆抵押","mortgageType":4,"deadlineNew":"3个月","timeFlag":0,"annualRate":"9.0","timeFlags":1,"ableTenderDate":"2018-10-15 14:57 ","id":3088,"amount":"1,200,000.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"95168316","subsidies":"9.0","subsidiesRate":"0.0","borrowSafelevel":1,"investNumTotal":"1,200,000.00","borrowRandomId":"61b20f0c-90f5-420e-a36e-c61a37ae30d4"},{"borrowTitle":"AAA级别1015004","borrowStatus":2,"schedules":"0.01%","mortgageTypeStr":"车辆抵押","mortgageType":4,"deadlineNew":"3个月","timeFlag":0,"annualRate":"9.0","timeFlags":1,"ableTenderDate":"2018-10-15 14:34","id":3087,"amount":"3,000,000.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"aaaa-1015004","subsidies":"9.0","subsidiesRate":"0.0","borrowSafelevel":1,"investNumTotal":"2,999,700.00","borrowRandomId":"e4ad9be9-7116-4ebc-8fb0-6fa7a1973da5"},{"borrowTitle":"BBB级别1015003","borrowStatus":2,"schedules":"0%","mortgageTypeStr":"车辆抵押","mortgageType":4,"deadlineNew":"3个月","timeFlag":0,"annualRate":"9.0","timeFlags":1,"ableTenderDate":"2018-10-15 14:34","id":3086,"amount":"3,000,000.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"aaaa-1015003","subsidies":"9.0","subsidiesRate":"0.0","borrowSafelevel":4,"investNumTotal":"3,000,000.00","borrowRandomId":"54de6d3a-b47f-46f2-bc61-dec436e7a587"}],"publicMsgList":[{"article_title":"亿信宝公告","article_pub_time":"2018-08-01","article_link":"","aid":228},{"article_title":"程成城称乘","article_pub_time":"2018-08-01","article_link":"","aid":229}],"path":"http://192.168.1.79:8080/yxb_oms/"}
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
         * bannerList : [{"l_name":"引导页app","picurl":"upload/imageManage/20180918/201809181801016030.png","l_total_count":3,"tid":114,"hrefurl":""},{"l_name":"引导页app","picurl":"upload/imageManage/20180918/201809181801144302.png","l_total_count":3,"tid":114,"hrefurl":""},{"l_name":"引导页app","picurl":"upload/imageManage/20180918/201809181801353746.png","l_total_count":3,"tid":114,"hrefurl":""}]
         * borrowList : [{"borrowTitle":"谢庚哲专用","borrowStatus":2,"schedules":"0%","mortgageTypeStr":"车辆抵押","mortgageType":4,"deadlineNew":"3个月","timeFlag":0,"annualRate":"9.0","timeFlags":1,"ableTenderDate":"2018-10-15 14:57 ","id":3088,"amount":"1,200,000.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"95168316","subsidies":"9.0","subsidiesRate":"0.0","borrowSafelevel":1,"investNumTotal":"1,200,000.00","borrowRandomId":"61b20f0c-90f5-420e-a36e-c61a37ae30d4"},{"borrowTitle":"AAA级别1015004","borrowStatus":2,"schedules":"0.01%","mortgageTypeStr":"车辆抵押","mortgageType":4,"deadlineNew":"3个月","timeFlag":0,"annualRate":"9.0","timeFlags":1,"ableTenderDate":"2018-10-15 14:34","id":3087,"amount":"3,000,000.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"aaaa-1015004","subsidies":"9.0","subsidiesRate":"0.0","borrowSafelevel":1,"investNumTotal":"2,999,700.00","borrowRandomId":"e4ad9be9-7116-4ebc-8fb0-6fa7a1973da5"},{"borrowTitle":"BBB级别1015003","borrowStatus":2,"schedules":"0%","mortgageTypeStr":"车辆抵押","mortgageType":4,"deadlineNew":"3个月","timeFlag":0,"annualRate":"9.0","timeFlags":1,"ableTenderDate":"2018-10-15 14:34","id":3086,"amount":"3,000,000.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"aaaa-1015003","subsidies":"9.0","subsidiesRate":"0.0","borrowSafelevel":4,"investNumTotal":"3,000,000.00","borrowRandomId":"54de6d3a-b47f-46f2-bc61-dec436e7a587"}]
         * publicMsgList : [{"article_title":"亿信宝公告","article_pub_time":"2018-08-01","article_link":"","aid":228},{"article_title":"程成城称乘","article_pub_time":"2018-08-01","article_link":"","aid":229}]
         * path : http://192.168.1.79:8080/yxb_oms/
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
             * picurl : upload/imageManage/20180918/201809181801016030.png
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
             * borrowTitle : 谢庚哲专用
             * borrowStatus : 2
             * schedules : 0%
             * mortgageTypeStr : 车辆抵押
             * mortgageType : 4
             * deadlineNew : 3个月
             * timeFlag : 0
             * annualRate : 9.0
             * timeFlags : 1
             * ableTenderDate : 2018-10-15 14:57
             * id : 3088
             * amount : 1,200,000.00
             * borrowStatusStr : 立即出借
             * isDayThe : 1
             * borrowSpe : 个人借款
             * borrowFrom : 1
             * borrowCode : 95168316
             * subsidies : 9.0
             * subsidiesRate : 0.0
             * borrowSafelevel : 1
             * investNumTotal : 1,200,000.00
             * borrowRandomId : 61b20f0c-90f5-420e-a36e-c61a37ae30d4
             */

            private String borrowTitle;
            private int borrowStatus;
            private String schedules;
            private String mortgageTypeStr;
            private int mortgageType;
            private String deadlineNew;
            private int timeFlag;
            private String annualRate;
            private int timeFlags;
            private String ableTenderDate;
            private int id;
            private String amount;
            private String borrowStatusStr;
            private int isDayThe;
            private String borrowSpe;
            private int borrowFrom;
            private String borrowCode;
            private String subsidies;
            private String subsidiesRate;
            private int borrowSafelevel;
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

            public String getMortgageTypeStr() {
                return mortgageTypeStr;
            }

            public void setMortgageTypeStr(String mortgageTypeStr) {
                this.mortgageTypeStr = mortgageTypeStr;
            }

            public int getMortgageType() {
                return mortgageType;
            }

            public void setMortgageType(int mortgageType) {
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

            public String getAnnualRate() {
                return annualRate;
            }

            public void setAnnualRate(String annualRate) {
                this.annualRate = annualRate;
            }

            public int getTimeFlags() {
                return timeFlags;
            }

            public void setTimeFlags(int timeFlags) {
                this.timeFlags = timeFlags;
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

            public String getSubsidies() {
                return subsidies;
            }

            public void setSubsidies(String subsidies) {
                this.subsidies = subsidies;
            }

            public String getSubsidiesRate() {
                return subsidiesRate;
            }

            public void setSubsidiesRate(String subsidiesRate) {
                this.subsidiesRate = subsidiesRate;
            }

            public int getBorrowSafelevel() {
                return borrowSafelevel;
            }

            public void setBorrowSafelevel(int borrowSafelevel) {
                this.borrowSafelevel = borrowSafelevel;
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
             * article_title : 亿信宝公告
             * article_pub_time : 2018-08-01
             * article_link :
             * aid : 228
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
