package com.android.custom_list;

/**
 * Created by Kamran ALi on 2/29/2016.
 */
public class Message {
    private String sender ;
    private String title ;
    private int id ;
    private boolean read;

    public Message(String sender, String title, int id) {
        this.sender = sender;
        this.title = title;
        this.id = id;
    }
    public Message(String sender, String title, int id,boolean read) {
        this.sender = sender;
        this.title = title;
        this.id = id;
        this.read = read ;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
