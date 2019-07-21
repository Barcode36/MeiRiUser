package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/6/14.
 */

public class ShopInfoModel extends BaseBean{


    /**
     * shop : {"shopID":1,"shopName":"优衣库2","province":null,"city":null,"district":null,"address":"","desc":"","tel":"","x":23.12919,"y":113.2639999,"headImg":"http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg","market_month":0,"market":0,"catgory_id":3,"status":1,"userID":2,"ystartime":"03:33","yendtime":"","content":"","score":1,"delivery_money":"0.00","start_price":"0.00","send_avg":0,"promo":[{"promo_id":1,"store_id":1,"title":"eee","username":"","addtime":1560022393,"fromtime":0,"totime":0,"price":"0.00","cost":"0.00","amount":0,"number":0,"open":0,"editor":"","edittime":0,"note":""},{"promo_id":2,"store_id":1,"title":"","username":"","addtime":0,"fromtime":0,"totime":0,"price":"10.00","cost":"10.00","amount":0,"number":0,"open":0,"editor":"","edittime":0,"note":""},{"promo_id":3,"store_id":1,"title":"","username":"","addtime":0,"fromtime":0,"totime":0,"price":"10.00","cost":"1.00","amount":0,"number":0,"open":0,"editor":"","edittime":0,"note":""}],"env_images":[]}
     */

    private ShopBean shop;

    public ShopBean getShop() {
        return shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
    }

    public static class ShopBean {
        /**
         * shopID : 1
         * shopName : 优衣库2
         * province : null
         * city : null
         * district : null
         * address :
         * desc :
         * tel :
         * x : 23.12919
         * y : 113.2639999
         * headImg : http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg
         * market_month : 0
         * market : 0
         * catgory_id : 3
         * status : 1
         * userID : 2
         * ystartime : 03:33
         * yendtime :
         * content :
         * score : 1
         * delivery_money : 0.00
         * start_price : 0.00
         * send_avg : 0
         * promo : [{"promo_id":1,"store_id":1,"title":"eee","username":"","addtime":1560022393,"fromtime":0,"totime":0,"price":"0.00","cost":"0.00","amount":0,"number":0,"open":0,"editor":"","edittime":0,"note":""},{"promo_id":2,"store_id":1,"title":"","username":"","addtime":0,"fromtime":0,"totime":0,"price":"10.00","cost":"10.00","amount":0,"number":0,"open":0,"editor":"","edittime":0,"note":""},{"promo_id":3,"store_id":1,"title":"","username":"","addtime":0,"fromtime":0,"totime":0,"price":"10.00","cost":"1.00","amount":0,"number":0,"open":0,"editor":"","edittime":0,"note":""}]
         * env_images : []
         */

        private int shopID;
        private String shopName;
        private Object province;
        private Object city;
        private Object district;
        private String address;
        private String desc;
        private String tel;
        private double x;
        private double y;
        private String headImg;
        private int market_month;
        private int market;
        private int catgory_id;
        private int status;
        private int userID;
        private String ystartime;
        private String yendtime;
        private String content;
        private int score;
        private String delivery_money;
        private String start_price;
        private int send_avg;
        private List<PromoBean> promo;
        private List<?> env_images;

        public int getShopID() {
            return shopID;
        }

        public void setShopID(int shopID) {
            this.shopID = shopID;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getDistrict() {
            return district;
        }

        public void setDistrict(Object district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getMarket_month() {
            return market_month;
        }

        public void setMarket_month(int market_month) {
            this.market_month = market_month;
        }

        public int getMarket() {
            return market;
        }

        public void setMarket(int market) {
            this.market = market;
        }

        public int getCatgory_id() {
            return catgory_id;
        }

        public void setCatgory_id(int catgory_id) {
            this.catgory_id = catgory_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getYstartime() {
            return ystartime;
        }

        public void setYstartime(String ystartime) {
            this.ystartime = ystartime;
        }

        public String getYendtime() {
            return yendtime;
        }

        public void setYendtime(String yendtime) {
            this.yendtime = yendtime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getDelivery_money() {
            return delivery_money;
        }

        public void setDelivery_money(String delivery_money) {
            this.delivery_money = delivery_money;
        }

        public String getStart_price() {
            return start_price;
        }

        public void setStart_price(String start_price) {
            this.start_price = start_price;
        }

        public int getSend_avg() {
            return send_avg;
        }

        public void setSend_avg(int send_avg) {
            this.send_avg = send_avg;
        }

        public List<PromoBean> getPromo() {
            return promo;
        }

        public void setPromo(List<PromoBean> promo) {
            this.promo = promo;
        }

        public List<?> getEnv_images() {
            return env_images;
        }

        public void setEnv_images(List<?> env_images) {
            this.env_images = env_images;
        }

        public static class PromoBean {
            /**
             * promo_id : 1
             * store_id : 1
             * title : eee
             * username :
             * addtime : 1560022393
             * fromtime : 0
             * totime : 0
             * price : 0.00
             * cost : 0.00
             * amount : 0
             * number : 0
             * open : 0
             * editor :
             * edittime : 0
             * note :
             */

            private int promo_id;
            private int store_id;
            private String title;
            private String username;
            private int addtime;
            private int fromtime;
            private int totime;
            private String price;
            private String cost;
            private int amount;
            private int number;
            private int open;
            private String editor;
            private int edittime;
            private String note;

            public int getPromo_id() {
                return promo_id;
            }

            public void setPromo_id(int promo_id) {
                this.promo_id = promo_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }

            public int getFromtime() {
                return fromtime;
            }

            public void setFromtime(int fromtime) {
                this.fromtime = fromtime;
            }

            public int getTotime() {
                return totime;
            }

            public void setTotime(int totime) {
                this.totime = totime;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getOpen() {
                return open;
            }

            public void setOpen(int open) {
                this.open = open;
            }

            public String getEditor() {
                return editor;
            }

            public void setEditor(String editor) {
                this.editor = editor;
            }

            public int getEdittime() {
                return edittime;
            }

            public void setEdittime(int edittime) {
                this.edittime = edittime;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }
        }
    }
}
