package com.example.lifelongeducationcenterapplication.Creditbankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.lifelongeducationcenterapplication.R;
public class CreditbanksystemMajorInformationActivity extends AppCompatActivity {
    //학점은행제 전공안내 외국어로서의 한국어학
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("전공안내");
        setContentView(R.layout.activity_creditbanksystem_major_information);
    }
}