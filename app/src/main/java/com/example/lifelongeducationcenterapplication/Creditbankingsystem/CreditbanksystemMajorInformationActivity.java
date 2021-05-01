package com.example.lifelongeducationcenterapplication.Creditbankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lifelongeducationcenterapplication.R;
public class CreditbanksystemMajorInformationActivity extends AppCompatActivity {
    //학점은행제 전공안내 외국어로서의 한국어학

    Spinner majormenuSpinner;
    String[] majormenuTitle = {"외국어로서의 한국어학", "체육학", "경제학"};
    Intent it;
    boolean firstCall = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("전공안내");
        setContentView(R.layout.activity_creditbanksystem_major_information);


        //전공안내 메뉴스피너
        majormenuSpinner = (Spinner) findViewById(R.id.majorspinner);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, majormenuTitle);
        majormenuSpinner.setAdapter(adapter);
        majormenuSpinner.setSelection(0);
        majormenuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(firstCall){
                    firstCall = false;
                }
                else{
                    if(i==0){
                        goMajorActivity();
                    }else if(i==1){
                        goMajorActivity2();
                    }else if(i==2){
                        goMajorActivity3();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    //전공안내 한국학 스피너
    void goMajorActivity(){
        it = new Intent(this, CreditbanksystemMajorInformationActivity.class);
        startActivity(it);
    }

    //전공안내 체육학 스피너
    void goMajorActivity2(){
        it = new Intent(this, CreditbanksystemMajorInformation2Activity.class);
        startActivity(it);
    }
    //전공안내 경제학 스피너
    void goMajorActivity3(){
        it = new Intent(this, CreditbanksystemMajorInformation3Activity.class);
        startActivity(it);
    }
}