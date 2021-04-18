package com.example.lifelongeducationcenterapplication.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.lifelongeducationcenterapplication.R;
public class MyPage_GradesVerificationActivity extends AppCompatActivity {
    //마이페이지 성적확인
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("성적확인");
        setContentView(R.layout.activity_my_page__grades_verification);
    }
}