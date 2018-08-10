package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class MyAddass_Gson {

    /**
     * message : 查询成功
     * result : {"addressList":[{"id":176,"createTime":"2018-08-09 14:41:19","isDefault":1,"userId":11298,"receiverName":"贺培勇","STATUS":1,"receiverAddress":"北京市东城区","addressDetail":"我家我家","receiverPhone":"18539396112"},{"id":175,"createTime":"2018-08-09 14:34:27","isDefault":0,"userId":11298,"receiverName":"贺培勇","STATUS":1,"receiverAddress":"北京市东城区","addressDetail":"逆袭wohyizhzy","receiverPhone":"18620180301"},{"id":177,"createTime":"2018-08-09 14:41:43","isDefault":0,"userId":11298,"receiverName":"贺培勇3","STATUS":1,"receiverAddress":"北京市东城区","addressDetail":"哈哈哈哈","receiverPhone":"18539396112"},{"id":178,"createTime":"2018-08-09 14:44:39","isDefault":0,"userId":11298,"receiverName":"贺培勇1111","STATUS":1,"receiverAddress":"北京市东城区","addressDetail":"噢所以影子一样","receiverPhone":"18533333333"}]}
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
             * id : 176
             * createTime : 2018-08-09 14:41:19
             * isDefault : 1
             * userId : 11298
             * receiverName : 贺培勇
             * STATUS : 1
             * receiverAddress : 北京市东城区
             * addressDetail : 我家我家
             * receiverPhone : 18539396112
             */

            private int id;
            private String createTime;
            private int isDefault;
            private int userId;
            private String receiverName;
            private int STATUS;
            private String receiverAddress;
            private String addressDetail;
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
