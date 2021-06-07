package com.example.lifelongeducationcenterapplication.Creditbankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lifelongeducationcenterapplication.R;

public class CreditbanksystemMajorInfoActivity extends AppCompatActivity {

    Spinner majorspinnerinfo;

    //Fragment frcreditbank;
    CreditbanksystemMajorInfo1Fragment creditbanksystemMajorInfo1Fragment;
    CreditbanksystemMajorInfo2Fragment creditbanksystemMajorInfo2Fragment;
    CreditbanksystemMajorInfo3Fragment creditbanksystemMajorInfo3Fragment;

    String[] majormenuTitle = {"외국어로서의 한국어학", "체육학",  "경제학"};

    boolean firstCall = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditbanksystem_major_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("전공안내");

        majorspinnerinfo = findViewById(R.id.majorspinnerinfo);
        //frcreditbank = getSupportFragmentManager().findFragmentById(R.id.frcreditbank);

        //creditbanksystemMajorInfo1Fragment = (CreditbanksystemMajorInfo1Fragment) getSupportFragmentManager().findFragmentById(R.id.frcreditbank);

        creditbanksystemMajorInfo1Fragment = new CreditbanksystemMajorInfo1Fragment();
        creditbanksystemMajorInfo2Fragment = new CreditbanksystemMajorInfo2Fragment();
        creditbanksystemMajorInfo3Fragment = new CreditbanksystemMajorInfo3Fragment();

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, majormenuTitle);
        majorspinnerinfo.setAdapter(adapter);
        majorspinnerinfo.setSelection(0);

        //onFragmentChanged(0);

        majorspinnerinfo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    onFragmentChanged(0);
                }else if(position==1){
                    onFragmentChanged(1);
                }else if(position==2){
                    onFragmentChanged(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    public void onFragmentChanged(int index){//프래그먼트 바꾸는 메소드
        if(index==0){//
            getSupportFragmentManager().beginTransaction().replace(R.id.frcreditbank,creditbanksystemMajorInfo1Fragment).commit();
        }else if(index==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frcreditbank,creditbanksystemMajorInfo2Fragment).commit();
        }else if(index==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.frcreditbank,creditbanksystemMajorInfo3Fragment).commit();
        }
    }

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
}