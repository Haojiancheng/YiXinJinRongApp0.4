package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class DuiHuanJiLu_gson {

    /**
     * message : 成功了
     * result : {"list":[{"createTime":"2018-06-22 16:48:33","memo":"兑换加息券","cut_score":1},{"createTime":"2018-06-22 11:57:48","memo":"兑换代金券","cut_score":3},{"createTime":"2018-06-22 11:57:42","memo":"兑换加息券","cut_score":3},{"createTime":"2018-06-15 16:47:51","memo":"兑换代金券","cut_score":8},{"createTime":"2018-06-15 16:47:45","memo":"兑换加息券","cut_score":7},{"createTime":"2018-06-05 09:54:19","memo":"兑换代金券","cut_score":2},{"createTime":"2018-05-18 17:13:20","memo":"兑换代金券","cut_score":1},{"createTime":"2018-05-18 17:13:10","memo":"兑换加息券","cut_score":2}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * createTime : 2018-06-22 16:48:33
             * memo : 兑换加息券
             * cut_score : 1
             */

            private String createTime;
            private String memo;
            private int cut_score;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public int getCut_score() {
                return cut_score;
            }

            public void setCut_score(int cut_score) {
                this.cut_score = cut_score;
            }
        }
    }
}
