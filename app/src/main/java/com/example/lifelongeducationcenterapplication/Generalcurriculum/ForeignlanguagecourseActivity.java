package com.example.lifelongeducationcenterapplication.Generalcurriculum;
import com.example.lifelongeducationcenterapplication.Account.Login;
import com.example.lifelongeducationcenterapplication.Lecture;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RegisterResult;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

//외국어 과정
public class ForeignlanguagecourseActivity extends AppCompatActivity {

    Retrofit retrofit;//httpclient library
    RemoteService rs;//DB를 위한 인터페이스

    Retrofit retrofit2;//httpclient library
    RemoteService rs2;//DB를 위한 인터페이스

    List<Lecture> lectures; // 배열 객체 생성
    ListView listLecture;//리스트뷰
    Button btDetail, btClassRg;
    TextView textName, textPeriod, textProfessor, textTime, textFee;
    MyAdapter adapter;


    // 아이디가 없으면 로그인하라고 로그인 하는 곳으로 이동
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("외국어과정");
        setContentView(R.layout.activity_foreignlanguagecourse);


        listLecture = (ListView) findViewById(R.id.foreignlanguagecourselistLecture);


        adapter = new MyAdapter();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);

        retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs2 = retrofit2.create(RemoteService.class);



    }


    @Override
    protected void onResume() {

            Call<List<Lecture>> call = rs.lecture("외국어과정");//call객체
            call.enqueue(new Callback<List<Lecture>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<Lecture>> call, Response<List<Lecture>> response) {
                    if(response.isSuccessful()){
                        lectures = response.body();
                        adapter.notifyDataSetChanged();
                        listLecture.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Lecture>> call, Throwable t) {
                    System.out.println("JSON 불러오기 실패" +call +" " + t);

                }
            });
        super.onResume();
    }


    class MyAdapter extends BaseAdapter {
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
            convertView = getLayoutInflater().inflate(R.layout.item_foreignlanguagecourse,null);

            Lecture lc =  lectures.get(position);

            String day = lc.getDayOfTheWeek();
            String name = lc.getName();
            String startDate = lc.getStartDate();
            String endDate = lc.getEndDate();
            String startTime = lc.getStartTime();
            String endTime = lc.getEndTime();
            String professor = lc.getProfessor();
            String studyFee = lc.getStudyFee();
            String status = lc.getStatus();
            String course = lc.getDivision();
            int subjectsemester = lc.getSemester();
            int year = lc.getYear();
            int number = lc.getNumber();



            textName = convertView.findViewById(R.id.foreignlanguagecourseName);
            textPeriod = convertView.findViewById(R.id.foreignlanguagecourseLecturePeriod);
            textProfessor = convertView.findViewById(R.id.foreignlanguagecourseLectureProfessor);
            textTime = convertView.findViewById(R.id.foreignlanguagecourseLectureTime);
            textFee = convertView.findViewById(R.id.foreignlanguagecourseLectureStudyfee);

            btClassRg = convertView.findViewById(R.id.foreignlanguagecourseEnrolment1);
            btDetail = convertView.findViewById(R.id.foreignlanguagecourseEnrolment2);


            textName.setText("강좌명  " + name);
            textPeriod.setText("・교육기간  " + startDate + " ~ " + endDate);
            textProfessor.setText("・교수진     " + professor);
            textTime.setText("・수업시간  " + day + " " + startTime +" ~ " + endTime);
            textFee.setText("・학습비     " +studyFee);


            // 수강 불가시 수강신청 버튼 변경
            if(status.equals("수강불가")){
                btClassRg.setBackgroundColor(Color.GRAY);
                btClassRg.setText("수강불가");
                btClassRg.setClickable(false);
            }
            
            // 상세보기 
            btDetail.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(), LearnmoreaboutforeignlanguagecoursesActivity.class);
                    intent.putExtra("number", number);

                    // 해야되는 부분 수강신청을 위해 해야됨
                    startActivity(intent);
                }

            });
            // 일반과정 수강신청
            btClassRg.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(StaticId.id.equals("") || StaticId.id == null){
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        Toast.makeText(getApplicationContext(), "로그인을 해야 수강신청이 가능합니다.", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }else{
                        Call<RegisterResult> call = rs2.userSubjectRegister(StaticId.id, number, year, subjectsemester, course);//call객체
                        call.enqueue(new Callback<RegisterResult>() {//enqueue 메소드 실행
                            @Override
                            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                                if(response.isSuccessful()){
                                    RegisterResult registerResult = response.body();
                                    if(registerResult.getResult().equals("ok")){
                                        Toast.makeText(getApplicationContext(), "수강신청이 되었습니다.", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "이미 수강신청된 강좌입니다..", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RegisterResult> call, Throwable t) {
                                System.out.println("일반과정 수강친청 실패 " +call +" " + t);

                            }
                        });
                    }
                }
            });
            return convertView;
        }
    }




}