package com.example.lifelongeducationcenterapplication.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.UserInfo;

public class SignUpCheckActivity extends AppCompatActivity {

    RadioButton rbGeneralCourse, rbCreditBank;//라디오버튼 일반과정, 학점은행제과정
    EditText edtNameCheck;//성명
    Spinner spinBirthCheck1,spinBirthCheck2,spinBirthCheck3; //생년월일
    Spinner spinSexCheck; //성별
    Button btnSignUpCheck; //등록확인
    String course;
    String userBirhday;
    String userName;
    String userGender;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_check);

        rbGeneralCourse = (RadioButton)findViewById(R.id.rbGeneralCourse);// 일반과정
        rbCreditBank = (RadioButton)findViewById(R.id.rbCreditBank);//학점은행제과정
        edtNameCheck = (EditText) findViewById(R.id.edtNameCheck);//성명
        spinBirthCheck1 = (Spinner) findViewById(R.id.spinBirthCheck1);
        spinBirthCheck2 = (Spinner) findViewById(R.id.spinBirthCheck2);
        spinBirthCheck3 = (Spinner) findViewById(R.id.spinBirthCheck3);//생년월일
        spinSexCheck = (Spinner) findViewById(R.id.spinSexCheck);//성별
        btnSignUpCheck = (Button) findViewById(R.id.btnSignUpCheck);//등록확인


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

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, year);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, month);
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, day);
        ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinBirthCheck1.setAdapter(adapter1);
        spinBirthCheck2.setAdapter(adapter2);
        spinBirthCheck3.setAdapter(adapter3);
        spinSexCheck.setAdapter(adapter4);


        btnSignUpCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                1. 수강생확인입력한것 가져오기
                2. 데이테베이스로부터 해당하는 회원정보를 가져오기
                3. 1번과 2번을 통해 가져온 자료를 바탕으로 회원가입유무확인
                 */

                // 나중에 고쳐야됨
                if(course.equals("일반과정")){
                    intent = new Intent(getApplicationContext(), SignUpActivity.class);
                }else{
                    intent = new Intent(getApplicationContext(), SignUpActivity.class);
                }


                userGender =spinSexCheck.getSelectedItem().toString().trim(); //회원 휴대폰 번호
                userName = edtNameCheck.getText().toString().trim(); //회원 비밀번호

                String spinBirthday1 = spinBirthCheck1.getSelectedItem().toString().trim(); //회원 생일1
                String spinBirthday2 = spinBirthCheck2.getSelectedItem().toString().trim(); //회원 생일2
                String spinBirthday3 = spinBirthCheck3.getSelectedItem().toString().trim(); //회원 생일3

                userBirhday = spinBirthday1 + "/" + spinBirthday2 + "/" +spinBirthday3;

                UserInfo userInfo = new UserInfo();
                userInfo.setCourse(course);
                userInfo.setName(userName);
                userInfo.setBirthday(userBirhday);
                userInfo.setGender(userGender);
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class );

                intent.putExtra("userInfo", userInfo);
                startActivity(intent);



            }
        });

        rbGeneralCourse.setOnClickListener(new View.OnClickListener() {
            //일반 과정 
            @Override
            public void onClick(View v) {
                course = "일반과정";
            }
        });

        rbCreditBank.setOnClickListener(new View.OnClickListener() {
            //학점 은행제
            @Override
            public void onClick(View v) {
                course = "학점은행제";
            }
        });

    }
}