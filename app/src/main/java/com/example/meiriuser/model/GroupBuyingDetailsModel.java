package com.example.meiriuser.model;

/**
 * Created by admin on 2019/5/22.
 */

public class GroupBuyingDetailsModel extends BaseBean{
    public GroupBuyingDetailsModel(String name, String num) {
        this.name = name;
        this.num = num;
    }

    private String name;
    private String num;

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }
}
