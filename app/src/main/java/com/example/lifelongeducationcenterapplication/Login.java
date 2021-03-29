package com.example.lifelongeducationcenterapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    Button btlogin,btresgister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btlogin = (Button) findViewById(R.id.loginbutton);
        btresgister = (Button)findViewById(R.id.registerbutton1);
    }

    //로그인인
   private void runLogin(){

    }
    //회원 가입
    public void goResgister(){}

}