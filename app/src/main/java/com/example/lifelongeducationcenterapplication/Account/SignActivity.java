package com.example.lifelongeducationcenterapplication.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lifelongeducationcenterapplication.R;

public class SignActivity extends AppCompatActivity {
    SignUpCheckFragment signUpCheckFragment;//회원가입확인프래그먼트
    AgreementFragment agreementFragment;//동의(일반)프래그먼트
    Agreement2Fragment agreement2Fragment;//동의(학점은행제)프래그먼트
    SignUpFragment signUpFragment;//회원가입프래그먼트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("회원가입");

        //프래그먼트활성화(기본값:회원가입확인)
        signUpCheckFragment = (SignUpCheckFragment) getSupportFragmentManager().findFragmentById(R.id.signMainFragment);
        agreementFragment = new AgreementFragment();
        agreement2Fragment = new Agreement2Fragment();
        signUpFragment = new SignUpFragment();


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
    /*
    @Override   //액셔바 홈버튼
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

     */

    public void onFragmentChanged(int index){//프래그먼트 바꾸는 메소드
        if(index==0){//
            getSupportFragmentManager().beginTransaction().replace(R.id.signcontainer,signUpCheckFragment).commit();
        }else if(index==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.signcontainer,agreementFragment).commit();
        }else if(index==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.signcontainer,agreement2Fragment).commit();
        }else if(index==3){
            getSupportFragmentManager().beginTransaction().replace(R.id.signcontainer,signUpFragment).commit();
        }
    }
}