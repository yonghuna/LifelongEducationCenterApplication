package com.example.lifelongeducationcenterapplication.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.CommunicationResult;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    Button btlogin, btresgister;//로그인 버튼,회원등록 버튼
    TextView et_id, et_phonenumber, et_password;//아이디,휴대폰 번호,비밀번호

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btlogin = (Button) findViewById(R.id.loginbutton);  //로그인 버튼
        btresgister = (Button) findViewById(R.id.registerbutton1);  // 회원 등록 버튼
        et_id = (TextView) findViewById(R.id.editTextTextPersonName); // 아이디(이름) 입력 텍스트
        et_phonenumber = (TextView) findViewById(R.id.editTextTextPersonName3); //휴대폰 번호 입력 텍스트
        et_password = (TextView) findViewById(R.id.editTextTextPassword); // 비밀번호 입력 텍스트


        //회원 등록 버튼 클릭시 이동
        btresgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignActivity.class);
                startActivity(intent);
                //회원 등록 클릭 이동
            }
        });


        //mysql 로그인 기능 구현
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userName = et_id.getText().toString(); //회원 아이디
                String userPhonenumber = et_phonenumber.getText().toString(); //회원 휴대폰 번호
                String userPass = et_password.getText().toString(); //회원 비밀번호

                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                if (blankCheck()) {
                    RemoteService rs = retrofit.create(RemoteService.class);
                    Call<List<CommunicationResult>> call = rs.userLogin(userName, userPhonenumber, userPass);
                    call.enqueue(new Callback<List<CommunicationResult>>() {
                        @Override
                        public void onResponse(Call<List<CommunicationResult>> call, Response<List<CommunicationResult>> response) {
                            if (response.isSuccessful()) {
                                List<CommunicationResult> userlogins = new ArrayList<>();
                                userlogins = response.body();
                                CommunicationResult communicationResult = userlogins.get(0);
                                if (communicationResult.getId().equals("false")) {
                                    Toast.makeText(Login.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                                    System.out.println("로그인 실패");
                                } else {
                                    Toast.makeText(Login.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                    System.out.println("로그인 성공");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CommunicationResult>> call, Throwable t) {
                            System.out.println("JSON 불러오기 실패" + call + " " + t);
                        }
                    });
                }
            }

        });
    }

        public boolean blankCheck() {
            boolean result = false;
            String userPw = et_password.getText().toString().trim();
            String userName = et_id.getText().toString().trim();
            String userPhone = et_phonenumber.getText().toString().trim();

            if (userPw.isEmpty() && userPhone.isEmpty() && userName.isEmpty()) {
                result = true;
            }
            return result;
        }

}











