package com.example.meiriuser.model.net;

/**
 * Created by admin on 2019/6/21.
 */

public class LoginCodeModel {
    private String tel;

    public LoginCodeModel(String tel, int sms_code) {
        this.tel = tel;
        this.sms_code = sms_code;
    }

    private int sms_code;


}
