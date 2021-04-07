package com.example.lifelongeducationcenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class SignUpActivity extends AppCompatActivity {



    TextView txtSignUpDivision, txtSignUpNameAndSex, txtSignUpBirthDate;//등록구분, 이름(성별)
    Spinner spinSignUpPhoneNumber1;//전화번호1
    EditText edtSignUpPhoneNumber2, edtSignUpPhoneNumber3;//전화번호2, 전화번호3
    TextView edtSignUpPostCode;//우편번호 EDITVIEW 인데 텍스트로 선언
    Button btnSignUpAddressSearch; //주소검색
    TextView edtSignUpAddress1, edtSignUpAddress2; //주소1, 주소2
    EditText edtSignUpPassword, edtSignUpPasswordCheck; //비밀번호입력, 비밀번호확인
    Button btnSignUpRegister, btnSignUpRegisterCancel; //등록, 취소
    Retrofit retrofit;
    String name, gender, birthDate, course;
    String post, addr, fullAddr;
    int idx;

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtSignUpDivision = (TextView) findViewById(R.id.txtSignUpDivision);
        txtSignUpNameAndSex = (TextView) findViewById(R.id.txtSignUpNameAndSex);
        txtSignUpBirthDate = (TextView) findViewById(R.id.txtSignUpBirthDate);//등록구분, 이름(성별)
        spinSignUpPhoneNumber1 = (Spinner) findViewById(R.id.spinSignUpPhoneNumber1);
        edtSignUpPhoneNumber2 = (EditText) findViewById(R.id.edtSignUpPhoneNumber2);
        edtSignUpPhoneNumber3 = (EditText) findViewById(R.id.edtSignUpPhoneNumber3);//전화번호
        edtSignUpPostCode = (TextView) findViewById(R.id.edtSignUpPostCode);//우편번호
        btnSignUpAddressSearch = (Button) findViewById(R.id.btnSignUpAddressSearch);//주소검색
        edtSignUpAddress1 = (TextView) findViewById(R.id.edtSignUpAddress1);
        edtSignUpAddress2 = (EditText) findViewById(R.id.edtSignUpAddress2);//주소1, 주소2
        edtSignUpPassword = (EditText) findViewById(R.id.edtSignUpPassword);
        edtSignUpPasswordCheck = (EditText) findViewById(R.id.edtSignUpPasswordCheck);//비밀번호입력, 비밀번호확인
        btnSignUpRegister = (Button) findViewById(R.id.btnSignUpRegister);
        btnSignUpRegisterCancel = (Button) findViewById(R.id.btnSignUpRegisterCancel);//회원등록,취소

        String[] phone = {
                "010"
        };

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, phone);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSignUpPhoneNumber1.setAdapter(adapter);



        // 초기 세팅

        Intent intent = getIntent();
        UserInfo userInfo = (UserInfo) intent.getSerializableExtra("userInfo");

        name = userInfo.getName();
        gender = userInfo.getGender();
        birthDate = userInfo.getBirthday();
        course = userInfo.getCourse();
        String nameGender = name + " (" + gender + ")";

        txtSignUpDivision.setText(course); // 어느 과정인지
        txtSignUpNameAndSex.setText(nameGender); // 성별 이름
        txtSignUpBirthDate.setText(birthDate);// 회원 생일






        //회원가입 유저 정보 디비 전송
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        btnSignUpAddressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. 주소API를 이용하여 주소를 확인함.
                //2. DaumWebViewActivity로부터 intent로 addr을 받아와서 표시해주는 부분

                Intent i = new Intent(SignUpActivity.this, WebViewActivity.class);
                startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);

            }
        });

        btnSignUpRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. 빠트린 부분이 없는지 체크(다이얼로그로 회원등록하세겠습니까 라는 창을 띄워서 한번더 사용자에게 확인시킴)
                //2. 비밀번호확인(올바르게 입력이 되었는지 확인, 비밀번호와 비밀번호확인과 일치하는지 확인)
                //3. 신규회원가입정보가 데이터베이스에 저장
                //4. 회원가입완료했다고 다이얼로그창을 띄운후 메인화면으로 넘어감.

                String userPhoneNumber = spinSignUpPhoneNumber1.getSelectedItem().toString().trim()
                        + edtSignUpPhoneNumber2.getText().toString().trim()
                        + edtSignUpPhoneNumber3.getText().toString().trim();// 휴대번호
                String userAddr = fullAddr + " " + edtSignUpAddress2.getText().toString().trim(); // 주소
                String userPass = edtSignUpPasswordCheck.getText().toString().trim(); //회원 비밀번호

                System.out.println(userAddr);
                // 데이터베이스로 전송
                RemoteService rs = retrofit.create(RemoteService.class);
                Call<List<CommunicationResult>> call = rs.userRegister(userPhoneNumber, userPass, course, userAddr, birthDate, name);
                call.enqueue(new Callback<List<CommunicationResult>>() {
                    public void onResponse(Call<List<CommunicationResult>> call, Response<List<CommunicationResult>> response) {
                        List<CommunicationResult> userRegister = new ArrayList<>();
                        userRegister = response.body();
                        CommunicationResult communicationResult = userRegister.get(0);
                        if (communicationResult.getResult().equals("ok")) {
                            Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                            System.out.println("회원가입 성공");
                        } else {
                            Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT).show();
                            System.out.println("회원가입 실패");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CommunicationResult>> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "회원가입이 성공하였습니다!", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                });

            }
        });

        btnSignUpRegisterCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                1. 회원가입을 취소하시겠습니까?라는 창을 띄워서 사용자에게 확인시킴
                2. 회원가입창을 종료시킨다.
                 */

            }
        });




    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        super.onActivityResult(requestCode, resultCode, intent);

        switch(requestCode){

            case SEARCH_ADDRESS_ACTIVITY:

                if(resultCode == RESULT_OK){

                    fullAddr = intent.getExtras().getString("data");
                    idx = fullAddr.indexOf(",");
                    post = fullAddr.substring(0, idx);
                    addr = fullAddr.substring(idx+1);
                    if (fullAddr != null)
                        edtSignUpPostCode.setText(post);
                        edtSignUpAddress1.setText(addr);

                }
                break;

        }

    }



}
