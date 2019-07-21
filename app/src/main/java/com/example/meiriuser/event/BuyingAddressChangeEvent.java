package com.example.meiriuser.event;

import com.example.meiriuser.model.AddressModel;

/**
 * Created by admin on 2019/6/26.
 */

public class BuyingAddressChangeEvent implements IBus.IEvent{
    private AddressModel tip;


    public BuyingAddressChangeEvent(AddressModel tip){
        this.tip=tip;

    }

    public AddressModel getTip() {
        return tip;
    }

    @Override
    public int getTag() {
        return 0;
    }
}
