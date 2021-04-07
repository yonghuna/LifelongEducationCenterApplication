package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CommunicationResult {

    @SerializedName("result")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
