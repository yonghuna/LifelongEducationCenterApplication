package com.example.lifelongeducationcenterapplication.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.lifelongeducationcenterapplication.R;
public class MyPage_CourseDetailsActivity extends AppCompatActivity {
    //마이페이지 수강내역
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("수강내역");
        setContentView(R.layout.activity_my_page__course_details);
    }
}