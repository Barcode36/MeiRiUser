package com.example.meiriuser.model;

/**
 * Created by admin on 2019/5/29.
 */

public class CleanMoreContentModel {

    private String title;
    private int res;
    private boolean isCheck;

    public CleanMoreContentModel(String title, int res,boolean isCheck) {
        this.title = title;
        this.res = res;
        this.isCheck=isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public String getTitle() {
        return title;
    }

    public int getRes() {
        return res;
    }


}
