package com.example.meiriuser.model.net;

import java.util.List;

/**
 * Created by admin on 2019/7/9.
 */

public class OrderPriceModel {
    private int store_id;
    private float longitude;
    private float latitude;
    private List<OtherInfoBean> list;

    public OrderPriceModel(int store_id, List<OtherInfoBean> list ,float longitude, float latitude) {
        this.store_id = store_id;
        this.list = list;
        this.longitude = longitude;
        this.latitude = latitude;
    }



    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public List<OtherInfoBean> getInfoBean() {
        return list;
    }

    public void setInfoBean(List<OtherInfoBean> list) {
        this.list = list;
    }



    public static class OtherInfoBean{
        public OtherInfoBean(int id, int num) {
            this.id = id;
            this.num = num;
        }

        private int id;
        private int num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }
}
