package com.example.meiriuser.model;

/**
 * Created by admin on 2019/6/28.
 */

public class PayDepositModel extends BaseBean {

    /**
     * data : {"status":1,"status_name":"已缴纳"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * status : 1
         * status_name : 已缴纳
         */

        private int status;
        private String status_name;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }
    }
}
