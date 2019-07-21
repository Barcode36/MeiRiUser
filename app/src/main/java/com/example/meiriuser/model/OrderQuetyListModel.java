package com.example.meiriuser.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/6/17.
 */

public class OrderQuetyListModel implements Serializable{

    private List<ListBean> list;

    public List<ListBean> getName() {
        return list;
    }

    public void setName(List<ListBean> name) {
        this.list = name;
    }

    public static class ListBean implements Serializable{


        private int id;
        private int num;
        private String foodName;
        private String foodattr;
        private String foodUrl;
        private String price;
        private String distribuFee;
        private String fullReduction;

        public String getDistribuFee() {
            return distribuFee;
        }

        public void setDistribuFee(String distribuFee) {
            this.distribuFee = distribuFee;
        }

        public void setFullReduction(String fullReduction) {
            this.fullReduction = fullReduction;
        }

        public String getFullReduction() {
            return fullReduction;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getFoodattr() {
            return foodattr;
        }

        public void setFoodattr(String foodattr) {
            this.foodattr = foodattr;
        }

        public String getFoodUrl() {
            return foodUrl;
        }

        public void setFoodUrl(String foodUrl) {
            this.foodUrl = foodUrl;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }



        private List<AttrBean> attr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<AttrBean> getAttr() {
            return attr;
        }

        public void setAttr(List<AttrBean> attr) {
            this.attr = attr;
        }



        public static class AttrBean implements Serializable{
            private int id;
            private int num;

        }

    }
}
