package com.example.meiriuser.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/20.
 */

public class FoodDetailsModel extends BaseBean implements Serializable{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : -1
         * name : 热销
         * template_file :
         * goods : [{"goods_id":1,"cat_id":2,"user_id":0,"goods_sn":"","goods_name":"商品1","click_count":0,"goods_number":0,"goods_weight":"0.000","market_price":"10.00","shop_price":"0.00","promote_price":"10.00","promote_start_date":1560575499,"promote_end_date":1580575499,"keywords":"","goods_brief":"","goods_desc":"","goods_thumb":"","goods_img":"","is_real":1,"extension_code":"","is_on_sale":1,"is_alone_sale":1,"integral":0,"addtime":"","sort_order":0,"is_delete":1,"is_best":0,"is_new":0,"is_hot":1,"is_promote":0,"edittime":"","goods_type":0,"seller_note":"","give_integral":0,"note":"","province":null,"city":null,"district":null,"store_id":1,"lat":"0.0000000","lng":"0.0000000","store_name":"","address":"","goods_images":["http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg","http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg","http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg","http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg"]}]
         * gcat_id : 1
         * user_id : 0
         * gcat_name : 分类一
         * keywords :
         * cat_desc :
         * parent_id : 0
         * sort_order : 0
         * pagesize :
         * show_in_nav : 0
         * content :
         * note :
         * addtime : 1970-01-01 08:02
         * edittime : 1970-01-01 11:05
         * store_id : 1
         */

        private int id;
        private String name;
        private String template_file;
        private int gcat_id;
        private int user_id;
        private String gcat_name;
        private String keywords;
        private String cat_desc;
        private int parent_id;
        private int sort_order;
        private String pagesize;
        private int show_in_nav;
        private String content;
        private String note;
        private String addtime;
        private String edittime;
        private int store_id;
        private List<GoodsBean> goods;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTemplate_file() {
            return template_file;
        }

        public void setTemplate_file(String template_file) {
            this.template_file = template_file;
        }

        public int getGcat_id() {
            return gcat_id;
        }

