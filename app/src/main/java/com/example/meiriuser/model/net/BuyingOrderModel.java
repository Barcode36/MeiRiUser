package com.example.meiriuser.model.net;

import java.util.List;

/**
 * Created by admin on 2019/6/26.
 */

public class BuyingOrderModel {
    private int address_id;
    private int shipping_id;
    private boolean is_contact;

    public BuyingOrderModel(int address_id, int shipping_id, boolean is_contact, boolean is_bill, float gratuity, OtherInfoBean other_info) {
        this.address_id = address_id;
        this.shipping_id = shipping_id;
        this.is_contact = is_contact;
        this.is_bill = is_bill;
        this.gratuity = gratuity;
        this.other_info = other_info;
    }

    private boolean is_bill;
    private float gratuity;
    private OtherInfoBean other_info;

    /**
     * other_info : {"buy_type":1,"buy_list":["999 感冒药"],"buy_address_name":"海珠区新港东路xxx号","buy_address_lat":23.179864,"buy_address_lng":113.250847}
     */



    public OtherInfoBean getOther_info() {
        return other_info;
    }

    public void setOther_info(OtherInfoBean other_info) {
        this.other_info = other_info;
    }

    public static class OtherInfoBean {
        /**
         * buy_type : 1
         * buy_list : ["999 感冒药"]
         * buy_address_name : 海珠区新港东路xxx号
         * buy_address_lat : 23.179864
         * buy_address_lng : 113.250847
         */

        private int buy_type;
        private String buy_address_name;
        private double buy_address_lat;
        private double buy_address_lng;
        private float buy_price;
        private List<String> buy_list;

        public float getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(float buy_price) {
            this.buy_price = buy_price;
        }

        public int getBuy_type() {
            return buy_type;
        }

        public void setBuy_type(int buy_type) {
            this.buy_type = buy_type;
        }

        public String getBuy_address_name() {
            return buy_address_name;
        }

        public void setBuy_address_name(String buy_address_name) {
            this.buy_address_name = buy_address_name;
        }

        public double getBuy_address_lat() {
            return buy_address_lat;
        }

        public void setBuy_address_lat(double buy_address_lat) {
            this.buy_address_lat = buy_address_lat;
        }

        public double getBuy_address_lng() {
            return buy_address_lng;
        }

        public void setBuy_address_lng(double buy_address_lng) {
            this.buy_address_lng = buy_address_lng;
        }

        public List<String> getBuy_list() {
            return buy_list;
        }

        public void setBuy_list(List<String> buy_list) {
            this.buy_list = buy_list;
        }
    }
}
