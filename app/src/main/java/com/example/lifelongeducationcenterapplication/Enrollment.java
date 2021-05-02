package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class Enrollment {

    @SerializedName("subjectnumber")
    int subjectnumber;

    public int getSubjectnumber() {
        return subjectnumber;
    }

    public void setSubjectnumber(int subjectnumber) {
        this.subjectnumber = subjectnumber;
    }
}
