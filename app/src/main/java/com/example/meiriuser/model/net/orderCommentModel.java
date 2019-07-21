package com.example.meiriuser.model.net;

/**
 * Created by admin on 2019/6/20.
 */

public class orderCommentModel {
    private String id;
    private String content;
    private int rank;
    private int rider_rank;

    public orderCommentModel(String id, String content, int rank, int rider_rank) {
        this.id = id;
        this.content = content;
        this.rank = rank;
        this.rider_rank = rider_rank;
    }


}
