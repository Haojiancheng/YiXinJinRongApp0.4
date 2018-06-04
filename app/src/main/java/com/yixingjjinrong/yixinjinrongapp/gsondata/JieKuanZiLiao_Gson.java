package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class JieKuanZiLiao_Gson {

    /**
     * message : 成功了
     * result : {"ICIMAGE":"http://192.168.1.84:8080/yxb_mobile/","qualificationList":[{"imgName":"哈哈哈","imgUrl":"upload/user/20180529/20180529094440501.jpg"},{"imgName":"哈哈哈","imgUrl":"upload/user/20180529/20180529094440448.jpg"},{"imgName":"哈哈","imgUrl":"upload/user/20180529/20180529094440677.jpg"},{"imgName":"哈哈","imgUrl":"upload/user/20180529/20180529094440493.jpg"}]}
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
         * ICIMAGE : http://192.168.1.84:8080/yxb_mobile/
         * qualificationList : [{"imgName":"哈哈哈","imgUrl":"upload/user/20180529/20180529094440501.jpg"},{"imgName":"哈哈哈","imgUrl":"upload/user/20180529/20180529094440448.jpg"},{"imgName":"哈哈","imgUrl":"upload/user/20180529/20180529094440677.jpg"},{"imgName":"哈哈","imgUrl":"upload/user/20180529/20180529094440493.jpg"}]
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
             * imgName : 哈哈哈
             * imgUrl : upload/user/20180529/20180529094440501.jpg
             */

            private String imgName;
            private String imgUrl;

            public String getImgName() {
                return imgName;
            }

            public void setImgName(String imgName) {
                this.imgName = imgName;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}
