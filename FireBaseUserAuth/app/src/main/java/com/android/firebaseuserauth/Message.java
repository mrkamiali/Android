package com.android.firebaseuserauth;

/**
 * Created by Kamran ALi on 5/10/2016.
 */
public class Message {
    private String title;
    private String sender;
    private int id;
    private boolean read;

    public Message(String title, String sender, int id, boolean read) {
        this.title = title;
        this.sender = sender;
        this.id = id;
        this.read = read;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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
