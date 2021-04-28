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

    @SerializedName("division")
    public String division;

    @SerializedName("number")
    public int number;

    @SerializedName("year")
    public int year;

    @SerializedName("semester")
    public int semester;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

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

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}
