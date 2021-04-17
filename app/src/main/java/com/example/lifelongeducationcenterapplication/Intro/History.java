package com.example.lifelongeducationcenterapplication.Intro;

import android.app.AppComponentFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.R;

public class History extends AppCompatActivity {
    //교육과정 발전과정 및 연혁
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("발전과정 및 연혁");
        setContentView(R.layout.introduction_history);

    }
}
