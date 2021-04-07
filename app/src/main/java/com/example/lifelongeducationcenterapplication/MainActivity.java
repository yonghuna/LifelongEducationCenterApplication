package com.example.lifelongeducationcenterapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.ArrayList;
import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class MainActivity extends AppCompatActivity {

    TextView DVtxtAccount, DVtxtIntro, DVtxtBank, DVtxtEdu, DVtxtCommunity, DVtxtMypage;
    ListView DVlistAccount, DVlistIntro, DVlistBank, DVlistEdu, DVlistCommunity, DVlistMypage;//메뉴목록

    DrawerLayout drawerLayout;
    LinearLayout drawerView;

    Retrofit retrofit;//httpclient library
    RemoteService rs;//DB를 위한 인터페이스

    List<MainLecture> lectures = new ArrayList<>(); // 배열 객체 생성
    ListView listLecture;//리스트뷰
    /*
    ArrayList<MainLecture> mainLectureArrayList = new ArrayList<>();//테스트용
    //ArrayList<MainLecture> mainLectureArrayList;

     */
    MainLectureAdapter mainLectureAdapter; //어댑터

    //ArrayList<MainLecture> mainLectureArrayList = new ArrayList<>();
    //ArrayList<MainLecture> mainLectureArrayList;
    //MainLectureAdapter mainLectureAdapter;

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

            DVlistAccount = findViewById(R.id.DVlistAccount);
            DVlistIntro = findViewById(R.id.DVlistIntro);
            DVlistBank = findViewById(R.id.DVlistBank);
            DVlistEdu = findViewById(R.id.DVlistEdu);
            DVlistCommunity = findViewById(R.id.DVlistCommunity);
            DVlistMypage = findViewById(R.id.DVlistMypage);

            listset();
            clickTitleMenu();//메뉴설정



            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);//메뉴버튼생성


            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            rs = retrofit.create(RemoteService.class);


        /*
            //mainLectureArrayList = new ArrayList<>();
        MainLecture mainLecture = new MainLecture(result,"11","111","111","1111");
        mainLectureArrayList.add(mainLecture);
         */

            mainLectureAdapter = new MainLectureAdapter();
            listLecture.setAdapter(mainLectureAdapter);
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
            super.onResume();

        }


        /* 샘플데이터
        MainLecture mainLecture = new MainLecture("1","11","111","111","1111");
        mainLectureArrayList.add(mainLecture);


        mainLectureAdapter = new MainLectureAdapter();
        listLecture.setAdapter(mainLectureAdapter);

         */


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


            //LinearLayout abc = findViewById(R.id.abc);
            //LinearLayout listMainLecture = findViewById(R.id.listMainLecture);

            LinearLayout abc = findViewById(R.id.abc);
            LinearLayout listMainLecture = findViewById(R.id.listMainLecture);

            TextView mainLectureName = convertView.findViewById(R.id.mainLectureName);//강좌명

            TextView mainLecturePeriod = convertView.findViewById(R.id.mainLecturePeriod);//교육기간
            TextView mainLectureProfessor = convertView.findViewById(R.id.mainLectureProfessor);//교수진
            TextView mainLectureTime = convertView.findViewById(R.id.mainLectureTime);//수업시간
            TextView mainLectureStudyfee = convertView.findViewById(R.id.mainLectureStudyfee);//학습비

            /*
            mainLectureArrayList.add(new MainLecture("1","11","111","1111","11111"));
            mainLectureArrayList.add(new MainLecture("2","21","211","2111","21111"));
             */


            //MainLecture mainLecture = mainLectureArrayList.get(position);
            MainLecture mainLecture = lectures.get(position);
            System.out.println("확인 : "+ mainLecture.getLectureName()+" "+mainLecture.getLecturePeriod());

            mainLectureName.setText(mainLecture.getLectureName());
            mainLecturePeriod.setText(mainLecture.getLecturePeriod());
            mainLectureProfessor.setText(mainLecture.getLectureProfessor());
            mainLectureTime.setText(mainLecture.getLectureTime());
            mainLectureStudyfee.setText(mainLecture.getLectureStudyfee());


            return convertView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://메뉴를 클릭했을때 메뉴화면이 슬라이드 형식으로 나옴.
                drawerLayout.openDrawer(drawerView);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void listset(){//메뉴 채우기
        final String[] listmenu1 = {"로그인","회원가입"};
        final String[] listmenu2 = {"인사말씀","발전과정 및 연혁","교육목적 및 목표","조직구성","찾아오시는길"};
        final String[] listmenu3 = {"학점은행제란?","입학안내","전공안내","수강신청","학사일정","자료실"};
        final String[] listmenu4 = {"모집안내","외국어과정","자격증과정","교양과정","아산시연계과정","일반경비원과정"};
        final String[] listmenu5 = {"공지사항","1:1질의응답","서식자료실","갤러리"};
        final String[] listmenu6 = {"회원정보관리","수강내역","성적확인","1:1질문"};

        final ArrayList<String[]> arrayListMenu = new ArrayList<>();
        arrayListMenu.add(listmenu1);
        arrayListMenu.add(listmenu2);
        arrayListMenu.add(listmenu3);
        arrayListMenu.add(listmenu4);
        arrayListMenu.add(listmenu5);
        arrayListMenu.add(listmenu6);

        ArrayAdapter adapter;

        for(String[] obj : arrayListMenu){
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,obj);
            if(obj.equals(listmenu1)){
                DVlistAccount.setAdapter(adapter);
                continue;
            }else if(obj.equals(listmenu2)){
                DVlistIntro.setAdapter(adapter);
                continue;
            }else if(obj.equals(listmenu3)){
                DVlistBank.setAdapter(adapter);
                continue;
            }else if(obj.equals(listmenu4)){
                DVlistEdu.setAdapter(adapter);
                continue;
            }else if(obj.equals(listmenu5)){
                DVlistCommunity.setAdapter(adapter);
                continue;
            }else if(obj.equals(listmenu6)){
                DVlistMypage.setAdapter(adapter);
                continue;
            }
        }

    }

    public void clickTitleMenu(){//메뉴의 타이틀을 클릭할때 서브메뉴를 열고 닫을 수 있음.
        DVtxtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DVlistAccount.setVisibility(View.VISIBLE);
                DVlistIntro.setVisibility(View.GONE);
                DVlistBank.setVisibility(View.GONE);
                DVlistEdu.setVisibility(View.GONE);
                DVlistCommunity.setVisibility(View.GONE);
                DVlistMypage.setVisibility(View.GONE);
            }
        });
        DVtxtIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DVlistAccount.setVisibility(View.GONE);
                DVlistIntro.setVisibility(View.VISIBLE);
                DVlistBank.setVisibility(View.GONE);
                DVlistEdu.setVisibility(View.GONE);
                DVlistCommunity.setVisibility(View.GONE);
                DVlistMypage.setVisibility(View.GONE);
            }
        });
        DVtxtBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DVlistAccount.setVisibility(View.GONE);
                DVlistIntro.setVisibility(View.GONE);
                DVlistBank.setVisibility(View.VISIBLE);
                DVlistEdu.setVisibility(View.GONE);
                DVlistCommunity.setVisibility(View.GONE);
                DVlistMypage.setVisibility(View.GONE);
            }
        });
        DVtxtEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DVlistAccount.setVisibility(View.GONE);
                DVlistIntro.setVisibility(View.GONE);
                DVlistBank.setVisibility(View.GONE);
                DVlistEdu.setVisibility(View.VISIBLE);
                DVlistCommunity.setVisibility(View.GONE);
                DVlistMypage.setVisibility(View.GONE);

            }
        });
        DVtxtCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DVlistAccount.setVisibility(View.GONE);
                DVlistIntro.setVisibility(View.GONE);
                DVlistBank.setVisibility(View.GONE);
                DVlistEdu.setVisibility(View.GONE);
                DVlistCommunity.setVisibility(View.VISIBLE);
                DVlistMypage.setVisibility(View.GONE);

            }
        });
        DVtxtMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DVlistAccount.setVisibility(View.GONE);
                DVlistIntro.setVisibility(View.GONE);
                DVlistBank.setVisibility(View.GONE);
                DVlistEdu.setVisibility(View.GONE);
                DVlistCommunity.setVisibility(View.GONE);
                DVlistMypage.setVisibility(View.VISIBLE);

            }
        });
    }
}
