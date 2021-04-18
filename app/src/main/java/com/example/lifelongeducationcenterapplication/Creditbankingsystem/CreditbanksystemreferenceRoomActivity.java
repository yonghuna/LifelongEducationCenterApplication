package com.example.lifelongeducationcenterapplication.Creditbankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.lifelongeducationcenterapplication.R;
public class CreditbanksystemreferenceRoomActivity extends AppCompatActivity {
    //학점은행제 자료실
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("자료실");
        setContentView(R.layout.activity_creditbanksystemreference_room);
    }
}