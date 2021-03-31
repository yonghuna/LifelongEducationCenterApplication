package com.example.lifelongeducationcenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    Button btlogin,btresgister;//로그인 버튼,회원등록 버튼
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btlogin = (Button) findViewById(R.id.loginbutton);  //로그인 버튼
        btresgister = (Button)findViewById(R.id.registerbutton1);  // 회원 등록 버튼
    }

    //로그인 버튼 기능
   private void runLogin(){

    }
    //회원 가입 등록 이동
    public void goRegister(){
        startActivity(new Intent(this,SignUpCheckActivity.class));
    }

}