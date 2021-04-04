package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String course;
    private String name;
    private String birthday;
    private String phoneNumber;
    private String address;
    private String pw;
    private String gender;



    public UserInfo(String course, String name, String birthday, String phoneNumber, String address, String pw) {
        this.course = course;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.pw = pw;
    }

    public UserInfo(String course, String name, String birthday, String gender) {
        this.course = course;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

