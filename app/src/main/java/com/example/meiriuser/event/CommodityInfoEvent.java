package com.example.meiriuser.event;

/**
 * Created by admin on 2019/6/18.
 */

public class CommodityInfoEvent implements IBus.IEvent{
    private String commodityName;

    public CommodityInfoEvent(String commodityName, String commodityAddress) {
        this.commodityName = commodityName;
        this.commodityAddress = commodityAddress;
    }

    private String commodityAddress;

    public String getCommodityAddress() {
        return commodityAddress;
    }

    public void setCommodityAddress(String commodityAddress) {
        this.commodityAddress = commodityAddress;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }



    @Override
    public int getTag() {
        return 0;
    }
}
