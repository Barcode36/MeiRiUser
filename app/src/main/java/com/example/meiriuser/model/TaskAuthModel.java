package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/5/28.
 */

public class TaskAuthModel extends BaseBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * task_apply_id : 1
         * task_id : 9
         * advantage : null
         * user_id : 37
         * addtime : 2019-07-02 16:30
         * status : 0
         * user : {"user_id":37,"username":"13412345678","headImg":""}
         * comments : 1.0
         */

        private int task_apply_id;
        private int task_id;
        private String advantage;
        private int user_id;
        private String addtime;
        private int status;
        private UserBean user;
        private String comments;

        public int getTask_apply_id() {
            return task_apply_id;
        }

        public void setTask_apply_id(int task_apply_id) {
            this.task_apply_id = task_apply_id;
        }

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public String getAdvantage() {
            return advantage;
        }

        public void setAdvantage(String advantage) {
            this.advantage = advantage;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public static class UserBean {
            /**
             * user_id : 37
             * username : 13412345678
             * headImg :
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
