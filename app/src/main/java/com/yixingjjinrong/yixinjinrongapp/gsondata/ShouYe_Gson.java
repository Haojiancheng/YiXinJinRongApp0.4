package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class ShouYe_Gson {


    /**
     * message : 查询成功
     * result : {"bannerList":[{"l_name":"正式app的banner栏目","picurl":"upload/imageManage/20180801/201808011345553381.jpg","l_total_count":3,"tid":119,"hrefurl":"www.baidu.com"},{"l_name":"正式app的banner栏目","picurl":"upload/imageManage/20180801/20180801134633305.jpg","l_total_count":3,"tid":119,"hrefurl":"www.baidu.com"},{"l_name":"正式app的banner栏目","picurl":"upload/imageManage/20180801/201808011346597369.png","l_total_count":3,"tid":119,"hrefurl":"www.baidu.com"}],"borrowList":[{"borrowTitle":"后台测试专用08-10","borrowStatus":2,"schedules":"0%","mortgageTypeStr":"房产抵押","mortgageType":1,"deadlineNew":"3个月","timeFlag":0,"annualRate":"6.0","ableTenderDate":"2018-08-10 16:30 ","id":2697,"amount":"100.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"xgz-2018-08-10-ceshi","subsidies":"5.0","subsidiesRate":"1.0","investNumTotal":"100.00","borrowRandomId":"8ec38ff2-e200-462f-a57e-7019afffe5fa"},{"borrowTitle":"测试0806001","borrowStatus":2,"schedules":"1.59%","mortgageTypeStr":"房产抵押","mortgageType":1,"deadlineNew":"4个月","timeFlag":0,"annualRate":"9.5","ableTenderDate":"2018-08-06 10:50 ","id":2679,"amount":"125,500.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"aaaa0806001","subsidies":"9.5","subsidiesRate":"0.0","investNumTotal":"123,500.00","borrowRandomId":"6b96c519-822a-4df7-9baa-c3b90dabc349"},{"borrowTitle":"测试0803002","borrowStatus":2,"schedules":"0.07%","mortgageTypeStr":"车辆抵押","mortgageType":4,"deadlineNew":"3个月","timeFlag":0,"annualRate":"9.0","ableTenderDate":"2018-08-03 19:09","id":2666,"amount":"1,233,550.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"abcd1234567","subsidies":"9.0","subsidiesRate":"0.0","investNumTotal":"1,232,650.00","borrowRandomId":"e74ed447-3e67-4667-a698-f24b710ab9bd"}],"publicMsgList":[{"article_title":"亿信宝公告","article_pub_time":"2018-08-01","article_link":"","aid":228},{"article_title":"程成城称乘","article_pub_time":"2018-08-01","article_link":"","aid":229}],"path":"http://newbck.yxb.com/"}
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
         * bannerList : [{"l_name":"正式app的banner栏目","picurl":"upload/imageManage/20180801/201808011345553381.jpg","l_total_count":3,"tid":119,"hrefurl":"www.baidu.com"},{"l_name":"正式app的banner栏目","picurl":"upload/imageManage/20180801/20180801134633305.jpg","l_total_count":3,"tid":119,"hrefurl":"www.baidu.com"},{"l_name":"正式app的banner栏目","picurl":"upload/imageManage/20180801/201808011346597369.png","l_total_count":3,"tid":119,"hrefurl":"www.baidu.com"}]
         * borrowList : [{"borrowTitle":"后台测试专用08-10","borrowStatus":2,"schedules":"0%","mortgageTypeStr":"房产抵押","mortgageType":1,"deadlineNew":"3个月","timeFlag":0,"annualRate":"6.0","ableTenderDate":"2018-08-10 16:30 ","id":2697,"amount":"100.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"xgz-2018-08-10-ceshi","subsidies":"5.0","subsidiesRate":"1.0","investNumTotal":"100.00","borrowRandomId":"8ec38ff2-e200-462f-a57e-7019afffe5fa"},{"borrowTitle":"测试0806001","borrowStatus":2,"schedules":"1.59%","mortgageTypeStr":"房产抵押","mortgageType":1,"deadlineNew":"4个月","timeFlag":0,"annualRate":"9.5","ableTenderDate":"2018-08-06 10:50 ","id":2679,"amount":"125,500.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"aaaa0806001","subsidies":"9.5","subsidiesRate":"0.0","investNumTotal":"123,500.00","borrowRandomId":"6b96c519-822a-4df7-9baa-c3b90dabc349"},{"borrowTitle":"测试0803002","borrowStatus":2,"schedules":"0.07%","mortgageTypeStr":"车辆抵押","mortgageType":4,"deadlineNew":"3个月","timeFlag":0,"annualRate":"9.0","ableTenderDate":"2018-08-03 19:09","id":2666,"amount":"1,233,550.00","borrowStatusStr":"立即出借","isDayThe":1,"borrowSpe":"个人借款","borrowFrom":1,"borrowCode":"abcd1234567","subsidies":"9.0","subsidiesRate":"0.0","investNumTotal":"1,232,650.00","borrowRandomId":"e74ed447-3e67-4667-a698-f24b710ab9bd"}]
         * publicMsgList : [{"article_title":"亿信宝公告","article_pub_time":"2018-08-01","article_link":"","aid":228},{"article_title":"程成城称乘","article_pub_time":"2018-08-01","article_link":"","aid":229}]
         * path : http://newbck.yxb.com/
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
             * l_name : 正式app的banner栏目
             * picurl : upload/imageManage/20180801/201808011345553381.jpg
             * l_total_count : 3
             * tid : 119
             * hrefurl : www.baidu.com
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
             * borrowTitle : 后台测试专用08-10
             * borrowStatus : 2
             * schedules : 0%
             * mortgageTypeStr : 房产抵押
             * mortgageType : 1
             * deadlineNew : 3个月
             * timeFlag : 0
             * annualRate : 6.0
             * ableTenderDate : 2018-08-10 16:30
             * id : 2697
             * amount : 100.00
             * borrowStatusStr : 立即出借
             * isDayThe : 1
             * borrowSpe : 个人借款
             * borrowFrom : 1
             * borrowCode : xgz-2018-08-10-ceshi
             * subsidies : 5.0
             * subsidiesRate : 1.0
             * investNumTotal : 100.00
             * borrowRandomId : 8ec38ff2-e200-462f-a57e-7019afffe5fa
             */

            private String borrowTitle;
            private int borrowStatus;
            private String schedules;
            private String mortgageTypeStr;
            private int mortgageType;
            private String deadlineNew;
            private int timeFlag;
            private String annualRate;
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