        public void setGcat_id(int gcat_id) {
            this.gcat_id = gcat_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getGcat_name() {
            return gcat_name;
        }

        public void setGcat_name(String gcat_name) {
            this.gcat_name = gcat_name;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getCat_desc() {
            return cat_desc;
        }

        public void setCat_desc(String cat_desc) {
            this.cat_desc = cat_desc;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public int getSort_order() {
            return sort_order;
        }

        public void setSort_order(int sort_order) {
            this.sort_order = sort_order;
        }

        public String getPagesize() {
            return pagesize;
        }

        public void setPagesize(String pagesize) {
            this.pagesize = pagesize;
        }

        public int getShow_in_nav() {
            return show_in_nav;
        }

        public void setShow_in_nav(int show_in_nav) {
            this.show_in_nav = show_in_nav;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getEdittime() {
            return edittime;
        }

        public void setEdittime(String edittime) {
            this.edittime = edittime;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean implements Serializable{
            /**
             * goods_id : 1
             * cat_id : 2
             * user_id : 0
             * goods_sn :
             * goods_name : 商品1
             * click_count : 0
             * goods_number : 0
             * goods_weight : 0.000
             * market_price : 10.00
             * shop_price : 0.00
             * promote_price : 10.00
             * promote_start_date : 1560575499
             * promote_end_date : 1580575499
             * keywords :
             * goods_brief :
             * goods_desc :
             * goods_thumb :
             * goods_img :
             * is_real : 1
             * extension_code :
             * is_on_sale : 1
             * is_alone_sale : 1
             * integral : 0
             * addtime :
             * sort_order : 0
             * is_delete : 1
             * is_best : 0
             * is_new : 0
             * is_hot : 1
             * is_promote : 0
             * edittime :
             * goods_type : 0
             * seller_note :
             * give_integral : 0
             * note :
             * province : null
             * city : null
             * district : null
             * store_id : 1
             * lat : 0.0000000
             * lng : 0.0000000
             * store_name :
             * address :
             * goods_images : ["http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg","http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg","http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg","http://192.168.0.10:8000/uploads/20190611/795b8cc2860e68269f044d2f317ae6e5.jpg"]
             */

            private int goods_id;
            private int cat_id;
            private int user_id;
            private String goods_sn;
            private String goods_name;
            private int click_count;
            private int goods_number;
            private String goods_weight;
            private String market_price;
            private String shop_price;
            private String promote_price;
            private int promote_start_date;
            private int promote_end_date;
            private String keywords;
            private String goods_brief;
            private List<String> goods_desc;
            private String goods_thumb;
            private String goods_img;
            private int is_real;
            private String extension_code;
            private int is_on_sale;
            private int is_alone_sale;
            private int integral;
            private String addtime;
            private int sort_order;
            private int is_delete;
            private int is_best;
            private int is_new;
            private int is_hot;
            private int is_promote;
            private String edittime;
            private int goods_type;
            private String seller_note;
            private int give_integral;
            private String note;
            private Object province;
            private Object city;
            private Object district;
            private int store_id;
            private String lat;
            private String lng;
            private String store_name;
            private String address;
            private List<String> goods_images;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public int getClick_count() {
                return click_count;
            }

            public void setClick_count(int click_count) {
                this.click_count = click_count;
            }

            public int getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(int goods_number) {
                this.goods_number = goods_number;
            }

            public String getGoods_weight() {
                return goods_weight;
            }

            public void setGoods_weight(String goods_weight) {
                this.goods_weight = goods_weight;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getPromote_price() {
                return promote_price;
            }

            public void setPromote_price(String promote_price) {
                this.promote_price = promote_price;
            }

            public int getPromote_start_date() {
                return promote_start_date;
            }

            public void setPromote_start_date(int promote_start_date) {
                this.promote_start_date = promote_start_date;
            }

            public int getPromote_end_date() {
                return promote_end_date;
            }

            public void setPromote_end_date(int promote_end_date) {
                this.promote_end_date = promote_end_date;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getGoods_brief() {
                return goods_brief;
            }

            public void setGoods_brief(String goods_brief) {
                this.goods_brief = goods_brief;
            }

            public List<String> getGoods_desc() {
                return goods_desc;
            }

            public void setGoods_desc(List<String> goods_desc) {
                this.goods_desc = goods_desc;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public int getIs_real() {
                return is_real;
            }

            public void setIs_real(int is_real) {
                this.is_real = is_real;
            }

            public String getExtension_code() {
                return extension_code;
            }

            public void setExtension_code(String extension_code) {
                this.extension_code = extension_code;
            }

            public int getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(int is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public int getIs_alone_sale() {
                return is_alone_sale;
            }

            public void setIs_alone_sale(int is_alone_sale) {
                this.is_alone_sale = is_alone_sale;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public int getSort_order() {
                return sort_order;
            }

            public void setSort_order(int sort_order) {
                this.sort_order = sort_order;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public int getIs_best() {
                return is_best;
            }

            public void setIs_best(int is_best) {
                this.is_best = is_best;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }

            public int getIs_promote() {
                return is_promote;
            }

            public void setIs_promote(int is_promote) {
                this.is_promote = is_promote;
            }

            public String getEdittime() {
                return edittime;
            }

            public void setEdittime(String edittime) {
                this.edittime = edittime;
            }

            public int getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(int goods_type) {
                this.goods_type = goods_type;
            }

            public String getSeller_note() {
                return seller_note;
            }

            public void setSeller_note(String seller_note) {
                this.seller_note = seller_note;
            }

            public int getGive_integral() {
                return give_integral;
            }

            public void setGive_integral(int give_integral) {
                this.give_integral = give_integral;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
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

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public List<String> getGoods_images() {
                return goods_images;
            }

            public void setGoods_images(List<String> goods_images) {
                this.goods_images = goods_images;
            }
        }
    }
}
