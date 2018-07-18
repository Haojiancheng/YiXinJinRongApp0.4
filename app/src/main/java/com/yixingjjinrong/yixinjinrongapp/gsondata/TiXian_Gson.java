package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class TiXian_Gson {

    /**
     * message : 成功了
     * result : {"userId":11298}
     * freeMoney : 372908.5
     * state : success
     * image : http://192.168.1.79:8080/yxb_mobile/images/bankMobile/js.png
     * bankName : 中国建设银行
     * msg :
     * usableSum : 372908.50
     * cardNum : 4367 **** **** 4415
     */

    private String message;
    private String result;
    private double freeMoney;
    private String state;
    private String image;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(double freeMoney) {
        this.freeMoney = freeMoney;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
