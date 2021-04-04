package com.example.lifelongeducationcenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class Login extends AppCompatActivity {

    Button btlogin, btresgister;//로그인 버튼,회원등록 버튼
    EditText et_id, et_phonenumber, et_password;//아이디,휴대폰 번호,비밀번호
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btlogin = (Button) findViewById(R.id.loginbutton);  //로그인 버튼
        btresgister = (Button) findViewById(R.id.registerbutton1);  // 회원 등록 버튼
        et_id = (EditText) findViewById(R.id.editTextTextPersonName); // 아이디(이름) 입력 텍스트
        et_phonenumber = (EditText) findViewById(R.id.editTextTextPersonName3); //휴대폰 번호 입력 텍스트
        et_password = (EditText) findViewById(R.id.editTextTextPassword); // 비밀번호 입력 텍스트


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

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
                String userName = et_id.getText().toString(); //회원 아이디
                String userPhonenumber = et_phonenumber.getText().toString(); //회원 휴대폰 번호
                String userPass = et_password.getText().toString(); //회원 비밀번호
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.

                RemoteService rs = retrofit.create(RemoteService.class);
                Call<Userlogin> call = rs.userLogin(userName, userPhonenumber, userPass);
                call.enqueue(new Callback<Userlogin>() {
                    @Override
                    public void onResponse(Call<Userlogin> call, Response<Userlogin> response) {
                        Userlogin userlogin = response.body();
                        Toast.makeText(Login.this, userlogin.getName(), Toast.LENGTH_SHORT).show();
                        System.out.println(userlogin.getName());
                    }

                    @Override
                    public void onFailure(Call<Userlogin> call, Throwable t) {
                        System.out.println("JSON 불러오기 실패" + call + " " + t);
                    }
                });
            }
        });
    }
    //로그인 버튼 기능
    private void runLogin() {

    }

    //회원 가입 등록 이동
    public void goRegister() {
        startActivity(new Intent(this, SignUpCheckActivity.class));
    }

}



