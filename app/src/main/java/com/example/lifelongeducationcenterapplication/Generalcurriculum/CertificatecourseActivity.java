package com.example.lifelongeducationcenterapplication.Generalcurriculum;
import com.example.lifelongeducationcenterapplication.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CertificatecourseActivity extends AppCompatActivity {
    //자격증과정
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("자격증과정");
        setContentView(R.layout.activity_certificatecourse);
    }
}