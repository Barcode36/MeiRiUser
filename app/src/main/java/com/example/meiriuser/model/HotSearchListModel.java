package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/6/24.
 */

public class HotSearchListModel extends BaseBean{

    /**
     * data : {"list":["123","123","123","123","123","123"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
