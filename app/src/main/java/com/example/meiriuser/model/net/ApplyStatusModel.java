package com.example.meiriuser.model.net;

/**
 * Created by admin on 2019/7/3.
 */

public class ApplyStatusModel {
    private int task_apply_id;

    public int getTask_apply_id() {
        return task_apply_id;
    }

    public int getStatus() {
        return status;
    }

    private int status;

    public ApplyStatusModel(int task_apply_id, int status) {
        this.task_apply_id = task_apply_id;
        this.status = status;
    }




}
