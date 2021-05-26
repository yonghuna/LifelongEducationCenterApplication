package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class PostNumber {
    @SerializedName("count")
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
