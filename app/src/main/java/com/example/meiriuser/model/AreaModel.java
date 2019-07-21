package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/6/21.
 */

public class AreaModel {

    /**
     * result : 1
     * code : 0
     * data : [{"id":1,"pid":0,"code":11,"name":"北京市"},{"id":2,"pid":0,"code":12,"name":"天津市"},{"id":3,"pid":0,"code":13,"name":"河北省"},{"id":4,"pid":0,"code":14,"name":"山西省"},{"id":5,"pid":0,"code":15,"name":"内蒙古自治区"},{"id":6,"pid":0,"code":21,"name":"辽宁省"},{"id":7,"pid":0,"code":22,"name":"吉林省"},{"id":8,"pid":0,"code":23,"name":"黑龙江省"},{"id":9,"pid":0,"code":31,"name":"上海市"},{"id":10,"pid":0,"code":32,"name":"江苏省"},{"id":11,"pid":0,"code":33,"name":"浙江省"},{"id":12,"pid":0,"code":34,"name":"安徽省"},{"id":13,"pid":0,"code":35,"name":"福建省"},{"id":14,"pid":0,"code":36,"name":"江西省"},{"id":15,"pid":0,"code":37,"name":"山东省"},{"id":16,"pid":0,"code":41,"name":"河南省"},{"id":17,"pid":0,"code":42,"name":"湖北省"},{"id":18,"pid":0,"code":43,"name":"湖南省"},{"id":19,"pid":0,"code":44,"name":"广东省"},{"id":20,"pid":0,"code":45,"name":"广西壮族自治区"},{"id":21,"pid":0,"code":46,"name":"海南省"},{"id":22,"pid":0,"code":50,"name":"重庆市"},{"id":23,"pid":0,"code":51,"name":"四川省"},{"id":24,"pid":0,"code":52,"name":"贵州省"},{"id":25,"pid":0,"code":53,"name":"云南省"},{"id":26,"pid":0,"code":54,"name":"西藏自治区"},{"id":27,"pid":0,"code":61,"name":"陕西省"},{"id":28,"pid":0,"code":62,"name":"甘肃省"},{"id":29,"pid":0,"code":63,"name":"青海省"},{"id":30,"pid":0,"code":64,"name":"宁夏回族自治区"},{"id":31,"pid":0,"code":65,"name":"新疆维吾尔自治区"}]
     */

    private int result;
    private int code;
    private List<DataBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * pid : 0
         * code : 11
         * name : 北京市
         */

        private int id;
        private int pid;
        private int code;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
