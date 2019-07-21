package com.example.meiriuser.model.net;

import com.example.meiriuser.model.OrderQuetyListModel;

import java.util.List;

/**
 * Created by admin on 2019/6/19.
 */

public class OrderNetModel {
    private int store_id;
    private int address_id;
    private int shipping_id;
    private float prome;
    private String note;
    private List<OrderQuetyListModel.ListBean> list;

    public OrderNetModel(int store_id, int address_id, int shipping_id,float prome, String note, List<OrderQuetyListModel.ListBean> list) {
        this.store_id = store_id;
        this.address_id = address_id;
        this.shipping_id = shipping_id;
        this.prome=prome;
        this.note=note;
        this.list = list;
    }






}
