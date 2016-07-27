package com.android.todofirepractice;

/**
 * Created by Kamran ALi on 5/8/2016.
 */
public class Message {
    private String name;
    private String msg;
    private boolean checkin;

    public Message(String name, String msg, boolean checkin) {
        this.name = name;
        this.msg = msg;
        this.checkin = checkin;
    }

    public Message() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public boolean isCheckin() {
        return checkin;
    }

    public void setCheckin(boolean checkin) {
        this.checkin = checkin;
    }
}
