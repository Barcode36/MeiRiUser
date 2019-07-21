package com.example.meiriuser.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */

public class TaskListModel extends BaseBean implements Serializable{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * task_id : 5
         * user_id : 35
         * title : dd
         * desc : ww
         * money : 70.00
         * tags : []
         * images : []
         * info : {"isTasks":true,"property":"公寓","otherInfo":["洗衣房"],"bedroomNum":2}
         * address : 北京市
         * complete_date : 0000-00-00
         * complete_time_frame : ["1"]
         * is_online : 0
         * lat : 23.1798650
         * lng : 113.2508500
         * addtime : 2019-06-28 17:02
         * status : 1
         * pay_status : null
         * pay_info : {"paymentId":"PAYID-LUK5PLY9YV59983JC556884P"}
         * receipt_id : null
         * receipt_name : null
         * receipt_tel : null
         * receipt_time : null
         * receipt_status : 0
         * is_comment : 0
         * user : {"user_id":35,"username":"18312345678","headImg":"http://192.168.0.10:8000/uploads/20190624/6869b93ce61aabe38d252d338abc4fbb.png"}
         * status_name : 付款中
         * has_cancel : true
         */

        private int task_id;
        private int user_id;
        private String title;
        private String desc;
        private String money;
       /* @SerializedName("info")
        private InfoBean infoX;*/
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
        private int is_comment;
        private UserBean user;
        private String status_name;
        private boolean has_cancel;
        private List<String> tags;
        private List<String> images;
        private List<String> complete_time_frame;
        private String receipt_status_name;

        public String getReceipt_status_name() {
            return receipt_status_name;
        }

        public void setReceipt_status_name(String receipt_status_name) {
            this.receipt_status_name = receipt_status_name;
        }

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

      /*  public InfoBean getInfoX() {
            return infoX;
        }

        public void setInfoX(InfoBean infoX) {
            this.infoX = infoX;
        }
*/
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

        public int getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(int is_comment) {
            this.is_comment = is_comment;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public boolean isHas_cancel() {
            return has_cancel;
        }

        public void setHas_cancel(boolean has_cancel) {
            this.has_cancel = has_cancel;
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

        public List<String> getComplete_time_frame() {
            return complete_time_frame;
        }

        public void setComplete_time_frame(List<String> complete_time_frame) {
            this.complete_time_frame = complete_time_frame;
        }

        public static class InfoBean {
            /**
             * isTasks : true
             * property : 公寓
             * otherInfo : ["洗衣房"]
             * bedroomNum : 2
             */

            private boolean isTasks;
            private String property;
            private int bedroomNum;
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

            public List<String> getOtherInfo() {
                return otherInfo;
            }

            public void setOtherInfo(List<String> otherInfo) {
                this.otherInfo = otherInfo;
            }
        }

        public static class PayInfoBean {
            /**
             * paymentId : PAYID-LUK5PLY9YV59983JC556884P
             */

            private String paymentId;

            public String getPaymentId() {
                return paymentId;
            }

            public void setPaymentId(String paymentId) {
                this.paymentId = paymentId;
            }
        }

        public static class UserBean {
            /**
             * user_id : 35
             * username : 18312345678
             * headImg : http://192.168.0.10:8000/uploads/20190624/6869b93ce61aabe38d252d338abc4fbb.png
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
