package com.example.lifelongeducationcenterapplication.Community;

import androidx.appcompat.app.AppCompatActivity;
import com.example.lifelongeducationcenterapplication.R;
import android.os.Bundle;

public class Community_QuestionAndAnswerActivity extends AppCompatActivity {
    //커뮤니티 1:1질의응답
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("1:1질의응답");
        setContentView(R.layout.activity_community__question_and_answer);
    }
}