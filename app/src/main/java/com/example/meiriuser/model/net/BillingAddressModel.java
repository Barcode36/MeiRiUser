package com.example.meiriuser.model.net;

/**
 * Created by admin on 2019/7/3.
 */

public class BillingAddressModel {
    private String country;
    private String province;
    private String city;
    private String zipcode;
    private String address_1;
    private String address_2;

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getAddress_1() {
        return address_1;
    }

    public String getAddress_2() {
        return address_2;
    }



    public BillingAddressModel(String country, String province, String city, String zipcode, String address_1, String address_2) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.zipcode = zipcode;
        this.address_1 = address_1;
        this.address_2 = address_2;
    }



}
