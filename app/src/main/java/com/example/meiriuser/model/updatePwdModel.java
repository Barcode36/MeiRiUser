package com.example.meiriuser.model;

/**
 * Created by admin on 2019/6/24.
 */

public class updatePwdModel {
    private String password_old;

    public updatePwdModel(String password_old, String password_new) {
        this.password_old = password_old;
        this.password_new = password_new;
    }

    private String password_new;

}
