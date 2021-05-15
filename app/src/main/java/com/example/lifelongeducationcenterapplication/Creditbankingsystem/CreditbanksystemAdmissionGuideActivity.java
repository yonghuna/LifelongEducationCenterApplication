package com.example.lifelongeducationcenterapplication.Creditbankingsystem;
import com.example.lifelongeducationcenterapplication.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CreditbanksystemAdmissionGuideActivity extends AppCompatActivity {
    //학점은행제 입학안내 액티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("입학안내");
        setContentView(R.layout.activity_creditbanksystem_admission_guide);
    }
    //CreditbanksystemreferenceRoom
    @Override   //뒤로가기
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    @Override   //액셔바 홈버튼
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

     */
}