package com.example.lifelongeducationcenterapplication.Account;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifelongeducationcenterapplication.Account.SignActivity;
import com.example.lifelongeducationcenterapplication.CommunicationResult;
import com.example.lifelongeducationcenterapplication.MainActivity;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RegisterResult;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.WebViewActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;
import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class SignUpFragment extends Fragment {

    TextView txtSignUpDivision, txtSignUpNameAndSex, txtSignUpBirthDate;//등록구분, 이름(성별)
    Spinner spinSignUpPhoneNumber1;//전화번호1
    EditText edtSignUpPhoneNumber2, edtSignUpPhoneNumber3;//전화번호2, 전화번호3
    TextView edtSignUpPostCode;//우편번호 EDITVIEW 인데 텍스트로 선언
    Button btnSignUpAddressSearch; //주소검색
    TextView edtSignUpAddress1, edtSignUpAddress2; //주소1, 주소2
    EditText edtSignUpPassword, edtSignUpPasswordCheck; //비밀번호입력, 비밀번호확인
    Button btnSignUpRegister, btnSignUpRegisterCancel; //등록, 취소

    Retrofit retrofit;
    String name, birthDate, course, sex, retrofitBirth, tokenResult;
    boolean pwResult, blankResult;

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    String[] phone = {
            "010", "011", "016", "017", "018", "019"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        firebase();
        setfindviewbyid(view);//id설정
        setText();//컴포넌트초기화(회원가입확인프래그먼트에서 받아온 성별,생년월일,이름,교육과정을 확인후 초기화)
        spinnerphone();//스피너초기화




        dbSend();//회원가입 유저 정보 디비 전송
        searchaddress();
        registerUser();


        ////////////////////////

        cancelregister();//취소버튼을 눌렀을 경우

        return view;
    }


    public void spinnerphone() {//스피너 초기화
        ArrayAdapter adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, phone);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSignUpPhoneNumber1.setAdapter(adapter1);
    }

    public void setfindviewbyid(View view) {
        txtSignUpDivision = view.findViewById(R.id.txtSignUpDivision);
        txtSignUpNameAndSex = view.findViewById(R.id.txtSignUpNameAndSex);
        txtSignUpBirthDate = view.findViewById(R.id.txtSignUpBirthDate);//등록구분, 이름(성별)
        spinSignUpPhoneNumber1 = view.findViewById(R.id.spinSignUpPhoneNumber1);
        edtSignUpPhoneNumber2 = view.findViewById(R.id.edtSignUpPhoneNumber2);
        edtSignUpPhoneNumber3 = view.findViewById(R.id.edtSignUpPhoneNumber3);//전화번호
        edtSignUpPostCode = view.findViewById(R.id.edtSignUpPostCode);//우편번호
        btnSignUpAddressSearch = view.findViewById(R.id.btnSignUpAddressSearch);//주소검색
        edtSignUpAddress1 = view.findViewById(R.id.edtSignUpAddress1);
        edtSignUpAddress2 = view.findViewById(R.id.edtSignUpAddress2);//주소1, 주소2
        edtSignUpPassword = view.findViewById(R.id.edtSignUpPassword);
        edtSignUpPasswordCheck = view.findViewById(R.id.edtSignUpPasswordCheck);//비밀번호입력, 비밀번호확인
        btnSignUpRegister = view.findViewById(R.id.btnSignUpRegister);
        btnSignUpRegisterCancel = view.findViewById(R.id.btnSignUpRegisterCancel);//회원등록,취소
    }


    public void dbSend() {
        //회원가입 유저 정보 디비 전송
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void setText() {//컴포넌트 초기화 (회원가입확인프래그먼트에서 받아온 성별,생년월일,이름,교육과정을 확인후 초기화)

        sex = getArguments().getString("txtSignUpSex");
        name = getArguments().getString("txtSignUpName");
        birthDate = getArguments().getString("txtSignUpBirthDate");
        course = getArguments().getString("txtSignUpDivision");

        String nameGender = name + " (" + sex + ")";
        retrofitBirth = birthDate.replace("/", "");

        txtSignUpDivision.setText(course); // 어느 과정인지
        txtSignUpNameAndSex.setText(nameGender); // 성별 이름
        txtSignUpBirthDate.setText(birthDate);// 회원 생일


    }

    public void searchaddress() {

        btnSignUpAddressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. 주소API를 이용하여 주소를 확인함.
                //2. DaumWebViewActivity로부터 intent로 addr을 받아와서 표시해주는 부분

                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                startActivityForResult(intent, SEARCH_ADDRESS_ACTIVITY);


            }
        });

    }

    public void registerUser() {
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
                String userAddr = edtSignUpAddress1.getText().toString().trim();// 주소
                String userDetailedAddr = edtSignUpAddress2.getText().toString().trim();
                String userAddrNumber = edtSignUpPostCode.getText().toString().trim();
                String userPass1 = edtSignUpPassword.getText().toString().trim(); //회원 비밀번호
                String userPass2 = edtSignUpPasswordCheck.getText().toString().trim(); //회원 비밀번호


                blankResult = blankCheck();
                pwResult = pwCorrect(userPass1, userPass2); // 패스워드가 조건에 맞는지 확인


                // 데이터베이스로 전송
                if (blankResult) {
                    if (pwResult) {
                        RemoteService rs = retrofit.create(RemoteService.class);
                        Call<RegisterResult> call = rs.userRegister(userPhoneNumber, userPass2, course, userAddrNumber, userAddr, userDetailedAddr, retrofitBirth, name, sex, tokenResult);
                        call.enqueue(new Callback<RegisterResult>() {
                            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                                RegisterResult registerResult = response.body();
                                System.out.println("학습은행제" + registerResult.getResult());
                                if (registerResult.getResult().equals("ok")) {
                                    Toast.makeText(getContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                                    System.out.println("회원가입 성공");
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);

                                } else if (registerResult.getResult().equals("false")) {
                                    Toast.makeText(getContext(), "회원가입 실패", Toast.LENGTH_LONG).show();
                                    System.out.println("회원가입 실패");
                                }
                            }

                            @Override
                            public void onFailure(Call<RegisterResult> call, Throwable t) {
                                Toast.makeText(getActivity(), "회원가입이 실패하였습니다!", Toast.LENGTH_LONG).show();
                            }

                        });

                    } else {
                        Toast.makeText(getContext(), "패스워드를 조건에 맞게 다시 설정해주세요!!", Toast.LENGTH_SHORT).show();
                        System.out.println(edtSignUpPassword.getText().toString());
                        System.out.println(edtSignUpPasswordCheck.getText().toString());
                    }
                } else {
                    Toast.makeText(getActivity(), "빈칸을 모두 채우시오!!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public boolean pwCorrect(String pw1, String pw2) {
        boolean result = false;
        Pattern pattern1 = Pattern.compile("[ !@#$%^&*(),.?\":{}|<>]");
        if (pw1.length() > 9 && pw1.length() < 20 && pw1.matches(".*[a-zA-Z].*") && pw1.matches(".*[0-9].*")
                && pattern1.matcher(pw1).find() && pw1.equals(pw2)) {
            result = true;
        }

        return result;

    }

    // 패스워드가 빈칸인지 확인하는 메소드
    public boolean blankCheck() {

        boolean result = false;
        String userPhoneNumber1 = spinSignUpPhoneNumber1.getSelectedItem().toString().trim(); //전화1
        String userPhoneNumber2 = edtSignUpPhoneNumber2.getText().toString().trim();// 전화2
        String userPhoneNumber3 = edtSignUpPhoneNumber3.getText().toString().trim();// 전화3
        String userAddr = edtSignUpAddress1.getText().toString().trim();// 주소
        String userDetailedAddr = edtSignUpAddress2.getText().toString().trim();
        String userAddrNumber = edtSignUpPostCode.getText().toString().trim();
        String userPass1 = edtSignUpPassword.getText().toString().trim(); //회원 비밀번호
        String userPass2 = edtSignUpPasswordCheck.getText().toString().trim(); //회원 비밀번호

        if (userPhoneNumber1.isEmpty() || userPhoneNumber2.isEmpty() || userPhoneNumber3.isEmpty()
                || userAddr.isEmpty() || userDetailedAddr.isEmpty() || userAddrNumber.isEmpty() || userPass1.isEmpty()
                || userPass2.isEmpty()) {

        } else {
            result = true;
        }

        return result;
    }

    public void cancelregister() {//취소버튼을 눌렀을 경우 이전화면인 동의화면으로 이동함.
        btnSignUpRegisterCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignActivity signActivity = (SignActivity) getActivity();
                if (txtSignUpDivision.getText().toString().trim().equals("일반과정")) {
                    signActivity.onFragmentChanged(1);
                } else if (txtSignUpDivision.getText().toString().trim().equals("학점은행제과정")) {
                    signActivity.onFragmentChanged(2);
                }

            }
        });
    }


    // 주소검색 메소드

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:

                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    int idx = data.indexOf(",");
                    if (data != null)
                        edtSignUpPostCode.setText(data.substring(0, idx));
                    edtSignUpAddress1.setText(data.substring(idx + 1));

                }
                break;

        }

    }

    public void firebase() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println("error");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        tokenResult = getString(R.string.msg_token_fmt, token);

                    }
                });
    }

}