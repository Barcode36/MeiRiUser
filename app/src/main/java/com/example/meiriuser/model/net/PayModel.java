package com.example.meiriuser.model.net;

/**
 * Created by admin on 2019/6/21.
 */

public class PayModel {
    private int order_id;

    public PayModel(int order_id, String pay_no) {
        this.order_id = order_id;
        this.pay_no = pay_no;
    }

    private String pay_no;

}
