package com.android.firebasetodoapp;

/**
 * Created by Kamran ALi on 6/4/2016.
 */
public class Message {
    private String name;
    private String message;


    public Message() {
    }

    public Message(String name, String message) {
        this.name = name;
        this.message = message;

    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
