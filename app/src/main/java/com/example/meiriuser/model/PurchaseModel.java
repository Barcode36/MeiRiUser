package com.example.meiriuser.model;

/**
 * Created by admin on 2019/5/31.
 */

public class PurchaseModel extends BaseBean{

    public PurchaseModel(String s1,String s2) {
        this.s1 = s1;
        this.s2=s2;
    }

    private String s1;

    private String s2;

    public String getS1() {
        return s1;
    }


    public String getS2() {
        return s2;
    }
}
