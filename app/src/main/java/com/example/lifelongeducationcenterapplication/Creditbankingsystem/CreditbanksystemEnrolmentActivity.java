package com.example.lifelongeducationcenterapplication.Creditbankingsystem;
import com.example.lifelongeducationcenterapplication.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CreditbanksystemEnrolmentActivity extends AppCompatActivity {
    //학점은행제 수강신청 액티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("수강신청");
        setContentView(R.layout.activity_creditbanksystem_enrolment);
    }
}