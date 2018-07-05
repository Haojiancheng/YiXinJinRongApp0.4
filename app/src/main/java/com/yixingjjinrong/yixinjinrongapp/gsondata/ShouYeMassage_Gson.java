package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class ShouYeMassage_Gson {

    /**
     * message : 查询成功
     * result : [{"id":208,"article_title":"app公告","arts":1,"lanmu_name":"公告栏目","article_pub_time":"2018-05-24","page_id":91,"lanmu_id":99,"adf":1},{"id":204,"article_title":"测试页数3","arts":1,"lanmu_name":"公告栏目","article_pub_time":"2018-01-31","page_id":91,"lanmu_id":99,"adf":1},{"id":203,"article_title":"测试页数2","arts":1,"lanmu_name":"公告栏目","article_pub_time":"2018-01-31","page_id":91,"lanmu_id":99,"adf":1},{"id":201,"article_title":"测试孙","arts":1,"lanmu_name":"公告栏目","article_pub_time":"2018-01-30","page_id":91,"lanmu_id":99,"adf":1},{"id":188,"article_title":"12345","arts":1,"lanmu_name":"公告栏目","article_pub_time":"2017-08-28","page_id":91,"lanmu_id":99,"adf":1},{"id":184,"article_title":"bck 后台","arts":1,"lanmu_name":"公告栏目","article_pub_time":"2017-08-03","page_id":91,"lanmu_id":99,"adf":1},{"id":183,"article_title":"草","arts":1,"lanmu_name":"公告栏目","article_pub_time":"2017-08-03","page_id":91,"lanmu_id":99,"adf":1},{"id":182,"article_title":"测试123","arts":1,"lanmu_name":"公告栏目","article_pub_time":"2017-08-03","page_id":91,"lanmu_id":99,"adf":1},{"id":181,"article_title":"真的试试","arts":1,"lanmu_name":"公告栏目","article_pub_time":"2017-08-03","page_id":91,"lanmu_id":99,"adf":1},{"id":179,"article_title":"xinzenge ","arts":2,"lanmu_name":"公告栏目","article_pub_time":"2017-08-02","page_id":91,"lanmu_id":99,"adf":1}]
     * state : success
     */

    private String message;
    private String state;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 208
         * article_title : app公告
         * arts : 1
         * lanmu_name : 公告栏目
         * article_pub_time : 2018-05-24
         * page_id : 91
         * lanmu_id : 99
         * adf : 1
         */

        private int id;
        private String article_title;
        private int arts;
        private String lanmu_name;
        private String article_pub_time;
        private int page_id;
        private int lanmu_id;
        private int adf;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getArticle_title() {
            return article_title;
        }

        public void setArticle_title(String article_title) {
            this.article_title = article_title;
        }

        public int getArts() {
            return arts;
        }

        public void setArts(int arts) {
            this.arts = arts;
        }

        public String getLanmu_name() {
            return lanmu_name;
        }

        public void setLanmu_name(String lanmu_name) {
            this.lanmu_name = lanmu_name;
        }

        public String getArticle_pub_time() {
            return article_pub_time;
        }

        public void setArticle_pub_time(String article_pub_time) {
            this.article_pub_time = article_pub_time;
        }

        public int getPage_id() {
            return page_id;
        }

        public void setPage_id(int page_id) {
            this.page_id = page_id;
        }

        public int getLanmu_id() {
            return lanmu_id;
        }

        public void setLanmu_id(int lanmu_id) {
            this.lanmu_id = lanmu_id;
        }

        public int getAdf() {
            return adf;
        }

        public void setAdf(int adf) {
            this.adf = adf;
        }
    }
}
