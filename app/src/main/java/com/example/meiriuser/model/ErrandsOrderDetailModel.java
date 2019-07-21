package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/6/27.
 */

public class ErrandsOrderDetailModel extends BaseBean{


    /**
     * data : {"order_id":132,"order_sn":"201906271607424f20e1","user_id":13,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"zzz","country":10,"province":10,"city":10,"district":10,"address":"hhh","zipcode":"","tel":"18344560792","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":3,"shipping_name":"代购","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"12.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"21.00","add_time":"2019-06-27 16:07","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":null,"task_code":null,"type":3,"rider_status":null,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","pay_info":null,"other_info":{"is_bill":0,"buy_list":["美日果汁"],"buy_type":1,"gratuity":"1.00","buy_price":12,"is_contact":0,"buy_address_lat":23.179864,"buy_address_lng":113.250847,"buy_address_name":"北京市"},"is_comment":0,"rider":{},"timestamp":1561622863,"status_name":"未付款"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order_id : 132
         * order_sn : 201906271607424f20e1
         * user_id : 13
         * order_status : 0
         * shipping_status : 0
         * pay_status : 0
         * consignee : zzz
         * country : 10
         * province : 10
         * city : 10
         * district : 10
         * address : hhh
         * zipcode :
         * tel : 18344560792
         * mobile : 18344560792
         * email :
         * best_time :
         * sign_building :
         * postscript :
         * shipping_id : 3
         * shipping_name : 代购
         * pay_id : 0
         * pay_name :
         * pay_fee : 0.00
         * pack_id : 0
         * pack_name :
         * pack_fee : 0.00
         * goods_amount : 12.00
         * shipping_fee : 0.00
         * insure_fee : 0.00
         * integral : 0
         * integral_money : 0.00
         * order_amount : 21.00
         * add_time : 2019-06-27 16:07
         * confirm_time :
         * pay_time :
         * shipping_time :
         * invoice_no :
         * to_buyer :
         * pay_note :
         * is_separate : 0
         * parent_id : 0
         * discount : 0
         * note :
         * rider_id : null
         * store_id : null
         * task_code : null
         * type : 3
         * rider_status : null
         * lat : 0.0000000
         * lng : 0.0000000
         * shipping_complete_time :
         * pay_info : null
         * other_info : {"is_bill":0,"buy_list":["美日果汁"],"buy_type":1,"gratuity":"1.00","buy_price":12,"is_contact":0,"buy_address_lat":23.179864,"buy_address_lng":113.250847,"buy_address_name":"北京市"}
         * is_comment : 0
         * rider : {}
         * timestamp : 1561622863
         * status_name : 未付款
         */

        private int order_id;
        private String order_sn;
        private int user_id;
        private int order_status;
        private int shipping_status;
        private int pay_status;
        private String consignee;
        private int country;
        private int province;
        private int city;
        private int district;
        private String address;
        private String zipcode;
        private String tel;
        private String mobile;
        private String email;
        private String best_time;
        private String sign_building;
        private String postscript;
        private int shipping_id;
        private String shipping_name;
        private int pay_id;
        private String pay_name;
        private String pay_fee;
        private int pack_id;
        private String pack_name;
        private String pack_fee;
        private String goods_amount;
        private String shipping_fee;
        private String insure_fee;
        private int integral;
        private String integral_money;
        private String order_amount;
        private String add_time;
        private String confirm_time;
        private String pay_time;
        private String shipping_time;
        private String invoice_no;
        private String to_buyer;
        private String pay_note;
        private int is_separate;
        private int parent_id;
        private int discount;
        private String note;
        private Object rider_id;
        private Object store_id;
        private Object task_code;
        private int type;
        private Object rider_status;
        private String lat;
        private String lng;
        private String shipping_complete_time;
        private Object pay_info;
        private OtherInfoBean other_info;
        private int is_comment;
        private RiderBean rider;
        private int timestamp;
        private String status_name;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public int getShipping_status() {
            return shipping_status;
        }

