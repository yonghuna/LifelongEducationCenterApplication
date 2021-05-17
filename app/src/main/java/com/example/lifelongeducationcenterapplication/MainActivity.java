package com.example.lifelongeducationcenterapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
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
import com.example.lifelongeducationcenterapplication.Community.Community_NoticeContentActivity;
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
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_MemberInfomation_Bank_Activity;
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_GradesVerificationActivity;
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_MemberInformationManagementActivity;
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_QuestionAndAnswerActivity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.android.gms.cloudmessaging.CloudMessagingReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.messaging.FirebaseMessaging;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "token";
    TextView DVtxtAccount, DVtxtIntro, DVtxtBank, DVtxtEdu, DVtxtCommunity, DVtxtMypage; //메뉴목록
    TextView DVtxtAccount_1, DVtxtAccount_2;//로그인, 회원가입
    TextView DVtxtIntro_1, DVtxtIntro_2, DVtxtIntro_3, DVtxtIntro_4, DVtxtIntro_5;//인사말씀, 발전과정 및 연혁, 교육목적 및 목표, 조직구성, 찾아오시는 길
    TextView DVtxtBank_1, DVtxtBank_2, DVtxtBank_3, DVtxtBank_4, DVtxtBank_5, DVtxtBank_6;//학점은행제란, 입학안내, 전공안내, 수강신청, 학사일정, 자료실
    TextView DVtxtEdu_1, DVtxtEdu_2, DVtxtEdu_3, DVtxtEdu_4, DVtxtEdu_5, DVtxtEdu_6;//모집안내, 외국어과정, 자격증과정, 교양과정, 아산시연계과정, 일반경비원과정
    TextView DVtxtCommunity_1, DVtxtCommunity_2, DVtxtCommunity_3, DVtxtCommunity_4;//공지사항, 1:1 질의응답, 서식자료실, 갤러리
    TextView DVtxtMypage_1, DVtxtMypage_2, DVtxtMypage_3, DVtxtMypage_4;//회원정보관리, 수강내역, 성적확인, 1:1 질문

    TextView link1, link2;

    TextView mainNotice, mainLecture;
    TextView mainLectureTitle2, mainLectureDivision2, mainLectureDate2, mainLectureText;

    TextView login, name;
    DrawerLayout drawerLayout;
    LinearLayout drawerView;

    Retrofit retrofit1;//httpclient library
    RemoteService rs1;//DB를 위한 인터페이스

    Retrofit retrofit2;//httpclient library
    RemoteService rs2;//DB를 위한 인터페이스

    Retrofit retrofit3;//httpclient library
    RemoteService rs3;//DB를 위한 인터페이스



    List<Enrollment> enrollments; // 배열 객체 생성
    ListView listLecture;//리스트뷰

    List<Notice> notices;
    ListView listNotice;

    List<Lecture> lectures;

    MyAdapter adapter;
    MainLectureAdapter mainLectureAdapter; //어댑터
    RecommendedLecture recommendedLecture;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("홈"); //홈

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
        link2 = (TextView) findViewById(R.id.private1);
        listNotice = (ListView) findViewById(R.id.listNoticed);
        login = (TextView) findViewById(R.id.login);
        name = (TextView) findViewById(R.id.name);
        mainLectureText = (TextView) findViewById(R.id.mainLecture);
        firebase();
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

        retrofit3 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs3 = retrofit3.create(RemoteService.class);

        adapter = new MyAdapter();
        mainLectureAdapter = new MainLectureAdapter();
        recommendedLecture = new RecommendedLecture();
        // 상단 로그인 로그아웃 체크
        // 비로그인시 추천강좌 로그인시 수강강좌
        if (StaticId.id == "" || StaticId.id == null) {
            login.setText("로그인");
            DVtxtAccount_1.setText("로그인");
            mainLectureText.setText("추천강좌");
           // recommendedLecture = new RecommendedLecture();

        } else {
            name.setText(StaticId.name);
            login.setText("로그아웃");
            DVtxtAccount_1.setText("로그아웃");
           // mainLectureAdapter = new MainLectureAdapter(); // lecture 올리기

        }

        //로그인 선택시
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StaticId.id == "" || StaticId.id == null) {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                } else {
                    logout();
                    Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });




    }

    @Override   //액셔바 홈버튼
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onResume() {

        Call<List<Notice>> call2 = rs2.notice();
        call2.enqueue(new Callback<List<Notice>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if (response.isSuccessful()) {
                    notices = response.body();
                    adapter.notifyDataSetChanged();
                    listNotice.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                System.out.println("공지사항 불러오기 실패" + call + " " + t);

            }
        });

        if(StaticId.id == null || StaticId.id == ""){
            Call<List<Lecture>> call = rs3.mainLecture();//call객체
            call.enqueue(new Callback<List<Lecture>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<Lecture>> call, Response<List<Lecture>> response) {

                    lectures = response.body();
                    recommendedLecture.notifyDataSetChanged();
                    listLecture.setAdapter(recommendedLecture);
                }

                @Override
                public void onFailure(Call<List<Lecture>> call, Throwable t) {
                    System.out.println("추천 강좌 JSON 불러오기 실패" + call + " " + t);
                }
            });

        }else{
            Call<List<Enrollment>> call = rs1.enrollment(StaticId.id);//call객체
            call.enqueue(new Callback<List<Enrollment>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<Enrollment>> call, Response<List<Enrollment>> response) {
                    enrollments = response.body();
                    mainLectureAdapter.notifyDataSetChanged();
                    listLecture.setAdapter(mainLectureAdapter);
                }

                @Override
                public void onFailure(Call<List<Enrollment>> call, Throwable t) {
                    System.out.println("JSON 불러오기 실패" + call + " " + t);

                }
            });
        }


        super.onResume();

    }


    public void logout() {
        StaticId.id = null;
        StaticId.course = null;
        StaticId.name = null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://메뉴를 클릭했을때 메뉴화면이 슬라이드 형식으로 나옴.
                drawerLayout.openDrawer(drawerView);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 공지사항
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
            convertView = getLayoutInflater().inflate(R.layout.item_main_noticeed, null);
            Notice notice = notices.get(position);
            mainNotice = (TextView) convertView.findViewById(R.id.main_noticeed);
            mainNotice.setText("・" + notice.getTitle());

            mainNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Community_NoticeContentActivity.class);
                    intent.putExtra("number", notice.getNumber());
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }

    // 수강 강좌
    class MainLectureAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return enrollments.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_mainlecture2, null);
            Enrollment enrollment = enrollments.get(position);
            System.out.println("확인함 로그인" + enrollment.getName());

            linearLayout = (LinearLayout) convertView.findViewById(R.id.main);
            mainLectureTitle2 = (TextView) convertView.findViewById(R.id.mainLectureName);
            mainLectureDivision2 = (TextView) convertView.findViewById(R.id.mainLectureCoursename);
            mainLectureDate2 = (TextView) convertView.findViewById(R.id.mainLecturePeriod);


            mainLectureTitle2.setText(enrollment.getName());
            mainLectureDivision2.setText(enrollment.getDivision());
            mainLectureDate2.setText(enrollment.getStartDate() + "~" + enrollment.getEndDate());
            System.out.println(enrollment.getDivision() + " 1231232i1yug3y21hg3 hadsgfhjasdf");
            if (enrollment.getName().isEmpty()) {
                mainLectureTitle2.setText("・ 수강 신청한 강좌가 없습니다.");
            }else{
                mainLectureTitle2.setText("・" + enrollment.getName());
            }

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), LearnmoreaboutforeignlanguagecoursesActivity.class);
                    intent.putExtra("number", enrollment.getSubjectnumber());
                    intent.putExtra("info", "myPage");
                    // 해야되는 부분 수강신청을 위해 해야됨
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }

    // 추천강좌
    class RecommendedLecture extends BaseAdapter {

        @Override
        public int getCount() {
            return lectures.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_mainlecture2, null);
            Lecture lecture = lectures.get(position);
            linearLayout = (LinearLayout) convertView.findViewById(R.id.main);
            mainLectureTitle2 = (TextView) convertView.findViewById(R.id.mainLectureName);
            mainLectureDivision2 = (TextView) convertView.findViewById(R.id.mainLectureCoursename);
            mainLectureDate2 = (TextView) convertView.findViewById(R.id.mainLecturePeriod);

            mainLectureTitle2.setText(lecture.getName());
            mainLectureDivision2.setText(lecture.getDivision());
            mainLectureDate2.setText(lecture.getStartDate() + "~" + lecture.getEndDate());

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LearnmoreaboutforeignlanguagecoursesActivity.class);
                    intent.putExtra("number", lecture.getNumber());
                    intent.putExtra("info", "메인임");
                    // 해야되는 부분 수강신청을 위해 해야됨
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }

    public void firebase(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.e(TAG,"Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void txtfindviewid() {
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


    public void clickTitleMenu() {//메뉴의 타이틀을 클릭할때 서브메뉴를 열고 닫을 수 있음.
        DVtxtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DVtxtAccount_1.getVisibility() == View.GONE) {
                    DVtxtAccount_1.setVisibility(View.VISIBLE);
                    DVtxtAccount_2.setVisibility(View.VISIBLE);
                } else if (DVtxtAccount_1.getVisibility() == View.VISIBLE) {
                    DVtxtAccount_1.setVisibility(View.GONE);
                    DVtxtAccount_2.setVisibility(View.GONE);
                }
            }
        });
        DVtxtIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DVtxtIntro_1.getVisibility() == View.GONE) {
                    DVtxtIntro_1.setVisibility(View.VISIBLE);
                    DVtxtIntro_2.setVisibility(View.VISIBLE);
                    DVtxtIntro_3.setVisibility(View.VISIBLE);
                    DVtxtIntro_4.setVisibility(View.VISIBLE);
                    DVtxtIntro_5.setVisibility(View.VISIBLE);

                } else if (DVtxtIntro_1.getVisibility() == View.VISIBLE) {
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
                if (DVtxtBank_1.getVisibility() == View.GONE) {
                    DVtxtBank_1.setVisibility(View.VISIBLE);
                    DVtxtBank_2.setVisibility(View.VISIBLE);
                    DVtxtBank_3.setVisibility(View.VISIBLE);
                    DVtxtBank_4.setVisibility(View.VISIBLE);
                    DVtxtBank_5.setVisibility(View.VISIBLE);
                    DVtxtBank_6.setVisibility(View.VISIBLE);
                } else if (DVtxtBank_1.getVisibility() == View.VISIBLE) {
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
                if (DVtxtEdu_1.getVisibility() == View.GONE) {
                    DVtxtEdu_1.setVisibility(View.VISIBLE);
                    DVtxtEdu_2.setVisibility(View.VISIBLE);
                    DVtxtEdu_3.setVisibility(View.VISIBLE);
                    DVtxtEdu_4.setVisibility(View.VISIBLE);
                    DVtxtEdu_5.setVisibility(View.VISIBLE);
                    DVtxtEdu_6.setVisibility(View.VISIBLE);
                } else if (DVtxtEdu_1.getVisibility() == View.VISIBLE) {
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
                if (DVtxtCommunity_1.getVisibility() == View.GONE) {
                    DVtxtCommunity_1.setVisibility(View.VISIBLE);
                    DVtxtCommunity_2.setVisibility(View.VISIBLE);
                    DVtxtCommunity_3.setVisibility(View.VISIBLE);
                    DVtxtCommunity_4.setVisibility(View.VISIBLE);
                } else if (DVtxtCommunity_1.getVisibility() == View.VISIBLE) {
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
                if (DVtxtMypage_1.getVisibility() == View.GONE) {
                    DVtxtMypage_1.setVisibility(View.VISIBLE);
                    DVtxtMypage_2.setVisibility(View.VISIBLE);
                    DVtxtMypage_3.setVisibility(View.VISIBLE);
                    DVtxtMypage_4.setVisibility(View.VISIBLE);
                } else if (DVtxtMypage_1.getVisibility() == View.VISIBLE) {
                    DVtxtMypage_1.setVisibility(View.GONE);
                    DVtxtMypage_2.setVisibility(View.GONE);
                    DVtxtMypage_3.setVisibility(View.GONE);
                    DVtxtMypage_4.setVisibility(View.GONE);
                }

            }
        });
    }


    public void menuChange() {//메뉴이동


        Button.OnClickListener mClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;

                switch (v.getId()) {
                    case R.id.DVtxtAccount_1:
                        //Toast.makeText(getApplicationContext(),"1-1번",Toast.LENGTH_SHORT).show();
                        System.out.println("확인함 로그인");
                        drawerLayout.closeDrawer(drawerView);
                        if (StaticId.id == "" || StaticId.id == null) {
                            intent = new Intent(MainActivity.this, Login.class);
                            startActivityForResult(intent, 1);
                        } else {
                            logout();
                            Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent1);
                        }

                        //화면이동(intent)
                        break;
                    case R.id.DVtxtAccount_2:
                        //Toast.makeText(MainActivity.this,"1-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, SignActivity.class);
                        startActivityForResult(intent, 2);
                        break;
                    case R.id.DVtxtIntro_1:
                        //Toast.makeText(MainActivity.this,"2-1번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Greetings.class);

                        //인사말씀 액티비티
                        startActivityForResult(intent, 3);

                        break;
                    case R.id.DVtxtIntro_2:
                        //Toast.makeText(MainActivity.this,"2-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, History.class);

                        //발전과정 및 연혁
                        startActivityForResult(intent, 4);

                        break;
                    case R.id.DVtxtIntro_3:
                        //Toast.makeText(MainActivity.this,"2-3번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, EducationalPurpose.class);

                        startActivityForResult(intent, 5);
                        //교육목적 및 목표

                        break;
                    case R.id.DVtxtIntro_4:
                        //Toast.makeText(MainActivity.this,"2-4번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Organizational.class);

                        startActivityForResult(intent, 6);
                        //조직 구성

                        break;
                    case R.id.DVtxtIntro_5:
                        //Toast.makeText(MainActivity.this,"2-5번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, SearchMap.class);

                        startActivityForResult(intent, 28);
                        //교육원 찾아가는 지도

                        break;

                    case R.id.DVtxtBank_1:
                        //Toast.makeText(MainActivity.this,"3-1번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemActivity.class);
                        startActivityForResult(intent, 8);
                        //학점은행제란
                        break;
                    case R.id.DVtxtBank_2:
                        //Toast.makeText(MainActivity.this,"3-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemAdmissionGuideActivity.class);
                        startActivityForResult(intent, 9);
                        //학점은행제 입학 안내
                        break;
                    case R.id.DVtxtBank_3:
                        //Toast.makeText(MainActivity.this,"3-3번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemMajorInformationActivity.class);
                        startActivityForResult(intent, 10);
                        //학점은행제 전공안내
                        break;
                    case R.id.DVtxtBank_4:
                        //Toast.makeText(MainActivity.this,"3-4번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemEnrolmentActivity.class);
                        startActivityForResult(intent, 11);
                        //학점은행제 수강신청
                        break;
                    case R.id.DVtxtBank_5:
                        //Toast.makeText(MainActivity.this,"3-5번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemAcademicScheduleActivity.class);
                        startActivityForResult(intent, 12);
                        //학점은행제 스케줄
                        break;
                    case R.id.DVtxtBank_6:
                        //Toast.makeText(MainActivity.this,"3-6번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CreditbanksystemreferenceRoomActivity.class);
                        startActivityForResult(intent, 13);
                        //학점은행제 자료실
                        break;
                    case R.id.DVtxtEdu_1:  //일반교육과정
                        //Toast.makeText(MainActivity.this,"4-1번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, GeneralEducationCourseRecruitmentGuideActivity.class);
                        startActivityForResult(intent, 14);
                        //일반교육과정 모집안내
                        break;
                    case R.id.DVtxtEdu_2:
                        //Toast.makeText(MainActivity.this,"4-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, ForeignlanguagecourseActivity.class);
                        startActivityForResult(intent, 15);
                        //외국어 과정
                        break;
                    case R.id.DVtxtEdu_3:
                        //Toast.makeText(MainActivity.this,"4-3번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, CertificatecourseActivity.class);
                        startActivityForResult(intent, 16);
                        //자격증 과정
                        break;
                    case R.id.DVtxtEdu_4:
                        //Toast.makeText(MainActivity.this,"4-4번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, LiberalArtsCourseActivity.class);
                        startActivityForResult(intent, 17);
                        //교양 과정
                        break;
                    case R.id.DVtxtEdu_5:
                        //Toast.makeText(MainActivity.this,"4-5번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, AsanCityLinkageProcessActivity.class);
                        startActivityForResult(intent, 18);
                        //아산시 연계과정
                        break;
                    case R.id.DVtxtEdu_6:
                        //Toast.makeText(MainActivity.this,"4-6번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, GeneralSecurityGuardCourseActivity.class);
                        startActivityForResult(intent, 19);
                        //일반 경비원 과정
                        break;
                    case R.id.DVtxtCommunity_1:
                        //Toast.makeText(MainActivity.this,"5-1번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_NoticeActivity.class);
                        startActivityForResult(intent, 20);
                        //커뮤니티 공지사항
                        break;
                    case R.id.DVtxtCommunity_2:
                        //Toast.makeText(MainActivity.this,"5-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_QuestionAndAnswerActivity.class);
                        startActivityForResult(intent, 21);
                        //커뮤니티 1:1질의 응답
                        break;
                    case R.id.DVtxtCommunity_3:
                        //Toast.makeText(MainActivity.this,"5-3번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_GalleryActivity.class);
                        startActivityForResult(intent, 22);
                        //커뮤니티 갤러리
                        break;
                    case R.id.DVtxtCommunity_4:
                        //Toast.makeText(MainActivity.this,"5-4번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        intent = new Intent(MainActivity.this, Community_FormattingRoomActivity.class);
                        startActivityForResult(intent, 23);
                        //커뮤니티 서식 자료실
                        break;
                    case R.id.DVtxtMypage_1:
                        //Toast.makeText(MainActivity.this,"6-1번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);

                        if (StaticId.id == null || StaticId.id == "") {
                            Toast.makeText(MainActivity.this, "로그인을 하세요", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(MainActivity.this, Login.class);
                            startActivity(intent1);
                        } else if (StaticId.course.equals("일반과정")) {
                            Intent intent1 = new Intent(MainActivity.this, MyPage_MemberInformationManagementActivity.class);
                            startActivity(intent1);
                        } else if (StaticId.course == "학점은행제과정" || StaticId.course.equals("학점은행제과정")) {
                            Intent intent1 = new Intent(MainActivity.this, MyPage_MemberInfomation_Bank_Activity.class);
                            startActivity(intent1);
                        }
                        //intent = new Intent(MainActivity.this, MyPage_MemberInformationManagementActivity.class);
                        //startActivityForResult(intent,24);
                        //마이페이지 회원정보 관리
                        break;
                    case R.id.DVtxtMypage_2:
                        //Toast.makeText(MainActivity.this,"6-2번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        if (StaticId.id == null || StaticId.id == "") {
                            Toast.makeText(MainActivity.this, "로그인을 하세요", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(MainActivity.this, Login.class);
                            startActivity(intent1);
                        } else {
                            intent = new Intent(MainActivity.this, MyPage_CourseDetailsActivity.class);
                            startActivityForResult(intent, 25);
                        }

                        //마이페이지 수강내역
                        break;
                    case R.id.DVtxtMypage_3:
                        //Toast.makeText(MainActivity.this,"6-3번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        if (StaticId.id == null || StaticId.id == "") {
                            Toast.makeText(MainActivity.this, "로그인을 하세요", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(MainActivity.this, Login.class);
                            startActivity(intent1);
                        } else {
                            intent = new Intent(MainActivity.this, MyPage_GradesVerificationActivity.class);
                            startActivityForResult(intent, 26);
                        }
                        //마이페이지 성적확인
                        break;
                    case R.id.DVtxtMypage_4:
                        //Toast.makeText(MainActivity.this,"6-4번",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(drawerView);
                        if (StaticId.id == null || StaticId.id == "") {
                            Toast.makeText(MainActivity.this, "로그인을 하세요", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(MainActivity.this, Login.class);
                            startActivity(intent1);
                        } else {
                            intent = new Intent(MainActivity.this, MyPage_QuestionAndAnswerActivity.class);
                            startActivityForResult(intent, 27);
                        }

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

