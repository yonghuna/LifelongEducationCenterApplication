package com.example.lifelongeducationcenterapplication.Creditbankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.lifelongeducationcenterapplication.R;
public class CreditbanksystemActivity extends AppCompatActivity {
    //학점은행제란? 액티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("학점은행제란");
        setContentView(R.layout.activity_creditbanksystem);
    }
}