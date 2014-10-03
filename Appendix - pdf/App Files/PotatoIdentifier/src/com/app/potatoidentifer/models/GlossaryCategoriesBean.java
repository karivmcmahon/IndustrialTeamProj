package com.app.potatoidentifer.models;

public class GlossaryCategoriesBean {
    private int id;
    private String title;
    private byte[] imageID;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImageID() {
        return imageID;
    }

    public void setImageID(byte[] imageID) {
        this.imageID = imageID;
    }
}
