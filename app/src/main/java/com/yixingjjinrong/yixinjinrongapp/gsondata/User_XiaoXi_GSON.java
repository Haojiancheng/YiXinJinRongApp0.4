package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class User_XiaoXi_GSON {

    /**
     * message : 查询成功
     * result : [{"sender":-1,"mailTitle":"投资获取积分","id":20535,"mailMode":2,"backgroundStatus":1,"borrowId":null,"sendTime":"2018-06-26 17:11:24","mailStatus":1,"mailContent":"恭喜您于20180626完成投资，获得10积分奖励。","reciver":10909,"mailType":2},{"sender":-1,"mailTitle":"会员通知","id":20536,"mailMode":2,"backgroundStatus":1,"borrowId":null,"sendTime":"2018-06-26 17:11:24","mailStatus":1,"mailContent":"尊敬的，恭喜您从普通1会员升级为普通2会员！详细信息请查看我的等级。","reciver":10909,"mailType":2},{"sender":-1,"mailTitle":"会员通知","id":20537,"mailMode":2,"backgroundStatus":1,"borrowId":null,"sendTime":"2018-06-26 17:11:24","mailStatus":1,"mailContent":"尊敬的，恭喜您从普通2会员升级为普通3会员！详细信息请查看我的等级。","reciver":10909,"mailType":2},{"sender":-1,"mailTitle":"会员通知","id":20538,"mailMode":2,"backgroundStatus":1,"borrowId":null,"sendTime":"2018-06-26 17:11:24","mailStatus":3,"mailContent":"尊敬的，恭喜您从普通3会员升级为VIP1会员！详细信息请查看我的等级。","reciver":10909,"mailType":2},{"sender":-1,"mailTitle":"理财投资报告","id":18376,"mailMode":2,"backgroundStatus":1,"borrowId":2022,"sendTime":"2018-04-12 09:38:42","mailStatus":3,"mailContent":"null","reciver":10909,"mailType":2},{"sender":-1,"mailTitle":"理财投资报告","id":18067,"mailMode":2,"backgroundStatus":1,"borrowId":1981,"sendTime":"2018-03-27 14:25:14","mailStatus":1,"mailContent":"null","reciver":10909,"mailType":2},{"sender":-1,"mailTitle":"投资获取积分","id":17860,"mailMode":2,"backgroundStatus":1,"borrowId":null,"sendTime":"2018-03-22 18:09:39","mailStatus":3,"mailContent":"恭喜您于20180322完成投资，获得1积分奖励。","reciver":10909,"mailType":2},{"sender":-1,"mailTitle":"会员通知","id":17861,"mailMode":2,"backgroundStatus":1,"borrowId":null,"sendTime":"2018-03-22 18:09:39","mailStatus":3,"mailContent":"尊敬的，恭喜您从小白会员升级为普通1会员！详细信息请查看我的等级。","reciver":10909,"mailType":2},{"sender":-1,"mailTitle":"充值成功报告","id":17859,"mailMode":2,"backgroundStatus":1,"borrowId":10909,"sendTime":"2018-03-22 18:09:17","mailStatus":3,"mailContent":"，您好！您于2018-03-22 18:20:04成功充值￥1000.00元，快去投资赚钱吧。","reciver":10909,"mailType":2},{"sender":-1,"mailTitle":"理财投资报告","id":17845,"mailMode":2,"backgroundStatus":1,"borrowId":1958,"sendTime":"2018-03-22 16:08:47","mailStatus":1,"mailContent":"null","reciver":10909,"mailType":2}]
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
         * sender : -1
         * mailTitle : 投资获取积分
         * id : 20535
         * mailMode : 2
         * backgroundStatus : 1
         * borrowId : null
         * sendTime : 2018-06-26 17:11:24
         * mailStatus : 1
         * mailContent : 恭喜您于20180626完成投资，获得10积分奖励。
         * reciver : 10909
         * mailType : 2
         */

        private int sender;
        private String mailTitle;
        private int id;
        private int mailMode;
        private int backgroundStatus;
        private Object borrowId;
        private String sendTime;
        private int mailStatus;
        private String mailContent;
        private int reciver;
        private int mailType;

        public int getSender() {
            return sender;
        }

        public void setSender(int sender) {
            this.sender = sender;
        }

        public String getMailTitle() {
            return mailTitle;
        }

        public void setMailTitle(String mailTitle) {
            this.mailTitle = mailTitle;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMailMode() {
            return mailMode;
        }

        public void setMailMode(int mailMode) {
            this.mailMode = mailMode;
        }

        public int getBackgroundStatus() {
            return backgroundStatus;
        }

        public void setBackgroundStatus(int backgroundStatus) {
            this.backgroundStatus = backgroundStatus;
        }

        public Object getBorrowId() {
            return borrowId;
        }

        public void setBorrowId(Object borrowId) {
            this.borrowId = borrowId;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public int getMailStatus() {
            return mailStatus;
        }

        public void setMailStatus(int mailStatus) {
            this.mailStatus = mailStatus;
        }

        public String getMailContent() {
            return mailContent;
        }

        public void setMailContent(String mailContent) {
            this.mailContent = mailContent;
        }

        public int getReciver() {
            return reciver;
        }

        public void setReciver(int reciver) {
            this.reciver = reciver;
        }

        public int getMailType() {
            return mailType;
        }

        public void setMailType(int mailType) {
            this.mailType = mailType;
        }
    }
}
