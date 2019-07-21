package com.example.meiriuser.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/13.
 */

public class TakeOutFoodModel implements Serializable{


    /**
     * result : 1
     * data : [{"store_id":1,"catgory_id":3,"user_id":2,"clerk_ids":"","store_name":"优衣库2","store_img":"http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg","contact":"","tel":"","address_id":0,"address":"","lat":"23.1291900","lng":"113.2639999","introduce":"","store_desc":"","score":1,"delivery_money":4.5,"start_price":"0.00","send_avg":0,"market_month":0,"status":1,"note":"","addtime":"2019-06-09 03:33","edittime":"2019-06-09 03:36","bankType":"","banknum":"","isdelete":1,"user_money":"0.00","sfrozen_money":"0.00","country":null,"province":null,"city":null,"district":null,"market":0,"ystartime":"03:33","yendtime":"","attestation":null,"attestation_status":null,"env_image":null,"shipping":null,"distance":"0 m"},{"store_id":4,"catgory_id":3,"user_id":0,"clerk_ids":"","store_name":"麦当劳(沙步店)","store_img":"","contact":"","tel":"","address_id":0,"address":"","lat":"23.0860450","lng":"113.2508470","introduce":"","store_desc":"","score":1,"delivery_money":7.5,"start_price":"0.00","send_avg":0,"market_month":0,"status":1,"note":"","addtime":"","edittime":"","bankType":"","banknum":"","isdelete":1,"user_money":"0.00","sfrozen_money":"0.00","country":null,"province":null,"city":null,"district":null,"market":0,"ystartime":"","yendtime":"","attestation":null,"attestation_status":0,"env_image":null,"shipping":null,"distance":"4.98 km"},{"store_id":5,"catgory_id":3,"user_id":0,"clerk_ids":"","store_name":"麦当劳(宝岗分店)","store_img":"","contact":"","tel":"","address_id":0,"address":"","lat":"23.1016610","lng":"113.2625890","introduce":"","store_desc":"","score":1,"delivery_money":6,"start_price":"0.00","send_avg":0,"market_month":0,"status":1,"note":"","addtime":"","edittime":"","bankType":"","banknum":"","isdelete":1,"user_money":"0.00","sfrozen_money":"0.00","country":null,"province":null,"city":null,"district":null,"market":0,"ystartime":"","yendtime":"","attestation":null,"attestation_status":0,"env_image":null,"shipping":null,"distance":"3.07 km"},{"store_id":16,"catgory_id":3,"user_id":0,"clerk_ids":"","store_name":"麦当劳(捷泰分店)","store_img":"","contact":"","tel":"","address_id":0,"address":"","lat":"23.1257850","lng":"113.2561450","introduce":"","store_desc":"","score":1,"delivery_money":4.5,"start_price":"0.00","send_avg":0,"market_month":0,"status":1,"note":"","addtime":"","edittime":"","bankType":"","banknum":"","isdelete":1,"user_money":"0.00","sfrozen_money":"0.00","country":null,"province":null,"city":null,"district":null,"market":0,"ystartime":"","yendtime":"","attestation":null,"attestation_status":0,"env_image":null,"shipping":null,"distance":"890 m"},{"store_id":17,"catgory_id":3,"user_id":28,"clerk_ids":"","store_name":"麦当劳(中唱大厦分店)","store_img":"","contact":"","tel":"","address_id":0,"address":"","lat":"23.1565830","lng":"113.2632100","introduce":"","store_desc":"","score":1,"delivery_money":6,"start_price":"0.00","send_avg":0,"market_month":0,"status":1,"note":"","addtime":"","edittime":"","bankType":"","banknum":"","isdelete":1,"user_money":"0.00","sfrozen_money":"0.00","country":null,"province":null,"city":null,"district":null,"market":0,"ystartime":"","yendtime":"","attestation":null,"attestation_status":0,"env_image":null,"shipping":null,"distance":"3.05 km"}]
     * total : 0
     * current_page : 1
     */

