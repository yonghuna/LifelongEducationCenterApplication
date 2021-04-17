package com.example.lifelongeducationcenterapplication.Generalcurriculum;
import com.example.lifelongeducationcenterapplication.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lifelongeducationcenterapplication.R;

//일반교육과정 모집안내
public class GeneralEducationCourseRecruitmentGuideActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("모집안내");
        setContentView(R.layout.activity_general_education_course_recruitment_guide);
    }
}