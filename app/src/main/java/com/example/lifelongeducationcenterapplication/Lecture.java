package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class Lecture {

    @SerializedName("name")
    public String name;

    @SerializedName("startdate")
    public String startDate;

    @SerializedName("enddate")
    public  String endDate;

    @SerializedName("studyfee")
    public  String studyFee;

    @SerializedName("starttime")
    public String startTime;

    @SerializedName("endtime")
    public  String endTime;

    @SerializedName("dayoftheweek")
    public String dayOfTheWeek;

    @SerializedName("professor")
    public String professor;

    @SerializedName("status")
    public String status;

    @SerializedName("number")
    public int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStudyFee() {
        return studyFee;
    }

    public void setStudyFee(String studyFee) {
        this.studyFee = studyFee;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
