package com.example.meiriuser.model;



import java.io.Serializable;


/**
 * Created by happy on 2017/2/16.
 */

public class BaseBean implements Serializable{


    /**
     * result : 0
     * info : smscode
     * code : 103
     */

    private int result;
    private String info;
    private int code;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
