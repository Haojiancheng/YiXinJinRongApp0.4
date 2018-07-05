package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class MyAddass_Gson {

    /**
     * message : 查询成功
     * result : {"addressList":[{"id":137,"createTime":"2018-03-23 12:11:49","status":1,"isDefault":1,"userId":11208,"receiverName":"Dddddddd","receiverAddress":"北京市东城区Lkjljl","receiverPhone":"17755555555"}]}
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
        private List<AddressListBean> addressList;

        public List<AddressListBean> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<AddressListBean> addressList) {
            this.addressList = addressList;
        }

        public static class AddressListBean {
            /**
             * id : 137
             * createTime : 2018-03-23 12:11:49
             * status : 1
             * isDefault : 1
             * userId : 11208
             * receiverName : Dddddddd
             * receiverAddress : 北京市东城区Lkjljl
             * receiverPhone : 17755555555
             */

            private int id;
            private String createTime;
            private int status;
            private int isDefault;
            private int userId;
            private String receiverName;
            private String receiverAddress;
            private String receiverPhone;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public String getReceiverAddress() {
                return receiverAddress;
            }

            public void setReceiverAddress(String receiverAddress) {
                this.receiverAddress = receiverAddress;
            }

            public String getReceiverPhone() {
                return receiverPhone;
            }

            public void setReceiverPhone(String receiverPhone) {
                this.receiverPhone = receiverPhone;
            }
        }
    }
}
