package com.example.meiriuser.model.net;

/**
 * Created by admin on 2019/7/19.
 */

public class CashoutModel {
    private float money;
    private String acccount_no;
    private String acccount_name;

    public CashoutModel(float money, String acccount_no, String acccount_name, String bsb) {
        this.money = money;
        this.acccount_no = acccount_no;
        this.acccount_name = acccount_name;
        this.bsb = bsb;
    }

    private String bsb;

}
