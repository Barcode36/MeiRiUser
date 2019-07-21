package com.example.meiriuser.model;

/**
 * Created by admin on 2019/7/5.
 */

public class BillingInfoModel {

    /**
     * result : 1
     * code : 0
     * data : {"bill":49,"type":1,"id_value":13,"country":"c","province":"b","city":"a","zipcode":"55","address_1":"111","address_2":"22","addtime":1563435969,"edittime":0}
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
         * bill : 49
         * type : 1
         * id_value : 13
         * country : c
         * province : b
         * city : a
         * zipcode : 55
         * address_1 : 111
         * address_2 : 22
         * addtime : 1563435969
         * edittime : 0
         */

        private int bill;
        private int type;
        private int id_value;
        private String country;
        private String province;
        private String city;
        private String zipcode;
        private String address_1;
        private String address_2;
        private int addtime;
        private int edittime;

        public int getBill() {
            return bill;
        }

        public void setBill(int bill) {
            this.bill = bill;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId_value() {
            return id_value;
        }

        public void setId_value(int id_value) {
            this.id_value = id_value;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public String getAddress_2() {
            return address_2;
        }

        public void setAddress_2(String address_2) {
            this.address_2 = address_2;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public int getEdittime() {
            return edittime;
        }

        public void setEdittime(int edittime) {
            this.edittime = edittime;
        }
    }
}
