package com.example.lifelongeducationcenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Memberregistrationagreement extends AppCompatActivity {

    Button btagree,btcancle;  //동의 버튼, 취소 버튼
    Button radiobutton1,radiobutton2,radiobutton3,radiobutton4; //약관 동의,약관 취소,약관 동의,약관 취소
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btagree = (Button)findViewById(R.id.agree_button); //동의 버튼
        btcancle = (Button)findViewById(R.id.cancel_button1); // 취소 버튼
        radiobutton1 = (Button)findViewById(R.id.radioButton1); //약관 동의
        radiobutton2 = (Button)findViewById(R.id.radioButton2); //약관 취소
        radiobutton3 = (Button)findViewById(R.id.radioButton3); //약관 동의
        radiobutton4 = (Button)findViewById(R.id.radioButton4); //약관 취소
        setContentView(R.layout.activity_member_registration_agreement);

        //약관 동의 버튼 기능
        btagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //취소 버튼 ->로그인 화면으로 넘어감
        btcancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Memberregistrationagreement.this, Login.class);
                startActivity(intent);
            }
        });
    }
    //동의 버튼 ->회원등록
    public void goSignUpActivity(){startActivity(new Intent(this,SignUpActivity.class));}

    //취소 버튼 ->로그인 화면
    public void goLogin(){startActivity(new Intent(this,Login.class));}
}