        public void setShipping_status(int shipping_status) {
            this.shipping_status = shipping_status;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getDistrict() {
            return district;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBest_time() {
            return best_time;
        }

        public void setBest_time(String best_time) {
            this.best_time = best_time;
        }

        public String getSign_building() {
            return sign_building;
        }

        public void setSign_building(String sign_building) {
            this.sign_building = sign_building;
        }

        public String getPostscript() {
            return postscript;
        }

        public void setPostscript(String postscript) {
            this.postscript = postscript;
        }

        public int getShipping_id() {
            return shipping_id;
        }

        public void setShipping_id(int shipping_id) {
            this.shipping_id = shipping_id;
        }

        public String getShipping_name() {
            return shipping_name;
        }

        public void setShipping_name(String shipping_name) {
            this.shipping_name = shipping_name;
        }

        public int getPay_id() {
            return pay_id;
        }

        public void setPay_id(int pay_id) {
            this.pay_id = pay_id;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }

        public String getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(String pay_fee) {
            this.pay_fee = pay_fee;
        }

        public int getPack_id() {
            return pack_id;
        }

        public void setPack_id(int pack_id) {
            this.pack_id = pack_id;
        }

        public String getPack_name() {
            return pack_name;
        }

        public void setPack_name(String pack_name) {
            this.pack_name = pack_name;
        }

        public String getPack_fee() {
            return pack_fee;
        }

        public void setPack_fee(String pack_fee) {
            this.pack_fee = pack_fee;
        }

        public String getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(String goods_amount) {
            this.goods_amount = goods_amount;
        }

        public String getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(String shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public String getInsure_fee() {
            return insure_fee;
        }

        public void setInsure_fee(String insure_fee) {
            this.insure_fee = insure_fee;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getIntegral_money() {
            return integral_money;
        }

        public void setIntegral_money(String integral_money) {
            this.integral_money = integral_money;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getConfirm_time() {
            return confirm_time;
        }

        public void setConfirm_time(String confirm_time) {
            this.confirm_time = confirm_time;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getShipping_time() {
            return shipping_time;
        }

        public void setShipping_time(String shipping_time) {
            this.shipping_time = shipping_time;
        }

        public String getInvoice_no() {
            return invoice_no;
        }

        public void setInvoice_no(String invoice_no) {
            this.invoice_no = invoice_no;
        }

        public String getTo_buyer() {
            return to_buyer;
        }

        public void setTo_buyer(String to_buyer) {
            this.to_buyer = to_buyer;
        }

        public String getPay_note() {
            return pay_note;
        }

        public void setPay_note(String pay_note) {
            this.pay_note = pay_note;
        }

        public int getIs_separate() {
            return is_separate;
        }

        public void setIs_separate(int is_separate) {
            this.is_separate = is_separate;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Object getRider_id() {
            return rider_id;
        }

        public void setRider_id(Object rider_id) {
            this.rider_id = rider_id;
        }

        public Object getStore_id() {
            return store_id;
        }

        public void setStore_id(Object store_id) {
            this.store_id = store_id;
        }

        public Object getTask_code() {
            return task_code;
        }

        public void setTask_code(Object task_code) {
            this.task_code = task_code;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getRider_status() {
            return rider_status;
        }

        public void setRider_status(Object rider_status) {
            this.rider_status = rider_status;
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

        public String getShipping_complete_time() {
            return shipping_complete_time;
        }

        public void setShipping_complete_time(String shipping_complete_time) {
            this.shipping_complete_time = shipping_complete_time;
        }

        public Object getPay_info() {
            return pay_info;
        }

        public void setPay_info(Object pay_info) {
            this.pay_info = pay_info;
        }

        public OtherInfoBean getOther_info() {
            return other_info;
        }

        public void setOther_info(OtherInfoBean other_info) {
            this.other_info = other_info;
        }

        public int getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(int is_comment) {
            this.is_comment = is_comment;
        }

        public RiderBean getRider() {
            return rider;
        }

        public void setRider(RiderBean rider) {
            this.rider = rider;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public static class OtherInfoBean {

            /**
             * is_bill : 0
             * buy_list : ["美日果汁"]
             * buy_type : 1
             * gratuity : 1.00
             * buy_price : 12
             * is_contact : 0
             * buy_address_lat : 23.179864
             * buy_address_lng : 113.250847
             * buy_address_name : 北京市
             */

            private int is_bill;
            private int buy_type;
            private String gratuity;
            private int buy_price;
            private int is_contact;
            private double buy_address_lat;
            private double buy_address_lng;
            private String buy_address_name;
            private List<String> buy_list;

            public int getIs_bill() {
                return is_bill;
            }

            public void setIs_bill(int is_bill) {
                this.is_bill = is_bill;
            }

            public int getBuy_type() {
                return buy_type;
            }

            public void setBuy_type(int buy_type) {
                this.buy_type = buy_type;
            }

            public String getGratuity() {
                return gratuity;
            }

            public void setGratuity(String gratuity) {
                this.gratuity = gratuity;
            }

            public int getBuy_price() {
                return buy_price;
            }

            public void setBuy_price(int buy_price) {
                this.buy_price = buy_price;
            }

            public int getIs_contact() {
                return is_contact;
            }

            public void setIs_contact(int is_contact) {
                this.is_contact = is_contact;
            }

            public double getBuy_address_lat() {
                return buy_address_lat;
            }

            public void setBuy_address_lat(double buy_address_lat) {
                this.buy_address_lat = buy_address_lat;
            }

            public double getBuy_address_lng() {
                return buy_address_lng;
            }

            public void setBuy_address_lng(double buy_address_lng) {
                this.buy_address_lng = buy_address_lng;
            }

            public String getBuy_address_name() {
                return buy_address_name;
            }

            public void setBuy_address_name(String buy_address_name) {
                this.buy_address_name = buy_address_name;
            }

            public List<String> getBuy_list() {
                return buy_list;
            }

            public void setBuy_list(List<String> buy_list) {
                this.buy_list = buy_list;
            }
        }

        public static class RiderBean {
        }
    }
}
