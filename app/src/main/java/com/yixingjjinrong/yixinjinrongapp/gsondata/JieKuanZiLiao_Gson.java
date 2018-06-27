package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class JieKuanZiLiao_Gson {


    /**
     * message : 成功了
     * result : {"qualificationList":[{"imgUrl":"upload/user/20180622/20180622135805649.jpg","imgName":""}],"ICIMAGE":"http://127.0.0.1:8080/yxb_oms/"}
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
         * qualificationList : [{"imgUrl":"upload/user/20180622/20180622135805649.jpg","imgName":""}]
         * ICIMAGE : http://127.0.0.1:8080/yxb_oms/
         */

        private String ICIMAGE;
        private List<QualificationListBean> qualificationList;

        public String getICIMAGE() {
            return ICIMAGE;
        }

        public void setICIMAGE(String ICIMAGE) {
            this.ICIMAGE = ICIMAGE;
        }

        public List<QualificationListBean> getQualificationList() {
            return qualificationList;
        }

        public void setQualificationList(List<QualificationListBean> qualificationList) {
            this.qualificationList = qualificationList;
        }

        public static class QualificationListBean {
            /**
             * imgUrl : upload/user/20180622/20180622135805649.jpg
             * imgName :
             */

            private String imgUrl;
            private String imgName;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getImgName() {
                return imgName;
            }

            public void setImgName(String imgName) {
                this.imgName = imgName;
            }
        }
    }
}
