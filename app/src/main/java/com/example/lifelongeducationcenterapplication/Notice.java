package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class Notice {
    @SerializedName("number")
    private int number;

    @SerializedName("title")
    private String title;

    @SerializedName("reportingdate")
    private  String reportingdate;

    @SerializedName("view")
    private  String view;

    @SerializedName("contents")
    private  String contents;

    @SerializedName("important")
    private  String important;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

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
