package com.example.meiriuser.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/31.
 */

public class AddressListModel implements Serializable{


    /**
     * result : 1
     * code : 0
     * data : [{"address_id":13,"address_name":"","user_id":13,"consignee":"dhb小","email":"","country":10,"province":10,"city":10,"district":10,"address":"花城广场","zipcode":"","tel":"","mobile":"18344560791","sign_building":"公司","best_time":"","note":"","isDefault":1,"addtime":"2019-06-11 09:44","edittime":"2019-06-11 14:41","sex":1,"lat":"0.0000000","lng":"0.0000000"},{"address_id":12,"address_name":"","user_id":13,"consignee":"小小","email":"","country":10,"province":10,"city":10,"district":10,"address":"广东省广州市天河区花城广场","zipcode":"","tel":"","mobile":"18344560792","sign_building":"公司","best_time":"","note":"","isDefault":0,"addtime":"2019-06-10 17:44","edittime":"","sex":0,"lat":"0.0000000","lng":"0.0000000"},{"address_id":11,"address_name":"","user_id":13,"consignee":"小小","email":"","country":10,"province":10,"city":10,"district":10,"address":"广东省广州市天河区花城广场","zipcode":"","tel":"","mobile":"18344560792","sign_building":"公司","best_time":"","note":"","isDefault":0,"addtime":"2019-06-10 17:42","edittime":"","sex":0,"lat":"0.0000000","lng":"0.0000000"},{"address_id":10,"address_name":"","user_id":13,"consignee":"","email":"","country":10,"province":10,"city":10,"district":10,"address":"广东省广州市天河区花城广场","zipcode":"","tel":"","mobile":"18344560792","sign_building":"公司","best_time":"","note":"","isDefault":0,"addtime":"2019-06-10 17:38","edittime":"","sex":0,"lat":"0.0000000","lng":"0.0000000"}]
     */

    private int result;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * address_id : 13
         * address_name :
         * user_id : 13
         * consignee : dhb小
         * email :
         * country : 10
         * province : 10
         * city : 10
         * district : 10
         * address : 花城广场
         * zipcode :
         * tel :
         * mobile : 18344560791
         * sign_building : 公司
         * best_time :
         * note :
         * isDefault : 1
         * addtime : 2019-06-11 09:44
         * edittime : 2019-06-11 14:41
         * sex : 1
         * lat : 0.0000000
         * lng : 0.0000000
         */

        private int address_id;
        private String address_name;
        private int user_id;
        private String consignee;
        private String email;
        private int country;
        private int province;
        private int city;
        private int district;
        private String address;
        private String zipcode;
        private String tel;
        private String mobile;
        private String sign_building;
        private String best_time;
        private String note;
        private int isDefault;
        private String addtime;
        private String edittime;
        private int sex;
        private String lat;
        private String lng;

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getAddress_name() {
            return address_name;
        }

        public void setAddress_name(String address_name) {
            this.address_name = address_name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getDistrict() {
            return district;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSign_building() {
            return sign_building;
        }

        public void setSign_building(String sign_building) {
            this.sign_building = sign_building;
        }

        public String getBest_time() {
            return best_time;
        }

        public void setBest_time(String best_time) {
            this.best_time = best_time;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getEdittime() {
            return edittime;
        }

        public void setEdittime(String edittime) {
            this.edittime = edittime;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }
    }
}
