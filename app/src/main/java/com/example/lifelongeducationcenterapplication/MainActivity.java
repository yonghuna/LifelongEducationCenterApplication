package com.example.lifelongeducationcenterapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.text.util.Linkify;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.lifelongeducationcenterapplication.Account.Login;
import com.example.lifelongeducationcenterapplication.Account.SignActivity;

import com.example.lifelongeducationcenterapplication.Community.Community_FormattingRoomActivity;
import com.example.lifelongeducationcenterapplication.Community.Community_GalleryActivity;
import com.example.lifelongeducationcenterapplication.Community.Community_NoticeActivity;
import com.example.lifelongeducationcenterapplication.Community.Community_QuestionAndAnswerActivity;
import com.example.lifelongeducationcenterapplication.Creditbankingsystem.CreditbanksystemAcademicScheduleActivity;
import com.example.lifelongeducationcenterapplication.Creditbankingsystem.CreditbanksystemActivity;
import com.example.lifelongeducationcenterapplication.Creditbankingsystem.CreditbanksystemAdmissionGuideActivity;
import com.example.lifelongeducationcenterapplication.Creditbankingsystem.CreditbanksystemEnrolmentActivity;
import com.example.lifelongeducationcenterapplication.Creditbankingsystem.CreditbanksystemMajorInformationActivity;
import com.example.lifelongeducationcenterapplication.Creditbankingsystem.CreditbanksystemreferenceRoomActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.AsanCityLinkageProcessActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.CertificatecourseActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.ForeignlanguagecourseActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.GeneralEducationCourseRecruitmentGuideActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.GeneralSecurityGuardCourseActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.LearnmoreaboutforeignlanguagecoursesActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.LiberalArtsCourseActivity;
import com.example.lifelongeducationcenterapplication.Intro.EducationalPurpose;
import com.example.lifelongeducationcenterapplication.Intro.Greetings;
import com.example.lifelongeducationcenterapplication.Intro.History;
import com.example.lifelongeducationcenterapplication.Intro.Organizational;
import com.example.lifelongeducationcenterapplication.Intro.SearchMap;
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_CourseDetailsActivity;
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_GradesVerificationActivity;
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_MemberInformationManagementActivity;
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_QuestionAndAnswerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class MainActivity extends AppCompatActivity {

    TextView DVtxtAccount, DVtxtIntro, DVtxtBank, DVtxtEdu, DVtxtCommunity, DVtxtMypage; //메뉴목록
    TextView DVtxtAccount_1,DVtxtAccount_2;//로그인, 회원가입
    TextView DVtxtIntro_1,DVtxtIntro_2,DVtxtIntro_3,DVtxtIntro_4,DVtxtIntro_5;//인사말씀, 발전과정 및 연혁, 교육목적 및 목표, 조직구성, 찾아오시는 길
    TextView DVtxtBank_1, DVtxtBank_2, DVtxtBank_3, DVtxtBank_4, DVtxtBank_5, DVtxtBank_6;//학점은행제란, 입학안내, 전공안내, 수강신청, 학사일정, 자료실
    TextView DVtxtEdu_1,DVtxtEdu_2,DVtxtEdu_3,DVtxtEdu_4,DVtxtEdu_5,DVtxtEdu_6;//모집안내, 외국어과정, 자격증과정, 교양과정, 아산시연계과정, 일반경비원과정
    TextView DVtxtCommunity_1,DVtxtCommunity_2,DVtxtCommunity_3,DVtxtCommunity_4;//공지사항, 1:1 질의응답, 서식자료실, 갤러리
    TextView DVtxtMypage_1,DVtxtMypage_2,DVtxtMypage_3,DVtxtMypage_4;//회원정보관리, 수강내역, 성적확인, 1:1 질문

    TextView link1, link2;

    TextView mainNotice;



    DrawerLayout drawerLayout;
    LinearLayout drawerView;

    Retrofit retrofit1;//httpclient library
    RemoteService rs1;//DB를 위한 인터페이스

    Retrofit retrofit2;//httpclient library
    RemoteService rs2;//DB를 위한 인터페이스


    List<Lecture> lectures; // 배열 객체 생성
    ListView listLecture;//리스트뷰

    List<Notice> notices;
    ListView listNotice;

    MyAdapter adapter;

    //MainLectureAdapter mainLectureAdapter; //어댑터





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            listLecture = findViewById(R.id.listLecture);

            drawerLayout = findViewById(R.id.drawerLayout);
            drawerView = findViewById(R.id.drawerView);

            DVtxtAccount = findViewById(R.id.DVtxtAccount);
            DVtxtIntro = findViewById(R.id.DVtxtIntro);
            DVtxtBank = findViewById(R.id.DVtxtBank);
            DVtxtEdu = findViewById(R.id.DVtxtEdu);
            DVtxtCommunity = findViewById(R.id.DVtxtCommunity);
            DVtxtMypage = findViewById(R.id.DVtxtMypage);
            link1 = (TextView) findViewById(R.id.email);
            link2 = (TextView)findViewById(R.id.private1);
            listNotice = (ListView) findViewById(R.id.listNoticed);


            txtfindviewid();//id정의
            //listset();
            clickTitleMenu();//메뉴설정
            menuChange();//메뉴이동



            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);//메뉴버튼생성



            // 아랫단 textview 웹뷰 링크 걸어주기
            Pattern pattern1 = Pattern.compile("이메일무단수집금지");
            Pattern pattern2 = Pattern.compile("개인정보처리방침");

            link1.setText("이메일무단수집금지           |");
            link2.setText("개인정보처리방침");

            Linkify.TransformFilter transformFilter = new Linkify.TransformFilter() {
                @Override
                public String transformUrl(Matcher matcher, String s) {
                    return "";
                }
            };

            Linkify.addLinks(link1, pattern1, "https://lily.sunmoon.ac.kr/Page/Etc/EmailPolicy.aspx", null, transformFilter);
            Linkify.addLinks(link2, pattern2, "https://lily.sunmoon.ac.kr/Page/Etc/Private.aspx", null, transformFilter);




            retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                    (GsonConverterFactory.create()).build();
            rs1 = retrofit1.create(RemoteService.class);

            retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                    (GsonConverterFactory.create()).build();
            rs2 = retrofit2.create(RemoteService.class);


            adapter = new MyAdapter();

            //mainLectureAdapter = new MainLectureAdapter();
            //listLecture.setAdapter(mainLectureAdapter);




        }




        @Override
        protected void onResume() {
            /*
            Call<List<MainLecture>> call = rs.listLecture();//call객체
            call.enqueue(new Callback<List<MainLecture>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<MainLecture>> call, Response<List<MainLecture>> response) {
                    lectures = response.body();
                    mainLectureAdapter.notifyDataSetChanged();
                    listLecture.setAdapter(mainLectureAdapter);
                }

                @Override
                public void onFailure(Call<List<MainLecture>> call, Throwable t) {
                    System.out.println("JSON 불러오기 실패" +call +" " + t);

                }
            });

             */

            Call<List<Notice>> call2 = rs2.notice();
            call2.enqueue(new Callback<List<Notice>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                    if(response.isSuccessful()){
                        notices = response.body();
                        adapter.notifyDataSetChanged();
                        listNotice.setAdapter(adapter);

                    }

                }

                @Override
                public void onFailure(Call<List<Notice>> call, Throwable t) {
                    System.out.println("공지사항 불러오기 실패" +call +" " + t);

                }
            });

            super.onResume();

        }



