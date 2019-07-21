package com.example.meiriuser.model;

import com.example.meiriuser.base.BaseActivity;

/**
 * Created by admin on 2019/5/30.
 */

public class MyBalanceModel extends BaseBean{

    public MyBalanceModel(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    private String s1;

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }

    public String getS3() {
        return s3;
    }

    private String s2;
    private String s3;
}
