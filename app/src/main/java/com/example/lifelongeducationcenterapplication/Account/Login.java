package com.example.lifelongeducationcenterapplication.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.R;

public class Login extends AppCompatActivity {

    Button btlogin,btresgister;//로그인 버튼,회원등록 버튼
    TextView et_id,et_phonenumber,et_password;//아이디,휴대폰 번호,비밀번호
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btlogin = (Button) findViewById(R.id.loginbutton);  //로그인 버튼
        btresgister = (Button)findViewById(R.id.registerbutton1);  // 회원 등록 버튼
        et_id = (TextView)findViewById(R.id.editTextTextPersonName); // 아이디(이름) 입력 텍스트
        et_phonenumber = (TextView)findViewById(R.id.editTextTextPersonName3); //휴대폰 번호 입력 텍스트
        et_password = (TextView)findViewById(R.id.editTextTextPassword); // 비밀번호 입력 텍스트


        //회원 등록 버튼 클릭시 이동
        btresgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUpCheckActivity.class);
                startActivity(intent);
                //회원 등록 클릭 이동
            }
        });

        //mysql 로그인 기능 구현
       btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = et_id.getText().toString(); //회원 아이디
                String userPhonenumber = et_phonenumber.getText().toString(); //회원 휴대폰 번호
                String userPass = et_password.getText().toString(); //회원 비밀번호
                /* 테스트 중 로그인 기능
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("hongchul" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");

                                Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPass", userPass);
                                startActivity(intent);
                            } else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };*/
            }
        });


    }

    //로그인 버튼 기능
   private void runLogin(){

    }
    //회원 가입 등록 이동
    public void goRegister(){
        startActivity(new Intent(this,SignUpCheckActivity.class));
    }

}