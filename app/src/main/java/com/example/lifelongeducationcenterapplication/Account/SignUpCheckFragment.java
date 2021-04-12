package com.example.lifelongeducationcenterapplication.Account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.lifelongeducationcenterapplication.R;

public class SignUpCheckFragment extends Fragment {

    RadioGroup rgCheckCourse;
    RadioButton rbGeneralCourse, rbCreditBank;//라디오버튼 일반과정, 학점은행제과정
    EditText edtNameCheck;//성명
    Spinner spinBirthCheck1,spinBirthCheck2,spinBirthCheck3; //생년월일
    Spinner spinSexCheck; //성별
    Button btnSignUpCheck; //등록확인

    boolean check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_check, container, false);

        rgCheckCourse = view.findViewById(R.id.rgCheckCourse);
        rbGeneralCourse = view.findViewById(R.id.rbGeneralCourse);
        rbCreditBank = view.findViewById(R.id.rbCreditBank);//라디오버튼 일반과정, 학점은행제과정
        edtNameCheck = view.findViewById(R.id.edtNameCheck);//성명
        spinBirthCheck1 = view.findViewById(R.id.spinBirthCheck1);
        spinBirthCheck2 = view.findViewById(R.id.spinBirthCheck2);
        spinBirthCheck3 = view.findViewById(R.id.spinBirthCheck3);//생년월일
        spinSexCheck = view.findViewById(R.id.spinSexCheck);//성별
        btnSignUpCheck = view.findViewById(R.id.btnSignUpCheck);//등록확인

        radioDefault();//라디오버튼 디폴트 처리
        check = checkRaidoButton();//라디오버튼체크
        goAgreement(check);//화면이동

        return view;
    }


    public void goAgreement(boolean check){
        btnSignUpCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                1. 수강생확인입력한것 가져오기
                2. 데이테베이스로부터 해당하는 회원정보를 가져오기
                3. 1번과 2번을 통해 가져온 자료를 바탕으로 회원가입유무확인
                4. 동의하는 화면(프래그먼트) 이동
                 */
                SignActivity signActivity = (SignActivity) getActivity();//부모엑티비티를 가져옴.
                if(check == true){
                    System.out.println("결과"+check);
                    signActivity.onFragmentChanged(1);//일반과정동의 이동
                }else if(check == false){
                    System.out.println("결과"+check);
                    signActivity.onFragmentChanged(2);//학점은행제동의 이동
                }



            }
        });
    }


    public void radioDefault(){
        rbGeneralCourse.setChecked(true);
    }

    public boolean checkRaidoButton(){
        //final boolean[] check = new boolean[1];


        boolean checkradio = true;
        if(rgCheckCourse.getCheckedRadioButtonId() == R.id.rbGeneralCourse){
            checkradio = true;
        }else if(rgCheckCourse.getCheckedRadioButtonId() == R.id.rbCreditBank){
            checkradio = false;
        }
        return checkradio;

        /*
        rgCheckCourse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbGeneralCourse){
                    check[0] = true;
                    System.out.println("결과"+ check[0]);
                }else if(checkedId == R.id.rbCreditBank){
                    check[0] = false;
                    System.out.println("결과"+ check[0]);
                }
            }
        });

         */

        /*
        System.out.println("결과2"+ check[0]);
        return check[0];

        rgCheckCourse.getCheckedRadioButtonId();


         */

    }
}