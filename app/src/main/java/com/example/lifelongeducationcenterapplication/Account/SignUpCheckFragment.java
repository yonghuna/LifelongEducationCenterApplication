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

    boolean check = true;

    String course;
    String userBirhday;
    String userName;
    String userGender;
    Intent intent;

    // 스피너 값 설정
    String[] year = {
            "2020", "2019","2018","2017", "2016","2015",
            "2014", "2013","2012","2011", "2010","2009",
            "2008", "2007","2006","2005", "2004","2003",
            "2002", "2001","2000","1999", "1998","1997",
            "1996", "1995","1994","1993", "1992","1991",
            "1990", "1989","1988","1987", "1986","1985",
            "1984", "1983","1982","1981", "1980","1979",
            "1978", "1977","1976","1975", "1974","1973",
            "1972", "1971","1970","1969", "1968","1967",
            "1966", "1965","1964","1963", "1962","1961",
            "1960", "1959","1958","1957", "1956","1955",
            "1954", "1953","1952","1951", "1950","1949",
            "1948", "1947","1946","1945", "1944","1943",
            "1942", "1941","1940","1939", "1938","1937",
            "1936", "1935","1934","1933", "1932","1931"
    };

    String [] month = {
            "01","02","03","04","05","06",
            "07","08","09","10","11","12"
    };

    String [] day = {
            "01","02","03","04","05","06","07","08","09","10","11","12","13","14",
            "15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"
    };

    String [] gender = {
            "남", "여"
    };




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_check, container, false);

        setfindviewbyid(view);//findviewbyid설정

        spinnerDefalut();

        radioDefault();//라디오버튼 디폴트 처리
        check = checkRaidoButton();//라디오버튼체크
        goAgreement(check);//화면이동

        return view;
    }

    public void setfindviewbyid(View view){
        rgCheckCourse = view.findViewById(R.id.rgCheckCourse);
        rbGeneralCourse = view.findViewById(R.id.rbGeneralCourse);
        rbCreditBank = view.findViewById(R.id.rbCreditBank);//라디오버튼 일반과정, 학점은행제과정
        edtNameCheck = view.findViewById(R.id.edtNameCheck);//성명
        spinBirthCheck1 = view.findViewById(R.id.spinBirthCheck1);
        spinBirthCheck2 = view.findViewById(R.id.spinBirthCheck2);
        spinBirthCheck3 = view.findViewById(R.id.spinBirthCheck3);//생년월일
        spinSexCheck = view.findViewById(R.id.spinSexCheck);//성별
        btnSignUpCheck = view.findViewById(R.id.btnSignUpCheck);//등록확인
    }

    public void spinnerDefalut(){
        ArrayAdapter adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, year);
        ArrayAdapter adapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, month);
        ArrayAdapter adapter3 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, day);
        ArrayAdapter adapter4 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, gender);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinBirthCheck1.setAdapter(adapter1);
        spinBirthCheck2.setAdapter(adapter2);
        spinBirthCheck3.setAdapter(adapter3);
        spinSexCheck.setAdapter(adapter4);
    }


    public void radioDefault(){
        rbGeneralCourse.setChecked(true);
    }//라디오버튼 디폴트


    public boolean checkRaidoButton(){//라디오버튼체크

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
                /*

                   앞으로 구현해야하는 것들 : 입력한 자료를 바탕으로 중복되는 회원이 있는지 확인해서 경고창(다이얼로그)를 띄우거나 화면이동을 결정한다.
                   그다음에 아래에 있는 기능을 구현한다.

                 */
                SignActivity signActivity = (SignActivity) getActivity();//부모엑티비티를 가져옴.

                setsignupfragmentconponent(signActivity,check); //회원가입화면에 입력초기화

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


    public void setsignupfragmentconponent(SignActivity signActivity, boolean check){ //회원가입화면에 입력초기화

        userGender =spinSexCheck.getSelectedItem().toString().trim(); //성별
        userName = edtNameCheck.getText().toString().trim(); //이름

        String spinBirthday1 = spinBirthCheck1.getSelectedItem().toString().trim(); //회원 생일1
        String spinBirthday2 = spinBirthCheck2.getSelectedItem().toString().trim(); //회원 생일2
        String spinBirthday3 = spinBirthCheck3.getSelectedItem().toString().trim(); //회원 생일3

        userBirhday = spinBirthday1 + "/" + spinBirthday2 + "/" +spinBirthday3;
        String nameGender = userName + " (" + userGender + ")";


        if(check == true){

            Bundle bundle = new Bundle();
            bundle.putString("txtSignUpDivision","일반과정");
            bundle.putString("txtSignUpNameAndSex",nameGender);
            bundle.putString("txtSignUpBirthDate",userBirhday);

            /* 잘못된과정
            signActivity.signUpFragment.txtSignUpDivision.setText("일반과정");//과정
            signActivity.signUpFragment.txtSignUpNameAndSex.setText(nameGender);//성별
            signActivity.signUpFragment.txtSignUpBirthDate.setText(userBirhday);// 회원 생일

             */

            signActivity.signUpFragment.setArguments(bundle);
        }else if(check == false){

            Bundle bundle = new Bundle();
            bundle.putString("txtSignUpDivision","일반과정");
            bundle.putString("txtSignUpNameAndSex",nameGender);
            bundle.putString("txtSignUpBirthDate",userBirhday);

            /*
            signActivity.signUpFragment.txtSignUpDivision.setText("학점은행제과정");
            signActivity.signUpFragment.txtSignUpNameAndSex.setText(nameGender);//성별
            signActivity.signUpFragment.txtSignUpBirthDate.setText(userBirhday);// 회원 생일

             */
            signActivity.signUpFragment.setArguments(bundle);
        }


    }





}