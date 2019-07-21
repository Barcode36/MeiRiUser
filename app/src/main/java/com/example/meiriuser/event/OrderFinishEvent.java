package com.example.meiriuser.event;

import java.io.Serializable;

/**
 * Created by admin on 2019/6/21.
 */

public class OrderFinishEvent implements IBus.IEvent{
    @Override
    public int getTag() {
        return 0;
    }
}
