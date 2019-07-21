package com.example.meiriuser.model.net;

/**
 * Created by admin on 2019/7/2.
 */

public class TaskApplyModel {
    private String task_id;

    public TaskApplyModel(String task_id, String advantage) {
        this.task_id = task_id;
        this.advantage = advantage;
    }

    private String advantage;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }



}
