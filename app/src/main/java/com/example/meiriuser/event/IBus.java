package com.example.meiriuser.event;

/**
 * Created by admin on 2019/4/22.
 */

public interface IBus {
    void register(Object object);
    void unregister(Object object);
    void post(IEvent event);
    void postSticky(IEvent event);


    interface IEvent {
        int getTag();
    }
}
