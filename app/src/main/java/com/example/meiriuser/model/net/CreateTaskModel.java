package com.example.meiriuser.model.net;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/6/28.
 */

public class CreateTaskModel implements Serializable{
    private String title;
    private String desc;
    private float money;
    private List<String> tags;
    private List<String> images;
    private CreateTaskInfo info;
    private String address;
    private float lat;
    private float lng;
    private String complete_date;
    private List<String> complete_time_frame;
    private String pay_no;
    private String task_id;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
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

    public CreateTaskInfo getInfo() {
        return info;
    }

    public void setInfo(CreateTaskInfo info) {
        this.info = info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getComplete_date() {
        return complete_date;
    }

    public void setComplete_date(String complete_date) {
        this.complete_date = complete_date;
    }

    public List<String> getComplete_time_frame() {
        return complete_time_frame;
    }

    public void setComplete_time_frame(List<String> complete_time_frame) {
        this.complete_time_frame = complete_time_frame;
    }

    public String getPay_no() {
        return pay_no;
    }

    public void setPay_no(String pay_no) {
        this.pay_no = pay_no;
    }

    public boolean isIs_online() {
        return is_online;
    }

    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

    private boolean is_online;




    public CreateTaskModel() {
    }




    public static class CreateTaskInfo implements Serializable{
        private String property;  //你的房屋类型
        private int bedroomNum;
        private int restroomNum;

        public int getRestroomNum() {
            return restroomNum;
        }

        public void setRestroomNum(int restroomNum) {
            this.restroomNum = restroomNum;
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

        public boolean isTasks() {
            return isTasks;
        }

        public void setTasks(boolean tasks) {
            isTasks = tasks;
        }

        public List<String> getOtherInfo() {
            return otherInfo;
        }

        public void setOtherInfo(List<String> otherInfo) {
            this.otherInfo = otherInfo;
        }

        private boolean isTasks;
        private List<String> otherInfo;

        public CreateTaskInfo() {
        }





    }


}
