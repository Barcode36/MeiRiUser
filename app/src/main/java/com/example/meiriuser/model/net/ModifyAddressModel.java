package com.example.meiriuser.model.net;

/**
 * Created by admin on 2019/6/20.
 */

public class ModifyAddressModel {
    private int id;
    private String consignee;
    private int sex;
    private int country;
    private int province;
    private int city;
    private int district;
    private String address;
    private String tel;
    private float lat;
    private float lng;
    private String note;

    public ModifyAddressModel(int id, String consignee, int sex, String address, String tel, float lat, float lng, String note) {
        this.id = id;
        this.consignee = consignee;
        this.sex = sex;
        this.address = address;
        this.tel = tel;
        this.lat = lat;
        this.lng = lng;
        this.note = note;
    }

    public ModifyAddressModel(String consignee, int sex, String address, String tel, float lat, float lng, String note) {
        this.consignee = consignee;
        this.sex = sex;
        this.address = address;
        this.tel = tel;
        this.lat = lat;
        this.lng = lng;
        this.note = note;
    }




}
