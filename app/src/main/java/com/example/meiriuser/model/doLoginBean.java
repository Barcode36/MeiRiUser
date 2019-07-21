package com.example.meiriuser.model;

/**
 * Created by admin on 2019/6/10.
 */

public class doLoginBean extends BaseBean{

    /**
     * data : {"token":"6c6e2f678a4915851a5519b0a84a3ee1"}
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
         * token : 6c6e2f678a4915851a5519b0a84a3ee1
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
