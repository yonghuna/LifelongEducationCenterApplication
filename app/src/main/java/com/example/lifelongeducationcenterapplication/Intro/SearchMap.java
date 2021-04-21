package com.example.lifelongeducationcenterapplication.Intro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.R;

public class SearchMap extends AppCompatActivity {
    //교육원 소개 찾아 오시는길 지도
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_map);
        getSupportActionBar().setTitle("찾아오시는 길");


    }
}
