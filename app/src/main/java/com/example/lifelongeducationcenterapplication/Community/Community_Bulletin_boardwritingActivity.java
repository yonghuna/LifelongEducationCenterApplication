package com.example.lifelongeducationcenterapplication.Community;

import androidx.appcompat.app.AppCompatActivity;
import com.example.lifelongeducationcenterapplication.R;
import android.os.Bundle;

public class Community_Bulletin_boardwritingActivity extends AppCompatActivity {
    //커뮤니티 게시판 글쓰기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("글쓰기");
        setContentView(R.layout.activity_community__bulletin_boardwriting);
    }
}