/*
    class MainLectureAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return lectures.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_mainlecture,null);


            TextView mainLectureName = convertView.findViewById(R.id.mainLectureName);//강좌명
            TextView mainLecturePeriod = convertView.findViewById(R.id.mainLecturePeriod);//교육기간
            TextView mainLectureProfessor = convertView.findViewById(R.id.mainLectureProfessor);//교수진
            TextView mainLectureTime = convertView.findViewById(R.id.mainLectureTime);//수업시간
            TextView mainLectureStudyfee = convertView.findViewById(R.id.mainLectureStudyfee);//학습비

            MainLecture mainLecture = lectures.get(position);

            mainLectureName.setText(mainLecture.getlectureName());
            mainLecturePeriod.setText(mainLecture.getlecturePeriod());
            mainLectureProfessor.setText(mainLecture.getlectureProfessor());
            mainLectureTime.setText(mainLecture.getlectureTime());
            mainLectureStudyfee.setText(mainLecture.getlectureStudyfee());
            return convertView;
        }
    }

 */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://메뉴를 클릭했을때 메뉴화면이 슬라이드 형식으로 나옴.
                drawerLayout.openDrawer(drawerView);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return notices.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_main_noticeed,null);
            Notice notice=  notices.get(position);
            mainNotice = (TextView)convertView.findViewById(R.id.main_noticeed);
            mainNotice.setText("・" + notice.getTitle());
            return convertView;
        }
    }



    public void txtfindviewid(){
        DVtxtAccount_1 = findViewById(R.id.DVtxtAccount_1);
        DVtxtAccount_2 = findViewById(R.id.DVtxtAccount_2);

        DVtxtIntro_1 = findViewById(R.id.DVtxtIntro_1);
        DVtxtIntro_2 = findViewById(R.id.DVtxtIntro_2);
        DVtxtIntro_3 = findViewById(R.id.DVtxtIntro_3);
        DVtxtIntro_4 = findViewById(R.id.DVtxtIntro_4);
        DVtxtIntro_5 = findViewById(R.id.DVtxtIntro_5);

        DVtxtBank_1 = findViewById(R.id.DVtxtBank_1);
        DVtxtBank_2 = findViewById(R.id.DVtxtBank_2);
        DVtxtBank_3 = findViewById(R.id.DVtxtBank_3);
        DVtxtBank_4 = findViewById(R.id.DVtxtBank_4);
        DVtxtBank_5 = findViewById(R.id.DVtxtBank_5);
        DVtxtBank_6 = findViewById(R.id.DVtxtBank_6);

        DVtxtEdu_1 = findViewById(R.id.DVtxtEdu_1);
        DVtxtEdu_2 = findViewById(R.id.DVtxtEdu_2);
        DVtxtEdu_3 = findViewById(R.id.DVtxtEdu_3);
        DVtxtEdu_4 = findViewById(R.id.DVtxtEdu_4);
        DVtxtEdu_5 = findViewById(R.id.DVtxtEdu_5);
        DVtxtEdu_6 = findViewById(R.id.DVtxtEdu_6);

        DVtxtCommunity_1 = findViewById(R.id.DVtxtCommunity_1);
        DVtxtCommunity_2 = findViewById(R.id.DVtxtCommunity_2);
        DVtxtCommunity_3 = findViewById(R.id.DVtxtCommunity_3);
        DVtxtCommunity_4 = findViewById(R.id.DVtxtCommunity_4);

        DVtxtMypage_1 = findViewById(R.id.DVtxtMypage_1);
        DVtxtMypage_2 = findViewById(R.id.DVtxtMypage_2);
        DVtxtMypage_3 = findViewById(R.id.DVtxtMypage_3);
        DVtxtMypage_4 = findViewById(R.id.DVtxtMypage_4);


    }


    public void clickTitleMenu(){//메뉴의 타이틀을 클릭할때 서브메뉴를 열고 닫을 수 있음.
        DVtxtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DVtxtAccount_1.getVisibility()==View.GONE){
                    DVtxtAccount_1.setVisibility(View.VISIBLE);
                    DVtxtAccount_2.setVisibility(View.VISIBLE);
                }else if(DVtxtAccount_1.getVisibility()==View.VISIBLE){
                    DVtxtAccount_1.setVisibility(View.GONE);
                    DVtxtAccount_2.setVisibility(View.GONE);
                }
            }
        });
        DVtxtIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DVtxtIntro_1.getVisibility()==View.GONE){
                    DVtxtIntro_1.setVisibility(View.VISIBLE);
                    DVtxtIntro_2.setVisibility(View.VISIBLE);
                    DVtxtIntro_3.setVisibility(View.VISIBLE);
                    DVtxtIntro_4.setVisibility(View.VISIBLE);
                    DVtxtIntro_5.setVisibility(View.VISIBLE);

                }else if(DVtxtIntro_1.getVisibility()==View.VISIBLE){
                    DVtxtIntro_1.setVisibility(View.GONE);
                    DVtxtIntro_2.setVisibility(View.GONE);
                    DVtxtIntro_3.setVisibility(View.GONE);
                    DVtxtIntro_4.setVisibility(View.GONE);
                    DVtxtIntro_5.setVisibility(View.GONE);
                }

            }
        });
        DVtxtBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DVtxtBank_1.getVisibility()==View.GONE){
                    DVtxtBank_1.setVisibility(View.VISIBLE);
                    DVtxtBank_2.setVisibility(View.VISIBLE);
                    DVtxtBank_3.setVisibility(View.VISIBLE);
                    DVtxtBank_4.setVisibility(View.VISIBLE);
                    DVtxtBank_5.setVisibility(View.VISIBLE);
                    DVtxtBank_6.setVisibility(View.VISIBLE);
                }else if(DVtxtBank_1.getVisibility()==View.VISIBLE){
                    DVtxtBank_1.setVisibility(View.GONE);
                    DVtxtBank_2.setVisibility(View.GONE);
                    DVtxtBank_3.setVisibility(View.GONE);
                    DVtxtBank_4.setVisibility(View.GONE);
                    DVtxtBank_5.setVisibility(View.GONE);
                    DVtxtBank_6.setVisibility(View.GONE);

                }
            }
        });
        DVtxtEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DVtxtEdu_1.getVisibility()==View.GONE){
                    DVtxtEdu_1.setVisibility(View.VISIBLE);
                    DVtxtEdu_2.setVisibility(View.VISIBLE);
                    DVtxtEdu_3.setVisibility(View.VISIBLE);
                    DVtxtEdu_4.setVisibility(View.VISIBLE);
                    DVtxtEdu_5.setVisibility(View.VISIBLE);
                    DVtxtEdu_6.setVisibility(View.VISIBLE);
                }else if(DVtxtEdu_1.getVisibility()==View.VISIBLE){
                    DVtxtEdu_1.setVisibility(View.GONE);
                    DVtxtEdu_2.setVisibility(View.GONE);
                    DVtxtEdu_3.setVisibility(View.GONE);
                    DVtxtEdu_4.setVisibility(View.GONE);
                    DVtxtEdu_5.setVisibility(View.GONE);
                    DVtxtEdu_6.setVisibility(View.GONE);
                }


            }
        });
        DVtxtCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DVtxtCommunity_1.getVisibility()==View.GONE){
                    DVtxtCommunity_1.setVisibility(View.VISIBLE);
                    DVtxtCommunity_2.setVisibility(View.VISIBLE);
                    DVtxtCommunity_3.setVisibility(View.VISIBLE);
                    DVtxtCommunity_4.setVisibility(View.VISIBLE);
                }else if(DVtxtCommunity_1.getVisibility()==View.VISIBLE){
                    DVtxtCommunity_1.setVisibility(View.GONE);
                    DVtxtCommunity_2.setVisibility(View.GONE);
                    DVtxtCommunity_3.setVisibility(View.GONE);
                    DVtxtCommunity_4.setVisibility(View.GONE);
                }

            }
        });
        DVtxtMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DVtxtMypage_1.getVisibility()==View.GONE){
                    DVtxtMypage_1.setVisibility(View.VISIBLE);
                    DVtxtMypage_2.setVisibility(View.VISIBLE);
                    DVtxtMypage_3.setVisibility(View.VISIBLE);
                    DVtxtMypage_4.setVisibility(View.VISIBLE);
                }else if(DVtxtMypage_1.getVisibility()==View.VISIBLE){
                    DVtxtMypage_1.setVisibility(View.GONE);
                    DVtxtMypage_2.setVisibility(View.GONE);
                    DVtxtMypage_3.setVisibility(View.GONE);
                    DVtxtMypage_4.setVisibility(View.GONE);
                }

            }
        });
    }

    public void menuChange(){//메뉴이동


        Button.OnClickListener mClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;

                switch (v.getId()){
                    case R.id.DVtxtAccount_1:
                        //Toast.makeText(getApplicationContext(),"1-1번",Toast.LENGTH_SHORT).show();
                        System.out.println("확인함 로그인");
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Login.class);
                        startActivityForResult(intent,1);
                        //화면이동(intent)
                        break;
                    case R.id.DVtxtAccount_2:
                        //Toast.makeText(MainActivity.this,"1-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, SignActivity.class);
                        startActivityForResult(intent,2);
                        break;
                    case R.id.DVtxtIntro_1:
                        //Toast.makeText(MainActivity.this,"2-1번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Greetings.class);

                        //인사말씀 액티비티
                        startActivityForResult(intent,3);

                        break;
                    case R.id.DVtxtIntro_2:
                        //Toast.makeText(MainActivity.this,"2-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, History.class);

                        //발전과정 및 연혁
                        startActivityForResult(intent,4);

                        break;
                    case R.id.DVtxtIntro_3:
                        //Toast.makeText(MainActivity.this,"2-3번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, EducationalPurpose.class);

                        startActivityForResult(intent,5);
                        //교육목적 및 목표

                        break;
                    case R.id.DVtxtIntro_4:
                        //Toast.makeText(MainActivity.this,"2-4번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Organizational.class);

                        startActivityForResult(intent,6);
                        //조직 구성

                        break;
                    case R.id.DVtxtIntro_5:
                        //Toast.makeText(MainActivity.this,"2-5번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, SearchMap.class);

                        startActivityForResult(intent,28);
                        //교육원 찾아가는 지도

                        break;

                    case R.id.DVtxtBank_1:
                        //Toast.makeText(MainActivity.this,"3-1번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemActivity.class);
                        startActivityForResult(intent,8);
                        //학점은행제란
                        break;
                    case R.id.DVtxtBank_2:
                        //Toast.makeText(MainActivity.this,"3-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemAdmissionGuideActivity.class);
                        startActivityForResult(intent,9);
                        //학점은행제 입학 안내
                        break;
                    case R.id.DVtxtBank_3:
                        //Toast.makeText(MainActivity.this,"3-3번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemMajorInformationActivity.class);
                        startActivityForResult(intent,10);
                        //학점은행제 전공안내
                        break;
                    case R.id.DVtxtBank_4:
                        //Toast.makeText(MainActivity.this,"3-4번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemEnrolmentActivity.class);
                        startActivityForResult(intent,11);
                        //학점은행제 수강신청
                        break;
                    case R.id.DVtxtBank_5:
                        //Toast.makeText(MainActivity.this,"3-5번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemAcademicScheduleActivity.class);
                        startActivityForResult(intent,12);
                        //학점은행제 스케줄
                        break;
                    case R.id.DVtxtBank_6:
                        //Toast.makeText(MainActivity.this,"3-6번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemreferenceRoomActivity.class);
                        startActivityForResult(intent,13);
                        //학점은행제 자료실
                        break;
                    case R.id.DVtxtEdu_1:  //일반교육과정
                        //Toast.makeText(MainActivity.this,"4-1번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, GeneralEducationCourseRecruitmentGuideActivity.class);
                        startActivityForResult(intent,14);
                        //일반교육과정 모집안내
                        break;
                    case R.id.DVtxtEdu_2:
                        //Toast.makeText(MainActivity.this,"4-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, ForeignlanguagecourseActivity.class);
                        startActivityForResult(intent,15);
                        //외국어 과정
                        break;
                    case R.id.DVtxtEdu_3:
                        //Toast.makeText(MainActivity.this,"4-3번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CertificatecourseActivity.class);
                        startActivityForResult(intent,16);
                        //자격증 과정
                        break;
                    case R.id.DVtxtEdu_4:
                        //Toast.makeText(MainActivity.this,"4-4번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, LiberalArtsCourseActivity.class);
                        startActivityForResult(intent,17);
                        //교양 과정
                        break;
                    case R.id.DVtxtEdu_5:
                        //Toast.makeText(MainActivity.this,"4-5번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, AsanCityLinkageProcessActivity.class);
                        startActivityForResult(intent,18);
                        //아산시 연계과정
                        break;
                    case R.id.DVtxtEdu_6:
                        //Toast.makeText(MainActivity.this,"4-6번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, GeneralSecurityGuardCourseActivity.class);
                        startActivityForResult(intent,19);
                        //일반 경비원 과정
                        break;
                    case R.id.DVtxtCommunity_1:
                        //Toast.makeText(MainActivity.this,"5-1번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_NoticeActivity.class);
                        startActivityForResult(intent,20);
                        //커뮤니티 공지사항
                        break;
                    case R.id.DVtxtCommunity_2:
                        //Toast.makeText(MainActivity.this,"5-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_QuestionAndAnswerActivity.class);
                        startActivityForResult(intent,21);
                        //커뮤니티 1:1질의 응답
                        break;
                    case R.id.DVtxtCommunity_3:
                        //Toast.makeText(MainActivity.this,"5-3번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_GalleryActivity.class);
                        startActivityForResult(intent,22);
                        //커뮤니티 갤러리
                        break;
                    case R.id.DVtxtCommunity_4:
                        //Toast.makeText(MainActivity.this,"5-4번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_FormattingRoomActivity.class);
                        startActivityForResult(intent,23);
                        //커뮤니티 서식 자료실
                        break;
                    case R.id.DVtxtMypage_1:
                        //Toast.makeText(MainActivity.this,"6-1번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, MyPage_MemberInformationManagementActivity.class);
                        startActivityForResult(intent,24);
                        //마이페이지 회원정보 관리
                        break;
                    case R.id.DVtxtMypage_2:
                        //Toast.makeText(MainActivity.this,"6-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this,  MyPage_CourseDetailsActivity.class);
                        startActivityForResult(intent,25);
                        //마이페이지 수강내역
                        break;
                    case R.id.DVtxtMypage_3:
                        //Toast.makeText(MainActivity.this,"6-3번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, MyPage_GradesVerificationActivity.class);
                        startActivityForResult(intent,26);
                        //마이페이지 성적확인
                        break;
                    case R.id.DVtxtMypage_4:
                        //Toast.makeText(MainActivity.this,"6-4번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, MyPage_QuestionAndAnswerActivity.class);
                        startActivityForResult(intent,27);
                        //마이페이지 질의 응답
                        break;
                }
            }
        };

        DVtxtAccount_1.setOnClickListener(mClick);
        DVtxtAccount_2.setOnClickListener(mClick);

        DVtxtIntro_1.setOnClickListener(mClick);
        DVtxtIntro_2.setOnClickListener(mClick);
        DVtxtIntro_3.setOnClickListener(mClick);
        DVtxtIntro_4.setOnClickListener(mClick);
        DVtxtIntro_5.setOnClickListener(mClick);

        DVtxtBank_1.setOnClickListener(mClick);
        DVtxtBank_2.setOnClickListener(mClick);
        DVtxtBank_3.setOnClickListener(mClick);
        DVtxtBank_4.setOnClickListener(mClick);
        DVtxtBank_5.setOnClickListener(mClick);
        DVtxtBank_6.setOnClickListener(mClick);

        DVtxtEdu_1.setOnClickListener(mClick);
        DVtxtEdu_2.setOnClickListener(mClick);
        DVtxtEdu_3.setOnClickListener(mClick);
        DVtxtEdu_4.setOnClickListener(mClick);
        DVtxtEdu_5.setOnClickListener(mClick);
        DVtxtEdu_6.setOnClickListener(mClick);

        DVtxtCommunity_1.setOnClickListener(mClick);
        DVtxtCommunity_2.setOnClickListener(mClick);
        DVtxtCommunity_3.setOnClickListener(mClick);
        DVtxtCommunity_4.setOnClickListener(mClick);

        DVtxtMypage_1.setOnClickListener(mClick);
        DVtxtMypage_2.setOnClickListener(mClick);
        DVtxtMypage_3.setOnClickListener(mClick);
        DVtxtMypage_4.setOnClickListener(mClick);


    }

}

