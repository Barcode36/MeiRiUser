package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/6/24.
 */

public class BannerListModel extends BaseBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * aid : 1
         * title : hongfseeeeeeeeeeeee
         * pid : 1
         * typeid : 0
         * amount : 0
         * currency :
         * url :
         * introduce :
         * hits : 0
         * username :
         * addtime : 1974-12-12 11:52
         * editor :
         * edittime : 2019-06-10 15:39
         * fromtime : 1974-12-12 11:52
         * totime : 1975-01-16 05:12
         * note :
         * text_name :
         * text_url :
         * text_title :
         * image_src :
         * image_url :
         * image_alt :
         * flash_src :
         * flash_url :
         * flash_loop : 1
         * key_word :
         * listorder : 0
         * status : 1
         */

        private int aid;
        private String title;
        private int pid;
        private int typeid;
        private int amount;
        private String currency;
        private String url;
        private String introduce;
        private int hits;
        private String username;
        private String addtime;
        private String editor;
        private String edittime;
        private String fromtime;
        private String totime;
        private String note;
        private String text_name;
        private String text_url;
        private String text_title;
        private String image_src;
        private String image_url;
        private String image_alt;
        private String flash_src;
        private String flash_url;
        private int flash_loop;
        private String key_word;
        private int listorder;
        private int status;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public String getEdittime() {
            return edittime;
        }

        public void setEdittime(String edittime) {
            this.edittime = edittime;
        }

        public String getFromtime() {
            return fromtime;
        }

        public void setFromtime(String fromtime) {
            this.fromtime = fromtime;
        }

        public String getTotime() {
            return totime;
        }

        public void setTotime(String totime) {
            this.totime = totime;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getText_name() {
            return text_name;
        }

        public void setText_name(String text_name) {
            this.text_name = text_name;
        }

        public String getText_url() {
            return text_url;
        }

        public void setText_url(String text_url) {
            this.text_url = text_url;
        }

        public String getText_title() {
            return text_title;
        }

        public void setText_title(String text_title) {
            this.text_title = text_title;
        }

        public String getImage_src() {
            return image_src;
        }

        public void setImage_src(String image_src) {
            this.image_src = image_src;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getImage_alt() {
            return image_alt;
        }

        public void setImage_alt(String image_alt) {
            this.image_alt = image_alt;
        }

        public String getFlash_src() {
            return flash_src;
        }

        public void setFlash_src(String flash_src) {
            this.flash_src = flash_src;
        }

        public String getFlash_url() {
            return flash_url;
        }

        public void setFlash_url(String flash_url) {
            this.flash_url = flash_url;
        }

        public int getFlash_loop() {
            return flash_loop;
        }

        public void setFlash_loop(int flash_loop) {
            this.flash_loop = flash_loop;
        }

        public String getKey_word() {
            return key_word;
        }

        public void setKey_word(String key_word) {
            this.key_word = key_word;
        }

        public int getListorder() {
            return listorder;
        }

        public void setListorder(int listorder) {
            this.listorder = listorder;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
