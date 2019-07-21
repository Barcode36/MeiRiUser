package com.example.meiriuser.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/7/2.
 */

public class TaskDetailsModel extends BaseBean implements Serializable{

    /**
     * data : {"task_id":11,"user_id":37,"title":"ss","desc":"qq","money":"70.00","tags":["ss","aa"],"images":["http://192.168.0.10:8000/uploads/20190702/cea68e9ff9ae9d8ed04086806a3c0371.png","http://192.168.0.10:8000/uploads/20190702/aa0f6c519c6c1b14f9fc5bfa305bada9.png"],"info":[],"address":"北京市","complete_date":"0000-00-00","complete_time_frame":["1","2"],"is_online":0,"lat":"23.1798650","lng":"113.2508500","addtime":"2019-07-02 15:18","status":2,"pay_status":null,"pay_info":{"paymentId":"PAYID-LUNQKSA3N3107396B857703R"},"receipt_id":null,"receipt_name":null,"receipt_tel":null,"receipt_time":null,"receipt_status":0,"user":{"user_id":37,"username":"13412345678","headImg":""}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * task_id : 11
         * user_id : 37
         * title : ss
         * desc : qq
         * money : 70.00
         * tags : ["ss","aa"]
         * images : ["http://192.168.0.10:8000/uploads/20190702/cea68e9ff9ae9d8ed04086806a3c0371.png","http://192.168.0.10:8000/uploads/20190702/aa0f6c519c6c1b14f9fc5bfa305bada9.png"]
         * info : []
         * address : 北京市
         * complete_date : 0000-00-00
         * complete_time_frame : ["1","2"]
         * is_online : 0
         * lat : 23.1798650
         * lng : 113.2508500
         * addtime : 2019-07-02 15:18
         * status : 2
         * pay_status : null
         * pay_info : {"paymentId":"PAYID-LUNQKSA3N3107396B857703R"}
         * receipt_id : null
         * receipt_name : null
         * receipt_tel : null
         * receipt_time : null
         * receipt_status : 0
         * user : {"user_id":37,"username":"13412345678","headImg":""}
         */

        private int task_id;
        private int user_id;
        private String title;
        private String desc;
        private String money;
        private String address;
        private String complete_date;
        private int is_online;
        private String lat;
        private String lng;
        private String addtime;
        private int status;
        private Object pay_status;
        private PayInfoBean pay_info;
        private Object receipt_id;
        private Object receipt_name;
        private Object receipt_tel;
        private Object receipt_time;
        private int receipt_status;
        private UserBean user;
        private List<String> tags;
        private List<String> images;
        @SerializedName("info")
        private InfoBean infoX;
        private List<String> complete_time_frame;

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getComplete_date() {
            return complete_date;
        }

        public void setComplete_date(String complete_date) {
            this.complete_date = complete_date;
        }

        public int getIs_online() {
            return is_online;
        }

        public void setIs_online(int is_online) {
            this.is_online = is_online;
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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getPay_status() {
            return pay_status;
        }

        public void setPay_status(Object pay_status) {
            this.pay_status = pay_status;
        }

        public PayInfoBean getPay_info() {
            return pay_info;
        }

        public void setPay_info(PayInfoBean pay_info) {
            this.pay_info = pay_info;
        }

        public Object getReceipt_id() {
            return receipt_id;
        }

        public void setReceipt_id(Object receipt_id) {
            this.receipt_id = receipt_id;
        }

        public Object getReceipt_name() {
            return receipt_name;
        }

        public void setReceipt_name(Object receipt_name) {
            this.receipt_name = receipt_name;
        }

        public Object getReceipt_tel() {
            return receipt_tel;
        }

        public void setReceipt_tel(Object receipt_tel) {
            this.receipt_tel = receipt_tel;
        }

        public Object getReceipt_time() {
            return receipt_time;
        }

        public void setReceipt_time(Object receipt_time) {
            this.receipt_time = receipt_time;
        }

        public int getReceipt_status() {
            return receipt_status;
        }

        public void setReceipt_status(int receipt_status) {
            this.receipt_status = receipt_status;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public InfoBean getInfoX() {
            return infoX;
        }

        public void setInfoX(InfoBean infoX) {
            this.infoX = infoX;
        }

        public List<String> getComplete_time_frame() {
            return complete_time_frame;
        }

        public void setComplete_time_frame(List<String> complete_time_frame) {
            this.complete_time_frame = complete_time_frame;
        }

        public static class PayInfoBean implements Serializable{
            /**
             * paymentId : PAYID-LUNQKSA3N3107396B857703R
             */

            private String paymentId;

            public String getPaymentId() {
                return paymentId;
            }

            public void setPaymentId(String paymentId) {
                this.paymentId = paymentId;
            }
        }

        public static class InfoBean implements Serializable{

            /**
             * isTasks : true
             * property : 公寓
             * otherInfo : ["地毯"]
             * bedroomNum : 1
             * restroomNum : 1
             */

            private boolean isTasks;
            private String property;
            private int bedroomNum;
            private int restroomNum;
            private List<String> otherInfo;

            public boolean isIsTasks() {
                return isTasks;
            }

            public void setIsTasks(boolean isTasks) {
                this.isTasks = isTasks;
            }

            public String getProperty() {
                return property;
            }

            public void setProperty(String property) {
                this.property = property;
            }

            public int getBedroomNum() {
                return bedroomNum;
            }

            public void setBedroomNum(int bedroomNum) {
                this.bedroomNum = bedroomNum;
            }

            public int getRestroomNum() {
                return restroomNum;
            }

            public void setRestroomNum(int restroomNum) {
                this.restroomNum = restroomNum;
            }

            public List<String> getOtherInfo() {
                return otherInfo;
            }

            public void setOtherInfo(List<String> otherInfo) {
                this.otherInfo = otherInfo;
            }
        }

        public static class UserBean implements Serializable{
            /**
             * user_id : 37
             * username : 13412345678
             * headImg :
             */

            private int user_id;
            private String username;
            private String headImg;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }
        }
    }
}
