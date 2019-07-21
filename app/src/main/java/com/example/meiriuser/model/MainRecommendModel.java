package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/5/9.
 */

public class MainRecommendModel extends BaseBean{


    /**
     * data : {"list":[{"id":0,"name":"hongfs0","images":"https://images.hongfs.cn/avatar.jpg"},{"id":1,"name":"hongfs1","images":"https://images.hongfs.cn/avatar.jpg"},{"id":2,"name":"hongfs2","images":"https://images.hongfs.cn/avatar.jpg"},{"id":3,"name":"hongfs3","images":"https://images.hongfs.cn/avatar.jpg"},{"id":4,"name":"hongfs4","images":"https://images.hongfs.cn/avatar.jpg"},{"id":5,"name":"hongfs5","images":"https://images.hongfs.cn/avatar.jpg"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 0
             * name : hongfs0
             * images : https://images.hongfs.cn/avatar.jpg
             */

            private int id;
            private String name;
            private String images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }
        }
    }
}
