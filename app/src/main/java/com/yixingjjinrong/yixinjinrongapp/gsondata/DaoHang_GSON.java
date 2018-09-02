package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class DaoHang_GSON {

    /**
     * message : 获取成功
     * result : {"imgList":[{"l_name":"app引导页栏目","picurl":"upload/imageManage/20180801/201808011422591856.png","l_total_count":3,"tid":97,"hrefurl":""},{"l_name":"app引导页栏目","picurl":"upload/imageManage/20180801/201808011423157472.png","l_total_count":3,"tid":97,"hrefurl":""},{"l_name":"app引导页栏目","picurl":"upload/imageManage/20180801/201808011423261955.png","l_total_count":3,"tid":97,"hrefurl":""}],"path":"http://bck-temp-stg.yxb.com/"}
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
         * imgList : [{"l_name":"app引导页栏目","picurl":"upload/imageManage/20180801/201808011422591856.png","l_total_count":3,"tid":97,"hrefurl":""},{"l_name":"app引导页栏目","picurl":"upload/imageManage/20180801/201808011423157472.png","l_total_count":3,"tid":97,"hrefurl":""},{"l_name":"app引导页栏目","picurl":"upload/imageManage/20180801/201808011423261955.png","l_total_count":3,"tid":97,"hrefurl":""}]
         * path : http://bck-temp-stg.yxb.com/
         */

        private String path;
        private List<ImgListBean> imgList;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<ImgListBean> getImgList() {
            return imgList;
        }

        public void setImgList(List<ImgListBean> imgList) {
            this.imgList = imgList;
        }

        public static class ImgListBean {
            /**
             * l_name : app引导页栏目
             * picurl : upload/imageManage/20180801/201808011422591856.png
             * l_total_count : 3
             * tid : 97
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
    }
}
