package com.example.meiriuser.model.net;

/**
 * Created by admin on 2019/7/3.
 */

public class TaskStatusModel {
    private int task_id;
    private int status;

    public int getTask_id() {
        return task_id;
    }

    public int getStatus() {
        return status;
    }



    public TaskStatusModel(int task_id, int status) {
        this.task_id = task_id;
        this.status = status;
    }


}
