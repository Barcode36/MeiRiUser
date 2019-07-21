package com.example.meiriuser.model;

/**
 * Created by admin on 2019/5/23.
 */

public class MyApplicationModel extends BaseBean{

    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }

    public String getS3() {
        return s3;
    }

    public String getS4() {
        return s4;
    }

    public String getS5() {
        return s5;
    }

    public String getS6() {
        return s6;
    }



    public MyApplicationModel(String s1, String s2, String s3, String s4, String s5, String s6) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
    }

}
