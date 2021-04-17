package com.example.lifelongeducationcenterapplication.Generalcurriculum;
import com.example.lifelongeducationcenterapplication.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//교양 과정
public class LiberalArtsCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("교양과정");
        setContentView(R.layout.activity_liberal_arts_course);
    }
}