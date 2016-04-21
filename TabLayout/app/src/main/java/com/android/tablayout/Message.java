package com.android.tablayout;

/**
 * Created by Kamran ALi on 4/15/2016.
 */
public class Message {
    private String name;
    private String msg;
    private int id;
    private boolean read;

    public Message(String name, String msg, int id, boolean read) {
        this.name = name;
        this.msg = msg;
        this.id = id;
        this.read = read;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
