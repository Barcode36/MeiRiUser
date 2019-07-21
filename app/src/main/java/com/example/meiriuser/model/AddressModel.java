package com.example.meiriuser.model;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/13.
 */

public class AddressModel implements Serializable{

    private double latitude;
    private double longitude;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public AddressModel(){

    }
    public AddressModel(String address,double latitude,double longitude) {
        this.address = address;
        this.latitude=latitude;
        this.longitude=longitude;
    }



    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
