package com.example.lifelongeducationcenterapplication.Intro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.R;

public class EducationalPurpose extends AppCompatActivity {
    //교육목적 및 목표
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("교육목적 및 목표");
        setContentView(R.layout.introduction_purposes);


    }
}
