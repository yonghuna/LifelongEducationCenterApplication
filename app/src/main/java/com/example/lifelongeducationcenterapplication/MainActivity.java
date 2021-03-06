package com.example.lifelongeducationcenterapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Toast;


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

    TextView DVtxtAccount, DVtxtIntro, DVtxtBank, DVtxtEdu, DVtxtCommunity, DVtxtMypage; //????????????
    TextView DVtxtAccount_1,DVtxtAccount_2;//?????????, ????????????
    TextView DVtxtIntro_1,DVtxtIntro_2,DVtxtIntro_3,DVtxtIntro_4,DVtxtIntro_5;//????????????, ???????????? ??? ??????, ???????????? ??? ??????, ????????????, ??????????????? ???
    TextView DVtxtBank_1, DVtxtBank_2, DVtxtBank_3, DVtxtBank_4, DVtxtBank_5, DVtxtBank_6;//??????????????????, ????????????, ????????????, ????????????, ????????????, ?????????
    TextView DVtxtEdu_1,DVtxtEdu_2,DVtxtEdu_3,DVtxtEdu_4,DVtxtEdu_5,DVtxtEdu_6;//????????????, ???????????????, ???????????????, ????????????, ?????????????????????, ?????????????????????
    TextView DVtxtCommunity_1,DVtxtCommunity_2,DVtxtCommunity_3,DVtxtCommunity_4;//????????????, 1:1 ????????????, ???????????????, ?????????
    TextView DVtxtMypage_1,DVtxtMypage_2,DVtxtMypage_3,DVtxtMypage_4;//??????????????????, ????????????, ????????????, 1:1 ??????

    TextView link1, link2;

    TextView mainNotice;

    TextView login, name;
    DrawerLayout drawerLayout;
    LinearLayout drawerView;

    Retrofit retrofit1;//httpclient library
    RemoteService rs1;//DB??? ?????? ???????????????

    Retrofit retrofit2;//httpclient library
    RemoteService rs2;//DB??? ?????? ???????????????


    List<Lecture> lectures; // ?????? ?????? ??????
    ListView listLecture;//????????????

    List<Notice> notices;
    ListView listNotice;

    MyAdapter adapter;

    //MainLectureAdapter mainLectureAdapter; //?????????





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
            login = (TextView) findViewById(R.id.login);
            name = (TextView) findViewById(R.id.name);

            txtfindviewid();//id??????
            //listset();
            clickTitleMenu();//????????????
            menuChange();//????????????



            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);//??????????????????



            // ????????? textview ?????? ?????? ????????????
            Pattern pattern1 = Pattern.compile("???????????????????????????");
            Pattern pattern2 = Pattern.compile("????????????????????????");

            link1.setText("???????????????????????????           |");
            link2.setText("????????????????????????");

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



            // ?????? ????????? ???????????? ??????
            if(StaticId.id == "" || StaticId.id == null){
                login.setText("?????????");
                DVtxtAccount_1.setText("?????????");
            }else{
                name.setText(StaticId.name);
                login.setText("????????????");
                DVtxtAccount_1.setText("????????????");
            }

            //????????? ????????? 
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(StaticId.id == "" || StaticId.id == null){
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }else{
                        logout();
                        Toast.makeText(getApplicationContext(),"???????????? ???????????????.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                }
            });
            //mainLectureAdapter = new MainLectureAdapter();
            //listLecture.setAdapter(mainLectureAdapter);




        }




        @Override
        protected void onResume() {
            /*
            Call<List<MainLecture>> call = rs.listLecture();//call??????
            call.enqueue(new Callback<List<MainLecture>>() {//enqueue ????????? ??????
                @Override
                public void onResponse(Call<List<MainLecture>> call, Response<List<MainLecture>> response) {
                    lectures = response.body();
                    mainLectureAdapter.notifyDataSetChanged();
                    listLecture.setAdapter(mainLectureAdapter);
                }

                @Override
                public void onFailure(Call<List<MainLecture>> call, Throwable t) {
                    System.out.println("JSON ???????????? ??????" +call +" " + t);

                }
            });

             */

            Call<List<Notice>> call2 = rs2.notice();
            call2.enqueue(new Callback<List<Notice>>() {//enqueue ????????? ??????
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
                    System.out.println("???????????? ???????????? ??????" +call +" " + t);

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


            TextView mainLectureName = convertView.findViewById(R.id.mainLectureName);//?????????
            TextView mainLecturePeriod = convertView.findViewById(R.id.mainLecturePeriod);//????????????
            TextView mainLectureProfessor = convertView.findViewById(R.id.mainLectureProfessor);//?????????
            TextView mainLectureTime = convertView.findViewById(R.id.mainLectureTime);//????????????
            TextView mainLectureStudyfee = convertView.findViewById(R.id.mainLectureStudyfee);//?????????

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
    public void logout(){
        StaticId.id="";
        StaticId.course="";
        StaticId.name="";
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://????????? ??????????????? ??????????????? ???????????? ???????????? ??????.
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
            mainNotice.setText("???" + notice.getTitle());
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


    public void clickTitleMenu(){//????????? ???????????? ???????????? ??????????????? ?????? ?????? ??? ??????.
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

    public void menuChange(){//????????????


        Button.OnClickListener mClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;

                switch (v.getId()){
                    case R.id.DVtxtAccount_1:
                        //Toast.makeText(getApplicationContext(),"1-1???",Toast.LENGTH_SHORT).show();
                        System.out.println("????????? ?????????");
                        drawerLayout.closeDrawer(drawerView);
                        if(StaticId.id == "" || StaticId.id == null){
                            intent = new Intent(MainActivity.this, Login.class);
                            startActivityForResult(intent,1);
                        }else{
                            logout();
                            Toast.makeText(getApplicationContext(),"???????????? ???????????????.",Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent1);
                        }

                        //????????????(intent)
                        break;
                    case R.id.DVtxtAccount_2:
                        //Toast.makeText(MainActivity.this,"1-2???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, SignActivity.class);
                        startActivityForResult(intent,2);
                        break;
                    case R.id.DVtxtIntro_1:
                        //Toast.makeText(MainActivity.this,"2-1???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Greetings.class);

                        //???????????? ????????????
                        startActivityForResult(intent,3);

                        break;
                    case R.id.DVtxtIntro_2:
                        //Toast.makeText(MainActivity.this,"2-2???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, History.class);

                        //???????????? ??? ??????
                        startActivityForResult(intent,4);

                        break;
                    case R.id.DVtxtIntro_3:
                        //Toast.makeText(MainActivity.this,"2-3???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, EducationalPurpose.class);

                        startActivityForResult(intent,5);
                        //???????????? ??? ??????

                        break;
                    case R.id.DVtxtIntro_4:
                        //Toast.makeText(MainActivity.this,"2-4???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Organizational.class);

                        startActivityForResult(intent,6);
                        //?????? ??????

                        break;
                    case R.id.DVtxtIntro_5:
                        //Toast.makeText(MainActivity.this,"2-5???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, SearchMap.class);

                        startActivityForResult(intent,28);
                        //????????? ???????????? ??????

                        break;

                    case R.id.DVtxtBank_1:
                        //Toast.makeText(MainActivity.this,"3-1???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemActivity.class);
                        startActivityForResult(intent,8);
                        //??????????????????
                        break;
                    case R.id.DVtxtBank_2:
                        //Toast.makeText(MainActivity.this,"3-2???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemAdmissionGuideActivity.class);
                        startActivityForResult(intent,9);
                        //??????????????? ?????? ??????
                        break;
                    case R.id.DVtxtBank_3:
                        //Toast.makeText(MainActivity.this,"3-3???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemMajorInformationActivity.class);
                        startActivityForResult(intent,10);
                        //??????????????? ????????????
                        break;
                    case R.id.DVtxtBank_4:
                        //Toast.makeText(MainActivity.this,"3-4???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemEnrolmentActivity.class);
                        startActivityForResult(intent,11);
                        //??????????????? ????????????
                        break;
                    case R.id.DVtxtBank_5:
                        //Toast.makeText(MainActivity.this,"3-5???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemAcademicScheduleActivity.class);
                        startActivityForResult(intent,12);
                        //??????????????? ?????????
                        break;
                    case R.id.DVtxtBank_6:
                        //Toast.makeText(MainActivity.this,"3-6???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemreferenceRoomActivity.class);
                        startActivityForResult(intent,13);
                        //??????????????? ?????????
                        break;
                    case R.id.DVtxtEdu_1:  //??????????????????
                        //Toast.makeText(MainActivity.this,"4-1???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, GeneralEducationCourseRecruitmentGuideActivity.class);
                        startActivityForResult(intent,14);
                        //?????????????????? ????????????
                        break;
                    case R.id.DVtxtEdu_2:
                        //Toast.makeText(MainActivity.this,"4-2???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, ForeignlanguagecourseActivity.class);
                        startActivityForResult(intent,15);
                        //????????? ??????
                        break;
                    case R.id.DVtxtEdu_3:
                        //Toast.makeText(MainActivity.this,"4-3???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CertificatecourseActivity.class);
                        startActivityForResult(intent,16);
                        //????????? ??????
                        break;
                    case R.id.DVtxtEdu_4:
                        //Toast.makeText(MainActivity.this,"4-4???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, LiberalArtsCourseActivity.class);
                        startActivityForResult(intent,17);
                        //?????? ??????
                        break;
                    case R.id.DVtxtEdu_5:
                        //Toast.makeText(MainActivity.this,"4-5???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, AsanCityLinkageProcessActivity.class);
                        startActivityForResult(intent,18);
                        //????????? ????????????
                        break;
                    case R.id.DVtxtEdu_6:
                        //Toast.makeText(MainActivity.this,"4-6???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, GeneralSecurityGuardCourseActivity.class);
                        startActivityForResult(intent,19);
                        //?????? ????????? ??????
                        break;
                    case R.id.DVtxtCommunity_1:
                        //Toast.makeText(MainActivity.this,"5-1???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_NoticeActivity.class);
                        startActivityForResult(intent,20);
                        //???????????? ????????????
                        break;
                    case R.id.DVtxtCommunity_2:
                        //Toast.makeText(MainActivity.this,"5-2???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_QuestionAndAnswerActivity.class);
                        startActivityForResult(intent,21);
                        //???????????? 1:1?????? ??????
                        break;
                    case R.id.DVtxtCommunity_3:
                        //Toast.makeText(MainActivity.this,"5-3???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_GalleryActivity.class);
                        startActivityForResult(intent,22);
                        //???????????? ?????????
                        break;
                    case R.id.DVtxtCommunity_4:
                        //Toast.makeText(MainActivity.this,"5-4???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_FormattingRoomActivity.class);
                        startActivityForResult(intent,23);
                        //???????????? ?????? ?????????
                        break;
                    case R.id.DVtxtMypage_1:
                        //Toast.makeText(MainActivity.this,"6-1???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, MyPage_MemberInformationManagementActivity.class);
                        startActivityForResult(intent,24);
                        //??????????????? ???????????? ??????
                        break;
                    case R.id.DVtxtMypage_2:
                        //Toast.makeText(MainActivity.this,"6-2???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this,  MyPage_CourseDetailsActivity.class);
                        startActivityForResult(intent,25);
                        //??????????????? ????????????
                        break;
                    case R.id.DVtxtMypage_3:
                        //Toast.makeText(MainActivity.this,"6-3???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, MyPage_GradesVerificationActivity.class);
                        startActivityForResult(intent,26);
                        //??????????????? ????????????
                        break;
                    case R.id.DVtxtMypage_4:
                        //Toast.makeText(MainActivity.this,"6-4???",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, MyPage_QuestionAndAnswerActivity.class);
                        startActivityForResult(intent,27);
                        //??????????????? ?????? ??????
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

