package com.example.meiriuser.model;

/**
 * Created by admin on 2019/5/9.
 */

public class MainTypeModel {
    private int url;

    public MainTypeModel(int url, String title) {
        this.url = url;
        this.title = title;
    }

    private String title;

    public int getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
