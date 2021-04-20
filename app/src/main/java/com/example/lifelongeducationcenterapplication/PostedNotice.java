package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class PostedNotice {

    @SerializedName("number")
    private int number;

    @SerializedName("title")
    private String title;

    @SerializedName("reportingdate")
    private  String reportingdate;

    @SerializedName("views")
    private String views;
}
