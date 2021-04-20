package com.example.lifelongeducationcenterapplication.Generalcurriculum;
import com.example.lifelongeducationcenterapplication.Lecture;
import com.example.lifelongeducationcenterapplication.LectureDetail;
import com.example.lifelongeducationcenterapplication.LectureWeek;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Retrofit;

//교양 과정
public class LiberalArtsCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("교양과정");
        setContentView(R.layout.activity_liberal_arts_course);
    }
}