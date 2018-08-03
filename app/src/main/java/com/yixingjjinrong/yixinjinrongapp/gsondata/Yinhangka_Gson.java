package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class Yinhangka_Gson {


    /**
     * message : 成功了
     * bankImage : http://192.168.1.79:8080/yxb_mobile/images/bankMobile/js.png
     * result : {"userId":11298,"token":"login:864711326104376","loginId":"login:11298"}
     * state : success
     * bankName : 中国建设银行
     * msg :
     * usableSum : 49058.00
     * cardNum : 4367 **** **** 4415
     */

    private String message;
    private String bankImage;
    private String result;
    private String state;
    private String bankName;
    private String msg;
    private String usableSum;
    private String cardNum;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBankImage() {
        return bankImage;
    }

    public void setBankImage(String bankImage) {
        this.bankImage = bankImage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsableSum() {
        return usableSum;
    }

    public void setUsableSum(String usableSum) {
        this.usableSum = usableSum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
