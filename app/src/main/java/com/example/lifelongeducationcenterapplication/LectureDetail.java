package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class LectureDetail {

    @SerializedName("name")
    public String name;

    @SerializedName("startdate")
    public String startDate;

    @SerializedName("enddate")
    public  String endDate;

    @SerializedName("professor")
    public String professor;

    @SerializedName("studyfee")
    public  String studyFee;

    @SerializedName("starttime")
    public String startTime;


    @SerializedName("endtime")
    public  String endTime;

    @SerializedName("dayoftheweek")
    public String dayOfTheWeek;

    @SerializedName("briefhistory")
    public String briefhistory;

    @SerializedName("introduce")
    public  String introduce;

    @SerializedName("status")
    public String status;

    @SerializedName("datedetail")
    public String datedetail;

    @SerializedName("yaer")
    public int yaer;

    @SerializedName("semester")
    public int semester;

    public int getYaer() {
        return yaer;
    }

    public void setYaer(int yaer) {
        this.yaer = yaer;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getDatedetail() {
        return datedetail;
    }

    public void setDatedetail(String datedetail) {
        this.datedetail = datedetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
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

    public String getBriefhistory() {
        return briefhistory;
    }

    public void setBriefhistory(String briefhistory) {
        this.briefhistory = briefhistory;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
