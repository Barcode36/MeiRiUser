package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/5/30.
 */

public class NotifiCenterModel extends BaseBean{

    /**
     * data : {"total":1,"per_page":10,"current_page":1,"last_page":1,"data":[{"id":1,"title":"","content":"erewr","addtime":"2019-06-11"}]}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total : 1
         * per_page : 10
         * current_page : 1
         * last_page : 1
         * data : [{"id":1,"title":"","content":"erewr","addtime":"2019-06-11"}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBeanX.DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBeanX.DataBean> getData() {
            return data;
        }

        public void setData(List<DataBeanX.DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1
             * title :
             * content : erewr
             * addtime : 2019-06-11
             */

            private int id;
            private String title;
            private String content;
            private String addtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }
    }
}
