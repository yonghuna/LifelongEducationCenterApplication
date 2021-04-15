package com.example.lifelongeducationcenterapplication.Account;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifelongeducationcenterapplication.CommunicationResult;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.WebViewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    String name, gender, birthDate, course;
    String post, addr, fullAddr;
    int idx;
    boolean result;

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    String[] phone = {
            "010","011","016","017","018","019"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        setfindviewbyid(view);//id설정
        settexttext();//컴포넌트초기화(회원가입확인프래그먼트에서 받아온 성별,생년월일,이름,교육과정을 확인후 초기화)
        spinnerphone();//스피너초기화




        //주석처리한거는 DB연동이 다 안되어있으므로 제대로된 테스트를 하지 못해 일부러 처리했습니다.
        ////////////////////////

        //dbsend();//회원가입 유저 정보 디비 전송
        searchaddress();
        //registeruser();
        registerusertest();//registeruser()가 완성되면 지워도 됩니다. 회원가입후 화면이 종료되는지 테스트하는 것입니다.

        ////////////////////////

        cancelregister();//취소버튼을 눌렀을 경우

        return view;
    }


    public void spinnerphone(){//스피너 초기화
        ArrayAdapter adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, phone);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSignUpPhoneNumber1.setAdapter(adapter1);
    }

    public void setfindviewbyid(View view){
        txtSignUpDivision = view.findViewById(R.id.txtSignUpDivision);
        txtSignUpNameAndSex = view.findViewById(R.id.txtSignUpNameAndSex);
        txtSignUpBirthDate =  view.findViewById(R.id.txtSignUpBirthDate);//등록구분, 이름(성별)
        spinSignUpPhoneNumber1 =  view.findViewById(R.id.spinSignUpPhoneNumber1);
        edtSignUpPhoneNumber2 =  view.findViewById(R.id.edtSignUpPhoneNumber2);
        edtSignUpPhoneNumber3 =  view.findViewById(R.id.edtSignUpPhoneNumber3);//전화번호
        edtSignUpPostCode =  view.findViewById(R.id.edtSignUpPostCode);//우편번호
        btnSignUpAddressSearch =  view.findViewById(R.id.btnSignUpAddressSearch);//주소검색
        edtSignUpAddress1 =  view.findViewById(R.id.edtSignUpAddress1);
        edtSignUpAddress2 =  view.findViewById(R.id.edtSignUpAddress2);//주소1, 주소2
        edtSignUpPassword =  view.findViewById(R.id.edtSignUpPassword);
        edtSignUpPasswordCheck =  view.findViewById(R.id.edtSignUpPasswordCheck);//비밀번호입력, 비밀번호확인
        btnSignUpRegister =  view.findViewById(R.id.btnSignUpRegister);
        btnSignUpRegisterCancel =  view.findViewById(R.id.btnSignUpRegisterCancel);//회원등록,취소
    }


    public void dbsend(){
        //회원가입 유저 정보 디비 전송
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void settexttext(){//컴포넌트 초기화 (회원가입확인프래그먼트에서 받아온 성별,생년월일,이름,교육과정을 확인후 초기화)

        txtSignUpDivision.setText(getArguments().getString("txtSignUpDivision")); // 어느 과정인지
        txtSignUpNameAndSex.setText(getArguments().getString("txtSignUpNameAndSex")); // 성별 이름
        txtSignUpBirthDate.setText(getArguments().getString("txtSignUpBirthDate"));// 회원 생일

        /* //초기세팅

        //signupcheckfragment에서 초기세팅을 했습니다. 참고해주세요. 그리고 intent는 쓰지 않고 다른방식(bundle)으로 데이터를 전달했습니다.
          signupcheckfragment의 setsignupfragmentconponent() 부분 참고하세요.

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

         */


    }

    public void searchaddress(){

        btnSignUpAddressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. 주소API를 이용하여 주소를 확인함.
                //2. DaumWebViewActivity로부터 intent로 addr을 받아와서 표시해주는 부분

                Intent i = new Intent(getActivity(), WebViewActivity.class);
                startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);

            }
        });

    }

    public void registeruser(){
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
                String userPass1 = edtSignUpPassword.getText().toString().trim(); //회원 비밀번호
                String userPass2 = edtSignUpPasswordCheck.getText().toString().trim(); //회원 비밀번호

                System.out.println(userAddr);

                result = pwCorrect(userPass1, userPass2); // 패스워드 옳은지 체크


                // 데이터베이스로 전송
                if (result) {
                    RemoteService rs = retrofit.create(RemoteService.class);
                    Call<List<CommunicationResult>> call = rs.userRegister(userPhoneNumber, userPass2, course, userAddr, birthDate, name);
                    call.enqueue(new Callback<List<CommunicationResult>>() {
                        public void onResponse(Call<List<CommunicationResult>> call, Response<List<CommunicationResult>> response) {
                            List<CommunicationResult> userRegister = new ArrayList<>();
                            userRegister = response.body();
                            CommunicationResult communicationResult = userRegister.get(0);
                            if (communicationResult.getResult().equals("ok")) {
                                Toast.makeText(getContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                                System.out.println("회원가입 성공");
                            } else {
                                Toast.makeText(getContext(), "회원가입 실패", Toast.LENGTH_LONG).show();
                                System.out.println("회원가입 실패");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CommunicationResult>> call, Throwable t) {
                            Toast.makeText(getActivity(), "회원가입이 실패하였습니다!", Toast.LENGTH_LONG).show();
                            //finish();
                        }

                    });

                }
            }
        });
    }



    public boolean pwCorrect(String pw1, String pw2){
        boolean result = false;
        Pattern pattern1 = Pattern.compile("[ !@#$%^&*(),.?\":{}|<>]");
        if(pw1.length() < 9 && pw1.length() > 20) {
            if(pw1.matches(".*[a-zA-Z].*")) {
                System.out.println("영문자가 포함되어 있습니다.");
                if(pw1.matches(".*[0-9].*")) {
                    System.out.println("숫자가 포함되어 있습니다.");
                    if(pattern1.matcher(pw1).find()){
                        System.out.println("특수문자가 포함되어있습니다.");
                        if(pw1.equals(pw2)) {
                            result = true;
                        } else {
                            Toast.makeText(getContext(), "패스워드가 일치 하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "패스워드에 특수문자를 포함해주세요", Toast.LENGTH_SHORT).show();
                    }


                }else {
                    System.out.println("숫자가 포함되어 있지 않습니다.");
                    Toast.makeText(getContext(), "패스워드에 숫자를 포함해주세요", Toast.LENGTH_SHORT).show();
                }
            }else {
                System.out.println("영문자가 포함되어 있지 않습니다.");
                Toast.makeText(getContext(), "패스워드에 영문자를 포함해주세요", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(getContext(), "패스워드를 9 ~ 20 로 만들어주세요", Toast.LENGTH_SHORT).show();
        }


        return result;


    }

    public void cancelregister(){//취소버튼을 눌렀을 경우 이전화면인 동의화면으로 이동함.
        btnSignUpRegisterCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignActivity signActivity = (SignActivity) getActivity();
                if(txtSignUpDivision.getText().toString().trim().equals("일반과정")){
                    signActivity.onFragmentChanged(1);
                }else if(txtSignUpDivision.getText().toString().trim().equals("학점은행제과정")){
                    signActivity.onFragmentChanged(2);
                }

            }
        });
    }

    public void registerusertest(){
        btnSignUpRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. 빠트린 부분이 없는지 체크(다이얼로그로 회원등록하세겠습니까 라는 창을 띄워서 한번더 사용자에게 확인시킴)
                //2. 비밀번호확인(올바르게 입력이 되었는지 확인, 비밀번호와 비밀번호확인과 일치하는지 확인)
                //3. 신규회원가입정보가 데이터베이스에 저장
                //4. 회원가입완료했다고 다이얼로그창을 띄운후 (메인화면으로 넘어감.) -> (회원가입 액티비티 종료, 자동으로 모든 프래그먼트 종료됨)

                /*

                 */

                //(회원가입 액티비티 종료, 자동으로 모든 프래그먼트 종료됨)에 해당함.
                SignActivity signActivity = (SignActivity) getActivity();
                signActivity.finish();//액티비티 종료

            }
        });
    }

}