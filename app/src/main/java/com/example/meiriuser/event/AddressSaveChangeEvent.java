package com.example.meiriuser.event;

import com.amap.api.services.help.Tip;
import com.example.meiriuser.model.AddressModel;

/**
 * Created by admin on 2019/6/3.
 */

public class AddressSaveChangeEvent implements IBus.IEvent{

    public static int SAVE_ADDRESS=0;
    public static int DISPLAY_ADDRESS=1;
    private AddressModel tip;
    private int flag;

    public AddressSaveChangeEvent(int flag,AddressModel tip){
        this.flag=flag;
        this.tip=tip;
    }

    public AddressModel getTip() {
        return tip;
    }

    @Override
    public int getTag() {
        return flag;
    }
}
