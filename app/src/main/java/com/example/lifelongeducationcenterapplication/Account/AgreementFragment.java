package com.example.lifelongeducationcenterapplication.Account;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lifelongeducationcenterapplication.R;

public class AgreementFragment extends Fragment {

    Button cancel_button1, agree_button1;
    RadioGroup agreeRadioGroup1,agreeRadioGroup2;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    boolean checkRadiogroup1=false;
    boolean checkRadiogroup2=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agreement, container, false);

        setfindviewbyid(view);
        radioDefault();//라디오버튼 디폴트

        checkagreement();

        movefragment();

        return view;
    }

    public void setfindviewbyid(View view){

        cancel_button1 = view.findViewById(R.id.cancel_button1);
        agree_button1 = view.findViewById(R.id.agree_button1);
        agreeRadioGroup1 = view.findViewById(R.id.agreeRadioGroup1);
        agreeRadioGroup2 = view.findViewById(R.id.agreeRadioGroup2);
        radioButton1 = view.findViewById(R.id.radioButton1);
        radioButton2 = view.findViewById(R.id.radioButton2);
        radioButton3 = view.findViewById(R.id.radioButton3);
        radioButton4 = view.findViewById(R.id.radioButton4);

    }

    public void radioDefault() {
        radioButton2.setChecked(true);
        radioButton4.setChecked(true);
        checkRadiogroup1=false;
        checkRadiogroup2=false;
    }//라디오버튼 디폴트

    public void movefragment(){//동의또는 취소를 눌러서 이전화면 또는 다음화면으로 이동
        cancel_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SignActivity signActivity = (SignActivity) getActivity();
                signActivity.onFragmentChanged(0);
            }
        });
        agree_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //동의여부확인
                if(checkRadiogroup1 == false || checkRadiogroup2 == false){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("동의");
                    builder.setMessage("개인정보 수집 및 이용 동의서에 동의를 하셔야만 등록을 하실 수 있습니다.");
                    builder.setPositiveButton("확인",null);
                    builder.show();
                }else{
                    SignActivity signActivity = (SignActivity) getActivity();
                    signActivity.onFragmentChanged(3);
                }

            }
        });
    }

    public void checkagreement(){//동의여부확인
        agreeRadioGroup1.setOnCheckedChangeListener(checkRaidoButton(agreeRadioGroup1));
        agreeRadioGroup2.setOnCheckedChangeListener(checkRaidoButton(agreeRadioGroup2));
    }

    public RadioGroup.OnCheckedChangeListener checkRaidoButton(RadioGroup checkgroup) {//라디오버튼체크

        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkgroup == agreeRadioGroup1){
                    if(checkedId == R.id.radioButton1){
                        checkRadiogroup1= true;
                    }else if(checkedId == R.id.radioButton2){
                        checkRadiogroup1= false;
                    }
                }else if(checkgroup == agreeRadioGroup2){
                    if(checkedId == R.id.radioButton3){
                        checkRadiogroup2= true;
                    }else if(checkedId == R.id.radioButton4){
                        checkRadiogroup2= false;
                    }
                }
            }
        };

        /*
        if (rgCheckCourse.getCheckedRadioButtonId() == R.id.rbGeneralCourse) {
            System.out.println("일반과정");
            checkradio = true;
        } else if (rgCheckCourse.getCheckedRadioButtonId() == R.id.rbCreditBank) {
            System.out.println("학점은행제");
            checkradio = false;
        }
        return checkradio;

        */
        return onCheckedChangeListener;

    }

}