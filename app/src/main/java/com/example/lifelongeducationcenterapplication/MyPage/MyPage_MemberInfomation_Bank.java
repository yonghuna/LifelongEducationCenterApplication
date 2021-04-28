package com.example.lifelongeducationcenterapplication.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;
import com.example.lifelongeducationcenterapplication.UserInfo;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyPage_MemberInfomation_Bank extends AppCompatActivity {

    TextView memberName, memberBirth, memberIdNumber, myPageBankText, memberPostcode, memberAdr1; // 이름 생년 주민번호, 학습은행제
    Spinner memberPhone1, memberEducation; // 휴대폰, 학력
    EditText memberPhone2, memberPhone3, memberAdr2, memberSchool  // 휴대폰, 주소, 전공 , 입학전공 , 패스워드
            , memberMajor, memberAdimissionMajor, memberPw1, memberPw2;

    Button btnModify;

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;


    LinearLayout myPageBank1, myPageBank2, myPageBank3;
    Retrofit retrofit1;
    Retrofit retrofit2;


    String[] education = {
            "선택하세요", "대학원 졸업이상", "대학원재학", "대학교졸업", "대학교재학", "고등학교졸업"
            , "고등학교재학", "중학교졸업", "중학교재학", "초등학교졸업",
    };

    String[] phone = {
            "010", "011", "016", "017", "018", "019"
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("수강내역");
        setContentView(R.layout.activity_my_page__course_details);

    }

    @Override
    protected void onResume() {
        RemoteService rs = retrofit1.create(RemoteService.class);
        Call<UserInfo> call = rs.myPage(StaticId.id);//call객체
        call.enqueue(new Callback<UserInfo>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {
                    UserInfo userInfo = response.body();
                    memberName.setText(userInfo.getName());

                    // 휴대폰
                    memberPhone2.setText(userInfo.getPhoneNumber().substring(3, 7));
                    memberPhone3.setText(userInfo.getPhoneNumber().substring(7, 11));

                    // 생일
                    memberBirth.setText(userInfo.getBirth().substring(0, 4) + "년" + userInfo.getBirth().substring(4, 6) + "월"
                            + userInfo.getBirth().substring(6, 8));
                    memberPostcode.setText(userInfo.getAddressnumber());
                    memberAdr1.setText(userInfo.getAddress());
                    memberAdr2.setText(userInfo.getDetailedaddress());

                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                System.out.println("Mypage 데이터 가져오기 오류" + call + " " + t);
            }
        });
        super.onResume();
    }

    public void setFindId() {
        memberName = (TextView) findViewById(R.id.mypagetxtSignUpNameAndSex);
        memberBirth = (TextView) findViewById(R.id.mypagetxtSignUpBirthDate);
        memberPhone1 = (Spinner) findViewById(R.id.mypagespinSignUpPhoneNumber1);
        memberPhone2 = (EditText) findViewById(R.id.mypageedtSignUpPhoneNumber2);
        memberPhone3 = (EditText) findViewById(R.id.mypageedtSignUpPhoneNumber3);
        memberPostcode = (TextView) findViewById(R.id.mypageedtSignUpPostCode);
        memberAdr1 = (TextView) findViewById(R.id.mypageedtSignUpAddress1);
        memberAdr2 = (EditText) findViewById(R.id.mypageedtSignUpAddress2);
        memberIdNumber = (TextView) findViewById(R.id.mypagetxtSocialSecurityNumber);
        memberEducation = (Spinner) findViewById(R.id.mypagespinEducation);
        memberSchool = (EditText) findViewById(R.id.mypageschool);
        memberMajor = (EditText) findViewById(R.id.mypagMajor);
        memberAdimissionMajor = (EditText) findViewById(R.id.mypagetxtAdmissionmajor);
        memberPw1 = (EditText) findViewById(R.id.mypageedtSignUpPassword);
        memberPw2 = (EditText) findViewById(R.id.mypageedtSignUpPasswordCheck);
        btnModify = (Button) findViewById(R.id.btnmypageModify);

        myPageBank1 = (LinearLayout) findViewById(R.id.myPageBank2);
        myPageBankText = (TextView) findViewById(R.id.myPageBank1);
        myPageBank2 = (LinearLayout) findViewById(R.id.myPageBank3);
        myPageBank3 = (LinearLayout) findViewById(R.id.myPageBank4);
    }


    public void spinnerEducation() {// 학력 스피너 초기화
        ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, education);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberEducation.setAdapter(adapter2);
    }

    public void spinnerPhone() {// 휴대폰 스피너 초기화
        ArrayAdapter adapter1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, phone);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberPhone1.setAdapter(adapter1);
    }

    public String pwCorrect(String pw1, String pw2) {
        String correct = "false";
        Pattern pattern1 = Pattern.compile("[ !@#$%^&*(),.?\":{}|<>]");
        if (pw1.isEmpty() && pw2.isEmpty()) {
            return correct;
        } else if (pw1.length() > 9 && pw1.length() < 20 && pw1.matches(".*[a-zA-Z].*") && pw1.matches(".*[0-9].*")
                && pattern1.matcher(pw1).find() && pw1.equals(pw2)) {
            return "ok";
        }
        return correct;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:

                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    int idx = data.indexOf(",");
                    if (data != null)
                        memberPostcode.setText(data.substring(0, idx));
                    memberAdr1.setText(data.substring(idx + 1));

                }
                break;

        }

    }


}
