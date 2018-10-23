package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class XiangMuGenZong_gson {

    /**
     * message : 成功了
     * result : {"imgList":[{"imgUrl":"upload/user/20141210/20180621134036.png","id":1697,"imgName":"啊","borrowId":3079,"uploadTime":"2018-10-12 17:50:52"},{"imgUrl":"upload/user/20141210/201412101432225731.jpg","id":1698,"imgName":"啊","borrowId":3079,"uploadTime":"2018-10-12 17:51:17"},{"imgUrl":"upload/user/20181015/20181015164436544.jpg","id":1726,"imgName":"dog4","borrowId":3079,"uploadTime":"2018-10-15 16:45:23"},{"imgUrl":"upload/user/20181015/20181015164436600.jpg","id":1727,"imgName":"动图","borrowId":3079,"uploadTime":"2018-10-15 16:45:23"},{"imgUrl":"upload/user/20181015/20181015164436589.jpg","id":1728,"imgName":"画","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164436818.jpg","id":1729,"imgName":"玫瑰","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164436814.jpg","id":1730,"imgName":"花海","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164436894.jpg","id":1731,"imgName":"狼与少女","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437221.jpg","id":1732,"imgName":"云海","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437290.jpg","id":1733,"imgName":"dog3","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437276.jpg","id":1734,"imgName":"火","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437428.jpg","id":1735,"imgName":"薰衣草","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437561.jpg","id":1736,"imgName":"dog2 ","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437539.jpg","id":1737,"imgName":"心形树","borrowId":3079,"uploadTime":"2018-10-15 16:45:25"},{"imgUrl":"upload/user/20181015/20181015164437671.jpg","id":1738,"imgName":"❤","borrowId":3079,"uploadTime":"2018-10-15 16:45:25"},{"imgUrl":"upload/user/20181015/20181015164437807.jpg","id":1739,"imgName":"dog1","borrowId":3079,"uploadTime":"2018-10-15 16:45:25"},{"imgUrl":"upload/user/20181015/20181015164437899.jpg","id":1740,"imgName":"山巅","borrowId":3079,"uploadTime":"2018-10-15 16:45:25"},{"imgUrl":"upload/user/20181015/20181015164459179.jpg","id":1741,"imgName":"梅花","borrowId":3079,"uploadTime":"2018-10-15 16:45:46"},{"imgUrl":"upload/user/20181015/20181015164459252.jpg","id":1742,"imgName":"向日葵","borrowId":3079,"uploadTime":"2018-10-15 16:45:46"},{"imgUrl":"upload/user/20181015/20181015164459229.jpg","id":1743,"imgName":"枫叶","borrowId":3079,"uploadTime":"2018-10-15 16:45:46"}],"tracking":{"lawCondition":"无","positionmonery":"250","overdueCondition":"无","borrowerRunCondition":"正常","borrowerRepaymentCondition":"正常","administrativePenalty":"无","endTime":"2018-10-12 00:00:00","positionnum":"150","borrowMoneyRunCondition":"正常"},"ICIMAGE":"http://127.0.0.1:8080/yxb_oms/","msg":2}
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
         * imgList : [{"imgUrl":"upload/user/20141210/20180621134036.png","id":1697,"imgName":"啊","borrowId":3079,"uploadTime":"2018-10-12 17:50:52"},{"imgUrl":"upload/user/20141210/201412101432225731.jpg","id":1698,"imgName":"啊","borrowId":3079,"uploadTime":"2018-10-12 17:51:17"},{"imgUrl":"upload/user/20181015/20181015164436544.jpg","id":1726,"imgName":"dog4","borrowId":3079,"uploadTime":"2018-10-15 16:45:23"},{"imgUrl":"upload/user/20181015/20181015164436600.jpg","id":1727,"imgName":"动图","borrowId":3079,"uploadTime":"2018-10-15 16:45:23"},{"imgUrl":"upload/user/20181015/20181015164436589.jpg","id":1728,"imgName":"画","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164436818.jpg","id":1729,"imgName":"玫瑰","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164436814.jpg","id":1730,"imgName":"花海","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164436894.jpg","id":1731,"imgName":"狼与少女","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437221.jpg","id":1732,"imgName":"云海","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437290.jpg","id":1733,"imgName":"dog3","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437276.jpg","id":1734,"imgName":"火","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437428.jpg","id":1735,"imgName":"薰衣草","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437561.jpg","id":1736,"imgName":"dog2 ","borrowId":3079,"uploadTime":"2018-10-15 16:45:24"},{"imgUrl":"upload/user/20181015/20181015164437539.jpg","id":1737,"imgName":"心形树","borrowId":3079,"uploadTime":"2018-10-15 16:45:25"},{"imgUrl":"upload/user/20181015/20181015164437671.jpg","id":1738,"imgName":"❤","borrowId":3079,"uploadTime":"2018-10-15 16:45:25"},{"imgUrl":"upload/user/20181015/20181015164437807.jpg","id":1739,"imgName":"dog1","borrowId":3079,"uploadTime":"2018-10-15 16:45:25"},{"imgUrl":"upload/user/20181015/20181015164437899.jpg","id":1740,"imgName":"山巅","borrowId":3079,"uploadTime":"2018-10-15 16:45:25"},{"imgUrl":"upload/user/20181015/20181015164459179.jpg","id":1741,"imgName":"梅花","borrowId":3079,"uploadTime":"2018-10-15 16:45:46"},{"imgUrl":"upload/user/20181015/20181015164459252.jpg","id":1742,"imgName":"向日葵","borrowId":3079,"uploadTime":"2018-10-15 16:45:46"},{"imgUrl":"upload/user/20181015/20181015164459229.jpg","id":1743,"imgName":"枫叶","borrowId":3079,"uploadTime":"2018-10-15 16:45:46"}]
         * tracking : {"lawCondition":"无","positionmonery":"250","overdueCondition":"无","borrowerRunCondition":"正常","borrowerRepaymentCondition":"正常","administrativePenalty":"无","endTime":"2018-10-12 00:00:00","positionnum":"150","borrowMoneyRunCondition":"正常"}
         * ICIMAGE : http://127.0.0.1:8080/yxb_oms/
         * msg : 2
         */

        private TrackingBean tracking;
        private String ICIMAGE;
        private int msg;
        private List<ImgListBean> imgList;

        public TrackingBean getTracking() {
            return tracking;
        }

        public void setTracking(TrackingBean tracking) {
            this.tracking = tracking;
        }

        public String getICIMAGE() {
            return ICIMAGE;
        }

        public void setICIMAGE(String ICIMAGE) {
            this.ICIMAGE = ICIMAGE;
        }

        public int getMsg() {
            return msg;
        }

        public void setMsg(int msg) {
            this.msg = msg;
        }

        public List<ImgListBean> getImgList() {
            return imgList;
        }

        public void setImgList(List<ImgListBean> imgList) {
            this.imgList = imgList;
        }

        public static class TrackingBean {
            /**
             * lawCondition : 无
             * positionmonery : 250
             * overdueCondition : 无
             * borrowerRunCondition : 正常
             * borrowerRepaymentCondition : 正常
             * administrativePenalty : 无
             * endTime : 2018-10-12 00:00:00
             * positionnum : 150
             * borrowMoneyRunCondition : 正常
             */

            private String lawCondition;
            private String positionmonery;
            private String overdueCondition;
            private String borrowerRunCondition;
            private String borrowerRepaymentCondition;
            private String administrativePenalty;
            private String endTime;
            private String positionnum;
            private String borrowMoneyRunCondition;

            public String getLawCondition() {
                return lawCondition;
            }

            public void setLawCondition(String lawCondition) {
                this.lawCondition = lawCondition;
            }

            public String getPositionmonery() {
                return positionmonery;
            }

            public void setPositionmonery(String positionmonery) {
                this.positionmonery = positionmonery;
            }

            public String getOverdueCondition() {
                return overdueCondition;
            }

            public void setOverdueCondition(String overdueCondition) {
                this.overdueCondition = overdueCondition;
            }

            public String getBorrowerRunCondition() {
                return borrowerRunCondition;
            }

            public void setBorrowerRunCondition(String borrowerRunCondition) {
                this.borrowerRunCondition = borrowerRunCondition;
            }

            public String getBorrowerRepaymentCondition() {
                return borrowerRepaymentCondition;
            }

            public void setBorrowerRepaymentCondition(String borrowerRepaymentCondition) {
                this.borrowerRepaymentCondition = borrowerRepaymentCondition;
            }

            public String getAdministrativePenalty() {
                return administrativePenalty;
            }

            public void setAdministrativePenalty(String administrativePenalty) {
                this.administrativePenalty = administrativePenalty;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getPositionnum() {
                return positionnum;
            }

            public void setPositionnum(String positionnum) {
                this.positionnum = positionnum;
            }

            public String getBorrowMoneyRunCondition() {
                return borrowMoneyRunCondition;
            }

            public void setBorrowMoneyRunCondition(String borrowMoneyRunCondition) {
                this.borrowMoneyRunCondition = borrowMoneyRunCondition;
            }
        }

        public static class ImgListBean {
            /**
             * imgUrl : upload/user/20141210/20180621134036.png
             * id : 1697
             * imgName : 啊
             * borrowId : 3079
             * uploadTime : 2018-10-12 17:50:52
             */

            private String imgUrl;
            private int id;
            private String imgName;
            private int borrowId;
            private String uploadTime;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgName() {
                return imgName;
            }

            public void setImgName(String imgName) {
                this.imgName = imgName;
            }

            public int getBorrowId() {
                return borrowId;
            }

            public void setBorrowId(int borrowId) {
                this.borrowId = borrowId;
            }

            public String getUploadTime() {
                return uploadTime;
            }

            public void setUploadTime(String uploadTime) {
                this.uploadTime = uploadTime;
            }
        }
    }
}
