package com.example.meiriuser.model;

/**
 * Created by admin on 2019/6/19.
 */

public class CodeModel {

    /**
     * result : 1
     * code : 0
     * data : {"sms":8458}
     */

    private int result;
    private int code;
    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sms : 8458
         */

        private int sms;

        public int getSms() {
            return sms;
        }

        public void setSms(int sms) {
            this.sms = sms;
        }
    }
}
