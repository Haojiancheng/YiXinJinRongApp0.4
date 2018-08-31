package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class MyAddass_Gson {


    /**
     * message : 查询成功
     * result : {"addressList":[{"createTime":"2018-08-31 19:31:38","isDefault":0,"receiverId":"215","userId":11298,"receiverName":"你会放大","STATUS":1,"receiverAddress":"天津市 天津市 和平区","addressDetail":"的发送到发送到发送到","receiverPhone":"15726615301"},{"createTime":"2018-08-31 19:35:45","isDefault":0,"receiverId":"216","userId":11298,"receiverName":"好紧张","STATUS":1,"receiverAddress":"山西省 太原市 小店区","addressDetail":"是大V防晒大V所需 ","receiverPhone":"121634654"}]}
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
             * createTime : 2018-08-31 19:31:38
             * isDefault : 0
             * receiverId : 215
             * userId : 11298
             * receiverName : 你会放大
             * STATUS : 1
             * receiverAddress : 天津市 天津市 和平区
             * addressDetail : 的发送到发送到发送到
             * receiverPhone : 15726615301
             */

            private String createTime;
            private int isDefault;
            private String receiverId;
            private int userId;
            private String receiverName;
            private int STATUS;
            private String receiverAddress;
            private String addressDetail;
            private String receiverPhone;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public String getReceiverId() {
                return receiverId;
            }

            public void setReceiverId(String receiverId) {
                this.receiverId = receiverId;
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

            public int getSTATUS() {
                return STATUS;
            }

            public void setSTATUS(int STATUS) {
                this.STATUS = STATUS;
            }

            public String getReceiverAddress() {
                return receiverAddress;
            }

            public void setReceiverAddress(String receiverAddress) {
                this.receiverAddress = receiverAddress;
            }

            public String getAddressDetail() {
                return addressDetail;
            }

            public void setAddressDetail(String addressDetail) {
                this.addressDetail = addressDetail;
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
