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

    @SerializedName("phonenumber")
    private String phoneNumber;

    @SerializedName("address")
    private String address;


    @SerializedName("password")
    private String password;

    @SerializedName("sex")
    private String sex;

    @SerializedName("detailedaddress")
    private String detailedaddress;


    @SerializedName("addressnumber")
    private String addressnumber;

    @SerializedName("education")
    private String education;

    @SerializedName("school")
    private String school;

    @SerializedName("major")
    private String major;

    @SerializedName("admissionmajor")
    private String admissionmajor;


    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAdmissionmajor() {
        return admissionmajor;
    }

    public void setAdmissionmajor(String admissionmajor) {
        this.admissionmajor = admissionmajor;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDetailedaddress() {
        return detailedaddress;
    }

    public void setDetailedaddress(String detailedaddress) {
        this.detailedaddress = detailedaddress;
    }

    public String getAddressnumber() {
        return addressnumber;
    }

    public void setAddressnumber(String addressnumber) {
        this.addressnumber = addressnumber;
    }


}

