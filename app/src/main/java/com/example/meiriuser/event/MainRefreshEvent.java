package com.example.meiriuser.event;

/**
 * Created by admin on 2019/6/25.
 */

public class MainRefreshEvent implements IBus.IEvent{

    public static int LOGINREFRESH=1;
    public static int LOGOUTREFRESH=2;

    public int flag;

    public int getFlag() {
        return flag;
    }

    public MainRefreshEvent(int flag){
        this.flag=flag;
    }

    @Override
    public int getTag() {
        return 0;
    }
}
