package com.android.todofire;

public class Message {
    private String name;
    private String msg;
    private boolean checkBox;



    public Message(String name, String msg, boolean checkBox) {
        this.name = name;
        this.msg = msg;
        this.checkBox = checkBox;
    }

    public Message() {
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
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

