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


public class Agreement2Fragment extends Fragment {
    Button cancel_button2,agree_button2;
    RadioGroup twoRadioGroup1, twoRadioGroup2, twoRadioGroup3, twoRadioGroup4;
    RadioButton tworadioBt1, tworadioBt2, tworadioBt3, tworadioBt4, tworadioBt5, tworadioBt6, tworadioBt7, tworadioBt8;
    boolean checkRadiogroup1=false;
    boolean checkRadiogroup2=false;
    boolean checkRadiogroup3=false;
    boolean checkRadiogroup4=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agreement2, container, false);

        setfindviewbyid(view);
        radioDefault();

        checkagreement();//동의여부확인

        movefragment();

        return view;
    }

    public void setfindviewbyid(View view){
        cancel_button2 = view.findViewById(R.id.cancel_button2);
        agree_button2 = view.findViewById(R.id.agree_button2);
        twoRadioGroup1 = view.findViewById(R.id.twoRadioGroup1);
        twoRadioGroup2 = view.findViewById(R.id.twoRadioGroup2);
        twoRadioGroup3 = view.findViewById(R.id.twoRadioGroup3);
        twoRadioGroup4 = view.findViewById(R.id.twoRadioGroup4);
        tworadioBt1 = view.findViewById(R.id.tworadioBt1);
        tworadioBt2 = view.findViewById(R.id.tworadioBt2);
        tworadioBt3 = view.findViewById(R.id.tworadioBt3);
        tworadioBt4 = view.findViewById(R.id.tworadioBt4);
        tworadioBt5 = view.findViewById(R.id.tworadioBt5);
        tworadioBt6 = view.findViewById(R.id.tworadioBt6);
        tworadioBt7 = view.findViewById(R.id.tworadioBt7);
        tworadioBt8 = view.findViewById(R.id.tworadioBt8);

    }

    public void radioDefault() {
        tworadioBt2.setChecked(true);
        tworadioBt4.setChecked(true);
        tworadioBt6.setChecked(true);
        tworadioBt8.setChecked(true);
        checkRadiogroup1=false;
        checkRadiogroup2=false;
        checkRadiogroup3=false;
        checkRadiogroup4=false;
    }//라디오버튼 디폴트

    public void movefragment(){//동의또는 취소를 눌러서 이전화면 또는 다음화면으로 이동
        cancel_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignActivity signActivity = (SignActivity) getActivity();
                signActivity.onFragmentChanged(0);
            }
        });
        agree_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //동의여부확인
                if(checkRadiogroup1==false || checkRadiogroup2==false || checkRadiogroup3==false|| checkRadiogroup4==false){
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

    public void checkagreement(){
        twoRadioGroup1.setOnCheckedChangeListener(checkRaidoButton(twoRadioGroup1));
        twoRadioGroup2.setOnCheckedChangeListener(checkRaidoButton(twoRadioGroup2));
        twoRadioGroup3.setOnCheckedChangeListener(checkRaidoButton(twoRadioGroup3));
        twoRadioGroup4.setOnCheckedChangeListener(checkRaidoButton(twoRadioGroup4));
    }

    public RadioGroup.OnCheckedChangeListener checkRaidoButton(RadioGroup checkgroup) {//라디오버튼체크

        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkgroup == twoRadioGroup1){
                    if(checkedId == R.id.tworadioBt1){
                        checkRadiogroup1= true;
                    }else if(checkedId == R.id.tworadioBt2){
                        checkRadiogroup1= false;
                    }
                }else if(checkgroup == twoRadioGroup2){
                    if(checkedId == R.id.tworadioBt3){
                        checkRadiogroup2= true;
                    }else if(checkedId == R.id.tworadioBt4){
                        checkRadiogroup2= false;
                    }
                }else if(checkgroup == twoRadioGroup3){
                    if(checkedId == R.id.tworadioBt5){
                        checkRadiogroup3= true;
                    }else if(checkedId == R.id.tworadioBt6){
                        checkRadiogroup3= false;
                    }
                }else if(checkgroup == twoRadioGroup4){
                    if(checkedId == R.id.tworadioBt7){
                        checkRadiogroup4= true;
                    }else if(checkedId == R.id.tworadioBt8){
                        checkRadiogroup4= false;
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