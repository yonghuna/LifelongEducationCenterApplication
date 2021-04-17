package com.example.lifelongeducationcenterapplication.Generalcurriculum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.lifelongeducationcenterapplication.R;
public class GeneralSecurityGuardCourseActivity extends AppCompatActivity {
//일반 경비원 과정
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("일반경비원과정");
        setContentView(R.layout.activity_general_security_guard_course);
    }
}