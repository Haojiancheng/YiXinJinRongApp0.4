package com.yixingjjinrong.yixinjinrongapp.eventbus_data;

public class User_data {
    private String phone;
    private String User_zhuangtai;
    private String userToken;
    private int user_id;

    public User_data(String phone, String user_zhuangtai, String userToken, int user_id) {
        this.phone = phone;
        User_zhuangtai = user_zhuangtai;
        this.userToken = userToken;
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_zhuangtai() {
        return User_zhuangtai;
    }

    public void setUser_zhuangtai(String user_zhuangtai) {
        User_zhuangtai = user_zhuangtai;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
