package com.android.firebaseobjectapp;

/**
 * Created by Kamran ALi on 4/21/2016.
 */
public class Message {
    private String name;
    private String msg;
    private long time;
    private int id;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                ", time=" + time +
                ", id=" + id +
                '}';
    }

    public Message(String name, String msg, long time, int id) {
        this.name = name;
        this.msg = msg;
        this.time = time;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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
}
