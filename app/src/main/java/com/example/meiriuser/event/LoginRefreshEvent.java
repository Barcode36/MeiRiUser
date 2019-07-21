package com.example.meiriuser.event;

/**
 * Created by admin on 2019/6/11.
 */

public class LoginRefreshEvent implements IBus.IEvent{
    public int flag;
    public static int MODIFY_PERSON_INFO=1;//修改个人信息


    public LoginRefreshEvent(int flag){
        this.flag=flag;
    }


    public LoginRefreshEvent(){

    }


    public int getFlag() {
        return flag;
    }




    @Override
    public int getTag() {
        return 0;
    }
}
