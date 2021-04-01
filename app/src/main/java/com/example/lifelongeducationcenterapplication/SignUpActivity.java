package com.example.lifelongeducationcenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class  SignUpActivity extends AppCompatActivity {

    /* 예시
    TextView inputText;
    Button register;//등록버튼

     */

    TextView txtSignUpDivision, txtSignUpNameAndSex, txtSignUpBirthDate;//등록구분, 이름(성별)
    Spinner spinSignUpPhoneNumber1;//전화번호1
    EditText edtSignUpPhoneNumber2, edtSignUpPhoneNumber3;//전화번호2, 전화번호3
    EditText edtSignUpPostCode;//우편번호
    Button btnSignUpAddressSearch; //주소검색
    EditText edtSignUpAddress1, edtSignUpAddress2; //주소1, 주소2
    EditText edtSignUpPassword, edtSignUpPasswordCheck; //비밀번호입력, 비밀번호확인
    Button btnSignUpRegister, btnSignUpRegisterCancel; //등록, 취소

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtSignUpDivision = findViewById(R.id.txtSignUpDivision);
        txtSignUpNameAndSex = findViewById(R.id.txtSignUpNameAndSex);
        txtSignUpBirthDate = findViewById(R.id.txtSignUpBirthDate);//등록구분, 이름(성별)
        spinSignUpPhoneNumber1 = findViewById(R.id.spinSignUpPhoneNumber1);
        edtSignUpPhoneNumber2 = findViewById(R.id.edtSignUpPhoneNumber2);
        edtSignUpPhoneNumber3 = findViewById(R.id.edtSignUpPhoneNumber3);//전화번호
        edtSignUpPostCode = findViewById(R.id.edtSignUpPostCode);//우편번호
        btnSignUpAddressSearch = findViewById(R.id.btnSignUpAddressSearch);//주소검색
        edtSignUpAddress1 = findViewById(R.id.edtSignUpAddress1);
        edtSignUpAddress2 = findViewById(R.id.edtSignUpAddress2);//주소1, 주소2
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtSignUpPasswordCheck = findViewById(R.id.edtSignUpPasswordCheck);//비밀번호입력, 비밀번호확인
        btnSignUpRegister = findViewById(R.id.btnSignUpRegister);
        btnSignUpRegisterCancel = findViewById(R.id.btnSignUpRegisterCancel);//회원등록,취소

        /*예시
        inputText = findViewById(R.id.inputtext);
        register = findViewById(R.id.register);//등록버튼


        inputText.setText("");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이벤트가 발생시 어떤걸 해야한다. 만약 어떤 값을 가져와야한다면 어디서 어떤걸 가져와야하는지

                //1. 데이터베이스에 회원정보를 등록해야한다.
                //2. 메인화면으로 넘어가야한다.
            }
        });
         */

        btnSignUpAddressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. 주소API를 이용하여 주소를 확인함.
                //2. 이때 스레드풀(asynctask 등)을 이용해야할지도모름.
            }
        });

        btnSignUpRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. 빠트린 부분이 없는지 체크(다이얼로그로 회원등록하세겠습니까 라는 창을 띄워서 한번더 사용자에게 확인시킴)
                //2. 비밀번호확인(올바르게 입력이 되었는지 확인, 비밀번호와 비밀번호확인과 일치하는지 확인)
                //3. 신규회원가입정보가 데이터베이스에 저장
                //4. 회원가입완료했다고 다이얼로그창을 띄운후 메인화면으로 넘어감.
                //
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

}