package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class LectureResult {
    @SerializedName("number")
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
