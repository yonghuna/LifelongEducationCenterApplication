package com.example.lifelongeducationcenterapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class SignUpCheckActivity extends AppCompatActivity {

    RadioButton rbGeneralCourse, rbCreditBank;//라디오버튼 일반과정, 학점은행제과정
    EditText edtNameCheck;//성명
    Spinner spinBirthCheck1,spinBirthCheck2,spinBirthCheck3; //생년월일
    Spinner spinSexCheck; //성별
    Button btnSignUpCheck; //등록확인


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_check);

        rbGeneralCourse = findViewById(R.id.rbGeneralCourse);
        rbCreditBank = findViewById(R.id.rbCreditBank);//라디오버튼 일반과정, 학점은행제과정
        edtNameCheck = findViewById(R.id.edtNameCheck);//성명
        spinBirthCheck1 = findViewById(R.id.spinBirthCheck1);
        spinBirthCheck2 = findViewById(R.id.spinBirthCheck2);
        spinBirthCheck3 = findViewById(R.id.spinBirthCheck3);//생년월일
        spinSexCheck = findViewById(R.id.spinSexCheck);//성별
        btnSignUpCheck = findViewById(R.id.btnSignUpCheck);//등록확인


        btnSignUpCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                1. 수강생확인입력한것 가져오기
                2. 데이테베이스로부터 해당하는 회원정보를 가져오기
                3. 1번과 2번을 통해 가져온 자료를 바탕으로 회원가입유무확인
                 */
            }
        });

    }
}