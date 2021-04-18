package com.example.lifelongeducationcenterapplication.Intro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.R;

public class Greetings extends AppCompatActivity {
    //교육원 소개 인사말씀
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("인사말씀");
        setContentView(R.layout.introduction_greetings);


    }
}
