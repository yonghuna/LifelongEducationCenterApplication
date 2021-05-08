package com.example.lifelongeducationcenterapplication;

import android.text.Spanned;

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

    @SerializedName("secret")
    private  String secret;


    @SerializedName("name")
    private  String name;

    @SerializedName("id")
    private  String id;

    @SerializedName("postcount")
    private  String postcount;

    public String getPostcount() {
        return postcount;
    }

    public void setPostcount(String postcount) {
        this.postcount = postcount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

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
