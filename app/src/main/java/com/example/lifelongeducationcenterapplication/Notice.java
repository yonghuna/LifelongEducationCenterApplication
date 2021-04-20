package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class Notice {
    @SerializedName("number")
    private int number;

    @SerializedName("title")
    private String title;

    @SerializedName("reportingdate")
    private  String reportingdate;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReportingdate() {
        return reportingdate;
    }

    public void setReportingdate(String reportingdate) {
        this.reportingdate = reportingdate;
    }


}
