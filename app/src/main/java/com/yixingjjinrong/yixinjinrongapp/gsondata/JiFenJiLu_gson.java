package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class JiFenJiLu_gson {

    /**
     * message : 成功了
     * result : {"list":[{"add_score":10,"createTime":"2018-06-26 17:23:13","memo":"投资获得积分"},{"add_score":1,"createTime":"2018-03-22 18:20:29","memo":"投资获得积分"}]}
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
             * add_score : 10
             * createTime : 2018-06-26 17:23:13
             * memo : 投资获得积分
             */

            private int add_score;
            private String createTime;
            private String memo;

            public int getAdd_score() {
                return add_score;
            }

            public void setAdd_score(int add_score) {
                this.add_score = add_score;
            }

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
        }
    }
}
