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

import com.example.lifelongeducationcenterapplication.Account.SignUpCheckFragment;
import com.example.lifelongeducationcenterapplication.Lecture;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;
import com.example.lifelongeducationcenterapplication.UserInfo;
import com.example.lifelongeducationcenterapplication.WebViewActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class MyPage_CourseDetailsActivity extends AppCompatActivity {
    //마이페이지 수강내역
    TextView memberName, memberBirth, memberIdNumber, myPageBankText, memberPostcode, memberAdr1; // 이름 생년 주민번호, 학습은행제
    Spinner memberPhone1, memberEducation; // 휴대폰, 학력
    EditText memberPhone2, memberPhone3, memberAdr2, memberSchool  // 휴대폰, 주소, 전공 , 입학전공 , 패스워드
            , memberMajor, memberAdimissionMajor, memberPw1, memberPw2 ;

    Button  btnSignUpAddressSearch;

    LinearLayout myPageBank1, myPageBank2, myPageBank3; // 학습은행제 일경우

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    Retrofit retrofit1;
    Retrofit retrofit2;

    String[] phone = {
            "010","011","016","017","018","019"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("수강내역");
        setContentView(R.layout.activity_my_page__course_details);

        setFindId();
        dbSend();
        searchAddress();
        spinnerPhone();

        
        //학점은행제인 경우 안보이게 설정
        if(StaticId.course == "학점은행제과정"){
            myPageBankText.setVisibility(View.GONE);
            myPageBank1.setVisibility(View.GONE);
            myPageBank2.setVisibility(View.GONE);
            myPageBank3.setVisibility(View.GONE);
        }


    }

    public void dbSend(){
        //회원가입 유저 정보 디비 전송
        retrofit1 = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void setFindId(){
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

        // 학습은행제
        myPageBank1 = (LinearLayout) findViewById(R.id.myPageBank2);
        myPageBankText = (TextView) findViewById(R.id.myPageBank1);
        myPageBank2 = (LinearLayout) findViewById(R.id.myPageBank3);
        myPageBank3 = (LinearLayout) findViewById(R.id.myPageBank4);


    }

    public void searchAddress(){

        btnSignUpAddressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. 주소API를 이용하여 주소를 확인함.
                //2. DaumWebViewActivity로부터 intent로 addr을 받아와서 표시해주는 부분

                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                startActivityForResult(intent, SEARCH_ADDRESS_ACTIVITY);


            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        super.onActivityResult(requestCode, resultCode, intent);

        switch(requestCode){
            case SEARCH_ADDRESS_ACTIVITY:

                if(resultCode == RESULT_OK){
                    String data = intent.getExtras().getString("data");
                    int idx = data.indexOf(",");
                    if (data != null)
                        memberPostcode.setText(data.substring(0, idx));
                        memberAdr1.setText(data.substring(idx+1));

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
                if(response.isSuccessful()){
                    UserInfo userInfo = response.body();
                    memberName.setText(userInfo.getName());

                    // 휴대폰
                    memberPhone2.setText(userInfo.getPhoneNumber().substring(3, 6));
                    memberPhone3.setText(userInfo.getPhoneNumber().substring(7, 10));

                    // 생일
                    memberBirth.setText(userInfo.getBirth().substring(0,3) + "년" + userInfo.getBirth().substring(4,5) + "월"
                                       + userInfo.getBirth().substring(6,7));
                    memberPostcode.setText(userInfo.getAddressnumber());
                    memberAdr1.setText(userInfo.getAddress());
                    memberAdr2.setText(userInfo.getDetailedaddress());

                    //학점은행제
                    memberIdNumber.setText(userInfo.getBirth().substring(2, 7) + " - *******" );

                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                System.out.println("Mypage 데이터 가져오기 오류" +call +" " + t);

            }
        });
        super.onResume();
    }


    public void spinnerPhone(){//스피너 초기화
        ArrayAdapter adapter1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, phone);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberPhone1.setAdapter(adapter1);
    }
}
