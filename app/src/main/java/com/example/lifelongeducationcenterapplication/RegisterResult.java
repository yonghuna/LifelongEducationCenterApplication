package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class RegisterResult {

    @SerializedName("result")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
