package com.example.lifelongeducationcenterapplication.Community;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lifelongeducationcenterapplication.R;

public class Community_NoticeActivity extends AppCompatActivity {
    //커뮤니티 공지사항
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("공지사항");
        setContentView(R.layout.activity_community__notice);
    }
}