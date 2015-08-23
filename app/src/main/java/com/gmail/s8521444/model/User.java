package com.gmail.s8521444.model;

public class User {
    private String id;
    private String name;
    private String imgLink;

    public User(final String id, final String name, final String imgLink) {
        this.id = id;
        this.name = name;
        this.imgLink = imgLink;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(final String imgLink) {
        this.imgLink = imgLink;
    }
}
