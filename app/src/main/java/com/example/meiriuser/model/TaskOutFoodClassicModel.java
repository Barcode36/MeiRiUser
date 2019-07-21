package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/6/17.
 */

public class TaskOutFoodClassicModel {


    /**
     * result : 1
     * code : 0
     * data : [{"cat_id":21,"cat_name":"美甲美睫","keywords":"发多少发送到","cat_desc":"的发送到发送到","parent_id":2,"sort_order":1,"template_file":"滚滚滚","pagesize":"20","show_in_nav":1,"content":"","note":"给对方给对方个梵蒂冈地方给对方 ","addtime":"2019-06-21 16:06","edittime":"2019-06-21 16:06","icon":"http://192.168.0.10:8000/uploads/20190621/4edbf9497508fce7fd141db547206282.jpeg"},{"cat_id":22,"cat_name":"半永久","keywords":"发多少发送到","cat_desc":"的发送到发送到","parent_id":2,"sort_order":1,"template_file":"滚滚滚","pagesize":"20","show_in_nav":1,"content":"","note":"给对方给对方个梵蒂冈地方给对方 ","addtime":"2019-06-21 16:07","edittime":"2019-06-21 16:07","icon":"http://192.168.0.10:8000/uploads/20190621/4edbf9497508fce7fd141db547206282.jpeg"},{"cat_id":23,"cat_name":"脱毛","keywords":"发多少发送到","cat_desc":"的发送到发送到","parent_id":2,"sort_order":1,"template_file":"滚滚滚","pagesize":"20","show_in_nav":1,"content":"","note":"给对方给对方个梵蒂冈地方给对方 ","addtime":"2019-06-21 16:08","edittime":"2019-06-21 16:08","icon":"http://192.168.0.10:8000/uploads/20190621/4edbf9497508fce7fd141db547206282.jpeg"},{"cat_id":24,"cat_name":"皮肤管理","keywords":"发多少发送到","cat_desc":"的发送到发送到","parent_id":2,"sort_order":1,"template_file":"滚滚滚","pagesize":"20","show_in_nav":1,"content":"","note":"给对方给对方个梵蒂冈地方给对方 ","addtime":"2019-06-21 16:08","edittime":"2019-06-21 16:08","icon":"http://192.168.0.10:8000/uploads/20190621/4edbf9497508fce7fd141db547206282.jpeg"},{"cat_id":25,"cat_name":"身材管理","keywords":"发多少发送到","cat_desc":"的发送到发送到","parent_id":2,"sort_order":1,"template_file":"滚滚滚","pagesize":"20","show_in_nav":1,"content":"","note":"给对方给对方个梵蒂冈地方给对方 ","addtime":"2019-06-21 16:08","edittime":"2019-06-21 16:08","icon":"http://192.168.0.10:8000/uploads/20190621/4edbf9497508fce7fd141db547206282.jpeg"},{"cat_id":26,"cat_name":"其他","keywords":"发多少发送到","cat_desc":"的发送到发送到","parent_id":2,"sort_order":1,"template_file":"滚滚滚","pagesize":"20","show_in_nav":1,"content":"","note":"给对方给对方个梵蒂冈地方给对方 ","addtime":"2019-06-21 16:08","edittime":"2019-06-21 16:08","icon":"http://192.168.0.10:8000/uploads/20190621/4edbf9497508fce7fd141db547206282.jpeg"}]
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
         * cat_id : 21
         * cat_name : 美甲美睫
         * keywords : 发多少发送到
         * cat_desc : 的发送到发送到
         * parent_id : 2
         * sort_order : 1
         * template_file : 滚滚滚
         * pagesize : 20
         * show_in_nav : 1
         * content :
         * note : 给对方给对方个梵蒂冈地方给对方
         * addtime : 2019-06-21 16:06
         * edittime : 2019-06-21 16:06
         * icon : http://192.168.0.10:8000/uploads/20190621/4edbf9497508fce7fd141db547206282.jpeg
         */

        private int cat_id;
        private String cat_name;
        private String keywords;
        private String cat_desc;
        private int parent_id;
        private int sort_order;
        private String template_file;
        private String pagesize;
        private int show_in_nav;
        private String content;
        private String note;
        private String addtime;
        private String edittime;
        private String icon;

        public int getCat_id() {
            return cat_id;
        }

        public void setCat_id(int cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
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

        public String getTemplate_file() {
            return template_file;
        }

        public void setTemplate_file(String template_file) {
            this.template_file = template_file;
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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
