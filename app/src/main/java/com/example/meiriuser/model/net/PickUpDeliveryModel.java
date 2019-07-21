package com.example.meiriuser.model.net;

import java.util.List;

/**
 * Created by admin on 2019/6/26.
 */

public class PickUpDeliveryModel {
    private int address_id;
    private int shipping_id;
    private boolean is_contact;
    private boolean is_bill;
    private float gratuity;

    public PickUpDeliveryModel(int address_id, int shipping_id, boolean is_contact, boolean is_bill, float gratuity, OtherInfoBean other_info) {
        this.address_id = address_id;
        this.shipping_id = shipping_id;
        this.is_contact = is_contact;
        this.is_bill = is_bill;
        this.gratuity = gratuity;
        this.other_info = other_info;
    }

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

        private String contact_name;
        private List<String> buy_list;

        public List<String> getBuy_list() {
            return buy_list;
        }

        public void setBuy_list(List<String> buy_list) {
            this.buy_list = buy_list;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public void setContact_tel(String contact_tel) {
            this.contact_tel = contact_tel;
        }

        private String contact_tel;

        public String getContact_name() {
            return contact_name;
        }

        public String getContact_tel() {
            return contact_tel;
        }

    }
}
