package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class Attachment {
    @SerializedName("number")
    private int number;

    @SerializedName("randomname")
    private String randomname;

    @SerializedName("realname")
    private String realname;

    @SerializedName("path")
    private String path;

    @SerializedName("size")
    private String size;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRandomname() {
        return randomname;
    }

    public void setRandomname(String randomname) {
        this.randomname = randomname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
