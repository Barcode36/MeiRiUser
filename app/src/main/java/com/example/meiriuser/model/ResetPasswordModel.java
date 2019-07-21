package com.example.meiriuser.model;

/**
 * Created by admin on 2019/7/18.
 */

public class ResetPasswordModel {
    private String tel;
    private int country;
    private int sms_code;

    public String getTel() {
        return tel;
    }

    public int getCountry() {
        return country;
    }

    public int getSms_code() {
        return sms_code;
    }

    public String getPassword() {
        return password;
    }

    private String password;

    public ResetPasswordModel(String tel, int country, int sms_code, String password) {
        this.tel = tel;
        this.country = country;
        this.sms_code = sms_code;
        this.password = password;
    }




}