    private int result;
    private int total;
    private String current_page;
    private List<DataBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * store_id : 1
         * catgory_id : 3
         * user_id : 2
         * clerk_ids :
         * store_name : 优衣库2
         * store_img : http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg
         * contact :
         * tel :
         * address_id : 0
         * address :
         * lat : 23.1291900
         * lng : 113.2639999
         * introduce :
         * store_desc :
         * score : 1
         * delivery_money : 4.5
         * start_price : 0.00
         * send_avg : 0
         * market_month : 0
         * status : 1
         * note :
         * addtime : 2019-06-09 03:33
         * edittime : 2019-06-09 03:36
         * bankType :
         * banknum :
         * isdelete : 1
         * user_money : 0.00
         * sfrozen_money : 0.00
         * country : null
         * province : null
         * city : null
         * district : null
         * market : 0
         * ystartime : 03:33
         * yendtime :
         * attestation : null
         * attestation_status : null
         * env_image : null
         * shipping : null
         * distance : 0 m
         */

        private int store_id;
        private int catgory_id;
        private int user_id;
        private String clerk_ids;
        private String store_name;
        private String store_img;
        private String contact;
        private String tel;
        private int address_id;
        private String address;
        private String lat;
        private String lng;
        private String introduce;
        private String store_desc;
        private int score;
        private double delivery_money;
        private String start_price;
        private int send_avg;
        private int market_month;
        private int status;
        private String note;
        private String addtime;
        private String edittime;
        private String bankType;
        private String banknum;
        private int isdelete;
        private String user_money;
        private String sfrozen_money;
        private Object country;
        private Object province;
        private Object city;
        private Object district;
        private int market;
        private String ystartime;
        private String yendtime;
        private Object attestation;
        private Object attestation_status;
        private Object env_image;
        private Object shipping;
        private String distance;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public int getCatgory_id() {
            return catgory_id;
        }

        public void setCatgory_id(int catgory_id) {
            this.catgory_id = catgory_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getClerk_ids() {
            return clerk_ids;
        }

        public void setClerk_ids(String clerk_ids) {
            this.clerk_ids = clerk_ids;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_img() {
            return store_img;
        }

        public void setStore_img(String store_img) {
            this.store_img = store_img;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getStore_desc() {
            return store_desc;
        }

        public void setStore_desc(String store_desc) {
            this.store_desc = store_desc;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public double getDelivery_money() {
            return delivery_money;
        }

        public void setDelivery_money(double delivery_money) {
            this.delivery_money = delivery_money;
        }

        public String getStart_price() {
            return start_price;
        }

        public void setStart_price(String start_price) {
            this.start_price = start_price;
        }

        public int getSend_avg() {
            return send_avg;
        }

        public void setSend_avg(int send_avg) {
            this.send_avg = send_avg;
        }

        public int getMarket_month() {
            return market_month;
        }

        public void setMarket_month(int market_month) {
            this.market_month = market_month;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
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

        public String getBankType() {
            return bankType;
        }

        public void setBankType(String bankType) {
            this.bankType = bankType;
        }

        public String getBanknum() {
            return banknum;
        }

        public void setBanknum(String banknum) {
            this.banknum = banknum;
        }

        public int getIsdelete() {
            return isdelete;
        }

        public void setIsdelete(int isdelete) {
            this.isdelete = isdelete;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getSfrozen_money() {
            return sfrozen_money;
        }

        public void setSfrozen_money(String sfrozen_money) {
            this.sfrozen_money = sfrozen_money;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getDistrict() {
            return district;
        }

        public void setDistrict(Object district) {
            this.district = district;
        }

        public int getMarket() {
            return market;
        }

        public void setMarket(int market) {
            this.market = market;
        }

        public String getYstartime() {
            return ystartime;
        }

        public void setYstartime(String ystartime) {
            this.ystartime = ystartime;
        }

        public String getYendtime() {
            return yendtime;
        }

        public void setYendtime(String yendtime) {
            this.yendtime = yendtime;
        }

        public Object getAttestation() {
            return attestation;
        }

        public void setAttestation(Object attestation) {
            this.attestation = attestation;
        }

        public Object getAttestation_status() {
            return attestation_status;
        }

        public void setAttestation_status(Object attestation_status) {
            this.attestation_status = attestation_status;
        }

        public Object getEnv_image() {
            return env_image;
        }

        public void setEnv_image(Object env_image) {
            this.env_image = env_image;
        }

        public Object getShipping() {
            return shipping;
        }

        public void setShipping(Object shipping) {
            this.shipping = shipping;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }
}
