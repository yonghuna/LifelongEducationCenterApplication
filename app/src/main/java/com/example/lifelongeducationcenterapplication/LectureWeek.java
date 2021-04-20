package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class LectureWeek {

    @SerializedName("week")
    public String week;

    @SerializedName("contents")
    public String contents;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
