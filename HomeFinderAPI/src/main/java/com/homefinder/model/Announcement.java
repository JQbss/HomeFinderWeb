package com.homefinder.model;

public class Announcement {
    private String Uid;
    private String description;
    private String title;
    private String link;

    public Announcement() {

    }

    public Announcement(String uid, String description, String title, String link) {
        Uid = uid;
        this.description = description;
        this.title = title;
        this.link = link;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
