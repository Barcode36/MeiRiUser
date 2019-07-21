package com.example.meiriuser.model;

/**
 * Created by admin on 2019/5/22.
 */

public class PayMethodModel {
    private int url;
    private String title;
    private String content;

    public PayMethodModel(int url, String title, String content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }



    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
