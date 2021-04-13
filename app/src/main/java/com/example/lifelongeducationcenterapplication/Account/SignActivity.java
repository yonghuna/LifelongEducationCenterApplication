package com.example.lifelongeducationcenterapplication.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
        getSupportActionBar().setTitle("회원가입");

        //프래그먼트활성화(기본값:회원가입확인)
        signUpCheckFragment = (SignUpCheckFragment) getSupportFragmentManager().findFragmentById(R.id.signMainFragment);
        agreementFragment = new AgreementFragment();
        agreement2Fragment = new Agreement2Fragment();
        signUpFragment = new SignUpFragment();

    }


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