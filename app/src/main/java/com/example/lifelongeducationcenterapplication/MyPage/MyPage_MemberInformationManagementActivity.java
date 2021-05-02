package com.example.lifelongeducationcenterapplication.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RegisterResult;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;
import com.example.lifelongeducationcenterapplication.UserInfo;
import com.example.lifelongeducationcenterapplication.WebViewActivity;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class MyPage_MemberInformationManagementActivity extends AppCompatActivity {
    //마이페이지 회원정보관리
    TextView memberName, memberBirth, memberIdNumber, myPageBankText, memberPostcode, memberAdr1; // 이름 생년 주민번호, 학습은행제
    Spinner memberPhone1, memberEducation; // 휴대폰, 학력
    EditText memberPhone2, memberPhone3, memberAdr2, memberSchool  // 휴대폰, 주소, 전공 , 입학전공 , 패스워드
            , memberMajor, memberAdimissionMajor, memberPw1, memberPw2;

    Button btnAdr, btnModify;

    LinearLayout myPageBank1, myPageBank2, myPageBank3; // 학습은행제 일경우

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    Retrofit retrofit1;
    Retrofit retrofit2;

    String[] phone = {
            "010", "011", "016", "017", "018", "019"
    };


    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("내 정보");
        setContentView(R.layout.activity_my_page__member_information_management);


        setFindId();
        dbSend();
        searchAddress();
        spinnerPhone();
        updatePost();

        //일반은행제인 경우 안보이게 설정
        myPageBankText.setVisibility(View.GONE);
        myPageBank1.setVisibility(View.GONE);
        myPageBank2.setVisibility(View.GONE);
        myPageBank3.setVisibility(View.GONE);


    }

    public void dbSend() {
        //회원가입 유저 정보 디비 전송
        retrofit1 = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit2 = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
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
        btnAdr = (Button) findViewById(R.id.btnmypageSignUpAddressSearch);

        // 학습은행제
        myPageBank1 = (LinearLayout) findViewById(R.id.myPageBank2);
        myPageBankText = (TextView) findViewById(R.id.myPageBank1);
        myPageBank2 = (LinearLayout) findViewById(R.id.myPageBank3);
        myPageBank3 = (LinearLayout) findViewById(R.id.myPageBank4);


    }

    public void searchAddress() {

        btnAdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. 주소API를 이용하여 주소를 확인함.
                //2. DaumWebViewActivity로부터 intent로 addr을 받아와서 표시해주는 부분

                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                startActivityForResult(intent, SEARCH_ADDRESS_ACTIVITY);
            }
        });

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
                            + userInfo.getBirth().substring(6, 8) + "일");
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


    public void spinnerPhone() {// 휴대폰 스피너 초기화
        ArrayAdapter adapter1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, phone);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberPhone1.setAdapter(adapter1);
    }


    public void updatePost() { // 업데이트 내용 전송
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = memberPhone1.getSelectedItem().toString().trim()
                        + memberPhone2.getText().toString().trim()
                        + memberPhone3.getText().toString().trim();

                String postCode = memberPostcode.getText().toString().trim();
                String addr1 = memberAdr1.getText().toString().trim();
                String addr2 = memberAdr2.getText().toString().trim();

                String pw1 = memberPw1.getText().toString().trim();
                String pw2 = memberPw2.getText().toString().trim();

                String result = pwCorrect(pw1, pw2);
                RemoteService rs2 = retrofit2.create(RemoteService.class);

                Call<RegisterResult> call = rs2.myPageInfoPost1(StaticId.id, result, phoneNumber, postCode, addr1, addr2, pw1);
                call.enqueue(new Callback<RegisterResult>() {
                    public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                        RegisterResult registerResult = response.body();
                        System.out.println("result------------ " + registerResult.getResult());
                        if (registerResult.getResult().equals("ok")) {
                            Toast.makeText(getApplicationContext(), "수정 성공", Toast.LENGTH_LONG).show();
                            System.out.println("수정 성공");
                            memberPw1.setText("");
                            memberPw2.setText("");

                        } else if (registerResult.equals("false")) {
                            Toast.makeText(getApplicationContext(), "수정이 실패하였습니다!", Toast.LENGTH_LONG).show();
                            System.out.println("일반 수정 실패");
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResult> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "수정이 실패하였습니다!", Toast.LENGTH_LONG).show();
                    }

                });
            }
        });
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

}