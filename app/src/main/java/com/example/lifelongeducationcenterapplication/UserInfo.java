package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserInfo implements Serializable {

    @SerializedName("course")
    private String course;

    @SerializedName("name")
    private String name;

    @SerializedName("birth")
    private String birth;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("address")
    private String address;

    @SerializedName("addressNumber")
    private String addressNumber;

    @SerializedName("password")
    private String password;

    @SerializedName("sex")
    private String sex;


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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birthday) {
        this.birth = birthday;
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
        return password;
    }

    public void setPw(String pw) {
        this.password = pw;
    }

    public String getGender() {
        return sex;
    }

    public void setGender(String gender) {
        this.sex = gender;
    }



}

